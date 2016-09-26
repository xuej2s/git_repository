<%@page import="org.apache.commons.codec.net.BCodec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
<link href="<c:url value="/resources/core/main.css" />" rel="stylesheet">


<style type="text/css">
td {
	border: 1px solid #ccc;
	height: 20px;
}

</style>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>下载文件</title>

</head>
<body>
	<c:forEach items="${files }" var="file">
		
		<a href="<%=basePath%>operating/downloading.do?fileName=${file }" >${file }</a><br>
	
	</c:forEach>
	<br>
	<button onclick="window.location='<%=basePath%>operating/skip.do'" >返回</button>
</body>
</html>
