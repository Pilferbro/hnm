package com.gdnybank.hnm.market.service;

import com.gdnybank.hnm.pub.dao.HSiteWarnTimeDao;
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

import java.util.List;
import java.util.Map;

/**
 * 添加站点状态提醒时间
 * @author: wxm

 */
@Service
public class Tmarket004Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tmarket004Service.class);

	@Autowired
	HSiteWarnTimeDao hSiteWarnTimeDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		//添加前先查询 不允许重复添加
		if(StringUtils.isEmpty(p.get("branch_id"))) {
			throw new BusinessException("Tmarket004ServiceException", "branch_id不能为空");
		}
		List<Map<String, Object>> list = hSiteWarnTimeDao.queryForList(BaseUtils.map("branch_id", p.get("branch_id")));
		if(list != null && list.size()>0){
			throw new BusinessException("Tmarket004ServiceException", "你已设置该机构，请勿重复添加");
		}

		p.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		p.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		hSiteWarnTimeDao.save(p);
		logger.info("Tmarket004Service执行完成");
		return BaseUtils.map("success","1");
	}

}
