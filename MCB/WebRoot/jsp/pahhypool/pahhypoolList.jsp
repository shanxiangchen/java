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
	<form action="happyPooll.html" method="post" name="happys" id="happys">
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th>编号</th>
			<th>热词名称</th>
			<th>热词顺序</th>
			<th>操作</th>
		</tr>
  	 	<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="hp" varStatus="vs">
				<tr class="main_info">
				<th onMouseOver="this.title=this.innerText">${vs.index+1}</th>
				<th onMouseOver="this.title=this.innerText">${hp.happyPoollName}</th>
				<th onMouseOver="this.title=this.innerText">${hp.happyPoollorder}</th>
				<th><a href="javascript:edithappy(${hp.happyPoollId});">编辑</a>|<a href="javascript:happyDel(${hp.happyPoollId});">删除</a></th>
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
    <div class="page_and_btn"> 
	  <div><a href="javascript:addhappy();" class="myBtn"><em>新增</em></a></div>
 	  ${happyPooll.page.pageStr}
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
			$("#happysForm").submit();
		}
		
		function addhappy(){
			var dg = new $.dialog({
				title:'新增热词',
				id:'happy_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'happyPooll/happyAdd.html'
				});
    		dg.ShowDialog();
		}
		
		function edithappy(happyPoollId){
			var dg = new $.dialog({
				title:'编辑热词',
				id:'happy_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'happyPooll/happyupdate.html?happyPoollId='+happyPoollId
				});
    		dg.ShowDialog();
		}
		
		function happyDel(happyId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'happyPooll/delect.html', 
					type: 'POST', 
					data: {happyPoollId:happyId},
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
			   		}
				});
			} 
		} 
		
		

		
	</script>  
</body>
</html>