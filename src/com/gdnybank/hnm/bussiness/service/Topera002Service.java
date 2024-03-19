package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.TOperateDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @desc 查询合规操作规则详情
 */
@Service
public class Topera002Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Topera002Service.class);

    @Autowired
    private TOperateDao tOperateDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(StringUtils.isEmpty(p.get("id"))) {
    		throw new BusinessException("Topera002ServiceException", "id不能为空");
    	}
    	List<Map<String, Object>> list = tOperateDao.queryForList(p);
        if(list == null || list.size() == 0){
            throw new BusinessException("Topera002ServiceException", "查无此记录");
        }
    	logger.info("---------------->"+list);
        logger.info("Topera002Service执行完成");
        return BaseUtils.map("msg",list);
    }
}
