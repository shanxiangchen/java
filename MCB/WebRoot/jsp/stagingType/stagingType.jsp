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
	<form action="stagingType.html" method="post" name="StagingTypeForm" id="StagingTypeForm">
	<div class="search_div"">
		<table>
			<tr>
				<td>分期类型名称:</td>
				<td><input type="text" name="stagName" id="stagName" value="${stagingType.stagName}"></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	<table id="tableId" width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>编号</th>
			<th>分期类型编码</th>
			<th>分期类型名称</th>
			<th>操作</th>
		</tr>
 	 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="stagingType" varStatus="vs">
				<tr class="main_info">
				<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
				<th onMouseOver="this.title=this.innerText">${stagingType.stagCode}</th>
				<th onMouseOver="this.title=this.innerText">${stagingType.stagName}</th>
				<th><a href="javascript:editStagingType(${stagingType.id});">编辑</a>|<a href="javascript:stagingTypeDel('${stagingType.id}','${stagingType.stagCode}');">删除</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="4">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	 <div class="page_and_btn">
		<div><a href="javascript:addstagingType();" class="myBtn"><em>新增</em></a></div>
		 ${stagingType.page.pageStr }
	 </div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/stagingType/stagingType_list.js"></script>
</body>
</html>