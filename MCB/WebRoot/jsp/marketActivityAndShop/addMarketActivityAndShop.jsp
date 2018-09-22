<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>信业银行信用卡APP后台管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:150px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="saveActAndShop.html" name="actAndShopForm" id="actAndShopForm" target="result" method="post"  >
		<div align="center">
			<table>
			<tr>
				<th>活动标题编码:</th>
				<td><input type="text" id=activityIds disabled="disabled"  />
					<input type="hidden" name="activityId" id="activityId"></td>
				<td><a href="javascript:activity();" class="myBtn"><em>点击</em></a></td>
			</tr>
			<tr>
				<th>商户名称编码:</th>
				<td><input type="text"  id="shopIds" disabled="disabled"/>	
					<input type="hidden" name="shopId" id="shopId"/>
				</td>
				<td><a href="javascript:shop();" class="myBtn"><em>点击</em></a></td>
			</tr>
		</table>
		</div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript">
			function activity(){
			var dg = new $.dialog({
				title:'活动标题',
				width:530,
				height:350,
				top:20,
				left:85,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'../shopAndMarketActivity/activity.html'
				});
    		dg.ShowDialog();
		}
		function shop(){
			var dg = new $.dialog({
				title:'商户',
				width:530,
				height:350,
				top:20,
				left:85, 
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'../shopAndMarketActivity/shop.html'
				});
    		dg.ShowDialog();
		}
		
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if ($("#activityId").val() == "请选择") {
					alert("请选择活动标题!");
					$("#activityId").focus();
					return false;
				}
				if ($("#shopIds").val() == "请选择") {
					alert("请选择商户名称!");
					$("#shopIds").focus();
					return false;
				}
				 
				  $.ajax({
					url:'selectCount.html', 
					type: 'POST', 
					data: {activityId:$("#activityId").val(),marActivityShop:$("#shopId").val()},
					dataType: 'text',
					timeout: 1000, 
					async: false,
					error: function(text){ 
						alert('读取数据失败，请联系管理员！'); 
						return false;
					}, 
					success: function(text){
					    
						if(text.split(",")[0]>0){
						   alert("已存在相应的商户与活动关系！");
						 return false;
			   			}
			   			if(text.split(",")[1]>0){
						   alert("商户下存在尚未结束的活动！");
						  return false;
			   			}
			   			 
			             $("#actAndShopForm").submit();
			            		
					}        
			     });  
			     
				 
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
</body>
</html>