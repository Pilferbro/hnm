package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.CupstsDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询助农取款交易列表
 *
 * @author: wxm
 */
@Service
public class Tvouch003Service extends TXBaseService {
    private static final Log logger = LogFactory.getLog(Tvouch003Service.class);

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private CupstsDao cupstsDao;

    private static final Map<String, Object> PRID_MAP = BaseUtils.map("56", "小额取款", "57", "现金汇款", "58",
            "转账汇款", "60", "活转定", "65", "定转活");

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        List<Map<String, Object>> list ;
        Map<String, Object> params = new HashMap<String, Object>();

        if (p.containsKey("tsdt") && ObjectUtil.isNotEmpty(p.get("tsdt"))) {
            params.put("tsdt", p.get("tsdt"));
        }
        if (p.containsKey("site_no") && ObjectUtil.isNotEmpty(p.get("site_no"))) {
            params.put("site_no", p.get("site_no"));
        }

        params.put("is_delete", "0");
        params.put("approval_status", "2");
        params.put("stat", "1");
        //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
        params.put("prids", "'56','57','58','60','65'");

        list = cupstsDao.queryForListBycondition1(delkong(params));

        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                BigDecimal f004 = BigDecimal.valueOf(Double.parseDouble(map.get("f004").toString()));
                BigDecimal amt = hnmCommService.dealVal(f004.divide(BigDecimal.valueOf(100)));
                map.put("f004", amt);
                String prid = String.valueOf(map.get("prid"));
                if ("57".equals(prid)) {
                    //现金汇款  收款卡号
                    map.put("f002", map.get("f062"));
                }
                //f002加密处理
                if (ObjectUtil.isNotEmpty(map.get("f002"))) {
                    String f002 = String.valueOf(map.get("f002"));
                    map.put("f002", mosaicAlipayName(f002));
                }

                map.put("prid_name", PRID_MAP.get(prid));
            }
        }

        logger.info("Tvouch003Service执行完成");
        return list;
    }

    private void add(Object object, Map<String, Object> p, String name) {
        if (object != null) {
            p.put(name, "%" + object.toString() + "%");
        }

    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : data.keySet()) {
            if (data.get(key) == null || "".equals(data.get(key))) {

            } else {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }

    private String mosaicAlipayName(String withdrawName) {
        //银行卡号
        String regex = "(\\w{6})(.*)(\\w{4})";
        Matcher m = Pattern.compile(regex).matcher(withdrawName);
        if (m.find()) {
            String rep = m.group(2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rep.length(); i++) {
                sb.append("*");
            }
            return withdrawName.replaceAll(rep, sb.toString());
        }

        return withdrawName.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2");
    }
}
