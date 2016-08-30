package com.lpy.entity;

import java.util.List;

public class Student {
    
	private String id;

    private String name;

    private Integer age;
    
    private List<CourseGrade> courseGrades;
    
    public Student() {
		// TODO Auto-generated constructor stub
	}
    
    public Student(String id,String name,int age) {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.name = name;
    	this.age = age;
	}
    

    public List<CourseGrade> getCourseGrades() {
		return courseGrades;
	}

	public void setCourseGrades(List<CourseGrade> courseGrades) {
		this.courseGrades = courseGrades;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}