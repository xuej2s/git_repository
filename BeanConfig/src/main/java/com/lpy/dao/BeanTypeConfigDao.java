package com.lpy.dao;

import java.util.List;
import java.util.Map;

import com.lpy.entity.BeanTypeConfig;

public interface BeanTypeConfigDao {
    int deleteByPrimaryKey(String beanName);

    
    int insert(BeanTypeConfig record);

    int insertSelective(BeanTypeConfig record);

    int insertByBatch(List<BeanTypeConfig> btList);
    
    BeanTypeConfig getConfigs(String serviceId);
    
    BeanTypeConfig selectByPrimaryKey(String beanName);
    
    List<BeanTypeConfig> selectByPlatform(String platformName);

    List<BeanTypeConfig> getAllConfig(Map<String, Object> map);
    
    int updateByPrimaryKeySelective(BeanTypeConfig record);

    int updateByPrimaryKey(BeanTypeConfig record);
    
    //List<BeanTypeConfig> selectAllConfig(BeanMethodConfig beanMethodConfig);
    
    
}