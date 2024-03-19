package com.gdnybank.hnm.comm.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.service.FilesManageService;
import com.gdnybank.hnm.pub.utils.HnmConstants;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import com.sunyard.ecm.server.bean.FileBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.*;

import static com.nantian.mfp.framework.context.MfpContextHolder.getRequest;


/**
 * @description:文件上传
 * @author: wxm
 */
@Service
public class Tupload001Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tupload001Service.class);

    @Autowired
    private HFileInfoDao hFileInfoDao;
    @Autowired
    private SysParamService sysParamService;
    @Resource
    private FilesManageService filesManageService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        List<Map<String,Object>> list = new ArrayList<>();
        List<MultipartFile> multipartFileList = (List<MultipartFile>) p.get("files");
        if (multipartFileList == null || multipartFileList.size() == 0) {
            logger.error("上传文件为空");
            throw new BusinessException("Tupload001ServiceException", "上传文件为空");
        } else {
            for (MultipartFile file : multipartFileList){
                // 获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀
                String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
                //校验文件后缀
                String file_suffix = sysParamService.getSysParam("FILE_SYSTEM_INFO", "FILE_SUFFIX", "");
                if(!file_suffix.contains(prefix.trim().toLowerCase())){
                    logger.error("上传文件格式错误");
                    throw new BusinessException("Tupload001ServiceException", "上传文件格式错误");
                }
                //保存于服务器 一期保存于项目目录下
                StringBuffer dirName = new StringBuffer();
                String baseDirName = sysParamService.getSysParam("BASE_CONFIG", "PROJECT_BASE_DIR", "/home/weblogic/hnm/hnm_dom/applications/hnm");

                String userId = MfpContextHolder.getLoginId();
                String business = String.valueOf(p.get("business"));
                String date = DateUtil.format(new Date(),"yyyyMMdd");
                //按照规则生成id
                String id = IdUtil.randomUUID().replace("-", "");
                //按照规则生成file_id
                String busi_seq_no = IdUtil.randomUUID().replace("-", "");

                StringBuffer temBaseFileName = new StringBuffer();
                temBaseFileName = temBaseFileName.append("media").append(File.separator).append(business).append(File.separator).append(userId).append(File.separator).append(date).append(File.separator).append(busi_seq_no);
                String baseFileName = temBaseFileName.toString();

                dirName.append(baseDirName).append(File.separator).append(baseFileName);

                String filePath = dirName + File.separator + fileName;

                FileOutputStream fos = null;
                try {
                    //打开FileOutStrean流
                    fos = FileUtils.openOutputStream(new File(filePath));
                    //将MultipartFile file转成二进制流并输入到FileOutStream
                    IOUtils.copy(file.getInputStream(),fos);

                    //保存至文件表
                    Map<String,Object> parms = new HashMap<>();
                    parms.put("id", id);
                    parms.put("busi_seq_no", busi_seq_no);
                    //判断文件存储位置（LOCAL 本地 REMOTE 远程）
                    parms.put("place", HnmConstants.MEDIA_STATUS_LOCAL); //默认文件存储在本地
                    //一期保存于本地 并保存于项目下
                    String place = sysParamService.getSysParam("FILE_SYSTEM_INFO", "FILE_PLACE", "LOCAL");
                    //在上传至文件服务器后是否删除本地文件  true 是 false 否
                    Boolean deleteLocalFileFlag = Boolean.valueOf(sysParamService.getSysParam("FILE_SYSTEM_INFO", "DELETE_LOCAL_FILE_FLAG", "false").trim());
                    if(HnmConstants.MEDIA_STATUS_REMOTE.equals(place)){ //存储在远程，直接上传至文件服务器
                        File targetDir = new File(dirName.toString());
                        if(targetDir.exists() && targetDir.isDirectory()){
                            try {
                                parms.putAll(filesManageService.upload(targetDir.listFiles(), parms.get("busi_seq_no").toString(),deleteLocalFileFlag));
                            } catch (Exception e) {
                                logger.error("上传至文件服务器失败，原因",e);
                                throw new BusinessException(MfpContextHolder.getTxcode(), "上传至文件服务器失败");
                            }
                            parms.put("place", HnmConstants.MEDIA_STATUS_REMOTE); //当系统参数配置默认直接上传至文件系统并上传成功后，标志该文件已经存储在文件系统
                        }
                    }

                    parms.put("business",business);
                    parms.put("file_type",p.get("file_type"));
                    parms.put("code",p.get("code"));
                    parms.put("upload_user_id",userId);
                    parms.put("file_num","1");
                    parms.put("local_file_path",filePath);
                    String createTime = DateUtil.now();
                    parms.put("create_time", createTime);
                    parms.put("update_time", createTime);
                    parms.put("is_delete", "0");
                    //获取访问url
                    String file_url = "/hnm/" + baseFileName + File.separator + fileName;
                    parms.put("file_url", file_url);
                    hFileInfoDao.save(parms);

                    //获取访问url
                    /*if(HnmConstants.MEDIA_STATUS_REMOTE.equals(place) && ObjectUtil.isNotEmpty(parms.get("batchid"))){
                        long start = System.currentTimeMillis();
                        Object returnObj = filesManageService.queryFilePathBybatchId(
                                parms.get("batchid").toString(),
                                changeTimePattern(parms.get("create_time").toString()));
                        logger.debug("查询影像资料耗时："
                                + (System.currentTimeMillis() - start));
                        logger.debug("返回的信息为：" + returnObj.toString());
                        List<FileBean> fileBeanList = null;
                        if (returnObj instanceof List) {
                            fileBeanList = (List<FileBean>) returnObj;
                        }
                        if (fileBeanList != null && fileBeanList.size() > 0) {
                            if (fileBeanList.get(0).getFileFormat().equals("mp4")) {
                                parms.put("file_url", URLEncoder.encode(fileBeanList.get(0).getUrl(), "UTF-8"));
                            } else{
                                parms.put("file_url", fileBeanList.get(0).getUrl());
                            }
                        }
                    }*/

                    list.add(parms);
                }catch (Exception e){
                    logger.error("上传文件失败");
                    //删除文件
                    FileUtils.deleteQuietly(new File(filePath));
                    throw new BusinessException("Tupload001ServiceException", "上传文件失败");
                }finally {
                    try {
                        fos.close();
                    }catch (Exception e){
                        logger.debug("上传文件流关闭异常");
                    }
                }
            }
        }
        return list;
    }

    private String changeTimePattern(String operTime) {
        String[] s = operTime.split("-");
        String operDate = s[0] + s[1] + s[2].substring(0, 2);
        return operDate;
    }
}
