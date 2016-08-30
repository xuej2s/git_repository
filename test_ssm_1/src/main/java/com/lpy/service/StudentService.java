package com.lpy.service;

import java.util.List;

import com.lpy.entity.Student;

public interface StudentService {
	Student findById(String id);
	
	List<Student> showAll();
	
	List<Student> queryStudentList();
	
	int insertByBatch(List<Student> stuList);
}
