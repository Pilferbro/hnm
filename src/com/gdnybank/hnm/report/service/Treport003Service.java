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

import java.util.*;

/**
 * 查询拓展信息
 * @author: wxm

 */
@Service
public class Treport003Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Treport003Service.class);

	@Autowired
	private HnmCommService hnmCommService;
	@Autowired
	private HSiteDao hSiteDao;
	@Autowired
    private HSiteLogDao hSiteLogDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(ObjectUtil.isEmpty(p.get("query_item"))){
            logger.error("query_item为必传字段");
            throw new BusinessException("Treport003ServiceException","query_item为必传字段");
        }
        Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> siteList = new ArrayList<>();
        List<Map<String, Object>> lastdaysiteList = new ArrayList<>();
        List<Map<String, Object>> lastmonthsiteList = new ArrayList<>();
        List<Map<String, Object>> lastyearsiteList = new ArrayList<>();

        boolean flag = true;

        Date date = new Date();
        if(ObjectUtil.isNotEmpty(p.get("query_date"))){
            date = DateUtils.parse(p.get("query_date").toString(),"yyyyMMdd");
            if (!DateUtils.getDate(date, "yyyyMMdd").equals(DateUtils.getDate(new Date(), "yyyyMMdd"))) {
                flag = false;
            }
        }

        if(flag){
            //当天数据查询
            //团队查询
            if(ObjectUtil.isNotEmpty(p.get("branch_id"))){
                //1 先查询其下所有的机构
                String orgids = hnmCommService.getUserBranchidsBybranch(p.get("branch_id").toString());
                //2 根据branchIds查询其下所有的服务点
                siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));
            }
            //客户经理查询
            if(ObjectUtil.isNotEmpty(p.get("mgr_id"))){
                // 根据mgr_id查询其下所有的服务点
                siteList = hSiteDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"),"is_delete","0","approval_status","2"));
            }
            //服务点查询
            if(ObjectUtil.isNotEmpty(p.get("site_no"))){
                // 根据site_no查询其下所有的服务点
                siteList = hSiteDao.query(BaseUtils.map("site_no", p.get("site_no"),"is_delete","0","approval_status","2"));
            }
        }else{
            //历史查询
            //团队查询
            if(ObjectUtil.isNotEmpty(p.get("branch_id"))){
                //1 先查询其下所有的机构
                String orgids = hnmCommService.getUserBranchidsBybranch(p.get("branch_id").toString());
                //2 根据branchIds查询其下所有的服务点
                siteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(date,"yyyyMMdd")));
            }
            //客户经理查询
            if(ObjectUtil.isNotEmpty(p.get("mgr_id"))){
                // 根据mgr_id查询其下所有的服务点
                siteList = hSiteLogDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"),"log_time",DateUtils.getDate(date,"yyyyMMdd")));
            }
            //服务点查询
            if(ObjectUtil.isNotEmpty(p.get("site_no"))){
                // 根据site_no查询其下所有的服务点
                siteList = hSiteLogDao.query(BaseUtils.map("site_no", p.get("site_no"),"log_time",DateUtils.getDate(date,"yyyyMMdd")));
            }
        }

        if(ObjectUtil.isNotEmpty(p.get("branch_id"))){
            //1 先查询其下所有的机构
            String orgids = hnmCommService.getUserBranchidsBybranch(p.get("branch_id").toString());

            //2 根据branchIds查询其下所有的服务点
            //查询上日拓展信息
            Date last_date = DateUtils.addDay(date,-1);
            lastdaysiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
            //查询上月拓展信息
            Date last_month = getMonthLast(date);
            lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
            //查询上年拓展信息
            Date last_year = getYearLast(date);
            lastyearsiteList = hSiteLogDao.query(BaseUtils.map("orgids", orgids,"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));
        }
        //客户经理查询
        if(ObjectUtil.isNotEmpty(p.get("mgr_id"))){
            // 根据mgr_id查询其下所有的服务点
            //查询上日拓展信息
            Date last_date = DateUtils.addDay(date,-1);
            lastdaysiteList = hSiteLogDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"),"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
            //查询上月拓展信息
            Date last_month = getMonthLast(date);
            lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"),"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
            //查询上年拓展信息
            Date last_year = getYearLast(date);
            lastyearsiteList = hSiteLogDao.query(BaseUtils.map("mgr_id", p.get("mgr_id"),"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));
        }
        //服务点查询
        if(ObjectUtil.isNotEmpty(p.get("site_no"))){
            // 根据site_no查询其下所有的服务点
            //查询上日拓展信息
            Date last_date = DateUtils.addDay(date,-1);
            lastdaysiteList = hSiteLogDao.query(BaseUtils.map("site_no", p.get("site_no"),"log_time",DateUtils.getDate(last_date,"yyyyMMdd")));
            //查询上月拓展信息
            Date last_month = getMonthLast(date);
            lastmonthsiteList = hSiteLogDao.query(BaseUtils.map("site_no", p.get("site_no"),"log_time",DateUtils.getDate(last_month,"yyyyMMdd")));
            //查询上年拓展信息
            Date last_year = getYearLast(date);
            lastyearsiteList = hSiteLogDao.query(BaseUtils.map("site_no", p.get("site_no"),"log_time",DateUtils.getDate(last_year,"yyyyMMdd")));
        }

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
        int than_last_day = 0;
        //比上月
        int than_last_month = 0;
        //比年初
        int than_last_year = 0;

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

        String query_item = p.get("query_item").toString();
        if("site_num1".equals(query_item)){
            than_last_day = site_num1 - lastdaysitenum1;
            than_last_month = site_num1 - lastmonthsitenum1;
            than_last_year = site_num1 - lastyearsitenum1;
        }
        if("site_num2".equals(query_item)){
            than_last_day = site_num2 - lastdaysitenum2;
            than_last_month = site_num2 - lastmonthsitenum2;
            than_last_year = site_num2 - lastyearsitenum2;
        }
        if("site_num3".equals(query_item)){
            than_last_day = site_num3 - lastdaysitenum3;
            than_last_month = site_num3 - lastmonthsitenum3;
            than_last_year = site_num3 - lastyearsitenum3;
        }
        if("site_num4".equals(query_item)){
            than_last_day = site_num4 - lastdaysitenum4;
            than_last_month = site_num4 - lastmonthsitenum4;
            than_last_year = site_num4 - lastyearsitenum4;
        }

        returnMap.put("site_num1",site_num1);
        returnMap.put("site_num2",site_num2);
        returnMap.put("site_num3",site_num3);
        returnMap.put("site_num4",site_num4);

        returnMap.put("than_last_day",than_last_day);
        returnMap.put("than_last_month",than_last_month);
        returnMap.put("than_last_year",than_last_year);

		logger.info("Treport003Service执行完成");
		return returnMap;
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

}
