package com.gdnybank.hnm.esb.service;

import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Tesb001Service extends TXBaseService {

    Logger log = LoggerFactory.getLogger(Tesb001Service.class);
    @Autowired
    private HSiteDao hSiteDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        log.info("服务点查询start");
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.putAll(p);
        queryMap.put("is_delete","0");
        queryMap.put("approval_status","2");
        List<Map<String, Object>> list = hSiteDao.queryForListToEsb(queryMap);
        if(list != null && list.size() > 0){
            returnMap = list.get(0);
        }
        log.info("服务点查询end");
        return returnMap;

    }
}
