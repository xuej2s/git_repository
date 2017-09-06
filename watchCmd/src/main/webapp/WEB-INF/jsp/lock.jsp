<%@page import="org.apache.commons.codec.net.BCodec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
<title>locks</title>

</head>
<body>
	
	<button onclick="window.location='<%=basePath%>cmd.do'">cmdConfig</button>


	
	<h1 align="center">lockConfigs</h1>
	<table align="center" class="table table-hover table-condensed"
		style="width: 100%; border: 1px solid #ccc;" id="table1" >

		<tr>
			<td align="center"><strong>ID</strong></td>

			<td align="center"><strong>bizID</strong></td>

			<td align="center"><strong>biz类型</strong></td>

			<td align="center"><strong>主机锁ID</strong></td>

			<td align="center"><strong>锁类型</strong></td>

			<td align="center"><strong>创建时间</strong></td>

			<td align="center"><strong>修改时间</strong></td>

			<td align="center"><strong>锁时间</strong></td>
			
			<td align="center"><strong>tntInstId</strong></td>
		</tr>
		<tbody id="table2">

			<c:forEach items="${locks }" var="bc">
				<tr>
					<td align="center"><c:out value="${bc.id }"></c:out></td>

					<td align="center"><c:out value="${bc.bizId }"></c:out></td>

					<td align="center"><c:out value="${bc.bizType }"></c:out></td>

					<td align="center"><c:out value="${bc.masterLockId }"></c:out></td>

					<td align="center"><c:out value="${bc.lockType }"></c:out></td>

					<td align="center"><c:out value="${bc.gmtCreatedStr }"></c:out></td>

					<td align="center"><c:out value="${bc.gmtModifiedStr }"></c:out></td>
					
					<td align="center"><c:out value="${bc.lockedTimeStr }"></c:out></td>
					
					<td align="center"><c:out value="${bc.tntInstId }"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<br>
<div align="center">
<span id="spanFirst">第一页</span>
<span id="spanPre">上一页</span>
<span id="spanNext">下一页</span>
<span id="spanLast">最后一页</span>
第
<span id="spanPageNum"></span>
页/共
<span id="spanTotalPage"></span>
页
</div>
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