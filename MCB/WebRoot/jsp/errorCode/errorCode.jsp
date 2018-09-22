<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/errorCode/errorCode_list.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="errorCode.html" method="post" name="ErrorCodeForm" id="ErrorCodeForm">
	<div class="search_div" >
	<table>
		<tr>
			<th>错误类别:</th>
			<td>
				<select name="errorCodeType" id="errorCodeType" >
					<option value="">请选择</option>
					<option value="0"<c:if test="${errorCode.errorCodeType=='0'}">selected</c:if>>发卡接口</option>
					<option value="1"<c:if test="${errorCode.errorCodeType=='1'}">selected</c:if>>前置接口</option>
					<option value="2"<c:if test="${errorCode.errorCodeType=='2'}">selected</c:if>>CCGW接口</option>
					<option value="3"<c:if test="${errorCode.errorCodeType=='3'}">selected</c:if>>账户决策系统</option>
				</select>
			</td>
			<th>错误码:</th>
			<td><input type="text" name="errorEncoding" id="errorEncoding" value="${errorCode.errorEncoding}"/></td>
			<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
		</tr>
	</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>错误类别</th>
			<th>错误编码</th>
			<th>错误编码描述</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="errorCode" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
				<th onmouseover="this.title=this.innerText"><c:if test="${errorCode.errorCodeType=='0'}">发卡接口</c:if>
					<c:if test="${errorCode.errorCodeType=='1'}">前置接口</c:if>
					<c:if test="${errorCode.errorCodeType=='2'}">CCGW接口</c:if>	
					<c:if test="${errorCode.errorCodeType=='3'}">账户决策系统</c:if>	
				</th>
				<th onmouseover="this.title=this.innerText">${errorCode.errorEncoding }</th>
				<th onmouseover="this.title=this.innerText">${errorCode.errorDescribe }</th>
				<th><a href="javascript:editerrorCode(${errorCode.errorCodeId });">修改</a> | <a href="javascript:delErrorCode(${errorCode.errorCodeId });">删除</a></th>
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
	   <div><a href="javascript:addErrorCode();" class="myBtn"><em>新增</em></a></div>
	   ${errorCode.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>