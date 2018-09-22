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
	<form action="template.html" method="post" name="TemplateFrom" id="TemplateFrom">
	<div class="search_div">
		<table>
			<tr>
				<th>信息类别:</th>
				<td>
					<select name="infoTemplateType" id="infoTemplateType" >
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${template.infoTemplateType==1}">selected</c:if>>短信提醒类</option>
						<option value="2" <c:if test="${template.infoTemplateType==2}">selected</c:if>>页面成功提示类</option>
				</select> 
				</td>
				<td><a href="javascript:search();" class="myBtn"><em>查询</em></a></td>
				
			</tr>
		</table>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>编号</th>
			<th>信息类别</th>
			<th>信息编码</th>
			<th>信息描述</th>
			<th>操作</th>
		</tr>
 	<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="templates" varStatus="vs">
				<tr class="main_info">
				<th onmouseover="this.title=this.innerText">${vs.index+1}</th>
				<th onmouseover="this.title=this.innerText"><c:if test="${templates.infoTemplateType==1}">短信提醒类</c:if>
					<c:if test="${templates.infoTemplateType==2}">页面成功提示类</c:if>
				</th>
				<th onmouseover="this.title=this.innerText">${templates.infoTemplateEncoding}</th>
				<th onmouseover="this.title=this.innerText">${templates.infoTemplateDescribe}</th>
				<th><a href="javascript:editTemlate(${templates.infoTenplateId });">修改</a> | <a href="javascript:delTemplate(${templates.infoTenplateId });">删除</a></th>
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
	  <div><a href="javascript:addTemplate();" class="myBtn"><em>新增</em></a></div>
	  ${template.page.pageStr}
  	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/template/template_list.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	</script>
</body>
</html>