<%@page import="org.apache.commons.codec.net.BCodec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 
<script>
			function showOrHidden(){
				var tb=document.getElementById('tb');
				if(tb.style.display=='none'){
					tb.style.display='block';
				}else{
					tb.style.display='none';
				}
			}
		</script>
 -->
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

	<form action="<%=basePath%>showConfig.do" method="post">
		Bean Name: <input type="text" name="beanName"><br> <br>
		Type Name: <input type="text" name="typeName"><br> <br>
		Service Id: <input type="text" name="serviceId"><br> <br>
		MethodName: <input type="text" name="methodName"><br> <br>
		<input type="submit" value="搜索">

	</form>
	<button onclick="window.location='<%=basePath%>add.do'">增加</button>
	<br>
	<br>

	<h1 align="center">BeanConfig</h1>

	<!-- 
		<input type="checkbox" onclick="showOrHidden()">显示/隐藏   
		display:none;
		id="tb"
	-->

	
	<table align="center" class="table table-hover table-condensed"
		style="width: 100%; border: 1px solid #ccc;" id="table1">
		<caption>显示/隐藏beanNAME</caption>
		<caption></caption>
		<tr>
			<td align="center"><strong>BeanName</strong></td>

			<td align="center"><strong>TypeName</strong></td>

			<td align="center"><strong>ServiceId</strong></td>

			<td align="center"><strong>MethodName</strong></td>

		</tr>
		<tbody id="table2">

			<c:forEach items="${list }" var="bc">
				<tr>
					<td align="center"><c:out value="${bc.beanName }"></c:out></td>

					<td align="center"><c:out value="${bc.typeName }"></c:out></td>
					<%
						int count = 0;
					%>
					<c:forEach items="${bc.beanMethodConfigs }" var="bmc">
						<%
							count++;
									if (count > 1) {
						%>
						<tr>
							<td align="center"><c:out value="${bc.beanName }"></c:out></td>

							<td align="center"><c:out value="${bc.typeName }"></c:out></td>
							<%
								}
							%>
							<td align="center"><c:out value="${bmc.serviceId }"></c:out>
							</td>

							<td align="center"><c:out value="${bmc.methodName }"></c:out>
							</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
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
	var pageSize = 5;
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
    window.onload = function(){
        var tab = document.getElementsByTagName('table')[0];
        tab.caption.onclick = function(){
            var trs = tab.rows;
            for(var i = 0, len = trs.length; i < len; i++){
                var cell = trs[i].cells[1];
                if(cell.style.display == 'none'){
                    cell.style.display = '';
                }else{
                    cell.style.display = 'none';
                }
            }
        }
    }
 </script>