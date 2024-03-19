/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nantian.mfp.safe.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.nantian.mfp.framework.cache.Cache;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.HttpException;
import com.nantian.mfp.framework.utils.MapUtils;
import com.nantian.mfp.framework.utils.RequestUtils;
import com.nantian.mfp.framework.utils.SafeConstants;
import com.nantian.mfp.pub.dao.CliAccountLocationDao;
import com.nantian.mfp.pub.dao.model.CliAccountLocation;
import com.nantian.mfp.pub.dao.model.CliTxinfo;
import com.nantian.mfp.pub.service.SysSessionService;
import com.nantian.mfp.safe.filter.TxCheckFilter;


/**
 * Filter required by concurrent session handling package.
 * <p>
 * This filter performs two functions. First, it calls
 * {@link SessionRegistry#refreshLastRequest(String)} for each request
 * so that registered sessions always have a correct "last update" date/time. Second, it retrieves a
 * {@link SessionInformation} from the <code>SessionRegistry</code>
 * for each request and checks if the session has been marked as expired.
 * If it has been marked as expired, the configured logout handlers will be called (as happens with
 * {@link org.springframework.security.web.authentication.logout.LogoutFilter}), typically to invalidate the session.
 * A redirect to the expiredURL specified will be performed, and the session invalidation will cause an
 * {@link org.springframework.security.web.session.HttpSessionDestroyedEvent} to be published via the
 * {@link org.springframework.security.web.session.HttpSessionEventPublisher} registered in <code>web.xml</code>.</p>
 *
 * @author Ben Alex
 */
public class MFPConcurrentSessionFilter extends GenericFilterBean implements Cache{
    //~ Instance fields ================================================================================================

    private SessionRegistry sessionRegistry;
    private String expiredUrl;
    private LogoutHandler[] handlers = new LogoutHandler[] {new SecurityContextLogoutHandler()};
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    Map<String,List<CliAccountLocation>> locationCache;
  
	@Autowired
	private CliAccountLocationDao accountLocationDao;
    
	@Autowired
	private SysSessionService sysSessionService;

    //~ Methods ========================================================================================================


    /**
     * @deprecated Use constructor which injects the <tt>SessionRegistry</tt>.
     */
    public MFPConcurrentSessionFilter() {
    }

    public MFPConcurrentSessionFilter(SessionRegistry sessionRegistry) {
        this(sessionRegistry, null);
    }

    public MFPConcurrentSessionFilter(SessionRegistry sessionRegistry, String expiredUrl) {
        this.sessionRegistry = sessionRegistry;
        this.expiredUrl = expiredUrl;
    }

    @Override
    public void afterPropertiesSet() {
    	try {
			initFilterBean();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assert.notNull(sessionRegistry, "SessionRegistry required");
        Assert.isTrue(expiredUrl == null || UrlUtils.isValidRedirectUrl(expiredUrl),
                expiredUrl + " isn't a valid redirect URL");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
    	 HttpServletRequest request = (HttpServletRequest) req;
         HttpServletResponse response = (HttpServletResponse) res;

         HttpSession session = request.getSession(false);
         
         String txcode=request.getParameter("txcode");
         CliTxinfo tx=null;

 		if(StringUtils.hasText(txcode)){
 			tx=TxCheckFilter.txinfoCache.get(txcode);
 		}

        if (session != null) {
             SessionInformation info = sessionRegistry.getSessionInformation(session.getId());
             logger.debug("[ConcurrentSessionFilter]当前访问用户的sessionid为:"+session.getId());
             MfpContextHolder.setCurrentUserId(MfpContextHolder.getLoginId());
             logger.debug("当前用户ID："+MfpContextHolder.getCurrentUserId());
             	/**
                  * 判断是否需要进行登录控制
                  */
                 Object userObj=MfpContextHolder.getCurrentUser();
                 UserDetails user=null;
                 if(userObj instanceof UserDetails ){
                 	user=(UserDetails)userObj;
                 }
                boolean hadlogin=user==null?false:true;
         		if(tx!=null&&"1".equals(tx.getIslogin())){
         			if(!hadlogin){
         				if(RequestUtils.isAjaxRequest(request)||RequestUtils.isWebSocketReq(request)){
         					logger.error("检查用户的登录状态不通过,该交易需要先登录,返回客户端 471 状态码");
         						throw new HttpException(471,"txCheckFilter158","该交易需要先登录");
         				}else{
         					
         					 doLogout(request, response);
         					 
                             String targetUrl = determineExpiredUrl(request, info);
                             
                             if (targetUrl != null) {
                                 redirectStrategy.sendRedirect(request, response, targetUrl);
                                 return;
                             } else {
                                 response.getWriter().print("This session has been expired (possibly due to multiple concurrent " +
                                         "logins being attempted as the same user).");
                                 response.flushBuffer();
                             }
                             return;
         				}
         			}
         		}
         		
         		
                /**
                 * 判断是否需要地理位置信息检查,如果需要检查,从http协议头中取出位置信息头LocationInfo进行判断业务是否超出了办理范围
                 */
        		boolean isInLocation=false;
                if(hadlogin &&tx!=null&&"1".equals(tx.getIslocation())){
                	String locationInfo=request.getHeader("LocationInfo");
                	if(null==locationInfo || "".equals(locationInfo)){
                		logger.error("检查位置信息不通过,交易["+txcode+"]需要上送位置信息,返回客户端 473 状态码");
                		throw new HttpException(473,"txCheckFilter170","交易["+txcode+"]需要上送位置信息");
                	}
                	try {
                		String[] liArr=locationInfo.split("/");
        				Double longitude=Double.parseDouble(liArr[1]);
        				Double latitude=Double.parseDouble(liArr[2]);
        				List<CliAccountLocation> accountLocationList=locationCache.get(user.getUsername());
        				
        				for (int i = 0; i < accountLocationList.size(); i++) {
        					CliAccountLocation location=accountLocationList.get(i);
        					double distance=MapUtils.getDistance(longitude, latitude, location.getLongitude(), location.getLatitude());
        					if(distance<location.getRadius()){
        						isInLocation=true;
        						break;
        					}
        				}
        				if(!isInLocation){
        					logger.error("检查位置信息不通过,您当前的地理位置超出了业务办理所允许的范围了,请回到既定区域内再办理业务,返回客户端 474 状态码");
        					throw new HttpException(474,"txCheckFilter188","您当前的地理位置超出了业务办理所允许的范围了,请回到既定区域内再办理业务");
        				}
        			} catch (NumberFormatException e) {
        				logger.error("解析位置信息错误,请检查上送的位置信息是否正确,返回客户端 488 状态码",e);
        				throw new HttpException(488,"txCheckFilter192","解析位置信息错误,请检查上送的位置信息是否正确");
        			}
                	
                }
             if(info==null&&hadlogin){
            	
            	 Map<String,Object> principal=new HashMap<String,Object>();
            	 principal.put("username", user.getUsername());
            	 principal.put("deviceid", request.getSession().getAttribute(SafeConstants.FW_SAFE_DEVICEID));
         		 principal.put("devicetype", "browser");
            	 List<SessionInformation> others = sessionRegistry.getAllSessions(principal, false);
            	 if(others.size()>0){
            	 	SecurityContextHolder.getContext().setAuthentication(null);
            	 	if(RequestUtils.isAjaxRequest(request)||RequestUtils.isWebSocketReq(request)){
						throw new HttpException(472,"txCheckFilter209","您的账号已在其他设备登录");
					}else {

            	 		logger.debug("用户账号在其他电脑登录,跳转到登录页面");
						doLogout(request, response);

						String targetUrl = determineExpiredUrl(request, info);

						if (targetUrl != null) {
							redirectStrategy.sendRedirect(request, response, targetUrl);
							return;
						} else {
							response.getWriter().print("This session has been expired (possibly due to multiple concurrent " +
									"logins being attempted as the same user).");
							response.flushBuffer();
						}
						return;
					}


				 }
             }
         		
             if (info != null) {
                     sessionRegistry.refreshLastRequest(info.getSessionId());
             }
             
         }

         chain.doFilter(request, response);
    }

    protected String determineExpiredUrl(HttpServletRequest request, SessionInformation info) {
        return expiredUrl;
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (LogoutHandler handler : handlers) {
            handler.logout(request, response, auth);
        }
    }

    /**
     * @deprecated use constructor injection instead
     */
    @Deprecated
    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }

    /**
     * @deprecated use constructor injection instead
     */
    @Deprecated
    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public void setLogoutHandlers(LogoutHandler[] handlers) {
        Assert.notNull(handlers);
        this.handlers = handlers;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    
  	/**
  	 * 当系统参数或者重启服务器后，会重新初始化此类
  	 */
  	protected void initFilterBean() throws ServletException {
  		
        locationCache=new HashMap<String,List<CliAccountLocation>>();
		List<CliAccountLocation> lal= accountLocationDao.queryForList(null, CliAccountLocation.class);
		for (int i = 0; i < lal.size(); i++) {
			CliAccountLocation accountLocation=lal.get(i);
				if(locationCache.containsKey(accountLocation.getAccountid())){
					List<CliAccountLocation> accountLocationList=(List<CliAccountLocation>) locationCache.get(accountLocation.getAccountid());
					accountLocationList.add(accountLocation);
					locationCache.put(accountLocation.getAccountid(), accountLocationList);
				}else{
					List<CliAccountLocation> accountLocationList=new ArrayList<CliAccountLocation>();
					accountLocationList.add(accountLocation);
					locationCache.put(accountLocation.getAccountid(), accountLocationList);
				}
		}
  		
  		
  		/**
  		 * 从系统参数表中获取营业开始和营业结束时间
  		 */
  	}
      
  	@Override
  	public void refresh() {
  		try {
  			this.initFilterBean();
  		} catch (ServletException e) {
  			logger.error(e);
  		}
  	}
}
