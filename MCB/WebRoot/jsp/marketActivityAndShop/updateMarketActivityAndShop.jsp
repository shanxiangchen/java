<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改活动与商户关系</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="updateActAndShopOk.html" name="actAndShopOkForm" id="actAndShopOkForm" target="result" method="post" >
		<h2>修改商户与活动关系</h2>
		<div align="center">
		<table>
		   <tr>
			<td align="left">活动标题:<br><br><br><br><br></td>
			<td align="right">
				<select id="activityId" name="activityId" style="width:200px">
				   <c:forEach var="li" items="${listActId}">
				     <option value="${li.activityId}" <c:if test="${li.activityId==activityId}">selected</c:if>> ${li.activityTitle}</option>
				   </c:forEach>
				</select><br><br><br><br><br>
		   </td>
		  </tr>	
				
		  <tr>
			<td align="left">商户名称:</td>
			<td align="right">
				<select id="shopIds" name="marActivityShop" style="width:200px">
				   <c:forEach var="lis" items="${listShop}">
				       <option value="${lis.shopId}">${lis.shopName}</option>
				   </c:forEach>
				</select>
		    </td>
		  </tr>   
		</table>
	 </div>		 
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			$.ajax({
					url:'selectCount.html', 
					type: 'POST', 
					data: {activityId:$("#activityId").val(),marActivityShop:$("#shopIds").val()},
					dataType: 'text',
					timeout: 1000, 
					async: false,
					error: function(text){ 
						alert('读取数据失败，请联系管理员！'); 
						return false;
					}, 
					success: function(text){
						if(text.split(",")[0]>0){
						   
						  dg.cancel();
						  return false; 
			   			} 
			   			if(text.split(",")[1]>0){
						   alert("商户下存在尚未结束的活动！");
						  return false;
			   			}
			   			 
			             $("#actAndShopOkForm").submit();
			            		
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
		
		function failed(){
			alert("新增失败，该用户名已存在！");
			$("#loginname").select();
			$("#loginname").focus();
		}
	</script>
</body>
</html>