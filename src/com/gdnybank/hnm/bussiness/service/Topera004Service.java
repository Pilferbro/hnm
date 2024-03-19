package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.TOperateDao;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 导出合规操作规则
 *
 * @author: phl
 */
@Service
public class Topera004Service extends TXBaseService {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TOperateDao tOperateDao;
    @Autowired
    SysParamService sysParamService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

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
                logger.info("Topera004Service导出表时出错001" + e);
                throw new BusinessException("Topera004ServiceException", "导出表时出错001");
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

                List<Map<String, Object>> operateList = tOperateDao.queryForList(p);
                //按规则编号排序
                operateList.sort(Comparator.comparing(o -> (Integer.parseInt(o.get("operate_no").toString()) )));
                int i = 1;
                Label labeltitle;
                labeltitle = new Label(0, 0, "规则编号");
                sheet.addCell(labeltitle);
                labeltitle = new Label(1, 0, "规则名称");
                sheet.addCell(labeltitle);
                labeltitle = new Label(2, 0, "触发时间");
                sheet.addCell(labeltitle);
                labeltitle = new Label(3, 0, "触发频率");
                sheet.addCell(labeltitle);
                labeltitle = new Label(4, 0, "触发条件");
                sheet.addCell(labeltitle);
                labeltitle = new Label(5, 0, "整改范围");
                sheet.addCell(labeltitle);
                labeltitle = new Label(6, 0, "风险等级");
                sheet.addCell(labeltitle);
                labeltitle = new Label(7, 0, "整改时间");
                sheet.addCell(labeltitle);
                labeltitle = new Label(8, 0, "问题描述");
                sheet.addCell(labeltitle);
                labeltitle = new Label(9, 0, "整改要求");
                sheet.addCell(labeltitle);
                labeltitle = new Label(10, 0, "处理人");
                sheet.addCell(labeltitle);
                labeltitle = new Label(11, 0, "是否启用");
                sheet.addCell(labeltitle);
                labeltitle = new Label(12, 0, "备注");
                sheet.addCell(labeltitle);

                for (Map<String, Object> map : operateList) {

                    labeltitle = new Label(0, i, valueOf(map.get("operate_no")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, i, valueOf(map.get("operate_name")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, i, valueOf(map.get("operate_time")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, i, valueOf(map.get("operate_rate")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, i, valueOf(map.get("operate_condition")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, i, convertArea(valueOf(map.get("operate_area"))));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, i, convertStatus(valueOf(map.get("operate_risk"))));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, i, valueOf(map.get("operate_day")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(8, i, valueOf(map.get("operate_content")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(9, i, valueOf(map.get("operate_requirement")));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(10, i, valueOfOperaNo(valueOf(map.get("operate_operno"))));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(11, i, valueOfUseUp(valueOf(map.get("use_up"))));
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(12, i, valueOf(map.get("remarks")));
                    sheet.addCell(labeltitle);
                    i++;
                }
                book.write();
                book.close();
                os.flush();
                os.close();
                //异步保存导出流水

            } catch (RowsExceededException e) {
                logger.info("Topera004Service导出表时出错002" + e);
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                logger.info("Topera004Service导出表时出错003" + e);
                e.printStackTrace();
            } catch (WriteException e) {
                logger.info("Topera004Service导出表时出错004" + e);
                e.printStackTrace();
            } catch (IOException e) {
                logger.info("Topera004Service导出表时出错005" + e);
                e.printStackTrace();
            }

            Map<String, Object> retMap = new HashMap<>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "合规操作规则表" + dayString + ".xls");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("Topera004ServiceException", "网络异常");
        }
    }

    public String valueOf(Object obj) {

        return obj == null ? "" : obj.toString();
    }

    public String valueOfOperaNo(String str) {

        return "0".equals(str) ? "分行管理员" : "站长对口惠农客户经理";
    }

    public String valueOfUseUp(String str) {

        return "0".equals(str) ? "否" : "是";
    }

    public String convertStatus(String nmb) {
        String str = "";
        String STR_0 = "低";
        String STR_1 = "中";
        String STR_2 = "高";

        switch (nmb) {
            case "0":
                str = STR_0;
                break;
            case "1":
                str = STR_1;
                break;
            case "2":
                str = STR_2;
                break;
        }
        return str;
    }

    public String convertArea(String nmb) {
        StringBuilder str = new StringBuilder();
        String STR_0 = "试营业站点";
        String STR_1 = "试营业(已申请pos)站点";
        String STR_2 = "开业站点";
        String STR_3 = "退出站点";
        String[] split = nmb.split(",");
        for (String s : split) {
            switch (s) {
                case "0":
                    str.append(STR_0).append(";");
                    break;
                case "1":
                    str.append(STR_1).append(";");
                    break;
                case "2":
                    str.append(STR_2).append(";");
                    break;
                case "3":
                    str.append(STR_3).append(";");
                    break;
            }
        }

        return str.substring(0,str.length()-1);
    }
}
