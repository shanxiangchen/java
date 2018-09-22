<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入白名单信息</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
 
</head>
<body>
	<form action="addClientWhiteList.html"  name="importWhiteForm" id="importWhiteForm" target="result" method="post" enctype="multipart/form-data">
		<center>
		<div class="page_and_btn">
				<input type="file" id="txtFile" name="files" accept=".txt" />  <br><br>
 </div>	
		</center>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="..js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	  
	    var dg;var count=0;
		$(document).ready(function(){
		   
			dg = frameElement.lhgDG;
			dg.addBtn('ok','导入',function(){
		     if($("#txtFile").val() == ""){
		    	alert("请选择txt格式的文件导入");
		    	return false;
		     }else{
		         
		          var name=$("#txtFile").val();
		          var names=name.split(".");
		          var fileType=names[names.length-1];
		          if(fileType.toLowerCase()!="txt"){
		          	alert("请导入txt格式的文件");
		          }else{
		          	//$("#importDataId").attr("disabled",true); 
					//var files=$("#txtFile").val();
					if(count==0){
		                count=count+1;
		                $("#importWhiteForm").ajaxSubmit({
			            type:"POST",
			            url:"addClientWhiteList.html",
			            dataType: "text",
			            success: function(text){  
			                if(text=="2"){
			                	alert("导入白名单数据文件成功");
			                	success();
			                }else if(text=="1"){
				   				alert("文件编码格式必须为UTF-8");
				            	count=0;
				   			}else if(text=="3"){
				   				alert("文件格式不正确");
				            	count=0;
				   			}else if(text=="4"){
				   				alert("文件为空");
				   				count=0;
				   			}else{
				   			    alert("导入失败");
			            	    dg.cancel();
				   			}
			            },
			            error: function(text){ 
			                count=0;
			                alert("导入失败！");
			            }
			          }); 
		            } 
			       
		        
		      //  $("#clientWhileListForm").submit();
		      } 
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
		 
        
		
	</script>
</body>
</html>