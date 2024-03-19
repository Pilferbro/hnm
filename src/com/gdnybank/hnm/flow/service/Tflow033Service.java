package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalItemDao;
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
 * 查询审批项目列表
 * @author: wxm

 */
@Service
public class Tflow033Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow033Service.class);

	@Autowired
	TApprovalItemDao tApprovalItemDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		List<Map<String,Object>> list=tApprovalItemDao.queryForList(delkong(p));
		logger.info("Tflow033Service执行完成");
		return list;
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
