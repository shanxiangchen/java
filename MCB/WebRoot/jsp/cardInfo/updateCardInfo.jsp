<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align:right;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="deitCardInfo.html" enctype="multipart/form-data" name="CardInfoForm" id="CardInfoForm" target="result"  method="post">
		<input type="hidden"  name="cardId" value="${cardInfo.cardId}"/>
		<input type="hidden"  name="cardNum" value="${cardInfo.cardNum}"/>
	<table border="0" cellpadding="0" cellspacing="0">
 	<tr class="info">
 	    <th>产品编码:</th>
			<td><input type="text" name="cardNum" disabled="disabled" value="${cardInfo.cardNum}" maxlength="30" id="cardNum" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
		<th>产品名称:</th>
			<td><input type="text" maxlength="50" name="cardNmae" id="cardNmae" value="${cardInfo.cardNmae}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
		</tr>
		
	<tr class="info">
		<th>产品类型:</th>
			<td>
			<select name="cardType" id="cardType" style="width:205px;">
				<c:forEach items="${list}" var="li">
					<option value="${li.value}" <c:if test="${li.value==cardInfo.cardType}">selected</c:if>>${li.content}</option>
				</c:forEach>
			</select>
			<label style="color: red;" class="red">*</label></td>
		
		<th>产品图片:</th>
			<td><input type="file" name="cardPicUrlFile" id="cardPicUrl"   style="width:205px;" accept="image/*" />
			<td colspan="2" align="right"><img src="${path}${cardInfo.cardPicUrl}" width="30" height="30" alt="${cardInfo.cardPicName}"/></td>
			
		</tr>
	
		
	<tr class="info">
			<th>产品介绍:</th>
			<td colspan="3">
			<textarea rows="4" cols="63" name="cardinfo" id="cardinfo">${cardInfo.cardinfo}</textarea>
			<label style="color: red;" class="red">*</label></td>
	</tr>	
		</form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<script type="text/javascript">
var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			if($("#cardNmae").val()==""){
				alert("带有*号的必填!");
				$("#cardNmae").focus();
				return false;
			}if($("#cardinfo").val()==""){
				alert("带有*号的必填!");
				$("#cardinfo").focus();
				return false;
			}if($("#cardType").val()==""){
				alert("带有*号的必填!");
				$("#cardType").focus();
				return false;
				}
				if(!checkImgName($("#cardPicUrl").val())){
				   return false;
				}
				$("#CardInfoForm").submit();
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