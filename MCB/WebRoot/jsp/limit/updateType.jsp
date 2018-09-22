<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信业银行信用卡APP后台管理系统</title>
	<link type="text/css" rel="stylesheet" href="../css/main.css"/>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/linesType/update__LinesType.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:150px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>

</head>
<body>
	<form action="updatesave.html" name="typeForm" id="typeForm" target="result" method="post" enctype="multipart/form-data">
		<input type="hidden" name="linesTypeId" id="linesTypeId" value="${bankLinesType.linesTypeId}">
		<input type="hidden" name="linesType" id="linesType" value="${bankLinesType.linesType}">
	<table border="0" cellpadding="0" cellspacing="0">

		<tr class="info">
	    <th>额度类型:</th>
			<td>
				<c:if test="${bankLinesType.linesType=='A'}">
				<input type="text" name="linesType"  disabled="disabled" value="临额"  style="width:155px">
				</c:if>
				<c:if test="${bankLinesType.linesType=='B'}">
				<input type="text" name="linesType" disabled="disabled" value="固额"  style="width:155px">
				</c:if>

			</td>
		</tr> 
		
		<tr class="info">
	    <th>次数:</th>
			<td>
				<input type="text" name="linesNumber" id="linesNumber" value="${bankLinesType.linesNumber}" style="width:155px">
				<label style="color: red;" class="red"  >*</label>
				
			</td>
		</tr> 
		<tr class="info">
	    <th>额度权限:</th>
			<td>
				<input type="text" name="linesMaxValue" id="linesMaxValue" value="${bankLinesType.linesMaxValue}" style="width:155px">
				<label style="color: red;" class="red"  >*</label>
			</td>
		</tr> 
		
		<tr class="info">
	    <th>提示信息:</th>
			<td>
				<input type="text" name="promptInformation" id="promptInformation" value="${bankLinesType.promptInformation}" style="width:155px">
				<label style="color: red;" class="red"  >*</label>
				
			</td>
		</tr> 
		
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>