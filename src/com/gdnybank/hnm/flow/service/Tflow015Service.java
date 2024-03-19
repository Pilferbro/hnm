package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.TApprovalDao;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审批提交时查询审批角色对应审批人员
 * @author: wxm

 */
@Service
public class Tflow015Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow015Service.class);

	@Autowired
	SysAccountDao sysAccountDao;
	@Autowired
	TApprovalDao approvalDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		// 获取审批类型
		if (p.get("apply_id") == null || "".equals(p.get("apply_id"))) {
			logger.error("上送“apply_id”为空");
			throw new BusinessException("Tflow015ServiceException", "上送“apply_id”为空");
		}
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> approvalList = approvalDao.queryForList(BaseUtils.map("apply_id",p.get("apply_id")));
		if(approvalList != null && approvalList.size() >0){
			//根据下一审批角色查询该角色下的用户
			list = sysAccountDao.queryByRole(BaseUtils.map("role_id", approvalList.get(0).get("next_role")));

		}else{
			logger.error("查无此审批流程");
			throw new BusinessException("Tflow015ServiceException", "查无此审批流程");
		}

		logger.info("Tflow015Service执行完成");
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
