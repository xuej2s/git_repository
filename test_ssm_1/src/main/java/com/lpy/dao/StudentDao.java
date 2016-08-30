package com.lpy.dao;

import java.util.List;

import com.lpy.entity.Student;

public interface StudentDao {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> selectAll();
    
    List<Student> queryStudentList();
    
    int insertByBatch(List<Student> stuList);
}