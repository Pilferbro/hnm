package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询审批申请详情
 * @author: wxm

 */
@Service
public class Tflow025Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow025Service.class);

	@Autowired
	TApprovalApplyDao tApprovalApplyDao;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		if (p.get("apply_id") == null || "".equals(p.get("apply_id"))) {
			logger.error("上送“apply_id”为空");
			throw new BusinessException("Tflow024ServiceException", "上送“apply_id”为空");
		}
		List<Map<String, Object>> approvalApplyList = tApprovalApplyDao.queryForList(BaseUtils.map("id", p.get("apply_id")));

		Map<String,Object> retMap = new HashMap<>();

		if(approvalApplyList != null && approvalApplyList.size() >0){
			retMap = approvalApplyList.get(0);
		}
		logger.info("Tflow025Service执行完成");
		return retMap;
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
