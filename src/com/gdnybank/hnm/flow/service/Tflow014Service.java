package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存（首次提交）时查询审批角色对应审批人员
 * @author: wxm

 */
@Service
public class Tflow014Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow014Service.class);

	@Autowired
	TApprovalTypeDao tApprovalTypeDao;
	@Autowired
	HnmCommService hnmCommService;
	@Autowired
	SysAccountDao sysAccountDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		// 获取审批类型
		if (p.get("approval_type") == null || "".equals(p.get("approval_type"))) {
			logger.error("上送“approval_type”为空");
			throw new BusinessException("Tflow014ServiceException", "上送“approval_type”为空");
		}
		//审批类型
		String approval_type = String.valueOf(p.get("approval_type"));
		// 获取当前用户所在的机构
		String branch_id = hnmCommService.getUserBranchid();

		Map<String, Object> rmap = new HashMap<>();
		rmap.put("use_up","0");
		//根据审批类型查询是否启用
		List<Map<String, Object>> mapList = tApprovalTypeDao.queryForList(BaseUtils.map("approval_type", approval_type));
		if(mapList != null && mapList.size() > 0){
			//启用
			if(ObjectUtil.isEmpty(mapList.get(0).get("use_up")) || "1".equals(mapList.get(0).get("use_up").toString())){
				rmap.put("use_up","1");
			}
		}

		//审批流程
		Map<String, Object> map = hnmCommService.getApprovalProcess(approval_type, branch_id);
		List<Map<String, Object>> list = new ArrayList<>();
		if(map != null && !map.isEmpty()) {
			//String  approval_process = String.valueOf(map.get("approval_process"));
			//add by wxm 20211220  新审批流程待用
			String  approval_process = hnmCommService.getNewApprovalProcess(p,map);
			if(!StringUtils.isEmpty(approval_process)){
				//获取首个流程节点
				String[] approval_processArr = approval_process.split("-");
				String roleid = approval_processArr[0];
				//根据角色查询该角色下的用户
				list = sysAccountDao.queryByRole(BaseUtils.map("role_id", roleid));
			}else{
				throw new BusinessException("Tflow014ServiceException", "准入条件不符合，拒绝提交");
			}
		}else{
            throw new BusinessException("Tflow014ServiceException", "未能获取审批流程");
        }
		rmap.put("dataList",list);
		logger.info("Tflow014Service执行完成");
		return rmap;
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
