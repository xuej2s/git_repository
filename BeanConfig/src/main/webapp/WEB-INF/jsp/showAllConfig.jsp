<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细信息</title>
</head>
<body>

	<tr>
		<td>服务平台:</td>
		<td>${beanTypeConfig.platformName }</td>
	</tr>
	<br>
	<br>

	<tr>
		<td>Bean名称:</td>
		<td>${beanTypeConfig.beanName }</td>
	</tr>
	<br>
	<br>

	<tr>
		<td>实现类:</td>
		<td>${beanTypeConfig.typeName }</td>
	</tr>
	<br>
	<br>

	<c:forEach items="${beanTypeConfig.beanMethodConfigs }" var="bms">

		<tr>
			<td>服务ID:</td>
			<td><c:out value="${bms.serviceId }"></c:out></td>
		</tr>
		<br>
		<br>

		<tr>
			<td>方法名称:</td>
			<td><c:out value="${bms.methodName }"></c:out></td>
		</tr>
		<br>
		<br>

		

		<tr>
			<td>入参（类型）:</td>
			<c:forEach items="${bms.methodParameters }" var="mps">
				<tr>
					<td><c:out
							value="${mps.methodParameter }(${mps.methodParameterType })"></c:out></td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</tr>
		<br>
		<br>

		<tr>
			<td>出参（类型）:</td>
			<c:forEach items="${bms.methodResults }" var="mps">
				<tr>
					<td><c:out
							value="${mps.methodResult }(${mps.methodResultType })"></c:out></td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</tr>
		
		<br>
		<br>
		<tr>
			<td>创建人:</td>
			<td>${bms.creator }</td>
		</tr>
		<br>
		<br>
		
		<tr>
			<td>创建时间:</td>
			<td>${bms.creatTime }</td>
		</tr>
		<br>
		<br>


	</c:forEach>



</body>
</html>