package com.lpy.entity;

public class MethodResult {
    private String methodResultType;

    private String serviceId;

    private String methodResult;

    public MethodResult() {
		// TODO Auto-generated constructor stub
	}
    
    public MethodResult(String methodResultType,String serviceId,String methodResult) {
		// TODO Auto-generated constructor stub
    	this.serviceId = serviceId;
    	this.methodResult = methodResult;
    	this.methodResultType = methodResultType;
    }
    
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getMethodResult() {
        return methodResult;
    }

    public void setMethodResult(String methodResult) {
        this.methodResult = methodResult == null ? null : methodResult.trim();
    }
    public String getMethodResultType() {
        return methodResultType;
    }

    public void setMethodResultType(String methodResultType) {
        this.methodResultType = methodResultType == null ? null : methodResultType.trim();
    }
}