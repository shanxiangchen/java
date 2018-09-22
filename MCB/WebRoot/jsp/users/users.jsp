<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" import="java.util.*"%>
<% String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	request.setAttribute("path", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>

<body >
	<form action="user.html" method="post" name="userForm" id="userForm" >
	<div class="search_div">
		 <input id="loginuserId" type="hidden" value="${seUser.userId}"/>
		<input type="hidden" id="pageNo1" name="pageNo" value="1"/> 
		登录名：<input type="text" id="loginname" name="loginname" value="${user.loginname }"/>
		用户名：<input type="text" id="username" name="username" value="${user.username }"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>登录名</th>
			<th>用户名</th>
			<th>角色</th>
			<th>状态</th>
			<th>描述</th>
			<th width="160">最近登录</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty listPage.list}">
				<c:forEach items="${listPage.list}" var="user" varStatus="vs">
				<tr class="main_info">
				<th><c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if></th>
				<th>${user.loginname }</th>
				<th>${user.username }</th>
				<th>${user.role.roleName }</th>
				<th>${user.status}</th>
				<th>${user.managedescribe}</th>
				<th><fmt:formatDate value="${user.lastLogin}" pattern="yyyy-MM-dd"/></th>
				<th>
					<a href="javascript:editUser(${user.userId });">修改</a> | 
					<a id = "${user.userId}" href="javascript:delUser(${user.userId });">删除</a></th>
				
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="8">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		 <div> 
		 <a href="javascript:addUser();" class="myBtn"><em>新增</em></a>
		 <a href="javascript:exportUser();" class="myBtn"><em>导出</em></a>
		 </div>
		 <input type="hidden" id="pageNo" name="pageNo" value="${listPage.pageNo}"/>
		 <input type="hidden" id="totalPages" name="totalPages" value="${listPage.totalPages}"/>
		<ul id="paging"></ul>
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
	
	function search(){
			$("#userForm").submit();
		}	
	
		$(document).ready(function(){
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
			var html = "user.html";
			$("#userForm").attr("action",html);
			$("#pageNo1").val(pageNo);
			$("#userForm").submit();
		}
		
		function addUser(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增用户',
				id:'user_new',
				width:350,
				height:400,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'user/add.html'
				});
    		dg.ShowDialog();
		}
		
		function editUser(userId){
			var dg = new $.dialog({
				title:'修改用户',
				id:'user_edit',
				width:350,
				height:400,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'user/edit.html?userId='+userId
				});
    		dg.ShowDialog();
		}
		
		
		function delUser(userId){
			var loginuserId = $("#loginuserId").val();
			if(loginuserId == userId){
				alert("不能删除登录账号！");
				return;
			}
			if(confirm("确定要删除该记录？")){
				var url = "user/delete.html?userId="+userId;
				$.get(url,function(data){
					if(data=="success"){
					    alert("删除成功！");
						document.location.reload();
					}else{
						document.location.reload();
					}
				});
			}
		}
	
		function exportUser(){
			document.location = "user/excel.html";
		}
	</script>
</body>
</html>