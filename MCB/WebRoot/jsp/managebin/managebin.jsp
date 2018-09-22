<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/managebin/managebin_list.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="managebin.html" method="post" name="ManageBinForm" id="ManageBinForm">
	<div class="search_div">
	<table>
		<tr>
			<th>卡产品名称:</th>
			<td><input type="text" name="binProductName" id="binProductName" value="${manageBin.binProductName}"/></td>
			<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
		</tr>
	</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>卡产品编码</th>
			<th>卡产品名称</th>
			<th>卡产品号低段</th>
			<th>卡产品号高段</th>
			<th>是否白金卡</th>
			<th>卡产品所属种类</th>
			<th>操作</th>
		</tr>
		 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="managebin" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1 }</th>
				<th onmouseover="this.title=this.innerText">${managebin.binProductCode }</th>
				<th onmouseover="this.title=this.innerText">${managebin.binProductName }</th>
				<th onmouseover="this.title=this.innerText">${managebin.binProductNoLow }</th>
				<th onmouseover="this.title=this.innerText">${managebin.binProductNoHigh }</th>
				<th onmouseover="this.title=this.innerText">
				   <c:choose>
				     <c:when test="${managebin.stateCode=='01' }">
		       			非白金卡
		     		 </c:when>
		     		 <c:when test="${managebin.stateCode=='02' }">
		       			白金卡
		     		 </c:when>
			  	  </c:choose>
				</th>
				<th onmouseover="this.title=this.innerText">
			      <c:choose>
				     <c:when test="${managebin.cardOfType=='1' }">
		       			兴业通卡
		     		 </c:when>
		     		 <c:when test="${managebin.cardOfType=='2' }">
		       			立享卡
		     		 </c:when>
		     		 <c:when test="${managebin.cardOfType=='3'}">
		       			标准公务卡
		     		 </c:when>
		     		  <c:when test="${managebin.cardOfType=='4'}">
		       			其他
		     		 </c:when>
			  	  </c:choose>
				</th>
				<th><a href="javascript:editUser(${managebin.binProductId });">修改</a> | <a href="javascript:delManageBin(${managebin.binProductId });">删除</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="8">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"> 
	 <div><a href="javascript:addManageBin();" class="myBtn"><em>新增</em></a></div>
	 ${manageBin.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>