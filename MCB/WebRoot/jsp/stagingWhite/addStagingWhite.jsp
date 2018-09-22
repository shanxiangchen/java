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
	<form  action="saveStagesWhite.html" enctype="multipart/form-data" name="stagesWhiteForm" id="stagesWhiteForm" target="result"  method="post">
	<table border="0" cellpadding="0" cellspacing="0" align="center">
 	<tr class="info">
 		<th>手机号:</th>
			<td><input type="text" name="listPhone" maxlength="11" id="listPhone" class="input_txt"/>
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
	             $("#stagesWhiteForm").ajaxSubmit({
						type : "POST",
						url : "saveStagesWhite.html",
						dataType : "text",
						success : function(text) {
							if(text=="1"){
								alert("手机号已经存在");
								$("#listPhone").val("");
							    $("#listPhone").focus();
								count=0;
							}else{
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
		    var reg=/^[0-9]*$/;
		    if($.trim($("#listPhone").val())==""){
		       alert("手机号不能为空");
		       $("#listPhone").val("");
		       $("#listPhone").focus();
		       return false;
		    }else{
		    	if(!reg.test($("#listPhone").val())){
		    		alert("手机号只能输入数字");
		    		$("#listPhone").val("");
				    $("#listPhone").focus();
		    		return false;
		    	}
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