<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	background-color: #FFFFFF;
	text-align: center;
}

.input_txt {
	width: 200px;
	height: 20px;
	line-height: 20px;
}

.info {
	height: 40px;
	line-height: 40px;
}

.info th {
	text-align: right;
	width: 65px;
	color: #4f4f4f;
	padding-right: 5px;
	font-size: 13px;
}

.info td {
	text-align: left;
}
</style>
</head>

<body>
	<form action="saver.html" name="userForm" id="userForm" target="result"
		method="post" onsubmit="return checkInfo();">
		<input type="hidden" name="userId" id="userId" value="${user.userId }" />
		<table border="0" cellpadding="0" cellspacing="0">
			<%-- <tr class="info">
				<th>编号:</th>
				<td><input type="text" name="manageno" id="manageno"
					class="input_txt" value="${user.manageno }" /></td>
			</tr> --%>

			<tr class="info">
				<th>登录名:</th>
				<td><input type="text" name="loginname" id="loginname"
					class="input_txt" value="${user.loginname }" />
					<label style="color: red;" class="red">*</label>
				</td>
			</tr>
			<tr class="info">
				<th>用户名:</th>
				<td><input type="text" name="username" id="username"
					class="input_txt" value="${user.username }" />
					<label style="color: red;" class="red">*</label>
				</td>
			</tr>

			<tr class="info">
				<th>新密码:</th>
				<td><input type="text" onfocus="this.type='password'"  name="password" id="password"
					class="input_txt" />
					<label style="color: red;" class="red">*</label>
				</td>
			</tr>

			<tr class="info">
				<th>确认密码:</th>
				<td><input type="text" onfocus="this.type='password'" name="chkpwd" id="chkpwd"
					class="input_txt" />
					<label style="color: red;" class="red">*</label>
				</td>
			</tr>

			<tr class="info">
				<th>状态:</th>
				<td><select name="status" id="status" class="input_txt">
						<option value="启用"
							<c:if test="${user.status=='启用'}">selected</c:if>>启用</option>
						<option value="未启用"
							<c:if test="${user.status=='未启用'}">selected</c:if>>未启用</option>
				</select></td>
			</tr>

			<tr class="info">
				<th>描述:</th>
				<td><input type="text" name="managedescribe"
					id="managedescribe" class="input_txt"
					value="${user.managedescribe }" /></td>
			</tr>

			<tr class="info">
				<th>角 色:</th>
				<td><select name="roleId" id="roleId" class="input_txt">
						<option value="">请选择</option>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.roleId }"
								<c:if test="${role.roleId==user.roleId }">selected</c:if>>${role.roleName }</option>
						</c:forEach>
				</select> <span id="rolid"></span>
				<label style="color: red;" class="red">*</label>
				</td>
			</tr>
		</table>
		<input type="hidden" name="myRoleId" id="myRoleId" value="" />  
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>

	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>

	<script type="text/javascript">
		var userId = "${sessionScope.user.userId}";

		if ($("#userId").val() == userId) {
			$("#roleId").attr("disabled", true);
			$("#myRoleId").val($("#roleId").val());
		}

		function getBasePath() {
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol + "//" + obj.host + "/" + contextPath;
			return basePath;
		}
 
		var dg;
		$(document).ready(function() {
			dg = frameElement.lhgDG;
			dg.addBtn('ok', '保存', function() {

				if ($("#roleId").val() == "") {
					alert("请选择角色");
					return false;
				}
				if ($("#loginname").val() == "") {
					alert("登录名不得为空！");
					$("#loginname").focus();
					return false;
				}
				if ($("#username").val() == "") {
					alert("用户名不得为空！");
					$("#username").focus();
					return false;
				}
				if ($("#Id").val()=="") {
					alert("请选择角色！");
					$("#Id").focus();
					return false;
				}
				var reg=/[`~!@#$%^&*_+<>{}\/'[\]]/im;
			    if(reg.test($("#loginname").val())){
			    	alert("登录名不得为特殊字符！");
			    	$("#loginname").val("");
			    	$("#loginname").focus();
			    	return false;
			    } 
				$("#userForm").submit();
			});

			if ($("#userId").val() != "") {
				$("#loginname").attr("readonly", "readonly");
				$("#loginname").css("color", "gray");
				/* $("#manageno").attr("readonly", "readonly");
				$("#manageno").css("color", "gray"); */
				var roleId = "${user.roleId}";
				if (roleId != "") {
					$("#roleId").val(roleId);
				}
			}
		});

		function checkInfo() {
			var value = document.getElementById("password").value;
			var chkpwd = document.getElementById("chkpwd").value;
			var regu = /\w*[0-9]+\w*$/;
			var regu2 = /\w*[a-zA-Z]+\w*$/;
			var newPassword = $("#password").val().replace(/[ ]/g, "");
			if ($("#password").val() != "") {
				if ($("#password").val() == "") {
					alert("密码不得为空！");
					$("#password").focus();
					return false;
				} else if ($("#chkpwd").val() == "") {
					alert("确认密码不得为空！");
					$("#chkpwd").focus();
					return false;
				} else if (value != chkpwd) {
					alert("两次密码输入不一致");
					return false;
				} else if (!regu.test(newPassword) || !regu2.test(newPassword)) {
					alert("密码必须包含字母和数字!");
					return false;
				} else if (value.length < 8) {
					alert("请输入8位字母和数字密码");
					return false;
				}
				return true;
			}

			return true;
		}

		function success() {
			if (dg.curWin.document.forms[0]) {
				dg.curWin.document.forms[0].action = dg.curWin.location + "";
				dg.curWin.document.forms[0].submit();
			} else {
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
	</script>
</body>
</html>