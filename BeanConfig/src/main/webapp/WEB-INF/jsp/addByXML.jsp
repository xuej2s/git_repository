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
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">

//第一次function 用的名字是 submit(),应该是保留字，所以提交前验证不成功！！！！
	function toSubmit() {
		var platformName = document.getElementById("plat").value;
		var file = document.getElementById("file").value;

		//alert(file);
		if (platformName == "" || file == "") {
			alert("文件和服务平台都不能为空！");
			return false;
		} else {
			return true;
		}
	}
</script>
<title>通过XML添加信息</title>
</head>
<body>
	<form action="<%=basePath%>addConfigByBatch.do" method="post"
		onsubmit="return toSubmit()" enctype="multipart/form-data">
		服务平台：<input align="middle" id="plat" type="text" name="platformName">
		<br>
		<br> 
		创建者：<input align="middle" type="text" name="creator">
		<br><br>
		file:<input type="file" id="file" name="file"><br><br> 
		
		<input type="submit" align="middle" value="批量增加">
	</form>
</body>
</html>

<!-- 
创建人：<input align="middle" type="text" name="creator">
		<br><br>
 -->