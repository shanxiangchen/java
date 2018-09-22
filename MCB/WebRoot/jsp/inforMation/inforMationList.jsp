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
	
	<form action="inforMationList.html" method="post" name="custNameForm"  id="custNameForm">
			 <div class="search_div">
				客户姓名：<input type="text"  name="custName" id="custName" value="${custName}"/>
				&nbsp;&nbsp;&nbsp;
				身份证号：<input type="text"  name="custCardId" id="custCardId" value="${custCardId}"/>
				<a href="javascript:search();" class="myBtn"><em>查询</em></a>
			</div>
			<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
				<tr class="main_head">
					<th>编号</th>
					<th>客户姓名</th>
					<th>客户拼音</th>
					<th>客户性别</th>
					<th>客户邮箱</th>
					<th>婚姻状态</th>
					<th>教育程度</th>
					<th>当前住址</th>
					<th>详细住址</th>
					<th>住宅邮编</th>
					<th>手机号码</th>
					<th>客户身份证号</th>
					<th>操作</th>
				</tr>
		  	 	<c:choose>
					<c:when test="${not empty pageBean.list}">
						<c:forEach items="${ pageBean.list}" var="im" varStatus="vs">
						<tr class="main_info">
						<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
						<th onMouseOver="this.title=this.innerText">${im.custName}</th>
						<th onMouseOver="this.title=this.innerText">${im.custNameAbc}</th>
						<th onMouseOver="this.title=this.innerText">
						<c:if test="${im.custSex=='1'}">男</c:if>
						<c:if test="${im.custSex=='2'}">女</c:if>
						</th>
						<th onMouseOver="this.title=this.innerText">${im.custEmail}</th>
						<th onMouseOver="this.title=this.innerText">
						<c:if test="${im.custIsmarry=='1'}">未婚</c:if>
						<c:if test="${im.custIsmarry=='2'}">已婚</c:if>
						<c:if test="${im.custIsmarry=='3'}">其他</c:if>
						</th>
						<th onMouseOver="this.title=this.innerText">${im.custEducation}</th>
						<th onMouseOver="this.title=this.innerText">${im.custNowAddress}</th>
						<th onMouseOver="this.title=this.innerText">${im.custMoreAddress}</th>
						<th onMouseOver="this.title=this.innerText">${im.custZipCode}</th>
						<th onMouseOver="this.title=this.innerText">${im.custPhoneNum}</th>
						<th onMouseOver="this.title=this.innerText">${im.custCardId}</th>
						<th><a href="javascript:details('${im.custId}')">详情</a></th>
						</tr>
						
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="13">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
<div class="page_and_btn"><div></div></div>
	<!--<div style="float:left; width:100px; height:100px;"><a href="javascript:addhappy();" class="myBtn"><em>新增</em></a></div>-->
 	<div style="float:right; height:100px;">当前第<font color="blue"><b>${pageBean.pageNo }</b></font>页 一共有<font color="red"><b>${pageBean.pageTotalPages }</b></font>页&nbsp;共有<font color="blue"><b>${pageBean.totalRecordes }</b></font>条记录			
  			<c:if test="${pageBean.pageTotalPages!=1 && pageBean.pageTotalPages!=0}">
  				 &nbsp;<a href="${pageContext.request.contextPath}/happyPooll.html?pageNo=${pageBean.homePage }&pageSize=${pageBean.pageSize}">首页</a>
	  				<a href="${pageContext.request.contextPath }/happyPooll.html?pageNo=${pageBean.upprPage}&pageSize=${pageBean.pageSize }">上一页</a>
	  				<a href="${pageContext.request.contextPath }/happyPooll.html?pageNo=${pageBean.downPage}&pageSize=${pageBean.pageSize }">下一页</a>
	  				<a href="${pageContext.request.contextPath }/happyPooll.html?pageNo=${pageBean.endPage }&pageSize=${pageBean.pageSize }">尾页</a>
  			</c:if>
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
			$("#custNameForm").submit();
		} 
		
		function details(custId){
 		var dg = new $.dialog({
				title:'详情',
				width:680,
				height:400,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'inforMationList/details.html?custId='+custId
				});
    		dg.ShowDialog();
			
		}
		

		
	</script>  
</body>
</html>