package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
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
 * @desc 查询机构下用户列表
 */
@Service
public class Tuser012Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if (userRoleLevel == 0) {
            throw new BusinessException("Tstaff006ServiceException", "登陆人员未配置角色");
        }
        if (p.containsKey("all")){
            userRoleLevel = 1;
        }
        //总行级可以看所有
        if (userRoleLevel != 1) {
            String branchids = hnmCommService.getUserBranchids();
            p.put("branchids", branchids);
        }


        return sysAccountDao.queryForListBycondition(p);
    }
}
