package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
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
 * 查询审批类型列表
 * @author: wxm

 */
@Service
public class Tflow001Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow001Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		add(p.get("approval_type"), p,"approval_type");
		add(p.get("approval_type_name"),p,"approval_type_name");

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

		logger.info("Tflow001Service执行完成");
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
