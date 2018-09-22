<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/patch/patch.js"></script>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath }/patch.html"
		method="post" name="patchFrom" id="patchFrom">
		<div class="search_div">
			<table>
				<tr>
					<th>原因码:</th>
					<td><input type="text" name="patchTypeCode" id="patchTypeCode"
						value="${patch.patchTypeCode}">
					</td>
					<td><a href="javascript:search();" class="myBtn"><em>查询</em></a>
					</td>
				</tr>
			</table>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>编号</th>
				<th>原因码</th>
				<th>补件类型</th>
				<th>页面展示说明</th>
				<th>是否展示</th>
				<th>档案管理说明</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty patchs}">
					<c:forEach items="${patchs}" var="plan" varStatus="vs">
						<tr class="main_info">
							<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
							<th onMouseOver="this.title=this.innerText">${plan.patchTypeCode}</th>
							<th onMouseOver="this.title=this.innerText">${plan.patchTypeNmae}</th>
							<th onMouseOver="this.title=this.innerText">${plan.patchPageExplain}</th>
							<th onMouseOver="this.title=this.innerText"><c:choose>
									<c:when test="${plan.patchPageShow=='1'}">
			       			是
			     		 </c:when>
									<c:when test="${plan.patchPageShow=='2'}">
			       			否
			     		 </c:when>
								</c:choose></th>
							<th onMouseOver="this.title=this.innerText">${plan.patchRecordExplain}</th>
							<th><a href="javascript:delPatch('${plan.patchTypeId}');">删除</a>
							</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="7">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
		<div style="float:left; width:100px; height:100px;">
			<a href="javascript:addPatch();" class="myBtn"><em>新增</em>
			</a>
		</div>
		${patch.page.pageStr}
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
</html>