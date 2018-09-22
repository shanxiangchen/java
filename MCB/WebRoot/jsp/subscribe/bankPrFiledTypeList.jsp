<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="filedType.html" method="post" name="filedTypeForm"
		id="filedTypeForm">
		<div class="search_div">
			权益类型：<select name="typeId" id="typeId" style="width:205px;">
				<option value="">请选择</option>
				<c:forEach items="${typeList}" var="li">
					<option value="${li.VALUE}"
						<c:if test="${li.VALUE==bankPrFiledType.typeId}">selected</c:if>>${li.CONTENT}</option>
				</c:forEach>
			</select> 
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th width="10%"><input type="checkbox" name="sltAll"
					id="sltAll" onclick="sltAllUser()" /> 序号</th>
				<th>权益编号</th>
				<th>权益类型</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty bankPrFiledTypes }">
					<c:forEach items="${bankPrFiledTypes}" var="list" varStatus="vs">
						<tr class="main_info">
							<th><input type="checkbox" type="hidden" name="filedTypeId"
								value="${list.typeId}" /> &nbsp;&nbsp; <c:if
									test="${vs.index+1<=9}">0${vs.index+1}</c:if> <c:if
									test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<th>${list.typeId}</th>
							<th>${list.typeName}</th>
							<td><a href="javascript:updateFiledType('${list.typeId}');">修改</a>|
								<a href="javascript:filedTypeInfo('${list.typeId}');">详情</a></td>
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
		<div class="page_and_btn">
			<div style="float:left;  height:100px;">
				<a href="javascript:addFiledType()" class="myBtn"><em>新增</em>
				</a> <a href="javascript:void(0)" onclick="delFiledType()" class="myBtn">
					<em>删除</em>
				</a>
			</div>
			${bankPrFiledType.page.pageStr}
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function search() {
			$("#filedTypeForm").submit();
		}

		function addFiledType() {

			var dg = new $.dialog({
				title : '新增权益预约',
				width : 850,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'filedType/addFiledType.html'
			});
			dg.ShowDialog();
		}

		function updateFiledType(typeId) {
			var dg = new $.dialog({
				title : '权益预约修改',
				width : 850,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'filedType/updateFiledType.html?typeId=' + typeId
			});
			dg.ShowDialog();
		}
		function filedTypeInfo(typeId) {
			var dg = new $.dialog({
				title : '权益预约详情',
				cancelBtnTxt:'关闭',
				width : 700,
				height : 350,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'filedType/filedTypeInfo.html?typeId=' + typeId
			});
			dg.ShowDialog();
		}

		function delFiledType() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="filedTypeId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="filedTypeId"]:checkbox:checked').each(
						function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'filedType/delFiledType.html',
						type : 'POST',
						data : {
							filedTypeIds : str
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
				$("input[name='filedTypeId']").attr("checked", true);
			} else {
				$("input[name='filedTypeId']").attr("checked", false);
			}
		}
	</script>
</body>
</html>