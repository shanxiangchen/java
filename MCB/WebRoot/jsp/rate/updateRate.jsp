<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信业银行信用卡APP后台管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/rate/updaterate.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="update_Edit_Rate.html" name="rateForm" id="rateForm" target="result" method="post" >
		<input type="hidden" name="rateId" id="rateId" value="${rate.rateId}">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>编号</th>
			<td>
				<input type="text" disabled="disabled" value="${rate.rateId}" style="width:155px"/>
			</td>
		
		<th>期数</th>
			<td>
				<input type="text" name="ratePeriod" id="ratePeriod" value="${rate.ratePeriod}"  style="width:155px">
				<label style="color: red;" class="red" >*</label>
			</td>
		</tr>
		
		 <tr class="info">
		<th>费率</th>
			<td>
				<input type="text" name="rates" id="rates" value="${rate.rates}"  style="width:155px">
				<label style="color: red;" class="red" >*</label>
			</td>
		
		<th>分期类型名称</th>
			<td>
			<select name="stagName"  disabled="disabled" id="stagName" style="width:160px">
					<c:forEach items="${listtype}" var="type">
					<option value="${rate.stagCode}"<c:if test="${type.stagCode==rate.stagCode}">selected</c:if>>${rate.stagName}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		
		 <tr class="info">
		<th>分期计划名称</th>
			<td>
			<select name="stagingPlanName" disabled="disabled" id="stagingPlanName" style="width:160px">
					<c:forEach items="${listtype}" var="type">
					<option value="${rate.stagCode}"<c:if test="${type.stagCode==rate.stagCode}">selected</c:if>>${rate.stagingPlanName}</option>
				</c:forEach>
			</select>
			</td>
		<th>分期状态名称:</th>
			<td>
			<select name="stateNmae" disabled="disabled" id="stateNmae" style="width:160px">
					<c:forEach items="${statelist}" var="state">
					<option value="${rate.stagCode}"<c:if test="${state.stagCode==rate.stagCode}">selected</c:if>>${rate.stateNmae}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		</tr>
		
		 <tr class="info">
		<th>支付方式名称</th>
			<td>
			<select name="paytypeName" disabled="disabled" id="paytypeName" style="width:160px">
					<c:forEach items="${listPaytype}" var="paytype">
					<option value="${rate.paytypeCode}"<c:if test="${paytype.paytypeCode==rate.paytypeCode}">selected</c:if>>${rate.paytypeName}</option>
				</c:forEach>
			</select>
			</td>
		<th>币种</th>
			<td>
				<input type="text" disabled="disabled" name="currencyName" id="currencyName" value="${rate.currencyName}" style="width:155px">
			</td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>