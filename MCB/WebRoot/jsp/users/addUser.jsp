<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
<script type="text/javascript" src="../js/validate.js"></script>
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
	<form action="save.html" name="userForm" id="userForm" target="result"
		method="post" onsubmit="return checkInfo();">
		<input type="hidden" name="userId" id="userId" value="${user.userId }" />
		<input type="hidden" name="roleId" id="roleId" />

		<table border="0" cellpadding="0" cellspacing="0">
			<!-- <tr class="info">
				<th>编号:</th>
				<td><input type="text" name="manageno" id="manageno"
					class="input_txt" /></td>
			</tr> -->
			<tr class="info">
				<th>登录名:</th>
				<td><input type="text" name="loginname" id="loginname"
					class="input_txt" />
					<label style="color: red;" class="red" >*</label>
				</td>
			</tr>
			<tr class="info">
				<th>用户名:</th>
				<td><input type="text" name="username" id="username"
					class="input_txt" />
					<label style="color: red;" class="red">*</label>
				</td>
			</tr>
			<tr class="info">
				<th>密 码:</th>
				<td><input type="text" onfocus="this.type='password'" name="password" id="password"
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
				<td><select name="status" id="status" style="width: 207px">
						<option value="启用">启用</option>
						<option value="未启用">未启用</option>
				</select></td>
			</tr>

			<tr class="info">
				<th>描述:</th>
				<td><input type="text" name="managedescribe"
					id="managedescribe" class="input_txt" /></td>
			</tr>


			<tr class="info">
				<th>角 色:</th>
				<td><select id="Id" onchange="onSelectItemTwo()" style="width: 207px">
						<option value="">--请选择--</option>
						<c:forEach items="${roleList}" var="role">
				         <option value="${role.roleId }">${role.roleName }</option>
			            </c:forEach>
					</select>
					<label style="color: red;" class="red">*</label>
				</td>
			</tr>
		</table>
		<input type="hidden" name="permissionsCategory"
			id="permissionsCategory" />
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function getBasePath() {
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol + "//" + obj.host + "/" + contextPath;
			return basePath;
		}
		 
		function onSelectItemTwo() {
			var sel_obj = document.getElementById("Id");
			var index = sel_obj.selectedIndex;
			var value = sel_obj.options[index].value;
			$("#roleId").val(value);
		}
		function checkInfo() {
			var value = document.getElementById("password").value;
			var chkpwd = document.getElementById("chkpwd").value;
			var regu = /\w*[0-9]+\w*$/;
			var regu2 = /\w*[a-zA-Z]+\w*$/;
			var newPassword = $("#password").val().replace(/[ ]/g, "");
			/* if ($("#manageno").val() == "") {
				alert("用户编号不得为空!");
				$("#manageno").focus();
				return false;
			} else {
				var reg = /^[0-9]*$/;
				if (!reg.test($("#manageno").val())) {
					alert("用户编号只能输入数字！");
					$("#manageno").val("");
					$("#manageno").focus();
					return false;
				}

			} */
			if ($("#loginname").val() == "") {
				alert("登录名不得为空！");
				$("#loginname").focus();
				return false;
			}
			
			var reg=/[`~!@#$%^&*_+<>{}\/'[\]]/im;
		    if(reg.test($("#loginname").val())){
		    	alert("登录名不得为特殊字符！");
		    	$("#loginname").val("");
		    	$("#loginname").focus();
		    	return false;
		    } 
			
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
			return true;
		}

		var dg;
		$(document).ready(function() {
			dg = frameElement.lhgDG;
			dg.addBtn('ok', '保存', function() {
				if (!checkInfo()) {
					return;
				}
				$("#userForm").submit();
			});
		});

		function success() {
			if (dg.curWin.document.forms[0]) {
				dg.curWin.document.forms[0].action = dg.curWin.location + "";
				dg.curWin.document.forms[0].submit();
			} else {
				dg.curWin.location.reload();
			}
			dg.cancel();
		}

		function failed() {
			alert("新增失败，该用户名已存在！");
			$("#loginname").select();
			$("#loginname").focus();
		}
	</script>
</body>
</html>