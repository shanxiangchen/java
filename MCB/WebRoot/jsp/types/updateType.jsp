<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:15px;font-size: 10px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="saver.html" name="typeForm" id="typeForm" 
	target="result" method="post" onsubmit="return checkInfo();">
		<input type="hidden" name="oddsshoptypeid" id="oddsshoptypeid" value="${type.oddsshoptypeid }"/>
	<table border="0" cellpadding="0" cellspacing="0">
	
		<tr class="info">
			<th>编号:</th>
			<td><input type="text" name="oddsshoptypeid" id="oddsshoptypeid" disabled="disabled" class="input_txt" value="${type.oddsshoptypeid }"/></td>
		</tr>
		
		
		 <tr class="info">
		<th>名称:</th>
		<td><input type="text" id="oddsshoptype" name="oddsshoptype" class="input_txt" value="${type.oddsshoptype }"> 
		<label style="color: red;" class="red">*</label></td>
		<tr>
		
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	
	
	<script type="text/javascript">
	
	function checkInfo(){
			if($("#oddsshoptype").val()=="请选择"){
				alert("请选择类型!");
				$("#oddsshoptype").focus();
				return false;
			}
		
			return true;
		}
			
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#typeForm").submit();
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
		
		function failed(){
			alert("新增失败！");
		}
		
	</script>
	
</body>
</html>