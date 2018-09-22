<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="rightsFiled.html" method="post" name="rightsFiledForm"  id="rightsFiledForm">
	      <div class="search_div">
			字段中文名称： <input type="text" name="filedName" value="${filedName}">
			    <a href="javascript:search();" class="myBtn"><em>查询</em></a>
			</div>
			<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
				<tr class="main_head">
				   <!--  <th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/> 序号</th> -->
					<th>字段中文名称</th>
					<th>字段英文名称</th>
					<th>表单类型</th>
					<th>操作</th>  
				</tr>
		  	 	<c:choose>
			      <c:when test="${not empty pageBean.list}">
				  <c:forEach items="${pageBean.list}" var="list" varStatus="vs">
				  <tr class="main_info">
				    <%-- <td ><input type="checkbox" type="hidden" name="filedId"   value="${list.filedId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if></td> --%>
					<th>${list.filedName}</th>
					<th>${list.filedAbcName}</th>
					<th> 
					    <c:choose>
					    <c:when test="${list.filedFormType=='0'}">
					             反显
					    </c:when>
					    <c:when test="${list.filedFormType=='1'}">
					              日期
					    </c:when>
					     <c:when test="${list.filedFormType=='2'}">
					              城市
					    </c:when>
					     <c:when test="${list.filedFormType=='3'}">
					              服务 
					    </c:when>
					    <c:when test="${list.filedFormType=='4'}">
					             计数
					    </c:when>
					    <c:when test="${list.filedFormType=='5'}">
					             单选
					    </c:when>
					    <c:when test="${list.filedFormType=='6'}">
					              文本
					    </c:when>
					    <c:when test="${list.filedFormType=='7'}">
					              提示
					    </c:when>
					  </c:choose>
					</th>
					<th><a href="javascript:editRightsFiled('${list.filedId}');">修改</a></th>  
				  </tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="4">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"><div></div></div>
	 <div style="float:left;  height:100px;">
	     <a href="javascript:addRightsFiled()" class="myBtn"><em>新增</em></a>
	     <!-- <a href="javascript:void(0)" onclick="delRightsFiled()" class="myBtn"> <em>删除</em></a>  -->
	</div>
    <div style="float:right; height:100px;">当前第<font color="blue"><b>${pageBean.pageNo }</b></font>页 一共有<font color="red"><b>${pageBean.pageTotalPages }</b></font>页&nbsp;共有<font color="blue"><b>${pageBean.totalRecordes }</b></font>条记录
  				<c:if test="${pageBean.pageTotalPages!=1 && pageBean.totalRecordes!=0}">
  				 &nbsp;<a href="${pageContext.request.contextPath }/rightsFiled.html?pageNo=${pageBean.homePage }&pageSize=${pageBean.pageSize}&filedName=${filedName}">首页</a>
	  				<a href="${pageContext.request.contextPath }/rightsFiled.html?pageNo=${pageBean.upprPage    }&pageSize=${pageBean.pageSize}&filedName=${filedName}" >上一页</a>
	  				<a href="${pageContext.request.contextPath }/rightsFiled.html?pageNo=${pageBean.downPage    }&pageSize=${pageBean.pageSize}&filedName=${filedName}">下一页</a>
	  				<a href="${pageContext.request.contextPath }/rightsFiled.html?pageNo=${pageBean.endPage     }&pageSize=${pageBean.pageSize}&filedName=${filedName}">尾页</a>
  				</c:if>
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
	
		 function search(){
				$("#rightsFiledForm").submit();
		 } 
		  
		 function addRightsFiled(){
		  
				 var dg = new $.dialog({
					title:'添加权益预约字段',
					width:800,
					height:250,
					iconTitle:false,
					cover:true,
					maxBtn:false,
					xButton:true,
					resize:false,
					page:'rightsFiled/addRightsFiled.html'
					});
	    		dg.ShowDialog(); 
		}
	    function editRightsFiled(filedId){
			var dg = new $.dialog({
				title:'编辑权益预约字段',
				width:800,
				height:250,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'rightsFiled/editRightsFiled.html?filedId='+filedId
			});
    		dg.ShowDialog();
		}
		
		  
		function delRightsFiled() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="filedId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="filedId"]:checkbox:checked')
						.each(function() {
							str += $(this).val() + ","
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'rightsFiled/delRightsFiled.html',
						type : 'POST',
						data : {
							filedIds : str
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
				$("input[name='filedId']").attr("checked", true);
			} else {
				$("input[name='filedId']").attr("checked", false);
			}
		}
		  
		 
	</script>  
</body>
</html>