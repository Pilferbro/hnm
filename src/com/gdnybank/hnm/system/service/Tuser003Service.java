package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc   新增修改用户时查询机构
 */
@Service
public class Tuser003Service extends TXBaseService {

    @Autowired
    private SysBranchDao sysBranchDao;
    @Autowired
    private HnmCommService hnmCommService;
    @Resource
    private SysParamService sysParamService;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        List<Map<String, Object>> branchList = new ArrayList<>();
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            throw new BusinessException("Tuser003ServiceException", "登陆人员未配置角色");
        }
        //总行级可以看所有
        if(userRoleLevel!=1){
            String branchids = hnmCommService.getUserBranchids();
            branchList = sysBranchDao.queryForListByconditionAll(BaseUtils.map("branchids",branchids));
        }else{
            branchList = sysBranchDao.queryForListByconditionAll(BaseUtils.map());
        }

        if(branchList != null && branchList.size()>0){
            String branchId = "";
            if(userRoleLevel!=1){
                branchId = hnmCommService.getUserBranchid();
            }else{
                branchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_BRANCHID", "9999");
            }
            //将顶级机构的上级机构置空
            for (Map<String, Object> map : branchList){
                if(branchId.equals(map.get("branch_id"))){
                    map.put("porgid",null);
                    break;
                }
            }
        }

        return branchList;
    }
}
