<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增广告</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
</head>
<body>
	<form action="save.html" name="StoreStagesForm" id="StoreStagesForm" target="result" method="post" >
 	 <div align="center">
 	  <table style="border-collapse: separate;  border-spacing:10px;">
 	    <tr>
 	       <td align="right">商店编号:<br><br></td> 
 	       <td align="left">
 	       		<input type="text" id="storeNo" name="storeNo" maxlength="100"/>
 	       		<label style="color: red;" class="red">*</label><br><br> 
 	       </td> 	    
 	    </tr>
 	    <tr>
 	       <td align="right">商店名称:<br><br></td> 
 	       <td align="left">
 	       		<input type="text" id="storeName" name="storeName" maxlength="100"/>
 	       		<label style="color: red;" class="red">*</label><br><br> 
 	       </td> 	    
 	    </tr>
 	    <tr>
 	       <td align="right">商店地址:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="storeAddr" name="storeAddr" maxlength="190" />
 	       		<label style="color: red;" class="red">*</label><br><br> 
 	       </td> 	    
 	    </tr>
 	    <tr>
 	       <td align="right">3期费率:<br><br></td>
 	       <td align="left">
 	       <input type="text" id="fee3" name="fee3" maxlength="25" /><br><br>
 	       </td> 	    
 	    </tr>
 	    <tr>
 	       <td align="right">6期费率:<br><br></td>
 	       <td align="left">
 	       <input type="text" id="fee6" name="fee6" maxlength="25" /><br><br>
 	       </td> 	     
 	    </tr>
 	    <tr>
 	       <td align="right">12期费率:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="fee12" name="fee12" maxlength="25" /><br><br>
 	       </td> 	     
 	    </tr>
 	    <tr>
 	       <td align="right">24期费率:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="fee24" name="fee24" maxlength="25" /><br><br>
 	       </td> 	     
 	    </tr>
 	     <tr>
 	       <td align="right">城 市:<br><br></td>
 	       <td align="left">
 	      <select name="cityNo" style="width: 155px;" id="cityNo" class="input_txt">
					<option selected value="请选择">请选择</option>
				<c:forEach items="${paymentcitylist}" var="paymentcity">
					<option value="${paymentcity.cityCode }">${paymentcity.cityName }</option>
					
				</c:forEach>
				</select>
 	      <label style="color: red;" class="red">*</label><br><br> 
 	       </td> 	     
 	    </tr>
 	    
 	     <tr>
 	       <td align="right">行业名称:<br><br></td>
 	       <td align="left">
 	       		<input type="text" id="tradeName" name="tradeName" maxlength="59"/>
 	       		<label style="color: red;" class="red">*</label><br><br>
 	       </td> 	     
 	    </tr>
	  </table>
 	</div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			    if(isNoNull()){
			      var fee3=$.trim($("#fee3").val());
			      var fee6=$.trim($("#fee6").val());
			      var fee12=$.trim($("#fee12").val());
			      var fee24=$.trim($("#fee24").val());
			      if(fee3=="") $("#fee3").val("-"); 
			      if(fee6=="") $("#fee6").val("-"); 
			      if(fee12=="") $("#fee12").val("-"); 
			      if(fee24=="") $("#fee24").val("-"); 
			      $("#StoreStagesForm").ajaxSubmit({
			          type:"POST",
			          url:"save.html",
			          dataType: "text",
			          success:function(text){
			              if(text=="1"){
			                alert("商户编号已经存在！");
			              }else if(text=="2"){
			                //alert("新增成功！");
			               success();
			              }else{
			                alert("新增失败！");
			              } 
			          },
			          error:function(){
			              alert("新增失败！");
			          }
		         });
			      
			      //$("#StoreStagesForm").submit();
			    }
				
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
		
		function isNoNull(){
		    var storeNo=$.trim($("#storeNo").val());
		    if(storeNo==""){
		      alert("商店编号不能为空！");
		      return;
		    }else{
		        var reg=/^[0-9]*$/;
			    if(!reg.test($("#storeNo").val())){
			    	alert("商户编号只能为数字！");
			    	$("#storeNo").val("");
			    	$("#storeNo").focus();
			    	return false;
			    } 
		    }
		    var storeName=$.trim($("#storeName").val());
		    if(storeName==""){
		      alert("商店名称不能为空！");
		      return;
		    }
		    var storeAddr=$.trim($("#storeAddr").val()); 
		    if(storeAddr==""){
		      alert("商店地址不能为空！");
		      return;
		    }
		    var fee3=$.trim($("#fee3").val());
		    if(isNaN(fee3)){
		      alert("请输入正确的数字！");
		      $("#fee3").val("").focus();
		      return;
		    }
		    var fee6=$.trim($("#fee6").val());
		    if(isNaN(fee6)){
		      alert("请输入正确的数字！");
		      $("#fee6").val("").focus();
		      return;
		    }
		    var fee12=$.trim($("#fee12").val());
		    if(isNaN(fee12)){
		      alert("请输入正确的数字！");
		      $("#fee12").val("").focus();
		      return;
		    }
		    var fee24=$.trim($("#fee24").val());
		    if(isNaN(fee24)){
		      alert("请输入正确的数字！");
		      $("#fee24").val("").focus();
		      return;
		    }
		    
		    var cityNo=$.trim($("#cityNo").val());
		    if(cityNo=="请选择"){
		      alert("请选择城市！");
		      return;
		    }
		    var tradeName=$.trim($("#tradeName").val());
		    if(tradeName==""){
		      alert("行业名称不能为空！");
		      return;
		    }
		    return true;
		}
		
		
	</script>
</body>
</html>