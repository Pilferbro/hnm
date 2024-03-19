package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.FilesManageService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import com.sunyard.ecm.server.bean.FileBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  站点详情
 * @author: zjh
 */
@Service
public class Tsite003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite003Service.class);

    @Autowired
    HSiteDao siteDao;
    @Autowired
    HFileInfoDao fileInfoDao;
    @Resource
    private FilesManageService filesManageService;
    @Autowired
    private HnmCommService hnmCommService;



    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> siteMap =new HashMap<String,Object>();
            if(ObjectUtil.isEmpty(p.get("id"))){
                throw new BusinessException("Tsite003ServiceException","id为必传字段");
            }
            List<Map<String, Object>> siteList = siteDao.queryForList(p);

            if(siteList != null && siteList.size() > 0){
                Map<String, Object> map = siteList.get(0);

                //资产余额
                BigDecimal amt1 = BigDecimal.ZERO;
                //资产日均
                BigDecimal amt2 = BigDecimal.ZERO;
                //存款余额
                BigDecimal amt3 = BigDecimal.ZERO;
                //存款日均
                BigDecimal amt4 = BigDecimal.ZERO;

                //所有客户数
                int cus_num1 = 0;
                //高净值
                int cus_num2 = 0;
                //价值客户
                int cus_num3 = 0;
                //有效户
                int cus_num4 = 0;

                String siteIds = "'"+map.get("site_no")+"'";

                // 根据服务点查询
                Map<String , Object> parms = new HashMap<>();
                parms.put("siteIds",siteIds);
                //获取当前日期
                Date date = new Date();

                //查询昨天的数据
                date = DateUtils.addDay(date,-1);

                //获取当前月份
                String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                parms.put("acctday",yyyyMM);
                //获取当前日期
                int day = DateUtil.dayOfMonth(date);
                String day_str = String.valueOf(day);
                if(day_str.length() == 1){
                    day_str = "0"+day_str;
                }
                //获取当年日期在今年的天数
                int dayOfYear = DateUtil.dayOfYear(date);

                String onlbal = "onlbal"+day_str;
                parms.put("onlbal",onlbal);

                String quararg = "quaraccum"+day_str;
                parms.put("quararg",quararg);

                StringBuilder sq1 = new StringBuilder("yearaccum");

                for(int n=day+1; n<=31; n++){
                    String n_str = String.valueOf(n);
                    if(n_str.length() == 1){
                        n_str = "0"+n_str;
                    }
                    sq1 = sq1.append("-onlbal").append(n_str);
                }
                parms.put("sq1",sq1.toString());
                parms.put("dayOfYear",dayOfYear);

                //查询资产信息
                List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);

                //查询存款信息
                List<Map<String, Object>> list2 = hnmCommService.qureyCK(parms);

                //查询客户信息
                String nowdate = DateUtils.getDate(date, "yyyyMMdd");
                parms.put("nowdate",nowdate);
                List<Map<String, Object>> list3 = hnmCommService.qureyCus(parms);
                cus_num1 = list3.size();

                //统计数值
                if(list1 != null && list1.size() > 0){
                    for (Map<String, Object> dataMap : list1){
                        if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
                            amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                        }
                        if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
                            amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                        }
                        if(ObjectUtil.isNotEmpty(dataMap.get("quararg"))){
                            double quarArg = Double.valueOf(dataMap.get("quararg").toString());
                            if(quarArg >= 1000000){
                                cus_num2 ++;
                            }else if(quarArg < 1000000 && quarArg >= 100000){
                                cus_num3 ++;
                            }else if(quarArg < 100000 && quarArg >= 1000){
                                cus_num4 ++;
                            }
                        }
                    }
                }

                if(list2 != null && list2.size() > 0){
                    for (Map<String, Object> dataMap : list2){
                        if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
                            amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                        }
                        if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
                            amt4 = amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                        }
                    }
                }

                //业绩数据
                map.put("amt1",hnmCommService.dealVal(amt1));
                map.put("amt2",hnmCommService.dealVal(amt2));
                map.put("amt3",hnmCommService.dealVal(amt3));
                map.put("amt4",hnmCommService.dealVal(amt4));
                //客户数据
                map.put("cus_num1",cus_num1);
                map.put("cus_num2",cus_num2);
                map.put("cus_num3",cus_num3);
                map.put("cus_num4",cus_num4);

                siteMap.put("siteDetail",map);

                //图片信息
                List<Map<String, Object>> fileList = fileInfoDao.queryForList(BaseUtils.map("busi_id",
                        p.get("id")));
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
                siteMap.put("fileList",fileList);
            }
            return siteMap;
        }catch (Exception e){
            throw new BusinessException("Tsite003ServiceException", "网络异常");
        }

    }

    private String changeTimePattern(String operTime) {
        String[] s = operTime.split("-");
        String operDate = s[0] + s[1] + s[2].substring(0, 2);
        return operDate;
    }

}
