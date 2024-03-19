package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import jxl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Tpatrol007Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol007Service.class);
    @Autowired
    private HPatrolLogContentDao patrolLogContentDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private SysBranchDao sysBranchDao;
    private static final String LOW = "低";
    private static final String MEDIUM = "中";
    private static final String HEIGHT = "高";
    private static final String PROJECT_NAME = "日常抽检";
    @Autowired
    private HProjectClassDao hProjectClassDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        int index, total;
        Sheet sheet;
        //检查巡查项目
//        List<Map<String, Object>> project = hProjectClassDao.queryForList(BaseUtils.map("project_name", PROJECT_NAME));
//        if (project != null && project.size() > 0) {
//            String str = project.get(0).get("pj_classify").toString();
//            index = Integer.parseInt(str);
//        } else {
//            int count = (int) hProjectClassDao.count(BaseUtils.map());
//            index = count + 1;
//            Map<String, Object> map = new HashMap<>();
//            map.put("created", DateUtil.now());
//            map.put("source", "admin");
//            map.put("isdeleted", 0);
//            map.put("pj_classify", index);
//           // map.put("project_name", PROJECT_NAME);
//            hProjectClassDao.save(map);
//        }
        //获取文件
        MultipartFile file = (MultipartFile) p.get("infile");
        if (file == null) {
            logger.debug("上传文件为空");
            throw new BusinessException("Tpatrol007ServiceException", "上传文件为空");
        }

        ArrayList<Map<String, Object>> returnList = new ArrayList<>();

        //保存表格的错误内容
        StringBuffer sb = new StringBuffer();
        List<Map<String, Object>> list;

        try {
            Workbook wb = Workbook.getWorkbook(file.getInputStream());

            //获取第一张Sheet表
            sheet = wb.getSheet(0);
            total = sheet.getRows() - 1;
            //将表格信息封装到list
            for (int i = 1; i < sheet.getRows(); i++) {

                    Map<String, Object> map = new HashMap<>();
                    for (int j = 1; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        String contents = cell.getContents();
                        switch (sheet.getCell(j, 0).getContents()) {
                            case "机构或站点编号":
                                //检查机构或站点编号是否存在
                                if (checkSiteNo(contents)) {
                                    map.put("site_no", contents);
                                } else {
                                    sb.append("第").append(i).append("行的'机构或站点编号'").append(contents).append("不存在, ");
                                }
                                break;
                            case "项目名称":
                                //检查项目名称是否存在
                                List<Map<String, Object>> projects = hProjectClassDao.queryByname(BaseUtils.map("project_name", contents));
                                if (projects != null && projects.size() > 0) {
                                    map.put("pj_classify", projects.get(0).get("pj_classify"));
                                } else {
                                    sb.append("第").append(i).append("行的'项目名称'").append(contents).append("不存在, ");
                                }
                                break;
                            case "问题描述":
                                if (cell.getType() == CellType.EMPTY) {
                                    sb.append("第").append(i).append("行的'问题描述'不可为空, ");
                                } else {
                                    map.put("content_text", contents);
                                }
                                break;
                            case "风险等级":
                                if (cell.getType() == CellType.EMPTY) {
                                    sb.append("第").append(i).append("行的'风险等级'不可为空, ");
                                } else {
                                    if (LOW.equals(contents) || MEDIUM.equals(contents) || HEIGHT.equals(contents)) {
                                        map.put("risk_level", contents);
                                    } else {
                                        sb.append("第").append(i).append("行的'整改要求'只能填‘高’，‘中’，‘低’, ");
                                    }

                                }
                                break;
                            case "整改时间":
                                if (cell.getType() == CellType.EMPTY) {
                                    sb.append("第").append(i).append("行的'整改时间'不可为空, ");
                                } else {
                                    if (cell.getType() == CellType.DATE) {
                                        DateCell dc = (DateCell) cell;
                                        Date date = dc.getDate();
                                        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                        map.put("end_date", format);
                                    } else {
                                        sb.append("第").append(i).append("行的'整改时间'填写不规范, ");
                                    }

                                }
                                break;
                            case "整改要求":
                                if (cell.getType() == CellType.EMPTY) {
                                    sb.append("第").append(i).append("行的'整改要求'不可为空, ");
                                } else {
                                    map.put("requirement", contents);
                                }
                                break;
                            case "整改人":
                                if (checkUserId(contents)) {
                                    map.put("responsible", contents);
                                } else {
                                    sb.append("第").append(i).append("行的'整改人'").append(contents).append("不存在, ");
                                }
                                break;
                        }
                    }
                    map.put("source", 2);
                    map.put("discoverer", evn.get("userid"));
                    map.put("created", DateUtil.now());
                    map.put("updatetime", DateUtil.now());
                    map.put("isdeleted", 0);
                    returnList.add(map);
                }

            //stream流去重
            list = returnList.stream().distinct().collect(Collectors.toList());
            wb.close();
        } catch (Exception e) {
            logger.info("文件无法读取", e);
            throw new BusinessException("Tpatrol007ServiceException", "文件无法读取");
        }

        if (StringUtils.hasText(sb)) {
            String s = sb.toString();
            throw new BusinessException("Tpatrol007ServiceException", s.endsWith(", ") ? s.substring(0, s.length() - 2) + "。" : s);
        }
        try {
            for (Map<String, Object> map : list) {
                if (map != null && map.size() > 0) {
                    map.put("indexs", "1");
                    //保存数据到数据库
                    patrolLogContentDao.save(map);
                }
            }
        } catch (Exception e) {
            logger.info("问题描述或整改要求填写超出规定字数", e);
            throw new BusinessException("Tpatrol007ServiceException", "问题描述或整改要求填写超出规定字数");
        }
        return writeExcelInfo(returnList, total);

    }

    private Boolean checkUserId(String UserId) {
        if (StringUtils.isEmpty(UserId)) {
            return false;
        }

        List<Map<String, Object>> userList = sysAccountDao.queryForList(BaseUtils.map("account_id", UserId));

        return userList != null && userList.size() > 0;
    }


    private Boolean checkSiteNo(String SiteNo) {
        if (StringUtils.isEmpty(SiteNo)) {
            return false;
        }
        //是否是服务点
        List<Map<String, Object>> siteList = hSiteDao.queryForList(BaseUtils.map("site_no", SiteNo));
        if (siteList != null && siteList.size() > 0) {

            return true;
        } else {
            //是否是机构
            List<Map<String, Object>> bList = sysBranchDao.queryForList(BaseUtils.map("branch_id", SiteNo));
            return bList != null && bList.size() > 0;
        }
    }

    private Map<String, Object> writeExcelInfo(List<Map<String, Object>> successList, int total) {
        int successTotal = successList.size();
        int failTotal = total - successTotal;
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("success_count", successTotal);
        mapInfo.put("total_count", total);
        mapInfo.put("fail_count", failTotal);
        return mapInfo;
    }
}
