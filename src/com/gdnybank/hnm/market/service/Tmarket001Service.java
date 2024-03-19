package com.gdnybank.hnm.market.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteWarnTimeDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询站点状态提醒时间列表
 * @author: wxm

 */
@Service
public class Tmarket001Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tmarket001Service.class);

	@Autowired
	HSiteWarnTimeDao hSiteWarnTimeDao;
	@Autowired
	private HnmCommService hnmCommService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		//校验角色   总行级可以看所有
		// 获取当前登陆用户
		String userId = MfpContextHolder.getLoginId();
		int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
		if(userRoleLevel==0){
			logger.error("登陆人员"+userId+"未配置角色");
			throw new BusinessException("Tflow007ServiceException", "登陆人员未配置角色");
		}
		if(userRoleLevel != 1){
			String branchids = hnmCommService.getUserBranchids();
			p.put("branchids", branchids);
		}
		if(ObjectUtil.isNotEmpty(p.get("bankid"))){
			p.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
		}

		PageInfo pageInfo = new PageInfo();
		int page = Integer.parseInt(p.get("page").toString());
		int num = Integer.parseInt(p.get("number").toString());
		pageInfo.setIDisplayStart(page*num);
		pageInfo.setIDisplayLength(num);
		MfpContextHolder.setPageInfo(pageInfo);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> result = hSiteWarnTimeDao.queryForListByconditionByPage(delkong(p));
		long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
		map.put("total",toatl);
		map.put("siteWarnTimeList",result);

		logger.info("Tmarket001Service执行完成");
		return map;
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
