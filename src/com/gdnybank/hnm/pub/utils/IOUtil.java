package com.gdnybank.hnm.pub.utils;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;

import com.ibm.wsdl.util.IOUtils;

/**
 * desc： 字节工具类
 * user:pzz
 * date:2015年10月20日 下午2:45:22
 */
public class IOUtil {
	static Logger log = Logger.getLogger(IOUtils.class);

    /** java临时文件存放目录 */
    public  static String TEMP_DIR = System.getProperty("java.io.tmpdir");

    public static byte[] readFileByChannel(String fileName){
        long size = 0 ;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
        	log.error("文件不存在", e);
        }
        FileChannel filechannel = fis.getChannel();
        try {
            size = filechannel.size();
        } catch (IOException e) {
            log.error("IOException", e);
        }
        ByteBuffer bb  = ByteBuffer.allocate((int) size);
        try {
            filechannel.read(bb, 0);
        } catch (IOException e) {
            log.error("IOException", e);
        }
        bb.flip();
        return bb.array();
    }
    public static byte[] readFile(String fileName){
        FileInputStream fin = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try {
            fin = new FileInputStream(new File(fileName));
            byte[] data = new byte[1024];
            while (true) {
                int numBytes = fin.read(data);
                if (numBytes < 0) break;
                baos.write(data, 0, numBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    log.error("FileInputStream关闭出错", e);
                }
            }
        }
        return baos.toByteArray();
    }
    /**
     * bytes转换为文件
     *
     * @param bytes
     * @param fileName	 本地绝对路径文件
     * @return
     */
    public static boolean writeFile(byte[] bytes, String fileName) {
        boolean result = false;
        OutputStream output = null;
        InputStream input =null;
        BufferedInputStream inBuff=null;
        BufferedOutputStream outBuff=null;
        try {
            File file = new File(fileName);
            // 父级不存在的创建
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            input =new ByteArrayInputStream(bytes);
            inBuff=new BufferedInputStream(input);
            output = new FileOutputStream(file);
            outBuff=new BufferedOutputStream(output);
            // 缓冲数组
            byte[] buffer = new byte[1024];
            while(inBuff.read(buffer)!=-1){//如果有数据，则从默认的缓存中读出来
                outBuff.write(buffer);//写入文本

            }
            outBuff.flush();//确保数据全部写入文本，则要刷新一下缓存
            result = true;
        } catch (Exception e) {
            log.error("操作文件出错：" + fileName, e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    log.error("OutputStream关闭出错", e);
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("InputStream关闭出错", e);
                }
            }
            if (inBuff != null) {
                try {
                    inBuff.close();
                } catch (IOException e) {
                    log.error("BufferedInputStream关闭出错", e);
                }
            }
            if (outBuff != null) {
                try {
                    outBuff.close();
                } catch (IOException e) {
                    log.error("BufferedOutputStream关闭出错", e);
                }
            }
        }
        return result;
    }

    /**
     * bytes转换为文件
     *
     * @param bytes
     * @return 返回文件名
     */
    public static String writeToTempFile(byte[] bytes,String fileType) {
        if(!TEMP_DIR.endsWith(String.valueOf(File.separatorChar)))
            TEMP_DIR = TEMP_DIR+File.separatorChar ;
        String fileName = TEMP_DIR + "DFS" + System.currentTimeMillis() + "_" + System.nanoTime() + "." +fileType;
        boolean isSuccess = writeFile(bytes, fileName);
        return isSuccess ? fileName : null;
    }


    /**
     * 删除文件
     * @param file
     */
    public static void deleteFile(File file){
    	if(file != null && file.exists()){
    		if(!file.isDirectory()){ //文件
    			file.delete();
    		}else{ //文件夹
    			File[] sonFiles = file.listFiles();
    			for(File sonFile:sonFiles){
    				deleteFile(sonFile);
    			}
    			file.delete();
    		}
    	}
    }
}
