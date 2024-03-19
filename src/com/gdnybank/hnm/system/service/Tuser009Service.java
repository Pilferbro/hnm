package com.gdnybank.hnm.system.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.EsbMsgProcService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.utils.IdCardUtil;
import com.gdnybank.hnm.pub.utils.PhoneUtil;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @description:用户信息批量导入
 * @author: wxm
 */
@Service
public class Tuser009Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tuser009Service.class);

    @Autowired
    private SysAccountDao sysAccountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysAccountRoleDao sysAccountRoleDao;

    @Autowired
    private HnmCommService hnmCommService;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysBranchDao sysBranchDao;

    @Autowired
    private EsbMsgProcService esbMsgProcService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private HManagerDao hManagerDao;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        logger.debug("后台接受到的参数：" + p);
        logger.debug("当前用户ID：" + MfpContextHolder.getCurrentUserId());
        return parsingFile(env, p);
    }

    @Transactional
    private Map<String, Object> parsingFile(Map<String, Object> env, Map<String, Object> p) {
        List<String> agrmNoList = new ArrayList<>();
        Set<String> agrmNoSet = new HashSet<>();
        CommonsMultipartFile files = (CommonsMultipartFile) p.get("infile");
//        try {
            if (files == null) {
                logger.debug("上传文件为空");
                throw new BusinessException("Tuser009ServiceException", "上传文件为空");
            } else {
                CommonsMultipartFile file = files;
                // MultipartFile格式转成 File格式
                // 获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀
                String prefix = fileName.substring(fileName.lastIndexOf("."));
                // 用uuid作为文件名，防止生成的临时文件重复
              /*  File excelFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
                file.transferTo(excelFile);
                Workbook wb;
//                wb = new XSSFWorkbook(excelFile);
                wb = WorkbookFactory.create(excelFile);*/
                File excelFile = null;
                Workbook wb;
                try {
                    excelFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
                    file.transferTo(excelFile);
                    wb = WorkbookFactory.create(excelFile);
                } catch (Exception e) {
                    logger.error("文件无法读取",e);
                    throw new BusinessException("Tuser009ServiceException","文件无法读取");
                }
                Sheet sheet = wb.getSheetAt(0);
                int firstRowIndex = sheet.getFirstRowNum() + 1;
                int lastRowIndex = sheet.getLastRowNum();
                Map<String,Integer> countMap = new HashMap<>();
                countMap.put("lastRowIndexFinal",lastRowIndex);
                //查询机构id
                String userFromBranchId = hnmCommService.getUserBranchids();
                /*List<Map<String, Object>> branchList = sysBranchDao.queryForList(BaseUtils.map());
                String userFromBranchId = "";
                if(branchList!=null && branchList.size()>0){
                    StringBuffer sb = new StringBuffer();
                    for (Map<String, Object> map:branchList){
                        sb.append(map.get("branch_id")).append(",");
                    }
                    userFromBranchId = sb.toString();
                }
*/
                Map<String, Map<String, Object>> batchInfoMapList = new HashMap<>();
                for (int i = firstRowIndex; i <= lastRowIndex; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();


                        Map<String, Object> batchInfoMap = null;
                        batchInfoMap = readExcelInfoToList(firstCellIndex, lastCellIndex, row, i, env, agrmNoList, agrmNoSet,countMap,userFromBranchId);
                        if (batchInfoMap == null){
                            continue;
                        }
                        batchInfoMap.put("row_count", i + 1);
                        if (batchInfoMap.get("account_id") != null && !StringUtils.isEmpty(batchInfoMap.get("account_id"))) {
                            batchInfoMapList.put(batchInfoMap.get("account_id").toString(), batchInfoMap);
                        }
                    }
                }

                if (countMap.get("lastRowIndexFinal")!= agrmNoSet.size()){
                    throw new BusinessException("Tuser009ServiceException", "导入文件存在重复的用户编号");
                }

                logger.debug("待批量插入的数据List展示（未去重）：" + batchInfoMapList);
                StringBuffer sb = new StringBuffer();
                int count = 0;
                for (String agrmNo : agrmNoList) {
                    if (count == 0) {
                        sb.append("'" + agrmNo + "'");
                    } else {
                        sb.append("," + "'" + agrmNo + "'");
                    }
                    ++count;
                }
                List<Map<String, Object>> existAgrmNoList = null;
                if (sb != null && !StringUtils.isEmpty(sb.toString())) {
                    //根据已保存的用户list，去数据库中，查询已经存在的用户信息，并在batchInfoMapList中剔除重复用户信息
                    existAgrmNoList = sysAccountDao.selectExistAccountIdInfo(sb.toString());
                    sb = new StringBuffer();
                    if (existAgrmNoList != null && existAgrmNoList.size() > 0) {
                        for (Map<String, Object> agrmNoMap : existAgrmNoList) {
                            String agrmNo = agrmNoMap.get("account_id").toString();
                            if (batchInfoMapList.containsKey(agrmNo)) {
                                batchInfoMapList.remove(agrmNo);
                            }
                        }
                    }
                }
                logger.debug("待批量插入的数据List展示（已去重）：" + batchInfoMapList);
                List<Map<String, Object>> batchInfoList = new ArrayList<>();
                for (String key : batchInfoMapList.keySet()) {
                    {
                        batchInfoList.add(batchInfoMapList.get(key));
                    }
                }
                sysAccountDao.batchInsertAccountInfo(batchInfoList);
                sysAccountRoleDao.batchInsertAccountRoleInfo(batchInfoList);

                //如果插入的为角色级别3或4 则 需要添加人员信息表
                if(batchInfoList != null && batchInfoList.size()>0){
                    for(Map<String, Object> map : batchInfoList){
                        boolean flag = false;
                        //根据角色查询级别
                        List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", map.get(
                                "role_id")));
                        if(mapList!=null && mapList.size()>0){
                            for(Map<String, Object> roleMap : mapList){
                                String role_level = roleMap.get("role_level").toString();
                                if("2".equals(role_level) || "3".equals(role_level) || "4".equals(role_level)){
                                    flag = true;
                                    break;
                                }
                            }
                        }
                        //添加客户经理表
                        if(flag){
                            List<Map<String, Object>> list = hManagerDao.queryList(BaseUtils.map("mgr_id", map.get("account_id"),
                                    "is_delete", "0", "approval_status", "2"));
                            if(list != null && list.size() >0){
                                //更新
                                for(Map<String, Object> hmap : list){
                                    map.put("id",hmap.get("id"));
                                    map.put("mgr_id",map.get("account_id"));
                                    map.put("mgr_name",map.get("name"));
                                    map.put("e_mail",map.get("email"));
                                    map.put("create_time",hmap.get("create_time"));
                                    map.put("update_time", cn.hutool.core.date.DateUtil.now());
                                    map.put("creator", hmap.get("creator"));
                                    hManagerDao.updateByPk(map);
                                }
                            }else{
                                //新增
                                //保存客户经理表
                                String id = IdUtil.randomUUID().replace("-", "");
                                map.put("id",id);
                                map.put("mgr_id",map.get("account_id"));
                                map.put("mgr_name",map.get("name"));
                                map.put("e_mail",map.get("email"));
                                map.put("create_time", cn.hutool.core.date.DateUtil.now());
                                map.put("update_time", cn.hutool.core.date.DateUtil.now());
                                map.put("is_delete","0");
                                hManagerDao.save(map);
                                //保存申请表
                                Map<String,Object> parms = new HashMap<>();
                                parms.put("approval_type", ApprovalTypeEnum.TYPE002.getApprovalType());
                                parms.put("approval_status", ApprovalStatusEnum.STATUS_2.getApprovalStatus());
                                parms.put("approval_status_name", ApprovalStatusEnum.STATUS_2.getApprovalStatusName());
                                parms.put("operator", MfpContextHolder.getLoginId());
                                parms.put("relation_id", id);
                                parms.put("create_time", cn.hutool.core.date.DateUtil.now());
                                parms.put("update_time", DateUtil.now());
                                tApprovalApplyDao.save(parms);
                            }
                        }
                        //添加客户经理表
                        if(flag){
                            Map<String, Object> rmap = new HashMap<>();
                            String id = IdUtil.randomUUID().replace("-", "");
                            //先查询
                            List<Map<String, Object>> list = hManagerDao.queryList(BaseUtils.map("mgr_id", map.get("account_id"),
                                    "is_delete", "0", "approval_status", "2"));
                            if(list != null && list.size() >0){
                                //修改
                                //保存客户经理表
                                Map<String, Object> hmap = list.get(0);
                                rmap.putAll(hmap);
                                rmap.putAll(map);
                                rmap.put("id",id);
                                rmap.put("mgr_id",map.get("account_id"));
                                rmap.put("mgr_name",map.get("name"));
                                rmap.put("e_mail",map.get("email"));
                                rmap.put("create_time", DateUtil.now());
                                rmap.put("update_time", DateUtil.now());
                                rmap.put("is_delete","0");
                                rmap.put("temp_role_id",map.get("role_id"));
                                hManagerDao.save(rmap);
                                //保存申请表
                                String suggestion = p.get("name") + "人员基本信息调整申请";
                                hnmCommService.saveApprovalApplyNoProcess(p,BaseUtils.map("approval_type",
                                        ApprovalTypeEnum.TYPE003.getApprovalType(), "relation_id", id,
                                        "operator", MfpContextHolder.getLoginId(),"suggestion",suggestion));
                            }else{
                                //新增
                                //保存客户经理表
                                map.put("id",id);
                                map.put("mgr_id",map.get("account_id"));
                                map.put("mgr_name",map.get("name"));
                                map.put("e_mail",map.get("email"));
                                map.put("create_time",DateUtil.now());
                                map.put("update_time",DateUtil.now());
                                map.put("is_delete","0");
                                map.put("temp_role_id",map.get("role_id"));
                                hManagerDao.save(map);
                                //保存申请表
                                String suggestion = p.get("name") + "人员新增申请";
                                hnmCommService.saveApprovalApplyNoProcess(p,BaseUtils.map("approval_type",
                                        ApprovalTypeEnum.TYPE002.getApprovalType(), "relation_id", id,
                                        "operator", MfpContextHolder.getLoginId(),"suggestion",suggestion));
                            }
                            List<Map<String, Object>> managerList = hManagerDao.queryList(BaseUtils.map(
                                    "mgr_id", p.get("account_id"),"approval_status","2"));
                            for (Map<String, Object> managerMap : managerList){
                                String managerMapId = managerMap.get("id").toString();
                                if (id.equals(managerMapId)) continue;
                                hManagerDao.updateByPk(BaseUtils.map("id",managerMapId,"is_delete","1"));
                            }
                        }

                    }

                }

                return writeExcelInfo(batchInfoList, existAgrmNoList);
            }
//        } catch (Exception e) {
//            logger.error("文件格式错误，解析出错", e);
//            throw new BusinessException("Tuser009ServiceException","文件格式错误，解析出错");
//        }
    }


    private Map<String, Object> writeExcelInfo(List<Map<String, Object>> successList, List<Map<String, Object>> failList)  {
        int successTotal = successList.size();
        int failTotal = failList.size();
        int total = successTotal + failTotal;
        Map<String, Object> operateRecordMapInfo = new HashMap<>();
        operateRecordMapInfo.put("success_count", successTotal);
        operateRecordMapInfo.put("total_count", total);
        operateRecordMapInfo.put("fail_count", failTotal);
        if (failList.size() > 0){
            operateRecordMapInfo.put("exist_account_id_list", failList);
        }
        return operateRecordMapInfo;
    }


    /**
     * 读取excel信息到List去
     *
     * @param firstCellIndex
     * @param LastCellIndex
     * @param row
     * @return
     */
    private Map<String, Object> readExcelInfoToList(int firstCellIndex, int LastCellIndex, Row row, int rowCount, Map<String, Object> env, List<String> agrmNoList, Set<String> agrmNoSet, Map<String,Integer> countMap,String userFromBranchId) {
        Map<String, Object> batchInfoMap = new HashMap<>(16);
        int firstCount = 0;
        int lastSixCount  = 0;
        String nullStr = "";
        int count = firstCellIndex;
        for (int j = firstCellIndex; j < LastCellIndex; j++) {
            Cell cell = row.getCell(j);
            CellType cellType = cell.getCellTypeEnum();
            String cellValue = null;

            if (cell != null) {
                switch (cellType) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cell.setCellType(CellType.STRING);
                        cellValue = String.valueOf(cell.getStringCellValue());
                        break;
                    case BLANK:
                        ++count;  //对空白的行进行计数
                        if (j == 0){
                            nullStr = nullStr + j;
                            firstCount++;
                        }else {
                            nullStr = nullStr + ";" + j;
                            lastSixCount++;
                        } //对空白的行进行计数
                        break;
                     default:
                        break;
                }
            }

            if (cellType.name().equals("BLANK") && j  != LastCellIndex - 1 ){
                continue;
            }

            if ( (j  == LastCellIndex - 1 ) && (lastSixCount > 0) ){
                if (firstCount + lastSixCount != 7){
                    String[] nullgroup = nullStr.split(";");
                    for(String s: nullgroup){
                        switch (s){
                            case "":
                                break;
                            case "0":
                                break;
                            case "3":
                                Map<String,Object> parms = new HashMap<>();
                                parms.put("role_id",cell.getRow().getCell(5).toString());
                                List<Map<String, Object>> roleList = sysRoleDao.queryForList(parms);
                                if(roleList.size()>0){
                                    String role_level = roleList.get(0).get("role_level").toString();
                                    if(!"1".equals(role_level)){
                                        throw new BusinessException("Tuser009ServiceException", "第" + (rowCount + 1) + "行必填字段字段不能为空");
                                    }
                                }
                            case "7":
                                break;
                            default:
                                throw new BusinessException("Tuser009ServiceException", "第" + (rowCount + 1) + "行必填字段字段不能为空");
                        }
                    }
                }else {
                    if (LastCellIndex == count){
                        Integer lastRowIndexFinal1 = countMap.get("lastRowIndexFinal");
                        --lastRowIndexFinal1;
                        countMap.put("lastRowIndexFinal",lastRowIndexFinal1);
                    }
                    return null;
                }
            }

            switch (j) {
                case 1:
                    batchInfoMap.put("name", cellValue);
                    break;
                case 2:
                    Map<String,Object> receiveMap = new HashMap<String, Object>();
                    try{
                        Map<String,Object> parms = new HashMap<>();
                        String userId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_USERID", "E9999");
                        parms.put("user_id", cellValue);
                        parms.put("bus_seq_no", hnmCommService.getFlowNo(userId));
                        parms.put("service_code", "11003000123");//esb服务代码
                        parms.put("service_scene", "01");//esb服务应用场景号，校验工号
                        receiveMap = esbMsgProcService.sendAndReceiveMsg(parms, false);
                    }catch(Exception e){
                        logger.error("校验工号失败，原因:",e);
                        throw new BusinessException("Tuser009ServiceExceptin", "第" + (rowCount + 1) +"行校验工号失败");
                    }
                    if("0".equals(esbMsgProcService.getRetStatus(receiveMap))){ //1，成功；0，失败；
                        String retCodeAndMsg = esbMsgProcService.getRetCodeMsg(receiveMap);
                        throw new BusinessException("Tuser009ServiceExceptin", "第" + (rowCount + 1) +"行"+retCodeAndMsg);
                    }
                    HashMap body = (HashMap) receiveMap.get("body");
                    if (ObjectUtil.isEmpty(body) || ObjectUtil.isEmpty(body.get("fullname"))){
                        throw new BusinessException("Tuser009ServiceExceptin", "第" + (rowCount + 1) +"行工号未登记，请重新输入");
                    }
                    if(!String.valueOf(batchInfoMap.get("name")).equals(String.valueOf(body.get("fullname")))){
                        throw new BusinessException("Tuser009ServiceExceptin", "第" + (rowCount + 1) +"行姓名未登记，请重新输入");
                    }
                    agrmNoList.add(cellValue);
                    agrmNoSet.add(cellValue);
                    batchInfoMap.put("account_id", cellValue);
                    break;
                case 3:
                    if (!userFromBranchId.contains(cellValue)){
                        throw new BusinessException("Tuser009ServiceException","第" + (rowCount + 1) + "行所属机构非本行机构，无法导入");
                    }

                    batchInfoMap.put("branch_id", cellValue);
                    break;
                case 4:
                    boolean b = PhoneUtil.phoneIsTrueMatch(cellValue);
                    if (!b) {
                        throw new BusinessException("Tuser009ServiceException", "第" +  (rowCount + 1)  + "行手机号格式错误");
                    }
                    batchInfoMap.put("phone", cellValue);
                    break;
                case 5:
                    List<Map<String, Object>> list = sysRoleDao.queryForList(BaseUtils.map("role_id", cellValue));
                    if (list == null || list.size() == 0){
                        throw new BusinessException("Tuser009ServiceException", "第" +  (rowCount + 1)  + "行用户角色ID不存在");
                    }
                    batchInfoMap.put("role_id", cellValue);
                    break;
                case 6:
                    if (!"correct".equals(IdCardUtil.IdentityCardVerification(cellValue))){
                        throw new BusinessException("Tuser009ServiceException", "第" +  (rowCount + 1)  + "行身份证格式错误");
                    }
                    batchInfoMap.put("identify_no", cellValue);
                    break;
                case 7:
                    if (StrUtil.isNotEmpty(cellValue)){
                        if (isEmail(cellValue)) batchInfoMap.put("email", cellValue);
                        else throw new BusinessException("Tuser009ServiceException", "第" +  (rowCount + 1)  + "行电子邮件格式错误");
                    }
                    break;
                default:
                    break;
            }
        }
        if (LastCellIndex == count){
            Integer lastRowIndexFinal1 = countMap.get("lastRowIndexFinal");
            --lastRowIndexFinal1;
            countMap.put("lastRowIndexFinal",lastRowIndexFinal1);
        }
        batchInfoMap.put("login_pwd", passwordEncoder.encode("123456"));
        //batchInfoMap.put("business_pwd", passwordEncoder.encode("password"));
        batchInfoMap.put("creator", env.get("userid"));
        batchInfoMap.put("status", "1");
        batchInfoMap.put("error_count", "0");
        batchInfoMap.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        return batchInfoMap;
    }

    /**
     * 判断Email合法性
     */
    public boolean isEmail(String email) {
        if (email == null)
            return false;
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;
    }
}
