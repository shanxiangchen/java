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
	<form action="${pageContext.request.contextPath }/cardAddInfo.html"
		method="post" name="cardInfoForm" id="cardInfoForm">
		<div class="search_div">
		卡产品名称：<input type="text" name="cardName" value="${cardAddInfo.cardName}"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	    </div>
		<table width="100%" border="0" cellpadding="1" cellspacing="1"
			class="main_table" id="tableId">
			<tr class="main_head">
				<th><input type="checkbox" name="sltAll" id="sltAll"
					onclick="sltAllUser()" /> 序号</th>
				<th>产品编码</th>
				<th>产品名称</th>
				<th>版面编号</th>
				<th>产品种类</th>
				<th>推荐优先级</th>
				<th>产品图片</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty cardAddInfos}">
					<c:forEach items="${cardAddInfos}" var="cf" varStatus="vs">
						<tr class="main_info">
							<th><input type="checkbox" type="hidden" name="cardId"
								value="${cf.cardId}" /> &nbsp;&nbsp; <c:if
									test="${vs.index+1<=9}">0${vs.index+1}</c:if> <c:if
									test="${vs.index+1>9}">${vs.index+1}</c:if></th>
							<th>${cf.cardNum}</th>
							<th>${cf.cardName}</th>
							<th>${cf.cardBmNo}</th>
							<th>${cf.typeName}</th>
							<th>${cf.cardForOrder}</th>
							<th><a href="${path}${cf.cardPicUrl}"> <img
									src="${path}${cf.cardPicUrl}" width="30" height="30" /> </a></th>
							<th><a href="javascript:editCardAddInfo('${cf.cardId}');">修改</a>
								| <a href="javascript:void(0)"
								onclick="infoCardAddInfo('${cf.cardId}')">详情</a></th>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="8">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
			<div style="float:left;  height:100px;">
				<a href="javascript:addCardInfo()" class="myBtn"><em>新增</em></a>
				<a href="javascript:void(0)" onclick="delAddCard()" class="myBtn"><em>删除</em></a>
			</div>
			${cardAddInfo.page.pageStr}
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function addCardInfo() {
			var dg = new $.dialog({
				title : '添加卡产品',
				id : 'CardInfo_new',
				width : 800,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'cardAddInfo/addCardInfo.html'
			});
			dg.ShowDialog();
		}
		function editCardAddInfo(cardId) {
			var dg = new $.dialog({
				title : '编辑卡产品',
				id : 'CardAddInfo_edit',
				width : 830,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'cardAddInfo/listCardIAddInfoById.html?cardId=' + cardId
			});
			dg.ShowDialog();
		}

		function infoCardAddInfo(cardId) {
			var dg = new $.dialog({
				title : '卡产品详情',
				cancelBtnTxt : '关闭',
				width : 800,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'cardAddInfo/infoCardAddInfoById.html?cardId=' + cardId
			});
			dg.ShowDialog();
		}

		function delAddCard() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="cardId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="cardId"]:checkbox:checked').each(
						function() {
							str += $(this).val() + ",";
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'cardAddInfo/deleteCardAdd.html',
						type : 'POST',
						data : {
							cardIds : str
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
		}

		function sltAllUser() {
			if ($("#sltAll").attr("checked")) {
				$("input[name='cardId']").attr("checked", true);
			} else {
				$("input[name='cardId']").attr("checked", false);
			}
		}

		function search() {
			$("#cardInfoForm").submit();
		}
	</script>
</body>
</html>
