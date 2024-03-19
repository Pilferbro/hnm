package com.gdnybank.hnm.report.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.SysBranchDao;
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
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:  导出助农存款业绩报表
 * @author: wxm
 */
@Service
public class Treport016Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Treport016Service.class);

    @Autowired
    private SysBranchDao sysBranchDao;

    @Autowired
    private HnmCommService hnmCommService;

    @Autowired
    SysParamService sysParamService;

    @Autowired
    private HSiteDao hSiteDao;

    @Autowired
    private HManagerDao hManagerDao;


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
                logger.info("Treport016Service导出表时出错001"+e);
                throw new BusinessException("Treport016Service","导出表时出错001");
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

                    if(ObjectUtil.isEmpty(p.get("query_level"))){
                        logger.error("query_level为必传字段");
                        throw new BusinessException("Treport016ServiceException","query_level为必传字段");
                    }
                    String query_level = p.get("query_level").toString();

                    if(ObjectUtil.isEmpty(p.get("query_org"))){
                        logger.error("query_org为必传字段");
                        throw new BusinessException("Treport016ServiceException","query_org为必传字段");
                    }
                    String query_org = p.get("query_org").toString();

                    Date start_date = null;
                    Date end_date = null;

                    if(ObjectUtil.isNotEmpty(p.get("start_date"))){
                        start_date = DateUtils.parse(p.get("start_date").toString(),"yyyyMMdd");
                    }
                    if(ObjectUtil.isNotEmpty(p.get("end_date"))){
                        end_date = DateUtils.parse(p.get("end_date").toString(),"yyyyMMdd");
                    }

                    String query_branch = "";
                    if(ObjectUtil.isNotEmpty(p.get("bankid"))){
                        query_branch = p.get("bankid").toString();
                    }

                    String userId = MfpContextHolder.getLoginId();
                    int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
                    if(userRoleLevel==0){
                        throw new BusinessException("Treport016ServiceException", "登陆人员未配置角色");
                    }

                    String branchids = "";
                    //总行级可以看所有
                    if(userRoleLevel != 1){
                        branchids = hnmCommService.getUserBranchids();
                    }

                    if(userRoleLevel == 4){
                        //客户经理级别
                        p.put("mgr_id",userId);
                    }

                    if(!StringUtils.isEmpty(query_branch)){
                        branchids = hnmCommService.getUserBranchidsBybranch(query_branch);
                    }

                    //去除p参数里的开始日期 结束日期等
                    p.remove("start_date");
                    p.remove("end_date");

                    if("1".equals(query_level)){
                        int i = 1;
                        Label labeltitle = null;
                        labeltitle = new Label(0, 0, "分行号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, 0, "分行");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, 0, "小额取款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, 0, "小额取款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, 0, "转账汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, 0, "转账汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, 0, "现金汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, 0, "现金汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(8, 0, "余额查询笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(9, 0, "余额查询金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(10, 0, "汇总笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(11, 0, "汇总金额（元）");
                        sheet.addCell(labeltitle);

                        //一级报表  分行
                        //查询所有的分行
                        List<Map<String, Object>> branchList = new ArrayList<>();
                        if(!StringUtils.isEmpty(branchids)){
                            p.put("branchids",branchids);
                            p.put("bran_level","1");
                            branchList = sysBranchDao.queryForListBycondition(delkong(p));
                        }else{
                            p.put("bran_level","1");
                            branchList = sysBranchDao.queryForListBycondition(delkong(p));
                        }
                        if(branchList != null && branchList.size() >0){
                            for (Map<String, Object> branch : branchList){
                                //1 先查询其下所有的机构
                                String orgids = hnmCommService.getUserBranchidsBybranch(branch.get("branch_id").toString());
                                //2 根据branchIds查询其下所有的服务点
                                if("1".equals(query_org)){
                                    //所属机构
                                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));
                                    branch = dealData(siteList, start_date, end_date ,branch);
                                }else if("2".equals(query_org)){
                                    //落地支行
                                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("branchids", orgids,"is_delete","0","approval_status","2"));
                                    branch = dealData(siteList, start_date, end_date ,branch);
                                }

                                branch.put("fh_branch_id",branch.get("branch_id"));
                                branch.put("fh_branch_name",branch.get("branch_name"));

                                labeltitle = new Label(0, i, (String)branch.get("fh_branch_id"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(1, i, (String)branch.get("fh_branch_name"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(2, i, String.valueOf(branch.get("count1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(3, i, String.valueOf(branch.get("amt1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(4, i, String.valueOf(branch.get("count2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(5, i, String.valueOf(branch.get("amt2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(6, i, String.valueOf(branch.get("count3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(7, i, String.valueOf(branch.get("amt3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(8, i, String.valueOf(branch.get("count4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(9, i, String.valueOf(branch.get("amt4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(10, i, String.valueOf(branch.get("count5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(11, i, String.valueOf(branch.get("amt5")));
                                sheet.addCell(labeltitle);
                                i++;
                            }
                        }

                    }else if("2".equals(query_level)){
                        int i = 1;
                        Label labeltitle = null;
                        labeltitle = new Label(0, 0, "支行号/团队号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, 0, "支行/团队");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, 0, "小额取款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, 0, "小额取款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, 0, "转账汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, 0, "转账汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, 0, "现金汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, 0, "现金汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(8, 0, "余额查询笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(9, 0, "余额查询金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(10, 0, "汇总笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(11, 0, "汇总金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(12, 0, "所属分行");
                        sheet.addCell(labeltitle);

                        //二级报表  支行/团队
                        //查询所有的支行/团队
                        List<Map<String, Object>> branchList = new ArrayList<>();
                        if(!StringUtils.isEmpty(branchids)){
                            p.put("branchids",branchids);
                            p.put("bran_level","2");
                            branchList = sysBranchDao.queryForListBycondition(delkong(p));
                        }else{
                            p.put("bran_level","2");
                            branchList = sysBranchDao.queryForListBycondition(delkong(p));
                        }
                        if(branchList != null && branchList.size() >0){
                            for (Map<String, Object> branch : branchList){
                                //1 先查询其下所有的机构
                                String orgids = hnmCommService.getUserBranchidsBybranch(branch.get("branch_id").toString());
                                //2 根据branchIds查询其下所有的服务点
                                if("1".equals(query_org)){
                                    //所属机构
                                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("orgids", orgids,"is_delete","0","approval_status","2"));
                                    branch = dealData(siteList, start_date, end_date ,branch);
                                }else if("2".equals(query_org)){
                                    //落地支行
                                    List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("branchids", orgids,"is_delete","0","approval_status","2"));
                                    branch = dealData(siteList, start_date, end_date ,branch);
                                }

                                List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                                        branch.get("branch_id")));
                                if(allPorgidList != null && allPorgidList.size()>0){
                                    for (Map<String, Object> allPorgid : allPorgidList){
                                        if(ObjectUtil.isNotEmpty(allPorgid.get("bran_level"))&& "1".equals(allPorgid.get("bran_level").toString())){
                                            branch.put("fh_branch_id",allPorgid.get("branch_id"));
                                            branch.put("fh_branch_name",allPorgid.get("branch_name"));
                                            break;
                                        }
                                    }
                                }
                                labeltitle = new Label(0, i, (String)branch.get("branch_id"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(1, i, (String)branch.get("branch_name"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(2, i, String.valueOf(branch.get("count1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(3, i, String.valueOf(branch.get("amt1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(4, i, String.valueOf(branch.get("count2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(5, i, String.valueOf(branch.get("amt2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(6, i, String.valueOf(branch.get("count3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(7, i, String.valueOf(branch.get("amt3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(8, i, String.valueOf(branch.get("count4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(9, i, String.valueOf(branch.get("amt4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(10, i, String.valueOf(branch.get("count5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(11, i, String.valueOf(branch.get("amt5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(12, i, (String)branch.get("fh_branch_name"));
                                sheet.addCell(labeltitle);
                                i++;
                            }
                        }
                    }else if("3".equals(query_level)){
                        int i = 1;
                        Label labeltitle = null;
                        labeltitle = new Label(0, 0, "客户经理号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, 0, "客户经理");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, 0, "小额取款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, 0, "小额取款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, 0, "转账汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, 0, "转账汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, 0, "现金汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, 0, "现金汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(8, 0, "余额查询笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(9, 0, "余额查询金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(10, 0, "汇总笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(11, 0, "汇总金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(12, 0, "所属分行");
                        sheet.addCell(labeltitle);

                        //三级报表  客户经理
                        //查询所有的客户经理
                        List<Map<String, Object>> mgrList = new ArrayList<>();
                        if(!StringUtils.isEmpty(branchids)){
                            p.put("branchids",branchids);
                            p.put("is_delete","0");
                            p.put("approval_status","2");
                            mgrList = hManagerDao.queryForListBycondition(delkong(p));
                        }else{
                            p.put("is_delete","0");
                            p.put("approval_status","2");
                            mgrList = hManagerDao.queryForListBycondition(delkong(p));
                        }

                        if(mgrList != null && mgrList.size() >0){
                            for (Map<String, Object> mgr : mgrList){
                                //根据mgr_id查询其下所有的服务点
                                List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("mgr_id", mgr.get("mgr_id"),"is_delete","0","approval_status","2"));
                                mgr = dealData(siteList, start_date, end_date, mgr);
                                List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                                        mgr.get("branch_id")));
                                if(allPorgidList != null && allPorgidList.size()>0){
                                    for (Map<String, Object> allPorgid : allPorgidList){
                                        if(ObjectUtil.isNotEmpty(allPorgid.get("bran_level"))&& "1".equals(allPorgid.get("bran_level").toString())){
                                            mgr.put("fh_branch_id",allPorgid.get("branch_id"));
                                            mgr.put("fh_branch_name",allPorgid.get("branch_name"));
                                            break;
                                        }
                                    }
                                }
                                labeltitle = new Label(0, i, (String)mgr.get("mgr_id"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(1, i, (String)mgr.get("mgr_name"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(2, i, String.valueOf(mgr.get("count1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(3, i, String.valueOf(mgr.get("amt1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(4, i, String.valueOf(mgr.get("count2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(5, i, String.valueOf(mgr.get("amt2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(6, i, String.valueOf(mgr.get("count3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(7, i, String.valueOf(mgr.get("amt3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(8, i, String.valueOf(mgr.get("count4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(9, i, String.valueOf(mgr.get("amt4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(10, i, String.valueOf(mgr.get("count5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(11, i, String.valueOf(mgr.get("amt5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(12, i, (String)mgr.get("fh_branch_name"));
                                sheet.addCell(labeltitle);
                                i++;
                            }
                        }
                    }else if("4".equals(query_level)){
                        int i = 1;
                        Label labeltitle = null;
                        labeltitle = new Label(0, 0, "服务点号");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(1, 0, "服务点");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(2, 0, "小额取款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(3, 0, "小额取款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(4, 0, "转账汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(5, 0, "转账汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(6, 0, "现金汇款笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(7, 0, "现金汇款金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(8, 0, "余额查询笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(9, 0, "余额查询金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(10, 0, "汇总笔数");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(11, 0, "汇总金额（元）");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(12, 0, "所属分行");
                        sheet.addCell(labeltitle);
                        labeltitle = new Label(13, 0, "所属支行");
                        sheet.addCell(labeltitle);

                        //四级报表  服务点
                        //查询所有服务点
                        List<Map<String, Object>> siteAllList = new ArrayList<>();
                        if(!StringUtils.isEmpty(branchids)){
                            if("1".equals(query_org)){
                                //所属机构
                                p.put("orgids",branchids);
                            }else if("2".equals(query_org)){
                                //落地支行
                                p.put("branchids",branchids);
                            }
                            p.put("is_delete","0");
                            p.put("approval_status","2");
                            siteAllList = hSiteDao.query(delkong(p));
                        }else{
                            p.put("is_delete","0");
                            p.put("approval_status","2");
                            siteAllList = hSiteDao.query(delkong(p));
                        }

                        if(siteAllList != null && siteAllList.size() >0){
                            for (Map<String, Object> site : siteAllList){
                                //根据site_no查询其下所有的服务点
                                List<Map<String, Object>> siteList = hSiteDao.query(BaseUtils.map("site_no", site.get("site_no"),"is_delete","0","approval_status","2"));
                                site = dealData(siteList, start_date, end_date, site);
                                if("1".equals(query_org)){
                                    //所属机构
                                    site.put("branch_id",site.get("org_id"));
                                    site.put("branch_name",site.get("org_name"));
                                }
                                List<Map<String, Object>> allPorgidList = sysBranchDao.queryAllPorgid(BaseUtils.map("branch_id",
                                        site.get("branch_id")));
                                if(allPorgidList != null && allPorgidList.size()>0){
                                    for (Map<String, Object> allPorgid : allPorgidList){
                                        if(ObjectUtil.isNotEmpty(allPorgid.get("bran_level"))&& "1".equals(allPorgid.get("bran_level").toString())){
                                            site.put("fh_branch_id",allPorgid.get("branch_id"));
                                            site.put("fh_branch_name",allPorgid.get("branch_name"));
                                            break;
                                        }
                                    }
                                }
                                labeltitle = new Label(0, i, (String)site.get("site_no"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(1, i, (String)site.get("site_name"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(2, i, String.valueOf(site.get("count1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(3, i, String.valueOf(site.get("amt1")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(4, i, String.valueOf(site.get("count2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(5, i, String.valueOf(site.get("amt2")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(6, i, String.valueOf(site.get("count3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(7, i, String.valueOf(site.get("amt3")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(8, i, String.valueOf(site.get("count4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(9, i, String.valueOf(site.get("amt4")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(10, i, String.valueOf(site.get("count5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(11, i, String.valueOf(site.get("amt5")));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(12, i, (String)site.get("fh_branch_name"));
                                sheet.addCell(labeltitle);
                                labeltitle = new Label(13, i, (String)site.get("branch_name"));
                                sheet.addCell(labeltitle);
                                i++;
                            }
                        }
                    }

                    book.write();
                    book.close();
                    os.flush();
                    os.close();
                    //异步保存导出流水

                } catch (RowsExceededException e) {
                    logger.info("Treport016Service导出表时出错002"+e);
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    logger.info("Treport016Service导出表时出错003"+e);
                    e.printStackTrace();
                } catch (WriteException e) {
                    logger.info("Treport016Service导出表时出错004"+e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Treport016Service导出表时出错005"+e);
                    e.printStackTrace();
                }
            }

            Map<String, Object> retMap = new HashMap<String, Object>();
            Date dayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dayString = sdf.format(dayDate);
            retMap.put("filePath", excelPathRelative);
            retMap.put("fileName", "小额取款业绩报表"+dayString+".xls");
            retMap.put("status", "1");
            return retMap;
        }catch (Exception e){
            throw new BusinessException("Treport016ServiceException", "网络异常");
        }

    }

    private Map<String, Object> dealData(List<Map<String, Object>> siteList,Date start_date,Date end_date,Map<String, Object> map){
        //小额取款笔数
        int count1 = 0;
        //小额取款金额
        BigDecimal amt1 = BigDecimal.ZERO;

        //转账汇款笔数
        int count2 = 0;
        //转账汇款金额
        BigDecimal amt2 = BigDecimal.ZERO;

        //现金汇款笔数
        int count3 = 0;
        //现金汇款金额
        BigDecimal amt3 = BigDecimal.ZERO;

        //余额查询笔数
        int count4 = 0;
        //余额查询金额
        BigDecimal amt4 = BigDecimal.ZERO;

        //汇总笔数
        int count5 = 0;
        //汇总金额
        BigDecimal amt5 = BigDecimal.ZERO;


        if(siteList !=null && siteList.size() > 0){
            String siteIds = "";
            for (Map<String, Object> site : siteList) {
                if(ObjectUtil.isNotEmpty(site.get("site_no"))){
                    siteIds+=",'"+site.get("site_no")+"'";
                }
            }
            if(siteIds.startsWith(",")){
                siteIds=siteIds.substring(1, siteIds.length());
            }

            Map<String , Object> parms = new HashMap<>();
            parms.put("siteIds",siteIds);

            if(start_date != null){
                parms.put("start_date",DateUtils.getDate(start_date,"yyyyMMdd"));
            }
            if(end_date != null){
                parms.put("end_date",DateUtils.getDate(end_date,"yyyyMMdd"));
            }
            //parms.put("tsdt",DateUtils.getDate(date,"yyyyMMdd"));

            //查询小额取款信息
            List<Map<String, Object>> list1 = new ArrayList<>();
            if(!StringUtils.isEmpty(siteIds)){
                list1 = hnmCommService.qureyZN(parms);
            }

            if(list1 != null && list1.size()>0){
                for (Map<String, Object> dataMap : list1){
                    if(ObjectUtil.isNotEmpty(dataMap.get("prid"))&&"56".equals(dataMap.get("prid").toString())){
                        count1 ++;
                        if(ObjectUtil.isNotEmpty(dataMap.get("f004"))){
                            amt1 = amt1.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("f004").toString())));
                        }
                    }
                    if(ObjectUtil.isNotEmpty(dataMap.get("prid"))&&"58".equals(dataMap.get("prid").toString())){
                        count2 ++;
                        if(ObjectUtil.isNotEmpty(dataMap.get("f004"))){
                            amt2 = amt2.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("f004").toString())));
                        }
                    }
                    if(ObjectUtil.isNotEmpty(dataMap.get("prid"))&&"57".equals(dataMap.get("prid").toString())){
                        count3 ++;
                        if(ObjectUtil.isNotEmpty(dataMap.get("f004"))){
                            amt3 = amt3.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("f004").toString())));
                        }
                    }
                    if(ObjectUtil.isNotEmpty(dataMap.get("prid"))&&"01".equals(dataMap.get("prid").toString())){
                        count4 ++;
                        if(ObjectUtil.isNotEmpty(dataMap.get("f004"))){
                            amt4 = amt4.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("f004").toString())));
                        }
                    }
                    count5 ++;
                    if(ObjectUtil.isNotEmpty(dataMap.get("f004"))){
                        amt5 = amt5.add(BigDecimal.valueOf(Double.valueOf(dataMap.get("f004").toString())));
                    }
                }
            }
        }

        map.put("count1",count1);
        map.put("amt1",hnmCommService.dealVal(amt1.divide(BigDecimal.valueOf(100))));

        map.put("count2",count2);
        map.put("amt2",hnmCommService.dealVal(amt2.divide(BigDecimal.valueOf(100))));

        map.put("count3",count3);
        map.put("amt3",hnmCommService.dealVal(amt3.divide(BigDecimal.valueOf(100))));

        map.put("count4",count4);
        map.put("amt4",hnmCommService.dealVal(amt4.divide(BigDecimal.valueOf(100))));

        map.put("count5",count5);
        map.put("amt5",hnmCommService.dealVal(amt5.divide(BigDecimal.valueOf(100))));
        return map;
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
