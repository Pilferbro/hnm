package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.service.EsbMsgProcService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:  校验工号
 * @author: zjh
 */
@Service
public class Tstaff005Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tstaff005Service.class);

    @Autowired
    HManagerDao managerDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    private EsbMsgProcService esbMsgProcService;
    @Autowired
    private SysParamService sysParamService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String,Object> receiveMap = new HashMap<String, Object>();
        try{
            if(ObjectUtil.isEmpty(p.get("user_id"))){
                throw new BusinessException("Tstaff005ServiceExceptin","工号为必传字段");
            }
            String userId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_USERID", "E9999");
            p.put("bus_seq_no", hnmCommService.getFlowNo(userId));
            p.put("service_code", "11003000123");//esb服务代码
            p.put("service_scene", "01");//esb服务应用场景号，校验工号
            receiveMap = esbMsgProcService.sendAndReceiveMsg(p, false);
        }catch(Exception e){
            logger.error("校验工号失败，原因:",e);
            throw new BusinessException("Tstaff005ServiceExceptin", "校验工号失败");
        }
        if("0".equals(esbMsgProcService.getRetStatus(receiveMap))){ //1，成功；0，失败；
            String retCodeAndMsg = esbMsgProcService.getRetCodeMsg(receiveMap);
            throw new BusinessException("Tstaff005ServiceExceptin", retCodeAndMsg);
        }
        HashMap body = (HashMap) receiveMap.get("body");
        if (ObjectUtil.isEmpty(body) || ObjectUtil.isEmpty(body.get("fullname"))){
            throw new BusinessException("Tstaff005ServiceExceptin", "此工号未登记，请重新输入");
        }
        logger.info("Tstaff005Service执行完成");
        return BaseUtils.map("name",body.get("fullname"));
    }
}
