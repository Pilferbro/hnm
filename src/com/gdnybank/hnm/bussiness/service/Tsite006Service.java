package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  站点下拉列表
 * @author: zjh
 */
@Service
public class Tsite006Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite006Service.class);

    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String , Object> params = new HashMap<String , Object>();
        try{
            //校验角色   总行级可以看所有
            String userId = MfpContextHolder.getLoginId();
            int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
            if(userRoleLevel==0){
                logger.error("登陆人员"+userId+"未配置角色");
                throw new BusinessException("Tstaff001ServiceException", "登陆人员未配置角色");
            }
            if(userRoleLevel != 1){
                String branchids = hnmCommService.getUserBranchids();

                if(ObjectUtil.isNotEmpty(p.get("all_flag")) && "1".equals(p.get("all_flag"))){
                    //落地支行
                    params.put("allOrgids", branchids);
                }else if(ObjectUtil.isNotEmpty(p.get("zh_flag")) && "1".equals(p.get("zh_flag"))){
                    //落地支行
                    params.put("branchids", branchids);
                }else{
                    //所属机构
                    params.put("orgids", branchids);
                }
            }
            if(userRoleLevel == 4){
                //非管理员只能查看自己的
                params.put("mgr_id", userId);
            }
            params.put("is_delete","0");
            params.put("approval_status","2");
            List<Map<String , Object>> resultList = siteDao.queryLesList(params);

            resultMap.put("siteList",resultList);
            return resultMap;
        }catch (Exception e){
            throw new BusinessException("Tsite006ServiceException", "网络异常");
        }

    }

}
