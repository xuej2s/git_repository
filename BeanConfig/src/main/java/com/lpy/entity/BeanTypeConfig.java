package com.lpy.entity;

import java.util.List;

public class BeanTypeConfig {
	
	private String platformName;
	
    private String beanName;

    private String typeName;
    
    private List<BeanMethodConfig> beanMethodConfigs;
    
    public BeanTypeConfig() {
		// TODO Auto-generated constructor stub
	}
    
    public BeanTypeConfig(String platformName,String beanName,String typeName) {
		// TODO Auto-generated constructor stub
    	this.platformName = platformName;
    	this.beanName = beanName;
    	this.typeName = typeName;
	}
    
    public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

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