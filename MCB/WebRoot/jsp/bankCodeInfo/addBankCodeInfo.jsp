<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增代码信息</title>
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
	<form action="saveBankCodeInfo.html" name="addCodeInfoForm" id="addCodeInfoForm"
		target="result" method="post">
		 
			<table border="0" cellpadding="6" cellspacing="0" align="center">

				<tr>
					<td align="right">代码类别名称： </td>
					<td align="left">
				       <select name="codeTypeId"  style="width:205px;line-height:25px;" id="codeTypeId" >
					     <option selected value="">请选择</option>
				         <c:forEach items="${typelist}" var="type">
					        <option value="${type.codeTypeId}">${type.codeTypeName}</option>
				         </c:forEach>
				       </select>
					   <label style="color: red;">*</label> 
				    </td>
						
				</tr>
				<tr>
					<td align="right">下拉框值：</td> 
					<td align="left"> 
					  <input type="text" id="value" name="value" width="125px" maxlength="5" class="input_txt"/>
					  <label style="color: red;">*</label> 
					</td>
				</tr>
				<tr>
					<td align="right">下拉框内容：</td> 
					<td align="left"> 
					  <input type="text" id="content" name="content" width="125px" maxlength="50" class="input_txt"/>
					  <label style="color: red;">*</label> 
					</td>
				</tr>
				<tr>
					<td align="right">排序号：</td> 
					<td align="left"> 
					  <input type="text" id="sortNo" name="sortNo" width="125px" maxlength="4" class="input_txt"/>
					  <label style="color: red;">*</label> 
					</td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td align="left"><textarea name="remark" onchange="testRemark()"
							onpropertychange="if(value.length>200) value=value.substr(0,200)"
							style="color:#ccc"
							onfocus="if(this.value=='最多可输入200字') {this.value='';this.style.color='#333';}"
							onblur="if(this.value=='') {this.value='最多可输入200字';this.style.color='#ccc';}"
							id="remark" style="width:350px;height:100px;">最多可输入200字</textarea> 
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
	   
		var dg,count=0; var reg=/^[0-9]*$/;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			       
			        if($("#codeTypeId").val()==""){
			           alert("请选择代码类别名称")
			           return;
			        }
			        
			        if($.trim($("#value").val())==""){
			           alert("下拉框值不能为空");
			           $("#value").focus();
			           return;
			        }else if(!reg.test($("#value").val())){
			            alert("下拉框值只能输入数字！");
				    	$("#value").val("");
				    	$("#value").focus();
				    	return false;
			        }
			        if($.trim($("#content").val())==""){
			           alert("下拉框内容不能为空");
			           $("#content").focus();
			           return;
			        }
			        
			        
			        if($.trim($("#sortNo").val())==""){
			           alert("排序号不能为空");
			           $("#sortNo").focus();
			           return;
			        }else if(!reg.test($("#sortNo").val())){
			            alert("排序号只能输入数字！");
				    	$("#sortNo").val("");
				    	$("#sortNo").focus();
				    	return false;
			        }
			        if($("#remark").val()=="最多可输入200字"){
			           $("#remark").val("");
			        }else if($("#remark").val().length>200){
						alert("备注不能大于200个字符,请重新输入！");
						return ;
					}
			        if(count!=0){
			          return;
			        }
				     count=count+1;
		             $("#addCodeInfoForm").ajaxSubmit({
							type : "POST",
							url : "saveBankCodeInfo.html",
							dataType : "text",
							success : function(text) {
								if (text == "1") {
									alert("下拉框值已经存在");
									$("#value").val("");
									$("#value").focus();
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
		});
		

		function testRemark(){
			if($("#remark").val().length>200){
				alert("备注不能大于200个字符,请重新输入！");
				return ;
			}
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