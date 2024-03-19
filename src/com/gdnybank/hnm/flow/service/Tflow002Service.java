package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 审批类型新增
 * @author: wxm

 */
@Service
public class Tflow002Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow002Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		p.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		p.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		tApprovalTypeDao.save(p);
		logger.info("Tflow002Service执行完成");
		return BaseUtils.map("success","1");
	}

}
