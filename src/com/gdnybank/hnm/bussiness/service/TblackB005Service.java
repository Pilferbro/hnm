package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.IdUtil;
import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.gdnybank.hnm.pub.dao.SysDictDao;
import com.gdnybank.hnm.pub.enums.RiskLevelEnum;
import com.gdnybank.hnm.pub.utils.CustInfoVerify;
import com.gdnybank.hnm.pub.utils.IdCardUtil;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import jxl.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 批量导入风险客户
 */
@Service
@Transactional
public class TblackB005Service extends TXBaseService {

    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private SysDictDao sysDictDao;
    @Autowired
    private CustInfoVerify custInfoVerify;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        int total;
        Sheet sheet;

        //获取文件
        MultipartFile file = (MultipartFile) p.get("infile");
        if (file == null) {
            logger.debug("上传文件为空");
            throw new BusinessException("TblackB005ServiceException", "上传文件为空");
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
                        case "客户姓名":

                            if (cell.getType() == CellType.EMPTY) {
                                sb.append("第").append(i).append("行的'客户姓名'为空");
                            } else {
                                map.put("cust_name", contents);
                            }
                            break;
                        case "证件类型":
                            //证件类型是否存在
                            List<Map<String, Object>> sysDictMaps = sysDictDao
                                    .queryForList(BaseUtils.map("dict_type_code", "11", "dict_name", contents));
                            if (sysDictMaps == null || !(sysDictMaps.size() > 0)) {
                                sb.append("第").append(i).append("行的'证件类型'不存在");
                            } else {
                                map.put("certType", sysDictMaps.get(0).get("dict_value"));
                            }
                            break;
                        case "证件号码":
                            if (cell.getType() != CellType.EMPTY) {
                                //检查项证件号码是否已存在
                                List<Map<String, Object>> blklist = blklistCtrlDao.queryForList(BaseUtils.map("cert_num", contents));
                                if (blklist != null && blklist.size() > 0) {
                                    sb.append("第").append(i).append("行的'证件号码'").append(contents).append("，已存在, ");
                                } else if (!"correct".equals(IdCardUtil.IdentityCardVerification(contents))) {
                                    sb.append("第").append(i).append("行的'证件号码'").append("，行身份证格式错误, ");
                                } else {
                                    map.put("cert_num", contents);
                                }
                            } else {
                                map.put("cert_num", "");
                            }

                            break;
                        case "账号":
                            if (cell.getType() != CellType.EMPTY) {
                                List<Map<String, Object>> blklist = blklistCtrlDao.queryForList(BaseUtils.map("acct_id", contents));

                                if (blklist != null && blklist.size() > 0) {
                                    sb.append("第").append(i).append("行的'账号'").append(contents).append("，已存在, ");
                                } else {
                                    map.put("acct_id", contents);
                                }
                            } else {
                                map.put("acct_id", "");
                            }
                            break;
                        case "风险等级":
                            if (cell.getType() == CellType.EMPTY) {
                                sb.append("第").append(i).append("行的'风险等级'不可为空, ");
                            } else {
                                map.put("risk_level", Objects.requireNonNull(RiskLevelEnum.findByValue(contents)).getItem());
                            }
                            break;
                        case "控制开始日期":
                            if (cell.getType() != CellType.EMPTY) {

                                if (cell.getType() == CellType.DATE) {
                                    DateCell dc = (DateCell) cell;
                                    Date date = dc.getDate();
                                    String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    map.put("set_ctrl_dt", format);
                                } else {
                                    sb.append("第").append(i).append("行的'控制开始日期'填写不规范, ");
                                }
                            } else {
                                map.put("set_ctrl_dt", "");
                            }
                            break;
                        case "控制截至日期":
                            if (cell.getType() != CellType.EMPTY) {

                                if (cell.getType() == CellType.DATE) {
                                    DateCell dc = (DateCell) cell;
                                    Date date = dc.getDate();
                                    String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    map.put("closing_dt", format);
                                } else {
                                    sb.append("第").append(i).append("行的'控制截至日期'填写不规范, ");
                                }
                            } else {
                                map.put("closing_dt", "");
                            }
                            break;
                    }
                }
                String custInfo = custInfoVerify.checkCustInfo(String.valueOf(map.get("certType")), String.valueOf(map.get("cert_num")));
                if (!"correct".equals(custInfo)) {
                    sb.append("第").append(i).append("行的'证件类型'与'证件号码填写不规范'填写不规范, ");
                }
                map.put("src", "100001");
                map.put("valid_flg", "1");
                map.put("create_time", DateUtils.getDate("yyyy-MM-dd"));
                map.put("id", IdUtil.randomUUID().replace("_", ""));

                returnList.add(map);
            }

            //stream流去重
            list = returnList.stream().distinct().collect(Collectors.toList());
            wb.close();
        } catch (Exception e) {
            logger.info("文件无法读取", e);
            throw new BusinessException("TblackB005ServiceException", "文件无法读取");
        }

        if (StringUtils.hasText(sb)) {
            String s = sb.toString();
            throw new BusinessException("TblackB005ServiceException", s.endsWith(", ") ? s.substring(0, s.length() - 2) + "。" : s);
        }
        //批量保存数据
        saveData(list);

        return writeExcelInfo(returnList, total);
    }


    private void saveData(List<Map<String, Object>> list) {
        try {
            Map<String, Object>[] paramMap = new Map[list.size()];
            for (int i = 0; i < list.size(); i++) {
                paramMap[i] = list.get(i);
            }
            blklistCtrlDao.batchUpdate(paramMap, list.get(0));
        } catch (Exception e) {
            logger.info("表格填写有误，请严格按照要求填写", e);
            throw new BusinessException("TblackB005ServiceException", "问题描述或整改要求填写超出规定字数");
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
