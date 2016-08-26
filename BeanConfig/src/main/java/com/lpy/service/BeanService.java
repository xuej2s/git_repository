package com.lpy.service;

import java.util.List;
import java.util.Map;

import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;

public interface BeanService {

	List<BeanTypeConfig> selectAllConfig(BeanMethodConfig beanMethodConfig);

	int insertConfig(Map<String, Object> map);

	List<BeanTypeConfig> getAllConfig(Map<String, Object> map);

	BeanMethodConfig autoCreateServiceId();
}
