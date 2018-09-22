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
<title>导入静态页面</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
 
</head>
<body>
	<form action="importData.html"  name="importForm" id="importForm" target="result" method="post" enctype="multipart/form-data">
		<center>
		<div class="page_and_btn">
		    <table border="0" cellpadding="6" cellspacing="0">
				<tr>
					<td >导入样式： </td>
					<td ><input type="file" id="cssStyle" name="importFile" />
						 <label style="color: red;">*</label> </td>
						
				</tr>
				<tr>
					<td >导入图片： </td>
					<td ><input type="file" id="images" name="importFile" />
						 <label style="color: red;">*</label> </td>
						
				</tr>
				<tr class="info">
					<td >导入页面： </td>
					<td ><input type="file" id="html" name="importFile" />
						 <label style="color: red;">*</label> </td>
						
				</tr>
				 
			</table>
		  
        </div>	
		</center>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="..js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	 
	    var dg,count=0;
		$(document).ready(function(){
		   
			dg = frameElement.lhgDG;
			dg.addBtn('ok','导入',function(){
		       if(!checkfile()){
		         return;
		       }
		       if(count!=0){
		         return;
		       }
		       count=count+1;
		       $("#importForm").ajaxSubmit({
		          type:"POST",
		          url:"importData.html",
		          dataType: "text",
		          success:function(text){
		              count=0;
		              success();
		          },
		          error:function(){
		              count=0;
		             alert("导入失败！");
		             success();
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
		 
        function checkfile(){  
            
                var css = document.getElementById("cssStyle");  
                var images = document.getElementById("images"); 
                var html = document.getElementById("html"); 
                if(css.value==""){   
                    alert("请先选择上传文件");   
                    return false;   
                }else{
                  var strs=css.value.split(".");
                  var fileType=strs[strs.length-1];
				  if(fileType!="css"){
					 alert("请选择css格式的上传文件！")
					 css.value="";
					 return false;
				  }
                } 
                if(images.value==""){   
                    alert("请先选择上传图片");   
                    return false;   
                }else{
                   var strs=images.value.split(".");
                   var fileType=strs[strs.length-1];
				   if(fileType!="gif"&&fileType!="jpg"&&fileType!="jpeg"&&fileType!="bmp"&&
		        fileType!="png"){
					 alert("图片类型必须是.gif,jpeg,jpg,bmp,png中的一种");
					 images.value="";
					 return false;
				   }
                }   
                if(html.value==""){   
                    alert("请先选择上传页面");   
                    return;   
                }else{
                   var strs=html.value.split(".");
                   var fileType=strs[strs.length-1];
				   if(fileType!="html"){
					 alert("请选择html格式的上传文件！")
					 html.value="";
					 return false;
				   }
                }    
                
                
               
                return true;  
               
             
        }   
		
	</script>
</body>
</html>