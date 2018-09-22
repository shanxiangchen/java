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
	<form action="cardState.html" method="post" name="cardStateForm" id="cardStateForm">
	 <div class="search_div">
		申请日期：<input type="text" id="insertSysDate" name="insertSysDate"   value="${bankUploadFtp.insertSysDate}"  onclick="WdatePicker()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:130px;padding:0px"/>
		&nbsp;&nbsp;&nbsp;<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
			<tr class="main_head">
				<th>序号</th>
				<th>客户姓名</th>
				<th>登录已持卡卡号</th>
				<th>手机号</th>
				<th>是否开通密码 </th><!-- 0.不开通（默认）1.开通 -->
				<th>是否分期</th>
				<!-- <th>分期起始金额</th>
				<th>分期期数</th> -->
				<th>卡产品编号</th>
				<th>卡产品名称</th>
				<th>主卡版面编号</th>
				<th>单位代号</th>
				<!-- <th>申请条形码</th> -->
				<th>email地址</th>
				<th>申请日期</th>
			</tr>
			<c:choose>
				<c:when test="${not empty list}">
					<c:forEach items="${list}" var="cf" varStatus="vs">
						<tr class="main_info">
							<th><c:if
									test="${vs.index+1<=9}">0${vs.index+1}</c:if> <c:if
									test="${vs.index+1>9}">${vs.index+1}</c:if></th>
							<th>${cf.custName}</th>
							<th>${cf.cardNum}</th>
							<th>${cf.custPhone}</th>
							<th>
							  <c:if test="${cf.cardConsumerPwd=='0'}">不开通</c:if> 
							  <c:if test="${cf.cardConsumerPwd=='1'}">开通</c:if>
							</th>
							<th>
							  ${cf.autoStages}
							</th>
							<%-- <th>${cf.autoStagesMoney}</th>
							<th>${cf.autoStagesNum}</th> --%>
							<th>${cf.cardId}</th>
							<th>${cf.cardName}</th>
							<th>${cf.mainCardPage}</th>
							<th>${cf.unitNum}</th>
							<%-- <th>${cf.barCodeNumber}</th> --%>
							<th>${cf.businessSource}</th>
							<th>${cf.insertSysDate}</th>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="12">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		 
		<div class="page_and_btn"> 
		  <div style="float:left;  height:100px;">
				<a href="javascript:exportInfo()" class="myBtn"><em>下载</em></a>
		  </div>
		  ${bankUploadFtp.page.pageStr}
	  	</div> 
	</form>
	<form action="cardState/exportData.html" method="post" name="exportDataForm" id="exportDataForm">
	 <input type="hidden" name="insertSysDate" id="inDate" value="">
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/ajaxform.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
	 });
	 function search(){
			$("#cardStateForm").submit();
	 }
	 function exportInfo(){
		 if($("#insertSysDate").val()==""){
			 alert("请先选择申请日期");
		 }else{
		 $("#cardStateForm").ajaxSubmit({
				type : "POST",
				url : "cardState/exportData.html",
				dataType : "text",
				success : function(text) {
				     if(text=="1"){
				    	 alert("该申请日期无加办卡申请信息");
				    	 return;
				     }else{
				    	 exportData();
				     }
					 
				},
				error : function() {
					alert("下载异常，请联系管理员");
				}
		 });
		 }
	 }
	  
     function exportData(){
    	 var date=$("#insertSysDate").val();
    	 $("#inDate").val(date);
    	 $("#exportDataForm").submit();
        
     }
	</script>
</body> 
</html>
