package test_ssm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lpy.entity.Student;
import com.lpy.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class Test_sm {
	private static Logger logger = Logger.getLogger(Test_sm.class);
	
	@Resource
	private StudentService studentService;
	
	@Test
	public void test1(){
		
//		Student student = studentService.findById("4");
//		System.out.println(student+"--------------------");
//		List<Student> list = studentService.showAll();
//		List<Student> uList = studentService.queryStudentList();
//		logger.info(JSON.toJSONString(student));
//		logger.info(JSON.toJSONString(list));
//		logger.info(JSON.toJSONString(uList));
		
		Student student = new Student();
		student.setId("1");
		student.setName("张飞");
		Student student2 = new Student("2", "关羽", 19);
		Student student3 = new Student("3", "刘备", 20);
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(student);
		stuList.add(student2);
		stuList.add(student3);
		int num = studentService.insertByBatch(stuList);
		logger.info(JSON.toJSON(num));
		
		
	}
}
