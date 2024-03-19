package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalConnectHisDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询沟通历史列表
 * @author: wxm

 */
@Service
public class Tflow032Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow032Service.class);

	@Autowired
    TApprovalConnectHisDao tApprovalConnectHisDao;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		List<Map<String, Object>> list = tApprovalConnectHisDao.queryForListByTime(BaseUtils.map("apply_id",p.get("apply_id")));
		logger.info("Tflow032Service执行完成");
		return list;
	}
}
