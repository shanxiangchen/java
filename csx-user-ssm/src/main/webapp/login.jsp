<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Test Page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">  
	<script type="text/javascript">
		function Check(){
			var username=document.getElementById("userName").value;
			var password=document.getElementById("password").value;
			if(username==""){
				alert("账号不能为空！");
				return false;
			}
			if(password==""){
				alert("密码不能为空！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body>
    <div align="center" style="position:relative; background-color:#cccccc; width:100%; height:100%;" >
    	<div style="position:absolute; width:400px; height:180px; margin:0 auto; margin-left:-200px; margin-top:-90px; left:50%; top:50%; border:1px solid red;">
    		<form action="login" method="post">
    			<table style="width:100%; height:100%;">
    				<tr>
    					<td style="width:35%; height:70px;" align="right">账号：</td>
    					<td style="width:65%; height:70px;" align="left"><input id="userName" name="userName" type="text" style="width:150px;" value="${userName}" /></td>
    				</tr>
    				<tr>
    					<td style="width:35%; height:40px;" align="right">密码：</td>
    					<td style="width:65%; height:40px;" align="left"><input id="password" name=password type="password" style="width:150px;"/></td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center" style="height:50px;" ><input type="submit" value="登陆" onclick="return Check();"/></td>
    				</tr>
    			</table>
    		</form>
    	</div>
    </div>
  </body>
</html>
