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
	<form  action="updateCardType.html" enctype="multipart/form-data" name="updateCardTypeForm" id="updateCardTypeForm" target="result"  method="post">
	 <input type="hidden" id="typeId" name="typeId" value="${bankCardType.typeId }"/>
	 <input type="hidden" id="typePicUrl" name="typePicUrl" value="${bankCardType.typePicUrl}"/>
	 <input type="hidden" id="typePicName" name="typePicName" value="${bankCardType.typePicName}"/>
	 <table border="0" cellpadding="0" cellspacing="0">
 	 <tr class="info">
 		 
		<th>卡产品种类名称:</th>
			<td><input type="text" name="typeName" id="typeName" disabled="disabled"  value="${bankCardType.typeName}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
	    <th>卡种类图片:</th>
			<td><input type="file" name="typePicFile" id="typePicName" disabled="disabled"  style="width:205px;" accept="image/*" />
			<label style="color: red;" class="red">*</label></td>
			<td colspan="2" align="right"><img src="${path}${bankCardType.typePicUrl}" width="30" height="30" alt="${cardAddInfo.cardPicName}"/></td>
		</tr>
	    <tr class="info">
		    <th>所属类型:</th>
		    <td colspan="3">
			  <c:forEach items="${listType}" var="li">
				 <input type="checkbox" name="cardOfType" disabled="disabled" value="${li.VALUE}" <c:if test="${fn:contains(bankCardType.cardOfType,li.VALUE)}">checked="checked"</c:if> >${li.CONTENT}
		      </c:forEach>
		      <label style="color: red;" class="red">*</label>
		    </td>
		</tr>
		<tr class="info">
			<th>卡产品种类介绍:</th>
			<td colspan="3">
			<textarea rows="8" cols="63" name="typeInfo" disabled="disabled"  id="typeInfo">${bankCardType.typeInfo}</textarea>
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