<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>styles/tables.css">
</head>

<body>
	<div align="center">
		<form action="" method="post">
			<br /> <br /> <br /> <br />
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
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.nameCn}</td>
						<td>${user.sex}</td>
						<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
						<td>${user.phone}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</body>
</html>
