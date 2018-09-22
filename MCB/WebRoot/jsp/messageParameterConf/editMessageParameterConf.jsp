<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:190px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="mssageParameterConfpEidt.html" name="MessageTemplateForm" id="MessageTemplateForm" target="result" method="post"  enctype="multipart/form-data" >
		<%-- <input type="hidden" name="bmpcId" id="bmpcId"  value="${bankMessageParameterConf.bmpcId}"/>
		<input type="hidden" name="bmpcUrl" id="bmpcUrl" value="${bankMessageParameterConf.bmpcUrl}"/>	 --%>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr class="info">
				<th>消息盒子名称:</th>
				<td>
					<input type="text" value="${bankMessageParameterConf.bmpcName}" id="bmpcName" name="bmpcName"class="input_txt" maxlength="150"/>
				</td>
			</tr>
			<tr class="info">
				<th>消息盒子图片:</th>
				<td>
					<input type="file" id="bmpcUrl" name="bmpcUrlfile"  accept="image/*" class="input_txt" maxlength="150"/>
				</td>
				<%-- <td><img src="${path}${bankMessageParameterConf.bmpcUrl}" width="30" height="30"/></td> --%>
			</tr>
			<tr class="info">
				<th>消息编码:</th>
				<td><input type="text" value="${bankMessageParameterConf.bmpcCode}" disabled="disabled" class="input_txt" maxlength="5">
				</td>
			</tr>
			<tr class="info">
				<th>消息盒子类型:</th>
				<td>
				 <select  disabled="disabled" style="width:197px;line-height:25px;" id="bmpcTypeName" >
					     <option selected value="">--请选择--</option>
				         <c:forEach items="${bankMessageTypes}" var="type">
					        <option value="${type.typeNo}" <c:if test="${type.typeNo==bankMessageParameterConf.bmpcType}">selected</c:if>>${type.typeName}</option>
				         </c:forEach>
				</select>
				</td>
			</tr>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
<script type="text/javascript">
	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#bmpcUrl").val()==""){
					alert("请上传图片!");
					$("#bmpcUrl").focus();
					return false;
		    }else{
			    if(!checkImgName($("#bmpcUrl").val())){
				   return false;
				} 
			}
			
				$("#MessageTemplateForm").submit();
			});
		});
		
			
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
</html>	