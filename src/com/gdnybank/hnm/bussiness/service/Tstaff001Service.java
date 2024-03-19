package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  人员管理列表
 * @author: zjh
 */
@Service
public class Tstaff001Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tstaff001Service.class);

    @Autowired
    HManagerDao managerDao;

    @Autowired
    HnmCommService hnmCommService;

    @Autowired
    private HSiteDao hSiteDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String,Object> staffMap = new HashMap<String,Object>();
        Map<String , Object> params = new HashMap<String , Object>();
        try{

            Object mgrName = p.get("mgr_name");
            if(ObjectUtil.isNotEmpty(mgrName)){
                params.put("mgr_name", mgrName);
            }
            Object phone = p.get("phone");
            if(ObjectUtil.isNotEmpty(phone)){
                params.put("phone", phone);
            }
            Object mgrId = p.get("mgr_id");
            if(ObjectUtil.isNotEmpty(mgrId)){
                params.put("mgr_id", mgrId);
            }
            Object role_id = p.get("role_id");
            if(ObjectUtil.isNotEmpty(role_id)){
                params.put("role_id", role_id);
            }

            //校验角色   总行级可以看所有
            String userId = MfpContextHolder.getLoginId();
            int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
            if(userRoleLevel==0){
                logger.error("登陆人员"+userId+"未配置角色");
                throw new BusinessException("Tstaff001ServiceException", "登陆人员未配置角色");
            }
            if(userRoleLevel != 1){
                String branchids = hnmCommService.getUserBranchids();
                params.put("branchids", branchids);
            }
            if(userRoleLevel == 4){
                params.put("mgr_id", userId);
            }
            if(ObjectUtil.isNotEmpty(p.get("bankid"))){
                params.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
            }

            params.put("is_delete","0");
            params.put("approval_status","2");
            setPageInfo(p);

            List<Map<String , Object>> resultList = managerDao.queryForListByPageForManageQuery(params);

            if(resultList != null && resultList.size()>0){
                for(Map<String , Object> map : resultList){
                    int site_num1 = 0;
                    int site_num2 = 0;

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

                    map.put("staff_show","0");
                    if(ObjectUtil.isNotEmpty(map.get("mgr_id"))){
                        List<Map<String, Object>> mapList = managerDao.queryList(BaseUtils.map("mgr_id",
                                map.get("mgr_id"), "approval_status", "1"));
                        //有在审批中的
                        if(mapList!=null && mapList.size()>0){
                            map.put("staff_show","1");
                        }

                        // 根据mgr_id查询其下所有的服务点
                        List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("mgr_id", map.get("mgr_id"),"is_delete","0","approval_status","2"));

                        if(siteList != null && siteList.size() > 0){
                            String siteIds = "";
                            for (Map<String, Object> site : siteList) {
                                siteIds+=",'"+site.get("site_no")+"'";

                                if("0".equals(site.get("status")) || "1".equals(site.get("status"))){
                                    site_num1 ++;
                                }
                                if("2".equals(site.get("status"))){
                                    site_num2 ++;
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
                    //拓展数据
                    map.put("site_num1",site_num1);
                    map.put("site_num2",site_num2);
                    //客户数据
                    map.put("cus_num1",cus_num1);
                }
            }

            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            staffMap.put("total",toatl);
            staffMap.put("userList",resultList);
            return staffMap;
        }catch (Exception e){
            throw new BusinessException("Tstaff001ServiceException", "查询人员列表失败");
        }

    }

    private void setPageInfo(Map<String, Object> p){
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page"))?p.get("page").toString():"0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number"))?p.get("number").toString():"10");
        PageInfo pageInfo=new PageInfo();
        pageInfo.setIDisplayStart(page*number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }
}
