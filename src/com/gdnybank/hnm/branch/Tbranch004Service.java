package com.gdnybank.hnm.branch;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HTeamDao;
import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
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
 * 机构修改
 * @author: wxm

 */
@Service
public class Tbranch004Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tbranch004Service.class);

	@Autowired
	SysBranchDao sysBranchDao;
	@Autowired
	HTeamDao hTeamDao;
	@Autowired
	HnmCommService hnmCommService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		if(ObjectUtil.isEmpty(p.get("branch_id"))) {
			throw new BusinessException("Tbranch004ServiceException", "branch_id不能为空");
		}
		if(ObjectUtil.isEmpty(p.get("team_flag"))){
			throw new BusinessException("Tbranch004ServiceException", "team_flag不能为空");
		}
		String team_flag = String.valueOf(p.get("team_flag"));
		if("0".equals(team_flag)){
			throw new BusinessException("Tbranch004ServiceException", "组织架构不允许修改");
		}
		//校验是否可修改
		List<Map<String, Object>> branchlist = sysBranchDao.queryForList(BaseUtils.map("branch_id", p.get("porgid")));
		if (branchlist != null && branchlist.size() > 0) {
			Map<String, Object> map = branchlist.get(0);
			if ("2".equals(team_flag)) {//片区
				if (!"0".equals(map.get("team_flag"))) {
					throw new BusinessException("Tbranch004ServiceException", "上级机构请选择机构");
				}
			}
		} else {
			throw new BusinessException("Tbranch004ServiceException", "查无此上级机构");
		}

		//修改
		p.put("creator",MfpContextHolder.getLoginId());
		sysBranchDao.updateByPk(p);

		//修改团队表
		Map<String, Object> rmap = new HashMap<>();
		String id = IdUtil.randomUUID().replace("-", "");
		List<Map<String, Object>> list = hTeamDao.queryList(BaseUtils.map("branch_id", p.get("branch_id"), "is_delete", "0",
				"approval_status", "2"));
		if(list != null && list.size() > 0){
			Map<String, Object> map = list.get(0);
			rmap.putAll(map);
			rmap.putAll(p);
			rmap.put("id",id);
			rmap.put("is_delete","0");
			rmap.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			hTeamDao.save(rmap);
			String suggestion = p.get("branch_name") + "团队基本信息调整申请";
			hnmCommService.saveApprovalApplyNoProcess(p,BaseUtils.map("approval_type",
					ApprovalTypeEnum.TYPE010.getApprovalType(), "relation_id", id,
					"operator", MfpContextHolder.getLoginId(),"suggestion",suggestion));
		}else{
			rmap.putAll(p);
			rmap.put("id",id);
			rmap.put("is_delete","0");
			rmap.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			hTeamDao.save(rmap);
			String suggestion = p.get("branch_name") + "团队新增申请";
			hnmCommService.saveApprovalApplyNoProcess(p,BaseUtils.map("approval_type",
					ApprovalTypeEnum.TYPE009.getApprovalType(), "relation_id", id,
					"operator", MfpContextHolder.getLoginId(),"suggestion",suggestion));
		}

		List<Map<String, Object>> teamList = hTeamDao.queryList(BaseUtils.map(
				"branch_id", p.get("branch_id"),"approval_status","2"));
		for (Map<String, Object> teamMap : teamList){
			String teamId = teamMap.get("id").toString();
			if (id.equals(teamId)) continue;
			hTeamDao.updateByPk(BaseUtils.map("id",teamId,"is_delete","1"));
		}

		logger.info("Tbranch004Service执行完成");
		return BaseUtils.map("success","1");
	}

}
