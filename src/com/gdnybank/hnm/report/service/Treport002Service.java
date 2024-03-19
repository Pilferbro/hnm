package com.gdnybank.hnm.report.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
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
 * 查询客户信息
 * @author: wxm

 */
@Service
public class Treport002Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Treport002Service.class);

	@Autowired
	private HnmCommService hnmCommService;
	@Autowired
	private HSiteDao hSiteDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		if(ObjectUtil.isEmpty(p.get("query_item"))){
			logger.error("query_item为必传字段");
			throw new BusinessException("Treport002ServiceException","query_item为必传字段");
		}
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> siteList = new ArrayList<>();

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

		//所有客户数
		int cus_num1 = 0;
		//高净值
		int cus_num2 = 0;
		//价值客户
		int cus_num3 = 0;
		//有效户
		int cus_num4 = 0;

		//上日所有客户数
		int last_day_cus_num1 = 0;
		//上日高净值
		int last_day_cus_num2 = 0;
		//上日价值客户
		int last_day_cus_num3 = 0;
		//上日有效户
		int last_day_cus_num4 = 0;

		//上月所有客户数
		int last_month_cus_num1 = 0;
		//上月高净值
		int last_month_cus_num2 = 0;
		//上月价值客户
		int last_month_cus_num3 = 0;
		//上月有效户
		int last_month_cus_num4 = 0;

		//上年所有客户数
		int last_year_cus_num1 = 0;
		//上年高净值
		int last_year_cus_num2 = 0;
		//上年价值客户
		int last_year_cus_num3 = 0;
		//上年有效户
		int last_year_cus_num4 = 0;

		//比上日
		int than_last_day = 0;
		//比上月
		int than_last_month = 0;
		//比年初
		int than_last_year = 0;

		if(siteList !=null && siteList.size() > 0){

			String siteIds = "";
			for (Map<String, Object> site : siteList) {
				siteIds+=",'"+site.get("site_no")+"'";
			}
			if(siteIds.startsWith(",")){
				siteIds=siteIds.substring(1, siteIds.length());
			}

			Date date = new Date();
			if(ObjectUtil.isNotEmpty(p.get("query_date"))){
				date = DateUtils.parse(p.get("query_date").toString(),"yyyyMMdd");
			}

			Map<String , Object> parms = new HashMap<>();
			parms.put("siteIds",siteIds);

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
			//查询客户信息
			String nowdate = DateUtils.getDate(date, "yyyyMMdd");
			parms.put("nowdate",nowdate);
			List<Map<String, Object>> list2 = hnmCommService.qureyCus(parms);
			cus_num1 = list2.size();


			//查询上日资产信息
			Date last_date = DateUtils.addDay(date,-1);
			//获取上日月份
			yyyyMM = DateUtils.getDate(last_date, "yyyyMM");
			parms.put("acctday",yyyyMM);
			//获取上日日期
			day = DateUtil.dayOfMonth(last_date);
			day_str = String.valueOf(day);
			if(day_str.length() == 1){
				day_str = "0"+day_str;
			}
			quararg = "quaraccum"+day_str;
			parms.put("quararg",quararg);
			List<Map<String, Object>> list3 = hnmCommService.qureyZC(parms);
			//查询上日客户信息
			nowdate = DateUtils.getDate(last_date, "yyyyMMdd");
			parms.put("nowdate",nowdate);
			List<Map<String, Object>> list4 = hnmCommService.qureyCus(parms);
			last_day_cus_num1 = list4.size();


			//查询上月资产信息
			Date last_month = getMonthLast(date);
			//获取上月月份
			yyyyMM = DateUtils.getDate(last_month, "yyyyMM");
			parms.put("acctday",yyyyMM);
			//获取上月最后一天日期
			day = DateUtil.dayOfMonth(last_month);
			day_str = String.valueOf(day);
			if(day_str.length() == 1){
				day_str = "0"+day_str;
			}
			quararg = "quaraccum"+day_str;
			parms.put("quararg",quararg);
			List<Map<String, Object>> list5 = hnmCommService.qureyZC(parms);
			//查询上月最后一天客户信息
			nowdate = DateUtils.getDate(last_month, "yyyyMMdd");
			parms.put("nowdate",nowdate);
			List<Map<String, Object>> list6 = hnmCommService.qureyCus(parms);
			last_month_cus_num1 = list6.size();


			//查询年初(去年最后一天)资产信息
			Date last_year = getYearLast(date);
			//获取年初(去年最后一天)月份
			yyyyMM = DateUtils.getDate(last_year, "yyyyMM");
			parms.put("acctday",yyyyMM);
			//获取年初(去年最后一天)日期
			day = DateUtil.dayOfMonth(last_year);
			day_str = String.valueOf(day);
			if(day_str.length() == 1){
				day_str = "0"+day_str;
			}
			quararg = "quaraccum"+day_str;
			parms.put("quararg",quararg);
			List<Map<String, Object>> list7 = hnmCommService.qureyZC(parms);
			//查询年初(去年最后一天)客户信息
			nowdate = DateUtils.getDate(last_year, "yyyyMMdd");
			parms.put("nowdate",nowdate);
			List<Map<String, Object>> list8 = hnmCommService.qureyCus(parms);
			last_year_cus_num1 = list8.size();


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
			if(list3 != null && list3.size()>0){
				for (Map<String, Object> dataMap : list3){
					if(ObjectUtil.isNotEmpty(dataMap.get("quararg"))){
						double quarArg = Double.valueOf(dataMap.get("quararg").toString());
						if(quarArg >= 1000000){
							last_day_cus_num2 ++;
						}else if(quarArg < 1000000 && quarArg >= 100000){
							last_day_cus_num3 ++;
						}else if(quarArg < 100000 && quarArg >= 1000){
							last_day_cus_num4 ++;
						}
					}
				}
			}
			if(list5 != null && list5.size()>0){
				for (Map<String, Object> dataMap : list5){
					if(ObjectUtil.isNotEmpty(dataMap.get("quararg"))){
						double quarArg = Double.valueOf(dataMap.get("quararg").toString());
						if(quarArg >= 1000000){
							last_month_cus_num2 ++;
						}else if(quarArg < 1000000 && quarArg >= 100000){
							last_month_cus_num3 ++;
						}else if(quarArg < 100000 && quarArg >= 1000){
							last_month_cus_num4 ++;
						}
					}
				}
			}
			if(list7 != null && list7.size()>0){
				for (Map<String, Object> dataMap : list7){
					if(ObjectUtil.isNotEmpty(dataMap.get("quararg"))){
						double quarArg = Double.valueOf(dataMap.get("quararg").toString());
						if(quarArg >= 1000000){
							last_year_cus_num2 ++;
						}else if(quarArg < 1000000 && quarArg >= 100000){
							last_year_cus_num3 ++;
						}else if(quarArg < 100000 && quarArg >= 1000){
							last_year_cus_num4 ++;
						}
					}
				}
			}

			String query_item = p.get("query_item").toString();
			if("cus_num1".equals(query_item)){
				than_last_day = cus_num1 - last_day_cus_num1;
				than_last_month = cus_num1 - last_month_cus_num1;
				than_last_year = cus_num1 - last_year_cus_num1;
			}
			if("cus_num2".equals(query_item)){
				than_last_day = cus_num2 - last_day_cus_num2;
				than_last_month = cus_num2 - last_month_cus_num2;
				than_last_year = cus_num2 - last_year_cus_num2;
			}
			if("cus_num3".equals(query_item)){
				than_last_day = cus_num3 - last_day_cus_num3;
				than_last_month = cus_num3 - last_month_cus_num3;
				than_last_year = cus_num3 - last_year_cus_num3;
			}
			if("cus_num4".equals(query_item)){
				than_last_day = cus_num4 - last_day_cus_num4;
				than_last_month = cus_num4 - last_month_cus_num4;
				than_last_year = cus_num4 - last_year_cus_num4;
			}
		}

		returnMap.put("cus_num1",cus_num1);
		returnMap.put("cus_num2",cus_num2);
		returnMap.put("cus_num3",cus_num3);
		returnMap.put("cus_num4",cus_num4);

		returnMap.put("than_last_day",than_last_day);
		returnMap.put("than_last_month",than_last_month);
		returnMap.put("than_last_year",than_last_year);

		logger.info("Treport002Service执行完成");
		return returnMap;
	}

	public int getDaysOfLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
