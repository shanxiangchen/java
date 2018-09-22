<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/experss/espress_list.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="express.html" method="post" name="userForm" id="userForm">
	<div class="search_div">
		<table>
			<tr>
				<th>快递服务名称:</th>
				<td><input type="text" name="expressServiceName" value="${express.expressServiceName}"/></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>快递服务名称</th>
			<th>快递服务地址</th>
			<th>是否生效</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="express" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
				<th onmouseover="this.title=this.innerText">${express.expressServiceName }</th>
				<th onmouseover="this.title=this.innerText">${express.expressServiceAddress }</th>
				<th onmouseover="this.title=this.innerText"><c:if test="${express.isUse=='1'}">是</c:if><c:if test="${express.isUse=='0'}">否</c:if></th>
				<th><a href="javascript:editExpress(${express.expressServiceNameId });">修改</a> | <a href="javascript:delExpress(${express.expressServiceNameId });">删除</a></th>
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
	  <div><a href="javascript:addExpress();" class="myBtn"><em>新增</em></a></div>
	  ${express.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>