<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>

<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="saver.html" name="shopForm" id="shopForm" target="result" method="post" enctype="multipart/form-data">
	<input type="hidden" name="oddsshopid" id="oddsshopid" value="${shop.oddsshopid}"/>
	<table cellpadding="1" cellspacing="1" align="center">
	<tr class="info">
		<th>商户名称:</th>
			<td><input type="text" disabled="disabled"  value="${shop.oddsshopname}"  class="input_txt" maxlength="50"/>
			<label style="color: red;" class="red">*</label></td>
		<th>商户地址:</th>
			<td><input type="text" disabled="disabled"  value="${shop.oddsshopaddress}" class="input_txt" maxlength="400"/>
			<label style="color: red;" class="red">*</label></td>
		</tr>
		
		<tr class="info">
			<th>商户经度:</th>
				<td><input type="text" disabled="disabled"  value="${shop.oddsshoplongitude}" class="input_txt" maxlength="32" onkeyup="clearNoNum(this)"/>
				<label style="color: red;" class="red">*</label></td>
		
			<th>商户纬度:</th>
				<td><input type="text" disabled="disabled" value="${shop.oddsshoplatitude}" class="input_txt" maxlength="32" onkeyup="clearNoNum(this)"/>
				<label style="color: red;" class="red">*</label></td>
		</tr>
		 <tr class="info">
			<th>联系方式:</th>
				<td><input type="text" name="oddsshopphone" id="oddsshopphone" value="${shop.oddsshopphone}" class="input_txt" maxlength="11"/>
				<label style="color: red;" class="red">*</label></td>
	
			<th>优惠形式:</th>
				<td><input type="text" name="oddsshopprivilege" id="oddsshopprivilege" value="${shop.oddsshopprivilege}" class="input_txt" maxlength="100"/>
				</td>
		</tr>
		<tr class="info">
			<th>展示顺序:</th>
				<td><input type="text" name="oddsshoporder" id="oddsshoporder" value="${shop.oddsshoporder}" class="input_txt" maxlength="32"/> </td>
				
			<th>城 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</th>
			<td>
				<select disabled="disabled" style="width:206px" class="input_txt">
					 <option value="">请选择</option>
					<c:forEach items="${gpslist}" var="gps">
						<option value="${gps.cityno }"<c:if test="${gps.cityno==shop.oddsshopcity}">selected</c:if>>${gps.cityname}</option>
					</c:forEach>
				</select>
				<label style="color: red;" class="red">*</label>
			</td> 
		</tr>
		<tr class="info">
			<th>优惠活动:</th>
				<td><input type="text" name="oddsshopprivilegeact" id="oddsshopprivilegeact" value="${shop.oddsshopprivilegeact}" class="input_txt" maxlength="100"/>
				</td>
			<th>商 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 圈:</th>
				<td>
					<select name="oddsshopring" id="oddsshopring"  style="width:206px"  class="input_txt">
					    <option value="">请选择</option>
						<c:forEach items="${cityShoplist}" var="cityShop">
							<option value="${cityShop.shopRingNo}"<c:if test="${cityShop.shopRingNo==shop.oddsshopring}">selected</c:if>>${cityShop.shopRingName }</option>
						</c:forEach>
					</select>
					 
			</td> 
		 </tr>
		<tr class="info">
			<th>商户图片:</th>
				<td>
				<input type="file" id="oddsshoppictureurl" name="oddsshoppictureurlFile"   accept="image/*"   class="input_txt" maxlength="100"/>
				<label style="color: red;" class="red">*</label></td>
				<td colspan="2" align="right"><img src="${path}${shop.oddsshoppictureurl}" width="30" height="30" alt="${shop.oddsshoppicturename}"/></td>
			</tr>
		<tr class="info">
			<th>人均消费:</th>
				<td><input type="text" name="oddsshopconsumption" id="oddsshopconsumption" value="${shop.oddsshopconsumption}" class="input_txt" maxlength="100"/>
				</td>
			<th>分店名称 :</th>
				<td><input type="text" name="oddsshopsubbranchname" id="oddsshopsubbranchname" value="${shop.oddsshopsubbranchname}" class="input_txt" maxlength="50"/>
				</td>
		</tr>
		<tr class="info">
			<th>活动介绍 :</th>
				<td><input type="text" name="oddsshopfavorableinfo" id="oddsshopfavorableinfo" value="${shop.oddsshopfavorableinfo}" class="input_txt" maxlength="2000"/>
			</td>
			<th>发布日期:</th>
				<td>
				<input type="text" id="oddsshoppublictime" name="oddsshoppublictime" readonly="readonly"  value="${shop.oddsshoppublictime}" class="input_txt" maxlength="50" >
				</td> 
		</tr>
		<tr class="info">
			
			<th>开始日期:</th>
				<td>
				<input type="hidden" id="oddsshopbegintime" name="oddsshopbegintime"  value="<fmt:parseDate value="${shop.oddsshopbegintime}" var="date2"></fmt:parseDate><fmt:formatDate value="${date2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()">
				<input id="oddsshopbegintimes" readonly="true" type="text" value="<fmt:parseDate value="${shop.oddsshopbegintime}" var="date2"></fmt:parseDate> <fmt:formatDate value="${date2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()"  style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:205px;padding:0px;"/>
				<label style="color: red;" class="red">*</label>
				</td> 
			<th>截止日期:</th>
				<td>
				<input type="hidden" id="oddsshopendtime" name="oddsshopendtime"  value="<fmt:parseDate value="${shop.oddsshopendtime}" var="date1"></fmt:parseDate><fmt:formatDate value="${date1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()">
				<input id="oddsshopendtimes" readonly="true" type="text" value="<fmt:parseDate value="${shop.oddsshopendtime}" var="date1"></fmt:parseDate> <fmt:formatDate value="${date1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()"  style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:205px;padding:0px;"/>
				<label style="color: red;" class="red">*</label>
				</td> 
		</tr>
		
		<tr class="info">
			<th>活动类型:</th>
				<td>
				<select name="oddsshoptypeid" id="oddsshoptypeid" class="input_txt">
					<c:forEach items="${typelist}" var="type">
						<option value="${type.oddsshoptypeid }"<c:if test="${type.oddsshoptypeid==shop.oddsshoptypeid}">selected</c:if>>${type.oddsshoptype}</option>
					</c:forEach>
				</select>
				<label style="color: red;" class="red">*</label>
				</td>
			<th>状 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</th>
				<td><select id="oddsshopstatus" name="oddsshopstatus" style="width:206px" class="input_txt"  > 
					<option  value="">请选择</option>
					<option  value="1" <c:if test="${shop.oddsshopstatus =='1'}">selected</c:if>>营业 </option> 
					<option  value="2" <c:if test="${shop.oddsshopstatus =='2'}">selected</c:if>>停止营业</option> 
					</select> 
					<label style="color: red;" class="red">*</label>
				</td>
		</tr>
		<tr class="info">
			<th>优惠内容:</th>
				<td colspan="3">
				<textarea rows="2" cols="60" name="oddsshopbriefintroduction" onchange="javascript:testLength();" id="oddsshopbriefintroduction">${shop.oddsshopbriefintroduction}</textarea>
				<br/>
				<span id="oddsshopspan"></span>
				</td> 
		</tr>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/shops/updateShops.js"></script>
	<script type="text/javascript">
	/**
	 * 测试文本值长度
	 */
	function testLength(){
		var textvalue = $("#oddsshopbriefintroduction").val();
		var textlength = textvalue.length;
		if(textlength>120){
			var substr =textvalue.substring(0,120);
			$("#oddsshopbriefintroduction").val(substr);
			$("#oddsshopspan").text("您输入的内容超过限定长度，已为您截取120个字符长度的内容。");
		}else{
			$("#oddsshopspan").text("您还能输入"+(120-textlength)+"字符。");
		}
	}
	</script>
</body>

</html>