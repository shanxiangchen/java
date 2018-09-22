<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"s://"+request.getServerName()+path;
	request.setAttribute("path",basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<form action="role.html" method="post" name="roleForm" id="roleForm">
		<div class="search_div">
			角色名称：<input type="text" name="roleName" value="${role.roleName }" />
			<a href="javascript:getPageHtml(1);" class="myBtn"><em>查询</em></a>
			<input type="hidden" id="userId" value="${role.roleId}"/>
			<input type="hidden" id="pageNo1" name="pageNo" value="1"/>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="0"
			cellspacing="0" class="main_table">
			<tr class="main_head">
				<th>序号</th>
				<th>角色名称</th>
				<th>权限类别</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty listPage.list}">
					<c:forEach items="${listPage.list}" var="item" varStatus="vs">
						<input type="hidden" id="permissionsCategory${item.roleId }"
							value="${item.permissionsCategory}" />
						<tr class="main_info">
							<th>${vs.index+1}</th>
							<th id="roleNameTd${item.roleId }">${item.roleName }</th>
							<th><c:choose>
									<c:when test="${item.permissionsCategory=='1'}">
										超级管理员
									</c:when>
									<c:when test="${item.permissionsCategory=='2'}">
										 管理员
									</c:when>
									<c:when test="${item.permissionsCategory=='3'}">
										 普通用户
									</c:when>
								</c:choose>
							</th>
							<th>
								<c:choose>
									<c:when test="${permissionsCategory==item.permissionsCategory&&item.roleId==role.roleId}">
										<span style="font-color:#ccc">修改</span> | <span
											style="font-color:#ccc">删除</span> | <a
											href="javascript:editRights(${item.roleId });">权限</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:editRole(${item.roleId });">修改</a> | <a
											href="javascript:delRole(${item.roleId });">删除</a> | <a
											href="javascript:editRights(${item.roleId });">权限</a>
									</c:otherwise>
								</c:choose>
							</th>
						</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="4">没有相关数据</td>
				</tr>
			</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
			<div style="float:left; width:100px; height:100px;">
				<a href="javascript:addRole();" class="myBtn"><em>新增</em> </a>
			</div>
			<input type="hidden" id="pageNo" name="pageNo" value="${listPage.pageNo}"/>
			<input type="hidden" id="totalPages" name="totalPages" value="${listPage.totalPages}"/>
			<ul id="paging"></ul>
		</div>
	</form>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
			/* 分页操作 */
			listPage();
		});

		/* 分页操作 */
		function listPage(){
			var pageNo = $("#pageNo").val();
			pageNo=parseInt(pageNo);
			var totalPages = $("#totalPages").val();
			if(totalPages>1){
				//当页数大于1时，显示
				//判断页数是否为1
				if(pageNo == '1'){
					$("#paging").append("<li id='firstPage' class='pageinfo'>首页</li>").append("<li id='prevPage' class='pageinfo'>上一页</li>");
				}else{
					$("#paging").append("<li id='firstPage' class='pageinfo'><a href='javascript:getPageHtml(1)'>首页</a></li>").append("<li id='prevPage' class='pageinfo'><a href='javascript:getPageHtml("+(pageNo-1)+")'>上一页</a></li>");
				}
				if(totalPages<=3){
					for(var i=0;i<totalPages;i++){
						if(i+1==pageNo){
							$("#paging").append("<li id='page_"+(i+1)+"' class='current'>"+(i+1)+"</li>");
						}else{
							$("#paging").append("<li id='page_"+(i+1)+"' class='pageinfo'><a href='javascript:getPageHtml("+(i+1)+")'>"+(i+1)+"</a></li>");
						}
					}
				}else{
					if(pageNo=='1'){
						$("#paging").append("<li id='page_"+1+"' class='current'>"+1+"</li>");
						$("#paging").append("<li id='page_"+2+"' class='pageinfo'><a href='javascript:getPageHtml(2)'>"+2+"</a></li>");
						$("#paging").append("<li id='page_"+3+"' class='pageinfo'><a href='javascript:getPageHtml(3)'>"+3+"</a></li>");
					}else if(pageNo == totalPages){
						$("#paging").append("<li id='page_"+(pageNo-2)+"' class='pageinfo'><a href='javascript:getPageHtml("+(pageNo-2)+")'>"+(pageNo-2)+"</a></li>");
 						$("#paging").append("<li id='page_"+(pageNo-1)+"' class='pageinfo'><a href='javascript:getPageHtml("+(pageNo-1)+")'>"+(pageNo-1)+"</a></li>");
						$("#paging").append("<li id='page_"+pageNo+"' class='current'>"+pageNo+"</li>");
					}else{
						$("#paging").append("<li id='page_"+(pageNo-1)+"' class='pageinfo'><a href='javascript:getPageHtml("+(pageNo-1)+")'>"+(pageNo-1)+"</a></li>");
						$("#paging").append("<li id='page_"+pageNo+"' class='current'>"+pageNo+"</li>");
						$("#paging").append("<li id='page_"+(pageNo+1)+"' class='pageinfo'><a href='javascript:getPageHtml("+(pageNo+1)+")'>"+(pageNo+1)+"</a></li>");
					}
				}
				if(pageNo == totalPages){
					$("#paging").append("<li id='nextPage' class='pageinfo'>下一页</li>").append("<li id='lastPage' class='pageinfo'>尾页</li>");
				}else{
					$("#paging").append("<li id='nextPage' class='pageinfo'><a href='javascript:getPageHtml("+(pageNo+1)+")'>下一页</a></li>").append("<li id='lastPage' class='pageinfo'><a href='javascript:getPageHtml("+totalPages+")'>尾页</a></li>");
				}
				$("#paging").append("<li id='theFirstPage' class='pageinfo'>第"+pageNo+"页</li>").append("<li id='totalPage' class='pageinfo'>共"+totalPages+"页</li>");
			}
		}
		
		function getPageHtml(pageNo){
			var html = "role.html";
			$("#roleForm").attr("action",html);
			$("#pageNo1").val(pageNo);
			$("#roleForm").submit();
		}
		
		function addRole() {
			var dg = new $.dialog(
					{
						title : '新增角色',
						id : 'role_new',
						width : 400,
						height : 250,
						iconTitle : false,
						cover : true,
						maxBtn : false,
						xButton : true,
						resize : false,
						html : "<div style='width:100%;height:40px;line-height:40px;text-align:center;'>"
								+ "<span style='color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;'>"
								+ "角色名称:</span><input type='text' name='roleName' id='roleName' style='width:155px'/>"
								+"<label style='color: red;' class='red'>*</label></div><br/>"
								+ "<div style='width:100%;height:40px;line-height:40px;text-align:center;'>"
								+ "<span style='color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;'>"
								+ "权限类别:</span><select name='permissionsCategory' id='permissionsCategory'style='width:159px'><option value=''>"
								+ "请选择</option><option value='2'>管理员</option><option value='3'>普通用户</option></select>"
								+"<label style='color: red;' class='red'>*</label></div>"
					});
			dg.ShowDialog();
			dg.addBtn('ok', '保存', function() {
			if($("#roleName").val()==""){
				alert("角色名称不能为空!");
				$("#roleName").focus();
				return false;
			}else{
				var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
				var	a=document.getElementById("roleName").value;
				if(pattern.test(a)){
				 alert("角色名称不能包含中文字符!");
		    	 return false;
				}
				}
			
			if($("#permissionsCategory").val()==""){
				alert("请选择权限类别");
				$("#permissionsCategory").focus();
				return false;
			}
			
				var url = "role/save.html";
				var postData = {
					roleName : $("#roleName").val(),
					permissionsCategory : $("#permissionsCategory").val()
				};
				$.post(url, postData, function(data) {
					data = eval("("+data+")");
					if (data.flag == 'success') {
						dg.curWin.location.reload();
						dg.cancel();
					} else {
						alert('角色名重复，保存失败！');
						$("#roleName").focus();
						$("#roleName").select();
					}
				});
			});
		}

		function editRole(roleId) {
			var roleName = $("#roleNameTd" + roleId).text();
			var permissionsCategory = $("#permissionsCategory" + roleId).val();
			if (permissionsCategory == "1") {
				var dg = new $.dialog(
						{
							title : '修改角色',
							id : 'role_edit',
							width : 400,
							height : 250,
							iconTitle : false,
							cover : true,
							maxBtn : false,
							xButton : true,
							resize : false,
							html : '<div style="height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bold;display:inline-block;vertical-align:middle;">角色名称:</span><input type="text" name="roleName" id="roleName" value="'+roleName+'" style="vertical-align: middle;"/><label style="color: red;" class="red">*</label></div><br/><div style="width:100%;height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;">权限类别:</span><select name="permissionsCategory" id="permissionsCategory" disabled="disabled" style="width:155px"><option value="1">管理员</option></select><label style="color: red;" class="red">*</label></div>'
						});
				dg.ShowDialog();
				dg.addBtn('ok', '保存', function() {

					if($("#roleName").val()==""){
						alert("角色名称不能为空!");
						$("#roleName").focus();
						return false;
					}else{
						var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
						var	a=document.getElementById("roleName").value;
						if(pattern.test(a)){
						 alert("角色名称不能包含中文字符!");
				    	 return false;
						}
						}
					
					if($("#permissionsCategory").val()==""){
						alert("请选择权限类别");
						$("#permissionsCategory").focus();
						return false;
					}
					var url = "role/save.html";
					var postData = {
						roleId : roleId,
						roleName : $("#roleName").val(),
						permissionsCategory : $("#permissionsCategory").val()
					};
					$.post(url, postData, function(data) {
						data = eval("("+data+")");
						if (data.flag == 'success') {
							dg.curWin.location.reload();
							dg.cancel();
						} else {
							alert('角色名重复，保存失败！');
							$("#roleName").focus();
							$("#roleName").select();
						}
					});
				});
			}

			if (permissionsCategory == "2") {
				var dg = new $.dialog(
						{
							title : '修改角色',
							id : 'role_edit',
							width : 400,
							height : 250,
							iconTitle : false,
							cover : true,
							maxBtn : false,
							xButton : true,
							resize : false,
							html : '<div style="height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bold;display:inline-block;vertical-align:middle;">角色名称: </span><input type="text" name="roleName" id="roleName" value="'+roleName+'" style="vertical-align: middle;"/></div><br/><div style="width:100%;height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;">权限类别: </span><select name="permissionsCategory" id="permissionsCategory" style="width:155px"><option value="2">管理员</option><option value="3">普通用户</option></select></div>'
						});
				dg.ShowDialog();
				dg.addBtn('ok', '保存', function() {
					if($("#roleName").val()==""){
						alert("角色名称不能为空!");
						$("#roleName").focus();
						return false;
					}else{
						var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
						var	a=document.getElementById("roleName").value;
						if(pattern.test(a)){
						 alert("角色名称不能包含中文字符!");
				    	 return false;
						}
						}
					
					if($("#permissionsCategory").val()==""){
						alert("请选择权限类别");
						$("#permissionsCategory").focus();
						return false;
					}
					var url = "role/save.html";
					var postData = {
						roleId : roleId,
						roleName : $("#roleName").val(),
						permissionsCategory : $("#permissionsCategory").val()
					};
					$.post(url, postData, function(data) {
						data = eval("("+data+")");
						if (data.flag == 'success') {
							dg.curWin.location.reload();
							dg.cancel();
						} else {
							alert('角色名重复，保存失败！');
							$("#roleName").focus();
							$("#roleName").select();
						}
					});
				});
			}
			if (permissionsCategory == "3") {
				var dg = new $.dialog(
						{
							title : '修改角色',
							id : 'role_edit',
							width : 400,
							height : 250,
							iconTitle : false,
							cover : true,
							maxBtn : false,
							xButton : true,
							resize : false,
							html : '<div style="height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bold;display:inline-block;vertical-align:middle;">角色名称: </span><input type="text" name="roleName" id="roleName" value="'+roleName+'" style="vertical-align: middle;"/></div><br/><div style="width:100%;height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;">权限类别: </span><select name="permissionsCategory" id="permissionsCategory" style="width:155px"><option value="3">普通用户</option><option value="2">管理员</option></select></div>'
						});
				dg.ShowDialog();
				dg.addBtn('ok', '保存', function() {
					if($("#roleName").val()==""){
						alert("角色名称不能为空!");
						$("#roleName").focus();
						return false;
					}else{
						var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
						var	a=document.getElementById("roleName").value;
						if(pattern.test(a)){
						 alert("角色名称不能包含中文字符!");
				    	 return false;
						}
						}
					
					if($("#permissionsCategory").val()==""){
						alert("请选择权限类别");
						$("#permissionsCategory").focus();
						return false;
					}
					var url = "role/save.html";
					var postData = {
						roleId : roleId,
						roleName : $("#roleName").val(),
						permissionsCategory : $("#permissionsCategory").val()
					};
					$.post(url, postData, function(data) {
						data = eval("("+data+")");
						if (data.flag == 'success') {
							dg.curWin.location.reload();
							dg.cancel();
						} else {
							alert('角色名重复，保存失败！');
							$("#roleName").focus();
							$("#roleName").select();
						}
					});
				});
			}
		}

		function editRights(roleId) {
			var dg = new $.dialog({
				title : '角色授权',
				id : 'auth',
				width : 280,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'role/auth.html?roleId=' + roleId
			});
			dg.ShowDialog();
		}

		/* function search() {
			$("#roleForm").submit();
		} */

		function delRole(roleId) {
			if (confirm("确定要删除该记录？")) {
				var url = "role/delete.html?roleId=" + roleId;
				$.get(url, function(data) {
					if (data == "success") {
						alert("删除成功！");
						document.location.reload();
					} else {
						alert("角色下有用户，无法进行删除！");
						document.location.reload();
					}
				});
			}
		}
	</script>
</body>
</html>