package com.gdnybank.hnm.branch;

import com.gdnybank.hnm.pub.dao.*;
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
 * 机构删除
 * @author: wxm

 */
@Service
public class Tbranch005Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tbranch005Service.class);

	@Autowired
	SysBranchDao sysBranchDao;
	@Autowired
	HTeamDao hTeamDao;
	@Autowired
	SysAccountDao sysAccountDao;
	@Autowired
	HSiteDao hSiteDao;


	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		String branch_id = String.valueOf(p.get("branch_id"));
		if(StringUtils.isEmpty(branch_id)) {
			 throw new BusinessException("Tbranch005ServiceException","branch_id不能为空");
		}
		String team_flag = String.valueOf(p.get("team_flag"));
		if(StringUtils.isEmpty(team_flag)){
			throw new BusinessException("Tbranch005ServiceException", "team_flag不能为空");
		}
		if("0".equals(team_flag)){
			throw new BusinessException("Tbranch005ServiceException", "组织架构不允许删除");
		}
		//删除片区时校验其下是否有团队
		List<Map<String, Object>> list1 = sysBranchDao.queryForList(BaseUtils.map("porgid", branch_id));
		if(list1 != null && list1.size() >0){
			throw new BusinessException("Tbranch005ServiceException", branch_id+"下有团队，不允许删除");
		}

		//校验其下是否有客户经理
		List<Map<String, Object>> list2 = sysAccountDao.queryForList(BaseUtils.map("branch_id", branch_id));
		if(list2 != null && list2.size() >0){
			throw new BusinessException("Tbranch005ServiceException", branch_id+"下有客户经理，不允许删除");
		}

		//校验其是否为落地支行
		List<Map<String, Object>> list3 = hSiteDao.queryForListBycondition(BaseUtils.map("branch_id", branch_id,
				"is_delete", "0", "approval_status", "2"));
		if(list3 != null && list3.size() >0){
			throw new BusinessException("Tbranch005ServiceException", branch_id+"为站点落地支行，不允许删除");
		}

		//删除
		sysBranchDao.deleteByPk(p);
		//逻辑删除团队表
		hTeamDao.updateStatus("1",branch_id);

		logger.info("Tbranch005Service执行完成");
		return BaseUtils.map("success","1");
	}

}
