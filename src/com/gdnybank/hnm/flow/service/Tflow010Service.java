package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalProcessDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @desc 查询审批流程
 */
@Service
public class Tflow010Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Tflow010Service.class);

	@Autowired
	TApprovalProcessDao tApprovalProcessDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

		List<Map<String, Object>> list = tApprovalProcessDao.queryForList(p);

//		if(list!=null && list.size()==1) {
//			Map<String, Object> map = list.get(0);
//			List<Map<String,String>> dynamicItem  = new ArrayList<>();
//			String[] roleids = map.get("approval_process").toString().split("-");
//			for (String roleid : roleids){
//				Map<String,String> roleidmap = new HashMap<>();
//				roleidmap.put("role_id",roleid);
//				dynamicItem.add(roleidmap);
//			}
//			map.put("dynamicItem",dynamicItem);
//		}else {
//			throw new BusinessException("Tflow010ServiceException", "查出多条或者无此数据");
//		}

		//新审批流程待用
    	if(list==null || list.size()!=1) {
    		throw new BusinessException("Tflow010ServiceException", "查出多条或者无此数据");
    	}

		logger.info("Tflow010Service执行完成");
		return BaseUtils.map("msg",list);
    }
}
