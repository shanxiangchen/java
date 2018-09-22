<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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
.info th{text-align:right;width:115px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="updateAddCardInfo.html" enctype="multipart/form-data" name="CardAddInfoForm" id="CardAddInfoForm" target="result"  method="post">
	 <input type="hidden" id="cardId" name="cardId" value="${cardAddInfo.cardId }"/>
	 <input type="hidden" id="cardPicUrl" name="cardPicUrl" value="${cardAddInfo.cardPicUrl}"/>
	 <input type="hidden" id="cardPicName" name="cardPicName" value="${cardAddInfo.cardPicName}"/>
	 <table border="0" cellpadding="0" cellspacing="0">
 	 <tr class="info">
 		<th>卡产品编码:</th>
			<td><input type="text" name="cardNum"  disabled="disabled" id="cardNum" value="${cardAddInfo.cardNum}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
		<th>卡产品名称:</th>
			<td><input type="text" name="cardName"  disabled="disabled" id="cardName" value="${cardAddInfo.cardName}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
	    <tr class="info">
		    <th>卡产品种类:</th>
			<td>
			<select name="typeId" id="typeId"  disabled="disabled" style="width:205px;">
				<option value="">请选择</option>
				<c:forEach items="${listOf}" var="li">
					<option value="${li.typeId}" <c:if test="${li.typeId==cardAddInfo.typeId}">selected</c:if>>${li.typeName}</option>
				</c:forEach>
			</select>
			<label style="color: red;" class="red">*</label></td>
		    <th>卡产品图片:</th>
			<td colspan="2" align="right"><img src="${path}${cardAddInfo.cardPicUrl}" width="30" height="30" alt="${cardAddInfo.cardPicName}"/></td>
		</tr>
		<tr class="info">
		    <th>推荐优先级:</th>
			<td><input type="text" name="cardForOrder"  disabled="disabled" id="cardForOrder" value="${cardAddInfo.cardForOrder}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
			<th>卡产品版面编号:</th>
			<td><input type="text" disabled="disabled" name="cardBmNo"   id="cardBmNo" value="${cardAddInfo.cardBmNo}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
			<%-- <th>是否可加办卡:</th>
			<td>
			  <select name="cardIsAdd" id="cardIsAdd" disabled="disabled" style="width:205px;">
				<option value="">请选择</option>
			   <option value="1" <c:if test="${cardAddInfo.cardIsAdd=='1'}">selected</c:if>>可加办卡</option>
			   <option value="2" <c:if test="${cardAddInfo.cardIsAdd=='2'}">selected</c:if>>不可加办卡</option>
			  </select><label style="color: red;" class="red">*</label>
			</td> --%>
		</tr>
		<tr class="info">
		<th>适合年龄段:</th>
			<td>
			 <c:forEach items="${listFor}" var="li">
				     <input type="checkbox" name="cardForAge"   disabled="disabled" value="${li.value}" <c:if test="${fn:contains(cardAddInfo.cardForAge,li.value)}">checked="checked"</c:if> >${li.content}
			 </c:forEach>
			 <label style="color: red;" class="red">*</label></td>
		     <th>适合性别:</th>
			<td>
			   <input type="checkbox" name="cardForSex" value="1"  disabled="disabled" <c:if test="${fn:contains(cardAddInfo.cardForSex,'1')}">checked="checked"</c:if>>男
			   <input type="checkbox" name="cardForSex" value="2"  disabled="disabled" <c:if test="${fn:contains(cardAddInfo.cardForSex,'2')}">checked="checked"</c:if>>女
			   <label style="color: red;" class="red">*</label>
			</td> 
		</tr>
		<tr class="info">
			<th>适合客户喜好:</th>
			<td colspan="4">
			  <c:forEach items="${listLike}" var="li">
				     <input type="checkbox" name="cardForLike"  disabled="disabled" value="${li.value}" <c:if test="${fn:contains(cardAddInfo.cardForLike,li.value)}">checked="checked"</c:if>>${li.content}
			  </c:forEach>
			  <label style="color: red;" class="red">*</label>
			 </td>
		</tr>
		<tr class="info">
			<th>卡产品介绍:</th>
			<td colspan="3">
			<textarea rows="4"  disabled="disabled" cols="63" name="cardInfo" id="cardInfo">${cardAddInfo.cardInfo}</textarea>
			<label style="color: red;" class="red">*</label></td>
		</tr>
		 </table>
		</form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	 
</html>