package test_ssm;




import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lpy.dao.BeanMethodConfigDao;
import com.lpy.dao.MethodParameterDao;
import com.lpy.dao.MethodResultDao;
import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.entity.MethodParameter;
import com.lpy.entity.MethodResult;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class testNewFunction {

	private static Logger logger = Logger.getLogger(testNewFunction.class);

	@Resource
	private MethodParameterDao methodParameterDao;
	
	@Resource
	private MethodResultDao methodResultDao;
	
	@Resource
	private BeanMethodConfigDao beanMethodConfigDao;

	@Test
	public void test3() {

		///path为存放各平台文件夹的文件路径
		List<String> list = beanMethodConfigDao.selectMethodName("up");
		for (String string : list) {
			System.out.println(string);
		}
	}

}	