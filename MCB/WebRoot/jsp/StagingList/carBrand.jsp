<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />

</head>
<body>

	<form action="carBrand.html" method="post" name="brandName"
		id="brandName">
		<div class="search_div">
			汽车品牌:&nbsp;<input type="text" name="brandName" id="brandName"
				value="${brand.brandName}" /> <a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		<table width="100%" id="tableId" border="0" cellpadding="1"
			cellspacing="1" class="main_table">
			<tr class="main_head">
				<th>编码</th>
				<th>品牌名称</th>
				<th>图片</th>
				<th>品牌详细</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${not empty bankCarBrands}">
					<c:forEach items="${ bankCarBrands}" var="im" varStatus="vs">
						<tr class="main_info">
							<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
							<th onMouseOver="this.title=this.innerText">${im.brandName}</th>
							<th><a href="${path}${im.imgAddressUrl }" class="preview">
									<img src="${path}${im.imgAddressUrl }" width="30" height="30" />
							</a>
							</th>
							<th onMouseOver="this.title=this.innerText"><a
								href="javascript:CarBrandDetailsPc('${im.brandId}')">详情</a>
							</th>
							<th><a href="javascript:editCarBrand('${im.brandId}');">修改</a>|<a
								href="javascript:CarBrandDel('${im.brandId}','${im.imgName}');">删除</a>
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
		  <c:if test="${fn:length(bankCarBrands)lt 9}">  
	       <div style="float:left; width:100px; height:100px;"><a href="javascript:carBrandsave();" class="myBtn"><em>新增</em></a></div>
	      </c:if>  
			${brand.page.pageStr }
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
			$("#brandName").submit();
		}

		function CarBrandDel(brandId, imgName) {
			if (confirm("确定要删除该记录？")) {
				$.ajax({
					url : 'carBrand/delect.html',
					type : 'POST',
					data : {
						brandId : brandId,
						imgName : imgName
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

		function carBrandsave() {
			var dg = new $.dialog({
				title : '新增汽车品牌',
				id : 'carBrand_new',
				width : 930,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'carBrand/carBrandAdd.html'
			});
			dg.ShowDialog();

		}

		function editCarBrand(brandId) {
			var dg = new $.dialog({
				title : '编辑名牌',
				id : 'carBrand_update',
				width : 930,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : 'carBrand/carBrandUpdate.html?brandId=' + brandId
			});
			dg.ShowDialog();
		}
		function CarBrandDetailsPc(brandId) {
			var dg = new $.dialog({
				title : '品牌详情',
				id : 'CarBrandDetailsPc_new',
				width : 630,
				height : 400,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				xButton : true,
				resize : false,
				page : 'carBrand/brandParticulars.html?brandId=' + brandId
			});
			dg.ShowDialog();
		}
	</script>
</body>
</html>
