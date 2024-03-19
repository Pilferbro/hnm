package com.gdnybank.hnm.report.service;

import com.nantian.mfp.framework.err.BusinessException;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 导出资产业绩报表-客户纬度
 * @author: wxm
 */
@Service
public class Treport018Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Treport018Service.class);

    @Autowired
    SysParamService sysParamService;

    @Autowired
    private Treport017Service treport017Service;

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
                logger.info("Treport018Service导出表时出错001" + e);
                throw new BusinessException("Treport018Service", "导出表时出错001");
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

                    p.put("export", 1);
                    Map<String, Object> result = (Map<String, Object>) treport017Service.doService(env, p);

                    List<Map<String, Object>> returnList = (List<Map<String, Object>>) result.get("returnList");

                    if ("1".equals(p.get("query_type"))) {
                        int i = 1;
                        Label labeltitle = null;
                        labeltitle = new Label(0, 0, "客户姓名");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, 0, "客户号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, 0, "电话");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, 0, "活期余额");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, 0, "定期余额");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, 0, "AUM");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, 0, "维护机构");
                        sheet.addCell(labeltitle);

                        for (Map<String, Object> map : returnList) {

                            labeltitle = new Label(0, i, String.valueOf(map.get("cltname")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(1, i, String.valueOf(map.get("cltnbr")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(2, i, String.valueOf(map.get("mstcon")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(3, i, String.valueOf(map.get("currents")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(4, i, String.valueOf(map.get("termly")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(5, i, String.valueOf(map.get("aum")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(6, i, String.valueOf(map.get("branch_name")));
                            sheet.addCell(labeltitle);
                            i++;
                        }
                    } else if ("2".equals(p.get("query_type"))) {
                        int i = 1;
                        Label labeltitle = null;
                        labeltitle = new Label(0, 0, "卡号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, 0, "开户日期");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, 0, "活期余额");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, 0, "定期余额");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, 0, "AUM");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, 0, "开户机构");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, 0, "服务点名称");
                        sheet.addCell(labeltitle);

                        for (Map<String, Object> map : returnList) {

                            labeltitle = new Label(0, i, String.valueOf(map.get("richnbr")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(1, i, String.valueOf(map.get("opendate")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(2, i, String.valueOf(map.get("currents")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(3, i, String.valueOf(map.get("termly")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(4, i, String.valueOf(map.get("aum")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(5, i, String.valueOf(map.get("branch_name")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(6, i, String.valueOf(map.get("site_name")));
                            sheet.addCell(labeltitle);
                            i++;
                        }
                    }
                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("Treport018Service导出表时出错002" + e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("Treport018Service导出表时出错003" + e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("Treport018Service导出表时出错004" + e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Treport018Service导出表时出错005" + e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<String, Object>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "客户明细报表" + dayString + ".xls");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("Treport018ServiceException", "网络异常");
        }

    }
}
