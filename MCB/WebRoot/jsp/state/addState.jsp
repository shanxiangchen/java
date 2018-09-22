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
<script type="text/javascript" src="../js/state/state_add.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="add_Edit_State.html" name="stateForm" id="stateForm" target="result" method="post">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>状态编码:</th>
			<td>
				<input type="text" name="stateCode" id="stateCode"  style="width:155px"/>
				<label style="color: red;" class="red" >*</label>
			</td>
		</tr>
		<tr class="info">
		<th>状态名称:</th>
			<td>
				<input type="text" name="stateName" id="stateName" style="width:155px">
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
		<th>分期类型:</th>
			<td>
			<select name="stagCode" id="stagCode" style="width:160px">
				<option value="">请选择</option>
					<c:forEach items="${listType}" var="type">
					<option value="${type.stagCode}">${type.stagName}</option>
				</c:forEach>
			</select>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
<script type="text/javascript">			
window.onload=function(){
		var verification = $("*[id^='verification$']");
			if(verification.length > 0){
				for (var i = 0;i < verification.length;i++){
					verification[i].style.color="red";
			}
		}else{
			if(verification.length > 0){
				for (var i = 0;i < verification.length;i++){
					verification[i].style.color="red";
				
				}
			}
		}
	};		
</script>
</html>