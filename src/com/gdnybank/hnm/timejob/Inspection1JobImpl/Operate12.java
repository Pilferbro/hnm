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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Operate12 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private HPatrolDao hPatrolDao;
    @Autowired
    private HSiteDao hSiteDao;


    @Override
    public Object doHandle(Map<String, Object> p) {

        Date date = (Date) p.get("date");
        String operate_area = String.valueOf(p.get("operate_area"));
        String operate_time = String.valueOf(p.get("operate_time"));

        List<Map<String, Object>> site_list = new ArrayList<>();
        //获取当前月份
        String yyyyMM = DateUtils.getDate(date, "yyyyMM");
        //2、查询符合条件的服务点
        //第一层筛选 整改范围
        List<Map<String, Object>> list1 = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                "approval_status", "2", "statuss", operate_area));
        //第二层筛选 触发时间 开业转退出或转试营业后90天后的巡检情况
        if (list1 != null && list1.size() > 0) {
            for (Map<String, Object> map1 : list1) {

                List<Map<String, Object>> list2 = hSiteDao.queryForHisList(BaseUtils.map("site_no", map1.get("site_no"), "approval_status", "2", "approval_type", "'004','007','008','022'"));
                if (list2 != null && list2.size() >= 2) {
                    //获取最近第一条审批记录类型
                    String approval_type1 = String.valueOf(list2.get(0).get("approval_type"));
                    //获取最近第二条审批记录类型
                    String approval_type2 = String.valueOf(list2.get(1).get("approval_type"));
                    //判断是否从开业转出且目前状态为退出或试营业
                    if ("007".equals(approval_type2) && ("008".equals(approval_type1) || "022".equals(approval_type1))) {
                        //获取最近第一条审批记录
                        Map<String, Object> map2 = list2.get(0);
                        String update_time = String.valueOf(map2.get("update_time"));
                        //判断转出开业状态的天数
                        if (checkDay(update_time, operate_time)) {
                            //第三层筛选  触发条件   根据业务情况具体处理
                            //根据巡检结果判断
                            Map<String, Object> params = new HashMap<>();
                            params.put("site_id", map1.get("site_no"));
                            yyyyMM = DateUtils.getDate(date, "yyyy-MM");
                            //String inspect_start = yyyyMM + "-01";
                            //String inspect_end = yyyyMM + "-31";
                            params.put("inspect_start", update_time);
                            //params.put("inspect_end", inspect_end);
                            params.put("category", 1);
                            List<Map<String, Object>> result_list = hPatrolDao.queryForListToInspection1(params);
                            if (result_list != null && result_list.size() > 0) {
                                //是否已经上传巡检报告
                                int num = 0;
                                for (Map<String, Object> result_map : result_list) {
                                    if (result_map.get("patrol_content") != null || ("80101".equals(result_map.get("patrol_content")) && result_map.get("patrol_result") != null)) {
                                        num = 0;
                                        break;
                                    } else {
                                        num++;
                                    }
                                }
                                if (num > 0) {
                                    //添加
                                    site_list.add(map1);
                                }
                            } else {
                                site_list.add(map1);
                            }
                        }

                    } else {
                        logger.info(map1.get("site_name") + "没有退出或转试营业审批记录");
                    }
                }
            }
        }

        p.put("site_list", site_list);
        p.put("yyyyMM", yyyyMM);
        p.put("type", 2);

        return p;
    }

    private Boolean checkDay(String update_time, String operate_time) {

        Calendar calendar = Calendar.getInstance();
        List<String> list = tqNum(operate_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date parse;
        String format1 = "", format2 = "";
        try {
            parse = sdf.parse(DateUtil.now());
            calendar.setTime(parse);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            // 当天N天前的日期
            int oldDay = day - Integer.parseInt(list.get(0));
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, oldDay);
            format1 = sdf.format(calendar.getTime());

            //审批通过的时间
            format2 = sdf.format(sdf.parse(update_time));
        } catch (ParseException e) {
            logger.info("日期转换失败:" + e);
        }
        return format1.compareTo(format2) == 0;
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
