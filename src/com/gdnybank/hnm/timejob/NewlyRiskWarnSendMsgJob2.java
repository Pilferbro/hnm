package com.gdnybank.hnm.timejob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.SendMsgLogDao;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 当天新增风险信息，发送短信通知（整改人）
 */
@Service
public class NewlyRiskWarnSendMsgJob2 extends BaseTimeJob {
    private Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private HnmCommService hnmCommService;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private SendMsgLogDao sendMsgLogDao;
    @Autowired
    private ApprovalService approvalService;

    private String SMS_CONTENT = "";

    @Override
    //@Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“NewlyRiskWarnSendMsgJob2”开始执行");
            if (checkSetting()) {
                sendMsg();
            }else {
                logger.info("短信提醒未启用");
            }
            logger.info("定时任务“NewlyRiskWarnSendMsgJob2”执行结束");

            return null;
        }
    }

    private boolean checkSetting() {

        boolean flag = false;
        Map<String, Object> settingMap = approvalService.getSetting("00000000004");
        if (settingMap != null && settingMap.size() > 0) {
            //短信内容
            SMS_CONTENT = settingMap.get("sms_content").toString();

            flag = (boolean) settingMap.get("flag");
        }
        return flag;
    }

    private void sendMsg() {
        //先查询需要发送提醒短信的信息
        //获取昨天和当天的日期
        Date ydate = DateUtils.addDay(DateUtils.getNowDate(),-1);
        String start_time = DateUtils.getDate(ydate,"yyyy-MM-dd");
        String end_time = DateUtils.getDate(DateUtils.getNowDate(),"yyyy-MM-dd");
        //查询昨天新增的风险信息
        List<Map<String, Object>> list = hPatrolLogContentDao.queryAndCountForListToJob(BaseUtils.map("start_time",start_time+" 00:00:00","end_time",end_time+" 00:00:00"));
        Set<Map<String, Object>> sendSet = new HashSet<>();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list){
                if(ObjectUtil.isNotEmpty(map.get("responsible"))){
                    String responsible = String.valueOf(map.get("responsible"));
                    //查询员工用户表
                    List<Map<String, Object>> userList = sysAccountDao.queryForList(BaseUtils.map("account_id", responsible));
                    if(userList!=null && userList.size()>0){
                        Map<String, Object> userMap = userList.get(0);
                        userMap.put("count",map.get("count"));
                        sendSet.add(userMap);
                    }
                }
            }
        }else {
            logger.info("昨天新增0条风险信息");
        }

        if(sendSet.size() > 0){
            doSend(sendSet);
        }
    }

    //逻辑处理
    private void doSend(Set<Map<String, Object>> set) {
        for (Map<String, Object> map : set) {
            if (ObjectUtil.isNotEmpty(map.get("phone"))) {
                String smsTplName = "MSG000001.ftl";
                //先保存至短信记录表
                Map<String, Object> saveMap = new HashMap<>();
                saveMap.put("id", IdUtil.randomUUID().replace("-", ""));
                saveMap.put("account_id",map.get("account_id"));
                saveMap.put("account_name",map.get("name"));
                saveMap.put("phone",map.get("phone"));
                saveMap.put("sms_tpl",smsTplName);
                saveMap.put("send_flag","0");
                saveMap.put("create_time", DateUtil.now());
                saveMap.put("update_time",DateUtil.now());
                sendMsgLogDao.save(saveMap);

                //发送短信
                boolean flag = sendVerifyCode(map.get("count").toString(),map.get("phone").toString(), smsTplName);
                if (flag) {//更新记录表
                    sendMsgLogDao.updateByPk(BaseUtils.map("id", saveMap.get("id"), "send_flag", "1"));
                }
            }
        }
    }

    //发送短信逻辑
    private boolean sendVerifyCode(String count, String mobile, String smsTplName) {
        // 发送短信
        boolean flag = false;
        try {
            //组装短信内容
            String sms_content = SMS_CONTENT.replace("*",count);

            Map<String, Object> sendMsgMap = new HashMap<>();
            sendMsgMap.put("msg_mobile", mobile); //手机号
            sendMsgMap.put("msg_flag", "0"); //0-立即；1-定时
            sendMsgMap.put("sms_content", sms_content); //短信内容
            hnmCommService.sendMobileMsg(smsTplName, sendMsgMap);
            flag = true;
            logger.info(mobile + "发送短信通知成功。");
        } catch (Exception e) {
            logger.error(mobile + "发送短信通知失败。" + e.getMessage());
        }
        return flag;
    }
}
