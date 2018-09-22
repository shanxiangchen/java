<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset=UTF-8">
<title>广告维护功能</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<style>#Img1{ width:30px; height:30px;}#Img1:hover{ width:300px; height:300px;}</style>
</head>
<body>
 <form action="advert.html" method="post" name="advertForm" id="advertForm">
     <input type="hidden" id="order" name="order" value="${moduleFunctionName.order}">
	 <div class="search_div">
		广告标题：<input type="text" name="advertTitle" id="advertTitle" value="${moduleFunctionName.advertTitle}"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
			序号</th>
			<th>广告ID</th>
			<th >广告标题</th>
			<th>广告图片</th>
			<th>功能模块名称</th>
			<th>广告位置</th>
			<th onclick="searchTwo()">客户端类型</th>
			<th>广告链接</th>
			<!-- <th>商户ID</th>
			<th>活动编号</th> -->
			<th>链接参数</th>
			<th>链接方式</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="li" varStatus="vs">
				 <c:forEach items="${li.advert}" var="list" >
					<tr class="main_info">
					<th><input type="checkbox" name="advertId" id="advertId" value="${list.advertId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th>
					<th onmouseover="this.title=this.innerText">${list.advertId}<input type="hidden" id="advertId" value="${list.advertId}"/></th>
					<th onmouseover="this.title=this.innerText">${list.advertTitle}</th>
					<th><a href="${path}${list.advertPicUrl}">
 						<img src="${path}${list.advertPicUrl}" width="30" height="30"></a>
 					</th>
 					<th onmouseover="this.title=this.innerText">${li.moduleFunctionName}</th>
					<th> 
						<c:choose>
					     <c:when test="${list.advertSeat=='1'}">
			       			顶部
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='2'}">
			       			中部
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='2.1'}">
			       			中部第一行
			     		 </c:when>
			     		  <c:when test="${list.advertSeat=='2.11'}">
			       			中部第一行左边
			     		 </c:when>
			     		  <c:when test="${list.advertSeat=='2.12'}">
			       			中部第一行右边
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='2.2'}">
			       			中部第二行
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='2.3'}">
			       			中部第三行
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='2.4'}">
			       			中部第四行
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='2.5'}">
			       			中部第五行
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='3'}">
			       			启动页
			     		 </c:when>
			     		 <c:when test="${list.advertSeat=='4'}">
			       			底部
			     		 </c:when>
				  	  </c:choose>
					</th>
					<th> 
						<c:choose>
					     <c:when test="${list.clientType=='iphone'}">
			       			苹果
			     		 </c:when>
			     		 <c:when test="${list.clientType=='android'}">
			       			安卓
			     		 </c:when>
				  	  </c:choose>
					</th>
					<th>${list.advertUrl}</th>
					<%-- <th>${list.shopId}</th>
					<th>${list.activityNo}</th> --%>
					<th>${list.advertParam }</th>
					<th>
					   <c:choose>
					     <c:when test="${list.linkType=='1'}">
			       			内链
			     		 </c:when>
			     		 <c:when test="${list.linkType=='2'}">
			       		          外链
			     		 </c:when>
			     	    </c:choose>
					</th>
					<th><a href="javascript:void(0)" onclick="editAdvert('${list.advertId}')">修改</a></th>
					</tr>
				</c:forEach>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="12">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
	    <div>
	      <a href="javascript:addAdvert();" class="myBtn"><em>新增</em></a>
	      <a href="javascript:void(0)" onclick="delAdvert()" class="myBtn"><em>删除</em></a>
	    </div>
        ${moduleFunctionName.page.pageStr}
	</div>
	 
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='advertId']").attr("checked",true);
			}else{
				$("input[name='advertId']").attr("checked",false);
			}
		}
		
		function addAdvert(){
			$("#advertTitle").val("");
			var dg = new $.dialog({
				title:'新增广告',
				id:'user_new',
				width:600,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'advert/add.html'
				});
    		dg.ShowDialog();
		}
		
		function editAdvert(advertId){
			$("#advertTitle").val("");
			var dg = new $.dialog({
				title:'修改广告',
				id:'user_edit',
				width:600,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'advert/editAdvert.html?advertId='+advertId
				});
    		dg.ShowDialog();
		}
		
		 function delAdvert(){
		    	var str="";
				//验证是否选择了记录
			  	if($('#tableId input[name="advertId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行删除！');
			  		return false;
			  	}else{
			  		$('#tableId input[name="advertId"]:checkbox:checked').each(function(){
					   str+=$(this).val()+",";  
					});
				   if(confirm("确定要删除该记录？")){
					   $.ajax({
								url:'advert/delete.html', 
								type: 'POST', 
								data: {str:str},
								dataType: 'text',
								timeout: 1000, 
								async: false,
								error: function(text){ 
									alert('读取数据失败，请联系管理员！'); 
									return false;
								}, 
								success: function(text){
									if(text>0){
									   alert("删除成功");
									   document.location.reload();
						   			}else{
						   			   alert("删除失败");
						   			}
								}        
					    });
			        }
			}
		 }
		
		function checkData(){
			var str="";
				//验证是否选择了记录
			  	if($('#tableId input[name="advertId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行审核！');
			  		return false;
			  	}else{
			  		$('#tableId input[name="advertId"]:checkbox:checked').each(function(){
					   str+=$(this).val()+",";  
					});
				   if(confirm("确定审核吗？")){
					   $.ajax({
								url:'marketActivity/checkDada.html', 
								type: 'POST', 
								data: {str:str},
								dataType: 'text',
								timeout: 1000, 
								async: false,
								error: function(text){ 
									alert('读取数据失败，请联系管理员！'); 
									return false;
								}, 
								success: function(text){
									 if(text>0){
									   alert("提交成功");
									   document.location.reload();
						   			}else{
						   			   alert("提交失败");
						   			   document.location.reload();
						   			} 
								}        
					    });
			        }
			}
		}
		function search(){
			$("#advertForm").submit();
		}
		 
		function searchTwo(){
			if($("#order").val()=="3"){
				$("#order").val("4");
			}else if($("#order").val()=="4"){
				$("#order").val("3");
			}else{
				$("#order").val("4");
			}
			$("#advertForm").submit();
		}
		 
	</script>
	
  	<script type="text/javascript">
   	 //图片预览
	 function ShowDiv(pic){ 
		 divPic.innerHTML="<img src="+pic+" width='550' height='550'>";
		 divPic.style.display="block"; 
     }
	 function HideDiv(){ 
	 	divPic.style.display="none";
	 }
  </script>
</body>
</html>