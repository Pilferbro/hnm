package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import static cn.hutool.core.util.NumberUtil.div;

@Service
public class Operate13 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private CupstsDao cupstsDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    public Object doHandle(Map<String, Object> p) {
        String operate_area = String.valueOf(p.get("operate_area"));
        String operate_time = String.valueOf(p.get("operate_time"));
        int operateTime = Integer.parseInt(getNum(operate_time).get(0));
        String operate_condition = String.valueOf(p.get("operate_condition"));
        String now_date = (String) p.get("now_date");
        Date date = (Date) p.get("date");

        //1、获取上月月份
        String yyyyMM = DateUtils.getDate(DateUtil.lastMonth(), "yyyyMM");

        List<Map<String, Object>> site_list = new ArrayList<>();

        //2、查询符合条件的服务点
        //第一层筛选 整改范围
        List<Map<String, Object>> list1 = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                "approval_status", "2", "statuss", operate_area));
        //第二层筛选 触发时间 该服务点经过开业申请后的最后一笔是开业状态的记录
        if (list1 != null && list1.size() > 0) {
            for (Map<String, Object> map1 : list1) {
                List<Map<String, Object>> list2 = hSiteDao.queryForListToInspection1(BaseUtils.map("site_no", map1.get("site_no"), "status", "2", "approval_status", "2", "approval_type", "'007'"));
                if (list2 != null && list2.size() > 0) {
                    Map<String, Object> map2 = list2.get(0);
                    String update_time = String.valueOf(map2.get("update_time"));
                    String month_date = getOverMonth(update_time, operateTime);

                    if (now_date.compareTo(month_date) > 0) {
                        //第三层筛选  触发条件   根据业务情况具体处理
                        //助农取款
                        Map<String, Object> params = new HashMap<>();

                        params.put("stat", "1");
                        params.put("site_no", map2.get("site_no"));

                        //获取n个月前的第一天
                        Date start_date1 = DateUtil.beginOfMonth(DateUtils.addMonth(date, -operateTime));
                        params.put("start_date", DateUtils.getDate(start_date1, "yyyyMMdd"));
                        //获取上个月的最后一天
                        Date end_date1 = DateUtil.endOfMonth(DateUtil.lastMonth());
                        params.put("end_date", DateUtils.getDate(end_date1, "yyyyMMdd"));
                        params.put("prids", "'56','57','58','60','65'");//56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
                        //获取过去半年的交易量
                        long count1 = cupstsDao.countByconditionToInspection(params);

                        //获取上个月的第一天
                        Date start_date2 = DateUtil.beginOfMonth(DateUtil.lastMonth());
                        params.put("start_date", DateUtils.getDate(start_date2, "yyyyMMdd"));

                        //获取最近一个月的交易量
                        long count2 = cupstsDao.countByconditionToInspection(params);

                        //计算基数
                        double cNum = div(count1, operateTime);
                        List<String> numList = getNum(operate_condition);

                        //交易笔数
                        long tNum = Long.parseLong(numList.get(2));

                        //判断触发条件
                        if (cNum < tNum) continue;

                        //偏离度计算公式=（当月交易量-基数（服务点过去半年的每月交易量的平均值））/基数*100%
                        DecimalFormat df = new DecimalFormat("0.#");
                        String format = df.format(div(count2 - cNum, cNum) * 100);

                        if (compareTo(format, numList.get(0)) > 0 && compareTo(format, numList.get(1)) <= 0) {
                            site_list.add(map1);
                        }
                    }
                } else {
                    logger.info(map1.get("site_name") + "没有开业审批记录");
                }
            }
        }

        p.put("site_list", site_list);
        p.put("yyyyMM", yyyyMM);
        p.put("type", 1);

        return p;
    }

    //获取n月前的第一天
    private String getOverMonth(String update_time, int operate_time) {
//        List<String> list = getNum(operate_time);
//        String mouth = list.get(0);
        Date date = DateUtils.addMonth(DateUtil.parse(update_time, "yyyy-MM-dd HH:mm:ss"), operate_time);
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
