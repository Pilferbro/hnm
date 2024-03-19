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
 * @desc   查询机构下面的所有客户经理列表(包括团队负责人/片区负责人/分行)
 */
@Service
public class Tstaff007Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        List<Map<String, Object>> list = new ArrayList<>();
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            throw new BusinessException("Tstaff007ServiceException", "登陆人员未配置角色");
        }
        String rolelevels = "'2','3','4'";
        p.put("rolelevels",rolelevels);
        p.put("deleted",0);
        //总行级可以看所有
        if(userRoleLevel!=1){
            String branchids = hnmCommService.getUserBranchids();
            p.put("branchids",branchids);
            list = sysAccountDao.queryForListBycondition(p);
        }else{
            list = sysAccountDao.queryForListBycondition(p);
        }

        return list;
    }
}
