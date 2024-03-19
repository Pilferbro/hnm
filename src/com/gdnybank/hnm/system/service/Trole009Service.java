package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleMenuDao;
import com.gdnybank.hnm.pub.dao.SysRoleMobileMenuDao;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 查询角色在途审批工单
 */
@Service
public class Trole009Service extends TXBaseService {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {


        if (ObjectUtil.isEmpty(p.get("approval_type"))) {
            throw new BusinessException("Trole009ServiceException", "审批类型不能为空");
        }
        List<Map<String, Object>> queryRole;
        Map<String, Object> resultMap = null;
        boolean flag = false;
        if (ObjectUtil.isNotEmpty(p.get("req_place"))) {
            String reqPlace = p.get("req_place").toString();
            if ("1".equals(reqPlace)) {
                //由我的申请页面过来
                flag = true;
            }
        }

        if (ApprovalTypeEnum.TYPE026.getApprovalType().equals(p.get("approval_type"))||ApprovalTypeEnum.TYPE025.getApprovalType().equals(p.get("approval_type"))) {
            if (ObjectUtil.isEmpty(p.get("role_id"))) {
                throw new BusinessException("Trole009ServiceException", "角色编号不能为空");
            }
            //查询临时表的update_id对应的role_id
            List<Map<String, Object>> temp;
            if (flag) {
                temp = sysRoleDao.queryTemp(BaseUtils.map("update_id", p.get("update_id")));
            } else {
                temp = sysRoleDao.queryTemp(BaseUtils.map("update_id", p.get("role_id")));
            }
            if (temp != null && temp.size() > 0) {
                for (Map<String, Object> map : temp) {
                    queryRole = tApprovalApplyDao.queryForRoleList(BaseUtils.map("relation_id", map.get("role_id"), "approval_type", "'025','026'", "approval_status", "1"));
                    if (queryRole != null && queryRole.size() > 0) {
                        resultMap = queryRole.get(0);
                        break;
                    }
                }
            }

        } else {
            if (ObjectUtil.isEmpty(p.get("role_name"))) {
                throw new BusinessException("Trole009ServiceException", "角色名称不能为空");
            }
            //检查角色是否存在
            List<Map<String, Object>> roleList = sysRoleDao.queryForList(BaseUtils.map("role_name", p.get("role_name")));
            if (roleList != null && roleList.size() > 0) {
                throw new BusinessException("Trole009ServiceException", "已存在角色：" + roleList.get(0).get("role_name") + ",请勿重复新增！");
            }

            List<Map<String, Object>> temp = sysRoleDao.queryTemp(BaseUtils.map("role_name", p.get("role_name")));
            if (temp != null && temp.size() > 0) {
                for (Map<String, Object> map : temp) {
                    queryRole = tApprovalApplyDao.queryForList(BaseUtils.map("relation_id", map.get("role_id"), "approval_type", p.get("approval_type"), "approval_status", "1"));
                    if (queryRole != null && queryRole.size() > 0) {
                        resultMap = queryRole.get(0);
                        break;
                    }
                }
            }
        }

        if (resultMap != null && resultMap.size() > 0) {
            throw new BusinessException("Trole009ServiceException", "已存在审核工单：" + resultMap.get("id") + ",请勿重复提交！");
        }
        return BaseUtils.map("success", "1");
    }
}
