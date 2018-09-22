<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信业银行信用卡APP后台管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>

<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:150px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="carupdatesave.html" name="CarBrandForm" id="CarBrandForm" target="result" method="post" enctype="multipart/form-data" >
		<input type="hidden"  name="detailsImgUrl" value="${carBrandDetails.detailsImgUrl}"/>
	    <input type="hidden"  name="detailsImgName" value="${carBrandDetails.detailsImgName}"/>
		<input type="hidden" name="detailsId" id="detailsId" value="${carBrandDetails.detailsId}"> 
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
		<th>品牌名称:</th>
			<td>
				    <select name="brandId"   style="width:160px;line-height:25px;" id="brandId" >
					     <option  value="">请选择</option>
				         <c:forEach items="${typelist}" var="type">
					        <option value="${type.brandId}" <c:if test="${type.brandId == carBrandDetails.brandId}">selected</c:if>>${type.brandName}</option>
				         </c:forEach>
				       </select>
				<label style="color: red;" class="red"  >*</label>
				
			</td>
			
					<th>车型:</th>
			<td>
				<input type="text" name="carModel" id="carModel" value="${carBrandDetails.carModel}" class="input_txt" maxlength="30" style="width:155px" >
				<label style="color: red;" class="red" >*</label>
			</td>
		</tr>
		
		
		<tr class="info">
		   <th>首付比例:</th>
			<td>
				<input type="text" name="downPaymentProportion" id="downPaymentProportion" value="${carBrandDetails.downPaymentProportion}" maxlength="20" style="width:155px">
				<label style="color: red;" class="red" >*</label>
				
			</td>
				<th>分期金额:</th>
			<td>
				<input type="text" name="installmentMoney" id="installmentMoney" value="${carBrandDetails.installmentMoney}"  style="width:155px">
				<label style="color: red;" class="red" id="verification$installmentMoney" >*</label>
	    
		</tr> 
		    <tr class="info">
			 <th>分期期数:</th>
			<td>
				<input type="text" name="installmentNumber" id="installmentNumber" maxlength="20" style="width:155px" value="${carBrandDetails.installmentNumber}" >
				<label style="color: red;" class="red"  >*</label>
				
			</td>
			 <th>客户费率:</th>
			<td>
				<input type="text" name="clienteleRate" id="clienteleRate" maxlength="20" style="width:155px" value="${carBrandDetails.clienteleRate}" >
				<label style="color: red;" class="red"  >*</label>
				
			</td>
		</tr> 
		
		<tr class="info">

			<th>活动开始时间:</th>
			<td>
				<input type="text" name="startTime" id="startTime" value="${carBrandDetails.startTime}" onclick="WdatePicker()" readonly="readonly" style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:155px;height:20px;padding:0px"/>
				<label style="color: red;" class="red"  >*</label>
			</td>
			
				<th>活动结束时间:</th>
			<td>
				<input type="text" name="endTime" id="endTime" value="${carBrandDetails.endTime}" onclick="WdatePicker()" readonly="readonly" style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:155px;height:20px;padding:0px"/>
				<label style="color: red;" class="red"  >*</label>
			</td>
		</tr>
		
		<tr class="info">
				     <th>图片url:</th>
		       <td>
				<input type="file" id="detailsImgUrl" name="detailsImgUrlFile"   accept="image/*"   class="input_txt"  />&nbsp;&nbsp;
				<label style="color: red;" class="red" id="verification$detailsImgUrlFile">*</label></td>
				<td align="left"><img src="${path}${carBrandDetails.detailsImgUrl}" width="30" height="30" alt="${carBrandDetails.detailsImgUrl}"/>
				</td>
	
		</tr>
		
		
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="../js/carBrand/BrandDetails_update.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</body>
<script type="text/javascript">			
window.onload=function(){
		var verification = $("*[id^='verification$']");
			if(verification.length > 0){
				for (var i = 0;i < verification.length;i++){
					verification[i].style.color="red";
			}
		}else{
			if(verification.length > 0){
				for (var i = 0;i < verification.length;i++){
					verification[i].style.color="red";
				
				}
			}
		}
	};
	
</script>
</html>