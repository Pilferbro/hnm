package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TApprovalProcessDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审批流程新增
 * @author: wxm

 */
@Service
public class Tflow011Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow011Service.class);

    @Autowired
    TApprovalProcessDao tApprovalProcessDao;
	@Autowired
    HnmCommService hnmCommService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
        //获取上传的机构
        String branch_id = "";
        if(ObjectUtil.isNotEmpty(p.get("branch_id"))){
            branch_id = p.get("branch_id").toString();
        }
        Map<String, Object> rMap = new HashMap<String, Object>();
        rMap.put("approval_type", p.get("approval_type"));
        //先查询 是否已设置流程  相同分行不可重复设置 且总行流程只能设置一条
        if(StringUtils.isEmpty(branch_id)){
            //总行级流程
            List<Map<String, Object>> list = tApprovalProcessDao.queryForListForAllBranch(rMap);
            if(list!=null&&list.size()>0) {
                logger.error(branch_id+"已设置全行默认审批流程，不可重复设置");
                throw new BusinessException("Tflow011ServiceException", "已设置全行默认审批流程，不可重复设置");
            }
        }else{
            rMap.put("branch_id", branch_id);
            List<Map<String, Object>> list = tApprovalProcessDao.queryForList(rMap);
            if(list!=null&&list.size()>0) {
                logger.error(branch_id+"机构已设置审批流程，不可重复设置");
                throw new BusinessException("Tflow011ServiceException", "你所在机构"+branch_id+"已设置审批流程，不可重复设置");
            }
        }

        p.put("branch_id", branch_id);
        p.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		p.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        tApprovalProcessDao.save(p);
		logger.info("Tflow011Service执行完成");
		return BaseUtils.map("success","1");
	}

}
