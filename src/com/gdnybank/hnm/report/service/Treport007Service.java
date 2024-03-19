package com.gdnybank.hnm.report.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HSiteLogDao;
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
 * 拓展业绩报表
 * @author: wxm

 */
@Service
public class Treport007Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Treport007Service.class);

    @Autowired
    private SysBranchDao sysBranchDao;
	@Autowired
	private HnmCommService hnmCommService;
	@Autowired
	private HSiteDao hSiteDao;
	@Autowired
    private HManagerDao hManagerDao;
    @Autowired
    private HSiteLogDao hSiteLogDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(ObjectUtil.isEmpty(p.get("query_level"))){
			logger.error("query_level为必传字段");
			throw new BusinessException("Treport007ServiceException","query_level为必传字段");
		}
		String query_level = p.get("query_level").toString();

        if(ObjectUtil.isEmpty(p.get("query_org"))){
            logger.error("query_org为必传字段");
            throw new BusinessException("Treport007ServiceException","query_org为必传字段");
        }
        String query_org = p.get("query_org").toString();

        Date date = new Date();
        if(ObjectUtil.isNotEmpty(p.get("query_date"))){
            date = DateUtils.parse(p.get("query_date").toString(),"yyyyMMdd");
        }

        String query_branch = "";
        if(ObjectUtil.isNotEmpty(p.get("bankid"))){
            query_branch = p.get("bankid").toString();
        }

        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            throw new BusinessException("Treport007ServiceException", "登陆人员未配置角色");
        }

        String branchids = "";
        //总行级可以看所有
        if(userRoleLevel != 1){
            branchids = hnmCommService.getUserBranchids();
        }

        if(userRoleLevel == 4){
            //客户经理级别
            p.put("mgr_id",userId);
        }

        if(!StringUtils.isEmpty(query_branch)){
            branchids = hnmCommService.getUserBranchidsBybranch(query_branch);
        }

        PageInfo pageInfo = new PageInfo();
        int page = Integer.parseInt(p.get("page").toString());
        int num = Integer.parseInt(p.get("number").toString());
        pageInfo.setIDisplayStart(page*num);
        pageInfo.setIDisplayLength(num);
        MfpContextHolder.setPageInfo(pageInfo);

        Map<String,Object> result = new HashMap<String,Object>();

        List<Map<String, Object>> siteList = new ArrayList<>();
        List<Map<String, Object>> lastdaysiteList = new ArrayList<>();
        List<Map<String, Object>> lastmonthsiteList = new ArrayList<>();
        List<Map<String, Object>> lastyearsiteList = new ArrayList<>();

        if("1".equals(query_level)){
            //一级报表  分行
            //查询所有的分行
            List<Map<String, Object>> branchList = new ArrayList<>();
            if(!StringUtils.isEmpty(branchids)){
                p.put("branchids",branchids);
                p.put("bran_level","1");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            }else{
                p.put("bran_level","1");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            }
            if(branchList != null && branchList.size() >0){
                for (Map<String, Object> branch : branchList){
                    //1 先查询其下所有的机构
                    String orgids = hnmCommService.getUserBranchidsBybranch(branch.get("branch_id").toString());
                    //2 根据branchIds查询其下所有的服务点

                    if("1".equals(query_org)){
                        //所属机构
                        if(DateUtils.getDate(date,"yyyyMMdd").equals(DateUtils.getDate(new Date(),"yyyyMMdd"))){
                            //当日
                            siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));
                        }else{
                            //历史日期
                            siteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(date,"yyyyMMdd")));
                        }
                        //查询上日拓展信息
                        Date last_date = DateUtils.addDay(date,-1);
                        lastdaysiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
                        //查询上月拓展信息
                        Date last_month = getMonthLast(date);
                        lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
                        //查询上年拓展信息
                        Date last_year = getYearLast(date);
                        lastyearsiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));

                        branch = dealData(siteList, lastdaysiteList,lastmonthsiteList,lastyearsiteList,branch);
                    }else if("2".equals(query_org)){
                        //落地支行
                        if(DateUtils.getDate(date,"yyyyMMdd").equals(DateUtils.getDate(new Date(),"yyyyMMdd"))){
                            //当日
                            siteList = hSiteDao.query(BaseUtils.map("branchids", orgids,"is_delete","0","approval_status","2"));
                        }else{
                            //历史日期
                            siteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(date,"yyyyMMdd")));
                        }
                        //查询上日拓展信息
                        Date last_date = DateUtils.addDay(date,-1);
                        lastdaysiteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
                        //查询上月拓展信息
                        Date last_month = getMonthLast(date);
                        lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
                        //查询上年拓展信息
                        Date last_year = getYearLast(date);
                        lastyearsiteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));

                        branch = dealData(siteList, lastdaysiteList,lastmonthsiteList,lastyearsiteList,branch);
                    }

                    branch.put("fh_branch_id",branch.get("branch_id"));
                    branch.put("fh_branch_name",branch.get("branch_name"));
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total",toatl);
            result.put("returnList",branchList);
        }
        if("2".equals(query_level)){
            //二级报表  支行/团队
            //查询所有的支行/团队
            List<Map<String, Object>> branchList = new ArrayList<>();
            if(!StringUtils.isEmpty(branchids)){
                p.put("branchids",branchids);
                p.put("bran_level","2");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            }else{
                p.put("bran_level","2");
                branchList = sysBranchDao.queryForListByconditionByPage(delkong(p));
            }
            if(branchList != null && branchList.size() >0){
                for (Map<String, Object> branch : branchList){
                    //1 先查询其下所有的机构
                    String orgids = hnmCommService.getUserBranchidsBybranch(branch.get("branch_id").toString());
                    //2 根据branchIds查询其下所有的服务点

                    if("1".equals(query_org)){
                        //所属机构
                        if(DateUtils.getDate(date,"yyyyMMdd").equals(DateUtils.getDate(new Date(),"yyyyMMdd"))){
                            //当日
                            siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));
                        }else{
                            //历史日期
                            siteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(date,"yyyyMMdd")));
                        }
                        //查询上日拓展信息
                        Date last_date = DateUtils.addDay(date,-1);
                        lastdaysiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
                        //查询上月拓展信息
                        Date last_month = getMonthLast(date);
                        lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
                        //查询上年拓展信息
                        Date last_year = getYearLast(date);
                        lastyearsiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));

                        branch = dealData(siteList, lastdaysiteList,lastmonthsiteList,lastyearsiteList,branch);
                    }else if("2".equals(query_org)){
                        //落地支行
                        if(DateUtils.getDate(date,"yyyyMMdd").equals(DateUtils.getDate(new Date(),"yyyyMMdd"))){
                            //当日
                            siteList = hSiteDao.query(BaseUtils.map("branchids", orgids,"is_delete","0","approval_status","2"));
                        }else{
                            //历史日期
                            siteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(date,"yyyyMMdd")));
                        }
                        //查询上日拓展信息
                        Date last_date = DateUtils.addDay(date,-1);
                        lastdaysiteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
                        //查询上月拓展信息
                        Date last_month = getMonthLast(date);
                        lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
                        //查询上年拓展信息
                        Date last_year = getYearLast(date);
                        lastyearsiteList = hSiteLogDao.query(BaseUtils.map("branchids", orgids,"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));

                        branch = dealData(siteList, lastdaysiteList,lastmonthsiteList,lastyearsiteList,branch);
                    }

                    List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                            branch.get("branch_id")));
                    if(allPorgidList != null && allPorgidList.size()>0){
                        for (Map<String, Object> allPorgid : allPorgidList){
                            if(ObjectUtil.isNotEmpty(allPorgid.get("bran_level"))&& "1".equals(allPorgid.get("bran_level").toString())){
                                branch.put("fh_branch_id",allPorgid.get("branch_id"));
                                branch.put("fh_branch_name",allPorgid.get("branch_name"));
                                break;
                            }
                        }
                    }
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total",toatl);
            result.put("returnList",branchList);
        }
        if("3".equals(query_level)){
            //三级报表  客户经理
            //查询所有的客户经理
            List<Map<String, Object>> mgrList = new ArrayList<>();
            if(!StringUtils.isEmpty(branchids)){
                p.put("branchids",branchids);
                p.put("is_delete","0");
                p.put("approval_status","2");
                mgrList = hManagerDao.queryForListByPageForManageQuery(delkong(p));
            }else{
                p.put("is_delete","0");
                p.put("approval_status","2");
                mgrList = hManagerDao.queryForListByPageForManageQuery(delkong(p));
            }

            if(mgrList != null && mgrList.size() >0){
                for (Map<String, Object> mgr : mgrList){
                    //根据mgr_id查询其下所有的服务点
                    if(DateUtils.getDate(date,"yyyyMMdd").equals(DateUtils.getDate(new Date(),"yyyyMMdd"))){
                        //当日
                        siteList = hSiteDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),"is_delete","0","approval_status","2"));
                    }else{
                        //历史日期
                        siteList = hSiteLogDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),"log_time",DateUtils.getDate(date,"yyyyMMdd")));
                    }
                    //查询上日拓展信息
                    Date last_date = DateUtils.addDay(date,-1);
                    lastdaysiteList = hSiteLogDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
                    //查询上月拓展信息
                    Date last_month = getMonthLast(date);
                    lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
                    //查询上年拓展信息
                    Date last_year = getYearLast(date);
                    lastyearsiteList = hSiteLogDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));

                    mgr = dealData(siteList, lastdaysiteList,lastmonthsiteList,lastyearsiteList,mgr);
                    List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                            mgr.get("branch_id")));
                    if(allPorgidList != null && allPorgidList.size()>0){
                        for (Map<String, Object> allPorgid : allPorgidList){
                            if(ObjectUtil.isNotEmpty(allPorgid.get("bran_level"))&& "1".equals(allPorgid.get("bran_level").toString())){
                                mgr.put("fh_branch_id",allPorgid.get("branch_id"));
                                mgr.put("fh_branch_name",allPorgid.get("branch_name"));
                                break;
                            }
                        }
                    }
                }
            }
            long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total",toatl);
            result.put("returnList",mgrList);
        }

		logger.info("Treport007Service执行完成");
		return result;
	}


	private Map<String, Object> dealData(List<Map<String, Object>> siteList,List<Map<String, Object>> lastdaysiteList,List<Map<String, Object>> lastmonthsiteList,List<Map<String, Object>> lastyearsiteList,Map<String, Object> map){
        int site_num1 = 0;
        int site_num2 = 0;
        int site_num3 = 0;
        int site_num4 = 0;

        int lastdaysitenum1 = 0;
        int lastdaysitenum2 = 0;
        int lastdaysitenum3 = 0;
        int lastdaysitenum4 = 0;

        int lastmonthsitenum1 = 0;
        int lastmonthsitenum2 = 0;
        int lastmonthsitenum3 = 0;
        int lastmonthsitenum4 = 0;

        int lastyearsitenum1 = 0;
        int lastyearsitenum2 = 0;
        int lastyearsitenum3 = 0;
        int lastyearsitenum4 = 0;

        //比上日
        int sitenum1_than_last_day = 0;
        //比上月
        int sitenum1_than_last_month = 0;
        //比年初
        int sitenum1_than_last_year = 0;

        //比上日
        int sitenum2_than_last_day = 0;
        //比上月
        int sitenum2_than_last_month = 0;
        //比年初
        int sitenum2_than_last_year = 0;

        //比上日
        int sitenum3_than_last_day = 0;
        //比上月
        int sitenum3_than_last_month = 0;
        //比年初
        int sitenum3_than_last_year = 0;

        //比上日
        int sitenum4_than_last_day = 0;
        //比上月
        int sitenum4_than_last_month = 0;
        //比年初
        int sitenum4_than_last_year = 0;


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

        if(lastdaysiteList !=null && lastdaysiteList.size() > 0){
            for (Map<String, Object> site : lastdaysiteList) {
                if("0".equals(site.get("status"))){
                    lastdaysitenum1 ++;
                }
                if("1".equals(site.get("status"))){
                    lastdaysitenum2 ++;
                }
                if("2".equals(site.get("status"))){
                    lastdaysitenum3 ++;
                }
                if("3".equals(site.get("status"))){
                    lastdaysitenum4 ++;
                }
            }
        }

        if(lastmonthsiteList !=null && lastmonthsiteList.size() > 0){
            for (Map<String, Object> site : lastmonthsiteList) {
                if("0".equals(site.get("status"))){
                    lastmonthsitenum1 ++;
                }
                if("1".equals(site.get("status"))){
                    lastmonthsitenum2 ++;
                }
                if("2".equals(site.get("status"))){
                    lastmonthsitenum3 ++;
                }
                if("3".equals(site.get("status"))){
                    lastmonthsitenum4 ++;
                }
            }
        }

        if(lastyearsiteList !=null && lastyearsiteList.size() > 0){
            for (Map<String, Object> site : lastyearsiteList) {
                if("0".equals(site.get("status"))){
                    lastyearsitenum1 ++;
                }
                if("1".equals(site.get("status"))){
                    lastyearsitenum2 ++;
                }
                if("2".equals(site.get("status"))){
                    lastyearsitenum3 ++;
                }
                if("3".equals(site.get("status"))){
                    lastyearsitenum4 ++;
                }
            }
        }

        sitenum1_than_last_day = site_num1 - lastdaysitenum1;
        sitenum1_than_last_month = site_num1 - lastmonthsitenum1;
        sitenum1_than_last_year = site_num1 - lastyearsitenum1;

        sitenum2_than_last_day = site_num2 - lastdaysitenum2;
        sitenum2_than_last_month = site_num2 - lastmonthsitenum2;
        sitenum2_than_last_year = site_num2 - lastyearsitenum2;

        sitenum3_than_last_day = site_num3 - lastdaysitenum3;
        sitenum3_than_last_month = site_num3 - lastmonthsitenum3;
        sitenum3_than_last_year = site_num3 - lastyearsitenum3;

        sitenum4_than_last_day = site_num4 - lastdaysitenum4;
        sitenum4_than_last_month = site_num4 - lastmonthsitenum4;
        sitenum4_than_last_year = site_num4 - lastyearsitenum4;

        map.put("site_num1",site_num1);
        map.put("site_num2",site_num2);
        map.put("site_num3",site_num3);
        map.put("site_num4",site_num4);

        map.put("sitenum1_than_last_day",sitenum1_than_last_day);
        map.put("sitenum1_than_last_month",sitenum1_than_last_month);
        map.put("sitenum1_than_last_year",sitenum1_than_last_year);

        map.put("sitenum2_than_last_day",sitenum2_than_last_day);
        map.put("sitenum2_than_last_month",sitenum2_than_last_month);
        map.put("sitenum2_than_last_year",sitenum2_than_last_year);

        map.put("sitenum3_than_last_day",sitenum3_than_last_day);
        map.put("sitenum3_than_last_month",sitenum3_than_last_month);
        map.put("sitenum3_than_last_year",sitenum3_than_last_year);

        map.put("sitenum4_than_last_day",sitenum4_than_last_day);
        map.put("sitenum4_than_last_month",sitenum4_than_last_month);
        map.put("sitenum4_than_last_year",sitenum4_than_last_year);

        return map;
    }


    /**
     * 获取上一年最后一天日期
     *
     * @param date
     *
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
     *
     * @return Date
     */
    public Date getMonthLast(Date date) {
        return DateUtil.endOfMonth(DateUtils.addMonth(date, -1));
    }

    public Map<String,Object> delkong(Map<String,Object> data){
        Map<String,Object> dataMap=new HashMap<String , Object>();
        for (String key  : data.keySet()) {
            if(data.get(key)==null||"".equals(data.get(key))){

            }else{
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
}
