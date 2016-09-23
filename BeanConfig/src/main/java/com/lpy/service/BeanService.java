package com.lpy.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;

public interface BeanService {

	//List<BeanTypeConfig> selectAllConfig(BeanMethodConfig beanMethodConfig);

	int insertAllConfig(Map<String, Object> map);

	List<BeanTypeConfig> getAllConfig(Map<String, Object> map);

	BeanMethodConfig autoCreateServiceId();

	int insertByBatch(Map<String, Object> map);
	
	int insertByBatch(File file, String platformName);
	
	BeanTypeConfig showAllConfig(String serviceId); 

	// 测试用
	int insertByBatch(List<BeanMethodConfig> list);
	
	int problems(BeanMethodConfig beanMethodConfig);
	
	BeanTypeConfig getConfigs(String serviceId);
	
//	int deleteByPrimaryKey(String serviceId);
	
	
	
	List<String> selectMethodName(String methodName);
}
