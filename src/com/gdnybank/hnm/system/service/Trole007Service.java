package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 修改时查询列表
 */
@Service
public class Trole007Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Trole007Service.class);

	@Autowired
	private SysRoleDao sysRoleDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
//		List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
		//查询二级及以下的角色
//		roleList = sysRoleDao.queryForListByLevel(BaseUtils.map("role_level","'2','3','4'"));
		return sysRoleDao.queryForListByLevel(BaseUtils.map("role_level","'2','3','4'"));
    }
}
