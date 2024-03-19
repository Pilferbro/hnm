package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.SysAccountRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  人员管理详情
 * @author: zjh
 */
@Service
public class Tstaff003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tstaff003Service.class);

    @Autowired
    HManagerDao managerDao;
    @Autowired
    private SysAccountRoleDao sysAccountRoleDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HSiteDao hSiteDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> staffMap =new HashMap<String,Object>();
            if(ObjectUtil.isEmpty(p.get("id"))){
                throw new BusinessException("Tstaff003ServiceException","id为必传字段");
            }
            List<Map<String, Object>> staffList = managerDao.queryForList(p);
            if(staffList != null && staffList.size()>0){
                Map<String, Object> map = staffList.get(0);

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

                // 根据mgr_id查询其下所有的服务点
                List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("mgr_id", map.get("mgr_id"),"is_delete","0","approval_status","2"));
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

                staffMap.put("staffDetail",map);
                List<Map<String, Object>> roleList = sysAccountRoleDao.queryUserRole(BaseUtils.map("account_id", map.get("mgr_id")));
                staffMap.put("roleList",roleList);
                if(ObjectUtil.isNotEmpty(p.get("req_place"))){
                    //来自我的申请及我的审批页面及我的沟通页面
                    if("1".equals(String.valueOf(p.get("req_place"))) || "2".equals(String.valueOf(p.get("req_place"))) || "3".equals(String.valueOf(p.get("req_place")))){
                        if(ObjectUtil.isNotEmpty(map.get("temp_role_id"))){
                            List<Map<String, Object>> list = sysRoleDao.queryForList(BaseUtils.map("role_id",
                                    map.get("temp_role_id")));
                            staffMap.put("roleList",list);
                        }
                    }
                }
            }

            return staffMap;
        }catch (Exception e){
            throw new BusinessException("Tstaff003ServiceException", "查询人员详情失败");
        }

    }


}
