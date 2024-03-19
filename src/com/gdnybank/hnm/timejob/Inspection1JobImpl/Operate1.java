package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
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
public class Operate1 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private HnmCommService hnmCommService;
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
        //获取当前月份
        String yyyyMM = DateUtils.getDate(date, "yyyyMM");
        //2、查询符合条件的服务点
        //第一层筛选 整改范围
        List<Map<String, Object>> list1 = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                "approval_status", "2", "statuss", operate_area));
        //第二层筛选 触发时间 该服务点经过试营业申请或转试营业申请后的最后一笔是试营业状态的记录
        if (list1 != null && list1.size() > 0) {
            for (Map<String, Object> map1 : list1) {
                List<Map<String, Object>> list2 = hSiteDao.queryForListToInspection1(BaseUtils.map("site_no", map1.get("site_no"), "status", "0", "approval_status", "2", "approval_type", "'004','022'"));
                if (list2 != null && list2.size() > 0) {
                    Map<String, Object> map2 = list2.get(0);
                    String update_time = String.valueOf(map2.get("update_time"));
                    String month_date = getOverMonth(update_time, operate_time);

                    if (now_date.compareTo(month_date) > 0) {
                        //第三层筛选  触发条件   根据业务情况具体处理
                        //资产余额
                        Map<String, Object> parms = new HashMap<>();
                        //获取上月月份
                        yyyyMM = DateUtils.getDate(DateUtils.addMonth(date, -1), "yyyyMM");
                        parms.put("acctday", yyyyMM);
                        //获取上月的最后一天
                        Date last_dateday = getLastDayOfMonth2();
                        String day_str = String.valueOf(DateUtil.dayOfMonth(last_dateday));
                        if (day_str.length() == 1) {
                            day_str = "0" + day_str;
                        }
                        String onlbal = "onlbal" + day_str;
                        parms.put("onlbal", onlbal);
                        parms.put("siteIds", "'" + map2.get("site_no") + "'");

                        //查询存款信息
                        List<Map<String, Object>> list_ck = hnmCommService.qureyCK(parms);
                        if (list_ck != null && list_ck.size() > 0) {
                            BigDecimal amt1 = BigDecimal.ZERO;
                            for (Map<String, Object> dataMap : list_ck) {
                                if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                                    amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                }
                            }
                            BigDecimal val = hnmCommService.dealVal(amt1);
                            BigDecimal rule_amt = BigDecimal.valueOf(Integer.parseInt(tqNum(operate_condition).get(0))).multiply(BigDecimal.valueOf(10000));
                            if (val.compareTo(rule_amt) < 0) {
                                //添加
                                site_list.add(map1);
                            } else {
                                logger.info(map1.get("site_name") + "的名下账户零售AUM存款余额为" + val);
                            }
                        } else {
                            //添加
                            site_list.add(map1);
                        }
                    }
                } else {
                    logger.info(map1.get("site_name") + "没有试营业审批记录");
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

    //获取上月月末日期
    private Date getLastDayOfMonth2() {
        Calendar cal = Calendar.getInstance();
        //月设为上一月
        cal.set(Calendar.MONTH, -1);
        //日，设为一号
        cal.set(Calendar.DATE, 1);
        //月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        //下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
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
