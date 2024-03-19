package com.gdnybank.hnm.branch;

import com.gdnybank.hnm.pub.dao.SysBranchDao;
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
 * @desc 机构详情查询
 */
@Service
public class Tbranch002Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Tbranch002Service.class);

    @Autowired
    SysBranchDao sysBranchDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        if(StringUtils.isEmpty(p.get("branch_id"))) {
    		throw new BusinessException("Tbranch002ServiceException", "branch_id不能为空");
    	}
    	p.put("is_delete","0");
        p.put("approval_status","2");
    	List<Map<String, Object>> list = sysBranchDao.queryForListDetail(p);
    	if(list == null || list.size() == 0){
            throw new BusinessException("Tbranch002ServiceException", "查无此机构");
        }
    	logger.info("---------------->"+list);
        logger.info("Tbranch002Service执行完成");
        return BaseUtils.map("msg",list);
    }
}
