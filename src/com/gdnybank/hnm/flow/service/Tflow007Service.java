package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
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
 * 查询审批类型列表（for 审批流程）
 * @author: wxm

 */
@Service
public class Tflow007Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow007Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;
	@Autowired
	private HnmCommService hnmCommService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		add(p.get("approval_type"), p,"approval_type");
		add(p.get("approval_type_name"),p,"approval_type_name");

		//校验角色   总行级可以看所有
		// 获取当前登陆用户
		String userId = MfpContextHolder.getLoginId();
		int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
		if(userRoleLevel==0){
			logger.error("登陆人员"+userId+"未配置角色");
			throw new BusinessException("Tflow007ServiceException", "登陆人员未配置角色");
		}
		if(userRoleLevel != 1){
			p.put("branch_up", "1");
		}

		PageInfo pageInfo = new PageInfo();
		int page = Integer.parseInt(p.get("page").toString());
		int num = Integer.parseInt(p.get("number").toString());
		pageInfo.setIDisplayStart(page*num);
		pageInfo.setIDisplayLength(num);
		MfpContextHolder.setPageInfo(pageInfo);
		Map<String,Object> typeRuslt = new HashMap<String,Object>();
		List<Map<String, Object>> result = tApprovalTypeDao.queryForListBycondition(delkong(p));
		long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
		typeRuslt.put("total",toatl);
		typeRuslt.put("approvalTypeList",result);

		logger.info("Tflow007Service执行完成");
		return typeRuslt;
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
