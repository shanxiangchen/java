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
<script type="text/javascript" src="../js/cityshop/cityshop_insert.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="CityShop_save_edit.html" name="errorCodeForm" id="errorCodeForm" target="result" method="post" >
	<input type="hidden" name="shopRingCity" id="shopRingCity"/>
	<input type="hidden" name="cityno" id="cityno"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>城市:</th>
			  <td><select onchange="onSelectItem()" id="city"  style="width:155px;">
    			<option>--请选择城市--</option>
    			</select>
    			<label style="color: red;" class="red">*</label>
    		  </td>
		</tr>
		<tr class="info">
			<th>商圈名称:</th>
			<td><input type="text" name="shopRingName" id="shopRingName" style="width:150px;"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info"> 
			<th>商圈编号:</th>
		<td><input type="text" name="shopRingNo" id="shopRingNo" style="width:150px;"/>
		<label style="color: red;" class="red">*</label>
		</td>
		</tr>
		<tr class="info">
			<th>商圈描述:</th>
		<td><input type="text" name="shopRingDescribe" id="shopRingDescribe" style="width:150px;"/></td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe> 
</body>
</html>