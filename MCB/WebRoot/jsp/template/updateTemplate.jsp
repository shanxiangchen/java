<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/template/template_edit.js"></script>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:15px;font-size: 10px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="template_Add_Edit.html" name="templateFrom" id="templateFrom" target="result" method="post">
	<input type="hidden" name="infoTenplateId" id="infoTenplateId" value="${template.infoTenplateId}"/>
	<table border="0" cellpadding="0" cellspacing="0">	
		<tr class="info">
			<th>信息类别:</th>
			<td>
			<select name="infoTemplateType" id="infoTemplateType" style="width:155px">
					<c:if test="${template.infoTemplateType==1}">
					<option value="1">短信提醒类</option>
					<option value="2">页面成功提示类</option>
					</c:if>
				<c:if test="${template.infoTemplateType==2}">
					<option value="2">页面成功提示类</option>
					<option value="1">短信提醒类</option>
					</c:if>
			</select>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		
		<tr class="info">
			<th>信息编码:</th>
			<td>
			<input  type="hidden" id="infoTemplateEncoding" name="infoTemplateEncoding"  value="${template.infoTemplateEncoding}">
			<input  type="text" name="" value="${template.infoTemplateEncoding}" disabled="disabled" class="input_txt" style="width:150px;">
			<label style="color: red;" class="red">*</label>
			</td>
		<tr>
		<tr class="info">
			<th>信息描述:</th>
			<td>
			<textarea name="infoTemplateDescribe" onpropertychange="if(value.length>250) value=value.substr(0,250)"    onfocus="if(this.value=='最多可输入250字') {this.value='';this.style.color='#333';}" onblur="if(this.value=='') {this.value='最多可输入250字';this.style.color='#ccc';}" id="infoTemplateDescribe" style="width:150px;">${template.infoTemplateDescribe}</textarea>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>	