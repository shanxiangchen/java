<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片管理</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="js/main.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/shops/shops.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<style type="text/css">
	sa { text-decoration:none; color:#f30; }
	img { border:none; }
	ul, li { margin:0; padding:0; }
	li { list-style:none;  }
	#preview { position:absolute; bottom：30px; width:10%; height:10%; }
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath }/shop.html" method="post" name="shopForm" id="shopForm" >
	<div class="search_div">
		商户名称：<input type="text" name="oddsshopname" id="oddsshopname" value="${shop.oddsshopname}"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	
	<table  width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1"  class="main_table">
		<tr class="main_head">
			<th>商户编号</th>
			<th>商户名称</th>
	        <th>商户地址</th>
  			<!-- <th>经        度</th>
  			<th>纬	度</th> -->
			<th>联系方式</th>
			<th>优惠形式</th>
			<th>城        市</th>
			<!-- <th>展示顺序</th> -->
			<th>商	圈 </th>
			<th>状	态</th>
			<th>商户图片</th>
			<!-- <th>点击数量</th> -->
			<th>活动类型</th>
			<th>人均消费</th> 
			<th>分店名称</th>
			<th>活动介绍</th>
			<th>开始日期</th>
			<th>截止日期</th>
			<th>发布日期</th>
			<!-- <th>优惠内容</th> -->
			<th>其他图片</th>
	 		<th>操	作 </th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="shop">
				<tr   class="main_info">
					<th onmouseover="this.title=this.innerText">${shop.oddsshopid }</th>
					<th onmouseover="this.title=this.innerText">${shop.oddsshopname }</th>
					<th onmouseover="this.title=this.innerText">${shop.oddsshopaddress }</th>
					<%-- <td onmouseover="this.title=this.innerText">${shop.oddsshoplongitude}</td>
					<td onmouseover="this.title=this.innerText">${shop.oddsshoplatitude}</td> --%>
					<th onmouseover="this.title=this.innerText">${shop.oddsshopphone }</th>
					<th onmouseover="this.title=this.innerText">${shop.oddsshopprivilege }</th> 
					<th onmouseover="this.title=this.innerText">${shop.gprs.cityname}</th>
				<%-- 	<td onmouseover="this.title=this.innerText">${shop.oddsshoporder }</td> --%>
					<th onmouseover="this.title=this.innerText">${shop.gprs.shopringname }</th>
					<th onmouseover="this.title=this.innerText">
						<c:if test="${shop.oddsshopstatus==1}">营业</c:if>
						<c:if test="${shop.oddsshopstatus==2}">停止营业</c:if>
					</th>
					<th><a href="${path}${shop.oddsshoppictureurl }" class="preview" >
						<img src="${path}${shop.oddsshoppictureurl }" width="30" height="30" /></a></th>
 			<%-- 		<td onmouseover="this.title=this.innerText">${shop.shoppraise.clickradixcount}</td> --%>
 					<th onmouseover="this.title=this.innerText">${shop.type.oddsshoptype }</th> 
					<th onmouseover="this.title=this.innerText">${shop.oddsshopconsumption }</th> 
					<th onmouseover="this.title=this.innerText">${shop.oddsshopsubbranchname }</th> 
					<th onmouseover="this.title=this.innerText">${shop.oddsshopfavorableinfo }</th> 
					<th onmouseover="this.title=this.innerText">${shop.oddsshopbegintime }</th>  
					<th onmouseover="this.title=this.innerText">${shop.oddsshopendtime }</th>  
					<th onmouseover="this.title=this.innerText">${shop.oddsshoppublictime }</th>  
					<%-- <td onmouseover="this.title=this.innerText">${shop.oddsshopbriefintroduction} </td> --%>
					<th><a href="javascript:listShopphoto('${shop.oddsshopid}')" ><img src="images/tupian.png" width="30" height="30" /></a></th>   
					<th><a href="###" onclick="editshop('${shop.oddsshopid}')">修改</a>|
						<a href="javascript:delshop('${shop.oddsshopid }');">删除</a> </th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
				<td colspan="16">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
   <div class="page_and_btn"> 
	 <div>
	   <a href="javascript:addshop();" class="myBtn"><em>新增</em></a>
	   <a href="javascript:exportshop();" class="myBtn"><em>导出</em></a>
	 </div>
	 ${shop.page.pageStr}		 
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	</body>
</html>