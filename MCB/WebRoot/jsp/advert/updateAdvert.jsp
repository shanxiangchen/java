<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改广告</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="updateOkAdvert.html" name="advertForm" id="advertForm" target="result" method="post"  enctype="multipart/form-data">
 	  <div align="center">
 		<table>
 		<c:forEach items="${list}" var="list">
 		 <c:forEach items="${list.advert}" var="li">
 		   <input type="hidden" name="advertId" id="advertId" value="${li.advertId}" /><br><br>
 		  
 		    <tr>
	 	      <td align="right">广告id:<br><br></td>
	 	      <td align="left">
	 	      		<input type="text" name="advertId" id="advertId" value="${li.advertId}" disabled="disabled"/><br><br>
	 	      </td> 
	 	    </tr>
	 	    <tr>
	 	       <td align="right">广告标题:<br><br></td>
	 	       <td align="left">
	 	       		<input type="text" id="advertTitle" name="advertTitle" value="${li.advertTitle}" maxlength="50"/>
	 	       		<label style="color: red;" class="red">*</label><br><br>
	 	       </td> 	    
	 	    </tr>
	 	    
	 	    <tr>
	 	       <td align="right">广告图片:<br><br></td>
	 	       <td align="left">
	 	       		<input type="file" id="advertPicUrl" name="advertPicUrlFile"  accept="image/*" /><br><br>
	 	       </td> 
	 	       <td>
			  	    <img src="${path}${li.advertPicUrl}" width="30" height="30" alt="广告图片"/>
			  	    <input type="hidden" id="url" value="${li.advertPicUrl}" name="url"/>
			   </td>  
	 	    </tr>
	 	    <tr>
	 	       <td align="right">广告功能模块名称:<br><br></td>
	 	       <td align="left">
	 	       		<select id="moduleFunctionNo" onchange="home(this)" style="width: 155px;" name="moduleFunctionNo">
	 	       		  <option value="请选择">请选择</option>
	 	       		  <c:forEach items="${moduleFunctionNameList}"  var="moduleList">
						<option value="${moduleList.moduleFunctionNo}" <c:if test="${moduleList.moduleFunctionNo==li.moduleFunctionNo}">selected</c:if>>${moduleList.moduleFunctionName}</option>
				 	  </c:forEach>
					</select>
					<label style="color: red;" class="red">*</label><br><br>
	 	       </td> 	    
	 	    </tr>
	 	    <tr>
	 	       <td align="right">广告位置:<br><br></td>
	 	       <td align="left">
	 	       		<select id="advertSeat" style="width: 155px;" name="advertSeat">
					  <option value="请选择">请选择</option>
					  <option value="1" <c:if test="${li.advertSeat=='1'}">selected</c:if>>顶部</option>
			  		  <option value="2" <c:if test="${li.advertSeat=='2'}">selected</c:if>>中部</option>
			  		  <option value="2.1" <c:if test="${li.advertSeat=='2.1'}">selected</c:if>>中部第一行</option>
					  <option value="2.11" <c:if test="${li.advertSeat=='2.11'}">selected</c:if>>中部第一行左边</option>
					  <option value="2.12" <c:if test="${li.advertSeat=='2.12'}">selected</c:if>>中部第一行右边</option>
					  <option value="2.2" <c:if test="${li.advertSeat=='2.2'}">selected</c:if>>中部第二行</option>
					  <option value="2.3" <c:if test="${li.advertSeat=='2.3'}">selected</c:if>>中部第三行</option>
					  <option value="2.4" <c:if test="${li.advertSeat=='2.4'}">selected</c:if>>中部第四行</option>
					  <option value="2.5" <c:if test="${li.advertSeat=='2.5'}">selected</c:if>>中部第五行</option>
					  <option value="3" <c:if test="${li.advertSeat=='3'}">selected</c:if>>启动页</option>
				  	  <option value="4" <c:if test="${li.advertSeat=='4'}">selected</c:if>>底部</option>
					</select>
					<label style="color: red;" class="red">*</label><br><br>
	 	       </td> 	     
	 	    </tr>
	 	    <tr>
	 	       <td align="right">客户端类型:<br><br></td>
	 	       <td align="left">
	 	       		<select id="clientType" style="width: 155px;" name="clientType">
					  <option value="请选择">请选择</option>
					  <option value="iphone" <c:if test="${li.clientType=='iphone'}">selected</c:if>>苹果</option>
					  <option value="android" <c:if test="${li.clientType=='android'}">selected</c:if>>安卓</option>
					</select>
					<label style="color: red;" class="red">*</label><br><br>
	 	       </td> 	     
 	   	   </tr>
 	   	    <tr>
 	       <td align="right">链接方式:<br><br></td>
 	       <td align="left">
 	       		<select id="linkType" style="width: 155px;" name="linkType" onchange="javascript:getlinkType();">
				  <option value="请选择">请选择</option>
				  <option <c:if test="${li.linkType=='1'}">selected</c:if> value="1">内链</option>
				  <option <c:if test="${li.linkType=='2'}">selected</c:if> value="2">外链</option>
				</select>
				<label style="color: red;" class="red">*</label><br><br>
 	       </td> 	     
 	    </tr>
 	    <tr>
 	       <td align="right">链接对象名称:<br><br></td>
 	       <td align="left">
 	       		<select id="linkNo" style="width: 155px;" name="linkNo">
				  <option value="请选择">请选择</option>
				  <c:forEach items="${listname}"  var="liname">
						<option value="${liname.LINKNO}" <c:if test="${liname.LINKNO==li.linkNo}">selected</c:if>>${liname.LINKNAME}</option>
				  </c:forEach>
				   
				</select>
				<label style="color: red;" class="red">*</label><br><br>
 	       </td> 	     
 	      </tr>
 	   	    <tr>
 	       <td align="right">链接URL:<br><br></td>
 	       <td align="left">
 	       		<input type="hidden" id="advertUrl1" name="advertUrl1" value="${li.advertUrl}" maxlength="480"/>
 	       		<input type="text" id="advertUrl" name="advertUrl" value="${li.advertUrl}" maxlength="480"/><br><br>
 	       </td> 	     
 	      </tr>
 	     <tr>
 	       <td align="right">链接参数:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="advertParam" name="advertParam" value="${li.advertParam}" maxlength="180" placeholder="adverId=123,adverNo=32432"/><br><br>
 	       </td> 	     
 	      </tr>
 		 </c:forEach>
 	    </c:forEach>
 	   </table>
 	  </div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/validate.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var dg;
		var count=0;
		$(document).ready(function(){
			
			testdisabled();
			
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
		
		function testdisabled(){
			var linkType = $("#linkType").val();
			if(linkType == 1){
				$("#advertUrl").attr("disabled",true);
				$("#advertParam").attr("disabled",false);
				$("#linkNo").attr("disabled",false);
				$("#advertUrl").val("");
			}else if(linkType==2){
				$("#advertUrl").attr("disabled",false);
				$("#advertParam").attr("disabled",false);
				$("#linkNo").attr("disabled",true);
			}else if(linkType == "请选择"){
				$("#advertUrl").attr("disabled",true);
				$("#advertParam").attr("disabled",true);
				$("#linkNo").attr("disabled",true);
			}
		}
		
		function getlinkType(){
			var linkType = $("#linkType").val();
			if(linkType == 1){
				if($("#advertUrl").val()!=""){
					$("#advertUrl1").val($("#advertUrl").val());
					$("#advertUrl").val("");
				}
				$("#advertUrl").attr("disabled",true);
				$("#advertParam").attr("disabled",false);
				$("#linkNo").attr("disabled",false);
			}else if(linkType==2){
				if($("#advertUrl1").val()==""){
					$("#advertUrl").val("http://");
				}else{
					$("#advertUrl").val($("#advertUrl1").val());
				}
				$("#advertUrl").attr("disabled",false);
				$("#advertParam").attr("disabled",false);
				$("#linkNo").attr("disabled",true);
				$("#linkNo").val("请选择");
			}else if(linkType == "请选择"){
				if($("#advertUrl").val()!=""){
					$("#advertUrl1").val($("#advertUrl").val());
					$("#advertUrl").val("");
				}
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
			if($("#advertPicUrl").val()!=""){
			   	//var start=$("#advertPicUrl").val().lastIndexOf("\\");
				//var end=$("#advertPicUrl").val().lastIndexOf(".");
				//var name=$("#advertPicUrl").val().substring(start+1,end);
			    if(!checkImgName($("#advertPicUrl").val())){
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