package com.lpy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lpy.dao.StudentDao;
import com.lpy.entity.Student;
import com.lpy.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;
	
	public Student findById(String id) {
		// TODO Auto-generated method stub
//		Student student = studentDao.selectByPrimaryKey(id);
//		if (student != null) {
//			return student;
//		} else {
//
//			return null;
//		}
		return studentDao.selectByPrimaryKey(id);
	}
	
	public List<Student> showAll() {
		// TODO Auto-generated method stub
		return studentDao.selectAll();
	}

	public List<Student> queryStudentList() {
		// TODO Auto-generated method stub
		return studentDao.queryStudentList();
	}

	public int insertByBatch(List<Student> stuList) {
		// TODO Auto-generated method stub
		return studentDao.insertByBatch(stuList);
	}

}
