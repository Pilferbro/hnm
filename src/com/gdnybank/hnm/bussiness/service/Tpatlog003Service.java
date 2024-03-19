package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.gdnybank.hnm.pub.dao.HPatrolDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogDao;
import com.gdnybank.hnm.report.service.Treport018Service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/*
* 打印巡查条款
*/
@Service
@Transactional
public class Tpatlog003Service extends TXBaseService {
    private static final Logger logger = LoggerFactory.getLogger(Treport018Service.class);
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private HPatrolLogDao patrolLogDao;
    @Autowired
    private HPatrolDao patrolDao;
    @Autowired
    private HPatrolLogContentDao patrolLogContentDao;
    @Autowired
    private TmarkS002Service mark;
    @Autowired
    private HFileInfoDao fileInfoDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try {
            if (ObjectUtil.isEmpty(p.get("patrol_id"))) {
                throw new BusinessException("Tpatlog003ServiceException", "patrol_id为必传字段");
            }
            //保存文件所需参数
            String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR", "/home/weblogic/hnm/info");
            //String sysPath = "/Users/zjh/Desktop/";
            String dirPath = sysPath + "/exportTable";
            String pdfPath = sysPath + "/exportTable/pdf.pdf";
            String excelPathRelative = "exportTable/pdf.pdf";
            File dir = new File(dirPath);
            File pdf;
            try {
                pdf = new File(pdfPath);
            } catch (Exception e) {
                logger.info("Tpatlog003Service" + e);
                throw new BusinessException("Tpatlog003Service", "导出表时出错001");
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!("".equals(dirPath)) && pdf != null) {
                if (pdf.exists()) {
                    pdf.delete();
                }
                // 打开输出流并将输入流输入
                try {
                    FileOutputStream os = new FileOutputStream(pdfPath);
                    // 创建一个文档（默认大小A4，边距36, 36, 36, 36）
                    Document document = new Document(PageSize.A4, 0, 0, 30, 30);
                    document.setPageCount(2);
                    PdfWriter writer = PdfWriter.getInstance(document, os);
                    //创建BaseFont对象，指明字体，编码方式,是否嵌入
//                    BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, false);
                    BaseFont bf = BaseFont.createFont("com/gdnybank/hnm/pub/utils/document/simsun.ttc,0", BaseFont.IDENTITY_H, false);
                    //创建Font对象，将基础字体对象，字体大小，字体风格
                    Font font = new Font(bf, 9, Font.NORMAL);
                    Font font1 = new Font(bf, 13, Font.BOLD);
                    Font font2 = new Font(bf, 10, Font.BOLD);

                    //查询巡查条款结果
                    Map<String, Object> patrolLogMap = dealPatrolLog(patrolLogDao.queryPatrolResult(delkong(p)));
                    Map<String, Object> patrolMap = dealPatrol(patrolDao.queryforExport(delkong(p)));
                    Map<String, Object> contentlMap = dealContent(patrolLogContentDao.queryContenst(delkong(p)));


                    List<Map<String, Object>> photoList;
                    List<String> sign001List = null, sign002List = null;
                    Map<String, Object> date = dealTime(valueOf(patrolMap.get("inspect_time")).substring(0,10));
                    if (patrolLogMap.get("patrol_photo") != null && !("".equals(patrolLogMap.get("patrol_photo")))) {
                        //获取签名图片
                        photoList = fileInfoDao.queryPhotoList(BaseUtils.map("ids", patrolLogMap.get("patrol_photo")));
//                        String time = valueOf(photoList.get(0).get("create_time"));
//                        date = dealTime(time);
                        //被检查人签名
                        sign001List = dealPhoto(photoList, "sign001");
                        //检查人签名
                        sign002List = dealPhoto(photoList, "sign002");
                    }

                    PdfPTable table = new PdfPTable(5);
                    table.setWidths(new int[]{1, 2, 7, 3, 3});
                    PdfPCell cell = new PdfPCell(new Paragraph("广东南粤银行普惠金融支付服务点巡检记录表", font1));
                    cell.setColspan(5);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    //设置最小单元格高度
                    cell.setMinimumHeight(30);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("服务点名称", font2));
                    cell.setColspan(2);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setMinimumHeight(10);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(valueOf(patrolMap.get("site_name")), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("服务点编号", font2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(valueOf(patrolMap.get("site_id")), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("站长姓名", font2));
                    cell.setColspan(2);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(valueOf(patrolMap.get("master_name")), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph("检查人", font2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(valueOf(patrolMap.get("name")), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("检查项目", font2));
                    cell.setRowspan(34);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    Map<String, Object> resultMap = (Map) mark.doService(env, p);
                    List<Map<String, Object>> markSheelList = (List<Map<String, Object>>) resultMap.get("markSheelList");

                    for (int i = 0; i < markSheelList.size(); i++) {
                        Map<String, Object> map = markSheelList.get(i);
                        String mark_sheel_id = map.get("mark_sheel_id").toString();
                        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");

                        cell = new PdfPCell(new Paragraph(map.get("content") + "情况", font2));
                        if ("8".equals(mark_sheel_id) || "9".equals(mark_sheel_id)) {
                            continue;
                        }
                        if ("1".equals(mark_sheel_id)) {
                            cell.setRowspan(list.size() + 1);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Paragraph("检查主要内容", font2));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Paragraph("检查结果", font2));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Paragraph("处理情况说明", font2));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                        } else {
                            cell.setRowspan(list.size());
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                        }

                        for (int j = 0; j < list.size(); j++) {
                            Map<String, Object> map1 = list.get(j);
                            String id = valueOf(map1.get("mark_sheel_id"));
                            cell = new PdfPCell(new Paragraph(map1.get("content").toString(), font));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setMinimumHeight(10);
                            table.addCell(cell);
                            cell = new PdfPCell(new Paragraph(valueOf(patrolLogMap.get(id)), font));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Paragraph("", font));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                        }
                    }
                    Paragraph elements = new Paragraph();
                    Chunk chunk1 = new Chunk("\n\n被检查人（站长）签名：", font);
                    elements.add(chunk1);
                    if (sign001List != null && sign001List.size() > 0) {
                        for (String s : sign001List) {
                            Image image = Image.getInstance(s);
                            image.scaleAbsolute(5,5);
                            image.scaleToFit(40, 40);
                            Chunk chunk = new Chunk(image, 3, -2);
                            elements.add(chunk);
                        }
                    }

                    Chunk chunk3 = new Chunk("\n\n\n日期：" + date.get("date"), font);

                    elements.add(chunk3);
                    cell = new PdfPCell(elements);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(5);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("服务点检查整体评价（检查人员填写）：", font2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setColspan(5);
                    cell.setMinimumHeight(18);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("\n       " + valueOf(patrolLogMap.get("80101")), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(23);
                    cell.setColspan(5);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("经办意见：", font2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setMinimumHeight(18);
                    cell.setColspan(5);
                    table.addCell(cell);

                    Paragraph elements1 = new Paragraph();
                    Chunk chunk4 = new Chunk("\n检查人意见：" + valueOf(contentlMap.get("content")), font);
                    elements1.add(chunk4);
                    Chunk chunk5 = new Chunk("\n\n\n检查人签名：", font);
                    elements1.add(chunk5);
                    if (sign002List != null && sign002List.size() > 0) {
                        for (String s : sign002List) {
                            Image image = Image.getInstance(s);
                            image.scaleAbsolute(5,5);
                            image.scaleToFit(40, 40);
                            Chunk chunk = new Chunk(image, 3, -2);
                            elements1.add(chunk);
                        }
                    }
                    Chunk chunk7 = new Chunk("\n\n\n日期：" + date.get("date"), font);
                    elements1.add(chunk7);
                    cell = new PdfPCell(elements1);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(5);
                    table.addCell(cell);

                    document.open();
                    document.add(table);
                    document.close();
                    writer.close();

                } catch (Exception e) {
                    logger.info("Tpatlog003Service导出表时出错005" + e);
                    e.printStackTrace();
                }
            }


            Map<String, Object> retMap = new HashMap<>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "巡检记录表" + dayString + ".pdf");
            retMap.put("status", "1");
            return retMap;
        } catch (Exception e) {
            throw new BusinessException("Tpatlog003ServiceException", "网络异常");
        }
    }

    private String valueOf(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    private Map<String, Object> dealTime(String time) {
        Map<String, Object> map = new HashMap<>();
        if (!"".equals(time)) {

            String year = time.substring(0, 4);
            String month = time.substring(5, 7);
            String day = time.substring(8, 10);

            if (month.startsWith("0")) {
                month = month.substring(1);
            }
            if (day.startsWith("0")) {
                day = day.substring(1);
            }
            map.put("date", year + "年  " + month + "月  " + day + "日");
        }
        return map;

    }

    private List<String> dealPhoto(List<Map<String, Object>> photoList, String sign) {
        List<String> reList = new ArrayList<>();
        if (photoList != null && photoList.size() > 0) {
            for (Map<String, Object> photoMap : photoList) {
                if (sign.equals(photoMap.get("code"))) {
                    reList.add(valueOf(photoMap.get("path")));
                }
            }
        }

        return reList;
    }

    private Map<String, Object> dealContent(List<Map<String, Object>> contents) {
        Map<String, Object> reMap = new HashMap<>();
        if (contents != null && contents.size() > 0) {
            String str = "";
            for (int i = 0; i < contents.size(); i++) {
                str += i + 1 + "、" + contents.get(i).get("content_text") + ";";
            }
            if (str.endsWith(";")) {
                str = str.substring(0, str.length() - 1) + "。";
            }
            reMap.put("content", str);
        }
        return reMap;
    }

    private Map<String, Object> dealPatrol(List<Map<String, Object>> list) {
        Map<String, Object> reMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            reMap = list.get(0);
        }
        return reMap;
    }

    private Map<String, Object> dealPatrolLog(List<Map<String, Object>> patrolLogList) {
        HashMap<String, Object> reMap = new HashMap<>();
        if (patrolLogList != null && patrolLogList.size() > 0) {
            for (Map<String, Object> map : patrolLogList) {
                String patrol_result = valueOf(map.get("patrol_result"));
                String result ;
                switch (patrol_result) {
                    case "0":
                        result = "是";
                        break;
                    case "1":
                        result = "否";
                        break;
                    case "2":
                        result = "未检查";
                        break;
                    default:
                        result = patrol_result;
                        break;
                }

                reMap.put(valueOf(map.get("patrol_content")), result);
            }
        }
        return reMap;
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
}
