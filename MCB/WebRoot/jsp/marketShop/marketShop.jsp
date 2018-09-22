<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>营销商户维护</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>

</head>
<body>
	<form action="marketShop.html" method="post" name="shopForm" id="shopForm">
		<div class="search_div">
			商户名称:<input type="text" name="shopName" value="${shop.shopName}" id="shopName"/>
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
	<table width="100%"  border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
			序号</th>
			<th width=15%>商户ID</th>
			<th width=15%>商户名称</th>
			<th width=25%>商户地址</th>
			<th width=10%>手机号</th>
			<th width=10%>商户经纬度</th>
			<th width=5%>是否置顶</th>
			<th width=5%>开放</th>
			<th width=5%>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				    <th><input type="checkbox" name="shopId" id="shopId" value="${list.shopId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th>
					<th>${list.shopId}<input type="hidden" id="shopId" value="${list.shopId}"/></th>
					<th>${list.shopName}</th>
					<th>${list.shopAddress}</th>
					<th>${list.shopPhone}</th>
					<th>${list.shopLongitude},${list.shopLatitude}</th>
					<th>
					   <c:choose>
					   	  <c:when test="${list.isDraft=='1'}">是</c:when>
					   	  <c:when test="${list.isDraft=='2'}">否</c:when>
					   </c:choose>
					</th>
					<th>
					   <c:choose>
					   	  <c:when test="${list.is0pen=='0'}">未开放</c:when>
					   	  <c:when test="${list.is0pen=='1'}">开放</c:when>
					   </c:choose>
					</th>
					<th><a href="javascript:editShop('${list.shopId}')">修改</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="9">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"> 
     <div>
  	    <a href="javascript:addShop();" class="myBtn"><em>新增</em></a>
        <a href="javascript:void(0)"  onclick="deleteActShop()" class="myBtn"><em>删除</em></a>
     </div>
     ${shop.page.pageStr}
    </div> 
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function search(){
			$("#shopForm").submit();
		}
		
		
		function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='shopId']").attr("checked",true);
			}else{
				$("input[name='shopId']").attr("checked",false);
			}
		}
		
		function addShop(){
			$('#shopName').val("");
			var dg = new $.dialog({
				title:'新增',
				id:'user_new',
				width:530,
				height:420,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'marketShop/addShop.html'
				});
    		dg.ShowDialog();
		}
		
		function editShop(shopId){
			var dg = new $.dialog({
				title:'修改',
				id:'user_edit',
				width:550,
				height:490,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'marketShop/editShop.html?shopId='+shopId
				});
    		dg.ShowDialog();
		}
		
		function deleteActShop(){
				 var str="";
				//验证是否选择了记录
			  	if($('#tableId input[name="shopId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行删除！');
			  		return false;
			  	}else{
			  		$('#tableId input[name="shopId"]:checkbox:checked').each(function(){
					   str+=$(this).val()+",";  
					});
				   if(confirm("确定要删除该记录？")){
					  // var url = "marketShop/deleteActShop.html?str="+str;
					  /*  $.get(url,function(data){
					    	alert(data);
							if(data==""){
							    alert(data+"if");
							   document.location.reload();
				   			}else{
				   			  alert("商户"+data+"与某些活动存在关联，请先解除两者之间的关系再进行删除");
				   			  return false;
				   			}
					   }); */
					   $.ajax({
								url:'marketShop/deleteActShop.html', 
								type: 'POST', 
								data: {str:str},
								dataType: 'text',
								timeout: 1000, 
								async: false,
								error: function(text){ 
									alert('读取数据失败，请联系管理员！'); 
									return false;
								}, 
								success: function(text){
									if(text==""){
											alert("删除成功");
									    	document.location.reload();
						   			}else{
						   			  alert("商户"+text+"与某些活动存在关联，请在商户和活动关系维护功能模块解除关系后才能删除");
						   			  return false;
						   			}
							   }        
					    });
			        }
				}
		}
		
		function editRights(userId){
			var dg = new $.dialog({
				title:'用户授权',
				id:'auth',
				width:280,
				height:370,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'user/auth.html?userId='+userId
				});
    		dg.ShowDialog();
		}
		
	</script>
</body>
</html>