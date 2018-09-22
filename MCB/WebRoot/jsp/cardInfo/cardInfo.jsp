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
<script type="text/javascript"
	src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath }/cardInfo.html"
		method="post" name="cardInfoForm" id="cardInfoForm">
		<div class="search_div" >
			 卡产品名称:<input type="text" name="cardNmae"
						value="${cardInfo.cardNmae}" />
					 <a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" border="0" cellpadding="1" cellspacing="1"
			class="main_table" id="tableId">
			<tr class="main_head">
				<th>序号</th>
				<th>产品编码</th>
				<th>产品名称</th>
				<th>产品介绍</th>
				<th>产品类型</th>
				<th>产品图片</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty cardInfos}">
					<c:forEach items="${cardInfos}" var="cf" varStatus="vs">
						<tr class="main_info">
							<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
							<th onmouseover="this.title=this.innerText">${cf.cardNum}</th>
							<th onmouseover="this.title=this.innerText">${cf.cardNmae}</th>
							<th onmouseover="this.title=this.innerText">${cf.cardinfo}</th>
							<th onmouseover="this.title=this.innerText">${cf.cardType}</th>
							<th onmouseover="this.title=this.innerText"><a
								href="${path}${cf.cardPicUrl}"> <img
									src="${path}${cf.cardPicUrl}" width="30" height="30" />
							</a>
							</th>
							<th><a href="javascript:editCardInfo('${cf.cardId}');">修改</a>|<a
								href="javascript:delCard('${cf.cardId}');">删除</a>
							</th>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="7">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
			<div>
				<a href="javascript:addCardInfo()" class="myBtn"><em>新增</em>
				</a>
			</div>
				${cardInfo.page.pageStr}
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});
		function search() {
			$("#cardInfoForm").submit();
		}

		function addCardInfo() {
			var dg = new $.dialog({
				title : '添加卡产品',
				id : 'CardInfo_new',
				width : 700,
				height : 350,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'cardInfo/addCardInfo.html'
			});
			dg.ShowDialog();
		}
		function editCardInfo(cardId) {
			var dg = new $.dialog({
				title : '编辑卡产品',
				id : 'CardInfo_edit',
				width : 700,
				height : 350,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'cardInfo/listCardIfnoByid.html?cardId=' + cardId
			});
			dg.ShowDialog();
		}

		function delCard(cardId) {
			if (confirm("确定要删除该记录？")) {
				$.ajax({
					url : 'cardInfo/delCardInfo.html',
					type : 'POST',
					data : {
						cardId : cardId
					},
					dataType : 'text',
					timeout : 1000,
					async : false,
					success : function(text) {
						if (text == "success") {
							alert("删除成功");
							document.location.reload();
						} else {
							alert("删除失败！");
							document.location.reload();
						}
					},
					error : function() {
						alert("删除失败！");
						document.location.reload();
					}
				});
			}
		}
	</script>
</body>
</html>
