package com.lpy.entity;

import java.util.List;

public class MethodParameterModel {

	private List<MethodParameter> methodParameters;

	public List<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

	public void setMethodParameters(List<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}
	
	public MethodParameterModel() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MethodParameterModel(List<MethodParameter> methodParameters) {
		// TODO Auto-generated constructor stub
		super();
		this.methodParameters = methodParameters;
	}
	
}
