package com.gdnybank.hnm.timejob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 合规操作模型 匹配规则后自动生成问题清单
 */
@Service
public class Inspection1Job extends BaseTimeJob {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private HnmCommService hnmCommService;
    @Autowired
    private TOperateDao tOperateDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Autowired
    private SysBranchDao sysBranchDao;
    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private CupstsDao cupstsDao;
    @Autowired
    private TVoucherDao tVoucherDao;
    @Autowired
    private HPatrolDao hPatrolDao;
    @Autowired
    private AllTradeInfoRiskDao allTradeInfoDao;
    private final Map<String, Object> RISK_LEVEL_MAP = BaseUtils.map("0", "低", "1", "中", "2", "高");
    //获取当前时间
    private Date date;
    //获取当前时间
    private String now_date;


    @Override
    //@Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“Inspection1Job”开始执行");
            //初始化当前时间
            now_date = DateUtil.now();
            date = DateUtils.getNowDate();

            //逻辑处理
            List<Map<String, Object>> list = tOperateDao.queryForList(BaseUtils.map());
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {

                    //是否启用
                    String use_up = String.valueOf(map.get("use_up"));
                    if ("0".equals(use_up)) {
                        //未启用
                        continue;
                    }
                    map.put("now_date", now_date);
                    map.put("date", date);
//                    //触发时间
//                    String operate_time = String.valueOf(map.get("operate_time"));
//                    //触发频率
                    String operate_rate = String.valueOf(map.get("operate_rate"));
//                    //触发条件
//                    String operate_condition = String.valueOf(map.get("operate_condition"));
                    //整改范围
                    String operate_area = String.valueOf(map.get("operate_area"));
                    StringBuffer sb = new StringBuffer();
                    String[] operate_area_arr = operate_area.split(",");
                    for (String area : operate_area_arr) {
                        sb.append("'").append(area).append("',");
                    }
                    operate_area = sb.substring(0, sb.length() - 1);
                    map.put("operate_area", operate_area);
//                    //问题描述
//                    String operate_content = String.valueOf(map.get("operate_content"));
//                    //风险等级
//                    String operate_risk = String.valueOf(map.get("operate_risk"));
//                    //整改时间
//                   String operate_day = String.valueOf(map.get("operate_day"));
//                    //整改要求
//                    String operate_requirement = String.valueOf(map.get("operate_requirement"));
//                    //处理人
//                    String operate_operno = String.valueOf(map.get("operate_operno"));
//                    //项目名称 默认系统自动排查
//                    String PROJECT_CLASS = "3";
                    map.put("project_class", "3");

                    //代理工厂获取对应的策略bean对象
                    Inspection1JobStrategy bean = MfpContextHolder.getBean("operate" + map.get("operate_no"));

                    if (bean != null && check(operate_rate)) {
                        logger.info("执行" + map.get("operate_name"));

                        Map<String, Object> resultMap = (Map<String, Object>) bean.doHandle(map);
                        createQuestion(resultMap);

                        logger.info(map.get("operate_name") + "执行完成");

                    } else {

                        logger.info(map.get("operate_name") + "执行失败" + "当前日期：" + date + "触发频率：" + operate_rate);
                    }

                }
            }
            logger.info("定时任务“Inspection1Job”执行结束");
            return null;
        }
    }


    private String getDates(String update_time, String operate_time) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        List<String> list = tqNum(operate_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse(update_time);
        calendar.setTime(parse);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // days  多少天后的日期
        int newDay = day + Integer.parseInt(list.get(0));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, newDay);
        return format.format(calendar.getTime());
    }

    private Boolean checkDay(String update_time, String operate_time) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        List<String> list = tqNum(operate_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //当天日期
//        Date parse = sdf.parse(DateUtil.now());
        Date parse = sdf.parse("2022-06-23 15:08:26");
        calendar.setTime(parse);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 当天N天前的日期
        int oldDay = day - Integer.parseInt(list.get(0));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, oldDay);
        String format1 = sdf.format(calendar.getTime());

        //审批通过的时间
        String format2 = sdf.format(sdf.parse(update_time));

        return format1.compareTo(format2) == 0;
    }

    //判断触发频率  是否触发
    private Boolean check(String operate_rate) {
        boolean flag = true;
        //获取当前日期的天
        int day = DateUtil.dayOfMonth(date);
        //1、先看触发频率
        if ("每日检查".equals(operate_rate)) {
            flag = true;
        } else if ("每月末检查".equals(operate_rate)) {
            if (!isLastDayOfMonth()) {
                flag = false;
            }
        } else if ("每季末检查".equals(operate_rate)) {
            if (!isLastDayOfQuarter()) {
                flag = false;
            }
        } else {
            List<String> numlist = tqNum(operate_rate);
            if (!(day + "").equals(numlist.get(0))) {
                flag = false;
            }
        }
        return flag;
    }

    //判断当天是否为本月末
    private Boolean isLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    //判断当天是否为本季末
    private Boolean isLastDayOfQuarter() {
        Calendar c = Calendar.getInstance();
        int cMonth = c.get(Calendar.MONTH) + 1;
        Date last = null;
        try {
            if (cMonth <= 3) {
                c.set(Calendar.MONTH, 3);
                c.set(Calendar.DATE, 31);
            } else if (cMonth <= 6) {
                c.set(Calendar.MONTH, 6);
                c.set(Calendar.DATE, 30);
            } else if (cMonth <= 9) {
                c.set(Calendar.MONTH, 9);
                c.set(Calendar.DATE, 30);
            } else if (cMonth <= 12) {
                c.set(Calendar.MONTH, 12);
                c.set(Calendar.DATE, 31);
            }
            last = c.getTime();
            String last_date = DateUtils.getDate(last, "yyyy-MM-dd");
            String now_date = DateUtils.getDate("yyyy-MM-dd");
            if (now_date.equals(last_date)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    //获取n月后月份的第一天
    private String getOverMonth(String time, String str) {
        List<String> list = tqNum(str);
        String mouth = list.get(0);
        Date date = DateUtils.addMonth(DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss"), Integer.parseInt(mouth));
        return DateUtils.getDate(DateUtil.beginOfMonth(date), "yyyy-MM-dd");
    }

    //获取本月月末日期
    private Date getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        //日，设为一号
        cal.set(Calendar.DATE, 1);
        //月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        //下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
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

    //生成问题清单
    private void createQuestion(Map<String, Object> dataMap) {

        List<Map<String, Object>> site_list = (List<Map<String, Object>>) dataMap.get("site_list");
        String operate_day = String.valueOf(dataMap.get("operate_day"));
        String ym = String.valueOf(dataMap.get("yyyyMM"));
        String operate_content = String.valueOf(dataMap.get("operate_content"));
        String operate_operno = String.valueOf(dataMap.get("operate_operno"));
        String operate_risk = String.valueOf(dataMap.get("operate_risk"));
        String operate_requirement = String.valueOf(dataMap.get("operate_requirement"));
        String project_class = String.valueOf(dataMap.get("project_class"));
        String operate_no = String.valueOf(dataMap.get("operate_no"));
        String operate_range = String.valueOf(dataMap.get("operate_range"));
        int type = (int) dataMap.get("type");

        //3 生成问题清单
        if (site_list != null && site_list.size() > 0) {
            //stream流根据站点名称生成List并打印到日志
            List<Object> site_nameList = site_list.stream().map(e -> e.get("site_name")).collect(Collectors.toList());
            logger.info("存在风险的站点：" + site_nameList);
            //获取整改日期
            String end_date = "";
            if ("本月末".equals(operate_day)) {
                end_date = DateUtils.getDate(getLastDayOfMonth(), "yyyy-MM-dd");
            } else {
                List<String> numlist = tqNum(operate_day);
                Date enddate = DateUtils.addDay(date, Integer.parseInt(numlist.get(0)));
                end_date = DateUtils.getDate(enddate, "yyyy-MM-dd");
            }

            if (ym != null) {
                ym = ym.replaceAll("-", "");
            }

            for (Map<String, Object> map : site_list) {
                //获取问题描述
                String content_text = "";
                if (1 == type) {
                    String new_ym = ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月";
                    content_text = operate_content.replaceAll("【服务点】【月份】", map.get("site_name") + new_ym);
                } else if (2 == type) {
                    content_text = operate_content.replaceAll("【服务点】", map.get("site_name") + "");
                } else if (3 == type) {
                    content_text = operate_content.replaceAll("【服务点】【季度】", map.get("site_name") + ym);
                } else if (4 == type) {
                    String new_ym = ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月";
                    content_text = operate_content.replaceAll("【服务点】【月份】【账户】", map.get("site_name") + new_ym + valueOf(map.get("bankacc")) + "账户");
                } else if (5 == type) {
                    String new_ym = ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月" + ym.substring(6, 8) + "日";
                    content_text = operate_content.replaceAll("【服务点】【日期】", map.get("site_name") + new_ym);
                } else if (6 == type) {
                    String new_ym = ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月" + ym.substring(6, 8) + "日";
                    content_text = operate_content.replaceAll("【服务点】【日期】", map.get("site_name") + new_ym).replace("**", valueOf(map.get("count")));
                } else if (7 == type) {
                    String new_ym = ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月" + ym.substring(6, 8) + "日";
                    content_text = operate_content.replaceAll("【日期】", new_ym)
                            .replace("【支行】", valueOf(map.get("branch_name")))
                            .replace("【客户】", valueOf(map.get("cust_name")))
                            .replace("【账户】", valueOf(map.get("acct_num")))
                            .replace("【交易量】", valueOf(map.get("count")))
                            .replace("%", valueOf(map.get("percent")) + "%")
                            .replace("【服务点】", valueOf(map.get("tran_addr")))
                            .replace("【金额】", valueOf(map.get("tran_amts")));
                } else if (8 == type) {
                    String new_ym = ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月";
                    content_text = operate_content.replaceAll("【日期】", new_ym)
                            .replace("【支行】", valueOf(map.get("branch_name")))
                            .replace("【客户】", valueOf(map.get("cust_name")))
                            .replace("【账户】", valueOf(map.get("acct_num")))
                            .replace("【交易量】", valueOf(map.get("count")))
                            .replace("%", valueOf(map.get("percent")) + "%")
                            .replace("【服务点】", valueOf(map.get("tran_addr")))
                            .replace("【终端号】", valueOf(map.get("terminal_id")));
                }

                //获取整改人
                if ("0".equals(operate_operno)) {
                    //分行管理员
                    //根据站点编号查询分行及分行管理员
                    List<Map<String, Object>> userlist = new ArrayList<>();
                    List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                            map.get("branch_id")));
                    if (allPorgidList != null && allPorgidList.size() > 0) {
                        for (Map<String, Object> allPorgid : allPorgidList) {
                            if (ObjectUtil.isNotEmpty(allPorgid.get("bran_level")) && "1".equals(allPorgid.get(
                                    "bran_level").toString())) {
                                String fh_branch_id = String.valueOf(allPorgid.get("branch_id"));
                                //查询分行管理员角色并给其分行管理员
                                userlist = sysAccountDao.queryForListBycondition(BaseUtils.map("branch_id", fh_branch_id, "role_id", "2021061500000004"));

                                break;
                            }
                        }
                    }
                    //没有分行管理员则关联到总行管理人
                    if (userlist == null || userlist.size() == 0) {
                        userlist = sysAccountDao.queryForListBycondition(BaseUtils.map("role_id", "2021061500000002"));
                    }
                    map.put("mgr_id", userlist.get(0).get("account_id"));

                } else if ("1".equals(operate_operno)) {
                    //站长对口惠农客户经理
                    if (ObjectUtil.isEmpty(map.get("mgr_id"))) {
                        List<Map<String, Object>> userlist = new ArrayList<>();
                        userlist = sysAccountDao.queryForListBycondition(BaseUtils.map("role_id", "2021061500000002"));
                        map.put("mgr_id", userlist.get(0).get("account_id"));
                    }
                    //总行管理员
                } else if ("2".equals(operate_operno)) {
                    List<Map<String, Object>> userlist = new ArrayList<>();
                    userlist = sysAccountDao.queryForListBycondition(BaseUtils.map("role_id", "2021061500000002"));
                    map.put("mgr_id", userlist.get(0).get("account_id"));
                }

                Map<String, Object> saveMap = new HashMap<>();
                if (ObjectUtil.isNotEmpty(map.get("site_no"))) {

                    saveMap.put("site_no", map.get("site_no"));
                }
                saveMap.put("indexs", "1");
                saveMap.put("content_text", content_text);
                saveMap.put("risk_level", RISK_LEVEL_MAP.get(operate_risk));
                saveMap.put("requirement", operate_requirement);
                saveMap.put("responsible", String.valueOf(map.get("mgr_id")));
                saveMap.put("source", "4");
                saveMap.put("isdeleted", "0");
                saveMap.put("pj_classify", project_class);
                saveMap.put("spare1", operate_no);
                saveMap.put("spare2", operate_range);
                //判断是否已有风险信息
                long count = hPatrolLogContentDao.count(saveMap);
                saveMap.put("end_date", end_date);
                saveMap.put("created", now_date);
                saveMap.put("updatetime", now_date);
                if (count == 0) {
                    String riskId = hPatrolLogContentDao.saveForId(saveMap);
                    logger.info("生成风险信息：" + content_text);

                    if (map.containsKey("tradeInfo") && map.get("tradeInfo") != null) {
                        List<Map<String, Object>> tradeInfoList = (List<Map<String, Object>>) map.get("tradeInfo");
                        for (Map<String, Object> stringObjectMap : tradeInfoList) {
                            stringObjectMap.put("risk_id", riskId);
                            allTradeInfoDao.save(stringObjectMap);
                        }
                    }
                } else {
                    logger.info(map.get("site_name") + "已存在风险信息");
                }
            }
        } else {
            logger.info("不存在风险站点");
        }
    }


    public String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}
