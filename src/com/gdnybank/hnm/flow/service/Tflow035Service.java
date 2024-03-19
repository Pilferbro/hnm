package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
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
 * 查询流程是否启用
 * @author: wxm

 */
@Service
public class Tflow035Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow035Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;
	@Autowired
	HnmCommService hnmCommService;
	@Autowired
	SysAccountDao sysAccountDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		// 获取审批类型
		if (p.get("approval_type") == null || "".equals(p.get("approval_type"))) {
			logger.error("上送“approval_type”为空");
			throw new BusinessException("Tflow035ServiceException", "上送“approval_type”为空");
		}
		//审批类型
		String approval_type = String.valueOf(p.get("approval_type"));

		Map<String, Object> rmap = new HashMap<>();
		rmap.put("use_up","0");
		//根据审批类型查询是否启用
		List<Map<String, Object>> mapList = tApprovalTypeDao.queryForList(BaseUtils.map("approval_type", approval_type));
		if(mapList != null && mapList.size() > 0){
			//启用
			if(ObjectUtil.isEmpty(mapList.get(0).get("use_up")) || "1".equals(mapList.get(0).get("use_up").toString())){
				rmap.put("use_up","1");
			}
		}
		logger.info("Tflow035Service执行完成");
		return rmap;
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
