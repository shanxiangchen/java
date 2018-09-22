 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/city/city_list.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>

	<form action="bankIntegral.html" method="post" name="bankIntegralForm" id="bankIntegralForm">
	<div class="search_div">
	<table >
	<tr>
		<th>积分类型:</th>
		<td>
			<select name="integralType" id="integralType" style="width:100px;">
			<option value="">--请选择--</option>
			<option value="1"<c:if test="${bankIntegral.integralType=='1'}">selected</c:if>>赚积分</option>
			<option value="0"<c:if test="${bankIntegral.integralType=='0'}">selected</c:if>>花积分</option>
			
		</select>
		</td>
		<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>	
	</tr>
	</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>积分图片</th>
			<th>积分类型</th>
			<th>积分详情</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty bankIntegrals}">
				<c:forEach items="${bankIntegrals}" var="li" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1 }</th>
				<th onmouseover="this.title=this.innerText">
				<a href="${path}${li.integralImgRul}" class="preview" >
						<img src="${path}${li.integralImgRul}" width="30" height="30" /></a>
				</th>
				<th onmouseover="this.title=this.innerText">
					<c:if test="${li.integralType=='0'}">花积分</c:if>
					<c:if test="${li.integralType=='1'}">赚积分</c:if>
				</th>
				<th><a href="javascript:selIntegralDetailsPc('${li.integralId}')">积分详情</a></th>
				<th><a href="javascript:editBankIntegral('${li.integralId }');">修改</a> | <a href="javascript:delBankIntegral('${li.integralId}','${li.integralImgRulName}');">删除</a></th>
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
		<div>
			<a href="javascript:addBankIntegral();" class="myBtn"><em>新增</em></a>
		</div>
		 ${bankIntegral.page.pageStr}
	</div>
	</form>
</body>
<script type="text/javascript">

		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
			$("#bankIntegralForm").submit();
		}	
        function addBankIntegral(){
			var dg = new $.dialog({
				title:'新增积分',
				id:'addBankIntegral_new',
				width:830,
				height:420,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankIntegral/addBankIntegral.html'
				});
    		dg.ShowDialog();
		}
	   function editBankIntegral(integralId){
			var dg = new $.dialog({
				title:'积分编辑',
				id:'IntegralDetail_new',
				width:830,
				height:420,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankIntegral/IntegralDetails.html?integralId='+integralId+'&id=2'
				});
    		dg.ShowDialog();
		}
	   function selIntegralDetailsPc(integralId){
			var dg = new $.dialog({
				title:'积分详情',
				id:'IntegralDetail_new',
				width:620,
				height:440,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankIntegral/IntegralDetails.html?integralId='+integralId+'&id=1'
				});
    		dg.ShowDialog();
		}
		
		function delBankIntegral(integralId,integralImgRulName){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'bankIntegral/delBankIntegral.html', 
					type: 'POST', 
					data: {"integralId":integralId,"integralImgRulName":integralImgRulName},
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
</script>
</html>