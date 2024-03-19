package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.CupstsDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.TVoucherDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class Operate5 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private CupstsDao cupstsDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private TVoucherDao tVoucherDao;


    @Override
    public Object doHandle(Map<String, Object> p) {
        String now_date = (String) p.get("now_date");
        String operate_area = String.valueOf(p.get("operate_area"));
        String operate_time = String.valueOf(p.get("operate_time"));
        String operate_condition = String.valueOf(p.get("operate_condition"));

        List<Map<String, Object>> site_list = new ArrayList<>();
        //获取当前季度
        String quarter = getCurrentQuarter();
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
                    if (now_date.compareTo(month_date) > 0) {
                        //第三层筛选  触发条件   根据业务情况具体处理
                        List<String> numlist = tqNum(operate_condition);
                        Map<String, Object> params = new HashMap<>();
                        params.put("is_delete", "0");
                        params.put("approval_status", "2");
                        params.put("stat", "1");
                        params.put("prids", "'56','57'");//56 小额取款  57现金汇款
                        params.put("site_no", map2.get("site_no"));
                        //获取本季度的第一天
                        params.put("start_date", DateUtils.getDate(getCurrentQuarterStartTime(), "yyyyMMdd"));
                        boolean flag = true;
                        int n = 0;
                        //查询没有做过的
                        List<Map<String, Object>> result_list1 = cupstsDao.queryByconditionToInspection1(params);
                        n += result_list1.size();
                        if (n > Integer.parseInt(numlist.get(0))) {
                            flag = false;
                        }

                        if (flag) {
                            //查询做过但账实不符的
                            params.clear();
                            params.put("site_no", map2.get("site_no"));
                            params.put("start_date", DateUtils.getDate(getCurrentQuarterStartTime(), "yyyyMMdd"));
                            params.put("gd_result", "1");
                            List<Map<String, Object>> result_list2 = tVoucherDao.queryForList(params);
                            n += result_list2.size();
                            if (n > Integer.parseInt(numlist.get(0))) {
                                flag = false;
                            }
                        }
                        if (!flag) {
                            //添加
                            site_list.add(map1);
                        }
                    }
                } else {
                    logger.info(map1.get("site_name") + "没有开业审批记录");
                }
            }
        }

        p.put("site_list",site_list);
        p.put("yyyyMM",quarter);
        p.put("type",3);

        return p;
    }

    private String getOverMonth(String update_time, String operate_time) {
        List<String> list = tqNum(operate_time);
        String mouth = list.get(0);
        Date date = DateUtils.addMonth(DateUtil.parse(update_time, "yyyy-MM-dd HH:mm:ss"), Integer.parseInt(mouth));
        return DateUtils.getDate(DateUtil.beginOfMonth(date), "yyyy-MM-dd");
    }

    //获取本季度的第一天
    private Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int cMonth = c.get(Calendar.MONTH) + 1;
        Date first = null;
        try {
            if (cMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (cMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (cMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (cMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            first = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return first;
    }

    //获取本季度是哪一季度
    private static String getCurrentQuarter() {
        Calendar c = Calendar.getInstance();
        int cMonth = c.get(Calendar.MONTH) + 1;
        String quarter = "";
        try {
            if (cMonth <= 3) {
                quarter = "第一季度";
            } else if (cMonth <= 6) {
                quarter = "第二季度";
            } else if (cMonth <= 9) {
                quarter = "第三季度";
            } else if (cMonth <= 12) {
                quarter = "第四季度";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quarter;
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
