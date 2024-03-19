package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HEventDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
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
 * @description:  删除活动
 * @author: szm
 */
@Service
public class Tevent003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tevent003Service.class);

    @Autowired
    HEventDao eventDao;

    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            if(StringUtils.isEmpty(p.get("event_id"))) {
                throw new BusinessException("Tevent003ServiceException", "event_id不能为空");
            }

            //删除主表
            eventDao.deleteByPk(p);
            logger.info("Tevent003Service执行完成");
            return BaseUtils.map("success","1");
        }catch (Exception e){
            throw new BusinessException("Tevent003ServiceException", "删除活动失败");
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
