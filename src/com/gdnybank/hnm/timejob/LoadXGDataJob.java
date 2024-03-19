package com.gdnybank.hnm.timejob;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
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
import java.util.*;

/**
 * 卸数销管数据
 */
@Service
public class LoadXGDataJob extends BaseTimeJob{
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TXgDataHisDao tXgDataHisDao;
	@Resource
	private HnmCommService hnmCommService;
	@Resource
	private SysParamService sysParamService;
	@Autowired
	private CodeGenService codeGenService;
	@Autowired
	private TtimpntDao ttimpntDao;
	@Autowired
	private TperprpDao tperprpDao;
	@Autowired
	private ThfacctDao thfacctDao;
	@Autowired
	private ThfclitDao thfclitDao;
	@Autowired
	private TclientDao tclientDao;
	@Autowired
	private TtimpntTempDao ttimpntTempDao;
	@Autowired
	private TperprpTempDao tperprpTempDao;
	@Autowired
	HnmCommDao hnmCommDao;

	private static final String TTIMPNT = "TTIMPNT";
	private static final String TPERPRP = "TPERPRP";
	private static final String THFACCT = "THFACCT";
	private static final String THFCLIT = "THFCLIT";
	private static final String TCLIENT = "TCLIENT";

	private static final String TTIMPNT_TEMP = "TTIMPNT_TEMP";
	private static final String TPERPRP_TEMP = "TPERPRP_TEMP";

	private static final String TTIMPNT_FILE = "crm0_ttimpnt_to_hnms";
	private static final String TPERPRP_FILE = "crm0_tperprp_to_hnms";
	private static final String THFACCT_FILE = "crm0_thfacct";
	private static final String THFCLIT_FILE = "crm0_thfclit";
	private static final String TCLIENT_FILE = "crm0_tclient_to_hnms";

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p){
		synchronized(this){
			logger.info("定时任务“LoadXGDataJob”开始执行");
			loadXGData();
			logger.info("定时任务“LoadXGDataJob”执行结束");

			return null;
		}
	}

	private boolean loadXGData(){
		//获取当前日期(跑批次日期)
		String batchDate = DateUtils.getDate("yyyyMMdd");
		//查询卸数记录表记录最大日期（针对每个卸数表）  暂定所有表数据都有了再卸数
		List<Map<String, Object>> list = tXgDataHisDao.queryForListMaxCreateDate(BaseUtils.map());
		if(list != null && list.size() > 0){
			if(ObjectUtil.isNotEmpty(list.get(0).get("max_date"))){
				String max_date = String.valueOf(list.get(0).get("max_date"));
				if(batchDate.equals(max_date)){
					return true;
				}else{
					//获取最大日期的下一天
					batchDate = DateUtils.format(DateUtils.addDay(DateUtils.parse(max_date, "yyyyMMdd"), 1),
							"yyyyMMdd");
				}
			}
		}

		//nasPath 由大数据平台卸数
		String nasBasePath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_BASE_DIR","/nas/bdp");
		String nasXGPath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_INPUT_DIR","input");
		String nasPath = hnmCommService.appendDir(nasBasePath,nasXGPath);
		File fileDir = new File(hnmCommService.appendDir(nasPath,batchDate));
		if(fileDir != null){
			File[] files = fileDir.listFiles();

			String name1 = TTIMPNT_FILE + "."+ batchDate+".dat";
			String name2 = TPERPRP_FILE + "."+ batchDate+".dat";
			String name3 = THFACCT_FILE + "."+ batchDate+".dat";
			String name4 = THFCLIT_FILE + "."+ batchDate+".dat";
			String name5 = TTIMPNT_FILE + "."+ batchDate+ ".ok";
			String name6 = TPERPRP_FILE + "."+ batchDate+ ".ok";
			String name7 = THFACCT_FILE + "."+ batchDate+ ".ok";
			String name8 = THFCLIT_FILE + "."+ batchDate+ ".ok";

			String name9 = TCLIENT_FILE + "."+ batchDate+".dat";
			String name10 = TCLIENT_FILE + "."+ batchDate+ ".ok";

			int num = 0;
			if(files != null && files.length > 0){
				for (File file : files){
					String fileName = file.getName();
					if(name1.equals(fileName) || name2.equals(fileName) ||name3.equals(fileName) ||name4.equals(fileName) ||
							name5.equals(fileName) ||name6.equals(fileName) ||name7.equals(fileName) ||name8.equals(fileName) ||name9.equals(fileName) ||name10.equals(fileName)){
						num ++;
					}
				}
			}else{
				logger.info("定时任务“LoadXGDataJob”未找到指定文件");
			}

			//暂定所有表数据都有了再卸数
			if(num == 10){
				String readerCode = sysParamService.getSysParam("NAS_CONFIG", "READER_CODE", "UTF-8");
				String splitStr = sysParamService.getSysParam("NAS_CONFIG", "SPLIT_STR", "0x1b");
				byte[] splitByte = {Byte.decode(splitStr)};
				splitStr = new String(splitByte);
				for (File file : files){
					String fileName = file.getName();
					if(name1.equals(fileName)){
						//清空临时表“TTIMPNT_TEMP”的数据
						ttimpntTempDao.truncate();
						fileInTable(TTIMPNT_TEMP, file.getAbsolutePath(),readerCode,splitStr);
						//同步“TTIMPNT_TEMP”表中的数据到“TTIMPNT”中
						ttimpntDao.synByTempData();
					}
					if(name2.equals(fileName)){
						//清空临时表“TPERPRP_TEMP”的数据
						tperprpTempDao.truncate();
						//把文件中的数据入库
						fileInTable(TPERPRP_TEMP, file.getAbsolutePath(),readerCode,splitStr);
						//同步“TPERPRP_TEMP”表中的数据到“TPERPRP”中
						tperprpDao.synByTempData();
					}
					if(name3.equals(fileName)){
						//清空表的数据
						thfacctDao.truncate();
						//把文件中的数据入库
						fileInTable(THFACCT, file.getAbsolutePath(),readerCode,splitStr);
					}
					if(name4.equals(fileName)){
						//清空表的数据
						thfclitDao.truncate();
						//把文件中的数据入库
						fileInTable(THFCLIT, file.getAbsolutePath(),readerCode,splitStr);
					}
					if(name9.equals(fileName)){
						//清空表的数据
						tclientDao.truncate();
						//把文件中的数据入库
						fileInTable(TCLIENT, file.getAbsolutePath(),readerCode,splitStr);
					}
				}
				//执行保存卸数记录表
				Map<String,Object> parms = new HashMap<>();
				parms.put("create_date",batchDate);
				parms.put("create_time",DateUtils.getDate("HHmmss"));
				parms.put("data_name",TTIMPNT+","+TPERPRP+","+THFACCT+","+THFCLIT+","+TCLIENT);
				tXgDataHisDao.save(parms);
			}
		}
		return true;
	}

	public boolean fileInTable(String tableName,String localFile,String readerCode, String splitStr){
		//获取该表的所有字段
		String[] columns = codeGenService.getTableColumnsName(tableName);

		List<Map<String ,Object>> batchValues = new ArrayList<Map<String ,Object>>();
		int batchCommitSize = 5000;
		try{
			batchCommitSize = Integer.valueOf(sysParamService.getSysParam("system", "BATCH_COMMIT_SIZE", "5000"));
		}catch(Exception e){
			logger.error("获取系统参数：批量导入提交次数，失败，原因：",e);
		}

		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader reader = null;
		try {
			//根据传入的编码值来读取文件
			fi = new FileInputStream(localFile);
			is = new InputStreamReader(fi,readerCode);
			reader = new BufferedReader(is);
			String lienStr = "";
			int lineSize = 0;
			//逐行处理数据
			while(null != (lienStr = reader.readLine())){
				String[] strs = lienStr.split(splitStr);
				Map<String , Object> lineMap = new HashMap<String , Object>();

				if(columns.length >= strs.length){
					for(int i = 0 ; i < strs.length ; i++){
						lineMap.put(columns[i], strs[i].trim());
					}
					//当获取的值个数小于列的个数时，后续值赋值为空，避免以二进制字符为分隔符时，末尾丢失的情况发生
					for(int i = 0 ; i< columns.length - strs.length ; i++){
						lineMap.put(columns[strs.length + i], "");
					}
				}else{
					for(int i = 0 ; i < columns.length ; i++){
						lineMap.put(columns[i], strs[i].trim());
					}
				}
				batchValues.add(lineMap);
				lineSize++;
				if(batchValues.size() == batchCommitSize){ //待提交列表中记录数已经达到配置的 提交记录数，先提交一次,防止文件数据记录数太多，一次性提交失败
					hnmCommDao.batchUpdate(tableName,columns, batchValues);
					batchValues.clear();
				}
			}
			logger.info("处理文件 "+localFile+" ,共读取 "+lineSize+" 行数据。");
		} catch (FileNotFoundException e) { //本地文件 %s不存在,请查证
			BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService026,localFile);
			logger.error(exception.getMessage(),e);
			throw exception;
		} catch (IOException e) { //读取本地文件 %s失败
			BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService027,localFile);
			logger.error(exception.getMessage(),e);
			throw exception;
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭流失败，原因：",e);
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭流失败，原因：",e);
				}
			}
			if(fi != null){
				try {
					fi.close();
				} catch (IOException e) {
					logger.error("关闭流失败，原因：",e);
				}
			}
		}
		if(batchValues.size() > 0){ //batchValues 还有未提交的数据，最后再次提交一次
			hnmCommDao.batchUpdate(tableName,columns, batchValues);
		}
		return true;
	}
}
