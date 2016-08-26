package test_ssm;

import java.beans.beancontext.BeanContextServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.service.BeanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestBean {

	private static Logger logger = Logger.getLogger(TestBean.class);
	
	@Resource
	private BeanService beanService;
	
	@Test
	public void test3(){
		
		BeanMethodConfig beanMethodConfig = new BeanMethodConfig();
		BeanTypeConfig beanTypeConfig = new BeanTypeConfig();

		beanTypeConfig.setBeanName("test");
//		beanTypeConfig.setTypeName("16");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beanTypeConfig", beanTypeConfig);
		map.put("beanMethodConfig", beanMethodConfig);

		List<BeanTypeConfig> list1 = beanService.getAllConfig(map);
//		int testInsert = beanService.insertConfig(map);
//		logger.info(JSON.toJSONString(list));
		logger.info(JSON.toJSONString(list1));
//		logger.info(JSON.toJSONString(testInsert));
		
	}
	
	
}
