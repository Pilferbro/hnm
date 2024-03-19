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

import java.util.List;
import java.util.Map;

/**
 * 站点状态调整时间详情
 * @author: wxm

 */
@Service
@Transactional
public class Tmarket002Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tmarket002Service.class);

	@Autowired
	HSiteWarnTimeDao hSiteWarnTimeDao;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(StringUtils.isEmpty(p.get("id"))) {
			throw new BusinessException("Tmarket002ServiceException", "id不能为空");
		}
		List<Map<String, Object>> list = hSiteWarnTimeDao.queryForList(p);
		if(list == null || list.size() == 0){
			throw new BusinessException("Tmarket002ServiceException", "查无此机构");
		}
		logger.info("---------------->"+list);
		logger.info("Tmarket002Service执行完成");
		return BaseUtils.map("msg",list);
	}


}
