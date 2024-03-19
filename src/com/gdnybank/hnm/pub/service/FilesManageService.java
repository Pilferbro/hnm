package com.gdnybank.hnm.pub.service;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.enums.DMFailCodeEnum;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.sunyard.client.SunEcmClientApi;
import com.sunyard.client.bean.ClientBatchBean;
import com.sunyard.client.bean.ClientBatchFileBean;
import com.sunyard.client.bean.ClientBatchIndexBean;
import com.sunyard.client.bean.ClientFileBean;
import com.sunyard.client.impl.SunEcmClientSocketApiImpl;
import com.sunyard.ecm.server.bean.BatchBean;
import com.sunyard.ecm.server.bean.BatchFileBean;
import com.sunyard.ws.utils.XMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc：文件管理服务
 *
 * @author pzz
 * @date 2016-11-24
 *
 */
@Service
public class FilesManageService implements InitializingBean {
	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private SysParamService sysParamService;
	@Autowired
	private SysAccountDao sysAccountDao;

/*
	@Resource
	private CliAccountDao cliAccountDao;
*/

	private static String SOCKET_IP = null; // 上传使用的socket通信IP
	private static String SOCKET_PORT = null; // 上传使用的ssocket通信端口
	private static String SOCKET_IP2 = null; // 查询使用的socket通信IP
	private static String SOCKET_PORT2 = null; // 查询使用的ssocket通信端口
	// private static String SERVER_NAME = null; //连接的服务工程名称(暂时没用)
	private static String GROUP_NAME = null; // 内容存储服务器组名
	private static String MODEL_CODE = null; // 内容模型代码
	private static String FILE_PART_NAME = null; // 文档部件模型代码
	private static String USERNAME = null; // 用户名
	private static String PASSWORD = null; // 密码
	private SunEcmClientApi clientApi = null;
	private static final String SPLIT = "<<::>>"; // 分隔符
	private static final String SPLIT_1 = "#"; // 分隔符

	/**
	 * desc:初始化参数
	 */
	private void init() {
		SOCKET_IP = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"SOCKET_IP", null);
		SOCKET_PORT = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"SOCKET_PORT", null);
		SOCKET_IP2 = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"SOCKET_IP2", null);
		SOCKET_PORT2 = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"SOCKET_PORT2", null);
		GROUP_NAME = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"GROUP_NAME", null);
		MODEL_CODE = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"MODEL_CODE", null);
		FILE_PART_NAME = sysParamService.getSysParam("FILE_SYSTEM_INFO",
				"FILE_PART_NAME", null);
		USERNAME = sysParamService.getSysParam("FILE_SYSTEM_INFO", "USERNAME",
				null);
		PASSWORD = sysParamService.getSysParam("FILE_SYSTEM_INFO", "PASSWORD",
				null);
		clientApi = null;
		clientApi = new SunEcmClientSocketApiImpl(SOCKET_IP,
				Integer.parseInt(SOCKET_PORT));
	}

	/**
	 * desc:上传
	 *
	 * @param files
	 *            本地文件
	 * @param flowNo
	 *            关联表主键：业务流水主键 或 户外签约主键
	 * @param deleteLocalFileFlag
	 *            是否删除本地文件 true 是 false 否 (只有成功的才判断是否删除)
	 * @return 批次信息
	 * @throws Exception
	 */
	public Map<String, Object> upload(File[] files, String busi_seq_no,
									  boolean deleteLocalFileFlag) throws Exception {
		if (files == null || files.length <= 0)
			return null;
		List<ClientFileBean> clientFileBeanList = new ArrayList<ClientFileBean>();
		int busiFilePagenum = 1;
		for (File file : files) {
			String fileName = file.getName(); // 文件真实名称
			// 如果文件数组中包含没有后缀名的文件，需忽略该文件
			if (fileName.indexOf(".") == -1) {
				continue;
			}
			String fileSuffix = fileName.substring(fileName.indexOf(".") + 1); // 文件后缀名
			ClientFileBean fileBean = new ClientFileBean();
			fileBean.setFileName(file.getAbsolutePath());
			fileBean.setFileFormat(fileSuffix);
			fileBean.addOtherAtt("TRUENAME",
					fileName.substring(0, fileName.indexOf(".")));
			fileBean.addOtherAtt("BUSI_FILE_PAGENUM", busiFilePagenum + "");// 文件的序号
			fileBean.addOtherAtt("BUSI_FILE_TYPE", "009001");// 影像平台规定
			clientFileBeanList.add(fileBean);
			busiFilePagenum++;
		}

		ClientBatchBean clientBatchBean = new ClientBatchBean();
		clientBatchBean.setModelCode(MODEL_CODE);
		clientBatchBean.setUser(USERNAME);
		clientBatchBean.setPassWord(PASSWORD);
		clientBatchBean.setBreakPoint(false); // 是否作为断点续传上传
		clientBatchBean.setOwnMD5(false); // 是否为批次下的文件添加MD5码

		// 若内容模型配置有安全校验
		// clientBatchBean.setToken_check_value(token_check_value);
		// clientBatchBean.setToken_code(tokenCode);

		// =========================设置索引对象信息开始=========================
		ClientBatchIndexBean clientBatchIndexBean = new ClientBatchIndexBean();
		clientBatchIndexBean.setAmount(clientFileBeanList.size() + "");// 必须与上传的设置文件数量一致（校验使用）
		// 索引自定义属性
		clientBatchIndexBean.addCustomMap("AMOUNT", clientFileBeanList.size()
				+ ""); // 入库使用
		clientBatchIndexBean.addCustomMap("BUSI_SERIAL_NO", busi_seq_no);
		clientBatchIndexBean.addCustomMap("BUSI_START_DATE",
				DateUtils.getDate("yyyyMMdd"));

		// =========================设置索引对象信息结束=========================

		// =========================设置文档部件信息开始=========================
		ClientBatchFileBean clientBatchFileBeanA = new ClientBatchFileBean();
		clientBatchFileBeanA.setFilePartName(FILE_PART_NAME);
		// =========================设置文档部件信息结束=========================

		// =========================添加文件=========================
		for (ClientFileBean clientFileBean : clientFileBeanList) {
			clientBatchFileBeanA.addFile(clientFileBean);
		}
		// =========================添加文件=========================
		clientBatchBean.setIndex_Object(clientBatchIndexBean);
		clientBatchBean.addDocument_Object(clientBatchFileBeanA);
		String batchInfo = clientApi.upload(clientBatchBean, GROUP_NAME);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		logger.debug("busi_seq_no:[" + busi_seq_no + "]上送完成，结果[" + batchInfo
				+ "]");
		if (batchInfo == null || StringUtils.isBlank(batchInfo)) { // 失败：远程服务返回空
			resultMap.put("send_error_msg", "上送失败，文件系统返回空");
			return resultMap;
		}
		String[] batchInfoAry = batchInfo.split(SPLIT);
		if (batchInfoAry == null || batchInfoAry.length <= 0) { // 失败：远程服务返回空
			resultMap.put("send_error_msg", "上送失败，文件系统返回空");
			return resultMap;
		}
		String batchSuccess = (batchInfoAry.length > 0) ? batchInfoAry[0]
				: null;
		String batchId = (batchInfoAry.length > 1) ? batchInfoAry[1] : null;
		resultMap.put("batchid", batchId);
		if ("success".equalsIgnoreCase(batchSuccess)) {
			if (batchId == null || StringUtils.isBlank(batchId)) {// 失败：远程服务返回批次号空
				resultMap.put("send_error_msg", "上送成功，但返回批次号为空[" + batchId
						+ "]");
				return resultMap;
			}
			// 成功
			resultMap.put("send_error_msg", "");
			try {
				if (deleteLocalFileFlag) {
					File fileDir = files[0].getParentFile();
					fileDir.deleteOnExit();
				}
			} catch (Exception e) {
				logger.error("上传至文件服务器后，删除本地文件失败，原因：", e);
			}
			return resultMap;
		}
		// 失败：远程服务返回错误编码
		String failCode = "";
		String errorMsg = "";
		if (batchInfoAry.length == 2) { // 错误信息返回格式1 ： 异常代码<<::>>异常信息
			if (resultMap.containsKey("batchid")) {
				resultMap.remove("batchid");
			}
			failCode = batchInfoAry[0];
			errorMsg = "[" + failCode + "]:"
					+ DMFailCodeEnum.getDescByCode(failCode) + "--"
					+ batchInfoAry[1];
		} else if (batchInfoAry.length > 2) { // 错误信息返回格式2：
			// FAIL<<::>>批次号<<::>>异常代码
			failCode = batchInfoAry[2];
			errorMsg = "[" + failCode + "]:"
					+ DMFailCodeEnum.getDescByCode(failCode);
		} else {
			if (resultMap.containsKey("batchid")) {
				resultMap.remove("batchid");
			}
			errorMsg = batchInfo;
		}
		resultMap.put("send_error_msg", errorMsg);
		return resultMap;
	}

	/**
	 * desc：根据批次号查询批次信息
	 *
	 * @param batchId
	 *            批次号
	 * @return 返回第一批次的文件列表
	 * @throws Exception
	 */
	public Object queryFilePathBybatchId(String batchId, String uploadDateStr)
			throws Exception {
		ClientBatchBean clientBatchBean = new ClientBatchBean();
		clientBatchBean.setModelCode(MODEL_CODE);
		clientBatchBean.setUser(USERNAME);
		clientBatchBean.setPassWord(PASSWORD);
		clientBatchBean.setDownLoad(false);
		// clientBatchBean.getIndex_Object().setVersion("1");
		clientBatchBean.getIndex_Object().setContentID(batchId);
		clientBatchBean.getIndex_Object().addCustomMap("BUSI_START_DATE",
				uploadDateStr);

		ClientBatchFileBean documentObjectA = new ClientBatchFileBean();
		documentObjectA.setFilePartName(FILE_PART_NAME); // 要查询的文档部件名
		clientBatchBean.addDocument_Object(documentObjectA);
		// documentObjectA.addFilter("TRUENAME",
		// "009420_2015120400000013_FIN000002_003");

		// 若内容模型配置有安全校验
		// clientBatchBean.setToken_check_value(token_check_value);
		// clientBatchBean.setToken_code(tokenCode);

		String resultMsg = clientApi.queryBatch(clientBatchBean, GROUP_NAME);
		logger.info("#######查询批次返回的信息[" + resultMsg + "]#######");
		if (resultMsg.indexOf("0001<<::>>") >= 0) {
			resultMsg = resultMsg.replace("0001<<::>>", "")
					.replace("<root>", "").replace("</root>", "");
			BatchBean batchBean = (BatchBean) XMLUtil.xml2Bean(resultMsg,
					BatchBean.class);
			List<BatchFileBean> lstBatchBean = batchBean.getDocument_Objects();
			if (lstBatchBean != null && lstBatchBean.size() > 0) {
				BatchFileBean batchFileBean = lstBatchBean.get(0); // 获取第一个批次
				return batchFileBean.getFiles();
			}
		}
		return resultMsg;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void refresh() {
		init();
	}

	/**
	 * 删除接口调用示例 -------------------------------------------------------
	 */
	public void deleteExample(String batchId) {
		ClientBatchBean clientBatchBean = new ClientBatchBean();
		clientBatchBean.setModelCode(MODEL_CODE);
		clientBatchBean.setPassWord(PASSWORD);
		clientBatchBean.setUser(USERNAME);
		clientBatchBean.getIndex_Object().setContentID(batchId);
		clientBatchBean.getIndex_Object().addCustomMap("BUSI_START_DATE",
				DateUtils.getDate("yyyyMmdd"));
		// 若内容模型配置有安全校验
		// clientBatchBean.setToken_check_value(token_check_value);
		// clientBatchBean.setToken_code(tokenCode);

		try {
			String resultMsg = clientApi.delete(clientBatchBean, GROUP_NAME);
			logger.debug("#######删除批次返回的信息[" + resultMsg + "]#######");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成静态URL供数仓使用
	 */
	public String createBatchUrl(Map<String, Object> fileInfo) {

		Map<String, Object> map = sysAccountDao.queryForMap(BaseUtils.map("account_id",fileInfo.get("upload_user_id")));
		String username = map.get("name")==null?"":map.get("name").toString();
		String orgname = "";
		try {
			username = URLEncoder.encode(username,"utf8");
			orgname = URLEncoder.encode("南粤银行", "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.debug("字符串转换utf8出错");
		}

		StringBuffer sb = new StringBuffer();
		sb.append("http://").append(SOCKET_IP2).append(":")
				.append(SOCKET_PORT2)
				.append("/SunIAS/SunIASRequestServlet.do?");
		sb.append("UID=").append(USERNAME).append("&");
		sb.append("PWD=").append(PASSWORD).append("&");
		sb.append("AppID=").append("YDZY").append("&");
		sb.append("UserID=").append(fileInfo.get("upload_user_id")).append("&");
		sb.append("UserName=").append(username).append("&");
		sb.append("OrgID=").append("9999").append("&");
		sb.append("OrgName=").append(orgname).append("&");
		sb.append("info1=BUSI_SERIAL_NO:").append("010404")
				.append(fileInfo.get("id").toString()).append(";");
		sb.append("OBJECT_NAME:").append("YDZY").append(";");
		sb.append("QUERY_TIME:").append(DateUtils.getDate("yyyyMMdd"))
				.append(";");
		sb.append("FILELEVEL:1;RIGHT:0100000");
		return sb.toString();

	}
}
