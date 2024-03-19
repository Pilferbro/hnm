package com.gdnybank.hnm.timejob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.utils.DateUtils;
import com.nantian.mfp.framework.utils.BaseUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站定状态调整短信提醒
 */
@Service
public class WarnTimeSendMegJob extends BaseTimeJob {
    private Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private HnmCommService hnmCommService;
    @Autowired
    HSiteDao hSiteDao;
    @Autowired
    HSiteWarnRecordDao hSiteWarnRecordDao;
    @Autowired
    HSiteWarnTimeDao hSiteWarnTimeDao;
    @Autowired
    SysBranchDao sysBranchDao;
    @Autowired
    SysAccountDao sysAccountDao;
    @Autowired
    private ApprovalService approvalService;

    private String SMS_CONTENT = "";
    @Override
    //@Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“WarnTimeSendMegJob”开始执行");
            if (checkSetting()) {
                sendMsg();
            }else {
                logger.info("短信提醒未启用");
            }
            logger.info("定时任务“WarnTimeSendMegJob”执行结束");

            return null;
        }
    }

    private boolean checkSetting() {

        boolean flag = false;
        Map<String, Object> settingMap = approvalService.getSetting("00000000001");
        if (settingMap != null && settingMap.size() > 0) {
            //短信内容
            SMS_CONTENT = settingMap.get("sms_content").toString();

            flag = (boolean) settingMap.get("flag");
        }
        return flag;
    }

    private void sendMsg() {
        //先查询需要发送提醒短信的站点
        List<Map<String, Object>> siteList = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                "approval_status",
                "2", "statuss", "'0','1'"));
        if (siteList != null && siteList.size() > 0) {
            List<Map<String, Object>> list = new ArrayList<>();
            String create_time = DateUtil.now();
            List<Map<String, Object>> siteWarnList = hSiteWarnTimeDao.queryForList(BaseUtils.map());
            for (Map map : siteList) {
                //先查询站点机构
                boolean flag = false;
                Map<String, Object> timeMap = new HashMap<>();
                //查询管辖客户经理的部门
                List<Map<String, Object>> mapList = sysAccountDao.queryForList(BaseUtils.map("account_id", map.get(
                        "mgr_id")));
                String branch_id = "";
                if (mapList != null && mapList.size() > 0) {
                    branch_id = String.valueOf(mapList.get(0).get("branch_id"));
                }
                if (siteWarnList != null && siteWarnList.size() > 0 && !StringUtils.isEmpty(branch_id)) {
                    for (int i = 0; i < siteWarnList.size(); i++) {
                        Map<String, Object> warnMap = siteWarnList.get(i);
                        if (ObjectUtil.isNotEmpty(warnMap.get("branch_id"))) {
                            //分行及分行下所有机构
                            List<String> branchids = hnmCommService.queryOrgid(BaseUtils.map("branch_id", warnMap.get("branch_id")));
                            branchids.add(warnMap.get("branch_id").toString());
                            if (branchids.contains(branch_id)) {
                                flag = true;
                                timeMap = warnMap;
                                break;
                            }
                        }
                    }
                }

                if (flag) {
                    //查询提醒记录表
                    List<Map<String, Object>> listMaxRecord = hSiteWarnRecordDao.queryForListByMax(BaseUtils.map("site_no",
                            map.get("site_no")));
                    if (listMaxRecord == null || listMaxRecord.size() == 0) {
                        //首次
                        String date = timeMap.get("warn_time").toString();
                        String approval_time = map.get("approval_time").toString();
                        String overDate = DateUtils.daysBetween(approval_time, DateUtil.now());
                        if (Long.parseLong(overDate) >= Long.parseLong(date)) {
                            Map<String, Object> warnMap = new HashMap<>();
                            warnMap.put("id", IdUtil.randomUUID().replace("-", ""));
                            warnMap.put("site_id", map.get("id"));
                            warnMap.put("site_no", map.get("site_no"));
                            warnMap.put("site_name", map.get("site_name"));
                            warnMap.put("site_status", map.get("status"));
                            warnMap.put("site_step", "试营业-开业");
                            warnMap.put("branch_id", map.get("branch_id"));
                            warnMap.put("mgr_id", map.get("mgr_id"));
                            warnMap.put("phone", map.get("phone"));
                            warnMap.put("send_msg", "0");
                            warnMap.put("push_msg", "0");
                            warnMap.put("create_time", create_time);
                            list.add(warnMap);
                        }
                    } else {
                        Map<String, Object> maxRecord = listMaxRecord.get(0);
                        String acdate = timeMap.get("warn_ac_time").toString();
                        String maxtime = maxRecord.get("create_time").toString();
                        String overDate = DateUtils.daysBetween(maxtime, DateUtil.now());
                        if (Long.parseLong(overDate) >= Long.parseLong(acdate)) {
                            Map<String, Object> warnMap = new HashMap<>();
                            warnMap.put("id", IdUtil.randomUUID().replace("-", ""));
                            warnMap.put("site_id", map.get("id"));
                            warnMap.put("site_no", map.get("site_no"));
                            warnMap.put("site_name", map.get("site_name"));
                            warnMap.put("site_status", map.get("status"));
                            warnMap.put("site_step", "试营业-开业");
                            warnMap.put("branch_id", map.get("branch_id"));
                            warnMap.put("mgr_id", map.get("mgr_id"));
                            warnMap.put("phone", map.get("phone"));
                            warnMap.put("send_msg", "0");
                            warnMap.put("push_msg", "0");
                            warnMap.put("create_time", create_time);
                            list.add(warnMap);
                        }
                    }
                }

            }
            if (list != null && list.size() > 0) {
                doSend(list);
            }
        }
    }

    //逻辑处理
    private void doSend(List<Map<String, Object>> list) {
        //先保存提醒记录表
        hSiteWarnRecordDao.saveList(list);
        for (Map<String, Object> map : list) {
            String smsTplName = "MSG000001.ftl";
            if (ObjectUtil.isNotEmpty(map.get("phone"))) {
                //发送短信
                boolean flag = sendVerifyCode(map.get("site_name").toString(), map.get("phone").toString(), smsTplName);
                //推送消息
                //boolean flagpush = pushMsg(map.get("site_id").toString(),map.get("site_name").toString(),map.get("mgr_id").toString());
                if (flag) {//更新提醒记录表
                    hSiteWarnRecordDao.updateByPk(BaseUtils.map("id", map.get("id"), "send_msg", "1"));
                }
                /*if (flagpush) {//更新提醒记录表
                    hSiteWarnRecordDao.updateByPk(BaseUtils.map("id", map.get("id"), "push_msg", "1"));
                }*/
            }
        }
    }

    //发送短信逻辑
    private boolean sendVerifyCode(String site_name, String mobile, String smsTplName) {
        // 发送短信
        boolean flag = false;

        String sms_content = SMS_CONTENT.replace("【服务点】", site_name);
        try {
            Map<String, Object> sendMsgMap = new HashMap<String, Object>();
            sendMsgMap.put("sms_content", sms_content); //短信内容
            sendMsgMap.put("msg_mobile", mobile); //手机号
            sendMsgMap.put("msg_flag", "0"); //0-立即；1-定时
            hnmCommService.sendMobileMsg(smsTplName, sendMsgMap);
            flag = true;
            logger.info(mobile + "发送短信通知成功。");
        } catch (Exception e) {
            logger.error(mobile + "发送短信通知失败。" + e.getMessage());
        }
        return flag;
    }

    //推送消息逻辑
    private boolean pushMsg(String site_id, String site_name, String mgr_id) {

        boolean flag = false;
        try {
            Map<String, Object> pushMsgMap = new HashMap<String, Object>();
            pushMsgMap.put("site_id", site_id); //站点编号
            pushMsgMap.put("site_name", site_name); //站点名称
            pushMsgMap.put("mgr_id", mgr_id); //客户经理编号
            hnmCommService.pushMsg(pushMsgMap);
            flag = true;
            logger.info(mgr_id + "推送消息通知成功。");
        } catch (Exception e) {
            logger.error(mgr_id + "推送消息market002通知失败。" + e.getMessage());
        }
        return flag;
    }
}
