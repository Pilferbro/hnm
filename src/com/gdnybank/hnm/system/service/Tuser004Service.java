package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 新增用户时查询角色
 */
@Service
public class Tuser004Service extends TXBaseService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        String userId = MfpContextHolder.getLoginId();
        int userRole = hnmCommService.getUserRole(BaseUtils.map("account_id", userId));
        if(userRole==0){
            throw new BusinessException("Tuser004ServiceException", "登陆人员未配置角色");
        }
        List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
        //超级管理员 总行管理员
        String super_manager = MfpContextHolder.getSysParam("role", "super_manager");
        String head_manager = MfpContextHolder.getSysParam("role", "head_manager");
        if(userRole==1 || userRole==2){
            roleList = sysRoleDao.queryAdminRole(BaseUtils.map("role_type","1","role_id1", super_manager));
        }else{
            roleList = sysRoleDao.queryAdminRole(BaseUtils.map("role_type","1","role_id1", super_manager,"role_id2", head_manager));
            //throw new BusinessException("Tuser004Service001","当前角色无权限访问此功能");
        }
        return roleList;
    }
}
