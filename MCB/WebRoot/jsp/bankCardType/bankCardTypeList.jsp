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
	<form action="${pageContext.request.contextPath }/cardType.html"
		method="post" name="cardTypeForm" id="cardTypeForm">
		<div class="search_div">
		 卡产品种类名称：<input type="text" name="typeName" value="${bankCardType.typeName}" />
					<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" border="0" cellpadding="1" cellspacing="1"
			class="main_table" id="tableId">
			<tr class="main_head">
				<th><input type="checkbox" name="sltAll" id="sltAll"
					onclick="sltAllUser()" /> 序号</th>
				<!-- <th>卡产品种类编号</th> -->
				<th>卡产品种类名称</th>
				<th>卡产品种类图片</th>
				<th>卡产品种类所属类型</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty bankCardTypes}">
					<c:forEach items="${bankCardTypes}" var="cf" varStatus="vs">
						<tr class="main_info">
							<th><input type="checkbox" type="hidden" name="typeId"
								value="${cf.typeId}" /> &nbsp;&nbsp; <c:if
									test="${vs.index+1<=9}">0${vs.index+1}</c:if> <c:if
									test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<%-- <th>${cf.typeNum}</th> --%>
							<th>${cf.typeName}</th>
							<th><a href="${path}${cf.typePicUrl}"> <img
									src="${path}${cf.typePicUrl}" width="30" height="30" />
							</a>
							</th>
							<td>${cf.cardOfName}</td>

							<th><a href="javascript:editCardType('${cf.typeId}');">修改</a>
								| <a href="javascript:void(0)"
								onclick="infoCardType('${cf.typeId}')">详情</a>
							</th>
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
		<div style="float:left;  height:100px;">
			<a href="javascript:addCardType()" class="myBtn"><em>新增</em></a>
			<a href="javascript:void(0)" onclick="delCardType()" class="myBtn"><em>删除</em></a>
		</div>
		${bankCardType.page.pageStr}
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function addCardType() {
			var dg = new $.dialog({
				title : '添加卡种类',
				width : 800,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'cardType/addCardType.html'
			});
			dg.ShowDialog();
		}
		function editCardType(typeId) {
			var dg = new $.dialog({
				title : '编辑卡种类',
				width : 800,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'cardType/cardTypeListById.html?typeId=' + typeId
			});
			dg.ShowDialog();
		}

		function infoCardType(typeId) {
			var dg = new $.dialog({
				title : '卡种类详情',
				cancelBtnTxt : '关闭',
				width : 800,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'cardType/infoCardTypeById.html?typeId=' + typeId
			});
			dg.ShowDialog();
		}

		function delCardType() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="typeId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="typeId"]:checkbox:checked').each(
						function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'cardType/deleteCardType.html',
						type : 'POST',
						data : {
							typeIds : str
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
				$("input[name='typeId']").attr("checked", true);
			} else {
				$("input[name='typeId']").attr("checked", false);
			}
		}

		function search() {
			$("#cardTypeForm").submit();
		}
	</script>
</body>
</html>
