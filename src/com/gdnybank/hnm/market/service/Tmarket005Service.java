package com.gdnybank.hnm.market.service;

import com.gdnybank.hnm.pub.dao.HSiteWarnTimeDao;
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
 * @desc 修改站点状态调整时间
 */
@Service
public class Tmarket005Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Tmarket005Service.class);

    @Autowired
    HSiteWarnTimeDao hSiteWarnTimeDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(StringUtils.isEmpty(p.get("id"))) {
            throw new BusinessException("Tmarket005ServiceException", "id不能为空");
        }
        p.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        hSiteWarnTimeDao.updateByPk(p);

        logger.info("Tmarket005Service执行完成");
        return BaseUtils.map("success","1");
    }
}
