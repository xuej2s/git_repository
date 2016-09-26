package test_ssm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.entity.MethodParameter;
import com.lpy.entity.MethodResult;
import com.lpy.service.BeanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class insertConfigByBatch {

	private static Logger logger = Logger.getLogger(insertConfigByBatch.class);

	@Resource
	private BeanService beanService;

	@Test
	public void test3() throws Exception {

		// /// path为存放各平台文件夹的文件路径
		// String path = "F:\\hengsheng_tea\\test_parseFile";
		
		
		//1.文件路径需要修改
		String path = "F:\\hengsheng_tea\\ebusi";
		//2.服务平台名需要修改
		String platformName = "ebusi";
		//3.创建人信息（可写可不写）
		String creator = "";
		
		
		File file_dir = new File(path);
		List<Map<String, Object>> maps1 = showAllFiles(file_dir,platformName,creator);
		System.out.println(maps1.size());
		for (Map<String, Object> map : maps1) {

			beanService.insertSelectiveByBatch(map);

//			logger.info(JSON.toJSON(nums));

		}
		//showAllFiles(file_dir);

	}

	// 遍历文件夹下所有文件
	static List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
	static List<Map<String, Object>> showAllFiles(File dir,String platformName, String creator) throws Exception {
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			// System.out.println(fs[i].getAbsolutePath());

			if (fs[i].isDirectory()) {
				try {
					showAllFiles(fs[i],platformName, creator);
				} catch (Exception e) {
				}
			} else {
				if (fileNameReg(fs[i].getName())) {
					System.out.println(fs[i].getName());
					Map<String, Object> map = parseXmlByDom4j(fs[i], platformName,creator);
					//System.out.println(map.size()+"----------");
					maps.add(map);
				}
			}
		}
		//System.out.println(maps.size()+"============");
		return maps;
	}

	

	// 匹配符合要求的文件
	public static boolean fileNameReg(String fileName) {

		String regEx = ".*\\.xml";

		Pattern pattern = Pattern.compile(regEx);

		Matcher matcher = pattern.matcher(fileName);

		return matcher.matches();
	}

	// 解析xml文件获取有效信息
	public static Map<String, Object> parseXmlByDom4j(File file, String platformName, String creator) {

		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(file);
			Element node = document.getRootElement();

			List<Element> serviceComponents = node.elements("service-component");
			List<BeanMethodConfig> bmList = new ArrayList<BeanMethodConfig>();
			List<BeanTypeConfig> btList = new ArrayList<BeanTypeConfig>();
			List<MethodParameter> methodParameters = new ArrayList<MethodParameter>();
			List<MethodResult> methodResults = new ArrayList<MethodResult>();

			for (Element serviceComponent : serviceComponents) {
				String beanName = serviceComponent.attributeValue("bean");
				String typeName = serviceComponent.attributeValue("type");
				BeanTypeConfig beanTypeConfig = new BeanTypeConfig(platformName, beanName, typeName);
				btList.add(beanTypeConfig);

				List<Element> serviceMethods = serviceComponent.elements("service-method");
				for (Element serviceMethod : serviceMethods) {
					String serviceId = serviceMethod.attributeValue("service-id");
					String methodName = serviceMethod.attributeValue("method-name");
					BeanMethodConfig beanMethodConfig = new BeanMethodConfig(platformName, serviceId, beanName,
							methodName);
					beanMethodConfig.setCreator(creator);
//					beanMethodConfig.setCreator("System");
					bmList.add(beanMethodConfig);

					List<Element> serviceParameters = serviceMethod.elements("service-parameters");
					// System.out.println(serviceParameters.size()+"mmmmmmmmmm");
					for (Element serviceParameter : serviceParameters) {
						List<Element> elements = serviceParameter.elements("service-parameter");
						for (Element element : elements) {

							String methodParameter = element.attributeValue("name");
							String methodParameterType = element.attributeValue("type");
							MethodParameter methodParameter2 = new MethodParameter(methodParameter, methodParameterType,
									serviceId);
							methodParameters.add(methodParameter2);
							// System.out.println(methodParameters.size());
						}

					}

					List<Element> serviceResults = serviceMethod.elements("service-result");
					for (Element serviceResult : serviceResults) {
						String methodResult = serviceResult.attributeValue("name");
						//System.out.println(methodResult);
						String methodResultType = serviceResult.attributeValue("type");
						MethodResult methodResult2 = new MethodResult(methodResultType, serviceId, methodResult);
						
						methodResults.add(methodResult2);
					}

				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bmList", bmList);
			map.put("btList", btList);
			map.put("methodParameters", methodParameters);
			map.put("methodResults", methodResults);
			//System.out.println(bmList.size()+"----"+btList.size()+"----"+methodParameters.size()+"----"+methodResults.size());

			return map;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}

/*
 * // 解析文件夹
	public static List<Map<String, Object>> parseFiles(String path) {

		File file_dir = new File(path);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		if (file_dir.isDirectory()) {
			File[] platformList = file_dir.listFiles();

			// 获取platformName
			for (File platform : platformList) {

				// System.out.println(platform.getName());
				String platformName = platform.getName();

				if (platform.isDirectory()) {

					File[] xmlFiles = platform.listFiles();

					// 获取平台下xml文件，对其进行遍历并收集所需信息
					for (File xmlFile : xmlFiles) {

						if (fileNameReg(xmlFile.getName())) {

							Map<String, Object> map = parseXmlByDom4j(xmlFile, platformName);
							
							maps.add(map);
						}
					}
				}
			}
		}

		return maps;
	}
 */