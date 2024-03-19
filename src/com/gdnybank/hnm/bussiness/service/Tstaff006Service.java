package com.gdnybank.hnm.bussiness.service;

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
 * @desc   查询机构下面的所有客户经理---未用到 留存
 */
@Service
public class Tstaff006Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        List<Map<String, Object>> leaderList = new ArrayList<>();
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            throw new BusinessException("Tstaff006ServiceException", "登陆人员未配置角色");
        }
        //总行级可以看所有
        p.put("role_level","4");
        p.put("deleted",0);
        if(userRoleLevel!=1){
//            String branchids = hnmCommService.getUserBranchids();
            p.put("branchids",hnmCommService.getUserBranchids());
            leaderList = sysAccountDao.queryForListBycondition(p);
        }else{
            leaderList = sysAccountDao.queryForListBycondition(p);
        }

        return leaderList;
    }
}
