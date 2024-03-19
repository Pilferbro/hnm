package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @description: 站点新增 暂存
 * @author: wxm
 */
@Service
public class Tsite007Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite007Service.class);

    @Autowired
    HSiteDao siteDao;
    @Autowired
    HFileInfoDao hFileInfoDao;
    @Autowired
    HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        //查询
        List<Map<String, Object>> list = siteDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId()
                , "temp_save", "1"));
        String id = "";
        if(list != null && list.size()>0){
            id = list.get(0).get("id").toString();
            p.put("id", id);
            p.put("status", "0");//试营业
            p.put("creator",MfpContextHolder.getLoginId());
            p.put("create_time", list.get(0).get("create_time"));
            p.put("update_time", DateUtil.now());
            p.put("is_delete", "0");
            p.put("temp_save","1");//暂存标志  0非暂存  1暂存
            siteDao.updateByPk(p);
        }else{
            //按照规则生成id
            id = IdUtil.randomUUID().replace("-", "");
            p.put("id", id);
            p.put("status", "0");//试营业
            p.put("creator",MfpContextHolder.getLoginId());
            String createTime = DateUtil.now();
            p.put("create_time", createTime);
            p.put("update_time", createTime);
            p.put("is_delete", "0");
            p.put("temp_save","1");//暂存标志  0非暂存  1暂存
            siteDao.save(p);
        }

        //更新图片资料
        hFileInfoDao.updateBusiId(BaseUtils.map("busi_id",id));

        //新增图片资料
        if(ObjectUtil.isNotEmpty(p.get("fileIdList"))){
            List<String> fileIdList = (List<String>)p.get("fileIdList");
            if(fileIdList != null && fileIdList.size() >0){
                String ids = "";
                for(String fileId : fileIdList){
                    ids+=",'"+fileId+"'";
                }
                if(StringUtils.isNotEmpty(ids) && ids.startsWith(",")){
                    ids=ids.substring(1, ids.length());
                    hFileInfoDao.updateByPkS(BaseUtils.map("ids",ids,"busi_id",id));
                }
            }
        }

        logger.info("Tsite007Service执行完成");
        return BaseUtils.map("success", "1");
    }
}
