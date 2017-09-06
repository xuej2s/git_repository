package com.lpy.dao;

import java.util.List;

import com.lpy.entity.AcAsynExecutorCmd;
import com.lpy.entity.AcAsynExecutorCmdKey;

public interface AcAsynExecutorCmdDao {
    int deleteByPrimaryKey(AcAsynExecutorCmdKey key);

    int insert(AcAsynExecutorCmd record);

    int insertSelective(AcAsynExecutorCmd record);

    AcAsynExecutorCmd selectByPrimaryKey(AcAsynExecutorCmdKey key);

    int updateByPrimaryKeySelective(AcAsynExecutorCmd record);

    int updateByPrimaryKey(AcAsynExecutorCmd record);
    
    List<AcAsynExecutorCmd> selectAllConfig();
}