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
<script type="text/javascript" src="../js/errorCode/errorCode_update.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="errorCode_save_edit.html" name="errorCodeForm" id="errorCodeForm" target="result" method="post" >
	<input type="hidden" name="errorCodeId" id="errorCodeId" value="${errorCode.errorCodeId }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>错误码类别:</th>
			 <td><select name="errorCodeType" id="errorCodeType"   style="width:155px;">
			<c:if test="${errorCode.errorCodeType=='0'}">
				<option value="0"><c:if test="${errorCode.errorCodeType=='0'}">发卡接口</c:if></option>
				<option value="1">前置接口</option>
				<option value="2">CCGW接口</option> 
				<option value="3">账户决策系统</option> 
			</c:if>
			<c:if test="${errorCode.errorCodeType=='1'}">
				<option value="1"><c:if test="${errorCode.errorCodeType=='1'}">前置接口</c:if></option>
				<option value="0">发卡接口</option>
				<option value="2">CCGW接口</option> 
				<option value="3">账户决策系统</option> 
			</c:if>
			<c:if test="${errorCode.errorCodeType=='2'}">
				<option value="2"><c:if test="${errorCode.errorCodeType=='2'}">CCGW接口</c:if></option>
				<option value="0">发卡接口</option>
				<option value="1">前置接口</option>
				<option value="3">账户决策系统</option> 
				 
			</c:if>
			<c:if test="${errorCode.errorCodeType=='3'}">
			   <option value="3"><c:if test="${errorCode.errorCodeType=='3'}">账户决策系统</c:if></option> 
			   <option value="0">发卡接口</option>
			   <option value="1">前置接口</option>
			   <option value="2">CCGW接口</option>
			 
			</c:if>
		</select>
		<label style="color: red;" class="red">*</label>
		</td>
		</tr>
		<tr class="info">
			<th>错误编码:</th>
			<td><input type="text" name="errorEncoding" value="${errorCode.errorEncoding }" id="errorEncoding" class="input_txt" style="width:150px;"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>错误编码描述:</th>
		<td>
		<textarea name="errorDescribe" id="errorDescribe" style="width:150px;">${errorCode.errorDescribe}</textarea>
		<label style="color: red;" class="red">*</label>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</html>