package com.lpy.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lpy.dao.BeanMethodConfigDao;
import com.lpy.dao.BeanTypeConfigDao;
import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.service.BeanService;

@Service("beanService")
public class BeanServiceImpl implements BeanService {

	@Resource
	private BeanTypeConfigDao beanTypeConfigDao;
	
	@Resource
	private BeanMethodConfigDao beanMethodConfigDao;
	
	public List<BeanTypeConfig> selectAllConfig(BeanMethodConfig beanMethodConfig) {
		// TODO Auto-generated method stub
		return beanTypeConfigDao.selectAllConfig(beanMethodConfig);
	}

	public List<BeanTypeConfig> getAllConfig(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return beanTypeConfigDao.getAllConfig(map);
	}

	public int insertConfig(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if (!beanTypeConfigDao.getAllConfig(map).isEmpty()) {
			return 0;
		}else {
			int bmc,btc = 0;
			if (beanTypeConfigDao.selectByPrimaryKey(((BeanTypeConfig)map.get("beanTypeConfig")).getBeanName()) != null) {
				 
				bmc = beanMethodConfigDao.insertSelective((BeanMethodConfig)map.get("beanMethodConfig"));
			
			}else {
				
				 btc = beanTypeConfigDao.insertSelective((BeanTypeConfig)map.get("beanTypeConfig"))+10;
				 bmc = beanMethodConfigDao.insertSelective((BeanMethodConfig)map.get("beanMethodConfig"));
				
			}
			return btc+bmc;
		}
	}

	public BeanMethodConfig autoCreateServiceId() {
		// TODO Auto-generated method stub
		BeanMethodConfig beanMethodConfig = new BeanMethodConfig();
		String serviceId = pk();
		
		while (beanMethodConfigDao.selectByPrimaryKey(serviceId) != null) {
			serviceId = pk();
		}
		
		beanMethodConfig.setServiceId(serviceId);
		
		return beanMethodConfig;

	}

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
		String string = chars[r1] + ""+chars[r2] + ""+chars[r3] + ""+charsNum[r4]+ ""+charsNum[r5]+ ""+charsNum[r6]+ ""+charsNum[r7]+ ""+charsNum[r8] ;
		return string;
	}
//	'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
}
