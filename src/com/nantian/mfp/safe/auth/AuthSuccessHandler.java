package com.nantian.mfp.safe.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.SysAccountRoleDao;
import com.gdnybank.hnm.pub.dao.SysMenuDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.RequestUtils;
import com.nantian.mfp.pub.dao.model.BaseAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SysAccountDao sysAccountDao;
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysAccountRoleDao sysAccountRoleDao;
	@Autowired
	private HnmCommService hnmCommService;



	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (RequestUtils.isAjaxRequest(request)) {

			logger.debug("没有设置默认失败跳转路径,直接返回客户端200状态码");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			BaseAccount ba=(BaseAccount)authentication.getPrincipal();
			Map<String, Object> p = BaseUtils.map("account_id", ba.getAccountId());

			Map<String, Object> userMap= new HashMap<String,Object>();
			List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> funcList = new ArrayList<Map<String,Object>>();
			int userRole=0;
			Object flag = MfpContextHolder.getSession().getAttribute("flag");

			//员工登录
			String role_id="";
			String role_level = "";
			userMap= sysAccountDao.queryForMap(p);
			userRole= hnmCommService.getUserRole(p);
			role_id= (String) sysAccountRoleDao.queryForMap(p).get("role_id");
			//获取用户角色级别
			role_level = hnmCommService.getUserRoleLevel(p)+"";

			if(hnmCommService.checkAgentIsMobile(MfpContextHolder.getRequest())){//app端
				if(userRole==1){
					//超级管理员返回所有菜单
					menuList = sysMenuDao.queryForList(BaseUtils.map("menu_type","1"));
					//超级管理员返回所有功能点（按钮）
					funcList = sysMenuDao.queryForList(BaseUtils.map("menu_type","2"));
				}else{
					//其它角色,根据角色返回菜单
					p.put("menu_type","1");
					menuList = sysMenuDao.queryMobileMenuByRole(p);
					//其它角色,根据角色返回功能点（按钮）
					p.put("menu_type","2");
					funcList = sysMenuDao.queryMobileMenuByRole(p);
				}
			}else{//pc端
				if(userRole==1){
					//超级管理员返回所有菜单
					menuList = sysMenuDao.queryForList(BaseUtils.map("menu_type","1"));
					//超级管理员返回所有功能点（按钮）
					funcList = sysMenuDao.queryForList(BaseUtils.map("menu_type","2"));
				}else{
					//其它角色,根据角色返回菜单
					p.put("menu_type","1");
					menuList = sysMenuDao.queryMenuByRole(p);
					//其它角色,根据角色返回功能点（按钮）
					p.put("menu_type","2");
					funcList = sysMenuDao.queryMenuByRole(p);
				}
			}

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("userMap",userMap);
			m.put("menuList",menuList);
			m.put("funcList",funcList);
			m.put("userRole",userRole);
			m.put("role_id",role_id);
			m.put("role_level",role_level);

			objectMapper.writeValue(response.getOutputStream(), m);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
