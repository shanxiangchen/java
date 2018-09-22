<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商户</title>
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
	<form action="updateOkMarketShop.html" name="marketShopForm" id="marketShopForm" target="result" method="post" >
 	  <div align="center">
 		<table border="0" cellpadding="3" cellspacing="0">
 		<c:forEach items="${listShop}" var="li">
 		   <input type="hidden" name="shopId" id="shopId" value="${li.shopId}" /><br><br>
 		     	<tr class="info">
	 	      		 <td align="right">商户id:</td>
	 	      		 <td align="left">
	 	      		  	 <input type="text" name="shopId" class="input_txt" id="shopId" value="${li.shopId}" disabled="disabled"/>
	 	      		  	  <label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">商户名称:</td>
	 	      		 <td align="left">
	 	      		 	 <input type="text" name="shopName" class="input_txt" id="shopName" value="${li.shopName}" maxlength="33">
	 	      		 	  <label style="color: red;" class="red">*</label> 
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">商户地址:</td>
	 	      		 <td align="left">
	 	      		 	<input type="text" name="shopAddress" class="input_txt" id="shopAddress"  value="${li.shopAddress}" maxlength="33"/>
	 	      		 	 <label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">手机号:</td>
	 	      		 <td align="left">
	 	      		 	 <input type="text" name="shopPhone" class="input_txt" id="shopPhone" value="${li.shopPhone}" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="11"/>
	 	      		 	  <label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">商户经度:</td>
	 	      		 <td align="left">
	 	      		 	<input type="text" name="shopLongitude" class="input_txt" id="shopLongitude" value="${li.shopLongitude}" onkeyup="clearNoNum(this)"/>
	 	      		 	<label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">商户纬度:</td>
	 	      		 <td align="left">
	 	      		   <input type="text" name="shopLatitude" class="input_txt" id="shopLatitude" value="${li.shopLatitude}" onkeyup="clearNoNum(this)"/>
	 	      		   <label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">是否开放:</td>
	 	      		 <td align="left">
		 	      		 <select id="is0pen" name="is0pen" style="width:205px; height:20px;line-height:20px;">
						  <option value="请选择">请选择</option>
						  <option value="1" <c:if test="${li.is0pen=='1'}">selected</c:if>>开放</option>
						  <option value="0"<c:if test="${li.is0pen=='0'}">selected</c:if>>未开放</option>
						 </select>
						  <label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>
	 	      	<tr class="info">
	 	      		 <td align="right">是否置顶:</td>
	 	      		 <td align="left">
	 	      		 <select id="isDraft" name="isDraft" style="width:205px; height:20px;line-height:20px;">
					  <option value="请选择">请选择</option>
					  <option value="1" <c:if test="${li.isDraft=='1'}">selected</c:if>>是</option>
					  <option value="2" <c:if test="${li.isDraft=='2'}">selected</c:if>>否</option>
					 </select>
					  <label style="color: red;" class="red">*</label>
	 	      		 </td> 
	 	      	</tr>	 
 		 </c:forEach>
 	   </table>
 	  </div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			/* var str=false;
			if(!str){ */
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
			$("#marketShopForm").submit();
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