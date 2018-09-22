<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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
.info th{text-align:right;width:115px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="updateAddCardInfo.html" enctype="multipart/form-data" name="CardupdayeInfoForm" id="CardupdayeInfoForm" target="result"  method="post">
	  <input type="hidden" id="cardId" name="cardId" value="${cardAddInfo.cardId }"/>
	 <input type="hidden"  name="cardPicUrl" value="${cardAddInfo.cardPicUrl}"/>
	 <input type="hidden" id="cardPicName" name="cardPicName" value="${cardAddInfo.cardPicName}"/>
	 <table border="0" cellpadding="0" cellspacing="0">
 	 <tr class="info">
 		<th>卡产品编号:</th>
			<td><input type="text" name="cardNum" id="cardNum" value="${cardAddInfo.cardNum}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
		<th>卡产品名称:</th>
			<td><input type="text" maxlength="50" name="cardName" id="cardName" value="${cardAddInfo.cardName}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
			</tr>
	    <tr class="info">
		    <th>卡产品种类:</th>
			<td>
			<select name="typeId" id="typeId" style="width:205px;">
				<option value="">请选择</option>
				<c:forEach items="${listOf}" var="li">
					<option value="${li.typeId}" <c:if test="${li.typeId==cardAddInfo.typeId}">selected</c:if>>${li.typeName}</option>
				</c:forEach>
			</select>
			<label style="color: red;" class="red">*</label></td>
			 
		
		    <th>卡产品图片:</th>
			<td><input type="file" name="cardPicUrlFile" id="cardPicUrl" style="width:205px;" accept="image/*" />
			<label style="color: red;" class="red">*</label></td>
			<td colspan="2" align="right"><img src="${path}${cardAddInfo.cardPicUrl}" width="30" height="30" alt="${cardAddInfo.cardPicName}"/></td>
		</tr>
		<tr class="info">
		    
		    <th>推荐优先级:</th>
			<td><input type="text" name="cardForOrder" maxlength="5" id="cardForOrder" value="${cardAddInfo.cardForOrder}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
			<th>卡产品版面编号:</th>
			<td><input type="text" name="cardBmNo" maxlength="10" id="cardBmNo" value="${cardAddInfo.cardBmNo}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
			
		</tr>
		<tr class="info">
		<th>适合年龄段:</th>
			<td>
			 <c:forEach items="${listFor}" var="li">
				     <input type="checkbox" name="cardForAge"  value="${li.value}" <c:if test="${fn:contains(cardAddInfo.cardForAge,li.value)}">checked="checked"</c:if> >${li.content}
			 </c:forEach>
			 <label style="color: red;" class="red">*</label></td>
		     <th>适合性别:</th>
			<td>
			   <input type="checkbox" name="cardForSex" value="1" <c:if test="${fn:contains(cardAddInfo.cardForSex,'1')}">checked="checked"</c:if>>男
			   <input type="checkbox" name="cardForSex" value="2" <c:if test="${fn:contains(cardAddInfo.cardForSex,'2')}">checked="checked"</c:if>>女
			   <label style="color: red;" class="red">*</label>
			</td> 
		</tr>
		<tr class="info">
			<th>适合客户喜好:</th>
			<td colspan="4">
			  <c:forEach items="${listLike}" var="li">
				     <input type="checkbox" name="cardForLike"  value="${li.value}" <c:if test="${fn:contains(cardAddInfo.cardForLike,li.value)}">checked="checked"</c:if>>${li.content}
			  </c:forEach>
			  <label style="color: red;" class="red">*</label>
			 </td>
		</tr>
		<tr class="info">
			<th>卡产品介绍:</th>
			<td colspan="3">
			<textarea rows="4" cols="63" name="cardInfo" id="cardInfo">${cardAddInfo.cardInfo}</textarea>
			<label style="color: red;" class="red">*</label></td>
		</tr>
		 
		</form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<!-- <script type="text/javascript" src="../js/jsAddress.js"></script> -->
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
        var dg; var count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			     if(!validata()){
			       return;
			     }
				 if(count!=0){
			       return;
			     }
			     count=count+1;
	             $("#CardupdayeInfoForm").ajaxSubmit({
						type : "POST",
						url : "updateAddCardInfo.html",
						dataType : "text",
						success : function(text) {
							/* if (text == "1") {
								alert("卡产品编号已经存在");
								$("#cardNum").val("");
								$("#cardNum").focus();
								count=0;
							}else{ */
							  success();
							/* } */
						},
						error : function() {
							alert("保存失败！");
							success();
						}
				 });
				 
			});
		
		
		function validata(){
		     var reg=/^[0-9]*$/;
		    if($.trim($("#cardNum").val())==""){
		       alert("卡产品编号不能为空");
		       $("#cardNum").val("");
		       $("#cardNum").focus();
		       return false;
		    }
		    if($.trim($("#cardName").val())==""){
		       alert("卡产品名称不能为空");
		       $("#cardName").val("");
		       $("#cardName").focus();
		       return false;
		    }
		    if($("#typeId").val()==""){
		       alert("请选择卡产品种类");
		       $("#typeId").val("");
		       $("#typeId").focus();
		       return false;
		    }
		    if($.trim($("#cardForOrder").val())==""){
		       alert("推荐优先级不能为空");
		       $("#cardForOrder").val("");
		       $("#cardForOrder").focus();
		       return false;
		    }else{
		       if(!reg.test($("#cardForOrder").val())){
		         alert("推荐优先级只能输入数字");
		         $("#cardForOrder").val("")
		         $("#cardForOrder").focus()
		         return false;
		       }
		       
		       if($("#cardForOrder").val()<1||$("#cardForOrder").val()>999){
		          alert("推荐优先级输入范围1-999之间");
		          return false;
		       }
		    }
		    
		    if($.trim($("#cardBmNo").val())==""){
		    	alert("卡产品版面编号不能为空");
		    	$("#cardBmNo").val("");
			    $("#cardBmNo").focus();
			    return false;
		    }
		    /*  if($("#cardIsAdd").val()==""){
		       alert("请选择是否可加办卡");
		       $("#cardIsAdd").val("");
		       $("#cardIsAdd").focus();
		       return false;
		    } */
		    if(!$("input[name='cardForSex']").is(":checked")){
		       alert("请选择适合性别");
		       return false;
		    }
		    if(!$("input[name='cardForAge']").is(":checked")){
		       alert("请选择适合年龄段");
		       return false;
		    }
		    if(!$("input[name='cardForLike']").is(":checked")){
		       alert("请选择适合客户喜好");
		       return false;
		    }
		     if($("#cardPicUrl").val()!=""){
		        var strs=$("#cardPicUrl").val().split(".");
		        var fileType=strs[strs.length-1].toLowerCase();
		        if(fileType!="gif"&&fileType!="jpg"&&fileType!="jpeg"&&fileType!="bmp"&&
		        fileType!="png"){
		            alert("图片类型必须是.gif,jpeg,jpg,bmp,png中的一种");
					 $("#cardPicUrl").val("");
					 return false;
				}
				if(!checkImgName($("#cardPicUrl").val())){
					return false;
				}
		     }
		    if($.trim($("#cardInfo").val())==""){
		        alert("卡产品介绍不能为空");
		        $("#cardInfo").val("");
		        $("#cardInfo").focus();
		        return false;
		    }
		    return true;
		
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
	 });
	</script>
</html>