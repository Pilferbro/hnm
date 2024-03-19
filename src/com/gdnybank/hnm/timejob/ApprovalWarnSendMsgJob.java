package com.gdnybank.hnm.timejob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.StyledEditorKit;
import java.util.*;

/**
 * 我的沟通、我的审批有未处理的信息，第二日发出一条通知给对应处理人
 */
@Service
public class ApprovalWarnSendMsgJob extends BaseTimeJob {
    private Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private HnmCommService hnmCommService;
    @Autowired
    private TApprovalDao tApprovalDao;
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
            logger.info("定时任务“ApprovalWarnSendMsgJob”开始执行");

            if (checkSetting()) {
                sendMsg();
            } else {
                logger.info("短信提醒未启用");
            }
            logger.info("定时任务“ApprovalWarnSendMsgJob”执行结束");

            return null;
        }
    }

    private boolean checkSetting() {

        boolean flag = false;
        Map<String, Object> settingMap = approvalService.getSetting("00000000002");
        if (settingMap != null && settingMap.size() > 0) {
            //短信内容
            SMS_CONTENT = settingMap.get("sms_content").toString();

            flag = (boolean) settingMap.get("flag");
        }
        return flag;
    }

    private void sendMsg() {
        //先查询需要发送提醒短信的信息
        //获取昨天的日期
        Date ydate = DateUtils.addDay(DateUtils.getNowDate(), -1);
        String date = DateUtils.getDate(ydate, "yyyy-MM-dd");
        List<Map<String, Object>> list = tApprovalDao.queryForListAndApply(BaseUtils.map("date", date + "%", "approval_status", "1"));
        Set<Map<String, Object>> sendSet = new HashSet<>();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                //先判断沟通人员
                if (ObjectUtil.isNotEmpty(map.get("gt_approval_user"))) {
                    String gt_approval_users = String.valueOf(map.get("gt_approval_user"));
                    String[] gt_approval_user_arr = gt_approval_users.split(",");
                    for (String gt_approval_user : gt_approval_user_arr) {
                        //查询员工用户表
                        List<Map<String, Object>> userList = sysAccountDao.queryForList(BaseUtils.map("account_id",
                                gt_approval_user));
                        if(userList!=null && userList.size()>0){
                            Map<String, Object> userMap = userList.get(0);
                            sendSet.add(userMap);
                        }
                    }
                } else {
                    if (ObjectUtil.isNotEmpty(map.get("approval_user"))) {
                        String approval_user = String.valueOf(map.get("approval_user"));
                        //查询员工用户表
                        List<Map<String, Object>> userList = sysAccountDao.queryForList(BaseUtils.map("account_id", approval_user));
                        if (userList != null && userList.size() > 0) {
                            Map<String, Object> userMap = userList.get(0);
                            sendSet.add(userMap);
                        }
                    }
                }
            }
        }

        if (sendSet != null && sendSet.size() > 0) {
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
                saveMap.put("account_id", map.get("account_id"));
                saveMap.put("account_name", map.get("name"));
                saveMap.put("phone", map.get("phone"));
                saveMap.put("sms_tpl", smsTplName);
                saveMap.put("send_flag", "0");
                saveMap.put("create_time", DateUtil.now());
                saveMap.put("update_time", DateUtil.now());
                sendMsgLogDao.save(saveMap);
                //发送短信
                boolean flag = sendVerifyCode(map.get("phone").toString(), smsTplName);
                if (flag) {//更新记录表
                    sendMsgLogDao.updateByPk(BaseUtils.map("id", saveMap.get("id"), "send_flag", "1"));
                }
            }
        }
    }

    //发送短信逻辑
    private boolean sendVerifyCode(String mobile, String smsTplName) {
        // 发送短信
        boolean flag = false;
        try {
            Map<String, Object> sendMsgMap = new HashMap<String, Object>();
            sendMsgMap.put("msg_mobile", mobile); //手机号
            sendMsgMap.put("msg_flag", "0"); //0-立即；1-定时
            sendMsgMap.put("sms_content", SMS_CONTENT); //短信内容
            hnmCommService.sendMobileMsg(smsTplName, sendMsgMap);
            flag = true;
            logger.info(mobile + "发送短信通知成功。");
        } catch (Exception e) {
            logger.error(mobile + "发送短信通知失败。" + e.getMessage());
        }
        return flag;
    }
}
