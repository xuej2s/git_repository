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
<script type="text/javascript">
	function winHeight() {
		return screen.availHeight;
	}

	function winWidth() {
		return screen.availWidth;
	}

	$(document).ready(function() {

		$('#methodName').autocomplete({
			serviceUrl: 'tip.do',
			paramName: "methodName",
			delimiter: ",",
		    transformResult: function(data) {
		    	alert(data)
		        return {
		        	
		            suggestions: $.map($.parseJSON(data), function(item) {
		            	alert(item)
		                return { value: item };
		            })
		            
		        };
		        
		    }
		    
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
	<form action="<%=basePath%>showConfig.do" method="post"
		style="margin: 0px; display: inline;">


		方法名称：<input id="methodName"/>

		<ul id="methodUl" style="display: block;">
			<li></li>
		</ul>

		<button id="testbut" type="button" onclick="testbut()">测试</button>
	</form>
</body>
</html>
