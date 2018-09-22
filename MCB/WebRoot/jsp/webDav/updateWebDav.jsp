<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>webDav服务器</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="updateWebDav.html" name="webDavForm" id="webDavForm" target="result" method="post">
		<div align="center"> 
		    <input  type="hidden" name="webDavNum" id="webDavNum" value="${webDav.webDavNum}"/>
			<table border="0" cellpadding="6" cellspacing="0">
				<tr>
					<td align="right">服务器编号： </td>
					<td align="left"><input type="text"  disabled="disabled"  value="${webDav.webDavId}" class="input_txt"/>
					<input type="hidden" id="webDavId"    name="webDavId" value="${webDav.webDavId}" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr>
				<tr>
					<td align="right">服务器IP： </td>
					<td align="left"><input type="text" id="webDavHostName" onkeyup="this.value=this.value.replace(/[^\w.]/g,'');" name="webDavHostName" value="${webDav.webDavHostName}"maxlength="15" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr>
				<tr>
					<td align="right">服务器端口号： </td>
					<td align="left"><input type="text" id="webDavPort" onkeyup="this.value=this.value.replace(/[^\w]/g,'');" name="webDavPort" value="${webDav.webDavPort }" maxlength="5" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr> 
				<tr>
					<td align="right">用户名： </td>
					<td align="left"><input type="text" id="webDavUserName" onkeyup="this.value=this.value.replace(/[^\w]/g,'');"  name="webDavUserName" value="${webDav.webDavUserName }" maxlength="10" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr> 
				<tr>
					<td align="right">服务器密码： </td>
					<td align="left"><input type="password" id="webDavPassword" onkeyup="this.value=this.value.replace(/[^\w@#$%&*._]/g,'');" style="width: 138px;" name="webDavPassword" value="${webDav.webDavPassword }" maxlength="10" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr>
				<tr>
					<td align="right">再次输入密码： </td>
					<td align="left"><input type="password" id="isOkPwd" onkeyup="this.value=this.value.replace(/[^\w@#$%&*._]/g,'');" style="width: 138px;" value="${webDav.webDavPassword }" name="isOkPwd" maxlength="10" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr>    
				<tr>
					<td align="right">上传路径： </td>
					<td align="left"><input type="text" id="webDavUrl"  onkeyup="this.value=this.value.replace(/[^\w]/g,'');" value="${webDav.webDavUrl }" name="webDavUrl" maxlength="10" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr> 
				<tr>
					<td align="right">访问路径： </td>
					<td align="left"><input type="text" id="webDavLookUrl"  onkeyup="this.value=this.value.replace(/[^\w]/g,'');" value="${webDav.webDavLookUrl }"name="webDavLookUrl" maxlength="10" class="input_txt"/>
					<label style="color: red;">*</label> </td>
				</tr> 
				<tr>
					<td align="right">服务器类型： </td>
					<td align="left">
                        <select style="width: 145px;" disabled >
						   <option value="">请选择</option>
						   <option value="1" <c:if test="${webDav.webDavNum=='1'}">selected</c:if>>主机</option>
			  		       <option value="2" <c:if test="${webDav.webDavNum=='2'}">selected</c:if>>备机</option>
				        </select>
						<label style="color: red;">*</label>
				    </td>
				</tr> 
			</table>
	   </div>	 
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	   
		var dg,count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			       if(!validata()){
			         return;
			       }
			       if(count==0){
				     count=count+1;
		             $("#webDavForm").submit();

					}
				 

			});
		});
		
		function validata(){
		    var reg=/^[0-9]*$/; 
		    if($.trim($("#webDavId").val())==""){
		        alert("服务器编号不能为空");
		        $("#webDavId").focus();
				return false;
		    }
		    if($.trim($("#webDavHostName").val())==""){
		        alert("服务器IP不能为空");
		        $("#webDavHostName").focus();
		        return false;
		    }
		     
		    if($.trim($("#webDavPort").val())==""){
		       alert("服务器端口号不能为空");
		       $("#webDavPort").focus();
		       return false;
		    }else{
		        if(!reg.test($("#webDavPort").val())){
		           alert("端口号请输入数字！");
		           $("#webDavPort").val("");
		           $("#webDavPort").focus();
		           return false;
		        }
		    }
		    if($.trim($("#webDavUserName").val())==""){
		       alert("用户名不能为空");
		       $("#webDavUserName").focus();
		       return false;
		    }
		   
		    var reg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
		    if($.trim($("#webDavPassword").val())==""){
		       alert("用户密码不能为空");
		       $("#webDavPassword").focus();
		       return false;
		    }else if(reg.test($("#webDavPassword").val())){
		    	alert("密码不能使用中文字符 ");
		    	$("#webDavPassword").val("");
		    	return false;
		    }
		    if($.trim($("#isOkPwd").val())==""){
		       alert("请确认密码");
		       $("#isOkPwd").focus();
		       return false;
		    }else if(reg.test($("#isOkPwd").val())){
		    	alert("密码不能使用中文字符 ");
		    	$("#isOkPwd").val("");
		    	return false;
		    }
		    
		    
		    if($.trim($("#webDavUrl").val())==""){
		       alert("上传路径不能为空");
		       $("#webDavUrl").focus();
		       return false;
		    }
		    
		    if($.trim($("#webDavLookUrl").val())==""){
		       alert("访问路径不能为空");
		       $("#webDavLookUrl").focus();
		       return false;
		    }
		    if($("#webDavNum").val()==""){
		      alert("请选择服务器标识");
		      return false;
		    }
		    
		    if($("#webDavPassword").val()!=$("#isOkPwd").val()){
		       alert("两次输入的密码不一致");
		       $("#webDavPassword").val("");
		       $("#isOkPwd").val("");
		        $("#webDavPassword").focus();
		       return false;
		    }
		     return true;
		
		}
 
		function success() {
			if (dg.curWin.document.forms[0]) {
				dg.curWin.document.forms[0].action = dg.curWin.location + "";
				dg.curWin.document.forms[0].submit();
			} else {
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
	</script>
</body>
</html>