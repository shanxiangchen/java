<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码类别信息</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>
	<form action="bankCodeInfo.html" method="post" name="codeInfoForm" id="codeInfoForm">
		<div class="search_div">
			代码类别英文名称:<input type="text" name="codeTypeId" value="${bankCodeInfo.codeTypeId}" id="codeTypeId"/>
			 
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		</div>
	<table width="100%"  border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
		    <th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>
		             序号</th>
			<th>代码类别英文名称</th> 
			<th>代码类别中文名称</th>
			<th>下拉框值</th>
			<th>下拉框内容</th>
			<th>排序号</th> 
			<th>备注</th>  
			<th>操作</th>  
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
				<tr class="main_info">
				    <th><input type="checkbox" type="hidden" name="codeInfoId"   value="${list.codeInfoId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th>   
					<th>${list.codeTypeId}</th>
					<th>${list.codeTypeName}</th>
					<th>${list.value}</th>
					<th>${list.content}</th>
					<th>${list.sortNo}</th>
					<th>${list.remark}</th>
					<th><a href="###" onclick="editCodeInfo('${list.codeInfoId}')">修改</a></th>  
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
		<a href="javascript:addCodeInfo();" class="myBtn"><em>新增</em></a>
		<a href="javascript:void(0)" onclick="deleteCodeInfo()" class="myBtn"> <em>删除</em></a>    
      </div>
      ${bankCodeInfo.page.pageStr}
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
		
		function addCodeInfo(){
			var dg = new $.dialog({
				title:'新增代码信息',
				width:600,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankCodeInfo/add.html'
				});
    		dg.ShowDialog();
		}
		
		function editCodeInfo(codeInfoId){
		   var dg = new $.dialog({
				title:'修改代码信息',
				width:600,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankCodeInfo/edit.html?codeInfoId='+codeInfoId
				});
    		dg.ShowDialog();
		}
		
		
		
		function deleteCodeInfo() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="codeInfoId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="codeInfoId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'bankCodeInfo/deleteCodeInfo.html',
						type : 'POST',
						data : {
							codeInfoIds : str
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
		
		 
	    function search(){
			$("#codeInfoForm").submit();
		}
		 
		function sltAllUser() {
			if ($("#sltAll").attr("checked")) {
				$("input[name='codeInfoId']").attr("checked", true);
			} else {
				$("input[name='codeInfoId']").attr("checked", false);
			}
		}
	</script>
</body>
</html>