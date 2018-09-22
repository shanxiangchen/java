 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="js/main.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/shopPhoto/shopPhoto_list.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<style type="text/css">
	sa { text-decoration:none; color:#f30; }
	img { border:none; }
	ul, li { margin:0; padding:0; }
	li { list-style:none;  }
	#preview { position:absolute; bottom：30px; width:10%; height:10%; }

</style>
</head>
<body>
	<input type="hidden" id="oddsshopId" name="oddsshopId" value="${oddsshopId }"/>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</th>
			<th>图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片</th>
			<th>图片名称</th>
			<th>操&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作</th>
		</tr>
		 <c:choose>
			 <c:when test="${not empty listShopPhoto}">
				<c:forEach items="${listShopPhoto}" var="phto" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1 }</th>
				<th><a href="${path}${phto.photourl}" class="preview"><img src="${path}${phto.photourl}" width="30" height="30"/></a></th>
				<th onmouseover="this.title=this.innerText">${phto.photoname }</th>
				<th><a href="javascript:editShopphoto(${phto.photoalbumid});">修改</a> | <a href="javascript:deShopphoto(${phto.photoalbumid });">删除</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise> 
				<tr class="main_info">
					<td colspan="4">没有相关数据</td>
				</tr>
			</c:otherwise>
		
		</c:choose>
	</table>
	<div class="page_and_btn"><div></div></div>
	<div style="float:left; width:100px; height:100px;"><a href="javascript:addShopphoto();" class="myBtn"><em>新增</em></a></div>
	<script type="text/javascript" src="../js/style.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>