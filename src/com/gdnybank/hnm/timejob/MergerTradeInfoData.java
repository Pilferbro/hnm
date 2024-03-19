package com.gdnybank.hnm.timejob;

import cn.hutool.core.util.ObjectUtil;

import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合并AllTradeInfo数据
 */
@Service
public class MergerTradeInfoData extends BaseTimeJob {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TCpDataHisDao tCpDataHisDao;
    @Autowired
    private TMerDataHisDao tMerDataHisDao;
    @Autowired
    private TZnDataHisDao tZnDataHisDao;
    @Autowired
    private AllTradeInfoDao allTradeInfoDao;
    @Autowired
    private TradeInfoDao tradeInfoDao;
    @Autowired
    private CupstsDao cupstsDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Resource
    private HnmCommService hnmCommService;


    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“MergerTradeInfoData”开始执行");
            MergerData();
            logger.info("定时任务“MergerTradeInfoData”执行结束");

            return null;
        }
    }

    private void MergerData() {

        String batchDate = DateUtils.getDate("yyyyMMdd");

        //助农合并记录
        List<Map<String, Object>> maxTime2 = tMerDataHisDao.queryForListMaxCreateDate(BaseUtils.map("data_name", "CUPSTS"));
        //交易合并记录
        List<Map<String, Object>> maxTime3 = tMerDataHisDao.queryForListMaxCreateDate(BaseUtils.map("data_name", "ALL_TRADE"));

        if (maxTime2 != null && maxTime2.size() > 0) {
            if (ObjectUtil.isNotEmpty(maxTime2.get(0).get("max_date"))) {
                String maxDate = String.valueOf(maxTime2.get(0).get("max_date"));
                if (batchDate.equals(maxDate)) {

                } else {
                    //获取最大日期的下一天
                    batchDate = DateUtils.format(DateUtils.addDay(DateUtils.parse(maxDate, "yyyyMMdd"), 1),
                            "yyyyMMdd");
                    //助农卸数记录
                    List<Map<String, Object>> znTimeList = tZnDataHisDao.queryForList(BaseUtils.map("create_date", batchDate));
                    if (znTimeList != null && znTimeList.size() > 0) {
                        String znTime = String.valueOf(znTimeList.get(0).get("create_date"));

                        if (batchDate.equals(znTime)) {
                            mergerZnData(batchDate);
                        }
                    }

                }
            }
        }
        if (maxTime3 != null && maxTime3.size() > 0) {
            if (ObjectUtil.isNotEmpty(maxTime3.get(0).get("max_date"))) {
                String maxDate = String.valueOf(maxTime3.get(0).get("max_date"));
                if (batchDate.equals(maxDate)) {

                } else {
                    //获取最大日期的下一天
                    batchDate = DateUtils.format(DateUtils.addDay(DateUtils.parse(maxDate, "yyyyMMdd"), 1),
                            "yyyyMMdd");
                    //交易卸数记录
                    List<Map<String, Object>> tranTimeList = tCpDataHisDao.queryForList(BaseUtils.map("create_date", batchDate));
                    if (tranTimeList != null && tranTimeList.size() > 0) {
                        String tranTime = String.valueOf(tranTimeList.get(0).get("create_date"));

                        if (batchDate.equals(tranTime)) {
                            mergerTranData(batchDate);
                        }
                    }

                }
            }
        }

    }


    private void mergerTranData(String batchDate) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mercht_cate_cd", "6051");
        paramMap.put("info_type_cd", "0200");
        paramMap.put("valid_cd", "0");
        //合并数据
        List<Map<String, Object>> resultMaps = tradeInfoDao.queryToMergerData(paramMap);

        if (resultMaps != null && resultMaps.size() > 0) {

            for (Map<String, Object> resultMap : resultMaps) {

                String procCd = String.valueOf(resultMap.get("proc_cd"));
                if (procCd.startsWith("00")) {
                    //消费
                    resultMap.put("tran_type", "111");
                } else if (procCd.startsWith("01")) {
                    //小额取款
                    resultMap.put("tran_type", "56");
                } else if (procCd.startsWith("46")) {
                    //汇款-转出
                    resultMap.put("tran_type", "58");
                } else if (procCd.startsWith("47")) {
                    //汇款-转入
                    resultMap.put("tran_type", "222");
                } else {
                    resultMap.put("tran_type", resultMap.get("proc_cd"));
                }
                String transDt = String.valueOf(resultMap.get("trans_dt"));
                transDt = transDt.replace("-", "");
                resultMap.put("trans_dt", transDt);
                resultMap.put("creat_data", DateUtils.getDate());
            }
            allTradeInfoDao.saveList(resultMaps);
        }

        //执行保存卸数记录表
        Map<String, Object> parms = new HashMap<>();
        parms.put("create_date", batchDate);
        parms.put("create_time", DateUtils.getDate("HHmmss"));
        parms.put("data_name", "ALL_TRADE");
        tMerDataHisDao.save(parms);
    }


    private void mergerZnData(String batchDate) {

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("stat", "1");
        //合并数据
        List<Map<String, Object>> resultMaps = cupstsDao.queryToMergerData(paramMap);

        if (resultMaps != null && resultMaps.size() > 0) {

            for (Map<String, Object> resultMap : resultMaps) {

                if (ObjectUtil.isNotEmpty(resultMap.get("site_no"))) {

                    List<Map<String, Object>> siteName = hSiteDao.querySiteName(
                            BaseUtils.map("site_no", resultMap.get("site_no"), "is_delete", "0", "approval_status", "2"));

                    if (siteName != null && siteName.size() > 0) {
                        resultMap.put("tran_addr", siteName.get(0).get("site_name"));

                    } else {
                        resultMap.put("tran_addr", " ");
                    }
                } else {
                    resultMap.put("tran_addr", " ");
                }

                if (ObjectUtil.isNotEmpty(resultMap.get("f004"))) {
                    BigDecimal f004 = BigDecimal.valueOf(Double.parseDouble(resultMap.get("f004").toString()));
                    BigDecimal amt = hnmCommService.dealVal(f004.divide(BigDecimal.valueOf(100)));
                    resultMap.put("tran_amt", amt);
                } else {
                    resultMap.put("tran_amt", 0.00);
                }
                resultMap.put("src_sys_cd", "CUPSTS");
                resultMap.put("creat_data", DateUtils.getDate());

            }
        }

        //执行保存卸数记录表
        Map<String, Object> parms = new HashMap<>();
        parms.put("create_date", batchDate);
        parms.put("create_time", DateUtils.getDate("HHmmss"));
        parms.put("data_name", "CUPSTS");
        tMerDataHisDao.save(parms);
    }
}

