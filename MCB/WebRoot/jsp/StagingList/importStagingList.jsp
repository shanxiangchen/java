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
<title>导入商户信息</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
 <style type="text/css">     
    .mask {       
            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;     
            z-index: 1002; left: 0px;     
            opacity:0.5; -moz-opacity:0.5;     
        }     
</style> 
</head>
<body>
	<form action=""  name="importDataForm" id="importDataForm" target="result" method="post" enctype="multipart/form-data">
		<center>
		<div class="page_and_btn">
				<input type="file" id="files" name="files" /><br><br>
 		</div>	
		</center>
	</form>
	<div id="infodiv"><p id="infop"></p></div>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="..js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("infodiv").hide();
		$(".main_info:even").addClass("main_table_even");
			 
	});
		
	    var dg,count=0;
		$(document).ready(function(){
		   
			dg = frameElement.lhgDG;
			dg.addBtn('ok','导入',function(){
			if(count!=0){
				return false;
			} 
		    if(checkfile()){
		    	count=count+1;
		    	$("#importDataForm").ajaxSubmit({
		    		beforeSubmit: showTips, 
			          type:"POST",
			          url:"importData.html",
			          dataType: "text",
			          success:function(text){
			              count=0;
			              if(text=="success"){
			            	  $("#infop").text("您的数据现已提交后台执行导入，请稍后...");
			            	  success();
			              }else if(text=="1"){
			            	  $("#infop").text("文件编码格式必须为UTF-8");
			            	  count=0;
			              }else if(text=="3"){
			            	  $("#infop").text("文件格式不正确");
			            	  count=0;
			              }else if(text=="4"){
			            	  $("#infop").text("文件为空");
			            	  count=0;
			              }else if(text.split(",").lenth != 0){
			            	  text = text.substring(0,text.length-1);
			            	  $("#infop").text("您导入的文件数据不正确，请检查第"+text+"行！");
			            	  dg.curWin.location.reload();
			            	  dg.cancel();
			              }else{
			            	  $("#infop").text("导入文件失败，请联系管理员！");
			            	  dg.curWin.location.reload();
			            	  dg.cancel();
			              }
			          },
			          error:function(){
			              count=0;
			              $("#infop").text("导入失败！");
			             dg.curWin.location.reload();
			             dg.cancel();
			          }
			      });
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
		
		function showTips(){
			dg.removeBtn("ok");
			$("#importDataForm").hide();
			$("#infodiv").show();
			$("#infop").text("您的数据现已提交至后台检查，请稍候...");
			//alert("您的数据现已提交至后台检查，请稍候...");
			return true;
		}
		
        function checkfile(){  
            
                var obj_file = document.getElementById("files");   
                if(obj_file.value==""){   
                    alert("请先选择上传文件!");   
                    return false;   
                }   
                
                var strs=obj_file.value.split(".");
                var fileType=strs[strs.length-1];
				if(fileType!="txt"&&fileType!="TXT"){
					 alert("请选择TXT格式的上传文件！");
					 return false;
				}
                return true;  
               
             
        }   
		
	</script>
</body>
</html>