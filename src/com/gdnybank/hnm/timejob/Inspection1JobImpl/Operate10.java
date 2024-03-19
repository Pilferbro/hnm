package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
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
import java.util.*;

@Service
public class Operate10 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private CupstsDao cupstsDao;


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
                        Map<String, Object> params = new HashMap<>();
                        params.put("is_delete", "0");
                        params.put("approval_status", "2");
                        params.put("stat", "1");
                        params.put("prids", "'57','58'");// 57现金汇款 58 转账汇款
                        params.put("site_no", map2.get("site_no"));

                        //获取上月
                        yyyyMM = DateUtils.getDate(DateUtils.addMonth(date, -1), "yyyyMM");
                        String start_date = yyyyMM + "01";
                        String end_date = yyyyMM + "31";
                        params.put("start_date", start_date);
                        params.put("end_date", end_date);
                        //查询
                        List<Map<String, Object>> result_list = cupstsDao.queryByconditionToInspection2(params);

                        if (result_list != null && result_list.size() > 0) {
                            List<String> numlist = tqNum(operate_condition);
                            String num1 = numlist.get(0);
                            String num2 = numlist.get(1);

                            Map<String, Map<String, Object>> new_map = new HashMap<>();
                            for (Map<String, Object> result_map : result_list) {
                                if (result_map.get("f062") != null) {
                                    if (new_map.containsKey(String.valueOf(result_map.get("f062")))) {
                                        Map<String, Object> two_map = new_map.get(String.valueOf(result_map.get(
                                                "f062")));
                                        String bankacc = two_map.get("bankacc").toString();
                                        BigDecimal amt = BigDecimal.valueOf(Double.parseDouble(two_map.get("amt").toString()));

                                        if (!bankacc.contains(result_map.get("f002") + ",")) {
                                            bankacc = bankacc + result_map.get("f002") + ",";
                                        }
                                        BigDecimal f004 = BigDecimal.valueOf(Double.valueOf(result_map.get("f004").toString()));
                                        amt = amt.add(hnmCommService.dealVal(f004.divide(BigDecimal.valueOf(100))));
                                        two_map.put("bankacc", bankacc);
                                        two_map.put("amt", amt);
                                        new_map.put(String.valueOf(result_map.get("f062")), two_map);
                                    } else {
                                        Map<String, Object> two_map = new HashMap<>();
                                        String bankacc = result_map.get("f002") + ",";
                                        BigDecimal f004 = BigDecimal.valueOf(Double.valueOf(result_map.get("f004").toString()));
                                        BigDecimal amt = hnmCommService.dealVal(f004.divide(BigDecimal.valueOf(100)));
                                        two_map.put("bankacc", bankacc);
                                        two_map.put("amt", amt);
                                        new_map.put(String.valueOf(result_map.get("f062")), two_map);
                                    }
                                }
                            }

                            //添加
                            if (ObjectUtil.isNotEmpty(new_map)) {
                                for (String key : new_map.keySet()) {
                                    Map<String, Object> map = new_map.get(key);
                                    String bankacc = map.get("bankacc").toString();
                                    BigDecimal amt = BigDecimal.valueOf(Double.parseDouble(map.get("amt").toString()));

                                    String[] bankacc_arr = bankacc.split(",");
                                    if (bankacc_arr.length >= Integer.parseInt(num1)) {
                                        BigDecimal rule_amt = BigDecimal.valueOf(Integer.parseInt(num2)).multiply(BigDecimal.valueOf(10000));
                                        if (amt.compareTo(rule_amt) >= 0) {
                                            //添加
                                            map1.put("bankacc", key);
                                            site_list.add(map1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    logger.info(map1.get("site_name") + "没有开业审批记录");
                }
            }
        }

        p.put("site_list", site_list);
        p.put("yyyyMM", yyyyMM);
        p.put("type", 4);

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
