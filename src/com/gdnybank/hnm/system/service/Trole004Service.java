package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleMenuDao;
import com.gdnybank.hnm.pub.dao.SysRoleMobileMenuDao;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.service.OperationFlowRecordService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色修改
 *
 * @Package: com.nantian.paymng.system
 * @author: wxm
 */
@Service
public class Trole004Service extends TXBaseService {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private OperationFlowRecordService recordService;
    @Autowired
    private SysRoleMobileMenuDao sysRoleMobileMenuDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (StringUtils.isEmpty(p.get("role_id"))) {
            throw new BusinessException("Trole004ServiceException", "角色编号没有上传");
        }
        if (ObjectUtil.isEmpty(p.get("approval_type"))) {
            throw new BusinessException("Trole004ServiceServiceException", "approval_type为必传字段");
        }

        String roleId = p.get("role_id").toString();
        String approvalType = p.get("approval_type").toString();
        String useUp = p.get("use_up").toString();
        boolean flag = false;
        if (ObjectUtil.isNotEmpty(p.get("req_place"))) {
            String req_place = p.get("req_place").toString();
            if ("1".equals(req_place)) {
                //由我的申请页面过来
                flag = true;
            }
        }
        if (flag) {
//            //由我的申请页面过来
            if (ObjectUtil.isNotEmpty(p.get("apply_id"))) {
                String applyId = p.get("apply_id").toString();
                List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("id", applyId));
                if (list != null && list.size() > 0) {
                    //修改保存角色信息
                    roleDao.updateTempByPk(p);
                    dealEnum(p);
                    //更新申请表状态
                    Map<String, Object> parms = new HashMap<>();
                    parms.put("approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus());
                    parms.put("approval_status_name", ApprovalStatusEnum.STATUS_1.getApprovalStatusName());
                    parms.put("id", applyId);
                    parms.put("approval_type", approvalType);
                    tApprovalApplyDao.updateStatus(parms);
                } else {
                    throw new BusinessException("Trole002ServiceException", "未查到该申请记录");
                }
            } else {
                throw new BusinessException("Trole002ServiceException", "apply_id为必传字段");
            }
            //保存操作记录
            p.put("suggestion", p.get("suggestion"));
            recordService.saveOperationRecord(p);
        } else {
            p.put("creator", env.get("userid"));
            p.put("update_id", roleId);
            p.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            roleDao.saveForRoleId(p);
            dealEnum(p);
            //审批逻辑
            if ("0".equals(useUp)) {
                //未启用审批
                hnmCommService.saveApprovalApplyNoProcess(p, BaseUtils.map("approval_type",
                        p.get("approval_type"), "relation_id", p.get("role_id"), "operator", MfpContextHolder.getLoginId(),
                        "approval_user", "admin", "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));
            } else {
                //启用审批 走之前的流程
                //审批逻辑
                hnmCommService.saveApprovalApply(p, BaseUtils.map("approval_type",
                        approvalType, "relation_id", p.get("role_id"), "operator", MfpContextHolder.getLoginId(),
                        "approval_user", p.get("approval_user"), "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));
            }
            //更改审核状态
            if (approvalType.equals(ApprovalTypeEnum.TYPE025.getApprovalType())) {
                //查询
                List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("relation_id", p.get("role_id"), "approval_type", approvalType, "approval_status", "2"));
                if (list != null && list.size() > 0) {
                    List<Map<String, Object>> tempList = roleDao.queryTemp(BaseUtils.map("role_id", p.get("role_id")));
                    Map<String, Object> roleMap = tempList.get(0);
                    roleMap.put("role_id",roleId);
                    roleDao.updateByPk(roleMap);
                    List<Map<String, Object>> menuList = sysRoleMenuDao.queryForList(BaseUtils.map("role_id", p.get("role_id")));
                    List<Map<String, Object>> mobilemenuList = sysRoleMobileMenuDao.queryForList(BaseUtils.map("role_id", p.get("role_id")));

                    List<Map<String, Object>> param = new ArrayList<>();
                    for (Map<String, Object> map : menuList) {
                        map.put("role_id", roleId);
                        param.add(map);
                    }
                    sysRoleMenuDao.saveList(param);

                    List<Map<String, Object>> mobileparam = new ArrayList<>();
                    for (Map<String, Object> map : mobilemenuList) {
                        map.put("role_id", roleId);
                        mobileparam.add(map);
                    }
                    sysRoleMobileMenuDao.saveList(mobileparam);
                    logger.info("Trole004Service执行完成");
                }
            }
        }

        return BaseUtils.map("success", "1");
    }
    private void dealEnum(Map<String, Object> paramMap) {
        List<Map<String, Object>> param = new ArrayList<>();
        List<Map<String, Object>> menuList = (List<Map<String, Object>>) paramMap.get("menuids");//用户提交的菜单列表

        logger.info("-------------------menuList:" + menuList);
        for (int i = 0; i < menuList.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            map.put("role_id", paramMap.get("role_id"));
            map.put("menu_code", menuList.get(i));
            param.add(map);
        }
        logger.info("--------------------->param:" + param);
        //先删除再新增
        sysRoleMenuDao.deleteByPk(BaseUtils.map("role_id", paramMap.get("role_id")));
        int[] result = sysRoleMenuDao.saveList(param);

        List<Map<String, Object>> mobileparam = new ArrayList<>();
        //用户提交的手机端菜单列表
        List<Map<String, Object>> mobilemenuList = (List<Map<String, Object>>) paramMap.get("mobile_menuids");

        logger.info("-------------------mobilemenuList:" + mobilemenuList);
        for (int i = 0; i < mobilemenuList.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            map.put("role_id", paramMap.get("role_id"));
            map.put("menu_code", mobilemenuList.get(i));
            mobileparam.add(map);
        }
        logger.info("--------------------->mobileparam:" + mobileparam);
        //先删除再新增
        sysRoleMobileMenuDao.deleteByPk(BaseUtils.map("role_id", paramMap.get("role_id")));
        int[] mobileresult = sysRoleMobileMenuDao.saveList(mobileparam);
    }
}
