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
 * @description:  查询活动详细
 * @author: szm
 */
@Service
public class Tevent005Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tevent005Service.class);

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
                throw new BusinessException("Tevent005ServiceException", "event_id不能为空");
            }
            List<Map<String, Object>> list = eventDao.queryForList(delkong(p));
            if(list == null || list.size() == 0){
                throw new BusinessException("Tevent005ServiceException", "查无此活动");
            }
            logger.info("---------------->"+list);
            logger.info("Tevent005Service执行完成");
            return BaseUtils.map("msg",list);
        }catch (Exception e){
            throw new BusinessException("Tevent005ServiceException", "查询活动明细失败");
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
