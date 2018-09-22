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
<title>导入客户白名单</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>

</head>
<body>
	 
	
	<form action="clientWhiteList.html" method="post" name="whiteImportForm" id="whiteImportForm" enctype="multipart/form-data">
	    <div class="search_div">
	                        原文件名:<input type="text" name="whiteImportOldname" value="${whiteImport.whiteImportOldname}" id="whiteImportOldname"/>
			上传用户:<input type="text" name="whiteImportUser" value="${whiteImport.whiteImportUser}" id="whiteImportUser"/>
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
		 
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
		<th width=5%><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
			序号</th> 
			<th width=6%>原文件名</th>
			<th width=14%>现文件名</th>
			<!-- <th width=20%>文件路径</th> -->
			<th width=5%>文件大小</th>
			<th width=8%>导入日期</th>
			<th width=5%>处理状态</th>
			<th width=20%>处理结果</th>
			<th width=5%>上传用户</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				    <th><input type="checkbox" name="whiteImportId"   value="${list.whiteImportId},${list.whiteImportStatus}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th> 
					<th>${list.whiteImportOldname}</th>
					<th>${list.whiteImportNewname}</th>
					<th>${list.whiteImportSize}</th>
					<th>${list.whiteImportDate}</th>
					<th>
					   <c:choose>
					     <c:when test="${list.whiteImportStatus=='1'}">
			       			处理中
			     		 </c:when>
			     		 <c:when test="${list.whiteImportStatus=='2'}">
			       			已完成
			     		 </c:when>
				  	  </c:choose>
					</th>
					<th>${list.loginfo }</th>
					<th>${list.whiteImportUser}</th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="8">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
     <div>
    	<a href="javascript:void(0)" onclick="deleteWhiteImport()" class="myBtn"> <em>删除</em></a>
    	<a href="javascript:importDatas();" class="myBtn"><em>导入白名单</em></a>
     </div>
     ${whiteImport.page.pageStr}
   </div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/resources/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		 
		
		function search(){
			$("#whiteImportForm").submit();
		}
		
		
		function deleteWhiteImport(){
		        
				var str="";
				//验证是否选择了记录
			  	if($('#tableId input[name="whiteImportId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行删除！');
			  		return false;
			  	}else{
			  		$('#tableId input[name="whiteImportId"]:checkbox:checked').each(function(){
			  		    
			  		      str+=$(this).val().split(",")[0]+",";
			  		  
					});
				   if(confirm("确定要删除该记录？")){
					   str = str.substring(0, str.length-1);
					   $.ajax({
								url:'clientWhiteList/deleteWhiteImport.html', 
								type: 'POST', 
								data: {whiteImportId:str},
								dataType: 'text',
								timeout: 1000, 
								async: false,
								success: function(text){
									if(text=="success"){
											alert("删除成功");
									    	document.location.reload();
						   			}else{
						   			   alert("删除失败！");
						   			   document.location.reload();
						   			}
							   },
							   error:function(){
			        	               alert("删除失败！");
			        	               document.location.reload();
			                   }  
					    });
			        }
				}
		}
		
		$(':checkbox').click(function(){  
		    if($(this).attr('checked')){   
			     if($(this).val().split(",")[1]=="1"){
			        alert("处理中的白名单不允许删除！");
			         $(this).attr('checked',false);
			          
			     }
		       
		    }  
		});  

		 
	    function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='whiteImportId']").each(function(){
				    if($(this).val().split(",")[1]=="2"){
				       $(this).attr("checked",true);
				    }else{
				       $(this).attr("checked",false);
				    }
				
				});
			}else{
				$("input[name='whiteImportId']").attr("checked",false);
			}
		}
		
			function importDatas(){
			    var dg = new $.dialog({
					title:'导入白名单',
					 
					width:300,
					height:170,
					iconTitle:false,
					cover:true,
					maxBtn:false,
					xButton:true,
					resize:false,
					page:'clientWhiteList/importout.html'
					});
		   		dg.ShowDialog();
		   }
		
	</script>
</body>
</html>