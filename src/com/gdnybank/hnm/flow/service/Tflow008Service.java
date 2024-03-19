package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TApprovalProcessDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.SysParamService;
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
 * 查询审批流程列表
 * @author: wxm

 */
@Service
public class Tflow008Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow008Service.class);

	@Autowired
	TApprovalProcessDao tApprovalProcessDao;
	@Autowired
	private HnmCommService hnmCommService;
	@Autowired
	private SysParamService sysParamService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		add(p.get("id"), p,"id");
		add(p.get("approval_process_name"),p,"approval_process_name");
		add(p.get("branch"),p,"branch");

		//校验角色   总行级可以看所有
		String userId = MfpContextHolder.getLoginId();
		int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
		if(userRoleLevel==0){
			logger.error("登陆人员"+userId+"未配置角色");
			throw new BusinessException("Tflow008ServiceException", "登陆人员未配置角色");
		}
		if(userRoleLevel != 1){
			String branchids = hnmCommService.getUserBranchids();
			p.put("branchids", branchids);
		}

		PageInfo pageInfo = new PageInfo();
		int page = Integer.parseInt(p.get("page").toString());
		int num = Integer.parseInt(p.get("number").toString());
		pageInfo.setIDisplayStart(page*num);
		pageInfo.setIDisplayLength(num);
		MfpContextHolder.setPageInfo(pageInfo);
		Map<String,Object> typeRuslt = new HashMap<String,Object>();
		List<Map<String, Object>> list = tApprovalProcessDao.queryForListBycondition(delkong(p));
		//将总行机构（默认全行的放置第一个）
		if(list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				if(ObjectUtil.isEmpty(list.get(i).get("branch_id"))) {
					Map<String, Object> map = list.get(i);
					list.remove(i);
					list.add(0, map);
				}
			}
		}
		long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
		typeRuslt.put("total",toatl);
		typeRuslt.put("approvalProcessList",list);

		logger.info("Tflow008Service执行完成");
		return typeRuslt;
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
