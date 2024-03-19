package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  新增活动
 * @author: szm
 */
@Service
public class Tevent002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tevent002Service.class);

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
            if(p.get("site_id").toString().indexOf(",") !=-1){
                String idstr = p.get("site_id").toString();
                String[] sideIds = idstr.substring(1,idstr.length()-1).split(",");
                for(int i = 0;i<sideIds.length;i++){
                    //按照规则生成id
                    String id = IdUtil.randomUUID().replace("-", "");
                    p.put("event_id", id);
                    p.put("site_id",sideIds[i].trim());
                    //查询站点对应客户经理
                    p.put("account_id",getAccountId(p));
                    eventDao.save(delkong(p));
                }
            }else{
                //按照规则生成id
                String id = IdUtil.randomUUID().replace("-", "");
                p.put("event_id", id);
                p.put("account_id",getAccountId(p));
                eventDao.save(delkong(p));
            }


            logger.info("Tevent002Service执行完成");
            return BaseUtils.map("success", "1");
        }catch (Exception e){
            throw new BusinessException("Tevent002ServiceException", "新增活动失败");
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
