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
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="save_edit_stagingPlan.html" name="stagingPlanForm" id="stagingPlanForm" target="result" method="post">
		<input type="hidden" name="planId" id="planId" value="${stagingPlan.planId}">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>分期计划编号</th>
			<td>
				<input type="text" value="${stagingPlan.stagingPlanCode}" disabled="disabled" style="width:155px"/>
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
		<th>分期计划名称:</th>
			<td>
				<input type="text" name="stagingPlanName" id="stagingPlanName" value="${stagingPlan.stagingPlanName}" style="width:155px">
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<th>费率:</th>
			<td>
				<input type="text" name="stagingPlanRate" id="stagingPlanRate"   value="${stagingPlan.stagingPlanRate}" style="width:155px">
				<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
		<th>状态名称:</th>
			<td>
			<select name="stateCode" disabled="disabled" id="stateCode" style="width:160px">
					<c:forEach items="${stateList}" var="list">
					<option value="${list.stateCode}"<c:if test="${list.stateCode==stagingPlan.stateCode}">selected</c:if>>${list.stateName}</option>
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
	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#stagingPlanName").val()==""){
					alert("带有*号的必填!");
					$("#stagingPlanName").focus();
					return false;
				}
				if($("#stagingPlanRate").val()==""){
					alert("带有*号的必填!");
					$("#stagingPlanRate").focus();
					return false;
				}
				var stagRate=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
				 if(!stagRate.test($("#stagingPlanRate").val())){
				    	alert("费率只能输入整数和小数！");
				    	$("#stagingPlanRate").val("");
				    	$("#stagingPlanRate").focus();
				    	return false;
				    } 
				$("#stagingPlanForm").submit();
				return true;
			});
		});
		
	function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
</script>
</html>