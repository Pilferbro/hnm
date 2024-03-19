package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleMenuDao;
import com.gdnybank.hnm.pub.dao.SysRoleMobileMenuDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 修改时查询列表
 */
@Service
public class Trole006Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Trole006Service.class);

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private Tmenu004Service tmenu004Service;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	private SysRoleMobileMenuDao sysRoleMobileMenuDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

    	String role_id = p.get("role_id").toString();
    	if(StringUtils.isEmpty(role_id)) {
    		throw new BusinessException("Trole006ServiceException", "角色编号不能为空");
    	}
    	List<Map<String, Object>> queryRole = sysRoleDao.queryForList(p);

    	logger.info("---------------->"+queryRole);


    	List<String> list = new ArrayList<String>();
		List<String> mobileList = new ArrayList<String>();
    	if(queryRole.size()==1) {

    		List<Map<String, Object>> queryForRoleMenu = sysRoleMenuDao.queryForList(p);
    		logger.info("---------------->queryForRoleMenu:"+queryForRoleMenu);
    		for (Map<String, Object> roleMenu : queryForRoleMenu) {
//    			map.put("menu_code", roleMenu.get("menu_code"));
    			String menu_code = (String)roleMenu.get("menu_code");
    			list.add(menu_code);
			}

			List<Map<String, Object>> queryForRoleMobileMenu = sysRoleMobileMenuDao.queryForList(p);
			logger.info("---------------->queryForRoleMobileMenu:"+queryForRoleMobileMenu);
			for (Map<String, Object> roleMenu : queryForRoleMobileMenu) {
//    			map.put("menu_code", roleMenu.get("menu_code"));
				String menu_code = (String)roleMenu.get("menu_code");
				mobileList.add(menu_code);
			}


    	}else {
    		throw new BusinessException("Trole006ServiceException", "查出多条或者无此数据");
    	}


        return BaseUtils.map("role_msg",queryRole,"menu_code",list,"mobile_menu_code",mobileList);
    }
}
