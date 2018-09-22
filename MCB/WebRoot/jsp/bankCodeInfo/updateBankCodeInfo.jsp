<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑代码信息</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="updateBankCodeInfo.html" name="updateCodeInfoForm" id="updateCodeInfoForm"
		target="result" method="post">
		     <input type="hidden" id="codeInfoId" name="codeInfoId" value="${bankCodeInfo.codeInfoId }"/>
			<table border="0" cellpadding="6" cellspacing="0">

				<tr>
					<td align="right">代码类别英文名称： </td>
					<td align="left">
					    <input type="text"  width="125px"  disabled="disabled" value="${bankCodeInfo.codeTypeId }" class="input_txt"/>
				        
				    </td>
						
				</tr>
				<tr>
					<td align="right">下拉框值：</td> 
					<td align="left"> 
					  <input type="text" id="value" name="value" disabled="disabled" value="${bankCodeInfo.value }" width="125px" maxlength="5" class="input_txt"/>
					  <label style="color: red;">*</label> 
					</td>
				</tr>
				<tr>
					<td align="right">下拉框内容：</td> 
					<td align="left"> 
					  <input type="text" id="content" name="content" value="${bankCodeInfo.content }" width="125px" maxlength="50" class="input_txt"/>
					  <label style="color: red;">*</label> 
					</td>
				</tr>
				<tr>
					<td align="right">排序号：</td> 
					<td align="left"> 
					  <input type="text" id="sortNo" name="sortNo" disabled="disabled" value="${bankCodeInfo.sortNo }" width="125px" maxlength="4" class="input_txt"/>
					  <label style="color: red;">*</label> 
					</td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td ><textarea name="remark" onchange="testRemark()"
							onpropertychange="if(value.length>200) value=value.substr(0,200)"
							onfocus="if(this.value=='最多可输入200字符') {this.value='';this.style.color='#333';}"
							onblur="if(this.value=='') {this.value='最多可输入200字符';this.style.color='#ccc';}"
							id="remark" style="width:350px;height:100px;">${bankCodeInfo.remark}</textarea> 
					</td>
				</tr>

			</table>
		 
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	   
		var dg,count=0; 
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			
			        
			        if($.trim($("#content").val())==""){
			           alert("下拉框内容不能为空");
			           $("#content").focus();
			           return;
			        }
			        if($("#remark").val()=="最多可输入200字符"){
			           $("#remark").val("");
			        }else if($("#remark").val().length>200){
						alert("备注不能大于200个字符,请重新输入！");
						return ;
					}
			        if(count!=0){
			          return;
			        }
			        
			        /* var remark = document.getElementById("remark").innerHTML; 
					if(remark.length > 200 ){
						alert("备注不能大于200个字符,请重新输入！");
						return ;
					} */
			        
				     count=count+1;
		             $("#updateCodeInfoForm").submit();

			});
		});

		 
		function success() {
			if (dg.curWin.document.forms[0]) {
				dg.curWin.document.forms[0].action = dg.curWin.location + "";
				dg.curWin.document.forms[0].submit();
			} else {
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		function testRemark(){
			if($("#remark").val().length>200){
				alert("备注不能大于200个字符,请重新输入！");
				return ;}
			/* var remark = document.getElementById("remark").innerHTML; 
			if(remark.length > 200 ){
				alert("备注不能大于200个字符,请重新输入！");
				return ;
			} */
		}
		
	</script>
</body>
</html>