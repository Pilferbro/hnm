package com.nantian.mfp.pub.ctrl;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.service.TXService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.framework.utils.RequestSystemUtils;
import com.nantian.mfp.pub.dao.SysTxlogDao;
import com.nantian.mfp.pub.service.SysParamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 带有交易码的ajax纯数据请求的总控 请求如: /应用英文名/txCtrl?txcode=acc001
 *
 * @author
 */
@Controller
@RequestMapping("/txCtrl")
public class TxController {

	Log logger = LogFactory.getLog(getClass());
	@Autowired
    SysTxlogDao sysTxlogDao;
	@Autowired
	SysParamService sysParamService;

	/***
	 * 普通纯数据请求
	 *
	 * @param p
	 * @param req
	 * @param txcode
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping()
	public Object doTx(@RequestBody(required = false) Map<String, Object> p, HttpServletRequest req,
                       @RequestParam String txcode, Model model) throws Exception {

		p = mergeParameter(p, req);
		/** url 来源于cli_txinfo中的url,在TxCheckFilter中放入req中 */

		// 获取上传的文件
		if (req != null && req instanceof MultipartRequest) {
			MultipartRequest multipartRequest = (MultipartRequest) req;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			if (fileMap != null && fileMap.size() > 0) {
				List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
				p.put("files", multipartFileList);
				for (Map.Entry<String, MultipartFile> file : fileMap.entrySet()) {
					logger.debug("key==" + file.getKey() + "==filename:"
							+ file.getValue().getOriginalFilename() + "==name:"
							+ file.getValue().getName() + "==size:"
							+ file.getValue().getSize());
					multipartFileList.add(file.getValue());
				}
			}
		}

		String url = (String) req.getAttribute("txUrl");
		Object value = callService(getTxEnv(req, txcode), p, req, txcode);
		model.addAttribute("data", value);
		return url;
	}

	/**
	 * 新增“LGN000003”的请求捕捉，用于处理用户登出
	 *
	 * @param req
	 * @param txcode
	 * @throws Exception
	 */
	@RequestMapping(params = "txcode=LGN000003")
	public void logout(HttpServletRequest req, @RequestParam String txcode)
			throws Exception {
		Assert.notNull(req, "HttpServletRequest required");
		HttpSession session = req.getSession(false);
		if (session != null) {
			logger.debug("Invalidating session: " + session.getId());
			session.invalidate();
		}

		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(null);

		SecurityContextHolder.clearContext();
		Map<String, Object> params = new HashMap<String, Object>();
		params = mergeParameter(params, req);
		// 如果是被单点登录拦截下来跳转的请求会带上“sessionCheck＝false”的请求参数
		if ("false".equals(params.get("sessionCheck"))
				|| "false" == params.get("sessionCheck")) {
			String msg = "该用户已在别处登录！";
			throw new BusinessException("TxController010", msg);
		} else {
			String msg = MfpContextHolder.getLoginId() + "用户登出成功";
			logger.info(msg);
		}
	}

	@RequestMapping(value = "/dataTables")
	public Object dataTables(@RequestBody(required = false) List<Map<String, Object>> pl, HttpServletRequest req,
                             @RequestParam String txcode) throws Exception {
		Map<String, Object> p = new HashMap<String, Object>();
		for (int i = 0; i < pl.size(); i++) {
			Map<String, Object> tempp = pl.get(i);
			p.put((String) tempp.get("name"), tempp.get("value"));
		}
		p = mergeParameter(p, req);
		PageInfo pageInfo = this.initPageInfo(p);
		MfpContextHolder.setPageInfo(pageInfo);
		Object data = callService(getTxEnv(req, txcode), p, req, txcode);
		PageInfo pi = MfpContextHolder.getPageInfo();
		Map<String, Object> result = BaseUtils.map("sEcho", pageInfo.getSEcho(), "iDisplayLength",
				pageInfo.getIDisplayLength(), "iDisplayStart", pageInfo.getIDisplayStart(), "iTotalDisplayRecords",
				pi.getITotalDisplayRecords(), "data", data);
		return result;
	}

	//@RequestMapping(value = "/mediaFile")
	@RequestMapping(params = "txcode=mediaFile")
	public Object mediaFile(@RequestBody(required = false) Map<String, Object> p, HttpServletRequest req,
							HttpServletResponse resp) throws Exception {
		p = mergeParameter(p, req);
		if(("".equals(p.get("filePath"))||(p.get("filePath")==null))){
			logger.error("filePath为空");
			throw new BusinessException("txController004", "filePath不能为空");

		}
		String filePath = p.get("filePath").toString();
		if(!filePath.startsWith("/")){
			String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR","/home/weblogic/hnm/info");
			//String sysPath = "/Users/zjh/Desktop";
			sysPath = sysPath + "/";
			filePath = sysPath + filePath;
		}
		String allowFilePath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR","/home/weblogic/hnm/info");
		//String allowFilePath = "/Users/zjh/Desktop/";
		if(!filePath.startsWith(allowFilePath)){
			logger.error("无法访问该路径:"+filePath);
			throw new BusinessException("txController002", "访问路径出错！");
		}
		if(filePath.contains("../")){
			logger.error("无法访问该路径:"+filePath);
			throw new BusinessException("txController003", "访问路径出错！");
		}
		p.put("filePath", filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			String msg = filePath + " 该路径下找不到对应的文件";
			logger.error(msg);
			throw new BusinessException("txController", msg);
		}
		// 读出文件到response
		//根据不同的请求类型设置response
		if("1".equals(p.get("isDownload"))){
			resp.reset();
			resp.setContentType("application/download;charset=utf-8"); // 设置传输文件类型
			resp.addHeader("Content-Disposition", "filename=" + new String(((String) p.get("fileName")).getBytes("utf-8"), "iso-8859-1"));
		}
		// 这里是先需要把要把文件内容先读到缓冲区
		// 再把缓冲区的内容写到response的输出流供用户下载
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		byte[] b = new byte[bufferedInputStream.available()];
		bufferedInputStream.read(b);
		OutputStream outputStream = resp.getOutputStream();
		outputStream.write(b);

		// 人走带门
		bufferedInputStream.close();
		outputStream.flush();
		outputStream.close();
		//使用文件后删除
		if (("1".equals(p.get("isDelete")))&&(file.exists())) {
			file.delete();
		}
		return null;
	}

	@RequestMapping(value = "/getFile")
	public Object getFile(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		URL url = new URL(req.getParameter("filePath"));
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		resp.addHeader("Content-Disposition", "filename=" + new String((req.getParameter("fileName")).getBytes("utf-8"), "iso-8859-1"));
		OutputStream out = resp.getOutputStream();

		byte[] b=new byte[1024*1024];
		int a=0;
		while((a=in.read(b))!=-1){
			out.write(b, 0, a);
		}

		out.flush();
		out.close();
		in.close();
		return null;
	}


	protected Map<String, Object> mergeParameter(Map<String, Object> p, HttpServletRequest req) {
		if (p == null) {
			p = new HashMap<String, Object>();
		}
		Map<String, String[]> p1 = req.getParameterMap();
		for (Entry<String, String[]> entry : p1.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (values.length > 1) {
				p.put(key, values);
			} else {
				p.put(key, values[0]);
			}
		}
		return p;
	}

	public Object callService(Map<String, Object> env, Map<String, Object> p, HttpServletRequest req, String txcode)
			throws Exception {
		Object bean = null;
		Object value = null;
		String beanName = "t" + txcode + "Service";
		if (logger.isDebugEnabled()) {
			logger.debug(
					"交易码[" + txcode + "],对应的服务类应为[T" + txcode + "Service],或者服务名称应为[" + beanName + "],应该实现TXService接口");
		}
		try {
			bean = MfpContextHolder.getBean(beanName);

		} catch (Exception e) {
			logger.error("没有找到交易服务[" + beanName + "]" + "交易码[" + txcode + "],对应的服务类类名应为[T" + txcode
					+ "Service]或者服务名称应为[" + beanName + "].\n" + "该错误可能是服务类注解配置错误引起的,请确保满足下列条件之一 \n 1.服务类类名为[T" + txcode
					+ "Service],并且其中的注解为 @Service \n 2.服务类类名为任意名字,但是注解为 @Service(\"" + beanName + "\")", e);
			throw new BusinessException("txController45", "没有找到交易服务[" + beanName + "]");
		}
		if (bean instanceof TXService) {
			TXService service = (TXService) bean;
			// 新增请求参数和返回结果的输出、交易流水表的日志纪录
			logger.debug("请求参数: " + p.toString());
			int status = 0;// 执行结果，0:成功，1:失败
			Map<String, Object> params = new HashMap<String, Object>();// 用于传输日志纪录的参数
			long startTime = 0l;
			// txcode:交易码，tran_date:交易日期，start_time:交易开始时间，end_time:交易结束时间，use_time:交易耗时，user_id:客户号，req_flow_no：请求流水号，status：执行结果,errCode:
			try {
				params.put("txcode", txcode);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				params.put("tran_date", sdf.format(new Date()));
				params.put("user_id", env.get("userid"));
				params.put("req_flow_no", MfpContextHolder.getReqFlowNo());
				sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SS");
				startTime = System.currentTimeMillis();
				params.put("start_time", sdf.format(new Date(startTime)));
				value = service.doService(env, p);
			} catch (Exception e) {
				// 纪录日志表
				status = 1;
				e.printStackTrace();
				if (null == e.getMessage() || "".equals(e.getMessage())) {
					params.put("err_msg", e.toString());
				} else {
					params.put("err_msg", e.getMessage());
				}
				if (e instanceof BusinessException) {
					params.put("err_code", ((BusinessException) e).getTerrcode());
					throw new BusinessException(((BusinessException) e).getTerrcode(), e.getMessage());
				} else {
					throw new Exception(e);
				}
			} finally {
				long endTime = System.currentTimeMillis();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SS");
				params.put("end_time", sdf.format(new Date(endTime)));
				long useTime = (endTime - startTime);
				params.put("use_time", useTime);
				params.put("status", status);
				sysTxlogDao.save(params);
			}
			logger.debug("service的返回结果为" + value.toString());
		} else {
			throw new BusinessException("txController51", bean.getClass() + "没有实现TXService接口,不能作为交易服务进行调用");
		}
		return value;
	}

	public PageInfo initPageInfo(Map<String, Object> p) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setIDisplayStart((Integer) p.get("iDisplayStart"));
		pageInfo.setSEcho((Integer) p.get("sEcho"));
		pageInfo.setIDisplayLength((Integer) p.get("iDisplayLength"));
		// add by lanweizhi 目前排序只支持select语句不存在重名字段的排序
		int iSortCol = (Integer) p.get("iSortCol_0");
		String sortName = (String) p.get("mDataProp_" + iSortCol);
		if (!"".equals(sortName) && sortName != null) {
			String sSortDir = (String) p.get("sSortDir_0");
			pageInfo.setOrderBy(" order by " + sortName + " " + sSortDir + " ");
		}
		// add end
		return pageInfo;
	}

	public Map<String, Object> getTxEnv(HttpServletRequest req, String txcode) {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("userid", MfpContextHolder.getLoginId());
		// env.put("userid", MfpContextHolder.getLoginId());
		env.put("txcode", txcode);
		env.put("sessionid", req.getSession().getId());
		String needSys = sysParamService.getSysParam("REQUEST_SYS_CONFIG", "NEED_SYS_TXCODE", "");
		Boolean checkFlag=-1<needSys.indexOf(txcode)? true:false;
		if(checkFlag){
			String ipAddr = RequestSystemUtils.getIpAddr(req);
			//String hostName = RequestSystemUtils.getHostName(ipAddr);
			//String  macAddress= RequestSystemUtils.getMacAddress(ipAddr);
			env.put("ip_address", ipAddr);
			//env.put("host_name", hostName);
			//env.put("mac_address", macAddress);
			System.out.println("env----"+env);
		}
		return env;
	}

}
