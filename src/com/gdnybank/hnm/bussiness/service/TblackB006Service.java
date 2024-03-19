package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.gdnybank.hnm.pub.enums.RiskLevelEnum;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import com.sun.istack.Nullable;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 批量导出风险客户
 */
@Service
public class TblackB006Service extends TXBaseService {

    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private SysParamService sysParamService;


    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

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
                logger.info("TblackB006ServiceException导出表时出错001" + e);
                throw new BusinessException("TblackB006ServiceException", "导出表时出错001");
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
                    p.put("valid_flg","1");
                    List<Map<String, Object>> returnList = blklistCtrlDao.queryForList(p);

                    int i = 1;
                    Label labeltitle;
                    labeltitle = new Label(0, 0, "客户姓名");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, 0, "证件号码");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, 0, "账号");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, 0, "风险等级");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, 0, "控制开始日期");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, 0, "控制截至日期");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, 0, "来源");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, 0, "入库时间");
                    sheet.addCell(labeltitle);

                    for (Map<String, Object> map : returnList) {

                        labeltitle = new Label(0, i, valueOf(map.get("cust_name")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, i, valueOf(map.get("cert_num")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, i, valueOf(map.get("acct_id")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, i, Objects.requireNonNull(RiskLevelEnum.findByItem(valueOf(map.get("risk_level")))).getValue());
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, i, valueOf(map.get("set_ctrl_dt")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, i, valueOf(map.get("closing_dt")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, i, convertStatus(valueOf(map.get("src"))));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, i, valueOf(map.get("create_time")));
                        sheet.addCell(labeltitle);

                        i++;
                    }
                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("TblackB006ServiceException导出表时出错002" + e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("TblackB006ServiceException导出表时出错003" + e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("TblackB006ServiceException导出表时出错004" + e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("TblackB006ServiceException导出表时出错005" + e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "风险客户报表" + dayString + ".xls");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("TblackB006ServiceException", "网络异常");
        }
    }

    public String convertStatus(String nmb) {
        String str;
        String STR_0 = "公安";
        String STR_1 = "核心系统";
        String STR_2 = "惠农系统";
        switch (nmb) {
            case "200501":
                str = STR_0;
                break;
            case "999999":
                str = STR_1;
                break;
            case "100001":
                str = STR_2;
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public String valueOf(Object obj) {

        return obj == null ? "" : obj.toString();
    }
}
