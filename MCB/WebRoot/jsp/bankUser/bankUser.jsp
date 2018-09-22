<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>

<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="${pageContext.request.contextPath }/buc.html" method="post" name="bankUserForm" id="bankUserForm">
		<div class="search_div">
		<table align="left">
			<tr>
				<td>用户:<input type="text" name="userPhone" value="${bankUser.userPhone}"/></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
		</div>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
			<tr class="main_head">
				<th>序号</th>
				<th>手机用户</th>
				<th>状态 </th>
				<th>时间</th>
				<th>操作</th>
			</tr>
	 		<c:choose>
				<c:when test="${not empty list}">
					<c:forEach items="${list}" var="li" varStatus="vs">
						<tr class="main_info">
							<th onmouseover="this.title=this.innerText">
								<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
								<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<th onmouseover="this.title=this.innerText">${li.userPhone }</th>
							<th onmouseover="this.title=this.innerText">
								<c:if test="${li.userOne>=3}">锁定</c:if>
								<c:if test="${li.userOne<3}">正常</c:if>
								<c:if test="${li.userOne==null}">正常</c:if>
							</th>
							<th onmouseover="this.title=this.innerText">${li.userTwo}</th>
							<th>
							  <c:if test="${li.userOne>=3}">
							    <a href="javascript:editBankUser('${li.userId}')">解锁</a>
							  </c:if>
							  <c:if test="${li.userOne<3 or li.userOne==null}"><span style="font-color:#ccc">解锁</span></c:if>
							</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="5">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
	</table>
		<div class="page_and_btn"> 
		 	${bankUser.page.pageStr}		
	  	</div> 
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
			function search(){
			$("#bankUserForm").submit();
		}
		
		function editBankUser(userId){
			if(confirm("确定要编辑该记录？")){
				$.ajax({
					url:'buc/bankUser_Edit.html', 
					type: 'POST', 
					data: {userId:userId},
					dataType:'text',
					async: false,
					success: function(text){
						if(text=="success"){
							 alert("编辑成功！");
							 document.location.reload();
						}else{
							 alert("编辑失败！");
							 document.location.reload();
						}
			   		},
					error:function(){
	     	               alert("删除失败！");
	     	               document.location.reload();
		             }  
				});
			}
		}
	</script>
</body> 
</html>
