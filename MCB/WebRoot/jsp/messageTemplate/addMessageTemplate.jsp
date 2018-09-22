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
.info th{text-align: right;width:70px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="saveBankMessageTemplate.html" name="MessageTemplateForm" id="MessageTemplateForm" target="result" method="post"  enctype="multipart/form-data" >
		<input name="inGoAndSeeLink" id="inLink" type="hidden"/>
		<input name="outGoAndSeeLink" id="outLink" type="hidden"/>
		<input name="smallTypeNo" id="code" type="hidden"/>
		
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>广告图片:</th>
			<td>
				<input type="file" id="advertisingImg" name="advertisingImgFile"  accept="image/*" class="input_txt" maxlength="150"/>
			</td>
			<th>链接方式:</th>
			<td>
				<select id="LinkWay" onchange="linkWay()" name="LinkWay" class="input_txt">
					<option value="">--请选择--</option>
					<option value="in">内链</option>
					<option value="out">外链</option>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>内链去看看:</th>
			<td >
			<select id="inGoAndSeeLink"  disabled="disabled" onchange="inlink()"  class="input_txt">
				<option value="">--请选择--</option>
				<c:forEach items="${listModel}" var="listModel">
					<option value="${listModel.linkNo}">${listModel.linkName}</option>
				</c:forEach>

			</select>
			</td>
			<th>外链去看看:</th>
			<td>
				<input type="text" id="outGoAndSeeLink" onblur="out()" disabled="disabled" class="input_txt" maxlength="150"/>
			</td>
		</tr>
		<tr class="info">
			<th>消息盒子:</th>
			<td>
    		<select name="typeNo" id="typeNo" class="input_txt">
				<option selected value="请选择">--请选择--</option>
				<c:forEach items="${list}" var="li">
				<option value="${li.bmpcCode }">${li.bmpcName }</option>
				</c:forEach>
			</select>
			<th>消息标题:</th>
			<td>
			<input name="messageTitle" id="messageTitle" type="text" class="input_txt" />	
			</td>
		</tr>
		<tr class="info">
			<th>文字内容:</th>
			<td colspan="3">
				<textarea rows="5" cols="60" id="writtenContent" name="writtenContent"></textarea>
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
				if(!checkImgName($("#advertisingImg").val())){
					return false;
				}
				$("#MessageTemplateForm").submit();
			});
		});
		
	function linkWay(){
				var sel_obj = document.getElementById("LinkWay");
				var index = sel_obj.selectedIndex;
 				var value=sel_obj.options[index].value;
		 		 if(value=="in"){
		 			$("#inGoAndSeeLink").removeAttr("disabled"); //移除disabled属性 
		 			$("#outGoAndSeeLink").val("").attr('disabled',"true");//添加disabled属性 
		 			$("#outLink").val("");
				}
				 if(value=="out"){
					$("#outGoAndSeeLink").removeAttr("disabled"); //移除disabled属性 
					$("#inGoAndSeeLink").val("").attr('disabled',"true");//添加disabled属性 
					$("#inLink").val("");
				}
				if(value==""){
					$("#inGoAndSeeLink").attr('disabled',"true");//添加disabled属性 
					$("#outGoAndSeeLink").attr('disabled',"true");//添加disabled属性 
				}
			}
			
			function inlink(){
				var sel_obj = document.getElementById("inGoAndSeeLink");
					var index = sel_obj.selectedIndex;
	 				var value=sel_obj.options[index].value;
	 				$("#inLink").val(value);
			}
			function out(){
				var outlink=$("#outGoAndSeeLink").val();
				$("#outLink").val(outlink);	
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