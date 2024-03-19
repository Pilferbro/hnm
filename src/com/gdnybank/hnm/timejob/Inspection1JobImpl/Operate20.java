package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import com.gdnybank.hnm.pub.dao.AllTradeInfoDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;

import static cn.hutool.core.util.NumberUtil.div;

@Service
public class Operate20 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AllTradeInfoDao allTradeInfoDao;

    @Override
    public Object doHandle(Map<String, Object> p) {

        String operateCondition = String.valueOf(p.get("operate_condition"));
        Date date = (Date) p.get("date");
        //上一个交易日

        String yesToDayTransDt = DateUtils.getDate(DateUtils.addDay(date, -1), "yyyyMMdd");
        List<Map<String, Object>> siteList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        //前天交易日
        params.put("trans_dt", DateUtils.getDate(DateUtils.addDay(date, -2), "yyyyMMdd"));
        //提取交易量
        List<String> numList = getNum(operateCondition);
        String num = numList.get(0);
        params.put("num", num);
        //本卡本行交易
        //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
        params.put("tran_types", "'56','58'");
        List<Map<String, Object>> tradeMaps = allTradeInfoDao.countTrades(params);
        if (tradeMaps != null && tradeMaps.size() > 0) {
            for (Map<String, Object> tradeMap : tradeMaps) {

                List<Map<String, Object>> yesDayTradeInfos = allTradeInfoDao.countTrades(BaseUtils.map(
                        "trans_dt", yesToDayTransDt, "tran_types", "'56','58'", "acct_num", tradeMap.get("acct_num")
                ));
                if (yesDayTradeInfos != null && yesDayTradeInfos.size() > 0) {
                    //前天交易量
                    long countNum1 = Long.parseLong(String.valueOf(tradeMap.get("countNum")));
                    //昨天交易量
                    long countNum2 = Long.parseLong(String.valueOf(yesDayTradeInfos.get(0).get("countNum")));

                    DecimalFormat df = new DecimalFormat("0.#");
                    String format = df.format(div(countNum2 - countNum1, countNum1) * 100);

                    if (compareTo(format, numList.get(1)) >= 0) {

                        List<Map<String, Object>> tradeInfo = allTradeInfoDao.queryTrade(
                                BaseUtils.map("acct_num", tradeMap.get("acct_num"), "tran_types", "'56','58'", "trans_dt", yesToDayTransDt));
                        if (tradeInfo != null && tradeInfo.size() > 0) {
                            Map<String, Object> returnMap = tradeInfo.get(0);
                            returnMap.put("acct_num", tradeMap.get("acct_num"));
                            returnMap.put("tradeInfo",tradeInfo);
                            siteList.add(returnMap);
                        }
                    }
                } else {
                    logger.info(tradeMap.get("acct_num") + " " + yesToDayTransDt + "交易量为0");
                }
            }
        } else {
            logger.info("没有符合条件的交易");
        }
        p.put("site_list", siteList);
        p.put("yyyyMM", yesToDayTransDt);
        p.put("type", 7);

        return p;
    }


    //比较两个字符数字的大小
    public double compareTo(String otherString, String anotherString) {

        return Double.parseDouble(otherString) - Double.parseDouble(anotherString);
    }

    //提取数字
    private List<String> getNum(String str) {
        List<String> list = new ArrayList<>();
        String[] ss = str.split("\\D+");
        for (String s : ss) {
            if (!StringUtils.isEmpty(s)) {
                list.add(s);
            }
        }
        return list;
    }

}
