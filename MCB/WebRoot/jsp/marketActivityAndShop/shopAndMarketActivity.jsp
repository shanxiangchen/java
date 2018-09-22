<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>营销活动与商户维护</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>

</head>
<body>
 <form action="shopAndMarketActivity.html" method="post" name="actAndShopForm" id="actAndShopForm"> 
	<div class="search_div">
		商户名称：<input type="text" name="shopName"  id="shopName" value="${market.shopName}"/>
		活动标题：<input type="text" name="activityTitle"  id="activityTitle" value="${market.activityTitle}"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table  width="100%" id="tb_1"  border="0" cellspacing="1" cellpadding="1" class="main_table"   >
		<tr class="main_head">
		    <th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
				序号
			</th>
			<th >商户id</th>
			<th>商户名称</th>
			<!-- <th>商户地址</th> -->
			<th>手机号</th>
			<!-- <th>商户经纬度</th> -->
			<th width="5%">是否置顶</th>
			<th width="5%">开放</th>
			<!-- <th>商户图片</th> -->
			<th>活动id</th>
			<th>活动标题</th>
			<th>开始日期</th>
			<th>结束日期</th>
			<!-- <th>活动分类</th> -->
			<!-- <th>活动图片</th> -->
			<th width="5%">状态</th>
			<!-- <th>营销活动区域性质</th> -->
<!-- 			<th width="5%">操作</th> -->
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="v">
					<c:forEach items="${list.marActivityShop}" var="li" varStatus="vs">
					  <tr class="main_info">
					    <th><input type="checkbox" name="shopId" id="shopId" value="${li.shopId}"/>
						&nbsp;&nbsp;
					    <c:if test="${list.no<=9}">0${list.no}</c:if>
						<c:if test="${list.no>9}">${list.no}</c:if> 
						</th>
						<th>${li.shopId}<input type="hidden" id="" value="${li.shopId}"/></th>
						<th>${li.shopName}</th>
						<%-- <td>${li.shopAddress}</td> --%>
						<th>${li.shopPhone}</th>
						<%-- <td>${li.shopLongitude},${li.shopLatitude}</td> --%>
						<th>
						    <c:if test="${li.isDraft=='1'}">是</c:if>
						    <c:if test="${li.isDraft=='2'}">否</c:if>
						</th>
						<th>
							<c:if test="${li.is0pen=='0'}">未开放</c:if>
						    <c:if test="${li.is0pen=='1'}">开放</c:if>
						</th>
						<th>${list.activityId}</th>
						<th>${list.activityTitle}</th>
						<th>${list.activityStartDate}</th>
						<th>${list.activityEndDate}</th>
						<%-- <td>${list.activityType.activityType}</td> --%>
 						<th>
 							<c:choose>
								<c:when test="${list.activityStatus=='0'}">
							  	  未发布
							  	</c:when>
							  	<c:when test="${list.activityStatus=='1'}">
							  	  已开始
							  	</c:when>
							  	<c:when test="${list.activityStatus=='2'}">
							  	  已结束
							  	</c:when>
							  	<c:when test="${list.activityStatus=='3'}">
							  	  已过期
							  	</c:when>
						  </c:choose>
 						</th>
						<%-- <td>
						  <c:choose>
						  	<c:when test="${list.activityAreaQuale=='1'}">
						  	  全国性活动 
						  	</c:when>
						  	<c:when test="${list.activityAreaQuale=='2'}">
						  	  区域性活动  
						  	</c:when>
						  </c:choose>
						</td> --%>
					<%-- 	<td><a href="javascript:void(0)" onclick="editActAndShop('${li.shopId}','${list.activityId}','${li.shopName}','${list.activityTitle}')">修改</a></td> --%>
					</tr>
				</c:forEach>
				 </c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="11">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"> 
	 <div>
		<a href="javascript:addActAndShop();" class="myBtn"><em>新增</em></a>
	    <a href="javascript:void(0)" onclick="delActAndShop()" class="myBtn"><em>解除关系</em></a> 
	 </div>
	 ${market.page.pageStr}
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
			$("#actAndShopForm").submit();
		}
		function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='shopId']").attr("checked",true);
			}else{
				$("input[name='shopId']").attr("checked",false);
			}
		}
		
		function addActAndShop(){
			$('#marActivityShop').val("");
			var dg = new $.dialog({
				title:'新增',
				id:'user_new',
				width:730,
				height:470,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'shopAndMarketActivity/get.html'
				});
    		dg.ShowDialog();
		}
		
		function editActAndShop(shopId,activityId,shopName,activityTitle){
			var dg = new $.dialog({
				title:'修改',
				id:'user_edit',
				width:800,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'shopAndMarketActivity/editActivityAndShop.html?shopId='+shopId+'&activityId='+activityId+'&shopName='+shopName+'&activityTitle='+activityTitle
				});
    		dg.ShowDialog();
		}
		
		function delActAndShop(){
				var str="";
				//验证是否选择了记录
			  	if($('#tb_1 input[name="shopId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行解除！');
			  		return false;
			  	}else{
			  		$('#tb_1 input[name="shopId"]:checkbox:checked').each(function(){
					   //放入数组里

					   str+=$(this).val()+",";  
					});
				   if(confirm("确定要解除该记录关系？")){
					    $.ajax({
								url:'shopAndMarketActivity/deleteActandShop.html', 
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
									if(text>0){
									   alert("解除成功");
									   document.location.reload();
						   			}else{
						   				alert("解除失败");
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