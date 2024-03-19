package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询审批状态列表
 * @author: wxm

 */
@Service
public class Tflow019Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow019Service.class);
	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		List<Map<String,Object>> list= ApprovalStatusEnum.toList();
		logger.info("Tflow019Service执行完成");
		return list;
	}
}
