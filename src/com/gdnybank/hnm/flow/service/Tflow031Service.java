package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.dao.TApprovalConnectHisDao;
import com.gdnybank.hnm.pub.dao.TApprovalDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交沟通
 * @author: wxm

 */
@Service
public class Tflow031Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow031Service.class);

	@Autowired
	TApprovalDao tApprovalDao;
	@Autowired
	TApprovalApplyDao tApprovalApplyDao;
	@Autowired
	TApprovalConnectHisDao tApprovalConnectHisDao;
	@Autowired
	private HnmCommService hnmCommService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		// 获取审批类型
		if (p.get("apply_id") == null || "".equals(p.get("apply_id"))) {
			logger.error("上送“apply_id”为空");
			throw new BusinessException("Tflow031ServiceException", "上送“apply_id”为空");
		}

		//查询审批状态
		List<Map<String, Object>> mapList = tApprovalApplyDao.queryForList(BaseUtils.map("id", p.get("apply_id")));
		if(mapList != null && mapList.size() >0){
			String approval_status = mapList.get(0).get("approval_status").toString();
			if(ApprovalStatusEnum.STATUS_9.getApprovalStatus().equals(approval_status)){
				//此流程已废弃
				logger.error("此审批流已废弃");
				throw new BusinessException("Tflow031ServiceException", "此审批流已废弃");
			}
		}

		List<Map<String, Object>> approvalList = tApprovalDao.queryForList(BaseUtils.map("apply_id", p.get("apply_id")));
		if(approvalList!=null && approvalList.size()>0){
			Map<String, Object> approvalMap = approvalList.get(0);
			//添加沟通历史
			saveApprovalConnectHis(p,approvalMap);
			//更改审批表信息

			// 获取当前登陆用户
			String userId = MfpContextHolder.getLoginId();
			int userRole = hnmCommService.getUserRole(BaseUtils.map("account_id", userId));
			if(userRole==0){
				logger.error("登陆人员"+userId+"未配置角色");
				throw new BusinessException("Tflow031ServiceException", "登陆人员未配置角色");
			}
			String gt_approval_user = null;
			if(userRole != 1){
				StringBuffer new_gt_approval_user = new StringBuffer();
				String[] gt_approval_user_arr = approvalMap.get("gt_approval_user").toString().split(",");
				if(gt_approval_user_arr != null && gt_approval_user_arr.length >0){
					for(String user : gt_approval_user_arr){
						if(!userId.equals(user)){
							new_gt_approval_user = new_gt_approval_user.append(user).append(",");
						}
					}
				}
				if(new_gt_approval_user.toString() != null && new_gt_approval_user.toString().endsWith(",")){
					gt_approval_user = new_gt_approval_user.substring(0,new_gt_approval_user.length()-1);
				}
			}
			approvalMap.put("gt_approval_user",gt_approval_user);
			tApprovalDao.updateByPk(approvalMap);
		}else{
			throw new BusinessException("Tflow031ServiceException", "查无此审批流");
		}

		logger.info("Tflow031Service执行完成");
		return BaseUtils.map("success","1");
	}

	//保存沟通历史
	private void saveApprovalConnectHis(Map<String, Object> p, Map<String, Object> approvalMap) {
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("approval_type",approvalMap.get("approval_type"));
		rMap.put("apply_id",approvalMap.get("apply_id"));
		rMap.put("approval_user",approvalMap.get("approval_user"));
		rMap.put("gt_approval_user", MfpContextHolder.getLoginId());
		rMap.put("approval_desc",p.get("approval_desc"));
		rMap.put("create_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		rMap.put("update_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		tApprovalConnectHisDao.save(rMap);
	}

	public Map<String,Object> delkong(Map<String,Object> data){
		Map<String,Object> dataMap=new HashMap<String , Object>();
		for (String key  : data.keySet()) {
			if(data.get(key)==null||"".equals(data.get(key))){

			}else{
				dataMap.put(key, data.get(key));
			}
		}
		return dataMap;
	}

}
