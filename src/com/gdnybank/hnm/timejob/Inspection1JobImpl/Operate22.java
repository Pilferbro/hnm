package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.AllTradeInfoDao;
import com.gdnybank.hnm.pub.dao.CupstsDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.gdnybank.hnm.pub.service.HnmCommService;
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
public class Operate22 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AllTradeInfoDao allTradeInfoDao;

    @Override
    public Object doHandle(Map<String, Object> p) {

        String operateCondition = String.valueOf(p.get("operate_condition"));

        Date date = (Date) p.get("date");
        //1、获取昨天日期

        String yesToDayTransDt = DateUtils.getDate(DateUtils.addDay(date, -1), "yyyyMMdd");
        List<Map<String, Object>> siteList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        //本卡他行交易
        //取款
//        params.put("proc_cd1", "'01%'");
        //转账-转出
//        params.put("proc_cd2", "'46%'");
//        params.put("mercht_cate_cd", "6051");
//        params.put("info_type_cd", "0200");
        //前天日期
        params.put("trans_dt", yesToDayTransDt);
        //提取限额金额
        List<String> numList = getNum(operateCondition);
        String num1 = numList.get(0);
        String num2 = numList.get(1);
        params.put("num1", num1);
        params.put("num2", num2);
        //本卡本行交易
        //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
        params.put("tran_types1", "56");
        params.put("tran_types2", "58");

        List<Map<String, Object>> tradeMaps = allTradeInfoDao.queryQuotaTrade(params);
        if (tradeMaps != null && tradeMaps.size() > 0) {
            for (Map<String, Object> tradeMap : tradeMaps) {
                params.put("acct_num", tradeMap.get("acct_num"));

                List<Map<String, Object>> tradeDetails = allTradeInfoDao.queryTrade(params);

                if (tradeDetails != null && tradeDetails.size() > 0) {
                    Map<String, Object> returnMap = tradeDetails.get(0);
                    returnMap.put("acct_num",tradeMap.get("acct_num"));
                    returnMap.put("tradeInfo",tradeDetails);
                    siteList.add(returnMap);
                }
            }
        }else {
            logger.info("没有超额交易。");
        }
        p.put("site_list", siteList);
        p.put("yyyyMM", yesToDayTransDt);
        p.put("type", 7);

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
