package com.gdnybank.hnm.timejob;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HnmCommDao;
import com.gdnybank.hnm.pub.dao.TZnDataHisDao;
import com.gdnybank.hnm.pub.enums.ErrorCodeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.codegen.service.CodeGenService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SysParamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卸数助农数据
 */
@Service
public class LoadZNDataJob extends BaseTimeJob {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TZnDataHisDao tZnDataHisDao;
    @Resource
    private HnmCommService hnmCommService;
    @Resource
    private SysParamService sysParamService;
    @Autowired
    private CodeGenService codeGenService;
    @Autowired
    private HnmCommDao hnmCommDao;
    @Autowired
    private HSiteDao hSiteDao;

    private static final String CUPSTS = "CUPSTS";
    private static final String CUPSTS_FILE = "znp0_cupsts";

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“LoadZNDataJob”开始执行");
            loadXGData();
            logger.info("定时任务“LoadZNDataJob”执行结束");

            return null;
        }
    }

    private boolean loadXGData() {
        //获取当前日期(跑批次日期)
        String batchDate = DateUtils.getDate("yyyyMMdd");
        //查询卸数记录表记录最大日期（针对每个卸数表）  暂定所有表数据都有了再卸数
        List<Map<String, Object>> list = tZnDataHisDao.queryForListMaxCreateDate(BaseUtils.map());
        if (list != null && list.size() > 0) {
            if (ObjectUtil.isNotEmpty(list.get(0).get("max_date"))) {
                String max_date = String.valueOf(list.get(0).get("max_date"));
                if (batchDate.equals(max_date)) {
                    return true;
                } else {
                    //获取最大日期的下一天
                    batchDate = DateUtils.format(DateUtils.addDay(DateUtils.parse(max_date, "yyyyMMdd"), 1),
                            "yyyyMMdd");
                }
            }
        }

        //nasPath 由于大数据平台卸数 故文件夹共享
        String nasBasePath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_BASE_DIR", "/nas/bdp");
        String nasZNPath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_INPUT_DIR", "input");
        String nasPath = hnmCommService.appendDir(nasBasePath, nasZNPath);
        File fileDir = new File(hnmCommService.appendDir(nasPath, batchDate));
        File[] files = fileDir.listFiles();
        String name1 = CUPSTS_FILE + "." + batchDate + ".dat";
        String name2 = CUPSTS_FILE + "." + batchDate + ".ok";
        int num = 0;

        if (files != null && files.length > 0) {
            for (File file : files) {
                String fileName = file.getName();
                if (name1.equals(fileName) || name2.equals(fileName)) {
                    num++;
                }
            }
        } else {
            logger.info("定时任务“LoadZNDataJob”未找到指定文件");
        }

        //暂定所有表数据都有了再卸数
        if (num == 2) {
            String readerCode = sysParamService.getSysParam("NAS_CONFIG", "READER_CODE", "UTF-8");
            String splitStr = sysParamService.getSysParam("NAS_CONFIG", "SPLIT_STR", "0x1b");
            byte[] splitByte = {Byte.decode(splitStr)};
            splitStr = new String(splitByte);
            for (File file : files) {
                String fileName = file.getName();
                if (name1.equals(fileName)) {
                    fileInTable(CUPSTS, file.getAbsolutePath(), readerCode, splitStr);
                }
            }
            //执行保存卸数记录表
            Map<String, Object> parms = new HashMap<>();
            parms.put("create_date", batchDate);
            parms.put("create_time", DateUtils.getDate("HHmmss"));
            parms.put("data_name", CUPSTS);
            tZnDataHisDao.save(parms);
        }
        return true;
    }

    public boolean fileInTable(String tableName, String localFile, String readerCode, String splitStr) {
        //获取该表的所有字段
        String[] columns = codeGenService.getTableColumnsName(tableName);

        List<Map<String, Object>> batchValues = new ArrayList<Map<String, Object>>();
        int batchCommitSize = 5000;
        try {
            batchCommitSize = Integer.parseInt(sysParamService.getSysParam("system", "BATCH_COMMIT_SIZE", "5000"));
        } catch (Exception e) {
            logger.error("获取系统参数：批量导入提交次数，失败，原因：", e);
        }

        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader reader = null;
        try {
            //根据传入的编码值来读取文件
            fi = new FileInputStream(localFile);
            is = new InputStreamReader(fi, readerCode);
            reader = new BufferedReader(is);
            String lienStr;
            int lineSize = 0;
            //逐行处理数据
            while (null != (lienStr = reader.readLine())) {
                String[] strs = lienStr.split(splitStr);
                Map<String, Object> lineMap = new HashMap<String, Object>();

                if (columns.length >= strs.length) {
                    for (int i = 0; i < strs.length; i++) {
                        lineMap.put(columns[i], strs[i].trim());
                    }
                    //当获取的值个数小于列的个数时，后续值赋值为空，避免以二进制字符为分隔符时，末尾丢失的情况发生
                    for (int i = 0; i < columns.length - strs.length; i++) {
                        //第一列 为站点编号
                        if (i == 0) {
                            //获取序号
                            List<Map<String, Object>> list = hSiteDao.queryForList(BaseUtils.map("is_delete", "0",
                                    "approval_status", "2", "terminal_no", lineMap.get("f041")));
                            if (list != null && list.size() > 0 && list.get(0).get("site_no") != null) {
                                lineMap.put(columns[strs.length + i], list.get(0).get("site_no"));
                            } else {
                                lineMap.put(columns[strs.length + i], "");
                            }
                        } else {
                            lineMap.put(columns[strs.length + i], "");
                        }
                    }
                } else {
                    for (int i = 0; i < columns.length; i++) {
                        lineMap.put(columns[i], strs[i].trim());
                    }
                }
                batchValues.add(lineMap);
                lineSize++;
                if (batchValues.size() == batchCommitSize) { //待提交列表中记录数已经达到配置的 提交记录数，先提交一次,防止文件数据记录数太多，一次性提交失败
                    hnmCommDao.batchUpdate(tableName, columns, batchValues);
                    batchValues.clear();
                }
            }
            logger.info("处理文件 " + localFile + " ,共读取 " + lineSize + " 行数据。");
        } catch (FileNotFoundException e) { //本地文件 %s不存在,请查证
            BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService026, localFile);
            logger.error(exception.getMessage(), e);
            throw exception;
        } catch (IOException e) { //读取本地文件 %s失败
            BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService027, localFile);
            logger.error(exception.getMessage(), e);
            throw exception;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("关闭流失败，原因：", e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("关闭流失败，原因：", e);
                }
            }
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    logger.error("关闭流失败，原因：", e);
                }
            }
        }
        if (batchValues.size() > 0) { //batchValues 还有未提交的数据，最后再次提交一次
            hnmCommDao.batchUpdate(tableName, columns, batchValues);
        }
        return true;
    }
}
