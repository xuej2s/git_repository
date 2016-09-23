package com.lpy.entity;

import java.util.List;
import java.util.Date;

public class BeanMethodConfig {

	private Date creatTime;

	private String creator;

	private String platformName;

	private String serviceId;

	private String beanName;

	private String methodName;

	private List<MethodParameter> methodParameters;

	private List<MethodResult> methodResults;

	public BeanMethodConfig() {
		// TODO Auto-generated constructor stub
	}

	public BeanMethodConfig(String platformName, String serviceId, String beanName, String methodName) {
		// TODO Auto-generated constructor stub
		this.platformName = platformName;
		this.serviceId = serviceId;
		this.beanName = beanName;
		this.methodName = methodName;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
	}

	public List<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

	public void setMethodParameters(List<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}

	public List<MethodResult> getMethodResults() {
		return methodResults;
	}

	public void setMethodResults(List<MethodResult> methodResults) {
		this.methodResults = methodResults;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId == null ? null : serviceId.trim();
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName == null ? null : beanName.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}
}