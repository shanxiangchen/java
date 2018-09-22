<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="bankPrSave.html" method="post" name="bankPrSaveForm"  id="bankPrSaveForm">
	      <div class="search_div">
			权益类型：<select name="typeId" id="typeId" style="width:205px;">
				     <option value="">请选择</option>
				     <c:forEach items="${typeList}" var="li">
				        <option value="${li.VALUE}" <c:if test="${li.VALUE==typeId}">selected</c:if>>${li.CONTENT}</option>
				      </c:forEach>
			      </select>
			<a href="javascript:search();" class="myBtn"><em>查询</em></a>
		    </div> 
			<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
				<tr class="main_head">
				    <th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/> 序号</th>
					<th>卡片ID</th>
					<th>权益类型</th>
					<th>操作</th>
				</tr>
		  	 	<c:choose>
			      <c:when test="${not empty pageBean.list}">
				  <c:forEach items="${pageBean.list}" var="list" varStatus="vs">
				  <tr class="main_info">
				    <th ><input type="checkbox" type="hidden" name="prSaveId"   value="${list.prSaveId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if></th>
					<th>${list.cardId}</th>
					<th>${list.typeName}</th>
					<th> 
					    <a href="javascript:infoBankPrSave('${list.prSaveId}');">详情</a>
					</th>
				  </tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="3">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"><div></div></div>
	 <div style="float:left;  height:100px;">
	     <a href="javascript:void(0)" onclick="delBankPrSave()" class="myBtn"> <em>删除</em></a> 
	</div>
    <div style="float:right; height:100px;">当前第<font color="blue"><b>${pageBean.pageNo }</b></font>页 一共有<font color="red"><b>${pageBean.pageTotalPages }</b></font>页&nbsp;共有<font color="blue"><b>${pageBean.totalRecordes }</b></font>条记录
  				<c:if test="${pageBean.pageTotalPages!=1 && pageBean.totalRecordes!=0}">
  				 &nbsp;<a href="${pageContext.request.contextPath }/bankPrSave.html?pageNo=${pageBean.homePage }&pageSize=${pageBean.pageSize}&typeId=${typeId}">首页</a>
	  				<a href="${pageContext.request.contextPath }/bankPrSave.html?pageNo=${pageBean.upprPage    }&pageSize=${pageBean.pageSize}&typeId=${typeId}" >上一页</a>
	  				<a href="${pageContext.request.contextPath }/bankPrSave.html?pageNo=${pageBean.downPage    }&pageSize=${pageBean.pageSize}&typeId=${typeId}">下一页</a>
	  				<a href="${pageContext.request.contextPath }/bankPrSave.html?pageNo=${pageBean.endPage     }&pageSize=${pageBean.pageSize}&typeId=${typeId}">尾页</a>
  				</c:if>
   </div>
	</form>
  
	 <script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	
		 function search(){
				$("#bankPrSaveForm").submit();
		 } 
		  
		 function infoBankPrSave(prSaveId){
		   
			 var dg = new $.dialog({
				title:'权益预约',
				cancelBtnTxt:'关闭',
				width:650,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankPrSave/infoBankPrSaveById.html?prSaveId='+prSaveId
			 });
	    		dg.ShowDialog();   
		}
	    
		function delBankPrSave() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="prSaveId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="prSaveId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'bankPrSave/delBankPrSave.html',
						type : 'POST',
						data : {
							prSaveIds : str
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
				$("input[name='prSaveId']").attr("checked", true);
			} else {
				$("input[name='prSaveId']").attr("checked", false);
			}
		}
		  
		 
	</script>  
</body>
</html>