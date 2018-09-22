<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<script type="text/javascript" src="js/bound/bound_list.js"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="${pageContext.request.contextPath }/bound.html" method="post" name="userForm" id="userForm">
	<div class="search_div">
		<table>
			<tr>
				<th>用户名：<input type="text" name="userPhone" value="${bound.userPhone}"/></th>
				<th>卡号：<input type="text" name="cardNo" value="${bound.cardNo}"/></th>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
		<!-- 	<th><input type="checkbox" name="cardIds" id="cardIds" onclick="sltAllbound()"/></th> -->
			<th>序号</th>
			<th>卡号</th>
			<th>卡号标示</th>
			<th>卡号类型</th>
			<th>用户名</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="bounds" varStatus="vs">
				<tr class="main_info">
				<%-- <td><input type="checkbox" name="cardIdss" id="cardIdss" value="${bounds.cardId}"/></td> --%>
				<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
				<th onmouseover="this.title=this.innerText">${bounds.cardNo }</th>
				<th onmouseover="this.title=this.innerText">
					<c:if test="${bounds.isDefaultCard=='1'}">默认卡</c:if>
					<c:if test="${bounds.isDefaultCard=='0'}">非默认卡</c:if>
				</td>
				<td onmouseover="this.title=this.innerText">${bounds.cardType }</th>
				<th onmouseover="this.title=this.innerText">${bounds.userPhone}</th>
				<th>
					<c:if test="${sessionScope.user.permissionsCategory=='1'}">
						<a href="javascript:delBound(${bounds.cardId},${bounds.userPhone});">解绑卡片</a>|<a href="javascript:editBound(${bounds.cardId});">修改用户</a>
					</c:if>
					<c:if test="${sessionScope.user.permissionsCategory=='2'}">
						<span style="font-color:#ccc">解绑卡片</span>|<span style="font-color:#ccc">修改用户</span>
				</th>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="6">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"> 
	<!-- <div  style="float:left; width:100px; height:100px;"><input type="button" onclick="l()" value="批量解绑"/></div> -->
	 ${bound.page.pageStr} 			
  	</div> 
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
</script>
</body> 
</html>
