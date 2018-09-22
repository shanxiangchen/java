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
	<form action="${pageContext.request.contextPath }/messageRecord.html" method="post" name="bankMessageRecordForm" id="bankMessageRecordForm">
	<div class="search_div"">
		<table>
			<tr>
				<td>code码:</td>
				<td><input type="text" id="messageCode" name="messageCode" maxlength="6" value="${bankMessageRecord.messageCode}" onchange="javascript:testchange();"></td>
				<td>&nbsp;&nbsp;日期:</td>
				<td><input type="text" id="date" name="date"   value="<fmt:formatDate type="date" value="${bankMessageRecord.date}" pattern="yyyy-MM-dd"/>"  onclick="WdatePicker()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:130px;padding:0px"/></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th style="width:10%;">序号</th>
			<th style="width:15%;">日期</th>
			<th style="width:30%;">配置名称</th>
			<th style="width:15%;">code码</th>
			<th style="width:10%;">操作状态</th>
			<th style="width:10%;">失败次数</th>
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
						<fmt:formatDate type="date" value="${cf.date}" pattern="yyyy-MM-dd HH:mm:ss"/> 
					</th>
					<th onmouseover="this.title=this.innerText">
						${cf.bankMessageAllocation.allocationName}
					</th>
					<th onmouseover="this.title=this.innerText">
						${cf.messageCode}	
					</th>
				    <th onmouseover="this.title=this.innerText">
				    	
				    	<c:if test="${cf.isSuccess == 1}">成功</c:if>
				    	<c:if test="${cf.isSuccess == 0}">失败</c:if>
				    </th>
				    <th onmouseover="this.title=this.innerText">
				    	${cf.failTimes}
				    </th>
					<th>
						<c:if test="${cf.isSuccess==1 || cf.failTimes == 1}">
							<span style="font-color:#ccc">重发</span>&nbsp;|&nbsp;
						</c:if>
						<c:if test="${cf.isSuccess!=1&&cf.failTimes==0}">
							<a href="javascript:resendRecord('${cf.recordId}');" id="code_${cf.recordId}">重发</a>｜
						</c:if>
						<a href="javascript:deleteRecord('${cf.recordId}');">删除</a>
					</th>
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
	 <!-- <div>
	     <a href="javascript:resendMessageRecords()" class="myBtn"><em>消息重发</em></a>
	  </div>  -->
	  ${bankMessageRecord.page.pageStr }
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
			
		});
	    
	    /* 匹配数字 */
		 var compareNum = /^[0-9]*$/; 
		 function testchange(){
			 var messageCode = $.trim($("#messageCode").val());
			 $("#messageCode").val(messageCode);
			 if(messageCode !=null && messageCode != ''){
				 if(!compareNum.exec(messageCode)||messageCode.length>6){
					 alert("请输入6位内的code码！");
					 return false;
				 }
			 }
			 return true;
		 }
	
		function deleteRecord(id) {
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'messageRecord/delete_messageRecord.html',
						type : 'POST',
						data : {
							recordId : id
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
			$("#bankMessageRecordForm").submit();
		 }
		 
		 
		 function resendMessageRecords(){
				var dg = new $.dialog({
					title:'定时任务重新执行',
					width:550,
					height:250,
					iconTitle:false,
					cover:true,
					maxBtn:false,
					xButton:true,
					resize:false,
					page:'messageAllocation/update_messageAllocation.html'
					});
	    		dg.ShowDialog();
			}
		 
		
		  function resendRecord(id){
			 var messageCode = $.trim($("#code_"+id).parent().prev().prev().prev().html());
			 var messageDate = $.trim($("#code_"+id).parent().prev().prev().prev().prev().prev().html());
			 if (confirm("确定要重新发送记录？")) {
					$.ajax({
						url : 'messageRecord/resend_messageRecord.html',
						type : 'POST',
						data : {
							recordId : id,
							messageCode:messageCode,
							messageDate:messageDate
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
		 }
	</script>
</body> 
</html>
