package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HTeamDao;
import com.gdnybank.hnm.pub.dao.TperprpDao;
import com.gdnybank.hnm.pub.dao.TtimpntDao;
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

import java.math.BigDecimal;
import java.util.*;

/**
 * @description:  团队/片区详情
 * @author: wxm
 */
@Service
public class Tteam003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tteam003Service.class);

    @Autowired
    HTeamDao hTeamDao;

    @Autowired
    private HnmCommService hnmCommService;

    @Autowired
    private HSiteDao hSiteDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> teamMap =new HashMap<String,Object>();
            if(ObjectUtil.isEmpty(p.get("id"))){
                throw new BusinessException("Tteam003ServiceException","id为必传字段");
            }
            List<Map<String, Object>> teamList = hTeamDao.queryForListDetail(p);

            if(teamList != null && teamList.size()>0){
                Map<String, Object> map = teamList.get(0);

                //试营业数
                int site_num1 = 0;
                //试营业（已申请pos数）
                int site_num2 = 0;
                //已开业数
                int site_num3 = 0;
                //已退出数
                int site_num4 = 0;

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


                //根据团队号查询其下所有的服务点数据
                if(ObjectUtil.isNotEmpty(map.get("branch_id"))){
                    // 先查询其下所有的机构
                    String orgids = hnmCommService.getUserBranchidsBybranch(map.get("branch_id").toString());
                    // 根据branchIds查询其下所有的服务点
                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));

                    if(siteList != null && siteList.size() > 0){
                        String siteIds = "";
                        for (Map<String, Object> site : siteList) {
                            siteIds+=",'"+site.get("site_no")+"'";

                            if("0".equals(site.get("status"))){
                                site_num1 ++;
                            }
                            if("1".equals(site.get("status"))){
                                site_num2 ++;
                            }
                            if("2".equals(site.get("status"))){
                                site_num3 ++;
                            }
                            if("3".equals(site.get("status"))){
                                site_num4 ++;
                            }
                        }
                        if(siteIds.startsWith(",")){
                            siteIds=siteIds.substring(1, siteIds.length());
                        }

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
                //拓展数据
                map.put("site_num1",site_num1);
                map.put("site_num2",site_num2);
                map.put("site_num3",site_num3);
                map.put("site_num4",site_num4);

                teamMap.put("teamDetail",map);
            }
            return teamMap;
        }catch (Exception e){
            throw new BusinessException("Tteam003ServiceException", "查询团队/片区详情失败");
        }
    }
}
