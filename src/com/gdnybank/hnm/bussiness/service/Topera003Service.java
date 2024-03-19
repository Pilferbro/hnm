package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.TOperateDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
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
 * 修改合规操作规则
 * @author: wxm

 */
@Service
public class Topera003Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Topera003Service.class);

	@Autowired
	private TOperateDao tOperateDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(StringUtils.isEmpty(p.get("id"))) {
			throw new BusinessException("Topera003ServiceException", "id不能为空");
		}
		p.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        p.put("creator", MfpContextHolder.getLoginId());
		tOperateDao.updateByPk(p);

		logger.info("Topera003Service执行完成");
		return BaseUtils.map("success","1");
	}

}
