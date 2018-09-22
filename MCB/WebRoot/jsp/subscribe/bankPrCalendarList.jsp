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
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="calendar.html" method="post"
		name="prActivityInfoForm" id="prActivityInfoForm">
		<div class="search_div">
			年份：<input type="text" name="calendarYear" value="${bankPrCalendar.calendarYear}" onclick="display()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;height:20px;width:150px;padding:0px"/>
			<a href="javascript:search();" class="myBtn"> <em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th><input type="checkbox" name="sltAll" id="sltAll"
					onclick="sltAllUser()" /> 序号</th>
				<th>假期类型</th>
				<th>年份</th>
				<th>开始日期</th>
				<th>结束日期</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty list}">
					<c:forEach items="${list}" var="li" varStatus="vs">
						<tr class="main_info">
						    <th><input type="checkbox" type="hidden"
								name="calendarId" value="${li.calendarId}" />
								&nbsp;&nbsp; <c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
								<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<th>
							 <c:choose>
							     <c:when test="${li.calendarType=='YD'}">
					       			元旦
					     		 </c:when>
					     		 <c:when test="${li.calendarType=='CJ'}">
					       			春节
					     		 </c:when>
					     		 <c:when test="${li.calendarType=='QM'}">
					       			清明
					     		 </c:when>
					     		 <c:when test="${li.calendarType=='WY'}">
					       			五一
					     		 </c:when>
					     		 <c:when test="${li.calendarType=='DW'}">
					       			端午
					     		 </c:when>
					     		 <c:when test="${li.calendarType=='ZQ'}">
					       			中秋
					     		 </c:when>
					     		  <c:when test="${li.calendarType=='GQ'}">
					       			国庆
					     		 </c:when>
					     		 <c:when test="${li.calendarType=='GZR'}">
					       			工作日
					     		 </c:when>
				  	         </c:choose>
							</th>
							<th>${li.calendarYear}</th>
							<th>${li.calendarBeginDate}</th>
							<th>${li.calendarEndDate}</th>
							<th><a href="javascript:editCalendar('${li.calendarId}');">修改</a>
							</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="6">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
			<div style="float:left;  height:100px;">
				<a href="javascript:addHoliday()" class="myBtn"><em>新增</em>
				</a> <a href="javascript:void(0)" onclick="delHoliday()"
					class="myBtn"> <em>删除</em>
				 
				</a>
			</div>
			${bankPrCalendar.page.pageStr }
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
				height : 250,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'calendar/addHoliday.html'
			});
			dg.ShowDialog();
		}

		 
		function editCalendar(calendarId) {
			var dg = new $.dialog(
					{
						title : '修改假期',
						width : 500,
						height : 250,
						iconTitle : false,
						cover : true,
						maxBtn : false,
						resize : false,
						page : 'calendar/editCalendar.html?calendarId='+ calendarId
								
					});
			dg.ShowDialog();
		}

		function delHoliday() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="calendarId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="calendarId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ",";
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'calendar/delCalendar.html',
						type : 'POST',
						data : {
							calendarIds : str
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
				$("input[name='calendarId']").attr("checked", true);
			} else {
				$("input[name='calendarId']").attr("checked", false);
			}
		}
		
		function display(){
			WdatePicker({skin:'whyGreen',startDate:'%y',dateFmt:'yyyy'});
		}
	</script>
</body>
</html>