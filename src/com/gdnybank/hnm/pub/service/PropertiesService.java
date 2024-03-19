package com.gdnybank.hnm.pub.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.nantian.mfp.framework.context.MfpContextHolder;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.nantian.mfp.framework.cache.Cache;

/**
 * desc: 属性文件刷新
 * @author:wxm
 * date:2021.04.21
 */
public class PropertiesService implements Cache{
	Logger logger = Logger.getLogger(PropertiesService.class);
	private List<String> propertyPaths;

	@Override
	public void refresh() {
		// 在刷新的时候先将原本的数据全部清掉，解决去掉某些参数时刷新不生效的问题
		MfpContextHolder.getProperties().clear();
		for(String propertyPath : propertyPaths){
			ClassPathResource classPathResource = new ClassPathResource(propertyPath);
			InputStream is = null;
			Properties properties = null;
			try {
				is = classPathResource.getInputStream();
				properties = new Properties();
				properties.load(is);
			} catch (IOException e) {
				logger.error("加载属性文件失败，原因：",e);
			}finally{
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						logger.error("加载属性文件，关闭流失败，原因：",e);
					}
				}
			}
			if(properties != null){
				MfpContextHolder.getProperties().putAll(properties);
			}
		}
	}

	public List<String> getPropertyPaths() {
		return propertyPaths;
	}

	public void setPropertyPaths(List<String> propertyPaths) {
		this.propertyPaths = propertyPaths;
	}

}
