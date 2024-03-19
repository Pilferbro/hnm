package com.gdnybank.hnm.pub.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TTranFlowDao;
import com.gdnybank.hnm.pub.utils.Utils;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.service.SysParamService;
import org.apache.cxf.common.util.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.MsgProcService;

/**
 * 发送esb通信类
 * 该类负责组装发送到ESB的报文头，其中必须要获取到的字段如下：
 * txcode:交易码，用于组装系统头module_id字段。获取规则：先从mfp上下文获取，再从上送Map中获取。
 * bus_seq_no:业务流水号，用于组装报文头。获取规则：从上送Map中获取，该字段为交易进行之前先行从后端获取的流水号。
 *
 * user_id：登陆柜员号，用于组装应用头及获取其他信息。获取规则：先从mfp上下文获取，再从上送Map的HEADKEY中获取(主要是给登陆交易用，登陆交易上下文中没有用户信息，同时避免与报文中字段冲突)。
 * branch_id:柜员机构号，用于组装应用头。获取规则：先从mfp上下文获取，再从上送Map的HEADKEY中获取(主要是给登陆交易用，登陆交易上下文中没有用户信息，同时避免与报文中字段冲突)，最后如果柜员号存在则根据柜员号去查表获取机构号。
 * rural_branch_id：法人编号，用于组装本地头及获取核心会计日期。获取规则：先从mfp上下文获取，再从上送Map的HEADKEY中获取(主要是给登陆交易用，登陆交易上下文中没有用户信息，同时避免与报文中字段冲突)，最后如果柜员号存在则根据柜员号去查表获取法人编号。
 *
 * @author Administrator
 *
 */
@Service
public class EsbMsgProcService {
	private static final String CONSUMER_ID="010404"; //esb设置的请求系统编号
	private static final String CHANNEL_CODE="000001"; //esb设置的请求渠道号 000001（柜面渠道）
	private static final String SYS_PARAM_ESB_CONFIG= "ESB_CONFIG"; //esb配置对应值的sys_param配置
	private static final String SYS_PARAM_ESB_CONSUMER_ID = "ESB_CONSUMER_ID"; //esb设置的请求系统编号对应值得sys_param配置
	private static final String SYS_PARAM_ESB_CHANNEL_CODE = "ESB_CHANNEL_CODE"; //esb设置的请求渠道号对应值得sys_param配置
	//获取日志控制器
	private Logger log = Logger.getLogger(EsbMsgProcService.class);

	@Resource
	private MsgProcService msgProcService;
	@Resource
	private SysParamService sysParamService;
	@Resource
	private TTranFlowDao tTranFlowDao;
	@Resource
	private HnmCommService hnmCommService;
	/**
	 * 组装头部信息 发送
	 * @param dataMap 请求数据
	 * @param txcode 交易码
	 * @param check 是否需要验密
	 * @return
	 */
	public Map<String,Object> sendAndReceiveMsg(Map<String,Object> sendMap,boolean check){
		//组装获取获取初始化参数
		Map<String,Object> initMap = initParam(sendMap);

		Map<String,Object> retMap = new HashMap<String, Object>();
		Map<String,Object> logMap = new HashMap<String, Object>();


		//登记通讯流水日志
		regTransionLog(sendMap,retMap,initMap);

		//组装传入模板的数据模型
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap = buildFreeMarkerDataModel(sendMap,check,initMap);

		//组装模板名称
		String serviceCode = String.valueOf(sendMap.get("service_code")); //服务代码：esb交易码 由交易定义
		String serviceScene = String.valueOf(sendMap.get("service_scene")); //服务应用场景:esb场景号 由交易定义
		String modelCode = serviceCode +"_"+ serviceScene;

		Map<String,Object> receiveMap = null;
		try {
			//向esb发送请求
			receiveMap = msgProcService.sendAndReceiveMsg(dataMap, modelCode);
			try{
				//将返回map的键值key统一改为小写；
				retMap = Utils.changeKeyToLower(receiveMap);
				logMap = getLogMap(retMap);
			}catch(Exception e){
				log.error("esb交易完成后，大写转小写或组装通讯流水日志异常,原因：",e);
				//由于通讯流水日志的成功与否与交易通讯成功与否无关，所以不将异常抛出
			}
		} catch (Exception e) {
			logMap = new HashMap<String, Object>();
			if(e instanceof ConnectTimeoutException){
				logMap.put("status", "3");//3，超时异常
			}else{
				logMap.put("status", "0");
			}
			logMap.put("errmsg", e.getMessage());
			throw new BusinessException("EsbMsgProcService004", "通信过程类执行异常"+e.getMessage());
		}finally{
			regTransionLog(sendMap,logMap,initMap);
		}


		log.info("#sendAndReceiveMsg#:"+retMap);


		return retMap;
	}


	/**
	 * 1，统一获取必要的参数放到一个map中
	 * 2，交易过滤白名单，一些交易用默认柜员信息发esb报文。
	 * @param sendMap
	 * @return
	 */
	private Map<String, Object> initParam(Map<String, Object> sendMap) {
		//获取组装报文必要字段信息，拿不到则抛异常。
		Map<String,Object> initMap = new HashMap<String, Object>();
		log.info("上送报文sendMap【"+sendMap+"】，开始初始化必要参数...");
		String reqNumber = hnmCommService.getReqNumber(); //请求流水号
		initMap.put("reqNumber", reqNumber);
		sendMap.put("tranSerialSeqId", reqNumber); //如果通讯失败，没有返回报文，也需要seq_id 记录业务流水t_trans_serial;参考UplCommService.saveTransSerial
		String txCode = getTxCode(sendMap);

		// 从msap迁移，所有与ESB的通讯均采用虚拟柜员
		String userId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_USERID", "E9999");
		String branchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_BRANCHID", "9999");
		String ruralBranchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_RURALBRANCHID", "0000");

		// 如果上传柜员 则根据上传柜员的信息
		if(sendMap.containsKey("app_head_user_id") && sendMap.containsKey("app_head_branch_id") && sendMap.containsKey("app_head_rural_branch_id")){
			boolean userIdFlag = null != sendMap.get("app_head_user_id")&& !"".equals(sendMap.get("app_head_user_id"));
			boolean branchIdFlag = null != sendMap.get("app_head_branch_id")&& !"".equals(sendMap.get("app_head_branch_id"));
			boolean ruralBranchIdFlag = null != sendMap.get("app_head_rural_branch_id")&& !"".equals(sendMap.get("app_head_rural_branch_id"));
			// 只有当这三个值都存在且都非空的情况下才替换
			if(userIdFlag && branchIdFlag&&ruralBranchIdFlag){
				userId = sendMap.get("app_head_user_id").toString();
				branchId = sendMap.get("app_head_branch_id").toString();
				ruralBranchId = sendMap.get("app_head_rural_branch_id").toString();
			}
		}

		String busSeqNo = "";
		if(ObjectUtil.isNotEmpty(sendMap.get("bus_seq_no"))){
			busSeqNo = String.valueOf(sendMap.get("bus_seq_no"));
		}else{
			busSeqNo = hnmCommService.getFlowNo(userId);
		}

		initMap.put("userId", userId);
		initMap.put("txCode", txCode);
		initMap.put("branchId", branchId);
		initMap.put("ruralBranchId", ruralBranchId);
		initMap.put("bus_seq_no", busSeqNo);

		String tranDate = hnmCommService.getCoreDate(ruralBranchId);
		initMap.put("tranDate", tranDate);

		if(txCode == null || userId == null || branchId == null || ruralBranchId == null){
			log.error("交易码，柜员号，机构号，法人编号，不能为空，初始化参数为：【"+initMap+"】");
			throw new BusinessException("EsbMsgProcService002","交易码或柜员号或机构号或法人编号，不能为空！");
		}
		if(tranDate == null){
			log.error("核心会计日期不能为空，请检查参数表system组或者手动初始化核心会计日期，初始化参数为：【"+initMap+"】");
			throw new BusinessException("EsbMsgProcService003","核心会计日期不能为空，请检查参数表system组或者手动初始化核心会计日期！");
		}

		log.info("初始化参数：【"+initMap+"】，上送报文sendMap【"+sendMap+"】，登记通讯流水，开始组装发送esb报文...");

		return initMap;
	}

	/**
	 * 获取返回信息中登记日志需要的信息
	 * @param retMap：返回报文
	 * @return
	 */
	private Map<String, Object> getLogMap(Map<String, Object> retMap) {
		Map<String,Object> logMap = new HashMap<String, Object>();
		if(!Utils.isNullOrEmpty(retMap))
		{
			logMap.put("esb_seq_no", getFromReceiveMap(retMap, "sys_head", "esb_seq_no"));//esb交易流水号,esb返回报文中系统头中获取；
			logMap.put("serv_seq_no", getFromReceiveMap(retMap, "app_head", "serv_seq_no"));//服务处理返回流水号，esb返回报文中应用头中获取；
			logMap.put("status", getRetStatus(retMap));//交易状态:0,失败；1，成功；2，发送中
			logMap.put("errmsg", getRetCodeMsg(retMap));//错误信息
			return logMap;
		}
		return logMap;
	}

	/**
	 * 登记通讯流水日志
	 * @param sendMap：上送Map；
	 * @param logMap：返回Map中记录日志需要的信息；
	 * @param initMap：初始化参数，柜员号，交易码，通讯流水
	 */
	private void regTransionLog(Map<String, Object> sendMap, Map<String, Object> logMap, Map<String, Object> initMap) {
		try {
			Map<String,Object> p = new HashMap<String,Object>();

			p.put("seq_id", initMap.get("reqNumber"));//本系统通讯流水号
			p.put("busi_seq_no", initMap.get("bus_seq_no"));//业务流水号
			p.put("esb_seq_no", "");//esb交易流水号,esb返回报文中系统头中获取；
			p.put("txcode", initMap.get("txCode"));// 本系统交易码
			p.put("esb_service_code", sendMap.get("service_code"));//esb服务代码
			p.put("esb_service_scene", sendMap.get("service_scene"));//esb服务应用场景号
			p.put("serv_seq_no", "");//服务处理返回流水号，esb返回报文中应用头中获取；
			p.put("user_id", initMap.get("userId"));// 操作柜员号

			if(!sendMap.containsKey("auth_user_id") || sendMap.get("auth_user_id") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(sendMap.get("auth_user_id").toString())){
				String authUserId1 = Utils.getNotNullValueString(sendMap, "auth1_user_id"); //授权柜员1
				String authUserId2 = Utils.getNotNullValueString(sendMap, "auth2_user_id"); //授权柜员2（可能为空）
				authUserId2 = (authUserId2 == null || org.apache.commons.lang3.StringUtils.isBlank(authUserId2))?"":","+authUserId2;
				sendMap.put("auth_user_id", authUserId1+authUserId2);
			}
			p.put("auth_user_id", sendMap.get("auth_user_id"));//授权柜员号

			p.put("branch_id", initMap.get("branchId"));//机构号
			p.put("trandate",initMap.get("tranDate"));//流水日期
			p.put("trantime",DateUtils.getDate("HH:mm:ss"));//流水时间
			if(!Utils.isNullOrEmpty(logMap))
			{
				p.put("esb_seq_no", logMap.get("esb_seq_no"));//esb交易流水号,esb返回报文中系统头中获取；
				p.put("serv_seq_no", logMap.get("serv_seq_no"));//服务处理返回流水号，esb返回报文中应用头中获取；
				p.put("status", logMap.get("status"));//交易状态:0,失败；1，成功；2，发送中
				p.put("errmsg", logMap.get("errmsg"));//错误信息
			}else{
				p.put("status", "2");//交易状态:0,失败；1，成功；2，发送中
				p.put("errmsg", "");//错误信息
			}
			//记录通讯流水日志

			tTranFlowDao.saveOrUpdate(p);
		} catch (Exception e) {
			log.error("登记通讯流水日志异常,原因：",e);
			//由于通讯流水日志的成功与否与交易通讯成功与否无关，所以不将异常抛出
		}
	}
	/**
	 * 获取返回报文中某个头部分中的第一层的key值；
	 * @param receiveMap
	 * @param header ： sys_head、app_head、local_head
	 * @param key
	 * @return
	 */
	private String getFromReceiveMap(Map<String,Object> receiveMap,String header,String key){
		String retStr = "";
		Map<String,Object> headerMap = (Map<String, Object>) receiveMap.get(header);
		if(headerMap != null){
			retStr = headerMap.get(key)==null?"":String.valueOf(headerMap.get(key));
		}
		return retStr;
	}
	/**
	 * @author RZT
	 * 获取返回结果状态，根据ret_status和ret_code来综合判断：ret_status为S且ret_code不是E开头时为1，否则为0。
	 * @param receiveMap
	 * @return 1，成功；0，失败；
	 */
	public String getRetStatus(Map<String,Object> receiveMap){
		String status = "0";
		if(!Utils.isNullOrEmpty(receiveMap)&& receiveMap.containsKey("sys_head"))
		{
			Map<String,Object> sysHead = (Map<String, Object>) receiveMap.get("sys_head");
			Map<String,Object> retMap = (Map<String, Object>) sysHead.get("ret");
			Object sdo = retMap.get("sdo");//ret的内容部分是数组，需要判断；如果返回的是一个数组，则内容为map类型，否则为list类型；
			if(sdo instanceof List){
				List<Map<String,Object>> sdoList = (List<Map<String, Object>>) sdo;
				for(Map<String,Object> m:sdoList){
					String retCode = String.valueOf(m.get("ret_code"));
					if(retCode.startsWith("E")){
						status = "F";
						break;
					}
					status = "S";
				}
			}else if(sdo instanceof Map){
				Map<String,Object> sdoMap = (Map<String, Object>) sdo;
				String retCode = String.valueOf(sdoMap.get("ret_code"));
				if(retCode.startsWith("E")){
					status = "F";
				}else{
					status = "S";
				}
			}
			String retStatus = String.valueOf(sysHead.get("ret_status"));
			if("S".equals(status)&&"S".equals(retStatus)){
				status = "1";
			}else{
				status = "0";
			}
		}
		return status;
	}
	/**
	 * 解析返回报文，提取拼串错误码和错误信息：retCode:retMsg;
	 * @param receiveMap
	 * @return
	 */
	public String getRetCodeMsg(Map<String, Object> receiveMap) {
		String retStr = "";
		if(!Utils.isNullOrEmpty(receiveMap)&&receiveMap.containsKey("sys_head"))
		{
			Map<String,Object> sysHead = (Map<String, Object>) receiveMap.get("sys_head");
			Map<String,Object> retMap = (Map<String, Object>) sysHead.get("ret");
			Object sdo = retMap.get("sdo");//ret的内容部分是数组，需要判断；如果返回的是一个数组，则内容为map类型，否则为list类型；
			if(sdo instanceof List){
				List<Map<String,Object>> sdoList = (List<Map<String, Object>>) sdo;
				for(Map<String,Object> m:sdoList){
					retStr += Utils.getNotNullValueString(m, "ret_code")+":"+ Utils.getNotNullValueString(m, "ret_msg")+";";
				}
			}else if(sdo instanceof Map){
				Map<String,Object> sdoMap = (Map<String, Object>) sdo;
				retStr += Utils.getNotNullValueString(sdoMap, "ret_code")+":"+Utils.getNotNullValueString(sdoMap, "ret_msg")+";";
			}
		}
		return retStr;
	}
	/**
	 * 组装请求报文
	 * @param sendMap 请求参数
	 * @param check 是否验密
	 * @param initMap
	 * @return
	 */
	private Map<String,Object> buildFreeMarkerDataModel(Map<String,Object> sendMap,boolean check, Map<String, Object> initMap){
		//组装传入模板的数据模型
		Map<String,Object> dataMap = new HashMap<String, Object>();
		//SYS_HEADER 系统头
		Map<String,Object> sysHeadMap = new HashMap<String, Object>();
		//SERVICE_CODE 服务代码：esb交易码 由交易定义
		//SERVICE_SCENE 服务应用场景:esb场景号 由交易定义
		String txcode = String.valueOf(initMap.get("txCode"));
		String user_id = String.valueOf(initMap.get("userId"));
		String branch_id = String.valueOf(initMap.get("branchId"));
		String rural_branch_id = String.valueOf(initMap.get("ruralBranchId"));
		String tran_date = String.valueOf(initMap.get("tranDate"));
		String reqNumber = String.valueOf(initMap.get("reqNumber"));

		String consumerId = sysParamService.getSysParam(SYS_PARAM_ESB_CONFIG, SYS_PARAM_ESB_CONSUMER_ID, CONSUMER_ID);
		sysHeadMap.put("service_code", sendMap.get("service_code")); //服务代码：esb交易码 由交易定义
		sysHeadMap.put("consumer_id", consumerId); //请求系统编号：需esb为我们系统设定－从sys_prarm表取出
		sysHeadMap.put("service_scene", sendMap.get("service_scene")); //服务应用场景:esb场景号 由交易定义
		sysHeadMap.put("module_id", getByTxCode(txcode,0,3));//请求系统模块标识：请求系统的内部模块编号，由请求系统自定义，以便管理跟踪 （理财＝fin取交易码前三位）
		sysHeadMap.put("program_id", sendMap.get("prdtype")==null?"":sendMap.get("prdtype"));//理财产品以产品类型填充次字段，否则为空  ||***请求系统应用程序模块：请求系统内部的应用程序编号，由请求系统自定义，以便管理跟踪（理财＝交易码后六位）***
		sysHeadMap.put("consumer_seq_no", reqNumber);// 服务请求发送方流水号：交易流水，由我们自己平台生成。规则：6位系统id＋yyyymmdd＋8位序列号
		sysHeadMap.put("org_sys_id", consumerId);//发起方系统编号：请求方发起每一笔报文时的流水号，每笔报文的唯一标识 需要CONSUMER上送，以便跟踪
		sysHeadMap.put("tran_date", tran_date);// 发送方交易日期：交易发起的日期，格式为YYYYMMDD，无掩码  ;此字段为核心会计日期
//		sysHeadMap.put("tran_date", DateUtils.getDate("yyyyMMdd"));// 发送方交易日期：交易发起的日期，格式为YYYYMMDD，无掩码  ;此字段为核心会计日期
		sysHeadMap.put("tran_timestamp", DateUtils.getDate("HHmmssSSS"));//发送方交易时间：交易发起的时间，格式为HHMMSSNNN，无掩码
		dataMap.put("SYS_HEADER", sysHeadMap);

		//APP_HEADER 应用头
		Map<String,Object> appHeadMap = new HashMap<String, Object>();
		appHeadMap.put("branch_id", branch_id);// 发送方机构ID：服务请求者的机构编号 3200
		appHeadMap.put("user_id", user_id);// 服务请求者身份：柜员号009420
		//后端需要授权的交易新增部分特殊的app_header(如电话银行签约)
		if(sendMap.containsKey("auth_user_id_array") && sendMap.get("auth_user_id_array") != null){
			appHeadMap.put("auth_user_id_array",sendMap.get("auth_user_id_array"));
		}
		// 增加应用的分页信息“per_page_num：每页显示数目” add by chenhao 20160408
		if(sendMap.containsKey("per_page_num") && sendMap.get("per_page_num") != null){
			appHeadMap.put("per_page_num",sendMap.get("per_page_num"));
		}
		// 增加应用的分页信息“query_key：查询结果定位串 第一次查询时为空或不填用于定位每页记录数” add by pzz 20160705
		if(sendMap.containsKey("app_header_query_key") && sendMap.get("app_header_query_key") != null){
			appHeadMap.put("query_key",sendMap.get("app_header_query_key"));
		}
		// 增加应用的分页信息“pgup_or_pgdn：上翻/下翻标志 取值范围： 0-上翻； 1-下翻；” add by pzz 20160705
		if(sendMap.containsKey("app_header_pgup_or_pgdn") && sendMap.get("app_header_pgup_or_pgdn") != null){
			appHeadMap.put("pgup_or_pgdn",sendMap.get("app_header_pgup_or_pgdn"));
		}
		// 增加“BIZ_SEQ_NO”的支持，如果交易上送的Map有送这个key则做处理，不送则不做处理 add by chenhao 20170331 ,modified by guohan 20170719
		if(sendMap.containsKey("app_header_biz_seq_no")){
			appHeadMap.put("biz_seq_no",sendMap.get("app_header_biz_seq_no"));
		}
		dataMap.put("APP_HEADER", appHeadMap);


		//LOCAL_HEADER 本地头
		Map<String,Object> localHeadMap = new HashMap<String, Object>();
		localHeadMap.put("rural_branch_id", rural_branch_id);// 法人编号：法人编号RURAL_BRANCH_ID，该字段区分不同法人。南粤银行业务送0000，南粤中山古镇村镇银行业务为0001
		localHeadMap.put("bus_seq_no", initMap.get("bus_seq_no"));// 业务跟踪号
		localHeadMap.put("channel_code", sysParamService.getSysParam(SYS_PARAM_ESB_CONFIG, SYS_PARAM_ESB_CHANNEL_CODE, CHANNEL_CODE));//渠道号
		localHeadMap.put("check", check);
		if(check){
			localHeadMap.put("key_date", sendMap.get("key_date"));// 密钥日期
			localHeadMap.put("key_name", sendMap.get("key_name"));// 密钥名称
			localHeadMap.put("key_type", sendMap.get("key_type"));// 加密类型
			localHeadMap.put("key_factor", sendMap.get("key_factor"));// 加密因子
		}
		if(sendMap.containsKey("local_head_link_flag")){
			localHeadMap.put("link_flag", sendMap.get("local_head_link_flag"));
		}
		//added by pzz on 20170313 联动交易码 涉及核心部分联动交易需上送主交易码时必输 需上送的交易：100102、100103、100006、100202、100203
		if(sendMap.containsKey("local_head_link_trans_code")){
			localHeadMap.put("link_trans_code", sendMap.get("local_head_link_trans_code"));
		}
		//added by pzz on 20170321 针对个别交易的渠道号 上传不一样的值 也许是 000001 -柜面  000008 非柜面
//		if(sendMap.containsKey("local_head_channel_code")){
//			localHeadMap.put("channel_code", sendMap.get("local_head_channel_code"));
//		}
		dataMap.put("LOCAL_HEADER", localHeadMap);


		/**
		 * 如果上送数据Map中包含对应报文头的数据，则使用上送的数据覆盖系统自动处理获取的值
		 * add by chenhao 20160421 start
		 */
//		if(sendMap.containsKey("SYS_HEADER")&&sendMap.get("SYS_HEADER") instanceof Map){
//			((Map)dataMap.get("SYS_HEADER")).putAll((Map)sendMap.get("SYS_HEADER"));
//		}
//		if(sendMap.containsKey("APP_HEADER")&&sendMap.get("APP_HEADER") instanceof Map){
//			((Map)dataMap.get("APP_HEADER")).putAll((Map)sendMap.get("APP_HEADER"));
//		}
//		if(sendMap.containsKey("LOCAL_HEADER")&&sendMap.get("LOCAL_HEADER") instanceof Map){
//			((Map)dataMap.get("LOCAL_HEADER")).putAll((Map)sendMap.get("LOCAL_HEADER"));
//		}
		/**
		 * 如果上送数据Map中包含对应报文头的数据，则使用上送的数据覆盖系统自动处理获取的值
		 * add by chenhao 20160421 end
		 */


		//组装模板body部分内容
		dataMap.put("BODY", sendMap);

		return dataMap;
	}
	/**
	 * 获取交易码部分内容
	 * @param txCode 交易码
	 * @param startIndex 开始位置
	 * @param endIndex 结束位置
	 * @return
	 */
	private String getByTxCode(String txCode,int startIndex,int endIndex){
		if(StringUtils.isEmpty(txCode)){
			return null;
		}
		if(txCode != null && startIndex<endIndex){
			return txCode.substring(startIndex, endIndex);
		}
		return null;
	}
	/**
	 * 获取交易码后n位。
	 * @param txCode 交易码
	 * @param num 需要获取的位数
	 * @return
	 */
	private static String getByTxCodeFromEnd(String txCode,int num){
		if(StringUtils.isEmpty(txCode)){
			return null;
		}
		if(txCode != null && txCode.length()>=num){
			return txCode.substring(txCode.length()- num, txCode.length());
		}
		return null;
	}

	/**
	 * 获取当前交易码，找不到则返回null：先从上下文查找，再从上送参数中查找
	 * @param sendMap
	 * @return
	 */
	private String getTxCode(Map<String,Object> sendMap){
		if(MfpContextHolder.getTxcode()!=null){
			return MfpContextHolder.getTxcode();
		}
		if(sendMap.get("txcode")!=null){
			return String.valueOf(sendMap.get("txcode"));
		}
		return null;
	}

}
