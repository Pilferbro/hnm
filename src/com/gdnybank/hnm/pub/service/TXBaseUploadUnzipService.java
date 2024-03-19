package com.gdnybank.hnm.pub.service;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdnybank.hnm.pub.dao.TFileInfoDao;
import com.gdnybank.hnm.pub.utils.HnmConstants;
import com.gdnybank.hnm.pub.utils.ZipUtil;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseFormService;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * desc: 上传解压公用基类
 * @author:pzz
 * date:2015年12月3日
 * ===========================================
 * desc:增加 beforeUpload 和 handleBeforeUpload 、wrapFileAdditionalInfo 三个方法
 * mdified by pzz 2016年12月12日
 */
public abstract class TXBaseUploadUnzipService extends TXBaseFormService {
	private Logger logger = Logger.getLogger(TXBaseUploadUnzipService.class);

	@Resource
	private SysParamService sysParamService;
	@Resource
	protected TFileInfoDao tFileInfoDao;
	@Resource
	private FilesManageService filesManageService;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private HnmCommService hnmCommService;


	/**
	 * desc:影像资料上传
	 * @author pzz
	 */
	@Override
    public  Object doService(Map<String, Object> env, Map<String, Object> p){
		//影响资料
		if(p.containsKey("files") && p.get("files") != null && p.get("files") instanceof List){
			List<MultipartFile> multipartFileList = (List<MultipartFile>)p.get("files");
			MultipartFile multipartFile = multipartFileList.get(0);//1、获取上传的文件
			String originFileName = multipartFile.getOriginalFilename(); //压缩文件名称
			Map<String, Object> infoMap = parseFileName(originFileName); //2、根据压缩资料名称获取 柜员号、业务流水、业务对应的最后交易的交易码txcode
			infoMap.put("oper_user", p.get("oper_user"));
			if(infoMap == null){
				logger.error(String.format("资料命名不规范[%s]，请遵守[柜员号_业务流水号_交易码]", originFileName));
				throw new BusinessException(MfpContextHolder.getTxcode()+"999", "资料命名不规范");
			}
			infoMap.put("zip_size", multipartFile.getSize()); //压缩包大小
			if(!beforeUpload(infoMap)){//上传前操作
				return handleBeforeUpload(infoMap);
			}
			//3、获取解压文件存放的文件夹
			String dirName = hnmCommService.getDirByInfo(infoMap);
			InputStream is = null;
			try{
				is = multipartFile.getInputStream();
				//4、解压文件到指定文件夹 得到所有文件存放的全路径（路径+文件名+扩展名）
				String allFileFullName = ZipUtil.unzip(is, originFileName,dirName,sysParamService.getSysParam("BASE_CONFIG", "UPZIP_PWD", "nantian"));
				if(StrUtil.isBlank(allFileFullName)){
					throw new BusinessException(MfpContextHolder.getTxcode()+"998", "解压失败");
				}
				infoMap.put("local_file_path", allFileFullName);

				//判断文件存储位置（LOCAL 本地 REMOTE 远程）
				infoMap.put("place", HnmConstants.MEDIA_STATUS_LOCAL); //默认文件存储在本地
				String place = sysParamService.getSysParam("FILE_SYSTEM_INFO", "FILE_PLACE", "LOCAL");
				//在上传至文件服务器后是否删除本地文件  true 是 false 否
				Boolean deleteLocalFileFlag = Boolean.valueOf(sysParamService.getSysParam("FILE_SYSTEM_INFO", "DELETE_LOCAL_FILE_FLAG", "false").trim());
				if(HnmConstants.MEDIA_STATUS_REMOTE.equals(place)){ //存储在远程，直接上传至文件服务器
					File targetDir = new File(dirName);
					if(targetDir.exists() && targetDir.isDirectory()){
						try {
							infoMap.putAll(filesManageService.upload(targetDir.listFiles(), infoMap.get("flow_no").toString(),deleteLocalFileFlag));
						} catch (Exception e) {
							logger.error("上传至文件服务器失败，原因",e);
							throw new BusinessException(MfpContextHolder.getTxcode()+"997", "上传至文件服务器失败");
						}
						infoMap.put("place", HnmConstants.MEDIA_STATUS_REMOTE); //当系统参数配置默认直接上传至文件系统并上传成功后，标志该文件已经存储在文件系统
					}
				}
				//读取文件附加信息：上传的备注、操作生成文件的柜员、授权柜员等
				wrapFileAdditionalInfo(infoMap,dirName);
				afterUpload(infoMap); //将文件路径等信息入库保存记录

			} catch (ZipException e) {
				logger.error("解压资料失败，原因",e);
				throw new BusinessException(MfpContextHolder.getTxcode()+"996", "解压资料失败");
			} catch (IOException e) {
				logger.error("解压资料失败，原因",e);
				throw new BusinessException(MfpContextHolder.getTxcode()+"995", "获取资料失败");
			}finally{
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						logger.error("关闭文件流失败，原因：",e);
					}
				}
			}
		}
		return BaseUtils.map("status","1");  //0:失败 1:成功
	}

	/**
	 * desc:根据压缩文件名获取信息<br/>
	 * 		压缩包的命名规则是：“柜员号”＋“_“＋“业务流水号”＋“_”＋“交易码”
	 * @param fileName 压缩文件名
	 * @return 组装的信息
	 * @author pzz
	 * ==================================================
	 * 影像资料可以补录
	 * 压缩包命名规则改为：“柜员号”＋“_“＋“业务流水号”＋“_”＋“交易码”+“_”+“文件生成时间yyyyMMddHHmmss”
	 * modified by pzz 2017-02-15 15:17
	 */
	private Map<String, Object> parseFileName(String fileName){
		if(StrUtil.isBlank(fileName)){
			return null;
		}

		while(fileName.indexOf(".") != -1){
			fileName = fileName.substring(0,fileName.indexOf("."));
		}

		if(StrUtil.isBlank(fileName)){
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		String[] infoAry = fileName.split("_");
		if(infoAry == null || infoAry.length < 3){
			return null;
		}
		for(String info:infoAry){
			if(StrUtil.isBlank(info)){
				return null;
			}
		}

		map.put("operno", infoAry[0]);
		map.put("busi_seq_no", infoAry[1]);
		map.put("txcode", infoAry[2]);
		//2017-02-17 modified by pzz 文件生成时间 yyyyMMddHHmmss
		if(infoAry.length > 3){
			map.put("generate_time", infoAry[3]);
		}else{
			map.put("generate_time", "");
		}
		return map;
	}



	/**
	 * desc:上传之后的操作
	 * @param infoMap
	 * @return
	 */
	public void afterUpload(Map<String, Object> infoMap) {
		infoMap.put("oper_user", infoMap.get("oper_user")); //上传者
		infoMap.put("oper_time", new Date()); //操作时间
		infoMap.put("create_time", new Date()); //上传时间
//		infoMap.put("local_file_path", infoMap.get("media_paths")); //本地文件路径
		infoMap.put("tx_user", infoMap.get("operno")); //经办柜员
		infoMap.put("generate_time1", infoMap.get("generate_time")); //生成时间，对应文件的生成时间，用于上传文件服务器时，定位文件所在目录（原有交易旧数据该值为空）
		analyzeFileCount(infoMap);
		int insertCount = tFileInfoDao.save(infoMap);
		if(insertCount <= 0){//插入数据失败
//			File file = new File(dirName);
//			if(file.exists()){
//				IOUtil.deleteFile(file);
//			}
			throw new BusinessException(MfpContextHolder.getTxcode()+"994", "保存影像资料信息记录失败");
		}



	}

	/**
	 * desc:上传前的操作
	 * @param p
	 * @return true 继续往下上传 false:执行 handleBeforeUpload
	 */
	public boolean beforeUpload(Map<String, Object> p)throws BusinessException {
		return true;
	}
	/**
	 * desc:上传前的操作
	 * @param p
	 * @return
	 */
	public Map<String, Object> handleBeforeUpload(Map<String, Object> p){
		return BaseUtils.map("status","1");  //0:失败 1:成功
	}

	/**
	 * desc:组装文件的附加信息：如 文件的备注、操作生成文件的柜员、授权柜员等信息
	 * 		从上传的影像资料压缩包中读取 additional_info 的文件，文件内容为json格式，与客户端约定json格式
	 * 		暂定格式：{"generate_user":"XXX","auth_user":"XXX","remark","XXXX"}
	 * @param infoMap 需要组装map
	 * @param fileDir 文件所在目录
	 */
	private void wrapFileAdditionalInfo(Map<String, Object> infoMap,String fileDir){
		File additionalInfoFile = new File(fileDir + File.separator + "additional_info");
		if(additionalInfoFile.exists()){
			FileInputStream fs = null;
			InputStreamReader isr = null;
			BufferedReader br = null;

			try {
				fs = new FileInputStream(additionalInfoFile);
				isr = new InputStreamReader(fs,"UTF-8");
				br = new BufferedReader(isr);
				StringBuffer sb = new StringBuffer("");
				String lineStr = null;
				while(null != (lineStr = br.readLine())){
					sb.append(lineStr);
				}
				String additionalInfo = sb.toString().trim();
				if(StrUtil.isNotBlank(additionalInfo)){
					@SuppressWarnings("unchecked")
					Map<String, Object> additionalMap = objectMapper.readValue(additionalInfoFile, Map.class);
					infoMap.putAll(additionalMap);
				}else{
					log.error("附加信息内容为空");
				}
			} catch (FileNotFoundException e) {
				log.error("附加信息文件不存在,原因：",e);
			}catch(Exception e){
				log.error("读取文件附加信息失败，原因：",e);
			}finally{
				try{
					if(fs != null){
						fs.close();
					}
					if(isr != null){
						isr.close();
					}
					if(br != null){
						br.close();
					}
				}catch(Exception e){
					log.error("关闭读取附加信息文件的流失败，原因：",e);
				}
			}

		}
	}

	/**
	 * desc:分析文件类型数量 图片几张 视频几张 未识别的影像资料文件几个
	 * @param infoMap
	 */
	private void analyzeFileCount(Map<String, Object> infoMap){
		String fileNames = infoMap.get("local_file_path").toString();
		String[] fileNameAry = fileNames.split(ZipUtil.FILENAME_SPLIT);
		int imgCount = 0; // 图片数量
		int vedioCount = 0; //视频数量
		int undefiedCount = 0; //未识别媒体信息数量
		//常用图片后缀
		List<String> imgSuffix= Arrays.asList(sysParamService.getSysParam("FILE_SYSTEM_INFO", "IMG_SUFFIX", "bmp,jpg,jpeg,png,gif,img,pic,tif").toLowerCase().split(","));
		//常用视频后缀
		List<String> vedioSuffix = Arrays.asList(sysParamService.getSysParam("FILE_SYSTEM_INFO", "VEDIO_SUFFIX", "avi,mpg,mov,swf,mp4,mpeg,ram,viv,wmv,mpeg1,mpeg2,mpeg4").toLowerCase().split(","));

		for(String fileName:fileNameAry){
			int suffixStartIndex = fileName.lastIndexOf(".");
			if(suffixStartIndex < 0){
				undefiedCount++;
				continue;
			}
			String suffix = fileName.substring(suffixStartIndex+1);
			suffix = StrUtil.isBlank(suffix)?"":suffix.toLowerCase();
			if(imgSuffix.contains(suffix)){
				imgCount++;
			}else if(vedioSuffix.contains(suffix)){
				vedioCount++;
			}else{
				undefiedCount++;
			}
		}
		infoMap.put("img_count", imgCount);
		infoMap.put("vedio_count", vedioCount);
		infoMap.put("undefied_count", undefiedCount);
	}
}
