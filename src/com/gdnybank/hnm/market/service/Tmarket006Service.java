package com.gdnybank.hnm.market.service;

import com.gdnybank.hnm.pub.dao.HSiteWarnTimeDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 删除站点状态调整时间
 * @author: wxm

 */
@Service
public class Tmarket006Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tmarket006Service.class);

	@Autowired
	HSiteWarnTimeDao hSiteWarnTimeDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(StringUtils.isEmpty(p.get("id"))) {
			throw new BusinessException("Tmarket006ServiceException", "id不能为空");
		}

		//删除
		hSiteWarnTimeDao.deleteByPk(p);
		logger.info("Tmarket006Service执行完成");
		return BaseUtils.map("success","1");
	}

}
