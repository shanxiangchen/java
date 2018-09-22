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
	<form action="${pageContext.request.contextPath }/messagepushrecord.html" method="post" name="messagepushrecordForm" id="messagepushrecordForm">
	<div class="search_div"">
		<table>
			<tr>
				<td>卡号:</td>
				<td><input type="text" id="card" name="card" maxlength="16"></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th style="width:10%;">序号</th>
			<th style="width:15%;">卡号</th>
			<th style="width:15%;">操作</th> 
		</tr>
		 <c:choose>
			<c:when test="${not empty messageRecordList}">
				<c:forEach items="${messageRecordList}" var="cf" varStatus="vs">
				<tr class="main_info">
					<th>
						<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
						<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th>
					<th onmouseover="this.title=this.innerText">
						${cf.bankMessageAllocation.allocationName}
					</th>
					<th>
						<c:if test="${cf.isSuccess!=1&&cf.failTimes==0}">
							<a href="javascript:resendRecord();" id="code_${cf.recordId}">重发</a>｜
						</c:if>
					</th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="3">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
			
		});
	    
		
		 function search(){
			$("#messagepushrecordForm").submit();
		 }
		 
		 
	/* 	
		  function resendRecord(){
			 if (confirm("确定要重新发送记录？")) {
					$.ajax({
						url : 'messagepushrecord/resend_messagepushrecord.html',
						type : 'POST',
						data : {
							messageCode:card
						},
						timeout : 1000,
						async : false,
						success : function(data) {
							var text = eval("("+data+")");
							if (text.errorInfo == "success") {
								alert("发送成功");
								document.location.reload();
							} else {
								alert("发送失败！");
								document.location.reload();
							}
						},
						error : function() {
							alert("发送失败！");
							document.location.reload();
						}
					});
				}
		 } */
	</script>
</body> 
</html>
