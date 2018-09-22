<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="save.html" name="uploadForm" id="uploadForm" target="result" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${upload.id }"/>
	<table border="0" cellpadding="0" cellspacing="0">
	
		<tr class="info">
			<th>id:</th>
			<td><input type="text" name="id" id="id" class="input_txt" value="${upload.id }"/></td>
		</tr>
		<tr class="info">
			<th>资源路径:</th>
			<td><input type="text" name="url" id="url" class="input_txt" value="${upload.url }"/></td>
		</tr>
		<tr class="info">
			<th>名　称:</th>
			<td><input type="text" name="name" id="name" class="input_txt" value="${upload.name }"/></td>
		</tr>
		<tr class="info">
			<th>商户图片:</th>
			<td><input type="file" name="imge"  class="input_txt"  value="${upload.imge }" onchange='PreviewImage(this)' /></td>
		    <td><div id="imgPreview" style='width:100px; height:100px;'> </div></td>
		</tr>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	
	<script> 
   function PreviewImage(imgFile) 
   { 
    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
    if(!pattern.test(imgFile.value)) 
    { 
     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
     imgFile.focus(); 
    } 
    else 
    { 
     var path; 
     if(document.all)//IE 
     { 
      imgFile.select(); 
      path = document.selection.createRange().text; 
      document.getElementById("imgPreview").innerHTML=""; 
      document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
     } 
     else//FF 
     { 
      path = URL.createObjectURL(imgFile.files[0]);
      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
     } 
    } 
   } 
  </script> 
	
	
	
	
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#uploadForm").submit();
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
			alert("新增失败！");
		}
		
	</script>
	
</body>
</html>