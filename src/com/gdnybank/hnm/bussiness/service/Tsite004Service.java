package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @description:  录入图片详情
 * @author: zjh
 */
@Service
public class Tsite004Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite004Service.class);

    @Autowired
    HFileInfoDao fileInfoDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            List<Map<String, Object>> fileList = fileInfoDao.queryForList(p);
            if (fileList.size()>1) return false;
        }catch (Exception e){
            throw new BusinessException("Tsite004ServiceException", "网络异常");
        }
        return true;
    }


}
