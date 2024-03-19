package com.nantian.mfp.pub.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nantian.mfp.dummy.service.DummyService;
import com.nantian.mfp.framework.communication.CommunicationFactory;
import com.nantian.mfp.framework.communication.ICommunication;
import com.nantian.mfp.framework.communication.WebServiceCommunication;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.msghandle.DisAssembleFactory;
import com.nantian.mfp.framework.msghandle.IMessageDisAssemble;
import com.nantian.mfp.framework.msghandle.MessageAssemble;
import com.nantian.mfp.pub.dao.model.FwBacktrans;

@Service
public class MsgProcService {
	static Log log = LogFactory.getLog(MsgProcService.class);
	@Autowired
	MessageAssemble messageAssemble;
	@Autowired
	DummyService dummyService;
	@Autowired
	FwBacktransService fwBacktransService;
	@Autowired
	CommunicationFactory communicationFactory;
	@Autowired
	DisAssembleFactory disAssembleFactory;
	@Autowired
	private ObjectMapper objectMapper;

	public Map<String, Object> sendAndReceiveMsg(Map<String, Object> sendMsg,
			String backTxCode) {
		Map<String, Object> rsMsg = null;
		try {
			FwBacktrans backTxPro = this.fwBacktransService
					.getTxPropertyByTxCode(backTxCode);

			ICommunication commuication = this.communicationFactory
					.getCommBeanById(backTxPro.getBack_svr_key());

			StringBuffer msg = new StringBuffer();

			String bodyTmp = "SEND_" + backTxCode + ".ftl";

			msg.append(this.messageAssemble.assemble(sendMsg, bodyTmp));
			log.warn("sendmsg:" + msg.toString());

			String returnMsg = null;
			if ("true".equals(MfpContextHolder.getProps("dummy." + backTxCode))) {
				backTxPro.setMsg_type("XML");
				returnMsg = this.dummyService.doDummy(msg.toString(),
						backTxCode,backTxPro);
			} else {
				returnMsg = commuication.communication(msg.toString());
			}
			log.warn("receivemsg:" + returnMsg);

			IMessageDisAssemble disAssemble = this.disAssembleFactory
					.getDisAssembleBeanById(backTxPro.getDisassemble_entity());

			rsMsg = disAssemble.disAssemble(returnMsg, backTxPro);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("通信过程类执行异常:" + e.getMessage());
			throw new BusinessException("MsgProcService122", "通信过程类执行异常"
					+ e.getMessage());
		}
		return rsMsg;
	}

	public Map<String, Object> sendAndReceiveMsgWithoutAssemble(
			Map<String, Object> sendMsg, String backTxCode) {
		Map<String, Object> rsMsg = null;
		try {
			FwBacktrans backTxPro = this.fwBacktransService
					.getTxPropertyByTxCode(backTxCode);

			ICommunication commuication = this.communicationFactory
					.getCommBeanById(backTxPro.getBack_svr_key());

			WebServiceCommunication webServiceCommunication = (WebServiceCommunication) commuication;

			StringBuffer msg = new StringBuffer();

			String bodyTmp = "SEND_" + backTxCode + ".ftl";

			msg.append(this.messageAssemble.assemble(sendMsg, bodyTmp));
			log.warn("sendmsg:" + msg.toString());

			String returnMsg = null;
			if ("true".equals(MfpContextHolder.getProps("dummy." + backTxCode))) {
				backTxPro.setMsg_type("XML");
				returnMsg = this.dummyService.doDummy(msg.toString(),
						backTxCode,backTxPro);
			} else {
				returnMsg = webServiceCommunication.communication(sendMsg);
			}
			log.warn("receivemsg:" + returnMsg);

			IMessageDisAssemble disAssemble = this.disAssembleFactory
					.getDisAssembleBeanById(backTxPro.getDisassemble_entity());

			rsMsg = disAssemble.disAssemble(returnMsg, backTxPro);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("通信过程类执行异常:" + e.getMessage());
			throw new BusinessException("MsgProcService122", "通信过程类执行异常"
					+ e.getMessage());
		}
		return rsMsg;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> sendAndReceiveMsgJson(Map<String, Object> sendMsg, String backTxCode) {
		Map<String, Object> rsMsg = null;
		try {
			FwBacktrans backTxPro = this.fwBacktransService.getTxPropertyByTxCode(backTxCode);

			ICommunication commuication = this.communicationFactory.getCommBeanById(backTxPro.getBack_svr_key());

			StringBuffer msg = new StringBuffer();

			msg.append(this.objectMapper.writeValueAsString(sendMsg));
			log.warn("sendmsg:" + msg.toString());

			String returnMsg = null;

			if ("true".equals(MfpContextHolder.getProps("dummy." + backTxCode))) {
				backTxPro.setMsg_type("XML");
				returnMsg = this.dummyService.doDummy(msg.toString(),backTxCode,backTxPro);
			} else{
				returnMsg = commuication.communication(msg.toString());
			}
			log.warn("receivemsg:" + returnMsg);
			rsMsg = (Map<String, Object>) this.objectMapper.readValue(returnMsg, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("通信过程类执行异常:" + e.getMessage());
			throw new BusinessException("MsgProcService122", "通信过程类执行异常"+ e.getMessage());
		}
		return rsMsg;
	}
}

