package com.gdnybank.hnm.pub.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 权限规则检验
 */
@Service
@Transactional
public class ApprovalService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TApprovalDetailsDao tApprovalDetailsDao;

    /**
     * 检查是否存在免审核项，存在则返回true
     *
     * @param p
     * @return boolean
     */
    public boolean checkApproval(Map<String, Object> p, String approval_type) {

        if (ObjectUtil.isEmpty(approval_type)) {
            throw new BusinessException("ApprovalServiceException", "approval_type不能为空");
        }

        for (String key : p.keySet()) {

            List<Map<String, Object>> approvalDetails = tApprovalDetailsDao.queryForList(
                    BaseUtils.map("approval_type", approval_type,
                            "approval_field", key, "approval_details", isRepetition(p.get(key))));

            if (approvalDetails != null && approvalDetails.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private Object isRepetition(Object obj) {
        if (null == obj) {
            return null;
        } else if (obj instanceof Map) {
            return null;
        } else if (obj instanceof Iterable) {
            return null;
        } else if (obj instanceof Iterator) {
            return null;
        } else {
            return ArrayUtil.isArray(obj) ? null : obj;
        }
    }


    @Autowired
    private SmsSettingDao smsSettingDao;

    /**
     * 获取短信参数
     *
     * @param id
     * @return Map
     */
    public Map<String, Object> getSetting(String id) {

        boolean flag = false;

        //查询短信参数
        List<Map<String, Object>> list = smsSettingDao.queryForList(BaseUtils.map("id", id));
        Map<String, Object> settingMap = null;
        if (list != null && list.size() > 0) {
            settingMap = list.get(0);
        }

        if (settingMap != null && settingMap.size() > 0) {
            String use_up = String.valueOf(settingMap.get("use_up"));

            //是否启用
            if (use_up.equals("1")) {
                flag = true;
            }
            settingMap.put("flag", flag);
        }
        return settingMap;
    }

    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private TMeasureDao tMeasureDao;

    /**
     * 检查风险客户是否被管控
     *
     * @param p
     * @return Map
     */
    public boolean checkRiskCustomer(Map<String, Object> p) {

        boolean flag = false;
        String id = String.valueOf(p.get("id"));
        if (!p.containsKey("acct_id")) {
            p.put("acct_id", "");
        }
        if (!p.containsKey("cert_num")) {
            p.put("cert_num", "");
        } else {
            String cert_num = String.valueOf(p.get("cert_num"));
            p.put("cert_num", cert_num.toUpperCase());
        }
        if (!p.containsKey("card_no")) {
            p.put("card_no", "");
        }
        p.put("endDate", DateUtils.getDate(DateUtils.getNowDate(), "yyyy-MM-dd"));

        //查询风险客户信息
        List<Map<String, Object>> list = blklistCtrlDao.queryOfConjunctive(p);

        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Map<String, Object> param = new HashMap<>();
                param.put("id", id);
                String riskLevel = tranStatus(String.valueOf(map.get("risk_level")));
                //状态为0-被管控
                param.put(riskLevel, "0");

                List<Map<String, Object>> maps = tMeasureDao.queryForList(param);
                if (maps != null && maps.size() > 0) {

                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private String tranStatus(String risk_level) {
        String str = "";
        switch (risk_level) {
            case "0":
                str = "h_risk_level";
                break;
            case "1":
                str = "m_risk_level";
                break;
            case "2":
                str = "l_risk_level";
                break;
        }
        return str;
    }

    /**
     * 查询终端设备信息
     */
    @Autowired
    private HSiteDao siteDao;

    public String checkTerminalInfo(Map<String, Object> p) {

        StringBuffer sbr = new StringBuffer();
        String siteNo = String.valueOf(p.get("site_no"));

        if (ObjectUtil.isNotEmpty(p.get("terminal_no"))) {
            sbr.append(getTerminalInfo(siteNo, "terminal_no", p.get("terminal_no").toString()));
        }
        if (ObjectUtil.isNotEmpty(p.get("stb_id"))) {
            sbr.append(getTerminalInfo(siteNo, "stb_id", p.get("stb_id").toString()));
        }
        if (ObjectUtil.isNotEmpty(p.get("merchant_num"))) {
            sbr.append(getTerminalInfo(siteNo, "merchant_num", p.get("merchant_num").toString()));
        }
        if (ObjectUtil.isNotEmpty(p.get("terminal_sn"))) {
            sbr.append(getTerminalInfo(siteNo, "terminal_sn", p.get("terminal_sn").toString()));
        }

        return sbr.length() > 0 ? sbr.replace(sbr.length() - 1, sbr.length(), "。").toString() : sbr.append("pass").toString();
    }

    private static final Map<String, Object> PARAMS = BaseUtils.map("terminal_no", "银行终端编号", "stb_id", "银联终端编号", "merchant_num",
            "银联商户编码", "terminal_sn", "终端SN号");

    private String getTerminalInfo(String siteNo, String key, String value) {

        String str = "";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", "'0','1','2'");
        paramMap.put("approval_status", "'1','2'");
        paramMap.put("is_delete", "0");
        paramMap.put(key, value);

        List<Map<String, Object>> siteMaps = siteDao.queryByTerminalInfo(paramMap);

        if (siteMaps != null && siteMaps.size() > 0) {
            for (Map<String, Object> site : siteMaps) {
                //非本站的的终端设备号 中断循环
                if (!site.get("site_no").equals(siteNo)) {
                    str = PARAMS.get(key) + value + "已被" + site.get("site_name") + "使用、";
                    break;
                }
            }
        }

        return str;
    }

}
