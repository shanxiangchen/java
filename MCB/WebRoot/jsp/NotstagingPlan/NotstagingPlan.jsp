<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="NotstagingPlan.html" method="post" name="StagingPlanForm" id="StagingPlanForm">
	<div class="search_div">
		<table>
			<tr>
				<td>分期计划名称:</td>
				<td><input type="text" name="stagingPlanName" id="stagingPlanName" value="${stagingPlan.stagingPlanName}"></td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
			</tr>
		</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>编号</th>
			<th>分期计划编码</th>
			<th>分期计划名称</th>
			<th>费率</th>
			<th>状态名称</th>
			<th>操作</th>
		</tr>
 	 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="plan" varStatus="vs">
				<tr class="main_info">
				<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
				<th onMouseOver="this.title=this.innerText">${plan.stagingPlanCode}</th>
				<th onMouseOver="this.title=this.innerText">${plan.stagingPlanName}</th>
				<th onMouseOver="this.title=this.innerText">${plan.stagingPlanRate}</th>
				<th onMouseOver="this.title=this.innerText">${plan.state.stateName}</th>
				<th><a href="javascript:editStagingPlan(${plan.planId});">编辑</a>|<a href="javascript:stagingPlanDel('${plan.planId}','${plan.stagingPlanCode}');">删除</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="5">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
   <div class="page_and_btn"> 
	<div><a href="javascript:notStagingPlanAdd();" class="myBtn"><em>新增</em></a></div>
	 ${stagingPlan.page.pageStr}
   </div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
		function search(){
			$("#StagingPlanForm").submit();
		}	
		
		function notStagingPlanAdd(){
			var dg = new $.dialog({
				title:'新增分期计划',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'NotstagingPlan/notStagingPlanAdd.html'
				});
    		dg.ShowDialog();
		}
		
		function editStagingPlan(planId){
			var dg = new $.dialog({
				title:'编辑分期计划',
				id:'stagingPlan_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'stagingPlan/stagingPlanEdit.html?planId='+planId
				});
    		dg.ShowDialog();
		}
		//绝对路径
		function getBasePath(){
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
			return basePath;
		}
		
		function stagingPlanDel(planId,stagingPlanCode){
			var url = getBasePath()+'/stagingPlan/getCountCode.html?stagingPlanCode='+stagingPlanCode;
			$.ajax({
				url:url,
				type:'post',
				dataType:'text',
				success:function(objs){
					if(objs!=0){
						alert("此数据有关联,请删除对应关联,方可删除!");
					}else{
						if(confirm("确定要删除该记录？")){
							$.ajax({
								url:'stagingPlan/stagingPlan_del.html', 
								type: 'POST', 
								data: {planId:planId},
								dataType:'text',
								async: false,
								success: function(text){
									if(text=="success"){
										 alert("删除成功！");
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
			});
		}
	</script>
</body>
</html>