<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>类型</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>
	<form action="type.html" method="post" name="typeForm" id="typeForm">
	<div class="search_div">
		名称:<input type="text" name="oddsshoptype" value="${type.oddsshoptype}"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>编号</th>
			<th>名称</th>
	 		<th>操作 </th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="type">
				<tr   class="main_info">
				<th>${type.oddsshoptypeid }</th>
				<th>${type.oddsshoptype }</th>
				<th><a href="###" onclick="edittype('${type.oddsshoptypeid}')">修改</a> 
				| <a href="javascript:deltype('${type.oddsshoptypeid}');">删除</a></th>
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
	 <div><a href="javascript:addtype();" class="myBtn"><em>新增</em></a></div>
	 ${type.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
	
		function addtype(){
			var dg = new $.dialog({
				title:'新增',
				id:'type_new',
				width:400,
				height:200,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'type/add.html'
				});
    		dg.ShowDialog();
		}
		
		
		function edittype(oddsshoptypeid){
			var dg = new $.dialog({
				title:'修改菜单',
				id:'type_edit',
				width:400,
				height:200,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'type/edit.html?oddsshoptypeid='+oddsshoptypeid
				});
    		dg.ShowDialog();
		} 
		
		
			function deltype(oddsshoptypeid){
			if(confirm("确定要删除该记录？")){
				var url = "type/delete.html?oddsshoptypeid="+oddsshoptypeid;
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
		function search(){
			$("#typeForm").submit();
		}
		
	</script>	
</body>
</html>