<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<style type="text/css">
sa {
	text-decoration: none;
	color: #f30;
}

img {
	border: none;
}

ul,li {
	margin: 0;
	padding: 0;
}

li {
	list-style: none;
}

#preview {
	position: absolute; bottom：30px;
	width: 10%;
	height: 10%;
}
</style>
<link type="text/css" rel="stylesheet" href="css/main.css" />

</head>
<body>
	<form action="${pageContext.request.contextPath }/rt.html"
		method="post" name="RightsTypeForm" id="RightsTypeForm">
		<div class="search_div">
			<table>
				<tr>
					<th>权益类型:</th>
					<td><input type="text" name="rigthsTypeName"
						id="rigthsTypeName" value="${rightsType.rigthsTypeName}" />
					</td>
					<td><a href="javascript:search();" class="myBtn"><em>查询</em></a>
					</td>
				</tr>
			</table>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>编号</th>
				<th>权益编码</th>
				<th>权益类型</th>
				<th>权益图片</th>
				<th>权益图标</th>
				<!-- <th>权益包名称</th> -->
				<th>是否明细</th>
				<th>是否预约</th>
				<th>是否本人</th>
				<th>是否随行</th>
				<th>按钮名称1</th>
				<th>按钮名称2</th>
				<th>预约计算方式</th>
				<th>提前预约天数</th>
				<th>最多预约天数</th>
				<th>提前预约小时</th>
				<th>权益描述</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty rightsTypes}">
					<c:forEach items="${ rightsTypes}" var="list" varStatus="vs">
						<tr class="main_info">
							<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
							<th onmouseover="this.title=this.innerText">${list.rigthsTypeCode}</th>
							<th onmouseover="this.title=this.innerText">${list.rigthsTypeName}</th>
							<th><a href="${path}${list.rigthsTypeUrl }" class="preview">
									<img src="${path}${list.rigthsTypeUrl}" width="30" height="30" />
							</a></th>
							<th><a href="${path}${list.rigthsTypeIconUrl}"
								class="preview"> <img
									src="${path}${list.rigthsTypeIconUrl}" width="30" height="30" />
							</a></th>
							<%-- <th onmouseover="this.title=this.innerText">${list.rightsPackagerkMapping.rightsPackagerkName}</th> --%>
							<th onmouseover="this.title=this.innerText"><c:if
									test="${list.rightsDetailBtn=='1'}">是</c:if> <c:if
									test="${list.rightsDetailBtn=='0'}">否</c:if></th>
							<th onmouseover="this.title=this.innerText"><c:if
									test="${list.rightsOrderBtn=='1'}">是</c:if> <c:if
									test="${list.rightsOrderBtn=='0'}">否</c:if></th>
							<th onmouseover="this.title=this.innerText"><c:if
									test="${list.rightsSelfUsed=='1'}">是</c:if> <c:if
									test="${list.rightsSelfUsed=='0'}">否</c:if></th>
							<th onmouseover="this.title=this.innerText"><c:if
									test="${list.rightsHeelUsed=='1'}">是</c:if> <c:if
									test="${list.rightsHeelUsed=='0'}">否</c:if></th>
							<th onmouseover="this.title=this.innerText">${list.rightsDetailName}</th>
							<th onmouseover="this.title=this.innerText">${list.rightsOrderName}</th>
							<th onmouseover="this.title=this.innerText" id="calculation">
								<c:if test="${list.calculation=='0'}">按小时</c:if>
								<c:if test="${list.calculation=='1'}">自然日</c:if>
								<c:if test="${list.calculation=='2'}">工作日</c:if>
							</th>
							<th onmouseover="this.title=this.innerText" id="rightsBeforeDay">	
							<c:if test="${list.rightsBeforeDay==''|| list.rightsBeforeDay==null}">-</c:if>
							<c:if test="${list.rightsBeforeDay!=''|| list.rightsBeforeDay!=null}">${list.rightsBeforeDay}</c:if>
							</th>
							<th onmouseover="this.title=this.innerText" id="rightsMaxBefoDay">
							<c:if test="${list.rightsMaxBeforeDay==''|| list.rightsMaxBeforeDay==null}">-</c:if>
							<c:if test="${list.rightsMaxBeforeDay!=''|| list.rightsMaxBeforeDay!=null}">${list.rightsMaxBeforeDay}</c:if>
							</th>
							<th onmouseover="this.title=this.innerText" id="rightsBeforeHour">
							<c:if test="${list.rightsBeforeHour==''|| list.rightsBeforeHour==null}">-</c:if>
							<c:if test="${list.rightsBeforeHour!=''|| list.rightsBeforeHour!=null}">${list.rightsBeforeHour}</c:if>
							</th>
							<th><a
								href="javascript:rightsTypeInfo('${list.rigthsTypeId}')">详情</a>
							</th>
							<th><a href="###"
								onclick="editRigthsType('${list.rigthsTypeId}')">修改</a> | <a
								href="javascript:delRigthsType('${list.rigthsTypeId}','${list.rigthsTypeCode}')">删除</a>
							</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="17">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
		<div>
			<a href="javascript:addRigthsType();" class="myBtn"><em>新增</em></a>
		</div>
		${rightsType.page.pageStr}
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
			$("#RightsTypeForm").submit();
		}

		function addRigthsType() {
			var dg = new $.dialog({
				title : '新增权益类型',
				id : 'RigthsType_new',
				width : 930,
				height : 420,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'rt/addRigthsType.html'
			});
			dg.ShowDialog();
		}

		function editRigthsType(typeId) {
			var dg = new $.dialog({
				title : '修改权益类型',
				id : 'RigthsType_new',
				width : 930,
				height : 420,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'rt/selRightsTypeInfo.html?typeId=' + typeId
			});
			dg.ShowDialog();
		}
		
		
		function delRigthsType(typeId,rigthsTypeCode) {

			if (confirm("确定要删除该记录？")) {
				$.ajax({
					url : 'rt/delRigthsType.html',
					type : 'POST',
					data : {
						"rigthsTypeId" : typeId,
						"rigthsTypeCode":rigthsTypeCode
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

		function rightsTypeInfo(typeId) {
			var dg = new $.dialog({
				title : '权益描述详情',
				cancelBtnTxt : '关闭',
				width : 630,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'rt/rightsTypeInfo.html?typeId=' + typeId
			});
			dg.ShowDialog();
		}
	</script>
</body>
</html>