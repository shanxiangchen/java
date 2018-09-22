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
	<form action="rim.html" method="post" name="RightsInterestsMappingForm" id="RightsInterestsMappingForm">
	<div class="search_div" style="text-align: center;">
			权益包名称:
			<input type="text" name="rightsPackagerkName" id="rightsPackagerkName" value="${rightsPackagerkMapping.rightsPackagerkName}"/>
			&nbsp;&nbsp;<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" id="tableId" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th>选择</th>
			<th>权益包编号</th>
			<th>权益包名称</th>
		</tr>
  	<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				<td><input type="radio" name="PackagerkCode" value="${list.rightsPackagerkCode}"/></td>
				<td onmouseover="this.title=this.innerText">${list.rightsPackagerkCode}</td>
				<td onmouseover="this.title=this.innerText">${list.rightsPackagerkName}</td>
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
		${rightsPackagerkMapping.page.pageStr}
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
			$("#RightsInterestsMappingForm").submit();
		}	
		$(document).ready(function(){
			dgs = frameElement.lhgDG;
			dgs.addBtn('ok','确认',function(){ 
				addClick();
				dgs.cancel();
			});
		});
		function addClick(){
		var name=$('input[name="PackagerkCode"]:checked').val();
		$("#rigthsPackagerk",window.parent.document).val(name);
		$("#rigthsPackagerks",window.parent.document).val(name);
		}
		
	</script>
</body>
</html>