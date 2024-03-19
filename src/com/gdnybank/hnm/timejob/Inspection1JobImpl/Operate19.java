package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import com.gdnybank.hnm.pub.dao.AllTradeInfoDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class Operate19 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AllTradeInfoDao allTradeInfoDao;


    @Override
    public Object doHandle(Map<String, Object> p) {

        String operateCondition = String.valueOf(p.get("operate_condition"));
        Date date = (Date) p.get("date");
        List<Map<String, Object>> siteList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        //提取交易量
        String num = getNum(operateCondition).get(0);
        params.put("num", num);
        //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
        params.put("tran_types", "'56','58'");
        //交易时间
        String transDate = DateUtils.getDate(DateUtils.addDay(date, -1), "yyyyMMdd");

        params.put("trans_dt", transDate);
        List<Map<String, Object>> tradeMaps = allTradeInfoDao.countTrades(params);
        if (tradeMaps != null && tradeMaps.size() > 0) {
            for (Map<String, Object> tradeMap : tradeMaps) {

                List<Map<String, Object>> tradeInfo = allTradeInfoDao.queryTrade(
                        BaseUtils.map("acct_num",tradeMap.get("acct_num"),"tran_types", "'56','58'","trans_dt", transDate));

                if (tradeInfo != null && tradeInfo.size() > 0) {
                    Map<String, Object> returnMap = tradeInfo.get(0);
                    returnMap.put("acct_num",tradeMap.get("acct_num"));
                    returnMap.put("count",tradeMap.get("countNum"));
                    returnMap.put("tran_amts",tradeMap.get("tran_amt"));
                    returnMap.put("tradeInfo",tradeInfo);
                    siteList.add(returnMap);
                }
            }
        }else {
            logger.info("19卡交易量异常不存在。");
        }
        p.put("site_list", siteList);
        p.put("yyyyMM", transDate);
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
