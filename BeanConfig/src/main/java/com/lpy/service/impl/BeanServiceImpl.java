package com.lpy.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.lpy.dao.BeanMethodConfigDao;
import com.lpy.dao.BeanTypeConfigDao;
import com.lpy.dao.MethodParameterDao;
import com.lpy.dao.MethodResultDao;
import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.entity.MethodParameter;
import com.lpy.entity.MethodResult;
import com.lpy.service.BeanService;

@Service("beanService")
public class BeanServiceImpl implements BeanService {

	@Resource
	private BeanTypeConfigDao beanTypeConfigDao;

	@Resource
	private BeanMethodConfigDao beanMethodConfigDao;

	@Resource
	private MethodParameterDao methodParameterDao;

	@Resource
	private MethodResultDao methodResultDao;

	/*
	 * 展示查询信息
	 */
	public List<BeanTypeConfig> getAllConfig(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return beanTypeConfigDao.getAllConfig(map);
	}

	/*
	 * 新增单条信息实现
	 */
	public int insertAllConfig(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if (!beanTypeConfigDao.getAllConfig(map).isEmpty()) {
			return 0;
		} else {

			String beanName = ((BeanTypeConfig) map.get("beanTypeConfig")).getBeanName();
			String platform = ((BeanTypeConfig) map.get("beanTypeConfig")).getPlatformName();
			String typeName = ((BeanTypeConfig) map.get("beanTypeConfig")).getTypeName();
			String serviceId = ((BeanMethodConfig) map.get("beanMethodConfig")).getServiceId();
			String methodName = ((BeanMethodConfig) map.get("beanMethodConfig")).getMethodName();
			List<MethodParameter> methodParameters = (List<MethodParameter>)map.get("methodParameters");
			List<MethodResult> methodResults = (List<MethodResult>)map.get("methodResults");
			
			
			int bmc, btc = 0;
			/*
			 * 三个增加信息判断 1.platformName 2.platformName + beanName + typeName
			 * 3.platformName + beanName + typeName + serviceId + methodName
			 */
			if (platform != "" && beanName == "" && typeName != null && serviceId == "" && methodName == ""
					&& beanTypeConfigDao.selectByPlatform(platform).size() == 0) {
				// System.out.println("method if 1");
				btc = beanTypeConfigDao.insertSelective((BeanTypeConfig) map.get("beanTypeConfig"));
				bmc = beanMethodConfigDao.insertSelective((BeanMethodConfig) map.get("beanMethodConfig"));
				

				return btc + bmc;
			}

			if (platform != "" && beanName != "" && typeName != null && serviceId == "" && methodName == "") {
				
				List<BeanTypeConfig> list = beanTypeConfigDao.selectByPlatform(platform);
				boolean allow = true;
				for (BeanTypeConfig beanTypeConfig : list) {
					if (beanTypeConfig.getBeanName() != null) {

						if (beanTypeConfig.getBeanName().equals(beanName)) {
							allow = false;
							break;
						}
					}
				}
		
				if (beanTypeConfigDao.selectByPlatform(platform) == null) {

					btc = beanTypeConfigDao.insertSelective((BeanTypeConfig) map.get("beanTypeConfig"));
					bmc = beanMethodConfigDao.insertSelective((BeanMethodConfig) map.get("beanMethodConfig"));
					return btc + bmc;
				} else if (allow) {

					btc = beanTypeConfigDao.insertSelective((BeanTypeConfig) map.get("beanTypeConfig"));
					bmc = beanMethodConfigDao.insertSelective((BeanMethodConfig) map.get("beanMethodConfig"));
					return btc + bmc;
				}

			}

			if (platform != "" && beanName != "" && typeName != null && serviceId != "" && methodName != ""
					&& beanMethodConfigDao.selectByPrimaryKey(serviceId) == null) {

				btc = beanTypeConfigDao.insertSelective((BeanTypeConfig) map.get("beanTypeConfig"));
				bmc = beanMethodConfigDao.insertSelective((BeanMethodConfig) map.get("beanMethodConfig"));
				
				if (methodResults != null) {
					
					for (MethodResult methodResult : methodResults) {
						
						String methodRes = methodResult.getMethodResult();
						String methodResultType = methodResult.getMethodResultType();
						
						if (methodRes != null && methodRes != "" && methodResultType != null
								&& methodResultType != "") {
							methodResult.setServiceId(serviceId);
							methodResultDao.insertSelective(methodResult);
						}
					}
				}
				
				if (methodParameters != null) {
					
					for (MethodParameter methodParameter : methodParameters) {
						
						String methodParam = methodParameter.getMethodParameter();
						String methodParameterType = methodParameter.getMethodParameterType();

						if (methodParam != null && methodParam != "" && methodParameterType != null
								&& methodParameterType != "") {
							//System.out.println("******************");
							methodParameter.setServiceId(serviceId);
							methodParameterDao.insertSelective(methodParameter);
						}
					}
				}

				
				return btc + bmc;
			}
			return 0;
		}
	}

	/*
	 * 自动生成serviceId
	 */
	public BeanMethodConfig autoCreateServiceId() {

		BeanMethodConfig beanMethodConfig = new BeanMethodConfig();
		String serviceId = pk();

		while (beanMethodConfigDao.selectByPrimaryKey(serviceId) != null) {
			serviceId = pk();
		}

		beanMethodConfig.setServiceId(serviceId);

		return beanMethodConfig;

	}

	
	
	/*
	 * 单机版增加创建者的批量增加
	 */

	public int insertSelectiveByBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<BeanTypeConfig> btList = (List<BeanTypeConfig>) map.get("btList");
		List<BeanMethodConfig> bmList = (List<BeanMethodConfig>) map.get("bmList");
		List<MethodParameter> methodParameters = (List<MethodParameter>)map.get("methodParameters");
		List<MethodResult> methodResults = (List<MethodResult>)map.get("methodResults");

		for (int i = 0; i < bmList.size(); i++) {
			if (beanMethodConfigDao.selectByPrimaryKey(bmList.get(i).getServiceId()) != null) {
				bmList.remove(i);
				i--;
			}
		}

		int btnum = 0, bmnum = 0,mpnum = 0, mrnum = 0;
		if (bmList.size() != 0) {

			btnum = beanTypeConfigDao.insertByBatch(btList);
			bmnum = beanMethodConfigDao.insertSelectiveByBatch(bmList);
			mpnum = methodParameterDao.insertParamByBatch(methodParameters)*100;
			mrnum = methodResultDao.insertResByBatch(methodResults)*1000;
		}

		return btnum + bmnum + mpnum + mrnum;
	}

	/*
	 * 服务端批量添加
	 */
	public int insertByBatch(File file, String platformName,String creator) {

		Map<String, Object> map = parseXmlByDom4j(file, platformName, creator);
		List<BeanTypeConfig> btList = (List<BeanTypeConfig>) map.get("btList");
		List<BeanMethodConfig> bmList = (List<BeanMethodConfig>) map.get("bmList");
		List<MethodParameter> methodParameters = (List<MethodParameter>)map.get("methodParameters");
		List<MethodResult> methodResults = (List<MethodResult>)map.get("methodResults");

		for (int i = 0; i < bmList.size(); i++) {
			if (beanMethodConfigDao.selectByPrimaryKey(bmList.get(i).getServiceId()) != null) {
				bmList.remove(i);
				i--;
			}
		}

		int btnum = 0, bmnum = 0,mpnum = 0, mrnum = 0;
		if (bmList.size() != 0) {

			btnum = beanTypeConfigDao.insertByBatch(btList);
			bmnum = beanMethodConfigDao.insertSelectiveByBatch(bmList);
			mpnum = methodParameterDao.insertParamByBatch(methodParameters)*100;
			mrnum = methodResultDao.insertResByBatch(methodResults)*1000;
		}

		return btnum + bmnum + mpnum + mrnum;
	}
	
	
	
	/*
	 * 测试批量增加
	 */

	public int insertByBatch(List<BeanMethodConfig> list) {

		return beanMethodConfigDao.insertByBatch(list);
	}
	
	
	/*
	 * 错误检验
	 */
	public int problems(BeanMethodConfig beanMethodConfig) {

		
		List<BeanTypeConfig> list = beanTypeConfigDao.selectByPlatform(beanMethodConfig.getPlatformName());
		if (list.size() != 0 && beanMethodConfig.getBeanName() == "" && beanMethodConfig.getMethodName() == ""
				&& beanMethodConfig.getServiceId() == "") {
			return 1;
		}

		if (list.size() != 0 && beanMethodConfig.getBeanName() != "" && beanMethodConfig.getMethodName() == ""
				&& beanMethodConfig.getServiceId() == "") {
			for (BeanTypeConfig beanTypeConfig1 : list) {
				if (beanTypeConfig1.getBeanName().equals(beanMethodConfig.getBeanName())) {
					return 2;
				}
			}

		}

		if (beanMethodConfig.getPlatformName() != "" && beanMethodConfig.getBeanName() != ""
				&& beanMethodConfig.getMethodName() != "" && beanMethodConfig.getServiceId() != ""
				&& beanMethodConfigDao.selectByPrimaryKey(beanMethodConfig.getServiceId()) != null) {
			return 3;
		}

		return 0;
	}
	
	/*
	 * 含入参出参信息详情显示
	 */
	public BeanTypeConfig showAllConfig(String serviceId) {
		
		return methodParameterDao.showAllConfig(serviceId);
	}
	
	
	/*
	 * 不含入参出参信息详情显示
	 */
	public BeanTypeConfig getConfigs(String serviceId) {

		return beanTypeConfigDao.getConfigs(serviceId);
	}
	
	
	
	/*
	 * id自动生成方法实现
	 */
	public static String pk() {
		char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] charsNum = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		int r1 = random.nextInt(26);
		int r2 = random.nextInt(26);
		int r3 = random.nextInt(26);
		int r4 = random.nextInt(10);
		int r5 = random.nextInt(10);
		int r6 = random.nextInt(10);
		int r7 = random.nextInt(10);
		int r8 = random.nextInt(10);
		int r9 = random.nextInt(10);
		int r10 = random.nextInt(10);
//		int r11 = random.nextInt(10);
		String string = chars[r1] + "" + chars[r2] + "" + chars[r3] + "" + charsNum[r4] + "" + charsNum[r5] + ""
				+ charsNum[r6] + "" + charsNum[r7] + "" + charsNum[r8] + charsNum[r9] + "" + charsNum[r10];
		return string;
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
						
						bmList.add(beanMethodConfig);

						List<Element> serviceParameters = serviceMethod.elements("service-parameters");
						for (Element serviceParameter : serviceParameters) {
							List<Element> elements = serviceParameter.elements("service-parameter");
							for (Element element : elements) {
								
								String methodParameter = element.attributeValue("name");
								String methodParameterType = element.attributeValue("type");
								MethodParameter methodParameter2 = new MethodParameter(methodParameter, methodParameterType,
										serviceId);
								methodParameters.add(methodParameter2);
							}

						}

						List<Element> serviceResults = serviceMethod.elements("service-result");
						for (Element serviceResult : serviceResults) {
							String methodResult = serviceResult.attributeValue("name");
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

				return map;
			} catch (DocumentException e) {

				e.printStackTrace();
				return null;
			}
		}

		public List<String> selectMethodName(String methodName) {
			// TODO Auto-generated method stub
			return beanMethodConfigDao.selectMethodName(methodName);
		}

		public int deleteByPrimaryKey(String serviceId) {
			// TODO Auto-generated method stub
			int bmd = beanMethodConfigDao.deleteByPrimaryKey(serviceId);
			int mpd = methodParameterDao.deleteByPrimaryKey(serviceId);
			int mrd = methodResultDao.deleteByPrimaryKey(serviceId);
			return bmd*100 + mpd * 10 + mrd;
		}

		public List<String> selectBeanName(String beanName) {
			// TODO Auto-generated method stub
			return beanMethodConfigDao.selectBeanName(beanName);
		}

		public List<String> selectServiceId(String serviceId) {
			// TODO Auto-generated method stub
			return beanMethodConfigDao.selectServiceId(serviceId);
		}
		
		
//		/*
//		 * 单机版批量增加信息
//		 */
//		public int insertByBatch(Map<String, Object> map) {
//
//			List<BeanTypeConfig> btList = (List<BeanTypeConfig>) map.get("btList");
//			List<BeanMethodConfig> bmList = (List<BeanMethodConfig>) map.get("bmList");
//			List<MethodParameter> methodParameters = (List<MethodParameter>)map.get("methodParameters");
//			List<MethodResult> methodResults = (List<MethodResult>)map.get("methodResults");
//
//			for (int i = 0; i < bmList.size(); i++) {
//				if (beanMethodConfigDao.selectByPrimaryKey(bmList.get(i).getServiceId()) != null) {
//					bmList.remove(i);
//					i--;
//				}
//			}
//
//			int btnum = 0, bmnum = 0,mpnum = 0, mrnum = 0;
//			if (bmList.size() != 0) {
//
//				btnum = beanTypeConfigDao.insertByBatch(btList);
//				bmnum = beanMethodConfigDao.insertSelectiveByBatch(bmList);
//				mpnum = methodParameterDao.insertParamByBatch(methodParameters)*100;
//				mrnum = methodResultDao.insertResByBatch(methodResults)*1000;
//			}
//
//			return btnum + bmnum + mpnum + mrnum;
//
//		}
		
		

		
}
