package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc   根据机构号查询下的所有团队
 */
@Service
public class Tteam005Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tteam005Service.class);

    @Autowired
    private SysBranchDao sysBranchDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        List<Map<String, Object>> teamList = new ArrayList<>();
        //校验角色   总行级可以看所有
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            logger.error("登陆人员"+userId+"未配置角色");
            throw new BusinessException("Tteam001ServiceException", "登陆人员未配置角色");
        }
        if(userRoleLevel != 1){
            String branchids = hnmCommService.getUserBranchids();
            teamList = sysBranchDao.queryForListByconditionAll(BaseUtils.map("branchids",branchids,"team_flag","1"));
        }else{
            teamList = sysBranchDao.queryForListByconditionAll(BaseUtils.map("team_flag","1"));
        }

        return teamList;
    }
}
