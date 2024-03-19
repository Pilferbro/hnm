package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HTeamDao;
import com.gdnybank.hnm.pub.dao.TApprovalItemDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询审批记录列表
 * @author: wxm

 */
@Service
public class Tflow034Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow034Service.class);

	@Autowired
	HSiteDao hSiteDao;
	@Autowired
	HTeamDao hTeamDao;
	@Autowired
	HManagerDao hManagerDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		List<Map<String,Object>> list = new ArrayList<>();

		String req_type = String.valueOf(p.get("req_type"));
		if("0".equals(req_type)){
			//站点
			List<Map<String, Object>> forList = hSiteDao.queryForList(BaseUtils.map("id", p.get("id")));
			if(forList != null && forList.size()>0){
				list = hSiteDao.queryForHisList(BaseUtils.map("site_no",forList.get(0).get("site_no"),"approval_status","1,2"));
			}
		}else if("1".equals(req_type)){
			//团队
			List<Map<String, Object>> forList = hTeamDao.queryForList(BaseUtils.map("id", p.get("id")));
			if(forList != null && forList.size()>0){
				list = hTeamDao.queryForHisList(BaseUtils.map("branch_id",forList.get(0).get("branch_id"),"approval_status","1,2"));
			}
		}else if("2".equals(req_type)){
			//客户经理
			List<Map<String, Object>> forList = hManagerDao.queryForList(BaseUtils.map("id", p.get("id")));
			if(forList != null && forList.size()>0){
				list = hManagerDao.queryForHisList(BaseUtils.map("mgr_id",forList.get(0).get("mgr_id"),"approval_status","1,2"));
			}
		}

		logger.info("Tflow034Service执行完成");
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
