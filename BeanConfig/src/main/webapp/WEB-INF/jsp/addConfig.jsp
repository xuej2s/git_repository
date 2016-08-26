<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>addBeanConfig</title>
</head>
<body>
	<h1 align="center">Add BeanConfig</h1>
	<form action="<%=basePath%>addConfig.do" method="post">
		Bean Name: <input align="middle" type="text" name="beanName"><br>
		<br> Type Name: <input align="middle" type="text" name="typeName"><br>
		<br> Service Id: <input align="middle" type="text"
			name="serviceId" value="${beanMethodConfig.serviceId }"
			><br>
		<br> MethodName: <input align="middle" type="text"
			name="methodName"><br>
		<br> <input align="middle" type="submit" value="增加">
	</form>
	<button onclick="window.location='<%=basePath%>select.do'">返回</button>
</body>
</html>