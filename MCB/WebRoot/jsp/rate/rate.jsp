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
	<form action="rate.html" method="post" name="rateForm" id="rateForm">
	<div class="search_div" >
	分期类型：<select name="stagCode" id="stagCode" style="vertical-align: middle;">
			<option value="">--请选择--</option>
			<c:forEach items="${StagingTypelist}" var="type">
			<option value="${type.stagCode}"<c:if test="${rate.stagCode==type.stagCode}">selected</c:if>>${type.stagName}</option>
			</c:forEach>
		</select> 
		分期计划：<select name="stagingPlanCode" id="stagingPlanCode" style="vertical-align: middle;">
			<option value="">--请选择--</option>
			<c:forEach items="${StagingPlanlist}" var="plan">
			<option value="${plan.stagingPlanCode}"<c:if test="${rate.stagingPlanCode==plan.stagingPlanCode}">selected</c:if>>${plan.stagingPlanName}</option>
			</c:forEach>
		</select> 
		状态：<select name="stateCode" id="stateCode" style="vertical-align: middle;">
			<option value="">--请选择--</option>
			<c:forEach items="${Statelist}" var="state">
			<option value="${state.stateCode}"<c:if test="${rate.stateCode==state.stateCode}">selected</c:if>>${state.stateName}</option>
			</c:forEach>
		</select> 
		&nbsp;&nbsp;&nbsp;<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table id="tableId" width="100%"  cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>期数</th>
			<th>费率</th>
			<th>分期类型名称</th>
			<th>分期计划名称</th>
			<th>分期状态名称</th>
			<th>支付方式名称</th>
			<th>币种</th>
			<th>操作</th>
		</tr>
 	 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="rates" varStatus="vs">
				<tr class="main_info">
				<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
				<th onMouseOver="this.title=this.innerText">${rates.ratePeriod}</th>
				<th onMouseOver="this.title=this.innerText">${rates.rates}</th>
				<th onMouseOver="this.title=this.innerText">${rates.stagName}</th>
				<th onMouseOver="this.title=this.innerText">${rates.stagingPlanName}</th>
				<th onMouseOver="this.title=this.innerText">${rates.stateNmae}</th>
				<th onMouseOver="this.title=this.innerText">${rates.paytypeName}</th>
				<th onMouseOver="this.title=this.innerText">${rates.currencyName}</th>
				<th><a href="javascript:editrateid(${rates.rateId});">编辑</a>|<a href="javascript:delRateById('${rates.rateId}');">删除</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="9">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
    <div class="page_and_btn"> 
	   <div >
	   <a href="javascript:addRate();" class="myBtn"><em>新增</em></a>
	   </div>
	   ${rate.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/rate/rate_list.js"></script>
</body>
</html>