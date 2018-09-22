<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增商户</title>
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
	<form action="saveMarketShop.html" name="marketShopForm" id="marketShopForm" target="result" method="post" >
<%-- 		<input type="hidden" name="activityId" id="activityId" value="${marketActivity.activityId}"/>
 --%>	
 	 <div align="center">
 	  <table border="0" cellpadding="3" cellspacing="0">
 	    <tr class="info">
 	       <td align="right">商户名称:</td>
 	       <td align="left">
 	       		 <input type="text" name="shopName" class="input_txt" id="shopName" maxlength="33">
 	       		 <label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
 	    <tr class="info">
 	       <td align="right">商户地址:</td>
 	       <td align="left">
 	       		<input type="text" id="shopAddress" name="shopAddress" class="input_txt" maxlength="33"/> 
 	       		<label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
 	    <tr class="info">
 	       <td align="right">手机号:</td>
 	       <td align="left">
 	       		<input type="text" id="shopPhone" name="shopPhone"  class="input_txt" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="11"/> 
 	       		<label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
 	    <tr class="info">
 	       <td align="right">商户经度:</td>
 	       <td align="left">
 	       		<input type="text" id="shopLongitude" class="input_txt" name="shopLongitude" onkeyup="clearNoNum(this)"/> 
 	       		<label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
 	    <tr class="info">
 	       <td align="right">商户纬度:</td>
 	       <td align="left">
 	       		<input type="text" id="shopLatitude" name="shopLatitude" class="input_txt" onkeyup="clearNoNum(this)"/> 
 	       		<label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
 	    <tr class="info">
 	       <td align="right">是否开放:</td>
 	       <td align="left" >
 	       		<select id="is0pen" name="is0pen" style="width:205px; height:20px;line-height:20px;">
				  <option value="请选择">请选择</option>
				  <option value="1">开放</option>
				  <option value="0">未开放</option>
				</select> 
				<label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
 	    <tr class="info">
 	       <td align="right" >是否置顶:</td>
 	       <td align="left">
 	       		<select id="isDraft" name="isDraft"  style="width:205px; height:20px;line-height:20px;">
				  <option value="请选择">请选择</option>
				  <option value="1">是</option>
				  <option value="2">否</option>
				</select> 
				<label style="color: red;" class="red">*</label>
 	       </td> 	    
 	    </tr>
	  </table>
 	</div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var dg;
		var count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			if($("#shopName").val()==""){
				alert("请输入商户名称!");
				$("#shopName").focus();
				return false;
			}
			if($("#shopAddress").val()==""){
				alert("请输入商户地址!");
				$("#shopAddress").focus();
				return false;
			}
			if($("#shopPhone").val()==""){
				alert("请输入手机号!");
				$("#shopPhone").focus();
				return false;
			}
			
			if($("#shopLongitude").val()==""){
			   alert("请输入商户经度！");
			   $("#shopLongitude").focus();
			   return false;
			}
			
			if($("#shopLatitude").val()==""){
			   alert("请输入商户纬度！");
			    $("#shopLatitude").focus();
			    return false;
			}
			
			if($("#shopPhone").val()!="" && $("#shopPhone").val().length<11){
				alert("请输入11位有效手机号码!");
				$("#shopPhone").focus();
				return false;
			}
			 
			 
			
			if($("#is0pen").val()=="请选择"){
				alert("请选择是否开放!");
				$("#is0pen").focus();
				return false;
			} 
			
			if($("#isDraft").val()=="请选择"){
				alert("请选择是否置顶!");
				$("#isDraft").focus();
				return false;
			} 
			
			if(count==0){
			   count=count+1;
			   $("#marketShopForm").submit();
			}
		  });	
		});
		
		function clearNoNum(obj){
		   obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
		   obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
		   obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
		   obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		}
		
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
</body>
</html>