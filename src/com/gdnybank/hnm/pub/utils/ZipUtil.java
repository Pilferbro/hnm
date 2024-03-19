package com.gdnybank.hnm.pub.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * desc: 解压文件工具类
 * user:pzz 
 * date:2015年10月22日
 */
public class ZipUtil {
	
	private static Logger logger = Logger.getLogger(ZipUtil.class);
	
	
	/**
	 * 设置缓冲值
	 */
	private static final int IO_BUFFER = 8192;
	
	private static final int CRYPT_BUFFER = 8192;

	private static final String ALGORITHM = "PBEWithMD5AndDES";
	
	public static final String FILENAME_SPLIT=",";
	
	
	/**
	 * 功能描述：将压缩文件解压到指定的文件目录下
	 * @param originInputStream 压缩文件流
	 * @param outputDirectory 指定解压目录
	 * @throws ArchiveException
	 * @return 所有 压缩文件存放全路径（路径+文件名+扩展名），逗号分隔
	 */
	public static String unzip(InputStream originInputStream, String outputDirectory)
			throws ArchiveException {
		return unzip(originInputStream, outputDirectory, null);
	}

	/**
	 * 功能描述：将压缩文件解压到指定的文件目录下
	 * @param originInputStream 压缩文件流
	 * @param outputDirectory 指定解压目录
	 * @param pwd 解压密码
	 * @throws ArchiveException
	 * @return 所有 压缩文件存放全路径（路径+文件名+扩展名），逗号分隔
	 */
	public static String unzip(InputStream originInputStream, String outputDirectory, String pwd)
			throws ArchiveException {
		ArchiveInputStream inputStream = null;
		try {
			inputStream =   new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP, originInputStream);
			return unzip(inputStream, outputDirectory, pwd);
		} catch (FileNotFoundException e) {
			throw new ArchiveException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ArchiveException(e.getMessage(), e);
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				throw new ArchiveException(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 功能描述：递归解压文件到指定输出目录
	 * @param inputStream zip文档输入流
	 * @param outputDirectory 指定输出目录
	 * @param pwd 解压密码
	 * @throws ArchiveException
	 * @throws IOException
	 * @return 所有 压缩文件存放全路径（路径+文件名+扩展名），逗号分隔
	 */
	private static String unzip(ArchiveInputStream inputStream,
			String outputDirectory, String pwd) throws ArchiveException, IOException {
		ArchiveEntry archiveEntry = null;
		StringBuffer fileFullNameBuffer = new StringBuffer(); //解压后文件全名称（路径+文件名+扩展名）
		while ((archiveEntry =  inputStream.getNextEntry()) != null) {
			if (archiveEntry.isDirectory()) {
				String name = archiveEntry.getName();
				name = name.substring(0, name.length() - 1);
				File file = new File(outputDirectory + File.separator
						+ name);
				file.mkdirs();
			} else {
				String fileFullName = outputDirectory + File.separator
						+ archiveEntry.getName();
				File file = new File(fileFullName);
				FileOutputStream outputStream = null;
				try {
					outputStream = openOutputStream(file, false);
					unzipFile(inputStream, outputStream, pwd);
					fileFullNameBuffer.append(fileFullName).append(",");
				} finally {
					if(outputStream != null) {
						outputStream.close();
					}
				}
			}
		}
		return fileFullNameBuffer.toString();
	}
	
	/**
	 * 功能描述：解压文件流
	 * @param inputStream zip文档输入流
	 * @param outputStream 文件输出流
	 * @param pwd 解压密码
	 * @throws ArchiveException
	 * @throws IOException
	 */
	private static void unzipFile(ArchiveInputStream inputStream,
			OutputStream outputStream, String pwd) throws ArchiveException, IOException {
		// 普通解压缩文件
		if (pwd == null || pwd.trim().equals("")) {
			
			IOUtils.copy(inputStream, outputStream, IO_BUFFER);
			
		} else {// 解压缩加密文件
			try {
				PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
				SecretKeyFactory keyFactory = SecretKeyFactory
						.getInstance(ALGORITHM);
				SecretKey passwordKey = keyFactory
						.generateSecret(keySpec);
				byte[] salt = new byte[8];
				inputStream.read(salt);
				int iterations = 100;
				PBEParameterSpec parameterSpec = new PBEParameterSpec(
						salt, iterations);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, passwordKey,
						parameterSpec);
				byte[] input = new byte[CRYPT_BUFFER];
				int bytesRead;
				while ((bytesRead = inputStream.read(input)) != -1) {
					byte[] output = cipher.update(input, 0, bytesRead);
					if (output != null) {
						outputStream.write(output);
					}
				}
			
				byte[] output = cipher.doFinal();
				if (output != null) {
					outputStream.write(output);
				}
			} catch (NoSuchAlgorithmException e) {
				throw new ArchiveException(e.getMessage(), e);
			} catch (InvalidKeySpecException e) {
				throw new ArchiveException(e.getMessage(), e);
			} catch (IllegalBlockSizeException e) {
				throw new ArchiveException(e.getMessage(), e);
			} catch (BadPaddingException e) {
				throw new ArchiveException(e.getMessage(), e);
			} catch (InvalidKeyException e) {
				throw new ArchiveException(e.getMessage(), e);
			} catch (InvalidAlgorithmParameterException e) {
				throw new ArchiveException(e.getMessage(), e);
			} catch (NoSuchPaddingException e) {
				throw new ArchiveException(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 打开文件输出流
	 * @param file 要输出流的文件名
	 * @param append 是否在文件末尾追加标志
	 * @return 文件输出流
	 * @throws IOException
	 */
	private static FileOutputStream openOutputStream(File file, boolean append)
			throws IOException {
		if (file.exists()) {
			if (file.isDirectory())
				throw new IOException((new StringBuilder()).append("File '")
						.append(file).append("' exists but is a directory")
						.toString());
			if (!file.canWrite())
				throw new IOException((new StringBuilder()).append("File '")
						.append(file).append("' cannot be written to")
						.toString());
		} else {
			File parent = file.getParentFile();
			if (parent != null && !parent.mkdirs() && !parent.isDirectory())
				throw new IOException((new StringBuilder())
						.append("Directory '").append(parent)
						.append("' could not be created").toString());
		}
		return new FileOutputStream(file, append);
	}
	
	
	/**
	 * desc:解压zip
	 * @param is 文件流
	 * @param fileZipName 原压缩文件名
	 * @param targetDirName 目标文件夹
	 * @param pwd 密码
	 * @throws IOException 
	 * @throws ZipException 
	 * @return 返回压缩文件解压后所有文件的地址名称
	 */
	public static String unzip(InputStream is,String fileZipName,String targetDirName,String pwd) throws IOException, ZipException {
		StringBuffer fileFullNameBuffer = new StringBuffer(); //解压后文件全名称（路径+文件名+扩展名）
		try{
			File targetDir = new File(targetDirName);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
			String targetFileName = targetDir+File.separator+fileZipName;
			File targetFile = new File(targetFileName); //保存zip到本地的文件
//            FileUtils.copyInputStreamToFile(is,targetFile);
            try
            {
              FileOutputStream output = openOutputStream(targetFile,false);
              try {
                IOUtils.copy(is, output);
                output.close();
              } finally {
                IOUtils.closeQuietly(output);
              }
            } finally {
              IOUtils.closeQuietly(is);
            }
            unzip(targetFileName, targetDirName,pwd);
          /*  if(targetFile != null && targetFile.exists()){ //删除原文件
            	targetFile.delete();
            }*/
            String[] fileNameAry = targetDir.list();
            if(fileNameAry != null && fileNameAry.length > 0){
            	for(String fileName:fileNameAry){
            		fileFullNameBuffer.append(targetDir).append(File.separator).append(fileName).append(FILENAME_SPLIT);
            	}
            }
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭压缩包文件流失败，原因：",e);
				}
			}
		}
		return fileFullNameBuffer.toString();
	}
	
	/**
	 * desc:解压文件到指定文件夹
	 * @param zipFile 压缩wenjia
	 * @param targetDir
	 * @param pwd
	 * @author pzz
	 * @throws ZipException 
	 */
	private static void unzip(String zipFile,String targetDir,String pwd) throws ZipException{
		ZipFile zFile = new ZipFile(zipFile);
		zFile.setFileNameCharset("GBK");
		
		if (!zFile.isValidZipFile()) { //
			return;
		}
		if (zFile.isEncrypted()) {
			zFile.setPassword(pwd); // 设置解压密码
		}
		zFile.extractAll(targetDir); // 将压缩文件解压到unzipPath中…
	}
}