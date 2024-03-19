package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
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
public class Operate23 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AllTradeInfoDao allTradeInfoDao;

    @Override
    public Object doHandle(Map<String, Object> p) {

        String operateCondition = String.valueOf(p.get("operate_condition"));

        Date date = (Date) p.get("date");
        //1、获取上月日期
        String lastMonth = DateUtils.getDate(DateUtils.addMonth(date, -1), "yyyyMMdd");
        List<Map<String, Object>> siteList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        //上月日期
        params.put("start_date", DateUtils.getDate(DateUtil.beginOfMonth(DateUtils.addMonth(date, -1)), "yyyyMMdd"));
        params.put("end_date", DateUtils.getDate(DateUtil.endOfMonth(DateUtils.addMonth(date, -1)), "yyyyMMdd"));

        List<String> numList = getNum(operateCondition);
        String num = numList.get(0);

        //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
        params.put("tran_types", "'56','58'");
        //总交易量
        long count = allTradeInfoDao.count(params);
        List<Map<String, Object>> tradeMaps = allTradeInfoDao.countTrades(params);
        if (tradeMaps != null && tradeMaps.size() > 0) {
            for (Map<String, Object> tradeMap : tradeMaps) {
                long countNum = Long.parseLong(String.valueOf(tradeMap.get("countNum")));

                DecimalFormat df = new DecimalFormat("0.#");
                String format = df.format(div(countNum, count) * 100);

                if (compareTo(format, num) > 0) {

                    params.put("acct_num", tradeMap.get("acct_num"));
                    List<Map<String, Object>> tradeDetails = allTradeInfoDao.queryTrade(params);
                    if (tradeDetails != null && tradeDetails.size() > 0) {
                        Map<String, Object> returnMap = tradeDetails.get(0);
                        returnMap.putAll(tradeMap);
                        returnMap.put("count", countNum);
                        returnMap.put("percent", num);
                        returnMap.put("tradeInfo",tradeDetails);
                        siteList.add(returnMap);

                    }
                }
            }
        }
        p.put("site_list", siteList);
        p.put("yyyyMM", lastMonth);
        p.put("type", 8);

        return p;
    }

    //获取n月前的第一天
    private String getOverMonth(String updateTime, int operateTime) {
//        List<String> list = getNum(operate_time);
//        String mouth = list.get(0);
        Date date = DateUtils.addMonth(DateUtil.parse(updateTime, "yyyy-MM-dd HH:mm:ss"), operateTime);
        return DateUtils.getDate(DateUtil.beginOfMonth(date), "yyyy-MM-dd");
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
