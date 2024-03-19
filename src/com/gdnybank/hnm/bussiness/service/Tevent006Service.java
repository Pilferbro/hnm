package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HEventDao;
import com.gdnybank.hnm.pub.dao.HPatrolDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
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
 * @description:  导出团队管理数据
 * @author: wxm
 */
@Service
public class Tevent006Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tevent006Service.class);

    @Autowired
    HEventDao eventDao;

    @Autowired
    private HnmCommService hnmCommService;

    @Autowired
    SysParamService sysParamService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            //保存文件所需参数
            String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR","/home/weblogic/hnm/info");
            String dirPath = sysPath + "/exportTable";
            String excelPath = sysPath + "/exportTable/excel_event.xls";
            String excelPathRelative = "exportTable/excel_event.xls";
            File dir = new File(dirPath);
            File excel = null;
            try {
                excel = new File(excelPath);
            } catch (Exception e) {
                logger.info("Tevent006Service导出表时出错001"+e);
                throw new BusinessException("Tevent006Service","导出表时出错001");
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
                    labeltitle = new Label(0, 0, "服务点名称");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, 0, "客户经理");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, 0, "活动日期");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, 0, "活动方式");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, 0, "活动效果");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, 0, "活动主题");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, 0, "现场是否开卡");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, 0, "活动参与人数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(8, 0, "其他");
                    sheet.addCell(labeltitle);

                    int i = 1;

                    //校验角色   总行级可以看所有
                    String userId = MfpContextHolder.getLoginId();
                    int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
                    if(userRoleLevel==0){
                        logger.error("登陆人员"+userId+"未配置角色");
                        throw new BusinessException("Tevent006ServiceException", "登陆人员未配置角色");
                    }

                    if(userRoleLevel != 1){
                        String branchids = hnmCommService.getUserBranchids();
                        p.put("orgids", branchids);
                    }

                    if(userRoleLevel == 4){
                        //非管理员只能查看自己的
                        p.put("account_id",userId);
                    }
                    //分页信息
                    p.put("number",999999999);
                    setPageInfo(p);
                    List<Map<String , Object>> resultList = eventDao.queryForListByPage(delkong(p));
                    for(Map<String, Object> backMap : resultList){
                        //服务点名称
                        labeltitle = new Label(0, i, (String) backMap.get("site_name"));
                        sheet.addCell(labeltitle);
                        //客户经理
                        labeltitle = new Label(1, i, (String) backMap.get("account_name"));
                        sheet.addCell(labeltitle);
                        //活动日期
                        labeltitle = new Label(2, i, (String) backMap.get("event_date"));
                        sheet.addCell(labeltitle);
                        //活动方式
                        String event_way = backMap.get("event_way").toString();
                        if("0".equals(event_way)){
                            event_way = "线上活动";
                        }else if("1".equals(event_way)){
                            event_way = "线下活动";
                        }else if("2".equals(event_way)){
                            event_way = "其他";
                        }
                        labeltitle = new Label(3, i, event_way);
                        sheet.addCell(labeltitle);
                        //活动效果
                        labeltitle = new Label(4, i, (String) backMap.get("event_effect"));
                        sheet.addCell(labeltitle);
                        //活动主题
                        labeltitle = new Label(5, i, (String) backMap.get("event_name"));
                        sheet.addCell(labeltitle);
                        //现场是否开卡
                        labeltitle = new Label(6, i, backMap.get("is_card").equals("0")?"是":"否");
                        sheet.addCell(labeltitle);
                        //活动参与人数
                        labeltitle = new Label(7, i, (String) backMap.get("event_staff"));
                        sheet.addCell(labeltitle);
                        //其他
                        labeltitle = new Label(8, i, (String) backMap.get("event_other"));
                        sheet.addCell(labeltitle);
                        i++;
                    }
                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("Tevent006Service导出表时出错002"+e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("Tevent006Service导出表时出错003"+e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("Tevent006Service导出表时出错004"+e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Tevent006Service导出表时出错005"+e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<String, Object>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "活动管理信息"+dayString+".xls");
            retMap.put("status", "1");
            return retMap;
        }catch (Exception e){
            throw new BusinessException("Tevent006ServiceException", "网络异常");
        }

    }

    public Map<String,Object> delkong(Map<String,Object> data){
        Map<String,Object> dataMap=new HashMap<String , Object>();
        for (String key  : data.keySet()) {
            if(data.get(key)==null||"".equals(data.get(key))){

            }else{
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
    private void setPageInfo(Map<String, Object> p){
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page"))?p.get("page").toString():"0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number"))?p.get("number").toString():"10");
        PageInfo pageInfo=new PageInfo();
        pageInfo.setIDisplayStart(page*number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }

}
