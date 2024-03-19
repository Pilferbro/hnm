package com.gdnybank.hnm.timejob;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ErrorCodeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.itextpdf.text.pdf.PRIndirectReference;
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
import java.util.*;

/**
 * 卸数大数据黑名单数据
 */


@Service
public class LoadBlackBookDataJob extends BaseTimeJob {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TBlDataHisDao tBlDataHisDao;
    @Resource
    private HnmCommService hnmCommService;
    @Resource
    private SysParamService sysParamService;
    @Autowired
    private CodeGenService codeGenService;
    @Autowired
    private BlklistCtrlTempDao blklistCtrlTempDao;
    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private HnmCommDao hnmCommDao;
    @Autowired
    private DepAcctBasicDao depAcctBasicDao;
    @Autowired
    private DepAcctBasicTempDao depAcctBasicTempDao;

    private static final String BLKLIST_CTRL = "BLKLIST_CTRL";
    private static final String BLKLIST_CTRL_TEMP = "hnms_blklist_ctrl_info";
    private static final String DEP_ACCT_BASIC = "DEP_ACCT_BASIC";
    private static final String DEP_ACCT_BASIC_TEMP = "hnms_dep_acct_basic_info";

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        synchronized (this) {
            logger.info("定时任务“LoadBlackBookDataJob”开始执行");
            loadBlackBookData();
            logger.info("定时任务“LoadBlackBookDataJob”执行结束");

            return null;
        }
    }

    private void loadBlackBookData() {
        //获取当前日期(跑批次日期)
        String batchDate = DateUtils.getDate("yyyyMMdd");
        //查询卸数记录表记录最大日期（针对每个卸数表）  暂定所有表数据都有了再卸数
        List<Map<String, Object>> list = tBlDataHisDao.queryForListMaxCreateDate(BaseUtils.map());
        if (list != null && list.size() > 0) {
            if (ObjectUtil.isNotEmpty(list.get(0).get("max_date"))) {
                String max_date = String.valueOf(list.get(0).get("max_date"));
                if (batchDate.equals(max_date)) {
                    return;
                } else {
                    //获取最大日期的下一天
                    batchDate = DateUtils.format(DateUtils.addDay(DateUtils.parse(max_date, "yyyyMMdd"), 1),
                            "yyyyMMdd");
                }
            }
        }
        logger.info("执行文件夹"+batchDate);
        //nasPath 由大数据平台卸数
        String nasBasePath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_BASE_DIR", "/nas/bdp");
        String nasXGPath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_BLACK_BOOK_DIR", "input");
        String nasPath = hnmCommService.appendDir(nasBasePath, nasXGPath);
        File fileDir = new File(hnmCommService.appendDir(nasPath, batchDate));
        File[] files = fileDir.listFiles();

        String name1 = BLKLIST_CTRL_TEMP + "." + batchDate + ".dat";
        String name2 = BLKLIST_CTRL_TEMP + "." + batchDate + ".ok";
        String name3 = DEP_ACCT_BASIC_TEMP + "." + batchDate + ".dat";
        String name4 = DEP_ACCT_BASIC_TEMP + "." + batchDate + ".ok";

        if (files != null && files.length > 0) {

            int count = 0;
            for (File file : files) {

                String fileName = file.getName();
                if (name1.equals(fileName) || name2.equals(fileName) || name3.equals(fileName) || name4.equals(fileName)) {
                    count++;
                }
            }

            //暂定所有表数据都有了再卸数
            if (count == 4) {
                String readerCode = sysParamService.getSysParam("NAS_CONFIG", "READER_CODE", "UTF-8");
                String splitStr = sysParamService.getSysParam("NAS_CONFIG", "SPLIT_STR", "0x1b");
                byte[] splitByte = {Byte.decode(splitStr)};
                splitStr = new String(splitByte);
                for (File file : files) {
                    if (file.getName().equals(name1)) {
                        //清空临时表“BLKLIST_CTRL_TEMP”的数据
                        blklistCtrlTempDao.truncate();
                        fileInTable("BLKLIST_CTRL_TEMP", file.getAbsolutePath(), readerCode, splitStr);
                        //删除临时表“BLKLIST_CTRL_TEMP”的重复数据
                        blklistCtrlTempDao.deleteRepData();
                        //同步“BLKLIST_CTRL_TEMP”表中的数据到“BLKLIST_CTRL”中
                        blklistCtrlDao.synByTempData();
                    }
                    if (file.getName().equals(name3)) {
                        //清空临时表“DEP_ACCT_BASIC_TEMP”的数据
                        depAcctBasicTempDao.truncate();
                        fileInTable("DEP_ACCT_BASIC_TEMP", file.getAbsolutePath(), readerCode, splitStr);
                        //删除临时表“DEP_ACCT_BASIC_TEMP”的重复数据
                        depAcctBasicTempDao.deleteRepData();
                        //同步“DEP_ACCT_BASIC_TEMP”表中的数据到“DEP_ACCT_BASIC”中
                        depAcctBasicDao.synByTempData();
                        //将风险等级为7，8级的账户信息同步到BLKLIST_CTRL
                        blklistCtrlDao.synByDepAcctBasicData();
                    }
                }

                //执行保存卸数记录表
                Map<String, Object> parms = new HashMap<>();
                parms.put("create_date", batchDate);
                parms.put("create_time", DateUtils.getDate("HHmmss"));
                parms.put("data_name", BLKLIST_CTRL + "," + DEP_ACCT_BASIC);
                tBlDataHisDao.save(parms);
            } else {
                logger.info("定时任务“LoadBlackBookDataJob”未找到指定文件");
            }

        } else {
            logger.info("定时任务“LoadBlackBookDataJob”未找到指定文件");
        }
    }

    public boolean fileInTable(String tableName, String localFile, String readerCode, String splitStr) {
        //获取该表的所有字段
        String[] columns = codeGenService.getTableColumnsName(tableName);

        List<Map<String, Object>> batchValues = new ArrayList<>();
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
                Map<String, Object> lineMap = new HashMap<>();

                if (columns.length >= strs.length) {
                    for (int i = 0; i < strs.length; i++) {
                        if (strs[i].getBytes().length > 4000) {
                            strs[i] = strs[i].substring(0, 4000);
                        }
                        lineMap.put(columns[i], strs[i].trim());
                    }
                    //当获取的值个数小于列的个数时，后续值赋值为空，避免以二进制字符为分隔符时，末尾丢失的情况发生
                    for (int i = 0; i < columns.length - strs.length; i++) {
                        lineMap.put(columns[strs.length + i], "");
                    }
                } else {
                    for (int i = 0; i < columns.length; i++) {
                        if (strs[i].getBytes().length > 4000) {
                            strs[i] = strs[i].substring(0, 4000);
                        }
                        lineMap.put(columns[i], strs[i].trim());
                    }
                }

                lineMap.put("id", IdUtil.randomUUID().replace("-", ""));
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
