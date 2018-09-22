<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>webDav服务器配置页</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>
	<form action="" method="post" name="newsTempletForm" id="newsTempletForm">
		 
	<table width="100%"  border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
		    <th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
		             序号</th>
			<th>服务器编号</th> 
			<th>服务器IP</th>
			<th>端口号</th>
			<th>用户名</th> 
			<th>上传路径</th> 
			<th>访问路径</th> 
			<th>服务器类型</th>
			<th>运行状态</th>
			<th>操作</th>  
		</tr>
		<c:choose>
			<c:when test="${not empty webDavList}">
				<c:forEach items="${webDavList}" var="list" varStatus="vs">
				<tr class="main_info">
				    <th><input type="checkbox" type="hidden" name="webDavId" id="webDavId" value="${list.webDavId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th>   
					<th>${list.webDavId}</th>
					<th>${list.webDavHostName}</th>
					<th>${list.webDavPort}</th>
					<th>${list.webDavUserName}</th>
					<th>${list.webDavUrl}</th>
					<th>${list.webDavLookUrl}</th>
					<th>
					  <c:choose>
					     <c:when test="${list.webDavNum=='1'}">
			       			主机
			     		 </c:when>
			     		 <c:when test="${list.webDavNum=='2'}">
			       			备机
			     		 </c:when>
				  	  </c:choose>
					  
					</th>
					<th> 
					   <c:choose>
					     <c:when test="${list.webDavIsOk=='1'}">
			       			正常
			     		 </c:when>
			     		 <c:when test="${list.webDavIsOk=='2'}">
			       			宕机
			     		 </c:when>
				  	  </c:choose>
					</th>
					<th><a href="###" onclick="editWebDav('${list.webDavId}')">修改</a></th>  
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="11">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"></div>
     <div style="float:left; height:100px; ">
     			<a href="javascript:addWebDav();" class="myBtn"><em>新增</em></a>
    			<a href="javascript:void(0)" onclick="deleteWebDav()" class="myBtn"> <em>删除</em></a>    
     </div>
    
	</form>
	 <script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	    function addWebDav(){
			var dg = new $.dialog({
				title:'新增服务',
				width:600,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'webDav/add.html'
				});
    		dg.ShowDialog();
		}
		
		function editWebDav(webDavId){
		    var dg = new $.dialog({
				title:'修改服务',
				width:600,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'webDav/edit.html?webDavId='+webDavId
				});
    		dg.ShowDialog();
		    
		}
		
		function deleteWebDav(){
		    var str="";
		    //验证是否选择了记录
			if ($('#tableId input[name="webDavId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="webDavId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'webDav/deleteWebDav.html',
						type : 'POST',
						data : {
							webDavId : str
						},
						dataType : 'text',
						timeout : 1000,
						async : false,
						success : function(text) {
							if (text == "success") {
								alert("删除成功");
								document.location.reload();
							} else {
								alert("删除失败！");
								document.location.reload();
							}
						},
						error : function() {
							alert("删除失败！");
							document.location.reload();
						}
					});
				}
			}		
		}
		
		function sltAllUser() {
			if ($("#sltAll").attr("checked")) {
				$("input[name='webDavId']").attr("checked", true);
			} else {
				$("input[name='webDavId']").attr("checked", false);
			}
		}
		 
	</script>
</body>
</html>