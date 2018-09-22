<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详情</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<script src="../js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
 
 
</head>
<body >
	<form action=""   target="result" method="post" >
	  <div align="center">
	   <table>
	    
		<tr>
			<td align="right">手机号:<br/><br/></td>
			<td align="left">
	   			 <input type="text" disabled="disabled"  value="${bankControlLog.controlPhone}"    style="width:215px"/><br><br>
			</td>
		</tr>
		<tr>
			<td align="right">功能模块:<br/><br/></td>
			<td align="left">
			  <c:if test="${bankControlLog.controlAct=='ONE'}">
		        <input type="text" disabled="disabled"     value="登录" style="width:215px"/><br><br>
		     </c:if>
		     <c:if test="${bankControlLog.controlAct=='TWO'}">
		        <input type="text" disabled="disabled"     value="我要开卡" style="width:215px"/><br><br>
		     </c:if>
		      <c:if test="${bankControlLog.controlAct=='THREE'}">
		        <input type="text" disabled="disabled"     value="预借现金" style="width:215px"/><br><br>
		     </c:if> <c:if test="${bankControlLog.controlAct=='FOUR'}">
		        <input type="text" disabled="disabled"     value="我要还款" style="width:215px"/><br><br>
		     </c:if>
		      <c:if test="${bankControlLog.controlAct=='FIVE'}">
		        <input type="text" disabled="disabled"     value="修改密码" style="width:215px"/><br><br>
		     </c:if>
			</td>
		</tr>
		<tr>
			<td align="right">是否成功:<br><br></td>
			<td align="left"> 
				<c:if test="${bankControlLog.controlIsOk=='Y'}">
			        <input type="text" disabled="disabled"     value="成功" style="width:215px"/><br><br>
			     </c:if>
			      <c:if test="${bankControlLog.controlIsOk=='N'}">
			        <input type="text" disabled="disabled"     value="失败" style="width:215px"/><br><br>
			     </c:if>
			</td>
		</tr>
		<tr>		
		   <td align="right">记录时间:<br><br></td>
		  <td align="left">
	   			 <input type="text" disabled="disabled"   value="${bankControlLog.controlDate}"   style="width:215px"/><br><br>
		  </td>
		</tr>
		  
		<tr>
			<td align="right" colspan="1">错误原因:<br/></td>
			<td align="left"  colspan="3">
				<textarea rows="4" cols="60"  readonly="readonly">${bankControlLog.controlCause}</textarea>
			</td>
	   </tr>
	 
    </table>
    </div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
</html>