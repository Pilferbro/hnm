package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.CupstsDao;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@Service
public class Tvouch007Service extends TXBaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private CupstsDao cupstsDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        Map<String, Object> pridMap = BaseUtils.map("56", "小额取款", "57", "现金汇款", "58",
                "转账汇款", "60", "活转定", "65", "定转活");
        String tableName = "";

        try {
            //保存文件所需参数
            String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR", "/home/weblogic/hnm/info");
            String dirPath = sysPath + "/exportTable";
            String excelPath = sysPath + "/exportTable/excel.xls";
            String excelPathRelative = "exportTable/excel.xls";
            File dir = new File(dirPath);
            File excel;
            try {
                excel = new File(excelPath);
            } catch (Exception e) {
                logger.info("Tvouch007Service导出表时出错001" + e);
                throw new BusinessException("Tvouch007Service", "导出表时出错001");
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
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

                Map<String, Object> params = new HashMap<>();

                if (p.containsKey("site_no") && ObjectUtil.isNotEmpty(p.get("site_no"))) {
                    params.put("site_no", p.get("site_no"));
                }
                if (p.containsKey("account_id") && ObjectUtil.isNotEmpty(p.get("account_id"))) {
                    params.put("account_id", p.get("account_id"));
                }

                //处理交易开始时间及结束时间
                if (p.containsKey("start_date") && ObjectUtil.isNotEmpty(p.get("start_date"))) {
                    Date startDate = DateUtils.parse(p.get("start_date").toString(), "yyyy-MM-dd");
                    params.put("start_date", DateUtils.getDate(startDate, "yyyyMMdd"));

                }
                if (p.containsKey("end_date") && ObjectUtil.isNotEmpty(p.get("end_date"))) {
                    Date endDate = DateUtils.parse(p.get("end_date").toString(), "yyyy-MM-dd");
                    params.put("end_date", DateUtils.getDate(endDate, "yyyyMMdd"));
                }

                //校验角色   总行级可以看所有
                String userId = MfpContextHolder.getLoginId();
                int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
                if (userRoleLevel == 0) {
                    logger.error("登陆人员" + userId + "未配置角色");
                    throw new BusinessException("Tvouch001ServiceException", "登陆人员未配置角色");
                }

                if (userRoleLevel != 1) {
                    String branchids = hnmCommService.getUserBranchids();
                    params.put("allOrgids", branchids);
                }

                if (userRoleLevel == 4) {
                    //非管理员只能查看自己的
                    params.put("mgr_id", userId);
                }

                if (ObjectUtil.isNotEmpty(p.get("orgid"))) {
                    params.put("orgids", hnmCommService.getUserBranchidsBybranch(p.get("orgid").toString()));
                }

                if (ObjectUtil.isNotEmpty(p.get("bankid"))) {
                    params.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
                }

                params.put("is_delete", "0");
                params.put("approval_status", "2");
                params.put("stat", "1");
                //56 小额取款  57现金汇款 58转账汇款 60活转定 65定转活
                params.put("prids", "'56','57','58','60','65'");
                List<Map<String, Object>> result;

                if (p.containsKey("activeName") && "voucher1".equals(p.get("activeName"))) {
                    result = cupstsDao.queryForExport1(delkong(params));
                    tableName = "未处理助农交易报表";
                } else {
                    result = cupstsDao.queryForExport2(delkong(params));
                    tableName = "已处理助农交易报表";
                }
                int i = 1;
                Label labeltitle;
                labeltitle = new Label(0, 0, "序号");
                sheet.addCell(labeltitle);
                labeltitle = new Label(1, 0, "落地支行");
                sheet.addCell(labeltitle);
                labeltitle = new Label(2, 0, "服务点编号");
                sheet.addCell(labeltitle);
                labeltitle = new Label(3, 0, "服务点名称");
                sheet.addCell(labeltitle);
                labeltitle = new Label(4, 0, "交易时间");
                sheet.addCell(labeltitle);
                labeltitle = new Label(5, 0, "凭证号");
                sheet.addCell(labeltitle);
                labeltitle = new Label(6, 0, "交易类型");
                sheet.addCell(labeltitle);
                labeltitle = new Label(7, 0, "储蓄品种");
                sheet.addCell(labeltitle);
                labeltitle = new Label(8, 0, "卡号");
                sheet.addCell(labeltitle);
                labeltitle = new Label(9, 0, "金额");
                sheet.addCell(labeltitle);
                labeltitle = new Label(10, 0, "登记结果");
                sheet.addCell(labeltitle);
                labeltitle = new Label(11, 0, "处理人");
                sheet.addCell(labeltitle);

                for (Map<String, Object> map : result) {

                    labeltitle = new Label(0, i, valueOf(i));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, i, valueOf(map.get("branch_name")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, i, valueOf(map.get("site_no")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, i, valueOf(map.get("site_name")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, i, valueOf(map.get("mask_from_v2")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, i, valueOf(map.get("f011")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, i, valueOf(pridMap.get(map.get("prid").toString())));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, i, "");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(8, i, mosaicAlipayName(valueOf(map.get("f002"))));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(9, i, valueOf(dealNum(map.get("f004"))));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(10, i, valueOf(map.get("voucher_result")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(11, i, valueOf(map.get("creator_name")));
                    sheet.addCell(labeltitle);
                    i++;
                }
                book.write();
                book.close();
                os.flush();
                os.close();
                //异步保存导出流水

            } catch (RowsExceededException e) {
                logger.info("Tvouch007Service导出表时出错002" + e);
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                logger.info("Tvouch007Service导出表时出错003" + e);
                e.printStackTrace();
            } catch (WriteException e) {
                logger.info("Tvouch007Service导出表时出错004" + e);
                e.printStackTrace();
            } catch (IOException e) {
                logger.info("Tvouch007Service导出表时出错005" + e);
                e.printStackTrace();
            }

            Map<String, Object> retMap = new HashMap<>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", tableName + dayString + ".xls");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("Tvouch007ServiceException", "网络异常");
        }
    }

    public String valueOf(Object obj) {

        return obj == null ? "" : obj.toString();
    }

    private String mosaicAlipayName(String withdrawName) {
        //银行卡号
        String regex = "(\\w{6})(.*)(\\w{4})";
        Matcher m = Pattern.compile(regex).matcher(withdrawName);
        if (m.find()) {
            String rep = m.group(2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rep.length(); i++) {
                sb.append("*");
            }
            return withdrawName.replaceAll(rep, sb.toString());
        }

        return withdrawName.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2");
    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : data.keySet()) {
            if (data.get(key) != null && !"".equals(data.get(key))) {

                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }

    private BigDecimal dealNum(Object str) {

        BigDecimal f004 = BigDecimal.valueOf(Double.parseDouble(str.toString()));
        return hnmCommService.dealVal(f004.divide(BigDecimal.valueOf(100)));
    }
}
