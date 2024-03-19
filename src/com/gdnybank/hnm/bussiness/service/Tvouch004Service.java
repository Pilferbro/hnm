package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.service.FilesManageService;
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

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @description:  查询助农取款业务凭证详情
 * @author: wxm
 */
@Service
public class Tvouch004Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tvouch004Service.class);

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HFileInfoDao fileInfoDao;
    @Autowired
    private TVoucherDao tVoucherDao;
    @Autowired
    private TVoucherFlowDao tVoucherFlowDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> returnMap =new HashMap<String,Object>();
            if(ObjectUtil.isEmpty(p.get("tsdt"))){
                throw new BusinessException("Tvouch004ServiceException","tsdt为必传字段");
            }
            if(ObjectUtil.isEmpty(p.get("site_no"))){
                throw new BusinessException("Tvouch004ServiceException","site_no为必传字段");
            }
            List<Map<String, Object>> list = tVoucherDao.queryForList(p);

            if(list != null && list.size() > 0){
                Map<String, Object> map = list.get(0);

                if(ObjectUtil.isNotEmpty(map.get("file_ids"))){
                    String file_ids = String.valueOf(map.get("file_ids"));

                    String[] file_ids_arr = file_ids.split(",");
                    StringBuffer sb = new StringBuffer();
                    for(String file_id : file_ids_arr){
                        sb.append("'").append(file_id).append("',");
                    }

                    file_ids = sb.substring(0,sb.length()-1);

                    //图片信息
                    List<Map<String, Object>> fileList = fileInfoDao.queryForListByIds(BaseUtils.map("ids", file_ids));
                    returnMap.put("fileList",fileList);
                    /*if(fileList !=null && fileList.size() >0){
                        for(Map<String, Object> file : fileList){
                            if(ObjectUtil.isNotEmpty(file.get("batchid"))){
                                Object returnObj = filesManageService.queryFilePathBybatchId(
                                        file.get("batchid").toString(),
                                        changeTimePattern(file.get("create_time").toString()));
                                List<FileBean> fileBeanList = null;
                                if (returnObj instanceof List) {
                                    fileBeanList = (List<FileBean>) returnObj;
                                }
                                if (fileBeanList != null && fileBeanList.size() > 0) {
                                    if (fileBeanList.get(0).getFileFormat().equals("mp4")) {
                                        file.put("file_url", URLEncoder.encode(fileBeanList.get(0).getUrl(), "UTF-8"));
                                    } else{
                                        file.put("file_url", fileBeanList.get(0).getUrl());
                                    }
                                }
                            }
                        }
                    }*/
                }

                //查询流水业务凭证
                List<Map<String, Object>> list2 = tVoucherFlowDao.queryForList(p);
                if(list2 != null && list2.size() > 0){
                    List<Map<String, Object>> voucher_result_list = new ArrayList<>();
                    for (Map<String, Object> map2 : list2){
                        Map<String, Object> voucherResultMap = new HashMap<>();
                        voucherResultMap.put("tsdt",map2.get("tsdt"));
                        voucherResultMap.put("site_no",map2.get("site_no"));
                        voucherResultMap.put("f011",map2.get("f011"));
                        voucherResultMap.put("file_ids",map2.get("file_ids"));
                        voucherResultMap.put("prid",map2.get("prid"));
                        voucherResultMap.put("voucher_result",map2.get("voucher_result"));
                        voucher_result_list.add(voucherResultMap);
                    }
                    returnMap.put("voucher_result_list",voucher_result_list);
                }

                //查询问题清单
                List<Map<String, Object>> list3 = hPatrolLogContentDao.queryForListAll(BaseUtils.map("patrol_id",
                        map.get("id"),"source", "3", "isdeleted", "0"));

                if(list3 != null && list3.size() > 0){
                    List<Map<String, Object>> questionList = new ArrayList<>();
                    int i = 1;
                    for (Map<String, Object> map3 : list3){
                        Map<String, Object> questionMap = new HashMap<>();
                        questionMap.put("id",i);
                        questionMap.put("gd_id",map3.get("id"));
                        questionMap.put("status",map3.get("status"));
                        questionMap.put("site_no",map3.get("site_no"));
                        questionMap.put("pj_classify",map3.get("pj_classify"));
                        questionMap.put("pj_classify_name","定期巡检");
                        questionMap.put("content_text",map3.get("content_text"));
                        questionMap.put("risk_level",map3.get("risk_level"));
                        questionMap.put("end_date",map3.get("end_date"));
                        questionMap.put("requirement",map3.get("requirement"));
                        questionMap.put("responsible",map3.get("responsible"));
                        questionMap.put("responsible_name",map3.get("responsible_name"));
                        questionList.add(questionMap);
                        i++;
                    }
                    returnMap.put("questionList",questionList);
                }

                returnMap.put("voucherDetail",map);
            }
            return returnMap;
        }catch (Exception e){
            throw new BusinessException("Tvouch004ServiceException", "查询助农取款业务凭证详情失败");
        }
    }
}
