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
<script type="text/javascript" src="../js/city/city_update.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="bankCity_save_edit.html" name="errorCodeForm" id="errorCodeForm" target="result" method="post" >
		<input type="hidden" name="cityId" id="cityId" value="${bankCity.cityId}"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>编号:</th>
			 <td>
			 	<input type="text" disabled="disabled" value="${bankCity.cityNo}" style="width:150px;"/>
			 	<label style="color: red;" class="red">*</label>
			 </td>
		</tr>
		<tr class="info">
			<th>地区名称:</th>
			<td><input type="text" name="cityName" id="cityName" value="${bankCity.cityName}"  style="width:150px;"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>父级别编号:</th>
		<td>
		<input type="text" disabled="disabled" value="${bankCity.cityParentNo}" style="width:150px;"/>
		</td>
		</tr>
		<tr class="info">
			<th>行政级别:</th>
		<td>
			<select disabled="disabled" style="width:155px;">
				 <c:if test="${bankCity.cityAdministration=='1'}">
				<option value="1"><c:if test="${bankCity.cityAdministration=='1'}">省</c:if></option>
				<option value="2">市</option>
				<option value="3">区/县</option>
				</c:if>
				<c:if test="${bankCity.cityAdministration=='2'}">
				<option value="2"><c:if test="${bankCity.cityAdministration=='2'}">市</c:if></option>
				<option value="1">省</option>
				<option value="3">区/县</option>
				</c:if>
				<c:if test="${bankCity.cityAdministration=='3'}">
				<option value="3"><c:if test="${bankCity.cityAdministration=='3'}">区/县</c:if></option>
				<option value="2">省</option>
				<option value="1">市</option>
				</c:if>
			</select>
			<label style="color: red;" class="red">*</label>
		</td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>