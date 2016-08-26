package com.lpy.dao;

import com.lpy.entity.BeanMethodConfig;

public interface BeanMethodConfigDao {
    int deleteByPrimaryKey(String serviceId);

    int insert(BeanMethodConfig record);

    int insertSelective(BeanMethodConfig record);

    BeanMethodConfig selectByPrimaryKey(String serviceId);

    int updateByPrimaryKeySelective(BeanMethodConfig record);

    int updateByPrimaryKey(BeanMethodConfig record);
}