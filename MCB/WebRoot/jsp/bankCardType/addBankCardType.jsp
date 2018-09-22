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
.info th{text-align:right;width:115px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="saveCardType.html" enctype="multipart/form-data" name="CardTypeForm" id="CardTypeForm" target="result"  method="post">
	<table border="0" cellpadding="0" cellspacing="0">
 	<tr class="info">
		<th>卡产品种类名称:</th>
			<td><input type="text" name="typeName" id="typeName" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
			 <th>卡种类图片:</th>
			<td>
			<input type="file" name="typePicFile" id="typePicName"   style="width:205px;" accept="image/*" />
			<label style="color: red;" class="red">*</label>
			</td>
	 </tr>		
     <tr class="info">
		<th>所属类型:</th>
		<td colspan="3">
		 <c:forEach items="${listType}" var="li">
				 <input type="checkbox"   name="cardOfType"  value="${li.VALUE}" >${li.CONTENT}
		  </c:forEach>
		  <label style="color: red;" class="red">*</label>
		 </td>
	 </tr>
	 <tr class="info">
		<th>卡产品种类介绍:</th>
		<td colspan="3">
		<textarea rows="8" cols="63" name="typeInfo" id="typeInfo"></textarea>
		<label style="color: red;" class="red">*</label></td>
	 </tr>
		 
		</form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
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
	             $("#CardTypeForm").ajaxSubmit({
						type : "POST",
						url : "saveCardType.html",
						dataType : "text",
						success : function(text) {
							  success();
							 
						},
						error : function() {
							alert("保存失败！");
							success();
						}
				 });
				 
			});
		
		function validata(){ 
 
		     if($.trim($("#typeName").val())==""){
		       alert("卡产品种类名称不能为空");
		       $("#typeName").val("");
		       $("#typeName").focus();
		       return false;
		     }
		    
		     var count=0;
		     $("input[type='checkbox']").each(function(){
		    	 if($(this).attr("checked")){
		    		 count++;
		    	 }
		     });
		     if(count==0){
		    	 alert("请选择所属类型");
		    	 return false;
		     } 
		    
		     if($("#typePicName").val()==""){
		         alert("请选择卡种类图片");
		         return false;
		     }else{
		        //var strs=$("#typePicName").val().split(".");
		        //var fileType=strs[strs.length-1].toLowerCase();
		        //if(fileType!="gif"&&fileType!="jpg"&&fileType!="jpeg"&&fileType!="bmp"&&
		        //fileType!="png"){
		        //    alert("图片类型必须是.gif,jpeg,jpg,bmp,png中的一种");
				//	 $("#typePicName").val("");
				//	 return false;
				//}
				if(!checkImgName($("#typePicName").val())){
				   return false;
				}
		     }
		     
		     if($("#typeInfo").val()==""){
		       alert("卡产品种类介绍不能为空");
		       $("#typeInfo").val("");
		       $("#typeInfo").focus();
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