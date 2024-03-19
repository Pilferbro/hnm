package com.gdnybank.hnm.timejob;

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
public class LoadXGDataTestJob extends BaseTimeJob{
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

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p){

		logger.info("----------LoadMediaData1Job开始-------------");

		//nas目录
		String nasBasePath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_BASE_DIR","/home/nas1");
		String nasXGPath = sysParamService.getSysParam("NAS_CONFIG", "FILE_NAS1_XG_DIR","xg");
		//得到今天的日期
		String date = DateUtils.getDate(new Date(),"yyyyMMdd");
		Map<String, Object> qMap = new HashMap<String, Object>();
		//查询
		List<Map<String, Object>> list = thfclitDao.queryForList(qMap);
		List<String> dataList = new ArrayList<String>();
		if(list!=null&&list.size()>0) {
			//获取数据分隔符
			String splitStr = sysParamService.getSysParam("NAS_CONFIG", "SPLIT_STR", "0x1b");
			byte[] splitByte = {Byte.decode(splitStr)};
			splitStr = new String(splitByte);
			for (Map<String, Object> m : list) {
				String line = isStrEmpty(String.valueOf(m.get("cltnbr")))+splitStr+isStrEmpty(String.valueOf(m.get("flag")))+splitStr+isStrEmpty(String.valueOf(m.get("branno")))
						+splitStr+isStrEmpty(String.valueOf(m.get("crmflag")))+splitStr+isStrEmpty(String.valueOf(m.get("isstock")))+splitStr+isStrEmpty(String.valueOf(m.get("iscancel")))
						+splitStr+isStrEmpty(String.valueOf(m.get("disttype")))+splitStr+isStrEmpty(String.valueOf(m.get("feedtype")))+splitStr+isStrEmpty(String.valueOf(m.get("proeftdate")))
						+splitStr+isStrEmpty(String.valueOf(m.get("proexpdate")))+splitStr+isStrEmpty(String.valueOf(m.get("cardno")))+splitStr+isStrEmpty(String.valueOf(m.get("cardopendate")))
						+splitStr+isStrEmpty(String.valueOf(m.get("quaraccum")))+splitStr+isStrEmpty(String.valueOf(m.get("quardate")))+splitStr+isStrEmpty(String.valueOf(m.get("ismeetcon")))
						+splitStr+isStrEmpty(String.valueOf(m.get("clttag")))+splitStr+isStrEmpty(String.valueOf(m.get("opendate")))+splitStr+isStrEmpty(String.valueOf(m.get("clttype")))
						+splitStr+isStrEmpty(String.valueOf(m.get("chrstatus")))+splitStr+isStrEmpty(String.valueOf(m.get("entrusttag")))+splitStr+isStrEmpty(String.valueOf(m.get("fbackid")))
						+splitStr+isStrEmpty(String.valueOf(m.get("fircrtdate")))+splitStr+isStrEmpty(String.valueOf(m.get("changedate")))+splitStr+isStrEmpty(String.valueOf(m.get("changetime")))
						+splitStr+isStrEmpty(String.valueOf(m.get("remark")))+splitStr+isStrEmpty(String.valueOf(m.get("reserv20")))+splitStr+isStrEmpty(String.valueOf(m.get("reserv30")))
						+splitStr+isStrEmpty(String.valueOf(m.get("reserv40")))+splitStr+isStrEmpty(String.valueOf(m.get("remark1")))+splitStr+isStrEmpty(String.valueOf(m.get("remark2")))
						+splitStr+isStrEmpty(String.valueOf(m.get("remark3")))+splitStr+isStrEmpty(String.valueOf(m.get("rcdver")))+splitStr+isStrEmpty(String.valueOf(m.get("rcdstatus")))
						;
				dataList.add(line);
			}
		}

		//创建目录
		String path = nasBasePath+File.separator+nasXGPath+File.separator+date;
		createDir(path);

		//生成.data文件
		String dataFilePath = path+File.separator+"THFCLIT.dat";
		prinFile(dataList,dataFilePath);

		//生成.ok文件
		List<String> okList = new ArrayList<String>();
		String tag = "f";
		long size = getFileSize(dataFilePath);
		int num = dataList.size();
		String ok = tag+" "+size+" "+num+" "+date;
		okList.add(ok);
		String okFilePath = path+File.separator+"THFCLIT.ok";
		prinFile(okList,okFilePath);
		logger.info("----------LoadMediaData1Job结束-------------");
		return null;
	}

	//判断是否为空
	private String isStrEmpty(String str){
		if(str == null){
			return "";
		}
		if("null".equals(str)){
			return "";
		}
		return str.trim();
	}

	/**
	 * 写入文件
	 * @param list 写入内容（每个元素为一行内容）
	 * @param filePath 文件路径
	 */
	private void prinFile(List<String> list,String filePath){
		PrintWriter print = null;
		try{
			log.info("开始写入文件【"+filePath+"】");
			File file = createFile(filePath);
			print = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
			for(String line : list){
				print.println(line);
			}
			log.info("写入文件【"+filePath+"】结束");
		}catch(Exception e){
			log.error("写入文件报错",e);
			e.printStackTrace();
		}finally {
			print.close();
		}
	}

	/**
	 * 生成文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private File createFile(String filePath) throws IOException{
		File file = null;
		file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
			log.info("变更文件【"+filePath+"】权限");
			//Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","chmod 777 " + filePath});
		}
		return file;
	}

	/**
	 * 生成目录
	 *
	 * @param dirPath
	 */
	private void createDir(String dirPath){
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
			try {
				log.info("变更目录权限");
				//Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","chmod 777 -R " + dirPath});
			} catch (Exception e) {
				log.error("变更目录权限报错", e);
			}
		}
	}

	/**
	 * 获取字节数
	 * @param filename
	 * @return
	 */
	private long getFileSize(String filename) {
		File file = new File(filename);
		if (!file.exists() || !file.isFile()) {
			log.info("文件不存在");
			return -1;
		}
		return file.length();
	}
}
