package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.TVoucherFlowDao;
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
 * @description:  查询流水业务凭证详情
 * @author: wxm
 */
@Service
public class Tvouch006Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tvouch006Service.class);

    @Autowired
    private TVoucherFlowDao tVoucherFlowDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            if(StringUtils.isEmpty(p.get("tsdt"))) {
                throw new BusinessException("Tvouch006ServiceException", "tsdt不能为空");
            }
            if(StringUtils.isEmpty(p.get("site_no"))) {
                throw new BusinessException("Tvouch006ServiceException", "site_no不能为空");
            }
            if(StringUtils.isEmpty(p.get("f011"))) {
                throw new BusinessException("Tvouch006ServiceException", "f011不能为空");
            }
            if(StringUtils.isEmpty(p.get("prid"))) {
                throw new BusinessException("Tvouch006ServiceException", "prid不能为空");
            }

            List<Map<String, Object>> list = tVoucherFlowDao.queryForList(delkong(p));
            logger.info("---------------->"+list);
            logger.info("Tvouch006Service执行完成");
            return BaseUtils.map("msg",list);
        }catch (Exception e){
            throw new BusinessException("Tvouch006ServiceException", "查询流水业务凭证详情失败");
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
