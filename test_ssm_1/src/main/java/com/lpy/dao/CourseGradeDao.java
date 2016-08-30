package com.lpy.dao;

import com.lpy.entity.CourseGrade;

public interface CourseGradeDao {
    int deleteByPrimaryKey(String pkid);

    int insert(CourseGrade record);

    int insertSelective(CourseGrade record);

    CourseGrade selectByPrimaryKey(String pkid);

    int updateByPrimaryKeySelective(CourseGrade record);

    int updateByPrimaryKey(CourseGrade record);
}