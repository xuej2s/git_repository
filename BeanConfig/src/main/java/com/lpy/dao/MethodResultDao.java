package com.lpy.dao;

import java.util.List;

import com.lpy.entity.MethodResult;

public interface MethodResultDao {
    int deleteByPrimaryKey(String serviceId);

    int insert(MethodResult record);

    int insertSelective(MethodResult record);
    
    int insertResByBatch(List<MethodResult> methodResults);
    

    MethodResult selectByPrimaryKey(MethodResult key);

    int updateByPrimaryKeySelective(MethodResult record);

    int updateByPrimaryKey(MethodResult record);
}