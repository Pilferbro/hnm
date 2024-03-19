package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审批提交时保存审批流程
 * @author: wxm

 */
@Service
public class Tflow017Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow017Service.class);

	@Autowired
	TApprovalDao tApprovalDao;
	@Autowired
	TApprovalApplyDao tApprovalApplyDao;
	@Autowired
	TApprovalHisDao tApprovalHisDao;
	@Autowired
	HnmCommService hnmCommService;
	@Autowired
	SysAccountDao sysAccountDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		// 获取审批类型
		if (p.get("apply_id") == null || "".equals(p.get("apply_id"))) {
			logger.error("上送“apply_id”为空");
			throw new BusinessException("Tflow017ServiceException", "上送“apply_id”为空");
		}
		if (p.get("relation_id") == null || "".equals(p.get("relation_id"))) {
			logger.error("上送“relation_id”为空");
			throw new BusinessException("Tflow017ServiceException", "上送“relation_id”为空");
		}
		if (p.get("approval_opinion") == null || "".equals(p.get("approval_opinion"))) {
			logger.error("上送“approval_opinion”为空");
			throw new BusinessException("Tflow017ServiceException", "上送“approval_opinion”为空");
		}

		//查询审批状态
		List<Map<String, Object>> mapList = tApprovalApplyDao.queryForList(BaseUtils.map("id", p.get("apply_id")));
		if(mapList != null && mapList.size() >0){
			String approval_status = mapList.get(0).get("approval_status").toString();
			if(ApprovalStatusEnum.STATUS_9.getApprovalStatus().equals(approval_status)){
				//此流程已废弃
				logger.error("此审批流已废弃");
				throw new BusinessException("Tflow017ServiceException", "此审批流已废弃");
			}
		}

		//分不同的情况处理
		List<Map<String, Object>> approvalList = tApprovalDao.queryForList(BaseUtils.map("apply_id", p.get("apply_id")));
		 if(approvalList!=null && approvalList.size()>0){
			 Map<String, Object> approvalMap = approvalList.get(0);
			 if(ObjectUtil.isNotEmpty(approvalMap.get("gt_approval_user"))){
			 	StringBuffer msg = new StringBuffer();
				 String[] gt_approval_user_arr = approvalMap.get("gt_approval_user").toString().split(",");
				 if(gt_approval_user_arr != null && gt_approval_user_arr.length >0){
					 for(String user : gt_approval_user_arr){
						 List<Map<String, Object>> accountList = sysAccountDao.queryForList(BaseUtils.map("account_id",
								 user));
						 if(accountList != null && accountList.size() >0){
							 msg = msg.append(accountList.get(0).get("account_id")).append("-").append(accountList.get(0).get("name")).append(",");
						 }else{
							 msg = msg.append(user).append(",");
						 }
					 }
				 }

				 //此审批流尚在沟通中
				 logger.error("此审批流尚在沟通中");
				 throw new BusinessException("Tflow017ServiceException", "此审批流尚在沟通中，未沟通人员为："+msg);
			 }
		 }

		//审批意见
		String approval_opinion = p.get("approval_opinion").toString();
		switch (approval_opinion){
			//审批通过
			case "1":
				if(approvalList!=null && approvalList.size()>0){
					Map<String, Object> approvalMap = approvalList.get(0);
					if(ObjectUtil.isEmpty(approvalMap.get("next_role"))){//最后一步审批 并审批通过
						//添加审批历史
						saveApprovalHis(p, approvalMap, null);
						//更改审批时间 审批岗位  审批人员  可删除重新添加
						tApprovalDao.deleteByPk(approvalMap);
						approvalMap.put("odd_approval_process",null);
						approvalMap.put("approval_role",null);
						approvalMap.put("approval_user",null);
						approvalMap.put("update_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
						tApprovalDao.saveById(approvalMap);
						//更改审批申请表状态 审批通过
						tApprovalApplyDao.updateByPk(BaseUtils.map("id",approvalMap.get("apply_id"),"approval_status", ApprovalStatusEnum.STATUS_2.getApprovalStatus(),"approval_status_name",ApprovalStatusEnum.STATUS_2.getApprovalStatusName(),"update_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss")));
					    //后续业务表处理
						boolean b = hnmCommService.doAfterApproval(BaseUtils.map("approval_type", approvalMap.get(
								"approval_type"), "approval_status", ApprovalStatusEnum.STATUS_2.getApprovalStatus(),
								"id", p.get("relation_id")));
						if(!b){
							throw new BusinessException("Tflow017ServiceException", "数据处理失败");
						}
					}else{//非最后一步审批 并审批通过
						//添加审批历史
						saveApprovalHis(p, approvalMap, p.get("approval_user"));
						//更改审批时间 及 审批角色 审批人员 及 下一步审批角色 可删除重新添加
						tApprovalDao.deleteByPk(approvalMap);
						if(ObjectUtil.isNotEmpty(approvalMap.get("odd_approval_process"))){
							String  odd_approval_process = String.valueOf(approvalMap.get("odd_approval_process"));
							//获取下个审批角色
							String[] odd_approval_processArr = odd_approval_process.split("-");
							if(odd_approval_processArr.length>=1){
								approvalMap.put("approval_role", odd_approval_processArr[0]);
								approvalMap.put("approval_user",p.get("approval_user"));
							}else{
								approvalMap.put("approval_role", null);
								approvalMap.put("approval_user", null);
							}
							if(odd_approval_processArr.length>=2){
								approvalMap.put("next_role", odd_approval_processArr[1]);
							}else{
								approvalMap.put("next_role", null);
							}
							//截取审批流程表中的审批流程
							if(odd_approval_processArr.length>=2){
								String[] newArray = Arrays.copyOfRange(odd_approval_processArr, 1, odd_approval_processArr.length);
								String new_approval_process = StringUtils.join(newArray, "-");
								//剩余审批流程
								approvalMap.put("odd_approval_process",new_approval_process);
							}else{
								approvalMap.put("odd_approval_process",null);
							}
						}else{
							approvalMap.put("next_role", null);
							approvalMap.put("odd_approval_process", null);
							approvalMap.put("approval_role", null);
							approvalMap.put("approval_user", null);
						}
						approvalMap.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
						tApprovalDao.saveById(approvalMap);
					}
				}else{
					throw new BusinessException("Tflow017ServiceException", "查无此审批流程");
				}
				break;
			//审批未通过
			case "2":
				if(approvalList!=null && approvalList.size()>0){
					Map<String, Object> approvalMap = approvalList.get(0);
					//添加审批历史
					saveApprovalHis(p, approvalMap, null);
					//更改审批流程表  先删除  再添加
					tApprovalDao.deleteByPk(approvalMap);
					approvalMap.put("next_role", null);
					approvalMap.put("odd_approval_process", null);
					approvalMap.put("approval_role", null);
					approvalMap.put("approval_user", null);
					approvalMap.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
					tApprovalDao.saveById(approvalMap);
					//更改审批申请表  审批未通过
					tApprovalApplyDao.updateByPk(BaseUtils.map("id",approvalMap.get("apply_id"),"approval_status", ApprovalStatusEnum.STATUS_3.getApprovalStatus(),"approval_status_name",ApprovalStatusEnum.STATUS_3.getApprovalStatusName(),"update_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss")));
					//后续业务表处理
					boolean b = hnmCommService.doAfterApproval(BaseUtils.map("approval_type", approvalMap.get(
							"approval_type"), "approval_status", ApprovalStatusEnum.STATUS_3.getApprovalStatus(), "id"
							, p.get("relation_id")));
					if(!b){
						throw new BusinessException("Tflow017ServiceException", "数据处理失败");
					}
				}else{
					throw new BusinessException("Tflow017ServiceException", "查无此审批流程");
				}
				break;
			//转办
			case "3":
				if(approvalList!=null && approvalList.size()>0){
					Map<String, Object> approvalMap = approvalList.get(0);
					//添加审批历史
					saveApprovalHis(p, approvalMap, p.get("up_approval_user"));
					//更改审批流程表  先删除  再添加
					tApprovalDao.deleteByPk(approvalMap);
					//approvalMap.put("approval_role", null);
					//approvalMap.put("approval_user", null);
					//转办时 增加approval_process 暂定转办时角色一致
					String approval_role = approvalMap.get("approval_role").toString();
					String approval_process = approvalMap.get("approval_process").toString();
					if(ObjectUtil.isNotEmpty(approvalMap.get("odd_approval_process"))){
						String odd_approval_process = approvalMap.get("odd_approval_process").toString();
						approval_process = approval_process.substring(0,approval_process.length()-odd_approval_process.length());
						if(approval_process.endsWith("-")){
							approval_process = approval_process + approval_role + "-" + odd_approval_process;
						}else{
							approval_process = approval_process + "-" + approval_role + "-" + odd_approval_process;
						}
					}else{
						if(approval_process.endsWith("-")){
							approval_process = approval_process + approval_role;
						}else{
							approval_process = approval_process + "-" + approval_role;
						}
					}
					if (p.get("up_approval_user") == null || "".equals(p.get("up_approval_user"))) {
						logger.error("上送“up_approval_user”为空");
						throw new BusinessException("Tflow017ServiceException", "上送“up_approval_user”为空");
					}
					approvalMap.put("approval_process", approval_process);
					approvalMap.put("approval_user", p.get("up_approval_user"));
					approvalMap.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
					tApprovalDao.saveById(approvalMap);
				}else{
					throw new BusinessException("Tflow017ServiceException", "查无此审批流程");
				}
				break;
			//4 驳回
			case "4":
				if(approvalList!=null && approvalList.size()>0) {
					Map<String, Object> approvalMap = approvalList.get(0);
					Map<String, Object> parms = new HashMap<>();
					parms.put("approval_status", ApprovalStatusEnum.STATUS_4.getApprovalStatus());
					parms.put("approval_status_name", ApprovalStatusEnum.STATUS_4.getApprovalStatusName());
					parms.put("id", p.get("apply_id"));
					//添加审批历史
					saveApprovalHis(p, approvalMap, null);
					tApprovalApplyDao.updateStatus(parms);
				}
				break;
			//5 沟通
			case "5":
				if(approvalList!=null && approvalList.size()>0) {
					Map<String, Object> approvalMap = approvalList.get(0);
					List<String> aulist = (List<String>)p.get("gt_approval_user");
					if(aulist != null && aulist.size() >0){
						String gt_approval_user = StringUtils.join(aulist, ",");
						approvalMap.put("gt_approval_user",gt_approval_user);
					}

					//添加审批历史
					saveApprovalHis(p, approvalMap, approvalMap.get("gt_approval_user"));
					approvalMap.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
					tApprovalDao.updateByPk(approvalMap);
				}
				break;
			default:
				break;
		}

		logger.info("Tflow017Service执行完成");
		return BaseUtils.map("success","1");
	}

	//保存审批历史
	private void saveApprovalHis(Map<String, Object> p, Map<String, Object> approvalMap, Object to_approval_user) {
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("approval_type",approvalMap.get("approval_type"));
		rMap.put("apply_id",approvalMap.get("apply_id"));
		rMap.put("approval_role",approvalMap.get("approval_role"));
		rMap.put("approval_user",approvalMap.get("approval_user"));
		rMap.put("approval_opinion",p.get("approval_opinion"));
		rMap.put("approval_desc",p.get("approval_desc"));
		rMap.put("create_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		rMap.put("update_time",DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		rMap.put("to_approval_user",to_approval_user);
		tApprovalHisDao.save(rMap);
	}

	private void add(Object object, Map<String, Object> p,String name) {
		if(object!=null){
			p.put(name, "%"+object.toString()+"%");
		}

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
