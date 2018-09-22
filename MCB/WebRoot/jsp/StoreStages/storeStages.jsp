<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分期商店信息维护</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>
	<form action="storeStages.html" method="post" name="storeStagesForm" id="storeStagesForm">
		<div class="search_div">
			商店名称:<input type="text" name="storeName" value="${store.storeName}" id="storeName"/>
			城市:<select name="cityNo" style="vertical-align: middle;width: 155px;  id="cityNo" class="input_txt">
					<option selected value="">请选择</option>
				<c:forEach items="${paymentcitylist}" var="paymentcity">
					<option value="${paymentcity.cityCode }" <c:if test="${paymentcity.cityCode==store.cityNo}">selected</c:if>>${paymentcity.cityName }</option>
				</c:forEach>
				</select>
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
		<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
			序号</th> 
			<th>商店编号</th>
			<th>商店名称</th>
			<th>商店地址</th>
			<th>3期费率</th>
			<th>6期费率</th>
			<th>12期费率</th>
			<th>24期费率</th>
			<th>城   市</th>
			<th>行业名称</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				    <th><input type="checkbox" name="storeStagesId" id=storeStagesId value="${list.storeStagesId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th> 
					<th>${list.storeNo}<input type="hidden" id="storeStagesId" value="${list.storeStagesId}"/></th>
					<th>${list.storeName}</th>
					<th>${list.storeAddr}</th>
					<th>${list.fee3}</th>
					<th>${list.fee6}</th>
					<th>${list.fee12}</th>
					<th>${list.fee24}</th>
					<th>${list.paymentcity.cityName}</th>
					<th>${list.tradeName}</th>
					<th><a href="###" onclick="editStages('${list.storeStagesId  }')">修改</a>|
					 <a href="javascript:delstoreStages('${list.storeStagesId }');">删除</a> </th>
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
	<div class="page_and_btn"> 
     <div>
     			<a href="javascript:addStages();" class="myBtn"><em>新增</em></a>
    			<a href="javascript:importData();" class="myBtn"><em>导入</em></a>
    			<a href="javascript:void(0)" onclick="deleteActShop()" class="myBtn"> <em>删除</em></a>
     </div>
     ${store.page.pageStr}
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
		
	function addStages(){
			var dg = new $.dialog({
				title:'新增地区',
				id:'storeStages_new',
				width:330,
				height:420,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'storeStages/addStages.html'
				});
    		dg.ShowDialog();
		}
		
				function deleteActShop(){
				var str="";
				//验证是否选择了记录
			  	if($('#tableId input[name="storeStagesId"]:checkbox:checked').length < 1){
			  		alert('请选择一条记录再进行删除！');
			  		return false;
			  	}else{
			  		$('#tableId input[name="storeStagesId"]:checkbox:checked').each(function(){
					   str+=$(this).val()+"," 
					});
				   if(confirm("确定要删除该记录？")){
					   $.ajax({
								url:'storeStages/deleteActShop.html', 
								type: 'POST', 
								data: {storeStagesId:str},
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
		
			function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='storeStagesId']").attr("checked",true);
			}else{
				$("input[name='storeStagesId']").attr("checked",false);
			}
		}
		
		
		
	function editStages(storeStagesId){
			var dg = new $.dialog({
				title:'修改菜单',
				id:'storeStages_edit',
				width:700,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'storeStages/edit.html?storeStagesId='+storeStagesId
				});
    		dg.ShowDialog();
		}
		
	function delstoreStages(storeStagesId){
			if(confirm("确定要删除该记录？")){
				//var url = "storeStages/delete.html?storeStagesId="+storeStagesId;
				 $.ajax({
								url:'storeStages/delete.html', 
								type: 'POST', 
								data: {storeStagesId:storeStagesId},
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
				
				/*  $.get(url,function(data){
					if(data=="success"){
					    alert("删除成功！");
						document.location.reload();
					}else{
					    alert("删除失败！");
					    document.location.reload();
					}
				});   */
			}
		}
		
	
		function importData(){
		    var dg = new $.dialog({
				title:'导入商户信息',
				id:'storeStages_importout',
				width:300,
				height:170,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'storeStages/importout.html'
				});
    		dg.ShowDialog();
		}
		
	function search(){
			$("#storeStagesForm").submit();
		}
		
	 
		
	</script>
</body>
</html>