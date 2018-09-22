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
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align:right;width:115px; color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="updateRightsFiled.html" enctype="multipart/form-data" name="updateRightsFiledForm" id="updateRightsFiledForm" target="result"  method="post">
	   <input type="hidden" name="filedId" value="${bankPrRightsFiled.filedId}"/>
	   <input type="hidden" name="filedAbcName" value="${bankPrRightsFiled.filedAbcName}"/>
	   <input type="hidden" name="filedFormType" value="${bankPrRightsFiled.filedFormType}"/>
	   <input type="hidden" name="filedIsShow" value="${bankPrRightsFiled.filedIsShow}"/>
	   <table border="0" cellpadding="0" cellspacing="0">
	    <tr class="info">
		    <th>字段中文名称:</th>
			<td><input type="text" name="filedName" maxlength="50" id="filedName" value="${bankPrRightsFiled.filedName}" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
		 
		    <th>字段英文名称:</th>
			<td><input type="text" name="filedAbcName" disabled="disabled" value="${bankPrRightsFiled.filedAbcName}"   maxlength="50" id="filedAbcName" class="input_txt"/>
			<label style="color: red;" class="red">*</label></td>
		</tr>	  
		<tr class="info">
		    <th>表单类型:</th>
			<td>
			<select name="filedFormType" disabled="disabled" id="filedFormType" style="width:205px;">
				<option value="">请选择</option>
				<option value="0" <c:if test="${bankPrRightsFiled.filedFormType=='0'}">selected</c:if>>反显</option>
				<option value="1" <c:if test="${bankPrRightsFiled.filedFormType=='1'}">selected</c:if>>日期</option>
				<option value="2" <c:if test="${bankPrRightsFiled.filedFormType=='2'}">selected</c:if>>城市</option>
				<option value="3" <c:if test="${bankPrRightsFiled.filedFormType=='3'}">selected</c:if>>服务</option>
				<option value="4" <c:if test="${bankPrRightsFiled.filedFormType=='4'}">selected</c:if>>计数</option>
				<option value="5" <c:if test="${bankPrRightsFiled.filedFormType=='5'}">selected</c:if>>单选</option>
				<option value="6" <c:if test="${bankPrRightsFiled.filedFormType=='6'}">selected</c:if>>文本</option>
				<option value="7" <c:if test="${bankPrRightsFiled.filedFormType=='7'}">selected</c:if>>提示</option>
			</select>
			<label style="color: red;" class="red">*</label></td>
		</tr>	  
		 </table>
		</form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
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
	             $("#updateRightsFiledForm").ajaxSubmit({
						type : "POST",
						url : "updateRightsFiled.html",
						dataType : "text",
						success : function(text) {
							if(text=="success"){
							  success();
							}else{
							  alert("保存失败");
							  success();
							}
						},
						error : function() {
							alert("保存失败！");
							success();
						}
				 });
				 
		});
		
		
		function validata(){
		     if($.trim($("#filedName").val())==""){
		       alert("字段中文名称不能为空");
		       $("#filedName").val("");
		       $("#filedName").focus();
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