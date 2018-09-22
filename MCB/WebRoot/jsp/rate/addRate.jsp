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
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/rate/rate_add.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="rate_save.html" name="arteFrom" id="arteFrom" target="result" method="post" >
	 <input type="hidden" name="stagCode" id="stagCode"/>
	<input type="hidden" name="stagName" id="stagName"/>
	
	<input type="hidden" name="stateCode" id="stateCode"/>
	<input type="hidden" name="stateNmae" id="stateNmae"/>
	
	<input type="hidden" name="stagingPlanCode" id="stagingPlanCode"/>
	<input type="hidden" name="stagingPlanName" id="stagingPlanName"/>
	
	<input type="hidden" name="paytypeCode" id="paytypeCode" />
	<input type="hidden" name="paytypeName" id="paytypeName" />
	
	<input type="hidden" name="currencyCode" id="currencyCode" />
	<input type="hidden" name="currencyName" id="currencyName" /> 
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>分期类型:</th>
			<td>
				<select onchange="onSelectItem()" id="StagingType"  style="width:190px;">
    				<option>--请选择--</option>
    			</select>
    			<label style="color: red;" class="red">*</label>
			</td>
			<th>状 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</th>
			<td>
			<select id="State" onchange="onSelectItemTwo()"  style="width:190px;">
    				<option>--请选择--</option>
    			</select>
    			<label style="color: red;" class="red">*</label>
			</td>
			
		</tr>
		<tr class="info">
			<th>分期计划:</th>
			<td>
			 <select id="StagingPlan"  onchange="onSelectItemThree()" style="width:190px;">
    				<option>--请选择--</option>
    			</select>
    			<label style="color: red;" class="red">*</label>
			</td>
			<th>支付方式:</th>
			<td><select id="Paytype" onchange="onSelectPaytype()" style="width:190px;">
			<option value="">--请选择--</option>
			<c:forEach items="${listPaytype}" var="type">
					<option value="${type.paytypeCode}">${type.paytypeNmae}</option>
			</c:forEach>
		</select>
		<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		 <tr class="info">
			<th>币种模式:</th>
			<td>
				<select id="currency" onchange="onSelectcurrency()" class="CRselectValue" style="width:190px">
					<option value="">--请选择--</option>
					<option value="156">人民币</option>
					<option value="840">美 元</option>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>期 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数:</th>
			<td>
				<input type="text" name="ratePeriod" id="ratePeriod"  style="width:190px">
				<label style="color: red;" class="red" >*</label>
			</td>
			<th>费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;率:</th>
			<td>
				<input type="text" name="rates" id="rates"  style="width:190px">
				<label style="color: red;" class="red" >*</label>
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