package com.gdnybank.hnm.report.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 资产业绩报表-客户纬度
 *
 * @author: wxm
 */
@Service
public class Treport005Service extends TXBaseService {
    private static final Log logger = LogFactory.getLog(Treport005Service.class);

    @Autowired
    private SysBranchDao sysBranchDao;
    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private HManagerDao hManagerDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("query_level"))) {
            logger.error("query_level为必传字段");
            throw new BusinessException("Treport005ServiceException", "query_level为必传字段");
        }
        String query_level = p.get("query_level").toString();

        if (ObjectUtil.isEmpty(p.get("query_org"))) {
            logger.error("query_org为必传字段");
            throw new BusinessException("Treport005ServiceException", "query_org为必传字段");
        }
        String query_org = p.get("query_org").toString();

        Date date = new Date();
        if (ObjectUtil.isNotEmpty(p.get("query_date"))) {
            date = DateUtils.parse(p.get("query_date").toString(), "yyyyMMdd");
        }

        String query_branch = "";
        if (ObjectUtil.isNotEmpty(p.get("bankid"))) {
            query_branch = p.get("bankid").toString();
        }

        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if (userRoleLevel == 0) {
            throw new BusinessException("Treport005ServiceException", "登陆人员未配置角色");
        }

        String branchids = "";
        //总行级可以看所有
        if (userRoleLevel != 1) {
            branchids = hnmCommService.getUserBranchids();
        }

        if(userRoleLevel == 4){
            //客户经理级别
            p.put("mgr_id",userId);
        }

        if (!StringUtils.isEmpty(query_branch)) {
            branchids = hnmCommService.getUserBranchidsBybranch(query_branch);
        }

        PageInfo pageInfo = new PageInfo();
        int page = Integer.parseInt(p.get("page").toString());
        int num = Integer.parseInt(p.get("number").toString());
        pageInfo.setIDisplayStart(page * num);
        pageInfo.setIDisplayLength(num);
        MfpContextHolder.setPageInfo(pageInfo);

        Map<String, Object> result = new HashMap<String, Object>();

        if ("1".equals(query_level)) {
            //一级报表  分行
            //查询所有的分行
            List<Map<String, Object>> branchList = new ArrayList<>();
            if (!StringUtils.isEmpty(branchids)) {
                p.put("branchids", branchids);
                p.put("bran_level", "1");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            } else {
                p.put("bran_level", "1");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            }
            if (branchList != null && branchList.size() > 0) {
                for (Map<String, Object> branch : branchList) {
                    //1 先查询其下所有的机构
                    String orgids = hnmCommService.getUserBranchidsBybranch(branch.get("branch_id").toString());
                    //2 根据branchIds查询其下所有的服务点
                    if ("1".equals(query_org)) {
                        //所属机构
                        List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,
                                "is_delete", "0", "approval_status", "2"));
                        branch = dealData(siteList, date, branch);
                    } else if ("2".equals(query_org)) {
                        //落地支行
                        List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("branchids", orgids,
                                "is_delete", "0", "approval_status", "2"));
                        branch = dealData(siteList, date, branch);
                    }
                    branch.put("fh_branch_id", branch.get("branch_id"));
                    branch.put("fh_branch_name", branch.get("branch_name"));
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total", toatl);
            result.put("returnList", branchList);
        }
        if ("2".equals(query_level)) {
            //二级报表  支行/团队
            //查询所有的支行/团队
            List<Map<String, Object>> branchList = new ArrayList<>();
            if (!StringUtils.isEmpty(branchids)) {
                p.put("branchids", branchids);
                p.put("bran_level", "2");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            } else {
                p.put("bran_level", "2");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            }
            if (branchList != null && branchList.size() > 0) {
                for (Map<String, Object> branch : branchList) {
                    //1 先查询其下所有的机构
                    String orgids = hnmCommService.getUserBranchidsBybranch(branch.get("branch_id").toString());
                    //2 根据branchIds查询其下所有的服务点
                    if ("1".equals(query_org)) {
                        //所属机构
                        List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,
                                "is_delete", "0", "approval_status", "2"));
                        branch = dealData(siteList, date, branch);
                    } else if ("2".equals(query_org)) {
                        //落地支行
                        List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("branchids", orgids,
                                "is_delete", "0", "approval_status", "2"));
                        branch = dealData(siteList, date, branch);
                    }

                    List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                            branch.get("branch_id")));
                    if (allPorgidList != null && allPorgidList.size() > 0) {
                        for (Map<String, Object> allPorgid : allPorgidList) {
                            if (ObjectUtil.isNotEmpty(allPorgid.get("bran_level")) && "1".equals(allPorgid.get(
                                    "bran_level").toString())) {
                                branch.put("fh_branch_id", allPorgid.get("branch_id"));
                                branch.put("fh_branch_name", allPorgid.get("branch_name"));
                                break;
                            }
                        }
                    }
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total", toatl);
            result.put("returnList", branchList);
        }
        if ("3".equals(query_level)) {
            //三级报表  客户经理
            //查询所有的客户经理
            List<Map<String, Object>> mgrList = new ArrayList<>();
            if (!StringUtils.isEmpty(branchids)) {
                p.put("branchids", branchids);
                p.put("is_delete", "0");
                p.put("approval_status", "2");
                mgrList = hManagerDao.queryForListByPageForManageQuery(delkong(p));
            } else {
                p.put("is_delete", "0");
                p.put("approval_status", "2");
                mgrList = hManagerDao.queryForListByPageForManageQuery(delkong(p));
            }

            if (mgrList != null && mgrList.size() > 0) {
                for (Map<String, Object> mgr : mgrList) {
                    //根据mgr_id查询其下所有的服务点
                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),
                            "is_delete", "0", "approval_status", "2"));
                    mgr = dealData(siteList, date, mgr);
                    List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                            mgr.get("branch_id")));
                    if (allPorgidList != null && allPorgidList.size() > 0) {
                        for (Map<String, Object> allPorgid : allPorgidList) {
                            if (ObjectUtil.isNotEmpty(allPorgid.get("bran_level")) && "1".equals(allPorgid.get(
                                    "bran_level").toString())) {
                                mgr.put("fh_branch_id", allPorgid.get("branch_id"));
                                mgr.put("fh_branch_name", allPorgid.get("branch_name"));
                                break;
                            }
                        }
                    }
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total", toatl);
            result.put("returnList", mgrList);
        }
        if ("4".equals(query_level)) {
            //四级报表  服务点
            //查询所有服务点
            List<Map<String, Object>> siteAllList = new ArrayList<>();
            if (!StringUtils.isEmpty(branchids)) {
                if ("1".equals(query_org)) {
                    //所属机构
                    p.put("orgids", branchids);
                } else if ("2".equals(query_org)) {
                    //落地支行
                    p.put("branchids", branchids);
                }
                p.put("is_delete", "0");
                p.put("approval_status", "2");
                siteAllList = hSiteDao.queryList(delkong(p));
            } else {
                p.put("is_delete", "0");
                p.put("approval_status", "2");
                siteAllList = hSiteDao.queryList(delkong(p));
            }

            if (siteAllList != null && siteAllList.size() > 0) {
                //所属机构
                for (Map<String, Object> site : siteAllList) {
                    //根据site_no查询其下所有的服务点
                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("site_no", site.get("site_no"),
                            "is_delete", "0", "approval_status", "2"));
                    site = dealData(siteList, date, site);
                    if ("1".equals(query_org)) {
                        //所属机构
                        site.put("branch_id", site.get("org_id"));
                        site.put("branch_name", site.get("org_name"));
                    }
                    List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                            site.get("branch_id")));
                    if (allPorgidList != null && allPorgidList.size() > 0) {
                        for (Map<String, Object> allPorgid : allPorgidList) {
                            if (ObjectUtil.isNotEmpty(allPorgid.get("bran_level")) && "1".equals(allPorgid.get(
                                    "bran_level").toString())) {
                                site.put("fh_branch_id", allPorgid.get("branch_id"));
                                site.put("fh_branch_name", allPorgid.get("branch_name"));
                                break;
                            }
                        }
                    }
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total", toatl);
            result.put("returnList", siteAllList);
        }

        logger.info("Treport005Service执行完成");
        return result;
    }

    public int getDaysOfLastYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    private Map<String, Object> dealData(List<Map<String, Object>> siteList, Date date, Map<String, Object> map) {

        //资产余额
        BigDecimal amt1 = BigDecimal.ZERO;
        //资产年日均
        BigDecimal amt2 = BigDecimal.ZERO;
        //资产月日均
        BigDecimal amt3 = BigDecimal.ZERO;

        //前一日资产余额
        BigDecimal last_amt1 = BigDecimal.ZERO;
        //前一日资产年日均
        BigDecimal last_amt2 = BigDecimal.ZERO;
        //前一日资产月日均
        BigDecimal last_amt3 = BigDecimal.ZERO;

        //资产余额比上日
        BigDecimal amt1_than_last_day = BigDecimal.ZERO;
        //资产余额比上月
        BigDecimal amt1_than_last_month = BigDecimal.ZERO;
        //资产余额比年初
        BigDecimal amt1_than_last_year = BigDecimal.ZERO;

        //资产年日均比上日
        BigDecimal amt2_than_last_day = BigDecimal.ZERO;
        //资产年日均比上月
        BigDecimal amt2_than_last_month = BigDecimal.ZERO;
        //资产年日均比年初
        BigDecimal amt2_than_last_year = BigDecimal.ZERO;

        //资产月日均比上日
        BigDecimal amt3_than_last_day = BigDecimal.ZERO;
        //资产月日均比上月
        BigDecimal amt3_than_last_month = BigDecimal.ZERO;
        //资产月日均比年初
        BigDecimal amt3_than_last_year = BigDecimal.ZERO;


        if (siteList != null && siteList.size() > 0) {
            String siteIds = "";
            for (Map<String, Object> site : siteList) {
                siteIds += ",'" + site.get("site_no") + "'";
            }
            if (siteIds.startsWith(",")) {
                siteIds = siteIds.substring(1, siteIds.length());
            }

            Map<String, Object> parms = new HashMap<>();
            parms.put("siteIds", siteIds);

            //获取当前月份
            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
            parms.put("acctday", yyyyMM);
            //获取当前日期所在月的天数
            int day = DateUtil.dayOfMonth(date);
            String day_str = String.valueOf(day);
            if (day_str.length() == 1) {
                day_str = "0" + day_str;
            }
            //获取当年日期在今年的天数
            int dayOfYear = DateUtil.dayOfYear(date);

            String onlbal = "onlbal" + day_str;
            parms.put("onlbal", onlbal);

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

            StringBuilder sq2 = new StringBuilder("monthaccum");
            for (int n = day + 1; n <= 31; n++) {
                String n_str = String.valueOf(n);
                if (n_str.length() == 1) {
                    n_str = "0" + n_str;
                }
                sq2 = sq2.append("-onlbal").append(n_str);
            }
            parms.put("sq2", sq2.toString());
            parms.put("dayOfMonth", day);

            //获取上月月末在所在年的天数
            int daysOfLastMonth = DateUtil.dayOfYear(DateUtil.endOfMonth(DateUtils.addMonth(date, -1)));
            parms.put("daysOfLastMonth", daysOfLastMonth);
            //获取上年全年的天数
            int daysOfLastYear = getDaysOfLastYear(date);
            parms.put("daysOfLastYear", daysOfLastYear);

            //查询上月最后一天
            Date last_month = getMonthLast(date);
            //获取当前日期所在月的天数
            int day_2 = DateUtil.dayOfMonth(last_month);
            parms.put("day_2", day_2);
            //查询上年最后一天
            Date last_year = getYearLast(date);
            //获取当前日期所在月的天数
            int day_3 = DateUtil.dayOfMonth(last_year);
            parms.put("day_3", day_3);

            //查询资产信息
            List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);

            //处理比上日
            date = DateUtils.addDay(date, -1);
            //获取前一日月份
            yyyyMM = DateUtils.getDate(date, "yyyyMM");
            parms.put("acctday", yyyyMM);
            //获取前一日日期在本月的天数
            day = DateUtil.dayOfMonth(date);
            day_str = String.valueOf(day);
            if (day_str.length() == 1) {
                day_str = "0" + day_str;
            }
            //获取前一日期在今年的天数
            dayOfYear = DateUtil.dayOfYear(date);
            onlbal = "onlbal" + day_str;
            parms.put("onlbal", onlbal);

            sq1 = new StringBuilder("yearaccum");
            for (int n = day + 1; n <= 31; n++) {
                String n_str = String.valueOf(n);
                if (n_str.length() == 1) {
                    n_str = "0" + n_str;
                }
                sq1 = sq1.append("-onlbal").append(n_str);
            }
            parms.put("sq1", sq1.toString());
            parms.put("dayOfYear", dayOfYear);

            sq2 = new StringBuilder("monthaccum");
            for (int n = day + 1; n <= 31; n++) {
                String n_str = String.valueOf(n);
                if (n_str.length() == 1) {
                    n_str = "0" + n_str;
                }
                sq2 = sq2.append("-onlbal").append(n_str);
            }
            parms.put("sq2", sq2.toString());
            parms.put("dayOfMonth", day);

            //查询前一日资产信息
            List<Map<String, Object>> list11 = hnmCommService.qureyZC(parms);

            if (list1 != null && list1.size() > 0) {
                for (Map<String, Object> dataMap : list1) {
                    if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                        amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("amt2"))) {
                        amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("amt3"))) {
                        amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt3").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("ass_month_bal"))) {
                        amt1_than_last_month =
                                amt1_than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ass_month_bal"
                                ).toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("ass_year_bal"))) {
                        amt1_than_last_year = amt1_than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get(
                                "ass_year_bal").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("ave_ass_month_bal"))) {
                        amt2_than_last_month =
                                amt2_than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get(
                                        "ave_ass_month_bal").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("ave_ass_year_bal"))) {
                        amt2_than_last_year = amt2_than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get(
                                "ave_ass_year_bal").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("ave_ass_month_bal_2"))) {
                        amt3_than_last_month =
                                amt3_than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get(
                                        "ave_ass_month_bal_2").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("ave_ass_year_bal_2"))) {
                        amt3_than_last_year = amt3_than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get(
                                "ave_ass_year_bal_2").toString())));
                    }
                }
            }

            if (list11 != null && list11.size() > 0) {
                for (Map<String, Object> dataMap : list11) {
                    if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                        last_amt1 = last_amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("amt2"))) {
                        last_amt2 = last_amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                    }
                    if (ObjectUtil.isNotEmpty(dataMap.get("amt3"))) {
                        last_amt3 = last_amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt3").toString())));
                    }
                }
            }
            amt1_than_last_day = amt1.subtract(last_amt1);
            amt2_than_last_day = amt2.subtract(last_amt2);
            amt3_than_last_day = amt3.subtract(last_amt3);
        }
        map.put("amt1", hnmCommService.dealVal(amt1));
        map.put("amt2", hnmCommService.dealVal(amt2));
        map.put("amt3", hnmCommService.dealVal(amt3));

        map.put("amt1_than_last_day", hnmCommService.dealVal(amt1_than_last_day));
        map.put("amt2_than_last_day", hnmCommService.dealVal(amt2_than_last_day));
        map.put("amt3_than_last_day", hnmCommService.dealVal(amt3_than_last_day));

        map.put("amt1_than_last_month", hnmCommService.dealVal(amt1_than_last_month));
        map.put("amt2_than_last_month", hnmCommService.dealVal(amt2_than_last_month));
        map.put("amt3_than_last_month", hnmCommService.dealVal(amt3_than_last_month));

        map.put("amt1_than_last_year", hnmCommService.dealVal(amt1_than_last_year));
        map.put("amt2_than_last_year", hnmCommService.dealVal(amt2_than_last_year));
        map.put("amt3_than_last_year", hnmCommService.dealVal(amt3_than_last_year));
        return map;
    }

    /**
     * 获取上一年最后一天日期
     *
     * @param date
     * @return Date
     */
    public Date getYearLast(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取上一月最后一天日期
     *
     * @param date
     * @return Date
     */
    public Date getMonthLast(Date date) {
        return DateUtil.endOfMonth(DateUtils.addMonth(date, -1));
    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        for (String key : data.keySet()) {
            if (data.get(key) == null || "".equals(data.get(key))) {

            } else {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
}
