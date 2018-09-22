<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:190px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:70px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="updateSaveBankInnerLink.html" name="BankInnerLink" id="BankInnerLink" target="result" method="post"  enctype="multipart/form-data" >
		<input type="hidden" value="${bankInnerLink.linkId}" name="linkId" id="linkId"/>
		<table>
		<tr>	
			<th>内链名称&nbsp;：&nbsp;</th>
		<td>
			<input type="text" name="linkName" value="${bankInnerLink.linkName }" class="input_txt"  id="linkName"/>
			<!-- <input type="hidden" id="linkFlag" value="2"/> -->
		    <label style="color: red;" class="red" >*</label>
		</td>
		</tr>
		<tr><th>
		内链编号&nbsp;：&nbsp;</th><td><input type="text" name="linkNo" value="${bankInnerLink.linkNo }"  class="input_txt" id="linkNo" onchange="javascript:testLinkNo()" disabled="disabled"/>
				<label style="color: red;" class="red" >*</label>
		</td>
		</tr>
		<tr><th>
		内链URL&nbsp;：&nbsp;</th><td><input type="text" name="linkURL" value="${bankInnerLink.linkURL }"  class="input_txt"  id="linkURL"/>
				<label style="color: red;" class="red" >*</label></td>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
</body>
<script type="text/javascript">
	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if(!validate()){
					return;
				}
				$("#BankInnerLink").submit();
			});
		});
	
		
	function validate(){
		var stars=/[\u4e00-\u9fa5]$/;
		if($("#linkName").val()==""){
			alert("内链名称不能为空！");
			return false;
		}
		 if(!stars.test($("#linkName").val())){
			alert("内链名称只能输入中文!");
			return false;
		} 
		
		var linkNo=/^[0-9]+$/;
		if($("#linkNo").val() == ""){
			alert("内链编号不能为空！");
			return false;
		}if(!linkNo.test($("#linkNo").val())){
			alert("内链编号请输入整数!");
			return false;
		} 
		
		if($("#linkURL").val() == ""){
			alert("内链URL不能为空！");
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
</script>
</html>	