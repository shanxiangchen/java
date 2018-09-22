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
	<form action="${pageContext.request.contextPath }/messageAllocation.html" method="post" name="bankMessageAllocationForm" id="bankMessageAllocationForm">
	 <div class="search_div"">
		<table>
			<tr>
				<td>code码:</td>
				<td><input type="text" id="messageCode" name="messageCode" maxlength="6" value="${bankMessageAllocation.messageCode}" onchange="javascript:testchange();"></td>
				<td>&nbsp;&nbsp;配置名称:</td>
				<td><input type="text" id="allocationName" name="allocationName" value="${bankMessageAllocation.allocationName}"></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th style="width:15%;">序号</th>
			<th style="width:25%;">配置名称</th>
			<th style="width:15%;">code码</th>
			<th style="width:15%;">发送次数</th>
			<th style="width:15%;">时间间隔(s)</th>
			<th style="width:15%;">操作</th> 
		</tr>
		 <c:choose>
			<c:when test="${not empty messageAllocationList}">
				<c:forEach items="${messageAllocationList}" var="cf" varStatus="vs">
				<tr class="main_info">
				<th>
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
				</th>
				<th onmouseover="this.title=this.innerText">
					${cf.allocationName }
				</th>
				<th onmouseover="this.title=this.innerText">
					${cf.messageCode }
				</th>
			    <th onmouseover="this.title=this.innerText">
			    	<c:if test="${cf.times!=''}">${cf.times}</c:if>
			    	<c:if test="${cf.times==''}">-</c:if>
			    </th>
			    <th onmouseover="this.title=this.innerText">
			    	<c:if test="${cf.intervalTime!=''}">${cf.intervalTime}</c:if>
			    	<c:if test="${cf.intervalTime==''}">-</c:if>
			    </th>
				<th>
					<a href="javascript:editMessageAllocation('${cf.messageId}');">修改</a>|
					<a href="javascript:deleteMessageAllocation('${cf.messageId}');">删除</a>
				</th>  
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
	     <a href="javascript:addMessageAllocation()" class="myBtn"><em>新增</em></a>
	  </div> 
	  ${bankMessageAllocation.page.pageStr }
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
			
		});
	
		function addMessageAllocation(){
			var dg = new $.dialog({
				title:'新增消息配置信息',
				width:380,
				height:250,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'messageAllocation/addMessageAllocation.html'
				});
    		dg.ShowDialog();
		}
	   function editMessageAllocation(id){
			var dg = new $.dialog({
				title:'编辑消息配置信息',
				width:380,
				height:250,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'messageAllocation/update_messageAllocation.html?messageId='+id
				});
    		dg.ShowDialog();
		}
		
		 
		
		function deleteMessageAllocation(id) {
			if (confirm("确定要删除该记录？")) {
				$.ajax({
					url : 'messageAllocation/delete_messageAllocation.html',
					type : 'POST',
					data : {
						messageId : id
					},
					dataType : 'text',
					timeout : 1000,
					async : false,
					success : function(text) {
						if (text == "success") {
							alert("删除成功");
							document.location.reload();
						} else {
							alert("删除失败！");
							document.location.reload();
						}
					},
					error : function() {
						alert("删除失败！");
						document.location.reload();
					}
				});
			}
		}
		
		 function search(){
			if(!testchange()){
				return;
			}
			$("#bankMessageAllocationForm").submit();
		 }
		 
		 /* 匹配数字 */
		 var compareNum = /^[0-9]*$/; 
		 function testchange(){
			 var messageCode = $.trim($("#messageCode").val());
			 $("#messageCode").val(messageCode);
			 if(!compareNum.exec(messageCode)){
				 alert("请输入6位code码！");
				 return false;
			 }
			 return true;
		 }
		
	</script>
</body> 
</html>
