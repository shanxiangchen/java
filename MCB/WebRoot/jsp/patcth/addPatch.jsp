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
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/patch/patchAdd.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="editPacth.html" name="patchForm" id="patchForm" target="result" method="post">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>原因码:</th>
			<td>
				<input type="text" name="patchTypeCode" id="patchTypeCode"style="width:149px"/>
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
		<th>补件类型:</th>
			<td>
			<select name="patchTypeNmae" id="patchTypeNmae" style="width:155px">
				<option value="">--请选择--</option>
				<option value="图片">图片</option>
				<option value="信息">信息</option>
			</select>
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
				<th>是否展示:</th>
			<td>
				<select name="patchPageShow" id="patchPageShow" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1">是</option>
					<option value="2">否</option>
				</select>
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>页面展示说明:</th>
			<td>
				<textarea name="patchPageExplain" id="patchPageExplain" style="width:150px;"></textarea>
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>档案管理说明:</th>
			<td>
				<textarea name="patchRecordExplain" id="patchRecordExplain" style="width:150px;"></textarea>
			</td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
<script type="text/javascript">			
/* window.onload=function(){
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
	}; */		
</script>
</html>