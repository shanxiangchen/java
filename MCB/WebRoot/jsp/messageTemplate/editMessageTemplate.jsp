<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/messageTemplate/editMessageTemplate.js"></script>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:190px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align:right;width:70px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="editBankMessageTemplate.html" name="MessageTemplateForm" id="MessageTemplateForm" target="result" method="post"  enctype="multipart/form-data" >
		<input name="inGoAndSeeLink" id="inLink" type="hidden"/>
		<input name="outGoAndSeeLink" id="outLink" type="hidden"/>
		<input type="hidden" name="advertisingImg" id="advertisingImg" value="${bankMessageTemplate.advertisingImg}"/>		
		<input type="hidden" name="advertisingImgName" id="advertisingImgName" value="${bankMessageTemplate.advertisingImgName}"/>
		<input type="hidden" name="messageTemplateId" id="messageTemplateId" value="${bankMessageTemplate.messageTemplateId}"/>	
		<input type="hidden" name="smallTypeNo" id="smallTypeNo" value="${bankMessageTemplate.smallTypeNo}"/>
		<input type="hidden" name="typeNo" value="${bankMessageTemplate.typeNo}"/>
	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tr class="info">
			<th>广告图片:</th>
			<td>
				<input type="file"  id="advertisingImgFile" name="advertisingImgFile"  accept="image/*" class="input_txt" maxlength="150"/>
			</td>
			<td><img src="${path}${bankMessageTemplate.advertisingImg}" width="30" height="30" alt="${bankMessageTemplate.advertisingImg}"/></td>
			<th>链接方式:</th>
			<td>
				<select id="LinkWay" onchange="link()" name="linkWay" class="input_txt">
					<option value="">--请选择--</option>
					<option value="in"<c:if test="${bankMessageTemplate.linkWay=='in'}">selected</c:if>>内链</option>
					<option value="out"<c:if test="${bankMessageTemplate.linkWay=='out'}">selected</c:if>>外链</option>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>内链去看看:</th>
			<td colspan="2">
			<select id="inGoAndSeeLink"  disabled="disabled" onchange="inlink()"  class="input_txt">
				<option value="">--请选择--</option>
				<c:forEach items="${listModel}" var="listModel">
					<option value="${listModel.linkNo}" <c:if test="${bankMessageTemplate.remark==listModel.linkNo}">selected</c:if>>${listModel.linkName}</option>
				</c:forEach>
			</select>
			</td>
			<th>外链去看看:</th>
			<td>
				<input type="text" id="outGoAndSeeLink" onblur="out()" disabled="disabled" value="${bankMessageTemplate.outGoAndSeeLink}" class="input_txt" maxlength="150"/>
			</td>
		</tr>
		<tr class="info">
			<th>消息盒子</th>
			<td colspan="2">
				<select onchange="onSelectItem()" disabled="disabled" id="typeNo"  class="input_txt">
					<option>--请选择--</option>
    				<c:forEach items="${list}" var="li">
    					<option value="${li.bmpcCode}"<c:if test="${li.bmpcCode==bankMessageTemplate.typeNo}">selected</c:if>>${li.bmpcName}</option>
    				</c:forEach>
    			</select>
			</td>
		  <th>消息标题:</th>
			<td colspan="2">
			<input name="messageTitle" id="messageTitle" value="${bankMessageTemplate.messageTitle}" type="text" class="input_txt" />	
			</td>
		</tr>
		<tr class="info">
			<th>文字内容:</th>
			<td colspan="4">
				<textarea rows="5" cols="60" id="writtenContent" name="writtenContent">${bankMessageTemplate.writtenContent}</textarea>
			</td>
		</tr>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>	