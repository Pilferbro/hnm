package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.TApprovalDao;
import com.gdnybank.hnm.pub.dao.TApprovalHisDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询审批流步骤
 * @author: wxm

 */
@Service
public class Tflow024Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow024Service.class);

	@Autowired
	TApprovalDao tApprovalDao;
	@Autowired
	TApprovalHisDao tApprovalHisDao;
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
		if (p.get("apply_id") == null || "".equals(p.get("apply_id"))) {
			logger.error("上送“apply_id”为空");
			throw new BusinessException("Tflow024ServiceException", "上送“apply_id”为空");
		}
		List<Map<String, Object>> approvalList = tApprovalDao.queryForList(BaseUtils.map("apply_id", p.get("apply_id")));
		List<Map<String, Object>> approvalStepList = new ArrayList<>();
		if(approvalList!=null && approvalList.size()>0){
			Map<String, Object> approvalMap = approvalList.get(0);
			String[] approval_processes = approvalMap.get("approval_process").toString().split("-");
			List<Map<String, Object>> approvalHisList = tApprovalHisDao.queryForListForStep(BaseUtils.map("apply_id", p.get("apply_id")));

			boolean flag = true;
			if(approval_processes.length != approvalHisList.size() && ObjectUtil.isEmpty(approvalMap.get("approval_user"))){
				flag = false;
			}
			//查询角色
			//先查询审批历史表
			List<Map<String, Object>> list2 = tApprovalHisDao.queryForListForStepOrder(BaseUtils.map("apply_id", p.get("apply_id")));

			boolean skill = true;
			for (int i=0; i < approval_processes.length ; i++){
				List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", approval_processes[i]));

				String approval_user_name = "";
				//先查询审批历史表
				if(list2 !=null && list2.size()>0 && list2.size()>i){
					if(ObjectUtil.isNotEmpty(list2.get(i).get("approval_user_name"))){
						approval_user_name = list2.get(i).get("approval_user_name").toString();
					}else{
						//再查询审批表
						if(skill){
							List<Map<String, Object>> list1 = tApprovalDao.queryForList(BaseUtils.map("apply_id", p.get("apply_id"),"approval_role",approval_processes[i]));
							if(list1 !=null && list1.size()>0){
								if(ObjectUtil.isNotEmpty(list1.get(0).get("approval_user_name"))){
									approval_user_name = list1.get(0).get("approval_user_name").toString();
								}
								skill = false;
							}
						}
					}
				}else{
					//再查询审批表
					if(skill){
						List<Map<String, Object>> list1 = tApprovalDao.queryForList(BaseUtils.map("apply_id", p.get("apply_id"),"approval_role",approval_processes[i]));
						if(list1 !=null && list1.size()>0){
							if(ObjectUtil.isNotEmpty(list1.get(0).get("approval_user_name"))){
								approval_user_name = list1.get(0).get("approval_user_name").toString();
							}
							skill = false;
						}
					}
				}

				Map<String, Object> map = new HashMap<>();
				if(mapList!=null && mapList.size()>0){
					map = mapList.get(0);
				}
				if(flag){
					if(i<approvalHisList.size()){
						map.put("status","success");
					}else if(i==approvalHisList.size()){
						map.put("status","process");
					}else{
						map.put("status","wait");
					}
				}else{
					if(i<approvalHisList.size()){
						map.put("status","success");
					}else{
						map.put("status","wait");
					}
				}
				map.put("approval_user_name",approval_user_name);
				approvalStepList.add(map);
			}
		}

		Map<String,Object> retMap = new HashMap<>();
		retMap.put("approvalStepList",approvalStepList);
		logger.info("Tflow024Service执行完成");
		return retMap;
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
