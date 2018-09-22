<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="shop.html" method="post" name="shopForm" id="shopForm">
	<table>
	<tr>
		<th>商户名称:</th>
		<td><input type="text" name="shopName" id="shopName" value="${marketActivityShop.shopName}"/></td>
		<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
	</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" id="tableId" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>选择</th>
			<th>商户名称</th>
		</tr>
  	<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				<th><input type="checkbox" name="shopId" id="shopId"value="${list.shopId}"/></th>
				<th onmouseover="this.title=this.innerText">${list.shopName}</th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="2">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"> 
	  ${marketActivityShop.page.pageStr}
	</div>
	</form>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
			$("#shopForm").submit();
		}	
		$(document).ready(function(){
			dgs = frameElement.lhgDG;
			dgs.addBtn('ok','确认',function(){ 
				addClick();
				dgs.cancel();
			});
		});
		function addClick(){
			var str="";
		    	//var chk_value =[]; 
				//验证是否选择了记录
			  	if($('#tableId input[name="shopId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行解除！');
			  		return false;
			  	}else{
			  		$('#tableId input[name="shopId"]:checkbox:checked').each(function(){
					   //放入数组里
					   //chk_value.push($(this).val());
					   str+=$(this).val()+",";  
					   $("#shopId",window.parent.document).val(str);
					  $("#shopIds",window.parent.document).val(str);
					});
					
					}
/* 		var name=$('input[name="shopId"]:checked').val();
		$("#shopId",window.parent.document).val(name);
		$("#shopIds",window.parent.document).val(name); */
		}
		
		function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='shopId']").attr("checked",true);
			}else{
				$("input[name='shopId']").attr("checked",false);
			}
		}
		
	</script>
</body>
</html>