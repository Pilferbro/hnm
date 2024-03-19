package com.gdnybank.hnm.timejob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.SendMsgLogDao;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 当天新增风险信息，发送短信通知（机构负责人）
 */
@Service
public class NewlyRiskWarnSendMsgJob1 extends BaseTimeJob {
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
    @Autowired
    private SysRoleDao sysRoleDao;


    private String SMS_CONTENT = "";
    private String SEND_ROLE = "";

    @Override
    //@Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“NewlyRiskWarnSendMsgJob1”开始执行");
            if (checkSetting()) {
                sendMsg();
            } else {
                logger.info("短信提醒未启用");
            }
            logger.info("定时任务“NewlyRiskWarnSendMsgJob1”执行结束");

            return null;
        }
    }

    private boolean checkSetting() {

        boolean flag = false;
        Map<String, Object> settingMap = approvalService.getSetting("00000000004");
        if (settingMap != null && settingMap.size() > 0) {
            //短信内容
            SMS_CONTENT = settingMap.get("sms_content").toString();
            //短信接收对象
            SEND_ROLE = settingMap.get("send_role").toString();

            flag = (boolean) settingMap.get("flag");
        }
        return flag;
    }

    private void sendMsg() {
        int userRoleLevel = 0;
        //获取短信接收人级别
        List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", SEND_ROLE));

        if (mapList != null && mapList.size() > 0) {
            userRoleLevel = Integer.parseInt(mapList.get(0).get("role_level").toString());
        }
        if (userRoleLevel == 0) {
            logger.error("角色" + SEND_ROLE + "不存在");
            throw new BusinessException("NewlyRiskWarnSendMsgJob1Exception","角色" + SEND_ROLE + "不存在");
        }

        //查询同角色人员信息
        List<Map<String, Object>> userList = sysAccountDao.queryPersonal(BaseUtils.map("role_id", SEND_ROLE));

        //短信接收人不是总行人员则查询则查询其所在的机构id和下属机构id
        if (userRoleLevel != 1) {
            //查询员工用户表及其所在的机构
            if (userList != null && userList.size() > 0) {
                for (Map<String, Object> map : userList) {
                    String branchIds = hnmCommService.getUserBranchidsBybranch(String.valueOf(map.get("branch_id")));
                    map.put("branchIds", branchIds);
                }
            }
        }
        //先查询需要发送提醒短信的信息
        //获取昨天和当天的日期
        Date ydate = DateUtils.addDay(DateUtils.getNowDate(), -1);
        String start_time = DateUtils.getDate(ydate, "yyyy-MM-dd");
        String end_time = DateUtils.getDate(DateUtils.getNowDate(), "yyyy-MM-dd");
        Set<Map<String, Object>> sendSet = new HashSet<>();
        if (userList != null && userList.size() > 0) {
            for (Map<String, Object> map : userList) {

                map.put("start_time", start_time + " 00:00:00");
                map.put("end_time", end_time + " 00:00:00");
                List<Map<String, Object>> list = hPatrolLogContentDao.countForList(map);

                Map<String, Object> map1 = list.get(0);
                String count = map1.get("count").toString();
                //机构有新增风险信息则加入发送
                if (!"0".equals(count)) {
                    map.put("count", map1.get("count"));
                    map.put("high", map1.get("high"));
                    map.put("middle", map1.get("middle"));
                    map.put("low", map1.get("low"));
                    sendSet.add(map);
                }

            }
        }

        if (sendSet.size() > 0) {
            doSend(sendSet);
        }else {
            logger.info("昨天没有新增风险信息");
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
                boolean flag = sendVerifyCode(map, smsTplName);
                if (flag) {//更新记录表
                    sendMsgLogDao.updateByPk(BaseUtils.map("id", saveMap.get("id"), "send_flag", "1"));
                }
            }
        }
    }

    //发送短信逻辑
    private boolean sendVerifyCode(Map<String, Object> p, String smsTplName) {
        // 发送短信
        boolean flag = false;
        try {
            BigDecimal count = (BigDecimal) p.get("count");
            BigDecimal low = (BigDecimal) p.get("low");

            //组装短信内容
            String sms_content = SMS_CONTENT.replace("【**】", count.toString()).replace("【*】", count.subtract(low).toString());

            Map<String, Object> sendMsgMap = new HashMap<>();
            sendMsgMap.put("msg_mobile", p.get("phone")); //手机号
            sendMsgMap.put("msg_flag", "0"); //0-立即；1-定时
            sendMsgMap.put("sms_content", sms_content); //短信内容
            hnmCommService.sendMobileMsg(smsTplName, sendMsgMap);
            flag = true;
            logger.info(p.get("mobile") + "发送短信通知成功。");
        } catch (Exception e) {
            logger.error(p.get("mobile") + "发送短信通知失败。" + e.getMessage());
        }
        return flag;
    }
}
