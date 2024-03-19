package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 审批类型修改
 * @author: wxm

 */
@Service
public class Tflow003Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow003Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(StringUtils.isEmpty(p.get("id"))) {
			throw new BusinessException("Tflow003ServiceException", "审批类型id不能为空");
		}
        p.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		tApprovalTypeDao.updateByPk(p);

		logger.info("Tflow003Service执行完成");
		return BaseUtils.map("success","1");
	}

}
