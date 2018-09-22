<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>

</head>
<body>
	<form action="cardPrompt.html" method="post" name="cardP" id="cardP">
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th width="10%">编号</th>
			<th width="80%">温馨提示</th>
			<th>操作</th>
		</tr>
  	 	<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="cp" varStatus="vs">
				<tr class="main_info">
				<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
				<th onMouseOver="this.title=this.innerText">${cp.typePrompt}</th>
				<th><a href="javascript:editcardPrompt(${cp.typeId});">编辑</a>
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
    <div class="page_and_btn"> 
<!-- 	  <div><a href="javascript:addhappy();" class="myBtn"><em>新增</em></a></div> --> 
	  ${bankTypePrompt.page.pageStr}
  	</div> 
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	 <script type="text/javascript">
	$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
	
		
		function editcardPrompt(typeId){
			var dg = new $.dialog({
				title:'编辑温馨提示',
				id:'cardPrompt_edit',
				width:600,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'cardPrompt/BankTypePromptupdate.html?typeId='+typeId
				});
    		dg.ShowDialog();
		}
		

		
	</script>  
</body>
</html>