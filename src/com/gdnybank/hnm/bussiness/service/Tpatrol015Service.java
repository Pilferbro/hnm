package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HProjectClassDao;
import com.gdnybank.hnm.report.service.Treport018Service;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
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

@Transactional
@Service
public class Tpatrol015Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Treport018Service.class);
    @Autowired
    SysParamService sysParamService;
    @Autowired
    private Tpatrol008Service tpatrol008Service;
    @Autowired
    private HProjectClassDao hProjectClassDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        try {
            //保存文件所需参数
            String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR", "/home/weblogic/hnm/info");
            //String sysPath = "/Users/zjh/Desktop/";
            String dirPath = sysPath + "/exportTable";
            String excelPath = sysPath + "/exportTable/excel.xls";
            String excelPathRelative = "exportTable/excel.xls";
            File dir = new File(dirPath);
            File excel;
            try {
                excel = new File(excelPath);
            } catch (Exception e) {
                logger.info("Tpatrol015Service导出表时出错001" + e);
                throw new BusinessException("Tpatrol015Service", "导出表时出错001");
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
                    Map<String, Object> result = (Map<String, Object>) tpatrol008Service.doService(env, p);

                    List<Map<String, Object>> returnList = (List<Map<String, Object>>) result.get("resultList");

                    int i = 1;
                    Label labeltitle;
                    labeltitle = new Label(0, 0, "工单流水号");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, 0, "项目名称");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, 0, "整改机构或站点");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, 0, "问题描述");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, 0, "风险等级");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, 0, "整改要求");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, 0, "整改预计完成日期");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, 0, "问题整改人");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(8, 0, "整改状态");
                    sheet.addCell(labeltitle);

                    for (Map<String, Object> map : returnList) {

                        List<Map<String, Object>> maps = hProjectClassDao.queryForList(BaseUtils.map("pj_classify", map.get("pj_classify")));
                        if (maps != null && maps.size() > 0) {
                            map.put("project_name", maps.get(0).get("project_name"));
                        }
                        labeltitle = new Label(0, i, valueOf(map.get("id")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, i, valueOf(map.get("project_name")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, i, valueOf(map.get("site_name")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, i, valueOf(map.get("content_text")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, i, valueOf(map.get("risk_level")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, i, valueOf(map.get("requirement")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, i, valueOf(map.get("end_date")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, i, valueOf(map.get("name")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(8, i, convertStatus(valueOf(map.get("status"))));
                        sheet.addCell(labeltitle);
                        i++;
                    }
                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("Tpatrol015Service导出表时出错002" + e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("Tpatrol015Service导出表时出错003" + e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("Tpatrol015Service导出表时出错004" + e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Tpatrol015Service导出表时出错005" + e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "风险信息报表" + dayString + ".xls");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("Tpatrol015ServiceException", "网络异常");
        }
    }

    public String convertStatus(String nmb) {
        String str = "";
        String STR_0 = "未整改";
        String STR_1 = "已整改";
        String STR_2 = "无需整改";
        String STR_3 = "无法整改";
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
            case "3":
                str = STR_3;
                break;
        }
        return str;
    }

    public String valueOf(Object obj) {

        return obj == null ? "" : obj.toString();
    }
}
