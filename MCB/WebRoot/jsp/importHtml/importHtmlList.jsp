<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入静态页面</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>
	<form action="importHtml.html" method="post" name="htmlForm" id="htmlForm">
		 
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th> 
			<th>页面名称</th>
			<th width="40%">访问路径</th>
			<th>导入日期</th>
			<th>导入用户</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				    <th> 
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th> 
					<th>${list.htmlName}</th>
					<th>${list.htmlUrl}</th>
					<th>${list.htmlDate}</th>
					<th>${list.htmlUser}</th>
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
     <div>
    	<a href="javascript:importData();" class="myBtn"><em>导入</em></a>
     </div>
     ${importHtml.page.pageStr}
    </div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});  
		function importData(){
		    var dg = new $.dialog({
				title:'导入信息',
				width:500,
				height:230,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'importHtml/import.html'
				});
    		dg.ShowDialog();
		}
		
	    function search(){
			$("#storeStagesForm").submit();
		}
		
	 
		
	</script>
</body>
</html>