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
public class Operate27 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AllTradeInfoDao allTradeInfoDao;
    @Autowired
    private HSiteDao hSiteDao;

    @Override
    public Object doHandle(Map<String, Object> p) {

        String operateCondition = String.valueOf(p.get("operate_condition"));

        Date date = (Date) p.get("date");
        //1、获取上月日期
        String yesToDayTransDt = DateUtils.getDate(DateUtils.addDay(date, -1), "yyyyMMdd");
        List<Map<String, Object>> siteList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        //昨日日期
        params.put("trans_dt", yesToDayTransDt);

        List<String> numList = getNum(operateCondition);
        String num = numList.get(0);


        //本卡本行交易
        //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
        params.put("tran_types", "'57','222'");
        params.put("num", num);

        List<Map<String, Object>> tradeMaps = allTradeInfoDao.sumTransTm(params);
        if (tradeMaps != null && tradeMaps.size() > 0) {
            for (Map<String, Object> tradeMap : tradeMaps) {

                List<Map<String, Object>> siteMaps = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                        "approval_status", "2", "status", "2", "card_no", tradeMap.get("acct_num")));
                //非站长账号
                if (!(siteMaps != null && siteMaps.size() > 0)) {
                    params.put("acct_num", tradeMap.get("acct_num"));
                    List<Map<String, Object>> maps = allTradeInfoDao.queryTrade(params);

                    Map<String, Object> returnMap = maps.get(0);
                    returnMap.put("acct_num", tradeMap.get("acct_num"));
                    returnMap.put("tran_amts", tradeMap.get("tran_amt"));
                    returnMap.put("tradeInfo", maps);
                    siteList.add(returnMap);
                }
            }
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
