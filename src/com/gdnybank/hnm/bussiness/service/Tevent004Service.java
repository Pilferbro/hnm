package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HEventDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  修改活动
 * @author: szm
 */
@Service
public class Tevent004Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tevent004Service.class);

    @Autowired
    HEventDao eventDao;

    @Autowired
    HSiteDao siteDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            if(StringUtils.isEmpty(p.get("event_id"))) {
                throw new BusinessException("Tevent004ServiceException", "event_id不能为空");
            }
            p.put("account_id",getAccountId(p));
            eventDao.updateByPk(p);
            logger.info("Tevent004Service执行完成");
            return BaseUtils.map("success","1");
        }catch (Exception e){
            throw new BusinessException("Tevent004ServiceException", "更新活动失败");
        }

    }

    public String getAccountId(Map<String, Object> p){
        Map<String , Object> params = new HashMap<String , Object>();
        params.put("is_delete","0");
        params.put("approval_status","2");
        params.put("site_no",p.get("site_id"));
        List<Map<String , Object>> resultList = siteDao.queryForList(params);
        return  resultList.get(0).get("MGR_ID").toString();
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
