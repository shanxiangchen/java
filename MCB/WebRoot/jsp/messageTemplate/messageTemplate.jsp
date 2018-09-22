
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
	<form action="bankMessageTemplate.html" method="post" name="BankMessageTemplateForm" id="BankMessageTemplateForm">
		<div class="search_div" >
			 消息盒子:<select name="typeNo" id="typeNo">
							<option value="">--请选择--</option>
							<c:forEach items="${list}" var="li">
								<option value="${li.bmpcCode}"
									<c:if test="${li.bmpcCode==bankMessageTemplate.typeNo}">selected</c:if>>${li.bmpcName}</option>
							</c:forEach>
					</select>
					<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>序号</th>
				<th>广告图片</th>
				<th>消息标题</th>
				<th>文字内容</th>
				<th>去看看链接</th>
				<th>消息盒子</th>
				<th>链接方式</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty bankMessageTemplates}">
					<c:forEach items="${bankMessageTemplates}" var="li" varStatus="vs">
						<tr class="main_info">
							<th onmouseover="this.title=this.innerText">${vs.index+1 }</th>
							<th><a href="${path}${li.advertisingImg}" class="preview">
									<img src="${path}${li.advertisingImg}" width="30" height="30" />
							</a></th>
							<th onmouseover="this.title=this.innerText">${li.messageTitle}</th>
							<th onmouseover="this.title=this.innerText">${li.writtenContent}</th>
							<c:if test="${li.linkWay=='out'}">
								
							</c:if>
							<c:choose>
								<c:when test="${li.linkWay=='in'}">
									<th onmouseover="this.title=this.innerText">
										<c:forEach items="${listModel}" var="list">
											<c:if test="${list.linkNo==li.remark}">
													${list.linkName}
											</c:if>
										</c:forEach>
									</th>
								</c:when>
								<c:otherwise>
									<th onmouseover="this.title=this.innerText">${li.outGoAndSeeLink}</th>
								</c:otherwise>
							</c:choose>
							<th onmouseover="this.title=this.innerText">${li.typeNo}</th>
							<th onmouseover="this.title=this.innerText"><c:if
									test="${li.linkWay=='in'}">内链</c:if> <c:if
									test="${li.linkWay=='out'}">外链</c:if>
							</th>
							<th><a href="javascript:edit(${li.messageTemplateId})">修改</a>
							</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="9">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
		<div style="float:left; width:100px; height:100px;">
			<!-- 消息模板必须后台开发接口，新增无意义，屏蔽 -->
			<!-- <a href="javascript:addMessageTemplate();" class="myBtn"><em>新增</em></a> -->
		</div>
			${bankMessageTemplate.page.pageStr}
		</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$(".main_info:even").addClass("main_table_even");
	});
	function search() {
		$("#BankMessageTemplateForm").submit();
	}
	function addMessageTemplate() {
		var dg = new $.dialog({
			title : '新增消息模板',
			id : 'MessageTemplate_new',
			width : 700,
			height : 400,
			iconTitle : false,
			cover : true,
			maxBtn : false,
			xButton : true,
			resize : false,
			page : 'bankMessageTemplate/addBankMessageTemplate.html'
		});
		dg.ShowDialog();
	}
	function edit(messageTemplateId) {
		var dg = new $.dialog(
				{
					title : '编辑消息模板',
					id : 'MessageTemplate_new',
					width : 800,
					height : 400,
					iconTitle : false,
					cover : true,
					maxBtn : false,
					xButton : true,
					resize : false,
					page : 'bankMessageTemplate/getBankMessageTemplateById.html?messageTemplateId='
							+ messageTemplateId
				});
		dg.ShowDialog();
	}

	function del(messageTemplateId) {
		if (confirm("确定要删除该记录？")) {
			$.ajax({
				url : 'bankMessageTemplate/delMessageTemplate.html',
				type : 'POST',
				data : {
					messageTemplateId : messageTemplateId
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
				},
				error : function() {
					alert("删除失败！");
					document.location.reload();
				}
			});
		}
	}
</script>
</html>