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
.input_txt{width:50px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}

.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="controlConfig.html"   name="configForm" id="configForm" target="result"  method="post">
	<table border="0" cellpadding="0" cellspacing="0" align="center">
	   <tr class="info">
	    <th style="color: #4f4f4f;padding-left:60px; padding-right:30px;font-size: 13px;">监控对象</th>
	    <th style="color: #4f4f4f;padding-right:15px;font-size: 13px;">标准阀值</th>
	   </tr>
 	   <c:choose>
		 <c:when test="${not empty list}">
		  <c:forEach items="${list}" var="cf" varStatus="vs">
	        <tr class="info">
			 <th style="text-align:right; color: #4f4f4f; padding-right:15px;font-size: 13px;">
			   <c:choose>
				     <c:when test="${cf.CONFIG_TYPE=='1'}">
		       			CCGW交易接口：
		     		 </c:when>
		     		 <c:when test="${cf.CONFIG_TYPE=='2'}">
		       			制卡系统加密机交易接口：
		     		 </c:when>
		     		 <c:when test="${cf.CONFIG_TYPE=='3'}">
		       			中间业务平台加密机交易接口：
		     		 </c:when>
		     		 <c:when test="${cf.CONFIG_TYPE=='4'}">
		       			彩信平台交易接口：
		     		 </c:when>
		     		 <c:when test="${cf.CONFIG_TYPE=='5'}">
		       			前置到交易平台模拟登录接口：
		     		 </c:when>
		     		 <c:when test="${cf.CONFIG_TYPE=='6'}">
		       			Apache服务器监测：
		     		 </c:when>
			  	  </c:choose>
			 </th>
			 <td><input type="text" maxlength="5" name="configRate" value="${cf.CONFIG_RATE}" class="input_txt"/>%
			 <label style="color: red;" class="red">*</label></td>
		   </tr>
	        
		   </c:forEach>
		  </c:when>
		</c:choose>
	  </table>	 
	 </form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
        var dg; var count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if(count!=0){
			       return;
			    }
				if(!validate()){
					return;
				}
			     count=count+1;
	             $("#configForm").ajaxSubmit({
						type : "POST",
						url : "controlConfig.html",
						dataType : "text",
						success : function(text) {
							 alert("保存成功");
							 success();
						},
						error : function() {
							alert("保存失败！");
							success();
						}
				 });
				 
			});
		
		function validate(){
			var count=0,num=0;
			$("input[name='configRate']").each(function(){
				if($.trim($(this).val())==""){
					count++;
				}else{
					var reg=/^[0-9]+(\.[0-9]+)?$/;
					if(!reg.test($(this).val())){
						num++;
					}
				}
			});
			
			if(count>0){
				alert("标准阀值不能为空");
				return false;
			}else if(num>0){
				alert("标准阀值只能输入正数或小数");
				return false;
			}
			 
			return true;
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
	 });
	</script>
</html>