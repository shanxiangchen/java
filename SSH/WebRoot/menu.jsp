<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>menu page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="tris is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/styles/tables.css">
  </head>
  
  <body>
    <div align="center">
    	<form action="" method="post">
    		<br/><br/><br/><br/><br/><br/>
    		<table class="tbstyle">
    			<thead>
	    			<tr>
		    			<th>ID</th>
		    			<th>姓名</th>
		    			<th>性别</th>
		    			<th>生日</th>
		    			<th>电话</th>
	    			</tr>
    			</thead>
    			<s:iterator id="user" value="userList" >
    				<tr>
	    				<td><s:property value="#user.userId"/></td>
	    				<td><s:property value="#user.nameCn"/></td>
	    				<td><s:property value="#user.sex"/></td>
	    				<td><s:date name="#user.birthday"  format="yyyy-MM-dd"/></td>
	    				<td><s:property value="#user.phone"/></td>
    				</tr>
    			</s:iterator>
    		</table>
    	</form>
    </div>
  </body>
</html>
