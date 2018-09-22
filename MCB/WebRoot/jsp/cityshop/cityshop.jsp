<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/cityshop/cityshop_list.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="CityShop.html" method="post" name="cityshopForm" id="cityshopForm">
	<div class="search_div">
		<table>
			<tr>
				<th>商圈名称:</th>
				<td><input type="text" name="shopRingName" value="${cityShop.shopRingName}"/></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>城市</th>
			<th>商圈名称</th>
			<th>商圈编号</th>
			<th>商圈描述</th>
			<th>操作</th>
		</tr>
		 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="cityshop" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
				<th onmouseover="this.title=this.innerText">${cityshop.shopRingCity}</th>
				<th onmouseover="this.title=this.innerText">${cityshop.shopRingName}</th>
				<th onmouseover="this.title=this.innerText">${cityshop.shopRingNo}</th>
				<td onmouseover="this.title=this.innerText">${cityshop.shopRingDescribe}</th>
				<th><a href="javascript:editCityShop(${cityshop.shopRingId });">修改</a> | <a href="javascript:delCityShop(${cityshop.shopRingId });">删除</a></th>
				</tr>
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
	  <div><a href="javascript:addCityShop();" class="myBtn"><em>新增</em></a></div>
	  ${cityShop.page.pageStr}
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>