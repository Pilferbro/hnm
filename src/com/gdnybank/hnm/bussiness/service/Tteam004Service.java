package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @desc   查询机构（所有，不含团队）
 */
@Service
public class Tteam004Service extends TXBaseService {

    @Autowired
    private SysBranchDao sysBranchDao;
    @Resource
    private SysParamService sysParamService;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        List<Map<String, Object>> branchList = sysBranchDao.queryForListByconditionAll(BaseUtils.map("team_flag","0"));
        if(branchList != null && branchList.size()>0){
            String branchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_BRANCHID", "9999");
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
