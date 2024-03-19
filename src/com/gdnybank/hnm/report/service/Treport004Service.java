package com.gdnybank.hnm.report.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HSiteLogDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 查询曲线数据-客户纬度
 *
 * @author: wxm
 */
@Service
public class Treport004Service extends TXBaseService {
    private static final Log logger = LogFactory.getLog(Treport004Service.class);

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private HSiteLogDao hSiteLogDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("query_item"))) {
            logger.error("query_item为必传字段");
            throw new BusinessException("Treport001ServiceException", "query_item为必传字段");
        }

        Map<String,Object> result = new HashMap<String,Object>();

        String query_item = p.get("query_item").toString();
        Date date = new Date();
        if (ObjectUtil.isNotEmpty(p.get("query_date"))) {
            date = DateUtils.parse(p.get("query_date").toString(), "yyyyMMdd");
        }
        String start_date = "";
        String end_date = "";
        if (DateUtils.getDate(date, "yyyyMMdd").equals(DateUtils.getDate(new Date(), "yyyyMMdd"))) {
            //如果为当天 则查询前15天内的
            start_date = getStartDate(date, 14);
            end_date = DateUtils.getDate(date, "yyyyMMdd");
        } else {
            //如果不为当天 则查询前后15天内的
            start_date = getStartDate(date, 7);
            end_date = getEndDate(date, 7);
        }

        List<Object> list = new ArrayList<>();

        List<String> dateList = new ArrayList<>();
        dateList = findDates(DateUtils.parse(start_date, "yyyyMMdd"),DateUtils.parse(end_date, "yyyyMMdd"));

        if ("site_num1".equals(query_item) || "site_num2".equals(query_item) || "site_num3".equals(query_item) ||
                "site_num4".equals(query_item)) {

            for (String cudate : dateList) {
                List<Map<String, Object>> siteList = new ArrayList<>();
                //试营业服务点数
                int site_num1 = 0;
                //试营业（已申请pos）服务点数
                int site_num2 = 0;
                //已开业服务点数
                int site_num3 = 0;
                //已退出服务点数
                int site_num4 = 0;

                if (cudate.equals(DateUtils.getDate(new Date(),"yyyyMMdd"))) {
                    //当天
                    //团队查询
                    if (ObjectUtil.isNotEmpty(p.get("branch_id"))) {
                        //1 先查询其下所有的机构
                        String orgids = hnmCommService.getUserBranchidsBybranch(p.get("branch_id").toString());
                        //2 根据branchIds查询其下所有的服务点
                        siteList = hSiteDao.query(BaseUtils.map("orgids", orgids, "is_delete", "0", "approval_status", "2"));
                    }
                    //客户经理查询
                    if (ObjectUtil.isNotEmpty(p.get("mgr_id"))) {
                        // 根据mgr_id查询其下所有的服务点
                        siteList = hSiteDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"), "is_delete", "0", "approval_status",
                                "2"));
                    }
                    //服务点查询
                    if (ObjectUtil.isNotEmpty(p.get("site_no"))) {
                        // 根据site_no查询其下所有的服务点
                        siteList = hSiteDao.query(BaseUtils.map("site_no", p.get("site_no"), "is_delete", "0", "approval_status",
                                "2"));
                    }
                }else{
                    //其他日期
                    //团队查询
                    if(ObjectUtil.isNotEmpty(p.get("branch_id"))){
                        //1 先查询其下所有的机构
                        String orgids = hnmCommService.getUserBranchidsBybranch(p.get("branch_id").toString());
                        //2 根据branchIds查询其下所有的服务点
                        siteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",cudate));
                    }
                    //客户经理查询
                    if(ObjectUtil.isNotEmpty(p.get("mgr_id"))){
                        // 根据mgr_id查询其下所有的服务点
                        siteList = hSiteLogDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"),"log_time",cudate));
                    }
                    //服务点查询
                    if(ObjectUtil.isNotEmpty(p.get("site_no"))){
                        // 根据site_no查询其下所有的服务点
                        siteList = hSiteLogDao.query(BaseUtils.map("site_no", p.get("site_no"),"log_time",cudate));
                    }
                }
                if(siteList !=null && siteList.size() > 0){
                    for (Map<String, Object> site : siteList) {
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
                }
                if("site_num1".equals(query_item)){
                    list.add(site_num1);
                }
                if("site_num2".equals(query_item)){
                    list.add(site_num2);
                }
                if("site_num3".equals(query_item)){
                    list.add(site_num3);
                }
                if("site_num4".equals(query_item)){
                    list.add(site_num4);
                }
            }
        }else{
            List<Map<String, Object>> siteList = new ArrayList<>();
            //团队查询
            if (ObjectUtil.isNotEmpty(p.get("branch_id"))) {
                //1 先查询其下所有的机构
                String orgids = hnmCommService.getUserBranchidsBybranch(p.get("branch_id").toString());
                //2 根据branchIds查询其下所有的服务点
                siteList = hSiteDao.query(BaseUtils.map("orgids", orgids, "is_delete", "0", "approval_status", "2"));
            }
            //客户经理查询
            if (ObjectUtil.isNotEmpty(p.get("mgr_id"))) {
                // 根据mgr_id查询其下所有的服务点
                siteList = hSiteDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"), "is_delete", "0", "approval_status",
                        "2"));
            }
            //服务点查询
            if (ObjectUtil.isNotEmpty(p.get("site_no"))) {
                // 根据site_no查询其下所有的服务点
                siteList = hSiteDao.query(BaseUtils.map("site_no", p.get("site_no"), "is_delete", "0", "approval_status",
                        "2"));
            }

            if (siteList != null && siteList.size() > 0) {
                Map<String, Object> parms = new HashMap<>();
                String siteIds = "";
                for (Map<String, Object> site : siteList) {
                    siteIds += ",'" + site.get("site_no") + "'";
                }
                if (siteIds.startsWith(",")) {
                    siteIds = siteIds.substring(1, siteIds.length());
                }
                parms.put("siteIds", siteIds);

                if ("amt1".equals(query_item) || "amt2".equals(query_item) || "amt3".equals(query_item) || "amt4".equals(query_item)) {

                    if ("amt1".equals(query_item)) {
                        for (String cudate : dateList) {
                            //资产余额
                            BigDecimal amt1 = BigDecimal.ZERO;

                            date = DateUtils.parse(cudate, "yyyyMMdd");
                            //获取当前月份
                            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                            parms.put("acctday", yyyyMM);
                            //获取当前日期所在月的天数
                            int day = DateUtil.dayOfMonth(date);
                            String day_str = String.valueOf(day);
                            if (day_str.length() == 1) {
                                day_str = "0" + day_str;
                            }
                            String onlbal = "onlbal" + day_str;
                            parms.put("onlbal", onlbal);
                            //查询资产信息
                            List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);
                            if (list1 != null && list1.size() > 0) {
                                for (Map<String, Object> dataMap : list1) {
                                    if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                                        amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                    }
                                }
                            }
                            list.add(hnmCommService.dealVal(amt1));
                        }
                    }

                    if ("amt2".equals(query_item)) {
                        for (String cudate : dateList) {
                            //资产日均
                            BigDecimal amt2 = BigDecimal.ZERO;

                            date = DateUtils.parse(cudate, "yyyyMMdd");
                            //获取当前月份
                            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                            parms.put("acctday", yyyyMM);
                            //获取当前日期所在月的天数
                            int day = DateUtil.dayOfMonth(date);

                            //获取当年日期在今年的天数
                            int dayOfYear = DateUtil.dayOfYear(date);
                            StringBuilder sq1 = new StringBuilder("yearaccum");
                            for (int n = day + 1; n <= 31; n++) {
                                String n_str = String.valueOf(n);
                                if (n_str.length() == 1) {
                                    n_str = "0" + n_str;
                                }
                                sq1 = sq1.append("-onlbal").append(n_str);
                            }
                            parms.put("sq1", sq1.toString());
                            parms.put("dayOfYear", dayOfYear);

                            //查询资产信息
                            List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);
                            if (list1 != null && list1.size() > 0) {
                                for (Map<String, Object> dataMap : list1) {
                                    if (ObjectUtil.isNotEmpty(dataMap.get("amt2"))) {
                                        amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                                    }
                                }
                            }
                            list.add(hnmCommService.dealVal(amt2));
                        }
                    }

                    if ("amt3".equals(query_item)) {
                        for (String cudate : dateList) {
                            //存款余额
                            BigDecimal amt3 = BigDecimal.ZERO;

                            date = DateUtils.parse(cudate, "yyyyMMdd");
                            //获取当前月份
                            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                            parms.put("acctday", yyyyMM);
                            //获取当前日期所在月的天数
                            int day = DateUtil.dayOfMonth(date);
                            String day_str = String.valueOf(day);
                            if (day_str.length() == 1) {
                                day_str = "0" + day_str;
                            }
                            String onlbal = "onlbal" + day_str;
                            parms.put("onlbal", onlbal);
                            //查询存款信息
                            List<Map<String, Object>> list1 = hnmCommService.qureyCK(parms);
                            if (list1 != null && list1.size() > 0) {
                                for (Map<String, Object> dataMap : list1) {
                                    if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                                        amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                    }
                                }
                            }
                            list.add(hnmCommService.dealVal(amt3));
                        }
                    }

                    if ("amt4".equals(query_item)) {
                        for (String cudate : dateList) {
                            //存款日均
                            BigDecimal amt4 = BigDecimal.ZERO;

                            date = DateUtils.parse(cudate, "yyyyMMdd");
                            //获取当前月份
                            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                            parms.put("acctday", yyyyMM);
                            //获取当前日期所在月的天数
                            int day = DateUtil.dayOfMonth(date);

                            //获取当年日期在今年的天数
                            int dayOfYear = DateUtil.dayOfYear(date);
                            StringBuilder sq1 = new StringBuilder("yearaccum");
                            for (int n = day + 1; n <= 31; n++) {
                                String n_str = String.valueOf(n);
                                if (n_str.length() == 1) {
                                    n_str = "0" + n_str;
                                }
                                sq1 = sq1.append("-onlbal").append(n_str);
                            }
                            parms.put("sq1", sq1.toString());
                            parms.put("dayOfYear", dayOfYear);

                            //查询存款信息
                            List<Map<String, Object>> list1 = hnmCommService.qureyCK(parms);
                            if (list1 != null && list1.size() > 0) {
                                for (Map<String, Object> dataMap : list1) {
                                    if (ObjectUtil.isNotEmpty(dataMap.get("amt2"))) {
                                        amt4 = amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                                    }
                                }
                            }
                            list.add(hnmCommService.dealVal(amt4));
                        }
                    }
                }

                if ("cus_num1".equals(query_item) || "cus_num2".equals(query_item) || "cus_num3".equals(query_item) ||
                        "cus_num4".equals(query_item)) {

                    if ("cus_num1".equals(query_item)) {
                        for (String cudate : dateList) {
                            //所有客户数
                            int cus_num1 = 0;
                            parms.put("nowdate",cudate);
                            List<Map<String, Object>> list1 = hnmCommService.qureyCus(parms);
                            cus_num1 = list1.size();
                            list.add(cus_num1);
                        }
                    }else{
                        for (String cudate : dateList) {
                            //高净值
                            int cus_num2 = 0;
                            //价值客户
                            int cus_num3 = 0;
                            //有效户
                            int cus_num4 = 0;

                            date = DateUtils.parse(cudate, "yyyyMMdd");
                            //获取当前月份
                            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                            parms.put("acctday",yyyyMM);
                            //获取当前日期
                            int day = DateUtil.dayOfMonth(date);
                            String day_str = String.valueOf(day);
                            if(day_str.length() == 1){
                                day_str = "0"+day_str;
                            }
                            String quararg = "quaraccum"+day_str;
                            parms.put("quararg",quararg);

                            //查询资产信息
                            List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);
                            if(list1 != null && list1.size()>0){
                                for (Map<String, Object> dataMap : list1){
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

                            if("cus_num2".equals(query_item)){
                                list.add(cus_num2);
                            }
                            if("cus_num3".equals(query_item)){
                                list.add(cus_num3);
                            }
                            if("cus_num4".equals(query_item)){
                                list.add(cus_num4);
                            }
                        }
                    }
                }
            }
        }
        result.put("dataList",list);
        result.put("dateList",dateList);
        logger.info("Treport004Service执行完成");
        return result;
    }


    public String getStartDate(Date date, int day) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String startDate = df.format(date.getTime() - day * 24 * 60 * 60 * 1000);
        return startDate;

    }

    public String getEndDate(Date date, int day) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String endDate = df.format(date.getTime() + day * 24 * 60 * 60 * 1000);
        return endDate;
    }

    public List<String> findDates(Date dBegin, Date dEnd){
        List<String> lDate = new ArrayList();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

}
