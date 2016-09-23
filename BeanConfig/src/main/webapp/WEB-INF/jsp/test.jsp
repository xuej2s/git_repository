<%@page import="org.apache.commons.codec.net.BCodec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	function winHeight() {
		return screen.availHeight;
	}

	function winWidth() {
		return screen.availWidth;
	}

	$(function() {
		$("#methodName")
				.change(
						function() {
							var methodName = document
									.getElementById("methodName").value;

							$
									.post(
											"tip.do",
											{
												'methodName' : methodName
											},
											function(data) {
												alert(data);
												var li = null;
												for ( var i in data) {
													var insertText = "<li><a href='javascript:;''>"
															+ data[i]
															+ "</a></li>";
													$("#methodUl").append(
															insertText);
												}
											});
						});
	});
</script>

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
<title>showConfig</title>

</head>
<body>
	<br>
	<br>
	<form action="<%=basePath%>showConfig.do" method="post"
		style="margin: 0px; display: inline;">
		
		方法名称: <input type="text" name="methodName" id="methodName">
		<ul id="methodUl" style="display: block;"></ul>
		
		


	</form>





</body>
</html>
