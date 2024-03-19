package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HTeamDao;
import com.gdnybank.hnm.pub.dao.TtimpntDao;
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

/**
 * @description:  导出团队管理数据
 * @author: wxm
 */
@Service
public class Tteam006Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tteam006Service.class);

    @Autowired
    HTeamDao hTeamDao;

    @Autowired
    private HnmCommService hnmCommService;

    @Autowired
    SysParamService sysParamService;

    @Autowired
    private HSiteDao hSiteDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            //保存文件所需参数
            String sysPath = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR","/home/weblogic/hnm/info");
            //String sysPath = "/Users/zjh/Desktop/";
            String dirPath = sysPath + "/exportTable";
            String excelPath = sysPath + "/exportTable/excel.xls";
            String excelPathRelative = "exportTable/excel.xls";
            File dir = new File(dirPath);
            File excel = null;
            try {
                excel = new File(excelPath);
            } catch (Exception e) {
                logger.info("Tstaff004Service导出表时出错001"+e);
                throw new BusinessException("Tstaff004Service","导出表时出错001");
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
                    labeltitle = new Label(0, 0, "团队/片区编号");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(1, 0, "团队/片区名称");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(2, 0, "团队/片区类型");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(3, 0, "所属机构");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(4, 0, "成立日期");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(5, 0, "团队/片区负责人");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(6, 0, "团队人数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(7, 0, "团队数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(8, 0, "片区人数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(9, 0, "其他");
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
                    labeltitle = new Label(18, 0, "试营业服务点数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(29, 0, "试营业（已申请pos）服务点数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(20, 0, "已开业服务点数");
                    sheet.addCell(labeltitle);
                    labeltitle = new Label(21, 0, "已退出服务点数");
                    sheet.addCell(labeltitle);

                    int i = 1;
                    Map<String, Object> p2 = new HashMap<String, Object>();

                    add(p.get("branch_id"), p2,"branch_id");
                    add(p.get("branch_name"),p2,"branch_name");

                    Object team_flag = p.get("team_flag");
                    if(ObjectUtil.isNotEmpty(team_flag)){
                        p2.put("team_flag", team_flag);
                    }

                    //校验角色   总行级可以看所有
                    String userId = MfpContextHolder.getLoginId();
                    int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
                    if(userRoleLevel==0){
                        logger.error("登陆人员"+userId+"未配置角色");
                        throw new BusinessException("Tstaff004ServiceException", "登陆人员未配置角色");
                    }
                    if(userRoleLevel != 1){
                        String branchids = hnmCommService.getUserBranchids();
                        p2.put("branchids", branchids);
                    }

                    if(ObjectUtil.isNotEmpty(p.get("bankid"))){
                        p2.put("branchids", hnmCommService.getUserBranchidsBybranch(p.get("bankid").toString()));
                    }

                    p2.put("is_delete","0");
                    p2.put("approval_status","2");
                    /*if(p2.isEmpty()){
                        return new ArrayList<Map<String , Object>>();
                    }*/
                    List<Map<String, Object>> backList = hTeamDao.query(p2);
                    for(Map<String, Object> backMap : backList){
                        //试营业数
                        int site_num1 = 0;
                        //试营业（已申请pos数）
                        int site_num2 = 0;
                        //已开业数
                        int site_num3 = 0;
                        //已退出数
                        int site_num4 = 0;
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

                        // 根据团队号查询其下所有的服务点数据
                        // 先查询其下所有的机构
                        String orgids = hnmCommService.getUserBranchidsBybranch(backMap.get("branch_id").toString());
                        // 根据branchIds查询其下所有的服务点
                        List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));

                        if(siteList != null && siteList.size() > 0){
                            String siteIds = "";
                            for (Map<String, Object> site : siteList) {
                                siteIds+=",'"+site.get("site_no")+"'";

                                if("0".equals(site.get("status"))){
                                    site_num1 ++;
                                }
                                if("1".equals(site.get("status"))){
                                    site_num2 ++;
                                }
                                if("2".equals(site.get("status"))){
                                    site_num3 ++;
                                }
                                if("3".equals(site.get("status"))){
                                    site_num4 ++;
                                }
                            }
                            if(siteIds.startsWith(",")){
                                siteIds=siteIds.substring(1, siteIds.length());
                            }
                            // 根据服务点查询
                            Map<String , Object> parms = new HashMap<>();
                            parms.put("siteIds",siteIds);
                            //获取当前日期
                            Date date = new Date();

                            //查询昨天的数据
                            date = DateUtils.addDay(date,-1);

                            //获取当前月份
                            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
                            parms.put("acctday",yyyyMM);
                            //获取当前日期
                            int day = DateUtil.dayOfMonth(date);
                            String day_str = String.valueOf(day);
                            if(day_str.length() == 1){
                                day_str = "0"+day_str;
                            }
                            //获取当年日期在今年的天数
                            int dayOfYear = DateUtil.dayOfYear(date);

                            String onlbal = "onlbal"+day_str;
                            parms.put("onlbal",onlbal);

                            String quararg = "quaraccum"+day_str;
                            parms.put("quararg",quararg);

                            StringBuilder sq1 = new StringBuilder("yearaccum");

                            for(int n=day+1; n<=31; n++){
                                String n_str = String.valueOf(n);
                                if(n_str.length() == 1){
                                    n_str = "0"+n_str;
                                }
                                sq1 = sq1.append("-onlbal").append(n_str);
                            }
                            parms.put("sq1",sq1.toString());
                            parms.put("dayOfYear",dayOfYear);
                            //查询资产信息
                            List<Map<String, Object>> list1 = hnmCommService.qureyZC(parms);

                            //查询存款信息
                            List<Map<String, Object>> list2 = hnmCommService.qureyCK(parms);

                            //查询客户信息
                            String nowdate = DateUtils.getDate(date, "yyyyMMdd");
                            parms.put("nowdate",nowdate);
                            List<Map<String, Object>> list3 = hnmCommService.qureyCus(parms);
                            cus_num1 = list3.size();

                            //统计数值
                            if(list1 != null && list1.size() > 0){
                                for (Map<String, Object> dataMap : list1){
                                    if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
                                        amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                    }
                                    if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
                                        amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                                    }
                                    if(ObjectUtil.isNotEmpty(dataMap.get("quararg"))){
                                        double quarArg = Double.valueOf(dataMap.get("quararg").toString());
                                        if(quarArg >= 1000000){
                                            cus_num2 ++;
                                        }else if(quarArg < 1000000 && quarArg >= 100000){
                                            cus_num3 ++;
                                        }else if(quarArg < 100000 && quarArg >= 1000){
                                            cus_num4 ++;
                                        }
                                    }
                                }
                            }

                            if(list2 != null && list2.size() > 0){
                                for (Map<String, Object> dataMap : list2){
                                    if(ObjectUtil.isNotEmpty(dataMap.get("amt1"))){
                                        amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt1").toString())));
                                    }
                                    if(ObjectUtil.isNotEmpty(dataMap.get("amt2"))){
                                        amt4 = amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("amt2").toString())));
                                    }
                                }
                            }
                        }
                        //业绩数据
                        backMap.put("amt1",hnmCommService.dealVal(amt1));
                        backMap.put("amt2",hnmCommService.dealVal(amt2));
                        backMap.put("amt3",hnmCommService.dealVal(amt3));
                        backMap.put("amt4",hnmCommService.dealVal(amt4));
                        //客户数据
                        backMap.put("cus_num1",cus_num1);
                        backMap.put("cus_num2",cus_num2);
                        backMap.put("cus_num3",cus_num3);
                        backMap.put("cus_num4",cus_num4);
                        //拓展数据
                        backMap.put("site_num1",site_num1);
                        backMap.put("site_num2",site_num2);
                        backMap.put("site_num3",site_num3);
                        backMap.put("site_num4",site_num4);

                        labeltitle = new Label(0, i, (String) backMap.get("branch_id"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, i, (String) backMap.get("branch_name"));
                        sheet.addCell(labeltitle);

                        String teamFlag = "";
                        if(ObjectUtil.isNotEmpty(backMap.get("team_flag"))){
                            teamFlag = (String) backMap.get("team_flag");
                            if("1".equals(teamFlag)){
                                teamFlag = "团队";
                            }else if("2".equals(teamFlag)){
                                teamFlag = "片区";
                            }
                        }
                        labeltitle = new Label(2, i, teamFlag);
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, i, (String) backMap.get("porg"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, i, (String) backMap.get("generate_date"));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, i, (String) backMap.get("team_leader_name"));
                        sheet.addCell(labeltitle);
                        if("团队".equals(teamFlag)){
                            labeltitle = new Label(6, i, String.valueOf(backMap.get("team_num")));
                            sheet.addCell(labeltitle);
                        }else{
                            labeltitle = new Label(6, i, "");
                            sheet.addCell(labeltitle);
                        }
                        if("片区".equals(teamFlag)){
                            labeltitle = new Label(7, i,String.valueOf(backMap.get("team_count")));
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(8, i, String.valueOf(backMap.get("area_num")));
                            sheet.addCell(labeltitle);
                        }else{
                            labeltitle = new Label(7, i,"");
                            sheet.addCell(labeltitle);
                            labeltitle = new Label(8, i,"");
                            sheet.addCell(labeltitle);
                        }
                        labeltitle = new Label(9, i, (String) backMap.get("remarks"));
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
                        labeltitle = new Label(18, i, String.valueOf(backMap.get("site_num1")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(19, i, String.valueOf(backMap.get("site_num2")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(20, i, String.valueOf(backMap.get("site_num3")));
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(21, i, String.valueOf(backMap.get("site_num4")));
                        sheet.addCell(labeltitle);

                        i++;
                    }
                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("Tstaff004Service导出表时出错002"+e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("Tstaff004Service导出表时出错003"+e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("Tstaff004Service导出表时出错004"+e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Tstaff004Service导出表时出错005"+e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<String, Object>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "团队管理信息"+dayString+".xls");
            retMap.put("status", "1");
            return retMap;
        }catch (Exception e){
            throw new BusinessException("Tstaff004ServiceException", "网络异常");
        }

    }

    private void add(Object object, Map<String, Object> p,String name) {
        if(object!=null){
            p.put(name, "%"+object.toString()+"%");
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

}
