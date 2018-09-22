
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
<title></title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<form action="messageParameterConf.html" method="post"name="BankMessageTemplateForm" id="BankMessageTemplateForm">
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>序号</th>
				<th>消息盒子名称</th>
				<th>消息盒子图片</th>
				<th>消息盒子编码</th>
				<th>消息盒子类型</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty messageParameterConfList}">
					<c:forEach items="${messageParameterConfList}" var="li" varStatus="vs">
						<tr class="main_info">
							<th onmouseover="this.title=this.innerText">${vs.index+1 }</th>
							<th onmouseover="this.title=this.innerText">${li.bmpcName}</th>
							<th><a href="${path}${li.bmpcUrl}" class="preview">
									<img src="${path}${li.bmpcUrl}" width="30" height="30" />
							</a></th>
							<th onmouseover="this.title=this.innerText">${li.bmpcCode}</th>
							<th onmouseover="this.title=this.innerText">${li.bmpcTypeName}</th>
							<th><a href="javascript:edit(${li.bmpcId})">修改</a>
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
		<div style="float:left; width:100px; height:100px;">
			<a href="javascript:addAllMessageParameterConf();" class="myBtn"><em>新增</em></a>
		</div>
			${messageParameter.page.pageStr}
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$(".main_info:even").addClass("main_table_even");
	});
	
	function addAllMessageParameterConf() {
		var dg = new $.dialog({
			title : '新增消息盒子',
			id : 'MessageTemplate_new',
			width : 380,
			height : 400,
			iconTitle : false,
			cover : true,
			maxBtn : false,
			xButton : true,
			resize : false,
			page : 'messageParameterConf/addAllMessageParameterConf.html'
		});
		dg.ShowDialog();
	}
	function edit(bmpcId) {
		var dg = new $.dialog(
				{
					title : '编辑消息盒子',
					id : 'MessageTemplate_new',
					width : 380,
					height : 400,
					iconTitle : false,
					cover : true,
					maxBtn : false,
					xButton : true,
					resize : false,
					page : 'messageParameterConf/updateMessageParameterConfpage.html?bmpcId='+ bmpcId
				});
		dg.ShowDialog();
	}
	
	function sltAllUser() {
			if ($("#sltAll").attr("checked")) {
				$("input[name='codeTypeId']").attr("checked", true);
			} else {
				$("input[name='codeTypeId']").attr("checked", false);
			}
		}
</script>
</html>