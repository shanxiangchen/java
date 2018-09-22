 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/city/city_list.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="${pageContext.request.contextPath }/bankCity.html" method="post" name="cityForm" id="cityForm">
	<div class="search_div">
		<table>
			<tr>
				<th>地区名称:</th>
				<td><input type="text" name="cityName" id="cityName" value="${bankCity.cityName}"/></td>
				<th>&nbsp;&nbsp;</th>
				<th>编&nbsp;&nbsp;号:</th>
				<td><input type="text" name="cityNo" id="cityNo" value="${bankCity.cityNo}"/></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>编号</th>
			<th>名称</th>
			<th>父级编号</th>
			<th>行政级别</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="city" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1 }</td>
				<th onmouseover="this.title=this.innerText">${city.cityNo }</th>
				<th onmouseover="this.title=this.innerText">${city.cityName }</th>
				<td onmouseover="this.title=this.innerText">${city.cityParentNo }</th>
				 <th onmouseover="this.title=this.innerText"><c:if test="${city.cityAdministration=='1'}">省</c:if>
					<c:if test="${city.cityAdministration=='2'}">市</c:if>
					<c:if test="${city.cityAdministration=='3'}">区/县</c:if>
				</th> 
				<th><a href="javascript:editBankCity(${city.cityId });">修改</a> | <a href="javascript:delUser(${city.cityId });">删除</a></th>
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
	  <div><a href="javascript:addCity();" class="myBtn"><em>新增</em></a></div>
	  ${bankCity.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>