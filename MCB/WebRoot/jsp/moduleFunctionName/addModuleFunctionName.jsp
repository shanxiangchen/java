<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增广告</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="saveAdvert.html" name="advertForm" id="advertForm" target="result" method="post"  enctype="multipart/form-data">
 	 <div align="center">
 	  <table>
 	    <tr>
 	       <td align="right">广告标题:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="advertTitle" name="advertTitle" maxlength="50"/>
 	       		<label style="color: red;" class="red">*</label><br><br>
 	       </td> 	    
 	    </tr>
 	    <tr>
 	       <td align="right">广告图片:<br><br></td>
 	       <td align="left">
 	       		<input type="file" id="advertPicUrl" name="advertPicUrlFile"  accept="image/*" />
 	       		<label style="color: red;" class="red">*</label><br><br>
 	       </td> 	    
 	    </tr>
 	    <tr>
 	       <td align="right">广告功能模块名称:<br><br></td>
 	       <td align="left">
 	       		<select id="moduleFunctionNo" onchange="home(this)" style="width: 155px;" name="moduleFunctionNo">
				  <option value="请选择">请选择</option>
				  <c:forEach items="${list}"  var="li">
					<option value="${li.moduleFunctionNo}">${li.moduleFunctionName}</option>
				  </c:forEach>
				</select> 
				<label style="color: red;" class="red">*</label>
				<br><br>
 	       </td> 	    
 	    </tr>
 	    <tr >
 	       <td align="right">广告位置:<br><br></td>
 	       <td align="left">
 	       		<select id="advertSeat" style="width: 155px;" name="advertSeat">
				  <option value="">请选择</option>
				  <option value="1">顶部</option> 
				  <option value="2">中部</option>
				  <option value="2.1">中部第一行</option>
				  <option value="2.11">中部第一行左边</option>
				  <option value="2.12">中部第一行右边</option>
				  <option value="2.2">中部第二行</option>
				  <option value="2.3">中部第三行</option>
				  <option value="2.4">中部第四行</option>
				  <option value="2.5">中部第五行</option>
				  <option value="3">启动页</option>
				  <option value="4">底部</option>
				</select>
				<label style="color: red;" class="red isShow">*</label><br><br>
 	       </td> 	     
 	    </tr>
 	    <tr>
 	       <td align="right">客户端类型:<br><br></td>
 	       <td align="left">
 	       		<select id="clientType" style="width: 155px;" name="clientType">
				  <option value="请选择">请选择</option>
				  <option value="iphone">苹果</option>
				  <option value="android">安卓</option>
				</select>
				<label style="color: red;" class="red">*</label><br><br>
 	       </td> 	     
 	    </tr>
 	     <tr>
 	       <td align="right">链接方式:<br><br></td>
 	       <td align="left">
 	       		<select id="linkType" style="width: 155px;" name="linkType" onchange="javascript:getlinkType();">
				  <option value="请选择">请选择</option>
				  <option value="1">内链</option>
				  <option value="2">外链</option>
				</select>
				<label style="color: red;" class="red isShow">*</label><br><br>
 	       </td> 	     
 	    </tr>
 	    <tr>
 	       <td align="right">链接对象名称:<br><br></td>
 	       <td align="left">
 	       		<select id="linkNo"  style="width: 155px;" name="linkNo" disabled="disabled">
				  <option value="请选择">请选择</option>
				  <c:forEach items="${listname}"  var="li">
					<option value="${li.LINKNO}">${li.LINKNAME}</option>
				  </c:forEach>
				</select>
				<label style="color: red;" class="red isShow">*</label><br><br>
 	       </td> 	     
 	    </tr>
 	    <tr>
 	       <td align="right">链接URL:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="advertUrl" disabled="disabled" name="advertUrl" maxlength="100"/><br><br>
 	       </td> 	     
 	    </tr>
 	     <tr>
 	       <td align="right">链接参数:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="advertParam" disabled="disabled" name="advertParam" maxlength="100" placeholder="adverId=123,adverNo=32432"/><br><br>
 	       </td> 	     
 	    </tr>
	  </table>
 	</div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var dg;
		var count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			    if(validate()){
			       if(count==0){
				    count=count+1;
					$("#advertForm").submit();
				   }
			    }
			 
		 	});
		});
		
		 function getlinkType(){
			var linkType = $("#linkType").val();
			if(linkType == 1){
				$("#advertUrl").attr("disabled",true);
				$("#advertParam").attr("disabled",false);
				$("#linkNo").attr("disabled",false);
			}else if(linkType==2){
				$("#advertUrl").attr("disabled",false);
				$("#advertParam").attr("disabled",false);
				$("#linkNo").attr("disabled",true);
				$("#linkNo").val("请选择");
			}else if(linkType == "请选择"){
				$("#advertUrl").attr("disabled",true);
				$("#advertParam").attr("disabled",true);
				$("#linkNo").attr("disabled",true);
				$("#linkNo").val("请选择");
			}
		 }
		
		function validate(){
		     var reg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
		     var clientType = $("#moduleFunctionNo").val();
		    if($.trim($("#advertTitle").val())==""){
				alert("请输入广告标题!");
				$("#advertTitle").focus();
				return false;
			}
			if($("#advertPicUrl").val()==""){
				if($("#advertPicUrl").val()==""){
					alert("请上传图片!");
					$("#advertPicUrl").focus();
					return false;
				} 
		    }else{
			   	var start=$("#advertPicUrl").val().lastIndexOf("\\");
				var end=$("#advertPicUrl").val().lastIndexOf(".");
				var name=$("#advertPicUrl").val().substring(start+1,end);
			    if(reg.test(name)){
				   alert("图片名称不能包含中文字符!");
				   return false;
				} 
			}
			if($("#moduleFunctionNo").val()=="请选择"){
				alert("请选择广告功能模块名称!");
				$("#moduleFunctionNo").focus();
				return false;
			} 
			
			if($("#advertSeat").val()=="请选择" && clientType != '15'){
				alert("请选择广告位置!");
				$("#advertSeat").focus();
				return false;
			}
			
			if($("#clientType").val()=="请选择" && clientType != '15'){
				alert("请选择客户端类型!");
				$("#clientType").focus();
				return false;
			}
			
			if($("#linkType").val()=="请选择" && clientType != '15'){
			    alert("请选择链接方式!");
				$("#linkType").focus();
				return false;
			}
			
			if($("#linkType").val()=="1"){
			   if($("#linkNo").val()=="请选择" && clientType != '15'){
			        alert("请选择链接对象名称！");
		            $("#advertParam").focus();
				    return false;        
			   }
			}else if($("#linkType").val()=="2"){
				if($.trim($("#advertUrl").val())==""){
					alert("请输入链接");
					return false;
				}
			}
		    return true;
		}
		
		function home(obj){
			if(obj.value=="15"){
				$("#linkType,#advertSeat,#clientType,#advertUrl,#linkNo,#advertParam,#shopId,#activityNo").attr("disabled",true);
				$(".isShow").hide();
			}else{
				$("#linkType,#advertSeat,#clientType,#advertUrl,#linkNo,#advertParam,#shopId,#activityNo").attr("disabled",false);
				$(".isShow").show();
			}
			if(obj.value=="3"){	
				$("#advertSeat").find("option[value='1']").attr("selected",true);
				$("#advertSeat").attr("disabled",true);
			}	
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
	</script>
</body>
</html>