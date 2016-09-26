package com.lpy.entity;

public class MethodParameter {
    private String methodParameterType;
    
    private String serviceId;

    private String methodParameter;
    
    public MethodParameter(){
    	
    }

    public MethodParameter(String methodParameter,String methodParameterType,String serviceId) {
    	// TODO Auto-generated constructor stub
    	this.methodParameterType = methodParameterType;
    	this.serviceId = serviceId;
    	this.methodParameter = methodParameter;
    	
    }
    
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getMethodParameter() {
        return methodParameter;
    }

    public void setMethodParameter(String methodParameter) {
        this.methodParameter = methodParameter == null ? null : methodParameter.trim();
    }

    
    public String getMethodParameterType() {
        return methodParameterType;
    }

    public void setMethodParameterType(String methodParameterType) {
        this.methodParameterType = methodParameterType == null ? null : methodParameterType.trim();
    }
    
    
}