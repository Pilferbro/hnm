package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalProcessDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
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
 * 审批类型删除
 * @author: wxm

 */
@Service
public class Tflow005Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow005Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;
	@Autowired
	TApprovalProcessDao tApprovalProcessDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		String id = String.valueOf(p.get("id"));
		if(StringUtils.isEmpty(id)) {
			 throw new BusinessException("Tflow005ServiceException","审批类型id不能为空");
		}

		Map<String, Object> param = BaseUtils.map("id", id);
		//先查询
		List<Map<String, Object>> list = tApprovalTypeDao.queryForList(param);
		if(list!=null && list.size()>0){
			//获取审批流程
			List<Map<String, Object>> queryForList = tApprovalProcessDao.queryForList(BaseUtils.map("approval_type",
					list.get(0).get("approval_type")));
			if(queryForList!=null && queryForList.size()>0){
				//存在审批流程   不允许删除
				throw new BusinessException("Tflow005ServiceException","该审批类型下有审批流程，不允许删除");
			}

		}else{
			throw new BusinessException("Tflow005ServiceException","审批类型不存在");
		}

		//删除审批类型
		tApprovalTypeDao.deleteByPk(param);
		logger.info("Tflow005Service执行完成");
		return BaseUtils.map("success","1");
	}

}
