package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HEventDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
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
 * @description:  活动列表
 * @author: szm
 */
@Service
public class Tevent001Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tevent001Service.class);

    @Autowired
    HEventDao eventDao;

    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String,Object> eventMap = new HashMap<String,Object>();
        try{
            //校验角色   总行级可以看所有
            String userId = MfpContextHolder.getLoginId();
            int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
            if(userRoleLevel==0){
                logger.error("登陆人员"+userId+"未配置角色");
                throw new BusinessException("Tevent001ServiceException", "登陆人员未配置角色");
            }

            if(userRoleLevel != 1){
                String branchids = hnmCommService.getUserBranchids();
                p.put("orgids", branchids);
            }

            if(userRoleLevel == 4){
                //非管理员只能查看自己的
                p.put("account_id",userId);
            }
            //分页信息
            setPageInfo(p);
            List<Map<String , Object>> resultList = eventDao.queryForListByPage(delkong(p));

            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            eventMap.put("total",toatl);
            eventMap.put("eventList",resultList);
            return eventMap;
        }catch (Exception e){
            throw new BusinessException("Tevent001ServiceException", "查询活动列表失败");
        }

    }

    private void setPageInfo(Map<String, Object> p){
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page"))?p.get("page").toString():"0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number"))?p.get("number").toString():"10");
        PageInfo pageInfo=new PageInfo();
        pageInfo.setIDisplayStart(page*number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }

    private void add(Object object, Map<String, Object> p,String name) {
        if(object!=null){
            p.put(name, "%"+object.toString()+"%");
        }

    }

    public Map<String,Object> delkong(Map<String,Object> data){
        Map<String,Object> dataMap = new HashMap<String , Object>();
        for (String key  : data.keySet()) {
            if(data.get(key) !=null && !"".equals(data.get(key))){
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
}
