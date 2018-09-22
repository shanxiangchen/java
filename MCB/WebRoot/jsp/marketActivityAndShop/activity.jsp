<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="activity.html" method="post" name="activityForm" id="activityForm">
	<table>
	<tr>
		<th>活动标题:</th>
		<td><input type="text" name="activityTitle" id="activityTitle" value="${marketActivity.activityTitle}"/></td>
		<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
	</tr>
	
	</table>
	<table width="100%" border="0" cellpadding="0" id="tableId" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th>选择</th>
			<th>活动标题</th>
		</tr>
  	<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				<th><input type="radio" name="activityId" value="${list.activityId}"/></th>
				<th onmouseover="this.title=this.innerText">${list.activityTitle}</th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="2">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
	   ${marketActivity.page.pageStr}
	</div>
		 
	</form>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
			$("#activityForm").submit();
		}	
		$(document).ready(function(){
			dgs = frameElement.lhgDG;
			dgs.addBtn('ok','确认',function(){ 
				addClick();
				dgs.cancel();
			});
		});
		function addClick(){
		var name=$('input[name="activityId"]:checked').val();
		$("#activityId",window.parent.document).val(name);
		$("#activityIds",window.parent.document).val(name);
		}
		
	</script>
</body>
</html>