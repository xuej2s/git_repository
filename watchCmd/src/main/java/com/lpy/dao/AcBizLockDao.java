package com.lpy.dao;

import java.util.List;

import com.lpy.entity.AcBizLock;
import com.lpy.entity.AcBizLockKey;

public interface AcBizLockDao {
    int deleteByPrimaryKey(AcBizLockKey key);

    int insert(AcBizLock record);

    int insertSelective(AcBizLock record);

    AcBizLock selectByPrimaryKey(AcBizLockKey key);

    int updateByPrimaryKeySelective(AcBizLock record);

    int updateByPrimaryKey(AcBizLock record);
    
    List<AcBizLock> selectAllConfig();
}