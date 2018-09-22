<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>

	<form action="BrandDetails.html" method="post" name="cardNumber"
		id="cardNumber">
		<div class="search_div">
			活动开始时间：<input type="text" name="startTime" value="${brandDetails.startTime}"
				onclick="WdatePicker()" readonly="readonly"
				style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:100px;padding:0px" />
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>编码</th>
				<th>品牌名称</th>
				<th>车型</th>
				<th>首付比例</th>
				<th>分期金额</th>
				<th>分期期数</th>
				<th>客户费率</th>
				<th>详情图片URL</th>
				<th>详情图片名称</th>
				<th>活动开始时间</th>
				<th>活动结束时间</th>
				<th>操作</th>

			</tr>
			<c:choose>
				<c:when test="${not empty bankCarBrandDetails}">
					<c:forEach items="${bankCarBrandDetails}" var="im" varStatus="vs">
						<tr class="main_info">
							<th><c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if> <c:if
									test="${vs.index+1>9}">${vs.index+1}</c:if></th>
							<th onmouseover="this.title=this.innerText">${im.brandName}</th>
							<th onmouseover="this.title=this.innerText">${im.carModel}</th>
							<th onmouseover="this.title=this.innerText">${im.downPaymentProportion}</th>
							<th onmouseover="this.title=this.innerText">${im.installmentMoney}</th>
							<th onmouseover="this.title=this.innerText">${im.installmentNumber}</th>
							<th onmouseover="this.title=this.innerText">${im.clienteleRate}</th>
							<th><a href="${path}${im.detailsImgUrl }"> <img
									src="${path}${im.detailsImgUrl }" width="30" height="30" />
								</a>
							</th>
							<th onmouseover="this.title=this.innerText">${im.detailsImgName}</th>
							<th onmouseover="this.title=this.innerText">${im.startTime}</th>
							<th onmouseover="this.title=this.innerText">${im.endTime}</th>
							<th><a href="javascript:editCarBrand('${im.detailsId}');">编辑</a>|<a
								href="javascript:CarBrandDel(${im.detailsId});">删除</a>
							</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="12">没有相关数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="page_and_btn">
		<div>
			<a href="javascript:carBrandsave();" class="myBtn"><em>新增</em></a>
		</div>
			${brandDetails.page.pageStr }
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
			$("#cardNumber").submit();
		}

		function carBrandsave() {
			var dg = new $.dialog({
				title : '新增汽车详情',
				id : 'carBrand_new',
				width : 600,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'BrandDetails/BrandDetailsAdd.html'
			});
			dg.ShowDialog();

		}

		function editCarBrand(detailsId) {
			var dg = new $.dialog({
				title : '编辑名牌',
				id : 'carBrand_update',
				width : 700,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'BrandDetails/carBrandUpdate.html?detailsId='
						+ detailsId
			});
			dg.ShowDialog();
		}

		function CarBrandDel(detailsId) {
			if (confirm("确定要删除该记录？")) {
				$.ajax({
					url : 'BrandDetails/delect.html',
					type : 'POST',
					data : {
						detailsId : detailsId
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
			;
		}
	</script>
</body>
</html>
