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
	<form action="carStaging.html" method="post" name="carStagingForm"  id="carStagingForm">
  			 <div class="search_div">
  			 <table>
  			 	<tr>
  			 		<th>城市:</th>
  			 		<td><input type="text"  name="carStagingCity" id="carStagingCity" value="${carStagingCity}"/></td>
  			 		<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
  			 	</tr>
  			 </table>
				
			</div>   
			<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
				<tr class="main_head">
					<th>序号</th>
					<th>城市</th>
					<th>品牌</th>
					<th>经销商名称</th>
					<th>电话</th>
					<th>地址</th>	
				</tr>
 		  	 	<c:choose>
					<c:when test="${not empty bankCarStagings}">
						<c:forEach items="${bankCarStagings}" var="ll" varStatus="vs">
							<tr class="main_info">
							<th onMouseOver="this.title=this.innerText">
							<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
							<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<th onMouseOver="this.title=this.innerText">${ll.carStagingCity}</th>
							<th onMouseOver="this.title=this.innerText">${ll.carStagingBrand}</th>
							<th onMouseOver="this.title=this.innerText">${ll.marketingName}</th>
							<th onMouseOver="this.title=this.innerText">${ll.carStagingPhone}</th>
							<th onMouseOver="this.title=this.innerText">${ll.carStagingLocation}</th>
							
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="6">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose> 
			</table>
		<div class="page_and_btn">
			<div>
			 <a href="javascript:importData();" class="myBtn"><em>导入</em></a>
		     <a href="javascript:carStagingExport('${carStagingCity}');" class="myBtn"><em>导出</em></a>
		    </div>
		   	 ${bankCarStaging.page.pageStr }
		</div>
	</form>
	<form action="carStaging/excel.html" method="post" name="cityForm" id="cityForm">
	 <input type="hidden" name="carStagingCity" id="city">
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
	function search(){
			$("#carStagingForm").submit();
		} 
	
		function importData(){
	    var dg = new $.dialog({
				title:'导入', 
				width:300,
				height:170,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'carStaging/importout.html'
				});
    		dg.ShowDialog();
	}	
	function carStagingExport(carStagingCity){
		    $("#city").val(carStagingCity);
		    $("#cityForm").submit();
			/* document.location = "carStaging/excel.html?carStagingCity="+carStagingCity; */
		}	
	</script>  
</body>
</html>
