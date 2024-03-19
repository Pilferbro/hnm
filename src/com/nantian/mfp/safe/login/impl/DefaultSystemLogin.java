package com.nantian.mfp.safe.login.impl;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.dao.model.BaseAccount;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DefaultSystemLogin implements IUserLogin, InitializingBean {

	protected final Log logger = LogFactory.getLog(DefaultSystemLogin.class);

	public DefaultSystemLogin() {
	}

	/** 普通密码加密 比对 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SysAccountDao sysAccountDao;
	@Autowired
	private HnmCommService hnmCommService;

	/** 当前用户拥有的权限 */
	List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();

	protected String rolePrefix = "";

	/**
	 * 根据用户账号查询用户详细信息
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		BaseAccount account = new BaseAccount();
		// 员工登录
		List<Map<String, Object>> userList = sysAccountDao.queryForList(BaseUtils.map("account_id", username));
		if (userList.size() == 0) {
			throw new BadCredentialsException("输入的账号或密码有误，请重新输入！");
		}

		account.setAccountId(userList.get(0).get("account_id").toString());
		account.setAccountName(userList.get(0).get("name") == null ? "" : userList.get(0).get("name").toString());
		account.setAlias(userList.get(0).get("branch_id") == null ? "" :userList.get(0).get("branch_id").toString());
		account.setPhone(userList.get(0).get("phone") == null ? "" : userList.get(0).get("phone").toString());
		account.setEntryPwd(
				userList.get(0).get("login_pwd") == null ? "" : userList.get(0).get("login_pwd").toString());

		return (UserDetails) account;
	}

	/**
	 * 查询用户拥有的权限
	 *
	 * @param username
	 * @return
	 */
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		String roleName1 = rolePrefix + "_user";// 签约用户
		String roleName2 = rolePrefix + "_gold";// 白金信用卡用户
		list.add(new SimpleGrantedAuthority(roleName1));
		list.add(new SimpleGrantedAuthority(roleName2));
		return list;
	}

	/**
	 * 密码/指纹/短信验证码/图形验证码检验 根实现方法，可重写
	 *
	 * @return
	 */
	public boolean matches(BaseAccount acc) {
		String accountId = acc.getAccountId();
		String entryPwd = acc.getEntryPwd();
		String imgFlowid = acc.getImgFlowid();
			// 员工登录
		Map<String, Object> userMap = null;
		try {
			userMap = sysAccountDao.queryForMap(BaseUtils.map("account_id", accountId,"deleted",0));
		} catch (Exception e) {
			throw new BadCredentialsException("该账户不存在或已停用");
		}
		if (userMap.size() == 0) {
				throw new BadCredentialsException("输入的账号或密码有误，请重新输入！");
			}
			//非手机端登陆  即电脑店登陆需要校验密码等    手机端不需要
			if(!hnmCommService.checkAgentIsMobile(MfpContextHolder.getRequest())){
				int error_count = Integer.parseInt(userMap.get("error_count").toString());
				int count = Integer.parseInt(MfpContextHolder.getProps("security.login.maxErrCount"));
				if (StringUtils.isEmpty(imgFlowid) && error_count >= count) {
					//throw new BadCredentialsException("密码错误次数超过" + count + "次,需开启图形验证码验证");
					sysAccountDao.addErrorCount(BaseUtils.map("account_id", accountId));
					throw new BadCredentialsException("密码错误次数超过" + count + "次,账户已被锁定,请联系系统管理员处理");
				}
				// 验证密码是否正确
				String login_pwd = userMap.get("login_pwd").toString();
				if (!passwordEncoder.matches(entryPwd, login_pwd)) {
					// 更新密码错误次数
					sysAccountDao.addErrorCount(BaseUtils.map("account_id", accountId));
					throw new BadCredentialsException("输入的账号或密码有误，请重新输入！");
				}
				// 判断图形验证码是否正确
				if (!StringUtils.isEmpty(imgFlowid)) {
					Object code = MfpContextHolder.getSession().getAttribute("randCheckCode");
					if (StringUtils.isEmpty(code)) {
						throw new BadCredentialsException("验证码已过期!");
					}
					if (!imgFlowid.equalsIgnoreCase(code.toString())) {
						throw new BadCredentialsException("验证码有误，请重新输入！");
					}
				}
				// 清空错误次数
				sysAccountDao.clearErrorCount(BaseUtils.map("account_id", accountId));
			}
			//添加用户map到MfpContextHolder session
			MfpContextHolder.getSession().setAttribute("userMap",userMap);
			return true;
	}

	/**
	 * 密码/指纹/短信验证码/图形验证码检验 根实现方法，可重写
	 *
	 * @return
	 */
//	public boolean matches(BaseAccount acc) {
//		String accountId = acc.getAccountId();
//		String entryPwd = acc.getEntryPwd();
//		String imgFlowid = acc.getImgFlowid();
//		Object flag = MfpContextHolder.getSession().getAttribute("flag");
//		if ("1".equals(flag)) {
//			// 员工登录
//			Map<String, Object> userMap = sysAccountDao.queryForMap(BaseUtils.map("account_id", accountId));
//			if (userMap.size() == 0) {
//				throw new BadCredentialsException("输入的账号或密码有误，请重新输入！");
//			}
//			int error_count = Integer.parseInt(userMap.get("error_count").toString());
//			int count = Integer.parseInt(MfpContextHolder.getProps("security.login.maxErrCount"));
//			if (StringUtils.isEmpty(imgFlowid) && error_count >= count) {
//				throw new BadCredentialsException("密码错误次数超过" + count + "次,需开启图形验证码验证");
//			}
//			// 验证密码是否正确
//			String login_pwd = userMap.get("login_pwd").toString();
//			if (!passwordEncoder.matches(entryPwd, login_pwd)) {
//				// 更新密码错误次数
//				sysAccountDao.addErrorCount(BaseUtils.map("account_id", accountId));
//				throw new BadCredentialsException("输入的账号或密码有误，请重新输入！");
//			}
//			// 判断图形验证码是否正确
//			if (!StringUtils.isEmpty(imgFlowid)) {
//				Object code = MfpContextHolder.getSession().getAttribute("randCheckCode");
//				if (StringUtils.isEmpty(code)) {
//					throw new BadCredentialsException("验证码已过期!");
//				}
//				if (!imgFlowid.equalsIgnoreCase(code.toString())) {
//					throw new BadCredentialsException("验证码有误，请重新输入！");
//				}
//			}
//			// 清空错误次数
//			sysAccountDao.clearErrorCount(BaseUtils.map("account_id", accountId));
//			return true;
//		} else {
//			logger.info("手机号码：" + accountId + ",短信验证码：" + entryPwd);
////			checkMsgCode(accountId, entryPwd);
//			return true;
//		}
//	}
//
//	/**
//	 * 校验短信验证码
//	 *
//	 * @param mobile
//	 * @param msgCode
//	 * @return
//	 */
//	private boolean checkMsgCode(String mobile, String msgCode) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		Date date = new Date(System.currentTimeMillis());
//		String SYS_REQ_TIME = sdf.format(date);
//		String traceId = System.currentTimeMillis() + "";
//		Map<String, Object> tplParam = new HashMap<String, Object>();
//		tplParam.put("SYS_EVT_TRACE_ID", traceId);
//		tplParam.put("SYS_REQ_TIME", SYS_REQ_TIME);
//		tplParam.put("TXN_DT", SYS_REQ_TIME.substring(0, 8));
//		tplParam.put("TXN_TM", SYS_REQ_TIME.substring(8));
//		tplParam.put("MblPhNo", mobile);
//		tplParam.put("Ori_TrcNo",getTrcNoByMobile(mobile));
//		tplParam.put("SMS_Vld_CD", msgCode);
//
//		String url = getSendMsgUrl();
//		Map<String, Object> sendMsg = MessageUtils.sendMessage(url, "ZFUSR0302.tpl", tplParam);
//		// retCode可能的结果：0或00－成功，01－失败,02-不确定。0001,0002,0003,0004都是异常
//		String retCode = (String) sendMsg.get("retCode");
//		if (("0".equals(retCode)) || ("00".equals(retCode))) { // 成功
//			return true;
//		} else {
//			logger.error("登录失败：" + sendMsg.get("retCode") + "|" + sendMsg.get("retMsg"));
//			throw new BadCredentialsException((String) sendMsg.get("retMsg"));
//		}
//	}
//
//	/**
//	 * 获取发送短信地址
//	 * @return
//	 */
//	private String getSendMsgUrl() {
//		 //获取发送短信地址
//  		Map<String, Object> qyp = new HashMap<String, Object>();
//  		qyp.put("sys_para_code", "WG_SMS_URL");
//  		String wgSmsUrl = (String) cmSysControlDao.queryForMap(qyp).get("sys_para_value");
//  		logger.info("发送短信地址：" + wgSmsUrl);
//		return wgSmsUrl;
//	}
//
//	/**
//	 * 根据手机号码查询流水号
//	 *
//	 * @param mobile
//	 * @return
//	 */
//	private String getTrcNoByMobile(String mobile) {
//		Map<String, Object> p = new HashMap<String, Object>();
//		p.put("mobile_no", mobile);
//		return (String) bcWgSmsTrcnoDao.selectTrcNoByMobile(p).get(0).get("trcno");
//	}

	/**
	 * 根据不同的操作系统初始化不同的指纹验证接口
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public List<GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(List<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

}
