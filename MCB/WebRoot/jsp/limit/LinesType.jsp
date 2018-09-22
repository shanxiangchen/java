<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />

</head>
<body>
	<form action="LinesType.html" method="post" name="linesTypeForm"
		id="linesTypeForm">
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>编号</th>
				<th>额度类型</th>
				<th>次数</th>
				<th>额度权限</th>
				<th>提示信息</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty linesTypes}">
					<c:forEach items="${ linesTypes}" var="li" varStatus="vs">
						<tr class="main_info">
							<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
							<th onMouseOver="this.title=this.innerText"><c:if
									test="${li.linesType=='A'}">临额</c:if> <c:if
									test="${li.linesType=='B'}">固额</c:if></th>
							<th onMouseOver="this.title=this.innerText">${li.linesNumber}</th>
							<th onMouseOver="this.title=this.innerText">${li.linesMaxValue}</th>
							<th onMouseOver="this.title=this.innerText">${li.promptInformation}</th>
							<th><a href="javascript:editlinesType(${li.linesTypeId});">编辑</a>
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
			${type.page.pageStr}
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="../js/linesType/update__LinesType.js"></script>
	<script type="text/javascript"
		src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function search() {
			$("#linesTypeForm").submit();
		}

		function editlinesType(linesTypeId) {
			var dg = new $.dialog({
				title : '编辑额度',
				id : 'linest_edit',
				width : 330,
				height : 300,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'LinesType/update.html?linesTypeId=' + linesTypeId
			});
			dg.ShowDialog();
		}

		function LinesTypeDel(linesTypeId) {
			if (confirm("确定要删除该记录？")) {
				$.ajax({
					url : 'LinesType/delete.html',
					type : 'POST',
					data : {
						linesTypeId : linesTypeId
					},
					dataType : 'text',
					async : false,
					success : function(text) {
						if (text == "success") {
							alert("删除成功！");
							document.location.reload();
						} else {
							alert("删除失败！");
							document.location.reload();
						}
					}
				});
			}
		}
	</script>
</body>
</html>