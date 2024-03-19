package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.CupstsDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class Operate11 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private CupstsDao cupstsDao;
    @Autowired
    private HSiteDao hSiteDao;



    @Override
    public Object doHandle(Map<String, Object> p) {

        Date date = (Date) p.get("date");
        String operate_area = String.valueOf(p.get("operate_area"));
        String operate_time = String.valueOf(p.get("operate_time"));
        String operate_condition = String.valueOf(p.get("operate_condition"));

        List<Map<String, Object>> site_list = new ArrayList<>();
        //获取当前月份
        String yyyyMMdd = DateUtils.getDate(date, "yyyyMM");
        //2、查询符合条件的服务点
        //第一层筛选 整改范围
        List<Map<String, Object>> list1 = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                "approval_status", "2", "statuss", operate_area));
        //第二层筛选 触发时间 该服务点经过开业申请后的最后一笔是开业状态的记录
        if (list1 != null && list1.size() > 0) {
            for (Map<String, Object> map1 : list1) {
                List<Map<String, Object>> list2 = hSiteDao.queryForListToInspection1(BaseUtils.map("site_no", map1.get("site_no"), "status", "2", "approval_status", "2", "approval_type", "'007'"));
                if (list2 != null && list2.size() > 0) {
                    //取第一条
                    Map<String, Object> map2 = list2.get(0);
                    String update_time = String.valueOf(map2.get("update_time"));
                    //获取当前时间的某月份后
                    String month_date = getOverMonth(update_time, operate_time);
                    //获取当前时间
                    String now_date = DateUtil.now();
                    if (now_date.compareTo(month_date) > 0) {
                        //第三层筛选  触发条件   根据业务情况具体处理

                        List<String> numlist = tqNum(operate_condition);
                        String num1 = numlist.get(0);
                        String num2 = numlist.get(1);
                        String num3 = numlist.get(2);
                        if (num1.length() == 1) {
                            num1 = "0" + num1;
                        }
                        if (num2.length() == 1) {
                            num2 = "0" + num2;
                        }

                        Map<String, Object> params = new HashMap<>();
                        params.put("is_delete", "0");
                        params.put("approval_status", "2");
                        params.put("stat", "1");
                        params.put("prids", "'56','57','58'");//56助农（小额）取款 57现金汇款 58 转账汇款
                        params.put("site_no", map2.get("site_no"));

                        //获取前日
                        yyyyMMdd = DateUtils.getDate(DateUtils.addDay(date, -2), "yyyy-MM-dd");
                        //获取昨日
                        String yyyyMMdd_yday = DateUtils.getDate(DateUtils.addDay(date, -1), "yyyy-MM-dd");
                        String start_time = yyyyMMdd + " " + num1 + ":00:00";
                        String end_time = yyyyMMdd_yday + " " + num2 + ":00:00";
                        params.put("start_time", start_time);
                        params.put("end_time", end_time);
                        //查询
                        List<Map<String, Object>> result_list = cupstsDao.queryByconditionToInspection2(params);

                        if (result_list != null && result_list.size() > 0) {
                            int num = result_list.size();
                            if (num > Integer.parseInt(num3)) {
                                //添加
                                site_list.add(map1);
                            }
                        }
                    }
                } else {
                    logger.info(map1.get("site_name") + "没有开业审批记录");
                }
            }
        }

        p.put("site_list", site_list);
        p.put("yyyyMM", yyyyMMdd);
        p.put("type", 5);

        return p;

    }

    private String getOverMonth(String update_time, String operate_time) {
        List<String> list = tqNum(operate_time);
        String mouth = list.get(0);
        Date date = DateUtils.addMonth(DateUtil.parse(update_time, "yyyy-MM-dd HH:mm:ss"), Integer.parseInt(mouth));
        return DateUtils.getDate(DateUtil.beginOfMonth(date), "yyyy-MM-dd");
    }


    //提取数字
    private List<String> tqNum(String str) {
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
