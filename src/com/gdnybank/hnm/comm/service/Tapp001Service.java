package com.gdnybank.hnm.comm.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.enums.ErrorCodeEnum;
import com.gdnybank.hnm.pub.service.MfpJsonMsgProcService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @description:app端登陆
 * @author: wxm
 */
@Service
public class Tapp001Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tapp001Service.class);
    @Autowired
    private MfpJsonMsgProcService mfpJsonMsgProcService;
    @Autowired
    private SysParamService sysParamService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String, Object> receiveMap = new HashMap<>();
        String xt_state = sysParamService.getSysParam("oauth", "state", "123");
        String client_id = sysParamService.getSysParam("oauth", "client_id", "hnm");
        String client_secret = sysParamService.getSysParam("oauth", "client_secret", "0ec6389ae7b547eb87192c3544ee1b10");
        //校验state
        if(ObjectUtil.isEmpty(p.get("state")) || !xt_state.equals(p.get("state").toString())){
            logger.error("state未通过校验");
            throw new BusinessException("TappLogin001ServiceException", "state未通过校验");
        }
        String code = String.valueOf(p.get("code"));
        //请求获取Token
        //拼装url
        String backCode = "/getToken";
        String qusCode = "/getToken?client_id="+client_id+"&grant_type=authorization_code&code="+code+"&client_secret="+client_secret;
        try {
            receiveMap =  mfpJsonMsgProcService.sendAndReceiveMsg(BaseUtils.map(),backCode,qusCode);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                logger.error("通讯异常");
                throw (BusinessException) e;
            } else { //通讯异常
                logger.error("通讯异常");
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService023);
            }
        }
        if(ObjectUtil.isNotEmpty(receiveMap) && ObjectUtil.isNotEmpty(receiveMap.get("access_token"))){
            backCode = "/getUserInfo";
            qusCode = "/getUserInfo?access_token="+receiveMap.get("access_token")+"&client_id="+client_id;
            try {
                Map<String,Object> map =  mfpJsonMsgProcService.sendAndReceiveMsg(BaseUtils.map(),backCode,qusCode);
                if(ObjectUtil.isNotEmpty(map) && ObjectUtil.isNotEmpty(map.get("loginName"))){
                    receiveMap.putAll(map);
                }
            } catch (Exception e) {
                if (e instanceof BusinessException) {
                    logger.error("通讯异常");
                    throw (BusinessException) e;
                } else { //通讯异常
                    logger.error("通讯异常");
                    throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService023);
                }
            }
        }

        return receiveMap;
    }
}
