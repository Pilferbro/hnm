package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
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
 * @description:  站点暂存详情
 * @author: wxm
 */
@Service
public class Tsite008Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite008Service.class);

    @Autowired
    HSiteDao siteDao;
    @Autowired
    HFileInfoDao fileInfoDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> siteMap =new HashMap<String,Object>();

            List<Map<String, Object>> siteList = siteDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId()
                    , "temp_save", "1"));

            if(siteList != null && siteList.size() > 0){
                Map<String, Object> map = siteList.get(0);
                siteMap.put("siteDetail",map);
                //图片信息
                List<Map<String, Object>> fileList = fileInfoDao.queryForList(BaseUtils.map("busi_id",
                        map.get("id")));
                siteMap.put("fileList",fileList);
            }
            return siteMap;
        }catch (Exception e){
            throw new BusinessException("Tsite008ServiceException", "查询站点暂存详情失败");
        }
    }
}
