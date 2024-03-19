package com.nantian.hnm;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.timejob.*;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.test.base.ServiceTests;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static cn.hutool.core.util.NumberUtil.div;

public class TimeJobTest extends ServiceTests {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private Inspection1Job inspection1Job;
    @Autowired
    private LoadXGDataJob loadXGDataJob;
    @Autowired
    private ApprovalWarnSendMsgJob approvalWarnSendMsgJob;
    @Autowired
    private WarnTimeSendMegJob warnTimeSendMegJob;
    @Autowired
    private NewlyRiskWarnSendMsgJob1 newlyRiskWarnSendMsgJob1;
    @Autowired
    private LoadBlackBookDataJob loadBlackBookDataJob;
    @Autowired
    private LoadZNDataJob loadZNDataJob;

    @Test
    public void inspection1JobTest() {
        inspection1Job.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    @Test
    public void loadZNDataJobTest() {
        loadZNDataJob.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    @Test
    public void loadXGDataJobTest() {
        loadXGDataJob.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    @Test
    public void approvalWarnSendMsgJobTest() {
        approvalWarnSendMsgJob.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    @Test
    public void warnTimeSendMegJobTest() {
        warnTimeSendMegJob.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    @Test
    public void newlyRiskWarnSendMsgJobTest() {
        newlyRiskWarnSendMsgJob1.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    @Test
    public void loadBlackBookDataJobTest() {
        loadBlackBookDataJob.doService(BaseUtils.map("", ""), BaseUtils.map("", ""));
    }

    //n天后的日期
    @Test
    public void getDates() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        //90天
        String checkDay = "90";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse("2022-04-15 23:00:00");
        calendar.setTime(parse);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // days  多少天后的日期
        int newDay = day + Integer.parseInt(checkDay);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, newDay);
        String format1 = format.format(calendar.getTime());
        System.out.println(day);
        System.out.println(format1);
    }

    @Test
    public void isLastDayOfMonth() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = DateUtils.getNowDate();
//        String date1 = sdf.format(date);
//        Date date1 = sdf.parse("2022-06-30");
//        DateTime dateTime = DateUtil.endOfMonth(date);
//        System.out.println( "当前时间："+date1);
//        System.out.println( "月末时间"+sdf.format(dateTime));
//        System.out.println( DateUtil.now());
//        System.out.println(LocalDate.now());
//        Calendar calendar = Calendar.getInstance();
//       calendar.setTime(date1);
//        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
//        System.out.println(calendar.get(Calendar.DAY_OF_MONTH) == 1);
//        System.out.println(TimeZone.getDefault());
//        System.out.println(date1.equals(sdf.format(dateTime)));
//        LocalDateTime now = LocalDateTime.now();
//        int day = DateUtil.dayOfMonth(DateUtil.date());
//        LocalDateTime date = LocalDateTime.now();
//        logger.info(DateUtils.getNowDate());
//        System.out.println(LocalDate.now());
//        System.out.println(day);
//        Date ydate = DateUtils.addDay(DateUtils.getNowDate(),0);
        String date = DateUtils.getDate(DateUtils.getNowDate(), "yyyy-MM-dd");
//        String date1 = DateUtils.getDate(ydate,"yyyy-MM-dd HH:mm:ss");
        System.out.println(date + " 00:00:00");
//        System.out.println(date1);
    }

    @Test
    public void checkDay() throws ParseException {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //当天日期
//        Date parse = sdf.parse(DateUtil.now());
        Date parse = sdf.parse("2022-06-23 15:08:26");
        calendar.setTime(parse);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 当天N天后的日期
        int oldDay = day - Integer.parseInt("90");
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, oldDay);
        String format1 = sdf.format(calendar.getTime());

        //审批通过的时间
        String format2 = sdf.format(sdf.parse("2022-05-16 15:08:26"));
        System.out.println(format1);
        System.out.println(format1.compareTo(format2) == 0);
    }

    @Test
    public void getOverMonth() {


        Date date = DateUtils.addDay(DateUtils.getNowDate(), 10);
        String date1 = DateUtils.getDate(date, "yyyy-MM-dd");
        System.out.println(date1);
        System.out.println(DateUtils.getDate(DateUtil.endOfMonth(DateUtil.lastMonth()), "yyyy-MM-dd"));
    }


    @Autowired
    private LoadSynTradeInfoDataJob prejnlDataJob;
    @Autowired
    private MergerTradeInfoData mergerTradeInfoData;

    @Test
    public void loadCp() {
//        SimpleDateFormat format = new SimpleDateFormat("MMDDhhmmss");
        String str = "银苑支行test";

        System.out.println(false);
        long countNum1 = Long.parseLong("20");
        long countNum2 = Long.parseLong("10");
//        String parse = format.format(new Date(Long.parseLong("0820152531") * 1000L));
//        Date date1 = DateUtils.addDay(DateUtils.getNowDate(), -1);
//        DecimalFormat df = new DecimalFormat("0.#");
//        String format = df.format(div(countNum1 - countNum2, countNum2) * 100);
//        double v = Double.parseDouble(format) - Double.parseDouble("100");
//        System.out.println("format:" + format);
//        System.out.println("v:" + v);
//        prejnlDataJob.doService(null, null);
        mergerTradeInfoData.doService(null, null);
//        String yyyyMMdd = DateUtils.format(DateUtils.addMonth(DateUtils.getNowDate(), -1), "yyyyMMdd");
//        System.out.println(yyyyMMdd);
    }
}
