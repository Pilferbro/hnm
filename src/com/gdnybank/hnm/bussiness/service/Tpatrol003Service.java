package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HPatrolDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogDao;
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
 * @description:  删除巡查任务
 * @author: szm
 */
@Service
public class Tpatrol003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol003Service.class);

    @Autowired
    HPatrolDao hPatrolDao;

    @Autowired
    HPatrolLogDao patrolLogDao;

    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HPatrolLogContentDao patrolLogContentDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            if(StringUtils.isEmpty(p.get("patrol_id"))) {
                throw new BusinessException("Tpatrol003ServiceException", "patrol_id不能为空");
            }

            //删除主表
            hPatrolDao.deleteByPk(p);
            //删除对应巡查记录
            List<Map<String, Object>> logList = patrolLogDao.queryForList(p);
            if(logList != null && logList.size() > 0){
                for(Map<String, Object> logItem : logList){
                    patrolLogDao.deleteByPk(logItem);
                }
            }
            patrolLogContentDao.deleteByPatrolId(p);
            logger.info("Tpatrol003Service执行完成");
            return BaseUtils.map("success","1");
        }catch (Exception e){
            throw new BusinessException("Tpatrol003ServiceException", "删除巡查任务失败");
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
