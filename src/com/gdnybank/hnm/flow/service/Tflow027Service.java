package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 废弃我的申请
 * @author: wxm

 */
@Service
public class Tflow027Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow027Service.class);

    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

        String apply_id = String.valueOf(p.get("apply_id"));
        if(StringUtils.isEmpty(apply_id)) {
            throw new BusinessException("Tflow027ServiceException","申请编号不能为空");
        }
        String approval_type = String.valueOf(p.get("approval_type"));
        if(StringUtils.isEmpty(approval_type)) {
            throw new BusinessException("Tflow027ServiceException","approval_type不能为空");
        }

        Map<String,Object> parms = new HashMap<>();
        parms.put("approval_status",ApprovalStatusEnum.STATUS_9.getApprovalStatus());
        parms.put("approval_status_name",ApprovalStatusEnum.STATUS_9.getApprovalStatusName());
        parms.put("id",apply_id);
        parms.put("approval_type",approval_type);

        tApprovalApplyDao.updateStatus(parms);

		logger.info("Tflow027Service执行完成");
		return BaseUtils.map("success","1");
	}

}
