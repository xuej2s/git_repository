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
			serviceUrl: 'methodName.do',
			paramName: "methodName",
			delimiter: ",",
		    transformResult: function(data) {
		    	//alert(data)
		        return {
		        	
		            suggestions: $.map($.parseJSON(data), function(item) {
		            	//alert(item)
		                return { value: item };
		            })
		            
		        };
		        
		    }
		    
		});
		
		$('#beanName').autocomplete({
			serviceUrl: 'beanName',
			paramName: "beanName",
			delimiter: ",",
		    transformResult: function(data) {
		    	//alert(data)
		        return {
		        	
		            suggestions: $.map($.parseJSON(data), function(item) {
		            	//alert(item)
		                return { value: item };
		            })
		            
		        };
		        
		    }
		    
		});
		
		$('#serviceId').autocomplete({
			serviceUrl: 'serviceId.do',
			paramName: "serviceId",
			delimiter: ",",
		    transformResult: function(data) {
		    	//alert(data)
		        return {
		        	
		            suggestions: $.map($.parseJSON(data), function(item) {
		            	//alert(item)
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
	<br>
	<br>
	<form action="<%=basePath%>showConfig.do" method="post"
		style="margin: 0px; display: inline;">
		<!--
		Platform Name: <input type="text" name="platformName"><br> <br>
		Type Name: <input type="text" name="typeName"><br> <br>
	-->

		服务ID: <input type="text" name="serviceId" id="serviceId" > &nbsp;
		Bean名称: <input type="text" name="beanName" id="beanName" style="width: 222px;"> &nbsp;
		方法名称: <input type="text" name="methodName" id="methodName" style="width: 222px;"> &nbsp;<input type="submit"
			value="搜索">

	</form>
	<!-- <button onclick="window.location='<%=basePath%>add.do'">增加</button> -->

	<button
		onclick="window.open('<%=basePath%>add.do','新增信息','height=400,width=500,top=200,left=600,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')">增加</button>
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br>
	<br>


	<!-- 
		<h1 align="center">BeanConfig</h1>
		<input type="checkbox" onclick="showOrHidden()">显示/隐藏   
		display:none;
		id="tb"
	-->


	<table align="center" class="table table-hover table-condensed"
		style="width: 100%; border: 1px solid #ccc;" id="table1">

		<tr>
			<td align="center"><strong>服务平台</strong></td>

			<td align="center"><strong>Bean名称</strong></td>


			<td align="center"><strong>服务ID</strong></td>

			<td align="center"><strong>方法名称</strong></td>
			
			<td align="center"><strong>操作</strong></td>

			<!-- <td align="center" ><strong>TypeName</strong></td> -->

		</tr>
		<tbody id="table2">

			<c:forEach items="${list }" var="bc">
				<tr>
					<td align="center"><c:out value="${bc.platformName }"></c:out></td>

					<td align="center"><c:out value="${bc.beanName }"></c:out></td>

					<%
						int count = 0;
					%>
					<c:forEach items="${bc.beanMethodConfigs }" var="bmc">
						<%
							count++;
									if (count > 1) {
						%>
						<tr>
							<td align="center"><c:out value="${bc.platformName }"></c:out></td>

							<td align="center"><c:out value="${bc.beanName }"></c:out></td>

							<!-- <td align="center" ><c:out value="${bc.typeName }"></c:out></td> -->

							<%
								}
							%>
							<td align="center"><a href="javascript:;"
								onclick="window.open('<%=basePath%>showAllConfig.do?serviceId=${bmc.serviceId }','新增信息','height=400,width=800,top=200,left=600,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')"><c:out
										value="${bmc.serviceId }"></c:out></a></td>

							<td align="center"><c:out value="${bmc.methodName }"></c:out>
							</td>
							<td align="center"><a href="<%=basePath%>del.do?serviceId=${bmc.serviceId }"><c:out
										value="删除"></a>
							</c:out></td>
					</c:forEach>
					<!-- <td align="center" ><c:out value="${bc.typeName }"></c:out></td> -->

				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<span id="spanFirst">第一页</span>
<span id="spanPre">上一页</span>
<span id="spanNext">下一页</span>
<span id="spanLast">最后一页</span>
第
<span id="spanPageNum"></span>
页/共
<span id="spanTotalPage"></span>
页
</body>
</html>
<script>
	var theTable = document.getElementById("table2");
	var totalPage = document.getElementById("spanTotalPage");
	var pageNum = document.getElementById("spanPageNum");

	var spanPre = document.getElementById("spanPre");
	var spanNext = document.getElementById("spanNext");
	var spanFirst = document.getElementById("spanFirst");
	var spanLast = document.getElementById("spanLast");

	var numberRowsInTable = theTable.rows.length;
	var pageSize = 25;
	var page = 1;

	//下一页
	function next() {

		hideTable();

		currentRow = pageSize * page;
		maxRow = currentRow + pageSize;
		if (maxRow > numberRowsInTable)
			maxRow = numberRowsInTable;
		for (var i = currentRow; i < maxRow; i++) {
			theTable.rows[i].style.display = '';
		}
		page++;

		if (maxRow == numberRowsInTable) {
			nextText();
			lastText();
		}
		showPage();
		preLink();
		firstLink();
	}

	//上一页
	function pre() {

		hideTable();

		page--;

		currentRow = pageSize * page;
		maxRow = currentRow - pageSize;
		if (currentRow > numberRowsInTable)
			currentRow = numberRowsInTable;
		for (var i = maxRow; i < currentRow; i++) {
			theTable.rows[i].style.display = '';
		}

		if (maxRow == 0) {
			preText();
			firstText();
		}
		showPage();
		nextLink();
		lastLink();
	}

	//第一页
	function first() {
		hideTable();
		page = 1;
		for (var i = 0; i < pageSize; i++) {
			theTable.rows[i].style.display = '';
		}
		showPage();

		preText();
		nextLink();
		lastLink();
	}

	//最后一页
	function last() {
		hideTable();
		page = pageCount();
		currentRow = pageSize * (page - 1);
		for (var i = currentRow; i < numberRowsInTable; i++) {
			theTable.rows[i].style.display = '';
		}
		showPage();

		preLink();
		nextText();
		firstLink();
	}

	function hideTable() {
		for (var i = 0; i < numberRowsInTable; i++) {
			theTable.rows[i].style.display = 'none';
		}
	}

	function showPage() {
		pageNum.innerHTML = page;
	}

	//总共页数
	function pageCount() {
		var count = 0;
		if (numberRowsInTable % pageSize != 0)
			count = 1;
		return parseInt(numberRowsInTable / pageSize) + count;
	}

	//显示链接
	function preLink() {
		spanPre.innerHTML = "<a href='javascript:pre();'>上一页</a>";
	}
	function preText() {
		spanPre.innerHTML = "上一页";
	}

	function nextLink() {
		spanNext.innerHTML = "<a href='javascript:next();'>下一页</a>";
	}
	function nextText() {
		spanNext.innerHTML = "下一页";
	}

	function firstLink() {
		spanFirst.innerHTML = "<a href='javascript:first();'>第一页</a>";
	}
	function firstText() {
		spanFirst.innerHTML = "第一页";
	}

	function lastLink() {
		spanLast.innerHTML = "<a href='javascript:last();'>最后一页</a>";
	}
	function lastText() {
		spanLast.innerHTML = "最后一页";
	}

	//隐藏表格
	function hide() {
		for (var i = pageSize; i < numberRowsInTable; i++) {
			theTable.rows[i].style.display = 'none';
		}

		totalPage.innerHTML = pageCount();
		pageNum.innerHTML = '1';

		nextLink();
		lastLink();
	}

	hide();
</script>
<script type="text/javascript">
	window.onload = function() {
		var tab = document.getElementsByTagName('table')[0];
		tab.caption.onclick = function() {
			var trs = tab.rows;
			for (var i = 0, len = trs.length; i < len; i++) {
				var cell = trs[i].cells[4];
				if (cell.style.display == 'none') {
					cell.style.display = '';
				} else {
					cell.style.display = 'none';
				}
			}
		}
	}
</script>