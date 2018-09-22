<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="state.html" method="post" name="stateForm" id="stateForm">
		<div class="search_div">
			<table>
				<tr>
					<td>状态名称:</td>
					<td><input type="text" name="stateName" id="stateName" value="${state.stateName}"></td>
					<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
				</tr>
			</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>编号</th>
			<th>状态编码</th>
			<th>状态名称</th>
			<th>分期类型名称</th>
			<th>操作</th>
		</tr>
 	 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="state" varStatus="vs">
				<tr class="main_info">
				<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
				<th onMouseOver="this.title=this.innerText">${state.stateCode}</th>
				<th onMouseOver="this.title=this.innerText">${state.stateName}</th>
				<th onMouseOver="this.title=this.innerText">${state.stagingType.stagName}</td>
				<th><a href="javascript:editState(${state.stateId});">编辑</a>|<a href="javascript:stateDel('${state.stateId}','${state.stateCode}');">删除</a></th>
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
	  <div><a href="javascript:addState();" class="myBtn"><em>新增</em></a></div>
	  ${state.page.pageStr}
     </div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/state/state_list.js"></script>
</body>
</html>