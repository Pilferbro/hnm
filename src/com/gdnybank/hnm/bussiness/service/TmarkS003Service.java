package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HQuitMarkSheelDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  评分表查询
 * @author: szm
 */
@Service
public class TmarkS003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(TmarkS003Service.class);

    @Autowired
    private HQuitMarkSheelDao markSheelDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //查询一级
            Map<String,Object> parms = new HashMap<>();
            parms.put("orderBy","mark_sheel_id");
            parms.put("mlevel","1");
            List<Map<String, Object>> projmap =  markSheelDao.queryForList(parms);
            //查询二级
            parms.put("mlevel","2");
            List<Map<String, Object>> contmap =  markSheelDao.queryForList(parms);


            groupMap(projmap,contmap);
            resultMap.put("markSheelList",projmap);
            return resultMap;
        }catch (Exception e){
            logger.info("查询退出巡查条款失败",e);
            throw new BusinessException("TmarkS003ServiceException", "查询退出巡查条款失败");
        }

    }
    //下级菜单放到上级菜单下list
    public List<Map<String,Object>> groupMap(List<Map<String,Object>> preData,List<Map<String,Object>> beData){
        for (Map<String,Object> preItem  : preData) {
            List<Map<String, Object>> tempList =  new ArrayList<>();
            for (Map<String,Object> beItem  : beData) {
                if(beItem.get("parent_id").toString().equals(preItem.get("mark_sheel_id"))){
                    tempList.add(beItem);
                }
            }
            preItem.put("list",tempList);
        }
        return preData;
    }
}
