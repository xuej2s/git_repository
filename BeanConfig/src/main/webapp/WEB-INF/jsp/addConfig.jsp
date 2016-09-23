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
<!-- 
<script src="/WEB-INF/js/jquery-1.8.3.min.js"></script>
 -->
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	function testPlatform() {
		var platform = document.getElementById("platformName").value;
		var serviceId = document.getElementById("serviceId").value;
		if (platform == "") {
			document.getElementById("meg1").innerHTML = "不能为空";

		} 
	}

	function testBeanname() {
		
		var beanname = document.getElementById("beanName").value;
		if (beanname == "") {
			document.getElementById("meg2").innerHTML = "不能为空";
		}
		
	}

	function testService() {
		var serviceId = document.getElementById("serviceId").value;
		
		if (serviceId == "") {
			document.getElementById("meg3").innerHTML = "不能为空";
		}
	}

	function testMethod() {
		var methodname = document.getElementById("methodName").value;
		var serviceId = document.getElementById("serviceId").value;
		if (methodname != "" && serviceId =="") {
			document.getElementById("serviceId").value = "${beanMethodConfig.serviceId }";
		} 
		
		if(methodname == ""){
			document.getElementById("meg4").innerHTML = "不能为空";
		}
	}

	function testBefore() {
		testPlatform();
		testBeanname();
		testService();
		testMethod();
	}

	function testSubmit() {
		var platform = document.getElementById("platformName").value;
		var serviceId = document.getElementById("serviceId").value;
		var beanname = document.getElementById("beanName").value;
		var methodname = document.getElementById("methodName").value;

		//alert("mdzz");
		if (platform == "" || serviceId == "" || beanname == ""
				|| methodname == "") {
			alert("输入数据不符合规范");
			return false;
		}

		if (platform != "" && serviceId != "" && beanname != ""
				&& methodname != "") {
			for (var n = 0; n < m; n++) {
				var mp = document.getElementById("p[" + n + "]").value;
				var mpt = document.getElementById("pt[" + n + "]").value;
				if (mp == "" ^ mpt == "") {
					alert("入参及类型必须全为空或者全不为空");
					return false;
				}
			}

			
				var mr = document.getElementById("r[0]").value;
				var mrt = document.getElementById("rt[0]").value;
				if (mr == "" ^ mrt == "") {
					alert("出参及类型必须全为空或者全不为空");
					return false;
				}
			

			return true;

		}

		alert("输入数据不符合规范");
		return false;
	}

	$(function() {
		$("#insert").click(function() {
			var platform = document.getElementById("platformName").value;
			var serviceId = document.getElementById("serviceId").value;
			var beanname = document.getElementById("beanName").value;
			var methodname = document.getElementById("methodName").value;

			$.post("problem.do", {
				'platformName' : platform,
				'serviceId' : serviceId,
				'beanName' : beanname,
				'methodName' : methodname
			}, function(data) {
				if (data == null || data == "") {
					//alert("mdzz");
					//return false;
				} else {

					alert(data);
					//return false;
				}
			});
		});

	});

	var m = 0;
	//	function addParam() {
	//		var insertText = "<div id='dp["+m+"]'>入&nbsp;&nbsp;参：<input align='middle' id='p["+m+"]' type='text' name='methodParameters[" + m + "].methodParameter'><br><br>类&nbsp;&nbsp;型：<input align='middle' id='pt["+m+"]' type='text' name='methodParameters[" + m + "].methodParameterType'><br><br></div>";
	//		document.getElementById("addParams").innerHTML = document
	//				.getElementById("addParams").innerHTML
	//				+ insertText;
	//		m++;
	//document.getElementById("insert").innerHTML = document.getElementById("insert").innerHTML+insertText;
	//	}

	$(document)
			.ready(
					function() {
						$("#but1")
								.click(
										function() {
											var insertText = "<div id='dp["+m+"]'>入&nbsp;&nbsp;参：<input align='middle' id='p["+m+"]' type='text' name='methodParameters[" + m + "].methodParameter'><br><br>类&nbsp;&nbsp;型：<input align='middle' id='pt["+m+"]' type='text' name='methodParameters[" + m + "].methodParameterType'><br><br></div>";
											$("#addParams").append(insertText);
											m++;
										});

						//		  $("#but2").click(function(){
						//			  var insertText = "<div id='dr["+i+"]'>出&nbsp;&nbsp;参：<input align='middle' id='r["+i+"]' type='text' name='methodResults[" + i +"].methodResult'><br><br>类&nbsp;&nbsp;型：<input align='middle' id='rt["+i+"]' type='text' name='methodResults[" + i + "].methodResultType'><br><br></div>";
						//		    $("#resParams").append(insertText);
						//		    i++;
						//		  });
					});

	function deleteParam() {
		var father = document.getElementById("addParams");
		var q = m;
		//alert(q)
		var child = document.getElementById("dp[" + (q - 1) + "]");
		father.removeChild(child);
		m--;
	}

	var i = 0;
	function resParam() {
		var insertText = "<div id='dr["+i+"]'>出&nbsp;&nbsp;参：<input align='middle' id='r["+i+"]' type='text' name='methodResults[" + i +"].methodResult'><br><br>类&nbsp;&nbsp;型：<input align='middle' id='rt["+i+"]' type='text' name='methodResults[" + i + "].methodResultType'><br><br></div>";
		document.getElementById("resParams").innerHTML = insertText;
		i++;
		//document.getElementById("insert").innerHTML = document.getElementById("insert").innerHTML+insertText;
	}

	function deleteRes() {
		var father = document.getElementById("resParams");
		var q = i;
		//alert(q)
		var child = document.getElementById("dr[" + (q - 1) + "]");
		father.removeChild(child);
		i--;
	}
</script>
<title>addBeanConfig</title>
</head>
<body>
	<h1 align="center">增加服务配置</h1>
	<form action="<%=basePath%>addConfig.do" method="post"
		onsubmit="return testSubmit()" id="subumitTest"
		style="margin: 0px; display: inline;">
		服务平台: <input align="middle" type="text" id="platformName"
			name="platformName" onblur="testPlatform()"
			value="${beanMethodConfig.platformName }">
		<div id="meg1" style="margin: 0px; display: inline; color: red"></div>
		<br> <br> Bean名称: <input align="middle" type="text"
			id="beanName" name="beanName" onblur="testBeanname()"
			value="${beanMethodConfig.beanName }">
		<div id="meg2" style="margin: 0px; display: inline; color: red"></div>
		<br> <br> <input style="display: none" align="middle"
			type="text" name="typeName" value=""> 方法名称: <input
			align="middle" id="methodName" type="text" name="methodName"
			onblur="testMethod()" value="${beanMethodConfig.methodName }">
		<div id="meg4" style="margin: 0px; display: inline; color: red"></div>
		<br> <br> 服务&nbsp;ID: <input align="middle" type="text"
			id="serviceId" name="serviceId" onblur="testService()">
		<div id="meg3" style="margin: 0px; display: inline; color: red"></div>
		<br> <br>

		<!-- 入参、出参添加 -->

		<div id="addParams"></div>
		<button id="but1" type="button" onclick="addParam()">添加入参</button>
		<button type="button" onclick="deleteParam()">删除入参</button>
		<br> <br>

		<div id='dr[0]'>
			出&nbsp;&nbsp;参：<input align='middle' id="r[0]" type='text'
				name='methodResults[0].methodResult'><br>
			<br>类&nbsp;&nbsp;型：<input align='middle' id="rt[0]"
				type='text' name='methodResults[0].methodResultType'><br>
			<br>
		</div>
		
<!-- 
		<div id="resParams"></div>
		<button id="but2" type="button" onclick="resParam()">添加出参</button>
		<button type="button" onclick="deleteRes()">删除出参</button>
		<br> <br>

 -->
		<!-- 入参、出参添加结束 -->
		创建人: <input align="middle" type="text" name="creator"> <br>
		<br> <input align="middle" type="submit" id="insert"
			onclick="testBefore()" value="增加">

		<button onclick="window.close()">返回</button>
	</form>


	<button id="insert" onclick="window.location='<%=basePath%>go.do'">通过XML文件添加</button>

	<br>
	<br>


</body>
</html>