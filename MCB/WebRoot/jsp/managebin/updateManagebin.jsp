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
.info th{text-align: right; color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/managebin/managebin_update.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="managebin_save_edit.html" name="managebinForm" id="managebinForm" target="result" method="post" >
	 <div align="center">
	<input type="hidden" name="binProductId" id="binProductId" value="${manageBin.binProductId }"/>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr class="info">
			<th>卡产品编码:</th>
			<td><input type="text" disabled="disabled" value="${manageBin.binProductCode }" class="input_txt"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>卡产品名称:</th>
			<td><input type="text" name="binProductName" maxlength="120" id="binProductName" value="${manageBin.binProductName }" class="input_txt"/>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>卡产品号低段:</th>
			<td><input type="text" name="binProductNoLow" maxlength="20" id="binProductNoLow" value="${manageBin.binProductNoLow }" class="input_txt"/>
			<label style="color: red;" class="red" >*</label>
			</td>
		</tr>
		<tr class="info">
			<th>卡产品号高段:</th>
			<td><input type="text" name="binProductNoHigh" id="binProductNoHigh" maxlength="20" value="${manageBin.binProductNoHigh}" class="input_txt"/>
			<label style="color: red;" class="red" >*</label>
			</td>
		</tr>
		<tr class="info">
			<th>是否白金卡:</th>
			 <td>
 	       		<select id="stateCode" style="width: 155px;" name="stateCode">
				  <option value="">请选择</option>
				  <option value="01" <c:if test="${manageBin.stateCode=='01'}">selected</c:if>>非白金卡</option> 
				  <option value="02" <c:if test="${manageBin.stateCode=='02'}">selected</c:if>>白金卡</option>
				</select>
				<label style="color: red;" class="red">*</label> 
 	       </td> 	     
		</tr>
		<tr class="info">
			<th>卡产品所属种类:</th>
			 <td>
 	       		<select id="cardOfType" style="width: 155px;" name="cardOfType">
				  <option value="">请选择</option>
				  <option value="1" <c:if test="${manageBin.cardOfType=='1'}">selected</c:if>>兴业通卡</option> 
				  <option value="2" <c:if test="${manageBin.cardOfType=='2'}">selected</c:if>>立享卡</option>
				  <option value="3" <c:if test="${manageBin.cardOfType=='3'}">selected</c:if>>标准公务卡</option>
				  <option value="4" <c:if test="${manageBin.cardOfType=='4'}">selected</c:if>>其他</option>
				</select>
				<label style="color: red;" class="red isShow">*</label> 
 	       </td> 	     
		</tr>
	</table>
	</div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
 
</html>