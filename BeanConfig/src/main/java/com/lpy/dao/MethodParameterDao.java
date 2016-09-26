package com.lpy.dao;


import java.util.List;

import com.lpy.entity.BeanTypeConfig;
import com.lpy.entity.MethodParameter;

public interface MethodParameterDao {
    int deleteByPrimaryKey(String serviceId);

    int insert(MethodParameter record);

    int insertSelective(MethodParameter record);
    
    int insertParamByBatch(List<MethodParameter> methodParameters);
    
    

    MethodParameter selectByPrimaryKey(MethodParameter key);

    int updateByPrimaryKeySelective(MethodParameter record);

    int updateByPrimaryKey(MethodParameter record);
    
    BeanTypeConfig showAllConfig(String serviceId);

}