package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
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
 * @description: 新增/修改助农取款业务凭证
 * @author: wxm
 */
@Service
public class Tvouch005Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tvouch005Service.class);

    @Autowired
    private TVoucherDao tVoucherDao;
    @Autowired
    private TVoucherFlowDao tVoucherFlowDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("tsdt"))) {
            throw new BusinessException("Tvouch005ServiceException", "tsdt为必传字段");
        }
        if (ObjectUtil.isEmpty(p.get("site_no"))) {
            throw new BusinessException("Tvouch005ServiceException", "site_no为必传字段");
        }
        if (ObjectUtil.isEmpty(p.get("gd_result"))) {
            throw new BusinessException("Tvouch005ServiceException", "gd_result为必传字段");
        }

        String id ;

        List<Map<String, Object>> list = tVoucherDao.queryForList(BaseUtils.map("tsdt", p.get("tsdt"), "site_no",
                p.get("site_no")));

        String time = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
        if(list != null && list.size()>0){
            //修改
            Map<String, Object> map = list.get(0);
            id = map.get("id").toString();
            map.put("gd_result",p.get("gd_result"));
            map.put("file_ids",p.get("file_ids"));
            map.put("update_time",time);
            map.put("remarks",p.get("remarks"));
            map.put("creator", MfpContextHolder.getLoginId());
            tVoucherDao.updateByPk(map);
        }else{
            //新增
            id = IdUtil.randomUUID().replace("-", "");
            p.put("id",id);
            p.put("create_time",time);
            p.put("update_time",time);
            p.put("creator", MfpContextHolder.getLoginId());
            tVoucherDao.save(p);
        }

        //流水业务凭证
        if (ObjectUtil.isNotEmpty(p.get("voucher_result_list"))) {
            List<Map<String, Object>> voucherResultList = (List<Map<String, Object>>)p.get("voucher_result_list");
            for(Map<String, Object> map : voucherResultList){
                //先查询
                List<Map<String, Object>> mapList = tVoucherFlowDao.queryForList(BaseUtils.map("tsdt", map.get("tsdt"),
                        "site_no",map.get("site_no"),"f011",map.get("f011"),"prid",map.get("prid")));
                if(mapList != null && mapList.size() > 0){
                    //更新
                    Map<String, Object> nodeMap = mapList.get(0);
                    nodeMap.put("voucher_result",map.get("voucher_result"));
                    nodeMap.put("file_ids",map.get("file_ids"));
                    nodeMap.put("update_time",time);
                    nodeMap.put("site_no",p.get("site_no"));
                    nodeMap.put("creator", MfpContextHolder.getLoginId());
                    tVoucherFlowDao.updateByPk(nodeMap);
                }else{
                    //新增
                    map.put("id",IdUtil.randomUUID().replace("-", ""));
                    map.put("site_no",p.get("site_no"));
                    map.put("create_time",time);
                    map.put("update_time",time);
                    map.put("creator", MfpContextHolder.getLoginId());
                    tVoucherFlowDao.save(map);
                }
            }
        }

        //问题清单
        //根据id查询
        List<Map<String, Object>> quelist = hPatrolLogContentDao.queryForListAll(BaseUtils.map("patrol_id", id, "source"
                , "3", "isdeleted", "0"));
        if(ObjectUtil.isEmpty(p.get("questionList"))){
            if(quelist != null && quelist.size() > 0){
                for(Map<String, Object> map : quelist){
                    hPatrolLogContentDao.updateByPk(BaseUtils.map("id",map.get("id"),"isdeleted","1","updatetime",time));
                }
            }
        }else{
            List<Map<String, Object>> questionList = (List<Map<String, Object>>)p.get("questionList");
            if(questionList != null && questionList.size() > 0){
                StringBuffer sb = new StringBuffer();
                for(Map<String, Object> map : questionList){
                    if(ObjectUtil.isNotEmpty(map.get("gd_id"))){
                        sb.append(map.get("gd_id")).append(",");
                    }
                }
                String gd_ids = sb.toString();
                if(quelist != null && quelist.size() > 0){
                    for(Map<String, Object> map : quelist){
                        if(!gd_ids.contains(map.get("id")+",")){
                            hPatrolLogContentDao.updateByPk(BaseUtils.map("id",map.get("id"),"isdeleted","1","updatetime",time));
                        }
                    }
                }
            }

            if(questionList != null && questionList.size() > 0){
                int i = 101;
                for(Map<String, Object> map : questionList){
                    if(ObjectUtil.isNotEmpty(map.get("gd_id"))){
                        //修改
                        Map<String, Object> savemap = new HashMap<>();
                        savemap.put("id",map.get("gd_id"));
                        savemap.put("patrol_id",id);
                        savemap.put("site_no",map.get("site_no"));
                        savemap.put("indexs",i);
                        savemap.put("content_text",map.get("content_text"));
                        savemap.put("risk_level",map.get("risk_level"));
                        savemap.put("end_date",map.get("end_date"));
                        savemap.put("requirement",map.get("requirement"));
                        savemap.put("updatetime",time);
                        savemap.put("responsible",map.get("responsible"));
                        savemap.put("source","3");
                        savemap.put("isdeleted","0");
                        savemap.put("pj_classify",map.get("pj_classify"));
                        savemap.put("discoverer",MfpContextHolder.getLoginId());
                        hPatrolLogContentDao.updateByPk(savemap);
                    }else{
                        //新增
                        Map<String, Object> savemap = new HashMap<>();
                        savemap.put("patrol_id",id);
                        savemap.put("site_no",map.get("site_no"));
                        savemap.put("indexs",i);
                        savemap.put("content_text",map.get("content_text"));
                        savemap.put("risk_level",map.get("risk_level"));
                        savemap.put("end_date",map.get("end_date"));
                        savemap.put("requirement",map.get("requirement"));
                        savemap.put("created",time);
                        savemap.put("updatetime",time);
                        savemap.put("responsible",map.get("responsible"));
                        savemap.put("source","3");
                        savemap.put("isdeleted","0");
                        savemap.put("pj_classify",map.get("pj_classify"));
                        savemap.put("discoverer",MfpContextHolder.getLoginId());
                        hPatrolLogContentDao.save(savemap);
                    }
                    i ++;
                }
            }
        }

        logger.info("Tvouch005Service执行完成");
        return BaseUtils.map("success", "1");
    }
}
