package com.lpy.entity;

import java.util.List;

public class BeanTypeConfig {
    private String beanName;

    private String typeName;
    
    private List<BeanMethodConfig> beanMethodConfigs;
    
    
    
//    private BeanMethodConfig beanMethodConfig;
//    
//    private String serviceId;
//    
//    private String methodName;
//    
//    public String getMethodName() {
//		return beanMethodConfig.getMethodName();
//	}
//    
//    public String getServiceId() {
//		return beanMethodConfig.getServiceId();
//	}
    
//    public BeanMethodConfig getBeanMethodConfig() {
//		return beanMethodConfig;
//	}
//
//	public void setBeanMethodConfig(BeanMethodConfig beanMethodConfig) {
//		this.beanMethodConfig = beanMethodConfig;
//	}

	public List<BeanMethodConfig> getBeanMethodConfigs() {
		return beanMethodConfigs;
	}

	public void setBeanMethodConfigs(List<BeanMethodConfig> beanMethodConfigs) {
		this.beanMethodConfigs = beanMethodConfigs;
	}

	public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}