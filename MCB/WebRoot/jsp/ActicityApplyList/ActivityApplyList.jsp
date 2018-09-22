<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/aalc.html" method="post" name="ActivityForm" id="ActivityForm">
	<div class="search_div">
		活动标题:<input type="text" id="activityTitle" name="activityTitle" value="${activityApplyList.activityTitle}"/>
		开始时间:<input type="text" id="activityStartDate" name="activityStartDate" value="${activityApplyList.activityStartDate}" onclick="WdatePicker()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:100px;padding:0px"/> - 
		结束时间:<input type="text" id="activityEndDate" name="activityEndDate"   value="${activityApplyList.activityEndDate}"  onclick="WdatePicker()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:100px;padding:0px"/>
		&nbsp;&nbsp;&nbsp;<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>报名编号</th>
			<th>手机号</th>
			<th>活动标题</th>
			<th>开始时间</th>
			<th>结束时间</th> 
		</tr>
		 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${list.activityApplyListId}</th>
				<th onmouseover="this.title=this.innerText">${list.activityApplyListPhone}</th>
				<th onmouseover="this.title=this.innerText">${list.marketActivity.activityTitle}</th>
				<th onmouseover="this.title=this.innerText">${list.marketActivity.activityStartDate}</th>
				<th onmouseover="this.title=this.innerText">${list.marketActivity.activityEndDate}</th>
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
		<c:if test="${not empty list}">
		  <div>
		   <a href="javascript:activityApplyList()" class="myBtn"><em>导出</em></a><!-- '${activityTitle}','${activityStartDate}','${activityEndDate}' -->
		  </div>
	    </c:if>
	    ${activityApplyList.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
	<script type="text/javascript">
		 var path = '${pageContext.request.contextPath }';
		 $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		
		function search(){
		var currentdate=null;
			var startTime = document.getElementById("activityStartDate").value;   
    		 currentdate = document.getElementById("activityEndDate").value; 
    		  if(currentdate=="" ||currentdate==null){
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
	         currentdate = year + seperator1 + month + seperator1 + strDate;
	    	} 
			if(startTime>currentdate){
				alert("开始日期大于结束日期");
				return false;
			}
			
			$("#ActivityForm").submit();
		}	
		
		function activityApplyList(){
			/* var activityTitle = $("#activityTitle").val();
			var activityStartDate = $("#activityStartDate").val();
			var activityEndDate = $("#activityEndDate").val(); */
			document.location = "aalc/excel.html";
		}
		</script>
</html>