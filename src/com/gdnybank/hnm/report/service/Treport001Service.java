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

import java.math.BigDecimal;
import java.util.*;

/**
 * 查询业绩信息-客户纬度
 * @author: wxm

 */
@Service
public class Treport001Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Treport001Service.class);

	@Autowired
	private HnmCommService hnmCommService;
	@Autowired
	private HSiteDao hSiteDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(ObjectUtil.isEmpty(p.get("query_item"))){
			logger.error("query_item为必传字段");
			throw new BusinessException("Treport001ServiceException","query_item为必传字段");
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

		//资产余额
		BigDecimal amt1 = BigDecimal.ZERO;
		//资产日均
		BigDecimal amt2 = BigDecimal.ZERO;
		//存款余额
		BigDecimal amt3 = BigDecimal.ZERO;
		//存款日均
		BigDecimal amt4 = BigDecimal.ZERO;

		//比上日
		BigDecimal than_last_day = BigDecimal.ZERO;
		//比上月
		BigDecimal than_last_month = BigDecimal.ZERO;
		//比年初
		BigDecimal than_last_year = BigDecimal.ZERO;

		//前一日资产余额
		BigDecimal last_amt1 = BigDecimal.ZERO;
		//前一日资产日均
		BigDecimal last_amt2 = BigDecimal.ZERO;
		//前一日存款余额
		BigDecimal last_amt3 = BigDecimal.ZERO;
		//前一日存款日均
		BigDecimal last_amt4 = BigDecimal.ZERO;

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
			//获取当前日期所在月的天数
			int day = DateUtil.dayOfMonth(date);
			String day_str = String.valueOf(day);
			if(day_str.length() == 1){
				day_str = "0"+day_str;
			}
			//获取当年日期在今年的天数
			int dayOfYear = DateUtil.dayOfYear(date);

			String onlbal = "onlbal"+day_str;
			parms.put("onlbal",onlbal);

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

			//获取上月月末在所在年的天数
			int daysOfLastMonth = DateUtil.dayOfYear(DateUtil.endOfMonth(DateUtils.addMonth(date, -1)));
			parms.put("daysOfLastMonth",daysOfLastMonth);
			//获取上年全年的天数
			int daysOfLastYear = getDaysOfLastYear(date);
			parms.put("daysOfLastYear",daysOfLastYear);

			String query_item = p.get("query_item").toString();
			if("amt1".equals(query_item) || "amt2".equals(query_item)){
				//查询资产信息
				List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);
				//处理比上日
				date = DateUtils.addDay(date,-1);
				//获取前一日月份
				yyyyMM = DateUtils.getDate(date, "yyyyMM");
				parms.put("acctday",yyyyMM);
				//获取前一日日期在本月的天数
				day = DateUtil.dayOfMonth(date);
				day_str = String.valueOf(day);
				if(day_str.length() == 1){
					day_str = "0"+day_str;
				}
				//获取前一日期在今年的天数
				dayOfYear = DateUtil.dayOfYear(date);
				onlbal = "onlbal"+day_str;
				parms.put("onlbal",onlbal);

				sq1 = new StringBuilder("yearaccum");
				for(int n=day+1; n<=31; n++){
					String n_str = String.valueOf(n);
					if(n_str.length() == 1){
						n_str = "0"+n_str;
					}
					sq1 = sq1.append("-onlbal").append(n_str);
				}
				parms.put("sq1",sq1.toString());
				parms.put("dayOfYear",dayOfYear);
				//查询前一日资产信息
				List<Map<String, Object>> list11 = hnmCommService.qureyZC(parms);
				if(list1 != null && list1.size()>0){
					for (Map<String, Object> dataMap : list1){
						if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
							amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
						}
						if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
							amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
						}
						if("amt1".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ass_month_bal"))){
							than_last_month = than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ass_month_bal").toString())));
						}
						if("amt1".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ass_year_bal"))){
							than_last_year = than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ass_year_bal").toString())));
						}
						if("amt2".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ave_ass_month_bal"))){
							than_last_month = than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ave_ass_month_bal").toString())));
						}
						if("amt2".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ave_ass_year_bal"))){
							than_last_year = than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ave_ass_year_bal").toString())));
						}
					}
				}

				if(list11 != null && list11.size()>0){
					for (Map<String, Object> dataMap : list11){
						if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
							last_amt1 = last_amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
						}
						if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
							last_amt2 = last_amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
						}
					}
				}

				if("amt1".equals(query_item)){
					than_last_day = amt1.subtract(last_amt1);
				}
				if("amt2".equals(query_item)){
					than_last_day = amt2.subtract(last_amt2);
				}
			}
			if("amt3".equals(query_item) || "amt4".equals(query_item)){
				//查询存款信息
				List<Map<String, Object>> list2 = hnmCommService.qureyCK(parms);
				//处理比上日
				date = DateUtils.addDay(date,-1);
				//获取前一日月份
				yyyyMM = DateUtils.getDate(date, "yyyyMM");
				parms.put("acctday",yyyyMM);
				//获取前一日日期
				day = DateUtil.dayOfMonth(date);
				day_str = String.valueOf(day);
				if(day_str.length() == 1){
					day_str = "0"+day_str;
				}
				//获取前一日期在今年的天数
				dayOfYear = DateUtil.dayOfYear(date);
				onlbal = "onlbal"+day_str;
				parms.put("onlbal",onlbal);

				sq1 = new StringBuilder("yearaccum");
				for(int n=day+1; n<=31; n++){
					String n_str = String.valueOf(n);
					if(n_str.length() == 1){
						n_str = "0"+n_str;
					}
					sq1 = sq1.append("-onlbal").append(n_str);
				}
				parms.put("sq1",sq1.toString());
				parms.put("dayOfYear",dayOfYear);
				//查询前一日资产信息
				List<Map<String, Object>> list22 = hnmCommService.qureyCK(parms);

				if(list2 != null && list2.size()>0){
					for (Map<String, Object> dataMap : list2){
						if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
							amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
						}
						if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
							amt4 = amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
						}
						if("amt3".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ass_month_bal"))){
							than_last_month = than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ass_month_bal").toString())));
						}
						if("amt3".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ass_year_bal"))){
							than_last_year = than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ass_year_bal").toString())));
						}
						if("amt4".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ave_ass_month_bal"))){
							than_last_month = than_last_month.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ave_ass_month_bal").toString())));
						}
						if("amt4".equals(query_item) && ObjectUtil.isNotEmpty(dataMap.get("ave_ass_year_bal"))){
							than_last_year = than_last_year.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("ave_ass_year_bal").toString())));
						}
					}
				}

				if(list22 != null && list22.size()>0){
					for (Map<String, Object> dataMap : list22){
						if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
							last_amt3 = last_amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
						}
						if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
							last_amt4 = last_amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
						}
					}
				}

				if("amt3".equals(query_item)){
					than_last_day = amt3.subtract(last_amt3);
				}
				if("amt4".equals(query_item)){
					than_last_day = amt4.subtract(last_amt4);
				}

			}
		}

		returnMap.put("amt1",hnmCommService.dealVal(amt1));
		returnMap.put("amt2",hnmCommService.dealVal(amt2));
		returnMap.put("amt3",hnmCommService.dealVal(amt3));
		returnMap.put("amt4",hnmCommService.dealVal(amt4));

		returnMap.put("than_last_day",hnmCommService.dealVal(than_last_day));
		returnMap.put("than_last_month",hnmCommService.dealVal(than_last_month));
		returnMap.put("than_last_year",hnmCommService.dealVal(than_last_year));

		logger.info("Treport001Service执行完成");
		return returnMap;
	}

	public int getDaysOfLastYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -1);
		return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance();
		int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dayOfYear = DateUtil.dayOfMonth(DateUtils.parse("20210105", "yyyyMMdd"));
		calendar.add(Calendar.YEAR, -1);
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		Date date = DateUtil.endOfMonth(DateUtils.addMonth(new Date(), -1));
		System.out.println(DateUtils.getDate(date,"yyyyMMdd"));
	}

}
