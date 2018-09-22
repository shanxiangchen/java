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
 
</head>
<body>
	<form action="addmerchantList.html"  name="merchantListForm" id="merchantListForm" target="result" method="post" enctype="multipart/form-data">
		<center>
		<div class="page_and_btn">
				<input type="file" id="files" name="files" /><br><br>
 </div>	
		</center>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="..js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
			 
	});
		
	    var dg,count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','导入',function(){
		    if(checkfile()){
		       if(count!=0){
		         return;
		       }
		       count=count+1;
		       $("#merchantListForm").ajaxSubmit({
		          type:"POST",
		          url:"addmerchantList.html",
		          dataType: "text",
		          success:function(text){
		              count=0;
		              if(text=="1"){
		                alert("文件过大，上传文件不能大于1M");
		              }else if(text=="2"){
		                alert("文件格式有误！");
		              }else if(text=="3"){
		                alert("商户已经存在，无新增商户！");
		              }else if(text=="4"){
		                alert("存在重复的商户编号！");
		              }else if(text=="fail"){
		                alert("导入失败");
		              }else if(text=="success"){
		                alert("导入成功");
		              }
		              success();
		          },
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
		 
        function checkfile(){  
            
                var obj_file = document.getElementById("files");   
                if(obj_file.value==""){   
                    alert("请先选择上传文件");   
                    return;   
                }   
                
                var strs=obj_file.value.split(".");
                
                var fileType=strs[strs.length-1];
				if(fileType!="xls"&&fileType!="xlsx"){
					 alert("请选择Excel格式的上传文件！");
					 return false;
				}
               
                return true;  
               
             
        }   
		
	</script>
</body>
</html>