package testWatch;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lpy.entity.AcAsynExecutorCmd;
import com.lpy.service.WatchService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestWatch {

	private static Logger logger = Logger.getLogger(TestWatch.class);
	
	@Resource
	private WatchService watchService;
	
	@Test
	public void test() {

		///path为存放各平台文件夹的文件路径
		List<AcAsynExecutorCmd> list = watchService.selectAllConfig();
		for (AcAsynExecutorCmd string : list) {
//			System.out.println(string);
			logger.info(JSON.toJSONString(string));
		}
		
	}
}
