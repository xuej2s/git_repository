package com.lpy.dao;

import java.util.List;

import com.lpy.entity.BeanMethodConfig;


public interface BeanMethodConfigDao {
    int deleteByPrimaryKey(String serviceId);

    int insert(BeanMethodConfig record);

    int insertSelective(BeanMethodConfig record);

    BeanMethodConfig selectByPrimaryKey(String serviceId);
    

    int updateByPrimaryKeySelective(BeanMethodConfig record);

    int updateByPrimaryKey(BeanMethodConfig record);
    
    int insertByBatch(List<BeanMethodConfig> bmList);
    
    
    
    int insertSelectiveByBatch(List<BeanMethodConfig> bmList);
    
    
    
    
    List<String> selectMethodName(String methodName);
    List<String> selectBeanName(String beanName);
    List<String> selectServiceId(String serviceId);
}