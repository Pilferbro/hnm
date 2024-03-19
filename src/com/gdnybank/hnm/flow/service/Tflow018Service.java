package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 查询审批类型列表
 * @author: wxm

 */
@Service
public class Tflow018Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow018Service.class);
	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		List<Map<String,Object>> list= ApprovalTypeEnum.toList();
		logger.info("Tflow018Service执行完成");
		return list;
	}
}
