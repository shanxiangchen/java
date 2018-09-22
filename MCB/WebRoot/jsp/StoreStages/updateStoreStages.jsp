<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
<form  action="updateStoreStages.html" name="StagesForm" id="StagesForm" target="result" method="post" >
		<input type="hidden" name="storeStagesId" id="storeStagesId" value="${storestages.storeStagesId }"/>
	<table border="0" cellpadding="0" cellspacing="0">
	
		<tr class="info">
			<th>商店创:</th>
			<td><input type="text" name="storeStagesId" id="storeStagesId" class="input_txt" disabled="disabled" value="${storestages.storeStagesId }"/>
			<label style="color: red;" class="red">*</label></td>
	
			<th>商店编号:</th>
			<td><input type="text" name="storeNo" id="storeNo"  disabled="disabled" class="input_txt" value="${storestages.storeNo }" maxlength="16"/>
			<label style="color: red;" class="red">*</label></td>
		</tr>
		
		<tr class="info">
			<th>商店名称:</th>
			<td><input type="text" name="storeName" id="storeName"  class="input_txt" value="${storestages.storeName }" maxlength="100"/>
			<label style="color: red;" class="red">*</label></td>
		 <th>商店地址:</th>
			<td><input type="text" name="storeAddr" id="storeAddr" class="input_txt" value="${storestages.storeAddr } " maxlength="190"/>
			<label style="color: red;" class="red">*</label></td>
		</tr>
	
	
	<tr class="info">
	<th>3期费率:</th>
			<td><input type="text" name="fee3" id="fee3" class="input_txt"  value="${storestages.fee3}" maxlength="25"/></td>
		
	<th>6期费率:</th>
			<td><input type="text" name="fee6" id="fee6" class="input_txt"  value="${storestages.fee6}" maxlength="25"/></td>
	
	</tr>
		 <tr class="info">
			<th>12期费率:</th>
			<td><input type="text" name="fee12" id="fee12" class="input_txt" value="${storestages.fee12 } " maxlength="25"/></td>
		
			<th>24期费率:</th>
			<td><input type="text" name="fee24" id="fee24" class="input_txt" value="${storestages.fee24 } " maxlength="25"/></td>
		</tr>
		
		<tr class="info">
			<th>城市:</th>
			<td><input type="text" name="cityNo" id="cityNo" class="input_txt" disabled="disabled"  value="${cityName}" maxlength="25">
		
		<%-- <th>分期期数:</th>
			<td><input type="text"  name="mayTransactStagesPeriods" id="mayTransactStagesPeriods"  class="input_txt" value="${storestages.mayTransactStagesPeriods}" maxlength="16">
			<label style="color: red;" class="red">*</label></td>
		</tr> 
		
		
		<tr class="info">--%>
			<th>行业名称:</th>
			<td><input type="text"  name="tradeName" id="tradeName"  class="input_txt" value="${storestages.tradeName}" maxlength="59">
			<label style="color: red;" class="red">*</label></td>
		</tr>
		
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="..js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		  
			if($.trim($("#fee3").val())=="-")$("#fee3").val("");
			if($.trim($("#fee6").val())=="-")$("#fee6").val("");
			if($.trim($("#fee12").val())=="-")$("#fee12").val("");
			if($.trim($("#fee24").val())=="-")$("#fee24").val("");
			 
		});
		
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			  if(isNoNull()){
			      var fee3=$.trim($("#fee3").val());
			      var fee6=$.trim($("#fee6").val());
			      var fee12=$.trim($("#fee12").val());
			      var fee24=$.trim($("#fee24").val());
			      if(fee3=="") $("#fee3").val("-"); 
			      if(fee6=="") $("#fee6").val("-"); 
			      if(fee12=="") $("#fee12").val("-"); 
			      if(fee24=="") $("#fee24").val("-"); 
			      $("#StagesForm").submit();
			    }
			
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
		function isNoNull(){
		    var storeName=$.trim($("#storeName").val());
		    if(storeName==""){
		      alert("商店名称不能为空！");
		      return;
		    }
		    var storeAddr=$.trim($("#storeAddr").val()); 
		    if(storeAddr==""){
		      alert("商店地址不能为空！");
		      return;
		    }
		    var fee3=$.trim($("#fee3").val());
		     
		    if(isNaN(fee3)){
		      alert("请输入正确的数字！");
		      $("#fee3").val("").focus();
		      return;
		    }
		    var fee6=$.trim($("#fee6").val());
		    if(isNaN(fee6)){
		      alert("请输入正确的数字！");
		      $("#fee6").val("").focus();
		      return;
		    }
		    var fee12=$.trim($("#fee12").val());
		    if(isNaN(fee12)){
		      alert("请输入正确的数字！");
		      $("#fee12").val("").focus();
		      return;
		    }
		    var fee24=$.trim($("#fee24").val());
		    if(isNaN(fee24)){
		      alert("请输入正确的数字！");
		      $("#fee24").val("").focus();
		      return;
		    }
		    
		    var cityNo=$.trim($("#cityNo").val());
		    if(cityNo=="请选择"){
		      alert("请选择城市！");
		      return;
		    }
		    var tradeName=$.trim($("#tradeName").val());
		    if(tradeName==""){
		      alert("行业名称不能为空！");
		      return;
		    }
		    return true;
		}
		
		
	</script>
</body>
</html>