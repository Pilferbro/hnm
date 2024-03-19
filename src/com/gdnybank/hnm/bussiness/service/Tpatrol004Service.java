package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HPatrolDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:  修改巡查任务
 * @author: szm
 */
@Service
public class Tpatrol004Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol004Service.class);

    @Autowired
    HPatrolDao hPatrolDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            if(StringUtils.isEmpty(p.get("patrol_id"))) {
                throw new BusinessException("Tpatrol004ServiceException", "patrol_id不能为空");
            }
            hPatrolDao.updateByPk(p);
            logger.info("Tpatrol004Service执行完成");
            return BaseUtils.map("success","1");
        }catch (Exception e){
            throw new BusinessException("Tpatrol004ServiceException", "更新巡查任务失败");
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
