<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/innerLink/innerLink.js"></script>
<script type="text/javascript"
src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<form action="bankInnerLink.html" method="post" name="bankInnerLink" id="bankInnerLink">
		<div class="search_div" >
			 内&nbsp;&nbsp;链&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;:&nbsp;<input name="linkName" id="linkName" value="${bankInnerLink.linkName }"/>
				<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>序号</th>
				<th>内链名称</th>
				<th>内链编号</th>
				<th>内链URL</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty bankInnerLinkList}">
					<c:forEach items="${bankInnerLinkList}" var="li" varStatus="vs">
						<tr class="main_info">
							<th onmouseover="this.title=this.innerText">${vs.index+1 }</th>
							<th onmouseover="this.title=this.innerText">${li.linkName}</th>
							<th onmouseover="this.title=this.innerText">${li.linkNo}</th>
							<th onmouseover="this.title=this.innerText">${li.linkURL}</th>
							<th><a href="javascript:edit(${li.linkNo });">修改</a> | <a href="javascript:del(${li.linkNo });">删除</a></th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="5">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
		<div style="float:left; width:100px; height:100px;">
			<a href="javascript:addBankInnerLink();" class="myBtn"><em>新增</em></a>
		</div>
			${bankInnerLink.page.pageStr}
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>