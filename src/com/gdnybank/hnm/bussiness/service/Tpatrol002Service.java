package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  新增巡查任务
 * @author: szm
 */
@Service
public class Tpatrol002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol002Service.class);

    @Autowired
    HPatrolDao hPatrolDao;

    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            if(ObjectUtil.isEmpty(p.get("site_id"))){
                throw new BusinessException("Tpatrol002ServiceException","服务点site_id为必传字段");
            }
            if(ObjectUtil.isEmpty(p.get("account_id"))){
                throw new BusinessException("Tpatrol002ServiceException","巡查人account_id为必传字段");
            }
            if(ObjectUtil.isEmpty(p.get("inspect_time"))){
                throw new BusinessException("Tpatrol002ServiceException","巡查时间inspect_time为必传字段");
            }
           Map<String, Object> resulMap = new HashMap<>();

            if(p.get("site_id").toString().indexOf(",") !=-1){
                String idstr = p.get("site_id").toString();
                String[] sideIds = idstr.substring(1,idstr.length()-1).split(",");
                for (String sideId : sideIds) {
                    //按照规则生成id
                    String id = IdUtil.randomUUID().replace("-", "");
                    p.put("patrol_id", id);
                    p.put("site_id", sideId.trim());
                    String patrol_id = hPatrolDao.saveAndReturnID(delkong(p));
                    resulMap.put("patrol_id",patrol_id);
                }
            }else{
                //按照规则生成id
                String id = IdUtil.randomUUID().replace("-", "");
                p.put("patrol_id", id);
                String patrol_id =  hPatrolDao.saveAndReturnID(delkong(p));
                resulMap.put("patrol_id",patrol_id);
            }

            logger.info("Tpatrol002Service执行完成");
            return resulMap;
        }catch (Exception e){
            throw new BusinessException("Tpatrol002ServiceException", "新增巡查任务失败");
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
