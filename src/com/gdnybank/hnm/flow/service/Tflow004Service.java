package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalDetailsDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 审批类型查询
 */
@Service
public class Tflow004Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Tflow004Service.class);

    @Autowired
    TApprovalTypeDao tApprovalTypeDao;
    @Autowired
    private TApprovalDetailsDao tApprovalDetailsDao;
    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(StringUtils.isEmpty(p.get("id"))) {
    		throw new BusinessException("Tflow004ServiceException", "审批类型id不能为空");
    	}
    	List<Map<String, Object>> queryApprovalType = tApprovalTypeDao.queryForList(p);
        if(queryApprovalType == null || queryApprovalType.size() == 0){
            throw new BusinessException("Tflow004ServiceException", "查无此审批类型");
        }

        List<Map<String, Object>> maps = tApprovalDetailsDao.queryForList(BaseUtils.map("approval_type", queryApprovalType.get(0).get("approval_type")));

        List<List<String>> results = new ArrayList<>();
        if (maps != null && maps.size() > 0){
            for (Map<String, Object> map : maps) {
                List<String> details = new ArrayList<>();
                details.add((String) map.get("approval_field"));
                details.add((String) map.get("approval_details"));
                results.add(details);
            }
        }
        logger.info("---------------->"+queryApprovalType);
        logger.info("Tflow004Service执行完成");
        return BaseUtils.map("msg",queryApprovalType,"approval_items",results);
    }
}
