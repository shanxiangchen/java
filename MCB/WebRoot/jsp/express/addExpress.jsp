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
.input_txt{width:150px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/experss/esperss_insert.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="express_save_edit.html" name="userForm" id="userForm" target="result" method="post" >
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>快递名称:</th>
			<td><input type="text" name="expressServiceName" id="expressServiceName" class="input_txt"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>快递地址:</th>
			<td><input type="text" name="expressServiceAddress" id="expressServiceAddress" class="input_txt"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>是否生效:</th>
			<td>
				<select name="isUse" id="isUse" style="width:155px;">
				<option value="">--请选择--</option>
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
			<label style="color: red;" class="red">*</label></td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>