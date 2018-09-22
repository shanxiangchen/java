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
	<form action="rim.html" method="post" name="RightsInterestsMappingForm"
		id="RightsInterestsMappingForm">
		<div class="search_div">
			<table>
				<tr>
					<th>权益包编码:</th>
					<td><input type="text" name="rightsPackagerkCode"
						id="rightsPackagerkCode" value="${rightsPackagerkMapping.rightsPackagerkCode}" />
					</td>
					<td><a href="javascript:search();" class="myBtn"><em>查询</em></a>
					</td>
				</tr>
			</table>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th width="10%"><input type="checkbox" name="sltAll"
					id="sltAll" onclick="sltAllUser()" /> 序号</th>
				<th>权益包编码</th>
				<th>权益包名称</th>
				<th>APP显示名称</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty packagerkMappings}">
					<c:forEach items="${packagerkMappings}" var="list" varStatus="vs">
						<tr class="main_info">
							<th><input type="checkbox" type="hidden"
								name="rightsPackagerkId" value="${list.rightsPackagerkId}" />
								&nbsp;&nbsp; <c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
								<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
							</th>
							<th onmouseover="this.title=this.innerText">${list.rightsPackagerkCode}</th>
							<th onmouseover="this.title=this.innerText">${list.rightsPackagerkName}</th>
							<th onmouseover="this.title=this.innerText">${list.appShowName}</th>
							<th><a
								href="javascript:editRightsMap('${list.rightsPackagerkId}');">修改</a>
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
			<div>
				<a href="javascript:addRightsMap()" class="myBtn"><em>新增</em>
				</a> <a href="javascript:void(0)"onclick="delRightsMapById('${list.rightsPackagerkId}')"
					class="myBtn"> <em>删除</em>
				</a>
			</div>
			${rightsPackagerkMapping.page.pageStr}
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
			$("#RightsInterestsMappingForm").submit();
		}

		function addRightsMap() {

			var dg = new $.dialog({
				title : '添加权益包',
				width : 450,
				height : 250,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'rim/addRightsMap.html'
			});
			dg.ShowDialog();
		}

		function delRightsMapById(rightsId) {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="rightsPackagerkId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="rightsPackagerkId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'rim/delRightsMapById.html',
						type : 'POST',
						data : {
							rightsIds : str
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

		function editRightsMap(rightsId) {
			var dg = new $.dialog({
				title : '修改权益活动',
				width : 450,
				height : 250,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'rim/editRightsMapById.html?rightsId=' + rightsId
			});
			dg.ShowDialog();
		}

		function sltAllUser() {
			if ($("#sltAll").attr("checked")) {
				$("input[name='rightsPackagerkId']").attr("checked", true);
			} else {
				$("input[name='rightsPackagerkId']").attr("checked", false);
			}
		}
	</script>
</body>
</html>