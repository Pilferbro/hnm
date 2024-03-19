package com.gdnybank.hnm.esb.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.TerminalInfoDao;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 助农交易信息查询
 *
 * @author test
 */
@Service
public class Tesb1100200022001Service extends EsbBaseService {

    Logger log = LoggerFactory.getLogger(Tesb1100200022001Service.class);
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private TerminalInfoDao terminalInfoDao;
    @Autowired
    private HSiteDao hSiteDao;


    @Override
    public Map<String, Object> doBusiness(Map<String, Object> sYSHEADER, Map<String, Object> aPPHEADER, Map<String, Object> lOCALHEADER, Map<String, Object> sendMap) {
        if (ObjectUtil.isEmpty(sendMap.get("status_code"))) {
            throw new BusinessException("Tesb1100200022001ServiceException", "交易状态码不能为空");
        }
        String statusCode = String.valueOf(sendMap.get("status_code"));
        Map<String, Object> returnMap;
        switch (statusCode) {
            case "1":
                returnMap = checkRisk2Customer(sYSHEADER, aPPHEADER, lOCALHEADER, sendMap);
                break;
            case "2":
                returnMap = checkTerminalMsg(sYSHEADER, aPPHEADER, lOCALHEADER, sendMap);
                break;
            default:
                returnMap = checkRisk2Customer(sYSHEADER, aPPHEADER, lOCALHEADER, sendMap);
                Map<String, Object> sysHeader = (Map) returnMap.get("SYS_HEADER");
                String retCode = String.valueOf(sysHeader.get("ret_status"));
                //风险信息校验通过后再校验助农交易
                if ("S".equals(retCode)) {
                    returnMap = checkTerminalMsg(sYSHEADER, aPPHEADER, lOCALHEADER, sendMap);
                }
                break;
        }
        return returnMap;
    }

    private Map<String, Object> checkTerminalMsg(Map<String, Object> sYSHEADER, Map<String, Object> aPPHEADER, Map<String, Object> lOCALHEADER, Map<String, Object> sendMap) {
        log.info("助农交易校验start");
        Map<String, Object> returnMap = new HashMap<>();

        if (ObjectUtil.isEmpty(sendMap.get("terminal_sn"))) {
            throw new BusinessException("Tesb1100200022001ServiceException", "终端SN号不能为空");
        }
        if (ObjectUtil.isEmpty(sendMap.get("merchant_num"))) {
            throw new BusinessException("Tesb1100200022001ServiceException", "银联商户编码不能为空");
        }
        if (ObjectUtil.isEmpty(sendMap.get("app_version"))) {
            throw new BusinessException("Tesb1100200022001ServiceException", "应用版本编号不能为空");
        }
        if (ObjectUtil.isEmpty(sendMap.get("stb_id"))) {
            throw new BusinessException("Tesb1100200022001ServiceException", "银联终端编码不能为空");
        }

        if (ObjectUtil.isEmpty(sendMap.get("lng")) || ObjectUtil.isEmpty(sendMap.get("lat"))) {
            throw new BusinessException("Tesb1100200022001ServiceException", "经纬度不能为空");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", "'0','2'");
        paramMap.put("approval_status", "2");
        paramMap.put("is_delete", "0");
        paramMap.put("terminal_sn", sendMap.get("terminal_sn"));
        paramMap.put("merchant_num", sendMap.get("merchant_num"));
        paramMap.put("stb_id", sendMap.get("stb_id"));
        List<Map<String, Object>> siteList = hSiteDao.queryByTerminalInfo(paramMap);
        Map<String, Object> siteMap;
        if (siteList != null && siteList.size() == 1) {

            siteMap = siteList.get(0);
        } else {
            return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "E010404000001", "终端信息有误，请联系系统管理人。", "F");
        }
        //查询初始版本控制信息
        List<Map<String, Object>> maps = terminalInfoDao.queryForList(BaseUtils.map("id", "2023022100000004"));
        String ctrlNo = String.valueOf(maps.get(0).get("ctrl_no"));
        if ("1".equals(ctrlNo)) {
            List<Map<String, Object>> terminalInfos = terminalInfoDao
                    .queryForList(BaseUtils.map("app_version", sendMap.get("app_version"), "use_up", "1", "is_delete", "0"));

            if (terminalInfos == null || terminalInfos.size() == 0) {
                returnMap.put("result", "应用版本不允许交易，请联系系统管理人处理。");
                return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "E010404000002", "应用版本不允许交易，请联系系统管理人处理。", "F");
            }
        }

        String distance = String.valueOf(maps.get(0).get("distance"));
        if (!"null".equals(distance)) {
            if (!doDistance(sendMap, siteMap, distance)) {
                returnMap.put("result", "终端已超出服务范围。");
                return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "E010404000003", "终端已超出服务范围。", "F");
            }
        }

        returnMap.put("result", "校验通过!");
        returnMap.put("pos_sn", siteMap.get("pos_sn"));
        log.info("助农交易校验end");
        return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "I010404000000", "校验通过！", "S");

    }

    private Map<String, Object> checkRisk2Customer(Map<String, Object> sYSHEADER, Map<String, Object> aPPHEADER, Map<String, Object> lOCALHEADER, Map<String, Object> sendMap) {
        log.info("风险客户查询start");
        Map<String, Object> returnMap = new HashMap<>();
        boolean flag1 = false, flag2 = false;
        //判断是否不能使用支付服务终端办理惠农业务
        if (ObjectUtil.isNotEmpty(sendMap.get("card_no1"))) {
            flag1 = approvalService.checkRiskCustomer(BaseUtils.map("card_no", sendMap.get("card_no1"), "id", "2"));
        }

        if (ObjectUtil.isNotEmpty(sendMap.get("card_no2"))) {
            flag2 = approvalService.checkRiskCustomer(BaseUtils.map("card_no", sendMap.get("card_no2"), "id", "2"));
        }


        if (flag1 && flag2) {
            returnMap.put("result", "不能在本渠道办理业务，请到银行网点办理。");
            return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "E010404000001", "不能在本渠道办理业务，请到银行网点办理。", "F");
        }

        if (flag1) {
            returnMap.put("result", sendMap.get("card_no1") + "不能在本渠道办理业务，请到银行网点办理。");
            return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "E010404000002", sendMap.get("card_no1") + "不能在本渠道办理业务，请到银行网点办理。", "F");
        }

        if (flag2) {
            returnMap.put("result", sendMap.get("card_no2") + "不能在本渠道办理业务，请到银行网点办理。");
            return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "E010404000003", sendMap.get("card_no2") + "不能在本渠道办理业务，请到银行网点办理。", "F");
        }
        returnMap.put("result", "校验通过!");
        log.info("风险客户查询end");
        return assembleBackData(sYSHEADER, aPPHEADER, lOCALHEADER, returnMap, "I010404000000", "校验通过！", "S");
    }

    public boolean doDistance(Map<String, Object> sendMap, Map<String, Object> siteMap, String distance) {
        try {
            // 根据上送的经纬度以及规则的经纬度和半径进行计算，判断是否在半径内
            // 经度
            double siteLongitude = Double.parseDouble(siteMap.get("lng").toString().trim());
            // 纬度
            double siteLatitude = Double.parseDouble(siteMap.get("lat").toString().trim());
            log.info("站点经度："+siteLongitude+",站点纬度："+siteLatitude);
            // 半径
            double radius = Double.parseDouble(distance);
            boolean flag = false;
            // 上送的经度
            double uLongitude = Double.parseDouble(sendMap.get("lng").toString().trim());
            // 上送的纬度
            double uLatitude = Double.parseDouble(sendMap.get("lat").toString().trim());
            double rRadius = MapUtils.getDistance(siteLongitude, siteLatitude, uLongitude, uLatitude) / 1000;
            log.debug("两个坐标点的距离:" + rRadius + "公里");

            if (rRadius <= radius) {
                flag = true;
            }

            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("设备位置匹配参数解析出错");
            return false;
        }

    }

}
