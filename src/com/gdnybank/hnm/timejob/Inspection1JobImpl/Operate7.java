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
public class Operate7 implements Inspection1JobStrategy {

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
        String yyyyMM = DateUtils.getDate(date, "yyyyMM");
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
                        String num = numlist.get(0);
                        Map<String, Object> params = new HashMap<>();
                        params.put("is_delete", "0");
                        params.put("approval_status", "2");
                        params.put("stat", "1");
                        params.put("prids", "'56','57'");//56 小额取款  57现金汇款
                        params.put("site_no", map2.get("site_no"));

                        //获取T-n月
                        yyyyMM = DateUtils.getDate(DateUtils.addMonth(date, -Integer.parseInt(num)), "yyyyMM");
                        String start_date = yyyyMM + "01";
                        String end_date = yyyyMM + "31";
                        params.put("start_date", start_date);
                        params.put("end_date", end_date);
                        //查询没有做过的
                        List<Map<String, Object>> result_list1 = cupstsDao.queryByconditionToInspection1(params);
                        if (result_list1 != null && result_list1.size() > 0) {
                            //添加
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
