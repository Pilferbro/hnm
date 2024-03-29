package com.gdnybank.hnm.system.service;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc  修改用户信息
 */
@Service
public class Tuser002Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private SysAccountRoleDao sysAccountRoleDao;
    @Autowired
    private HManagerDao hManagerDao;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    HnmCommService hnmCommService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(StringUtils.isEmpty(p.get("account_id"))){
            throw new BusinessException("Tuser002ServiceException","用户编号为必传字段");
        }
        p.put("creator",env.get("userid"));

        List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
        List<String> roleList = (List<String>) p.get("roleids");
        String temp_role_id = "";
        boolean flag = false;
        for(int i=0;i<roleList.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            String role_id = roleList.get(i);
            map.put("account_id",p.get("account_id"));
            map.put("role_id",role_id);
            paramList.add(map);
            temp_role_id = role_id;

            //校验角色级别
            String userRoleLevel = "";
            List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", role_id));
            if(mapList != null && mapList.size() >0){
                userRoleLevel = mapList.get(0).get("role_level").toString();
            }
            if("2".equals(userRoleLevel) || "3".equals(userRoleLevel) || "4".equals(userRoleLevel)){
                flag = true;
            }

            //非总行级角色需要校验所属机构
            if(!"1".equals(userRoleLevel)){
                if(ObjectUtil.isEmpty(p.get("branch_id"))){
                    throw new BusinessException("Tuser002ServiceException","请选择用户所属机构");
                }
            }
        }

        //更新用户信息
        sysAccountDao.updateByPk(p);
        //更新用户角色信息
        sysAccountRoleDao.delete(BaseUtils.map("account_id",p.get("account_id")));
        sysAccountRoleDao.saveList(paramList);

        if(flag){
            Map<String, Object> rmap = new HashMap<>();
            String id = IdUtil.randomUUID().replace("-", "");
            //先查询
            List<Map<String, Object>> list = hManagerDao.queryList(BaseUtils.map("mgr_id", p.get("account_id"),
                    "is_delete", "0", "approval_status", "2"));
            if(list != null && list.size() >0){
                //修改
                //保存客户经理表
                Map<String, Object> map = list.get(0);
                rmap.putAll(map);
                rmap.putAll(p);
                rmap.put("id",id);
                rmap.put("mgr_id",p.get("account_id"));
                rmap.put("mgr_name",p.get("name"));
                rmap.put("e_mail",p.get("email"));
                rmap.put("create_time", DateUtil.now());
                rmap.put("update_time", DateUtil.now());
                rmap.put("is_delete","0");
                rmap.put("temp_role_id",temp_role_id);
                hManagerDao.save(rmap);
                //保存申请表
                String suggestion = p.get("name") + "人员基本信息调整申请";
                hnmCommService.saveApprovalApplyNoProcess(p,BaseUtils.map("approval_type",
                        ApprovalTypeEnum.TYPE003.getApprovalType(), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),"suggestion",suggestion));
            }else{
                //新增
                //保存客户经理表
                p.put("id",id);
                p.put("mgr_id",p.get("account_id"));
                p.put("mgr_name",p.get("name"));
                p.put("e_mail",p.get("email"));
                p.put("create_time",DateUtil.now());
                p.put("update_time",DateUtil.now());
                p.put("is_delete","0");
                p.put("temp_role_id",temp_role_id);
                hManagerDao.save(p);
                //保存申请表
                String suggestion = p.get("name") + "人员新增申请";
                hnmCommService.saveApprovalApplyNoProcess(p,BaseUtils.map("approval_type",
                        ApprovalTypeEnum.TYPE002.getApprovalType(), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),"suggestion",suggestion));
            }
            List<Map<String, Object>> managerList = hManagerDao.queryList(BaseUtils.map(
                    "mgr_id", p.get("account_id"),"approval_status","2"));
            for (Map<String, Object> managerMap : managerList){
                String managerMapId = managerMap.get("id").toString();
                if (id.equals(managerMapId)) continue;
                hManagerDao.updateByPk(BaseUtils.map("id",managerMapId,"is_delete","1"));
            }
        }else{
            //逻辑删除客户经理表
            String account_id = "'"+p.get("account_id")+"'";
            hManagerDao.updateStatus("1",account_id);
        }

        return BaseUtils.map("success","1");

    }
}
