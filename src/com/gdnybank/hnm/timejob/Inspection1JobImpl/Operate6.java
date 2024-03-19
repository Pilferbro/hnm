package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.HPatrolDao;
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
public class Operate6 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private HPatrolDao hPatrolDao;
    @Autowired
    private HSiteDao hSiteDao;



    @Override
    public Object doHandle(Map<String, Object> p) {
        String now_date = (String) p.get("now_date");
        Date date = (Date) p.get("date");
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
                    Map<String, Object> map2 = list2.get(0);
                    String update_time = String.valueOf(map2.get("update_time"));
                    String month_date = getOverMonth(update_time, operate_time);

                    if (now_date.compareTo(month_date) > 0) {
                        //第三层筛选  触发条件  根据业务情况具体处理
                        //根据巡检结果判断
                        Map<String, Object> params = new HashMap<>();
                        params.put("site_id", map2.get("site_no"));
                        params.put("inspect_start", DateUtils.getDate(getCurrentQuarterStartTime(), "yyyy-MM-dd"));
                        params.put("patrol_content", "80101");
                        List<Map<String, Object>> result_list = hPatrolDao.queryForListToInspection1(params);
                        if (result_list != null && result_list.size() > 0) {
                            int num = 0;
                            List<String> numlist = tqNum(operate_condition);
                            //本季度只有一次巡查
                            if (result_list.size() == 1) {
                                num += 2;
                            }
                            //本季度只有两次巡查
                            if (result_list.size() == 2) {
                                num++;
                            }
                            for (Map<String, Object> result_map : result_list) {
                                if (result_map.get("patrol_result") != null && "情况异常".equals(result_map.get("patrol_result"))) {
                                    num++;
                                }
                            }
                            if (num >= Integer.parseInt(numlist.get(0))) {
                                //添加
                                site_list.add(map1);
                            }
                        } else {
                            //本季度没有做巡检
                            site_list.add(map1);
                            logger.info(map1.get("site_name") + "本季度没有做巡检");
                        }
                    }
                } else {
                    logger.info(map1.get("site_name") + "没有开业审批记录");
                }
            }
        }

        p.put("site_list", site_list);
        p.put("yyyyMM", quarter);
        p.put("type", 3);

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
