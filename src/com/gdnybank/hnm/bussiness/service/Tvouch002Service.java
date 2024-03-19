package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.CupstsDao;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询已处理助农取款业务凭证列表
 * @author: wxm

 */
@Service
public class Tvouch002Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tvouch002Service.class);

	@Autowired
	private HnmCommService hnmCommService;
	@Autowired
	private CupstsDao cupstsDao;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		PageInfo pageInfo = new PageInfo();
		int page = Integer.parseInt(p.get("page").toString());
		int num = Integer.parseInt(p.get("number").toString());
		pageInfo.setIDisplayStart(page*num);
		pageInfo.setIDisplayLength(num);
		MfpContextHolder.setPageInfo(pageInfo);

		Map<String,Object> returnMap = new HashMap<String,Object>();
		Map<String , Object> params = new HashMap<String , Object>();

		if(p.containsKey("site_no") && ObjectUtil.isNotEmpty(p.get("site_no"))){
			params.put("site_no",p.get("site_no"));
		}
		if(p.containsKey("account_id") && ObjectUtil.isNotEmpty(p.get("account_id"))){
			params.put("account_id",p.get("account_id"));
		}

		//处理交易开始时间及结束时间
		if(p.containsKey("start_date") && ObjectUtil.isNotEmpty(p.get("start_date"))){
			Date start_date = DateUtils.parse(p.get("start_date").toString(),"yyyy-MM-dd");
			params.put("start_date", DateUtils.getDate(start_date,"yyyyMMdd"));

		}
		if(p.containsKey("end_date") && ObjectUtil.isNotEmpty(p.get("end_date"))){
			Date end_date = DateUtils.parse(p.get("end_date").toString(),"yyyy-MM-dd");
			params.put("end_date", DateUtils.getDate(end_date,"yyyyMMdd"));
		}

		//校验角色   总行级可以看所有
		String userId = MfpContextHolder.getLoginId();
		int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
		if(userRoleLevel==0){
			logger.error("登陆人员"+userId+"未配置角色");
			throw new BusinessException("Tvouch002ServiceException", "登陆人员未配置角色");
		}

		if(userRoleLevel != 1){
			String branchids = hnmCommService.getUserBranchids();
			params.put("allOrgids", branchids);
		}

		if(userRoleLevel == 4){
			//非管理员只能查看自己的
			params.put("mgr_id", userId);
		}

		if(ObjectUtil.isNotEmpty(p.get("orgid"))){
			params.put("orgids", hnmCommService.getUserBranchidsBybranch(p.get("orgid").toString()));
		}

		if(ObjectUtil.isNotEmpty(p.get("bankid"))){
			params.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
		}

		params.put("is_delete","0");
		params.put("approval_status","2");
		params.put("stat","1");
		//56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
		params.put("prids","'56','57','58','60','65'");

		List<Map<String, Object>> result = cupstsDao.queryBycondition2(delkong(params));
		long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();

		returnMap.put("total",toatl);
		returnMap.put("voucherList",result);

		logger.info("Tvouch002Service执行完成");
		return returnMap;
	}

	private void add(Object object, Map<String, Object> p,String name) {
		if(object!=null){
			p.put(name, "%"+object.toString()+"%");
		}

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
