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
	<form  action="save.html" enctype="multipart/form-data" name="shopForm" id="shopForm" target="result"  method="post">
		<input type="hidden" name="oddsshopid" id="oddsshopid" value="${shop.oddsshopid }"/>
		<input type="hidden" name="oddsshopcity" id="oddsshopcity"/>
		<input type="hidden" name="oddsshopring" id="oddsshopring"/>
		
	<table border="0" cellpadding="0" cellspacing="0" align="center">
 	<tr class="info">
		<th>商户名称:</th>
			<td><input type="text" name="oddsshopname" id="oddsshopname" class="input_txt" maxlength="50"/>
			<label style="color: red;" class="red">*</label></td>
		
		<th>商户地址:</th>
			<td><input type="text" name="oddsshopaddress" id="oddsshopaddress" class="input_txt" maxlength="400"/>
			<label style="color: red;" class="red">*</label></td>
		</tr>
		
		<tr class="info">
			<th>商户经度:</th>
				<td><input type="text" name="oddsshoplongitude" id="oddsshoplongitude" class="input_txt" maxlength="32" onkeyup="clearNoNum(this)"/>
		        <label style="color: red;" class="red">*</label></td>
			<th>商户纬度:</th>
				<td><input type="text" name="oddsshoplatitude" id="oddsshoplatitude" class="input_txt" maxlength="32" onkeyup="clearNoNum(this)"/>
				<label style="color: red;" class="red">*</label></td>
		</tr>
		
		<tr class="info">
			<th>联系方式:</th>
				<td><input type="text" name="oddsshopphone" id="oddsshopphone" class="input_txt" maxlength="11"/>
				<label style="color: red;" class="red">*</label></td>
	
			<th>优惠形式:</th>
				<td><input type="text" name="oddsshopprivilege" id="oddsshopprivilege" class="input_txt" maxlength="100"/>
				</td>
		</tr>
		<tr class="info">
			<th>展示顺序:</th>
				<td><input type="text" name="oddsshoporder" id="oddsshoporder" class="input_txt" maxlength="32"/> </td>
				<th>城 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</th>
			<td>
				<select id="oddsshopcityId" onchange="onSelectItemTwo()" style="width:206px" class="input_txt">
					<option  value="">请选择</option>
		<%-- 		<c:forEach items="${gpslist}" var="gps">
					<option value="${gps.cityno }">${gps.cityname }</option>
				</c:forEach> --%>
				</select>
				<label style="color: red;" class="red">*</label>
			</td> 
		</tr>
		<tr class="info">
			<th>优惠活动:</th>
				<td><input type="text" name="oddsshopprivilegeact" id="oddsshopprivilegeact" class="input_txt" maxlength="100"/></td>
				<th>商 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 圈:</th>
				<td>
					<select id="oddsshopringId" onchange="onSelectItemThree()"  style="width:206px"  class="input_txt">
						<option value="">请选择</option>
					<c:forEach items="${cityShoplist}" var="citylist">
						<option value="${citylist.shopRingNo }">${citylist.shopRingName }</option>
					</c:forEach>
					</select>
				</td> 
		 </tr>
		<tr class="info">
		 	<th>商户图片:</th>
				<td><input type="file" id="oddsshoppictureurl" name="oddsshoppictureurlFile"  accept="image/*"   class="input_txt" maxlength="150"/>
				<label style="color: red;" class="red">*</label></td> 
				<th>活动介绍  :</th>
				<td><input type="text" name="oddsshopfavorableinfo" id="oddsshopfavorableinfo" class="input_txt" maxlength="2000"/></td>
		<tr class="info">
			<th>人均消费:</th>
				<td><input type="text" name="oddsshopconsumption" id="oddsshopconsumption" class="input_txt" maxlength="100"/>
				</td>
			<th>分店名称 :</th>
				<td><input type="text" name="oddsshopsubbranchname" id="oddsshopsubbranchname" class="input_txt" maxlength="50"/>
				</td>
		</tr>
		<tr class="info">
			
			<th>开始日期:</th>
				<td><input type="text" name="oddsshopbegintime" id="oddsshopbegintime" class="input_txt" maxlength="10"
				value="<fmt:formatDate value="${shop.oddsshopbegintime}" pattern="yyyy-MM-dd" />"
				onclick="WdatePicker()" style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:205px;padding:0px;" readonly="readonly"/>
				<label style="color: red;" class="red">*</label></td>
			<th>截止日期:</th>
				<td><input type="text" name="oddsshopendtime" id="oddsshopendtime" class="input_txt" maxlength="10"
				value="<fmt:formatDate value="${shop.oddsshopendtime}" pattern="yyyy-MM-dd" />"
				onclick="WdatePicker()" style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;width:205px;padding:0px;" readonly="readonly"/>
				<label style="color: red;" class="red">*</label></td>
		</tr>
			
		<tr class="info">
			<th>活动类型:</th>
				<td>
				<select name="oddsshoptypeid" id="oddsshoptypeid" class="input_txt">
					<option  value="">请选择</option>
				<c:forEach   items="${typelist}" var="type">
					<option value="${type.oddsshoptypeid }">${type.oddsshoptype }</option>
				</c:forEach>
				</select>
				<label style="color: red;" class="red">*</label>
				</td>
				<th>状 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</th>
				<td><select id="oddsshopstatus" name="oddsshopstatus" style="width:206px" class="input_txt"  > 
					<option  value="">请选择</option>
					<option  value="1">营业 </option> 
					<option  value="2">停止营业</option> 
					</select> 
					<label style="color: red;" class="red">*</label>
				</td>
		</tr>
		<tr class="info">
			<th>优惠内容:</th>
				<td colspan="3">
				<textarea rows="2" cols="60" onchange="javascript:testLength();" name="oddsshopbriefintroduction" id="oddsshopbriefintroduction"></textarea>
				<br/>
				<span id="oddsshopspan"></span>
				</td> 
		</tr>
		<tr class="info">
			<th>其它图片:</th>
			<td colspan="3" align="center"><input type="button" value="增加一行" id="addid" class="btn_public" onclick="addNewRow();"></td>
		</tr>
		</table>
		<div style="display:block;height:160px;overflow:auto;" >
					<table id="purdetail">
						<tr style="height:0px;">
							<td style="height:0px;width: 200px"><img src="../images/tupian.png" width="30" height="30" /></td>
							<td style="height:0px;width: 50px"><input type="file"  id="oddsshop" name="oddsshopFile" accept="image/*"   class="input_txt" maxlength="100"/></td>
							<td style="height:0px;width: 200px"><img style='cursor:pointer' src='../images/delete.gif'/></td>
						</tr>
					</table>
				</div>
		</form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<!-- <script type="text/javascript" src="../js/shops/saveShops.js"></script> -->
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
	function getBasePath()
	{
		var obj = window.location;
		var contextPath = obj.pathname.split("/")[1];
		var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
		return basePath;
	}
	var url = getBasePath()+"/shop/ajax_searchShops.html";
 	$(function(){
		$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			success:function(objs){
					city1 = objs;
					//获取城市的下拉列表
					var province=$("#oddsshopcityId");
					//遍历
					for(var i=0;i<objs.length;i++){
						var op="<option value='"+objs[i].cityno+"'>"+objs[i].cityname+"</option>";
						province.append(op);
					}
				}
		});
		$("#oddsshopcityId").change(function(){
			//获取城市下拉列表的id
			var cityno=$("#oddsshopcityId").val();
			//获取状态的下拉列表
			var city=$("#oddsshopringId");
			$("#oddsshopringId option:gt(0)").remove();
			$.post(
			getBasePath()+"/shop/searchCityShop.html?cityno="+cityno,
				function(objs){
					for(var i=0;i<objs.length;i++){
						var op="<option value='"+objs[i].shopRingNo+"'>"+objs[i].shopRingName+"</option>";
						city.append(op);
					}
				},
				'json'
			);
		});
	});
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn('ok','保存',function(){
			 
		if($("#oddsshopname").val()==""){
			alert("商户名称不能为空!");
			$("#oddsshopname").focus();
			return false;
		}if($("#oddsshopaddress").val()==""){
			alert("商户地址不能为空!");
			$("#oddsshopaddress").focus();
			return false;
		} if($("#oddsshoplongitude").val()==""){
			 alert("商户经度不能为空！");
			 $("#oddsshoplongitude").focus();
			 return false;
		}if($("#oddsshoplatitude").val()==""){
			alert("商户纬度不能为空！");
			$("#oddsshoplatitude").focus();
			return false;
		}
		if($("#oddsshopphone").val()==""){
			alert("联系方式不能为空!");
			$("#oddsshopphone").focus();
			return false;
		}else{
			var reg=/^[0-9]*$/;
		    if(!reg.test($("#oddsshopphone").val())){
		    	alert("联系方式只能输入数字！");
		    	$("#oddsshopphone").val("");
		    	$("#oddsshopphone").focus();
		    	return false;
		    } 
		   
		}
		if($("#oddsshopcityId").val()==""){
		     alert("商户所在的城市 ");
			 $("#oddsshopcityId").focus();
			return false;
		} if($("#oddsshopringId").val()==""){
		     alert("请选择商圈 ");
			 $("#oddsshopringId").focus();
			return false;
		}if($("#oddsshopstatus").val()==""){
		     alert("请选择状态");
			 $("#oddsshopstatus").focus();
			return false;
		} 
		var reg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
		if($("#oddsshoppictureurl").val()==""){
		     alert("请上传商户图片");
			 $("#oddsshoppictureurl").focus();
			return false;
		}else{
			var start=$("#oddsshoppictureurl").val().lastIndexOf("\\");
			var end=$("#oddsshoppictureurl").val().lastIndexOf(".");
			var name=$("#oddsshoppictureurl").val().substring(start+1,end);
		    if(reg.test(name)){
			   alert("图片名称不能包含中文字符!");
			   return false;
			} 
			if(!checkImgName($("#oddsshoppictureurl").val())){
				return false;
			}
			
		}
		 if($("#oddsshop").val()==""){
		     alert("请上传其它图片");
			 $("#oddsshop").focus();
			return false;
		}else{
			var start=$("#oddsshop").val().lastIndexOf("\\");
			var end=$("#oddsshop").val().lastIndexOf(".");
			var name=$("#oddsshop").val().substring(start+1,end);
		    if(reg.test(name)){
			   alert("图片名称不能包含中文字符!");
			   return false;
			} 
			if(!checkImgName($("#oddsshop").val())){
				return false;
			}
		} if($("#oddsshoptypeid").val()==""){
		     alert("请选择类型");
			 $("#oddsshoptypeid").focus();
			return false;
		}
		var a = /^(\d{4})-(\d{2})-(\d{2})$/;
		if (!a.test(document.getElementById("oddsshopendtime").value)) {
			alert("日期格式不正确!");
			return false;
		}
		if (!a.test(document.getElementById("oddsshopbegintime").value)) {
			alert("日期格式不正确!");
			return false;
		}
		var endtime = document.getElementById("oddsshopendtime").value;
		   var begintime = document.getElementById("oddsshopbegintime").value;
		   var bt=begintime.split("-")[0]+begintime.split("-")[1]+begintime.split("-")[2];
		   var et=endtime.split("-")[0]+endtime.split("-")[1]+endtime.split("-")[2];
		if(et-bt<0){
			alert("结束日期要大于或等于开始日期");
			return false;
		}
			$("#shopForm").submit();
		});
		
	});
	
   function checkTime(startTime, endTime) {
	   var nd = new Date(startTime.replace("-", "/"));
	   var ld = new Date(endTime.replace("-", "/"));
	   if (Date.parse(ld) - Date.parse(nd) < 0) {
		   alert("结束日期要大于或等于开始日期");
		   return false;
	   } else {
		   return true;
	   }
   }
   
	function PreviewImage(imgFile) {
		var pattern = /(\.*.jpg	)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if (!pattern.test(imgFile.value)) {
			alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
			imgFile.focus();
		} else {
			var path;
			if (document.all)//IE 
			{
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById("imgPreview").innerHTML = "";
				document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果 
			} else//FF 
			{
				path = URL.createObjectURL(imgFile.files[0]);
				document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>";
			}
		}
	}


	function clearNoNum(obj) {
			obj.value = obj.value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符
			obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是.
			obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
			obj.value = obj.value.replace(".", "$#$").replace(/\./g, "")
					.replace("$#$", ".");
		
	}


	function success(){
		if(dg.curWin.document.forms[0]){
			dg.curWin.document.forms[0].action = dg.curWin.location+"";
			dg.curWin.document.forms[0].submit();
		}else{
			dg.curWin.location.reload();
		}
		dg.cancel();
	}

	function failed(){
	var i=document.getElementById("oddsshoporder").value;
		alert("新增失败！展示顺"+i+"已经添加，不能重复添加");
		$("#oddsshoporder").select();
		$("#oddsshoporder").focus();
	}
	
	
function onSelectItemTwo(){
	var sel_obj = document.getElementById("oddsshopcityId");
	var index = sel_obj.selectedIndex;
	var value=sel_obj.options[index].value;
 	$("#oddsshopcity").val(value);
}
	
function onSelectItemThree(){
	var sel_obj = document.getElementById("oddsshopringId");
	var index = sel_obj.selectedIndex;
	var value=sel_obj.options[index].value;
 	$("#oddsshopring").val(value);
}	
	
	
	
/**
* 增加一行
*/
var i=2;
function addNewRow(){

var purdetail=document.getElementById("purdetail");
var newRow=purdetail.insertRow();
var newcell =newRow.insertCell();
$(newcell).append("<img src='../images/tupian.png' width='30' height='30' />");
var newcell =newRow.insertCell();
$(newcell).append("<input type='file'  id='oddsshop' name='oddsshopFile' accept='image/*' class='input_txt' maxlength='100'/>");
var newcell=newRow.insertCell();
$(newcell).append("<img style='cursor:pointer' title='删除' src='../images/delete.gif' onclick='deleteRow("+0+",this)'/>");
}
/**
* 删除最后一行
*/
function deleteRow(id, event) {
$(event.parentElement.parentElement).remove();
}

	</script>
</html>