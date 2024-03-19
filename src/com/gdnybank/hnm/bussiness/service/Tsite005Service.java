package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 导出站点管理数据
 * @author: zjh
 */
@Service
public class Tsite005Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite005Service.class);

    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    SysParamService sysParamService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try {
            //保存文件所需参数
            String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR", "/home/weblogic/hnm/info");
            //String sysPath = "/Users/zjh/Desktop/";
            String dirPath = sysPath + "/exportTable";
            String excelPath = sysPath + "/exportTable/excel.xls";
            String excelPathRelative = "exportTable/excel.xls";
            File dir = new File(dirPath);
            File excel = null;
            try {
                excel = new File(excelPath);
            } catch (Exception e) {
                logger.info("Tsite005Service导出表时出错001" + e);
                throw new BusinessException("Tsite005Service", "导出表时出错001");
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!("".equals(dirPath)) && excel != null) {
                if (excel.exists()) {
                    excel.delete();
                }
                // 打开输出流并将输入流输入
                try {
                    FileOutputStream os = new FileOutputStream(excelPath);
                    WritableWorkbook book = Workbook.createWorkbook(os);// 根据输出流创建一个文件
                    WritableSheet sheet = book.createSheet("table1", 0); // 设置第一个表名
                    SheetSettings ssSettings = sheet.getSettings();
                    ssSettings.setVerticalFreeze(1);
                    Label labeltitle = null;
                    labeltitle = new Label(0, 0, "站点编号");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, 0, "站点名称");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, 0, "所属机构");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, 0, "落地支行");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, 0, "管辖客户经理");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, 0, "服务点状态");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, 0, "站点来源");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, 0, "站长姓名");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(8, 0, "签约时间");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(9, 0, "预计开业时间");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(10, 0, "资产余额（元）");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(11, 0, "资产年日均（元）");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(12, 0, "存款余额（元）");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(13, 0, "存款年日均（元）");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(14, 0, "所有客户数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(15, 0, "高净值客户数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(16, 0, "价值客户数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(17, 0, "有效客户数");
                    sheet.addCell(labeltitle);
                    if (p.containsKey("source")) {
                        labeltitle = new Label(18, 0, "建档审批完成时间");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(19, 0, "开业审批完成时间");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(20, 0, "退出审批完成时间");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(21, 0, "站长联系电话");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(22, 0, "站点地址");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(23, 0, "定位经度");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(24, 0, "定位纬度");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(25, 0, "终端编号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(26, 0, "银联商户编码");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(27, 0, "银联终端编号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(28, 0, "银联终端序列号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(29, 0, "终端SN号");
                        sheet.addCell(labeltitle);
                    }
                    int i = 1;
                    Map<String, Object> p2 = new HashMap<String, Object>();

                    Object site_no = p.get("site_no");
                    if (ObjectUtil.isNotEmpty(site_no)) {
                        p2.put("site_no", site_no);
                    }
                    Object siteName = p.get("site_name");
                    if (ObjectUtil.isNotEmpty(siteName)) {
                        p2.put("site_name", siteName);
                    }
                    Object mgrId = p.get("mgr_id");
                    if (ObjectUtil.isNotEmpty(mgrId)) {
                        p2.put("mgr_id", mgrId);
                    }
                    Object masterName = p.get("master_name");
                    if (ObjectUtil.isNotEmpty(masterName)) {
                        p2.put("master_name", masterName);
                    }
                    Object q_status = p.get("status");
                    if (ObjectUtil.isNotEmpty(q_status)) {
                        p2.put("status", q_status);
                    }
                    //处理申请开始时间及结束时间
                    String approval_types = "'" + ApprovalTypeEnum.TYPE004.getApprovalType() + "','" + ApprovalTypeEnum.TYPE006.getApprovalType() + "','" + ApprovalTypeEnum.TYPE007.getApprovalType() + "','" + ApprovalTypeEnum.TYPE022.getApprovalType() + "'";
                    if (p.containsKey("start_date") && ObjectUtil.isNotEmpty(p.get("start_date"))) {
                        p2.put("start_date", p.get("start_date") + " 00:00:00");
                        p2.put("approval_types", approval_types);
                    }
                    if (p.containsKey("end_date") && ObjectUtil.isNotEmpty(p.get("end_date"))) {
                        p2.put("end_date", p.get("end_date") + " 24:59:59");
                        p2.put("approval_types", approval_types);
                    }

                    //校验角色   总行级可以看所有
                    String userId = MfpContextHolder.getLoginId();
                    int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
                    if (userRoleLevel == 0) {
                        logger.error("登陆人员" + userId + "未配置角色");
                        throw new BusinessException("Tstaff004ServiceException", "登陆人员未配置角色");
                    }

                    if (userRoleLevel != 1) {
                        String branchids = hnmCommService.getUserBranchids();
                        p2.put("allOrgids", branchids);
                    }
                    if (userRoleLevel == 4) {
                        //非管理员只能查看自己的
                        p2.put("mgr_id", userId);
                    }

                    if (ObjectUtil.isNotEmpty(p.get("orgid"))) {
                        p2.put("orgids", hnmCommService.getUserBranchidsBybranch(p.get("orgid").toString()));
                    }

                    if (ObjectUtil.isNotEmpty(p.get("bankid"))) {
                        p2.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
                    }

                    p2.put("is_delete", 0);
                    p2.put("approval_status", "2");
                    /*if(p2.isEmpty()){
                        return new ArrayList<Map<String , Object>>();
                    }*/
                    List<Map<String, Object>> backList = siteDao.query(p2);
                    for (Map<String, Object> backMap : backList) {
                        List<Map<String, Object>> hisList = siteDao.queryForHisList(BaseUtils.map("site_no", backMap.get("site_no"), "approval_status", "2", "approval_type", "'004','007','008'"));
                        dealList(backMap, hisList);
                        //资产余额
                        BigDecimal amt1 = BigDecimal.ZERO;
                        //资产日均
                        BigDecimal amt2 = BigDecimal.ZERO;
                        //存款余额
                        BigDecimal amt3 = BigDecimal.ZERO;
                        //存款日均
                        BigDecimal amt4 = BigDecimal.ZERO;

                        //所有客户数
                        int cus_num1 = 0;
                        //高净值
                        int cus_num2 = 0;
                        //价值客户
                        int cus_num3 = 0;
                        //有效户
                        int cus_num4 = 0;

                        String siteIds = "'" + backMap.get("site_no") + "'";

                        // 根据服务点查询
                        Map<String, Object> parms = new HashMap<>();
                        parms.put("siteIds", siteIds);
                        //获取当前日期
                        Date date = new Date();

                        //查询昨天的数据
                        date = DateUtils.addDay(date, -1);

                        //获取当前月份
                        String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                        parms.put("acctday", yyyyMM);
                        //获取当前日期
                        int day = DateUtil.dayOfMonth(date);
                        String day_str = String.valueOf(day);
                        if (day_str.length() == 1) {
                            day_str = "0" + day_str;
                        }

                        //获取当年日期在今年的天数
                        int dayOfYear = DateUtil.dayOfYear(date);

                        String onlbal = "onlbal" + day_str;
                        parms.put("onlbal", onlbal);

                        String quararg = "quaraccum" + day_str;
                        parms.put("quararg", quararg);

                        StringBuilder sq1 = new StringBuilder("yearaccum");

                        for (int n = day + 1; n <= 31; n++) {
                            String n_str = String.valueOf(n);
                            if (n_str.length() == 1) {
                                n_str = "0" + n_str;
                            }
                            sq1 = sq1.append("-onlbal").append(n_str);
                        }
                        parms.put("sq1", sq1.toString());
                        parms.put("dayOfYear", dayOfYear);
                        //查询资产信息
                        List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);

                        //查询存款信息
                        List<Map<String, Object>> list2 = hnmCommService.qureyCK(parms);

                        //查询客户信息
                        String nowdate = DateUtils.getDate(date, "yyyyMMdd");
                        parms.put("nowdate", nowdate);
                        List<Map<String, Object>> list3 = hnmCommService.qureyCus(parms);
                        cus_num1 = list3.size();

                        //统计数值
                        if (list1 != null && list1.size() > 0) {
                            for (Map<String, Object> dataMap : list1) {
                                if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                                    amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                }
                                if (ObjectUtil.isNotEmpty(dataMap.get("amt2"))) {
                                    amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                                }
                                if (ObjectUtil.isNotEmpty(dataMap.get("quararg"))) {
                                    double quarArg = Double.valueOf(dataMap.get("quararg").toString());
                                    if (quarArg >= 1000000) {
                                        cus_num2++;
                                    } else if (quarArg < 1000000 && quarArg >= 100000) {
                                        cus_num3++;
                                    } else if (quarArg < 100000 && quarArg >= 1000) {
                                        cus_num4++;
                                    }
                                }
                            }
                        }

                        if (list2 != null && list2.size() > 0) {
                            for (Map<String, Object> dataMap : list2) {
                                if (ObjectUtil.isNotEmpty(dataMap.get("amt1"))) {
                                    amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                }
                                if (ObjectUtil.isNotEmpty(dataMap.get("amt2"))) {
                                    amt4 = amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                                }
                            }
                        }

                        //业绩数据
                        backMap.put("amt1", hnmCommService.dealVal(amt1));
                        backMap.put("amt2", hnmCommService.dealVal(amt2));
                        backMap.put("amt3", hnmCommService.dealVal(amt3));
                        backMap.put("amt4", hnmCommService.dealVal(amt4));

                        //客户数据
                        backMap.put("cus_num1", cus_num1);
                        backMap.put("cus_num2", cus_num2);
                        backMap.put("cus_num3", cus_num3);
                        backMap.put("cus_num4", cus_num4);

                        labeltitle = new Label(0, i, (String) backMap.get("site_no"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, i, (String) backMap.get("site_name"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, i, (String) backMap.get("org_name"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, i, (String) backMap.get("branch_name"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, i, (String) backMap.get("mgr_name"));
                        sheet.addCell(labeltitle);
                        String status = "";
                        if (ObjectUtil.isNotEmpty(backMap.get("status"))) {
                            status = (String) backMap.get("status");
                            if ("0".equals(status)) {
                                status = "试营业";
                            } else if ("1".equals(status)) {
                                status = "试营业（已申请pos）";
                            } else if ("2".equals(status)) {
                                status = "已开业";
                            } else {
                                status = "已退出";
                            }
                        }
                        labeltitle = new Label(5, i, status);
                        sheet.addCell(labeltitle);
                        String site_source = "";
                        if (ObjectUtil.isNotEmpty(backMap.get("site_source"))) {
                            site_source = (String) backMap.get("site_source");
                            if ("0".equals(site_source)) {
                                site_source = "自拓";
                            } else if ("1".equals(site_source)) {
                                site_source = "转介绍";
                            } else {
                                site_source = "其他";
                            }
                        }
                        labeltitle = new Label(6, i, site_source);
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, i, (String) backMap.get("master_name"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(8, i, (String) backMap.get("contract_date"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(9, i, (String) backMap.get("open_date"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(10, i, String.valueOf(backMap.get("amt1")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(11, i, String.valueOf(backMap.get("amt2")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(12, i, String.valueOf(backMap.get("amt3")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(13, i, String.valueOf(backMap.get("amt4")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(14, i, String.valueOf(backMap.get("cus_num1")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(15, i, String.valueOf(backMap.get("cus_num2")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(16, i, String.valueOf(backMap.get("cus_num3")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(17, i, String.valueOf(backMap.get("cus_num4")));
                        sheet.addCell(labeltitle);
                        if (p.containsKey("source")) {
                            labeltitle = new Label(18, i, valueOf(backMap.get("build_time")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(19, i, valueOf(backMap.get("start_time")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(20, i, valueOf(backMap.get("quit_time")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(21, i, valueOf(backMap.get("master_tel")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(22, i, valueOf(backMap.get("site_adr")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(23, i, valueOf(backMap.get("lng")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(24, i, valueOf(backMap.get("lat")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(25, i, valueOf(backMap.get("terminal_no")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(26, i, valueOf(backMap.get("merchant_num")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(27, i, valueOf(backMap.get("stb_id")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(28, i, valueOf(backMap.get("pos_sn")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(29, i, valueOf(backMap.get("terminal_sn")));
                            sheet.addCell(labeltitle);
                        }
                        i++;
                    }
                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("Tsite005Service导出表时出错002" + e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("Tsite005Service导出表时出错003" + e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("Tsite005Service导出表时出错004" + e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Tsite005Service导出表时出错005" + e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<String, Object>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "站点管理信息" + dayString + ".xls");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("Tstaff005ServiceException", "网络异常");
        }
    }

    private void dealList(Map<String, Object> backMap, List<Map<String, Object>> hisList) {
        if (hisList != null && hisList.size() > 0) {
            for (Map<String, Object> map : hisList) {
                if (map.get("approval_type").equals(ApprovalTypeEnum.TYPE004.getApprovalType())) {
                    backMap.put("build_time", map.get("update_time"));
                } else if (map.get("approval_type").equals(ApprovalTypeEnum.TYPE007.getApprovalType())) {
                    backMap.put("start_time", map.get("update_time"));
                } else if (map.get("approval_type").equals(ApprovalTypeEnum.TYPE008.getApprovalType())) {
                    backMap.put("quit_time", map.get("update_time"));
                }
            }
        }
    }

    public String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}
