<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath }/quota.html" method="post" name="quotaForm" id="quotaForm">
		 <div class="search_div">
		申请日期：<input type="text" name="beginDate" id="beginDate" onclick="display()" value="${temporaryQuota.beginDate}"   readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:100px;padding:0px"/>-
		<input type="text" name="endDate" id="endDate" onclick="display()" value="${temporaryQuota.endDate}"   readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:100px;padding:0px"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		<input type="hidden" name="searchAll" id="searchAll" value="${temporaryQuota.searchAll}">
	    </div>  
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
			<tr class="main_head">
				<th>序号</th>
				<th>申请日期</th>
				<th>申请时间</th>
				<th width="15%">卡号</th>
				<th>期望调升后的额度</th>
				<th>临时额度生效日期</th>
				<th>临时额度失效日期</th>
				<th>临时额度用途</th>
				<th>账户决策结果</th>
				<th>可调整金额</th>
			</tr>
			<c:choose>
				<c:when test="${not empty list}">
					<c:forEach items="${list}" var="cf" varStatus="vs">
						<tr class="main_info">
							<th>
						      <c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if> 
							  <c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
						    </th>
							<th>${cf.applyDate}</th>
							<th>${cf.applyTime}</th>
							<th>${cf.cardNo}</th>
							<th>${cf.expectAmount}</th>
							<th>${cf.effectiveDate}</th>
							<th>${cf.expirationDate}</th> 
							<th>${cf.usedTypeName}</th> 
							<th>${cf.decisionResult}</th> 
							<th>${cf.adjustableAmount}</th> 
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="10">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
		    <div>
		    <a href="javascript:quotaExport();" class="myBtn"><em>导出</em></a>
		    </div>
			${temporaryQuota.page.pageStr}
		</div>
	</form>
	<form action="quota/excel.html" method="post" name="exportForm" id="exportForm">
	 <input type="hidden" name="beginDate" id="begin">
	 <input type="hidden" name="endDate" id="end">
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});
 
		function search() {
			var endDate=null;
			var beginDate = document.getElementById("beginDate").value;   
    		 endDate = document.getElementById("endDate").value; 
    		  if(endDate=="" ||endDate==null){
    		  var date = new Date();
	        var seperator1 = "-";
	        var year = date.getFullYear();
	        var month = date.getMonth() + 1;
	        var strDate = date.getDate();
	        if (month >= 1 && month <= 9) {
	            month = "0" + month;
	        }
	        if (strDate >= 0 && strDate <= 9) {
	            strDate = "0" + strDate;
	        }
	         endDate = year + seperator1 + month + seperator1 + strDate;
	    	} 
			if(beginDate>endDate){
				alert("申请开始日期大于申请结束日期");
				return false;
			}
			$("#quotaForm").submit();
		}
		
		function quotaExport(applyDate){
			if($("#beginDate").val()==""){
				alert("请选择申请开始日期");
				return;
			}
			if($("#endDate").val()==""){
				alert("请选择申请结束日期");
				return;
			}
			
			
		    $("#begin").val($("#beginDate").val());
		    $("#end").val($("#endDate").val());
		    $("#exportForm").submit();
		}
		
		function display(){
			WdatePicker({skin:'whyGreen',startDate:'%y%M%d',dateFmt:'yyyyMMdd'});
		}
	</script>
</body>
</html>
