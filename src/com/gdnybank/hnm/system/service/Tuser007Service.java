package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc  用户列表查询
 */
@Service
public class Tuser007Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        Object account_id = p.get("account_id");
        Object phone = p.get("phone");
        Object name = p.get("name");
        Object branch_id = p.get("branch_id");
        Object role_id = p.get("role_id");

        if(!StringUtils.isEmpty(phone)){
            p.put("phone","%"+phone+"%");
        }else{
            p.remove("phone");
        }
        if(StringUtils.isEmpty(account_id)){
            p.remove("account_id");
        }
        if(!StringUtils.isEmpty(name)){
            p.put("name","%"+name+"%");
        }else{
            p.remove("name");
        }
        if(StringUtils.isEmpty(branch_id)){
            p.remove("branch_id");
        }
        if(StringUtils.isEmpty(role_id)){
            p.remove("role_id");
        }

        //校验角色   总行级可以看所有
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            throw new BusinessException("Tuser007ServiceException", "登陆人员未配置角色");
        }
        if(userRoleLevel != 1){
            String branchids = hnmCommService.getUserBranchids();
            p.put("branchids", branchids);
        }
        if(userRoleLevel == 4){//客户经理级
            p.put("account_id", userId);
        }

        if(ObjectUtil.isNotEmpty(p.get("bankid"))){
            p.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
        }

        Map<String,Object> userMap = new HashMap<String,Object>();
        int page = Integer.parseInt(p.get("page").toString());
        int number = Integer.parseInt(p.get("number").toString());
        PageInfo pageInfo=new PageInfo();
        pageInfo.setIDisplayStart(page*number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);

        p.put("deleted",0);
        List<Map<String, Object>> userList = sysAccountDao.queryForListByconditionBypage(p);

        long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
        userMap.put("total",toatl);
        userMap.put("userList",userList);

        return userMap;
    }
}
