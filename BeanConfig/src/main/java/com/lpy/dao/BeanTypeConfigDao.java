package com.lpy.dao;

import java.util.List;
import java.util.Map;

import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;

public interface BeanTypeConfigDao {
    int deleteByPrimaryKey(String beanName);

    int insert(BeanTypeConfig record);

    int insertSelective(BeanTypeConfig record);

    BeanTypeConfig selectByPrimaryKey(String beanName);

    int updateByPrimaryKeySelective(BeanTypeConfig record);

    int updateByPrimaryKey(BeanTypeConfig record);
    
    List<BeanTypeConfig> selectAllConfig(BeanMethodConfig beanMethodConfig);
    
    List<BeanTypeConfig> getAllConfig(Map<String, Object> map);
}