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
	<form action="prActivityInfo.html" method="post"
		name="prActivityInfoForm" id="prActivityInfoForm">
		<div class="search_div">
			服务名称:<input type="text" name="activityName" value="${bankPrActivityInfo.activityName}"
				id="activityName" /> <a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th><input type="checkbox" name="sltAll" id="sltAll"
					onclick="sltAllUser()" /> 序号</th>
				<th>权益类型</th>
				<th>服务编码</th>
				<th>服务名称</th>
				<th>是否本人</th>
				<th>是否随行</th>
				<th>是否可预约</th>
				<th>按钮名称</th>
				<th>是否展示</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty bankPrActivityInfos}">
					<c:forEach items="${bankPrActivityInfos}" var="list" varStatus="vs">
						<tr class="main_info">
							<th><input type="checkbox" type="hidden"
								name="activityInfoId" value="${list.activityInfoId}" />
								&nbsp;&nbsp; <c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
								<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<th onmouseover="this.title=this.innerText">${list.rightsTypeCode}</th>
							<th>${list.activityCode}</th>
							<th onmouseover="this.title=this.innerText">${list.activityName}</th>
							<th>
							 <c:if test="${list.activitySelfUsed=='1'}">是</c:if>
							 <c:if test="${list.activitySelfUsed=='0'}">否</c:if></th>
							<th>
							   <c:if test="${list.activityHeelUsed=='1'}">是</c:if>
							   <c:if test="${list.activityHeelUsed=='0'}">否</c:if>
							</th>
							<th>
							   <c:if test="${list.activityOrder=='1'}">是</c:if> 
							   <c:if test="${list.activityOrder=='0'}">否</c:if></th>
							<th>${list.activityOrderName}</th>
							<th>
							   <c:if test="${list.activitySow=='1'}">是</c:if> 
							   <c:if test="${list.activitySow=='0'}">否</c:if>
							</th>
							<th onmouseover="this.title=this.innerText">${list.activityContent}</th>
							<th><a href="javascript:editPrActivityInfo('${list.activityInfoId}');">修改</a>
							</th>
						</tr>
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
			<div style="float:left;  height:100px;">
				<a href="javascript:addPrActivityInfo()" class="myBtn"><em>新增</em>
				</a> <a href="javascript:void(0)" onclick="delPrActivityInfo()"
					class="myBtn"> <em>删除</em>
			<!-- 	</a> <a href="javascript:addHoliday()" class="myBtn"><em>假期</em> -->
				</a>
			</div>
			${bankPrActivityInfo.page.pageStr }
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function search() {
			$("#prActivityInfoForm").submit();
		}

		function addHoliday() {

			var dg = new $.dialog({
				title : '假期设置',
				width : 500,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'prActivityInfo/addHoliday.html'
			});
			dg.ShowDialog();
		}

		function addPrActivityInfo() {

			var dg = new $.dialog({
				title : '添加权益活动',
				width : 750,
				height : 430,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'prActivityInfo/addPrActivityInfo.html'
			});
			dg.ShowDialog();
		}
		function editPrActivityInfo(activityInfoId) {
			var dg = new $.dialog(
					{
						title : '修改权益活动',
						width : 750,
						height : 430,
						iconTitle : false,
						cover : true,
						maxBtn : false,
						resize : false,
						page : 'prActivityInfo/prActivityInfoListById.html?activityInfoId='
								+ activityInfoId
					});
			dg.ShowDialog();
		}

		function delPrActivityInfo() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="activityInfoId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="activityInfoId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'prActivityInfo/deletePrActivityInfo.html',
						type : 'POST',
						data : {
							activityInfoIds : str
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
				$("input[name='activityInfoId']").attr("checked", true);
			} else {
				$("input[name='activityInfoId']").attr("checked", false);
			}
		}
	</script>
</body>
</html>