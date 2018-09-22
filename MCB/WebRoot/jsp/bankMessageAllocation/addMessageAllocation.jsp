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
.info th{text-align:right;width:110px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
.none{display: none;}
#preview { position:absolute; bottom：30px; width:10%; height:10%; }
</style>
</head>
<body>
	<form  action="save_messageAllocation.html" enctype="multipart/form-data" name="addMessageAllocationForm" id="addMessageAllocationForm" target="result"  method="post">
	   <table border="0" cellpadding="0" cellspacing="0" id="tableId">
	  </table>  
	  <div id="aa">
	     <table border="0" cellpadding="0" cellspacing="0">
		     <tr class="info">
		     	<th>配置名称：</th>
		     	<td></td>
		     	<td><input type="text" value="" name="allocationName" id="allocationName" placeholder="请输入配置名称" class="input_txt"/>
		     	<label style="color: red;" class="red">*</label>
		     	</td>
	 		 
	 		 </tr>
		     <tr class="info">
		     	<th>code码：</th>
		     	<td></td>
		     	<td><input type="text" vlaue="" name="messageCode" id="messageCode" maxlength="6" placeholder="请输入6位数字以内的code码" class="input_txt" onchange="javascript:testmessageCode()"/>
		     		<span style="color:red;" id="messageworn" value=""></span>
		     		<label style="color: red;" class="red">*</label>
		     	</td>
		     	
	 		 </tr>
	 		  <tr class="info">
	 		  	<th>允许推送次数：</th>
	 		  	<td></td>
	 		  	<td><input type="text" value="" name="times" id="times" class="input_txt"/></td>
	 		  </tr>
	 		  <tr class="info">
	 		  	<th>推送间隔(s):</th>
	 		  	<td></td>
	 		  	<td><input type="text" value="" name="intervalTime" id="intervalTime" class="input_txt" placeholder="以秒计算"/></td>
	 		  </tr>
	     </table>
	  </div>  
    </form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/main.js" ></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			   	if(!validata()){
			   		return;
			   	}
			   	
			   	$("#addMessageAllocationForm").ajaxSubmit({
					type : "POST",
					url : "save_messageAllocation.html",
					dataType : "text",
					success : function(text) {
						if (text == "1") {
							alert("数据传输错误");
							/* $("#value").val("");
							$("#value").focus(); */
						}else if(text == "2"){
							alert("code编码重复！");
						}else{
							success();
						}
					},
					error : function() {
						alert("保存失败！");
						success();
					}
			  });
			   	/* dg.curWin.location.reload(); */
			});
		});
		
		
		/* 匹配数字 */
		var compareNum = /^[0-9]*$/; 
		function validata(){
			var allocationName = $.trim($("#allocationName").val());
			var messageCode = $.trim($("#messageCode").val());
			var times = $.trim($("#times").val());
			var intervalTime = $.trim($("#intervalTime").val());
			if(allocationName == null || allocationName == ''){
				alert("请输入配置名称！");
				return false;
			}
			if(messageCode == null || messageCode == ''){
				alert("请输入6位数字以内的code码！");
				return false;
			}
			if(messageCode.length > 6 || !compareNum.exec(messageCode)){
				alert("请输入6位数字以内的code码！");
				return false;
			}
			if(times != null && times != ''&& !(compareNum.exec(times))){
				alert("次数只能为数字或为空！");
				return false;
			}
			if(intervalTime != null && intervalTime != '' && !(compareNum.exec(intervalTime))){
				alert("时间间隔只能是数字或为空！");
				return false;
			}
			
			$("#times").val(times);
			$("#intervalTime").val(intervalTime);
			$("#allocationName").val(allocationName);
			$("#messageCode").val(messageCode);
			return true;

		}
		
		function testmessageCode(){
			var messageCode = $("#messageCode").val();
			if(messageCode == null || messageCode == ''){
				$("#messageworn").val("请输入6位code码！");
				return false;
			}
			if(messageCode.length > 6 || !compareNum.exec(messageCode)){
				$("#messageworn").val("请输入6位数字以内的code码！");
				return false;
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
</html>