package com.gdnybank.hnm.branch;

import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
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
import java.util.List;
import java.util.Map;

/**
 * 查询机构（根据机构级别查询）
 * @author: wxm

 */
@Service
public class Tbranch006Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tbranch006Service.class);

	@Autowired
	private SysBranchDao sysBranchDao;
	@Autowired
	private HnmCommService hnmCommService;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

		String bran_level = String.valueOf(p.get("bran_level"));
		if(StringUtils.isEmpty(bran_level)) {
			 throw new BusinessException("Tbranch006ServiceException","bran_level不能为空");
		}

		List<Map<String, Object>> branchList = new ArrayList<>();
		String userId = MfpContextHolder.getLoginId();
		int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
		if(userRoleLevel==0){
			throw new BusinessException("Tuser003ServiceException", "登陆人员未配置角色");
		}
		//总行级可以看所有
		if(userRoleLevel!=1){
			String branchids = hnmCommService.getUserBranchids();
			branchList = sysBranchDao.queryForListByconditionAll(BaseUtils.map("branchids",branchids,"bran_level",bran_level));
		}else{
			branchList = sysBranchDao.queryForListByconditionAll(BaseUtils.map("bran_level",bran_level));
		}
		return branchList;
	}

}
