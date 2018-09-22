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

	<form action="stagesWhiteList.html" method="post" name="stagesWhiteformList" id="stagesWhiteformList">
		<div class="search_div">
		手机号：<input type="text" name="listPhone" id="listPhone" value="${bankStagesWhiteList.listPhone}"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	    </div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th width="20%"><input type="checkbox" name="sltAll" id="sltAll"
					onclick="sltAllUser()" />&nbsp;&nbsp;序号</th>
				<th>手机号</th>
				 
			</tr>
			<c:choose>
				<c:when test="${not empty list}">
					<c:forEach items="${list}" var="li" varStatus="vs">
						<tr class="main_info">
							<th><input type="checkbox" type="hidden" name="whiteList"
								value="${li.listId}" /> &nbsp;&nbsp; <c:if
									test="${vs.index+1<=9}">0${vs.index+1}</c:if> <c:if
									test="${vs.index+1>9}">${vs.index+1}</c:if></th>
							<th onMouseOver="this.title=this.innerText">${li.listPhone}</th>
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
			<div>
			    <a href="javascript:addStagesWhite()" class="myBtn"><em>新增</em></a>
				<a href="javascript:importData();" class="myBtn"><em>导入</em>
				</a> <a href="javascript:void(0)" onclick="delStag()" class="myBtn">
					<em>删除</em>
				</a>
			</div>
			${bankStagesWhiteList.page.pageStr }
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function search() {
			$("#stagesWhiteformList").submit();
		}
		
		function addStagesWhite() {
			var dg = new $.dialog({
				title : '添加白名单',
				width : 400,
				height : 270,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'stagesWhiteList/addStagesWhite.html'
			});
			dg.ShowDialog();
		}

		function importData() {
			var dg = new $.dialog({
				title : '导入自动分期名单',
				width : 300,
				height : 170,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'stagesWhiteList/importWhite.html'
			});
			dg.ShowDialog();
		}
 
		function delStag() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="whiteList"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="whiteList"]:checkbox:checked').each(
						function() {
							str += $(this).val() + ",";
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'stagesWhiteList/delStag.html',
						type : 'POST',
						data : {
							speedyLists : str
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
				$("input[name='whiteList']").attr("checked", true);
			} else {
				$("input[name='whiteList']").attr("checked", false);
			}
		}
	</script>
</body>
</html>
