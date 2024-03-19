package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.SysAccountRoleDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 用户详细信息查询
 */
@Service
public class Tuser008Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private SysAccountRoleDao sysAccountRoleDao;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {
        if(StringUtils.isEmpty(p.get("account_id"))){
            throw new BusinessException("Tuser008Service001","用户编号为必传字段");
        }
        Map<String,Object> userMap =new HashMap<String,Object>();
        List<Map<String, Object>> userList = sysAccountDao.queryForList(BaseUtils.map("account_id", p.get("account_id")));
        List<Map<String, Object>> roleList = sysAccountRoleDao.queryUserRole(BaseUtils.map("account_id", p.get("account_id")));
        if(userList != null && userList.size()>0){
            userMap.put("user",userList.get(0));
        }
        userMap.put("roleList",roleList);

        return userMap;
    }
}
