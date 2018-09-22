<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="${pageContext.request.contextPath }/lohasAllocation.html" method="post" name="lohasAllocationForm" id="lohasAllocationForm">
	 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/> 序号</th>
			<th>图片</th>
			<th>链接方式</th>
			<th>链接URL</th>
			<th>链接参数</th>
			<th>操作</th> 
		</tr>
		 <c:choose>
			<c:when test="${not empty allocations}">
				<c:forEach items="${allocations}" var="cf" varStatus="vs">
				<tr class="main_info">
				<th><input type="checkbox" type="hidden" name="lohasId"   value="${cf.lohasShowType}"/>
					<input type="hidden" class="elementId" value="${cf.lohasId}">
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if></th>
				<th ><a href="${path}${cf.lohasImgUrl}"><img src="${path}${cf.lohasImgUrl}" width="30" height="30"></a></th>
				<th><c:if test="${cf.linkType==1}">内链</c:if>
					<c:if test="${cf.linkType==2}">外链</c:if></th>
				
			    <th onmouseover="this.title=this.innerText">
			    	<c:if test="${cf.linkType==1}"><span id="linkName"+${vs.index+1}></span>
				    	<select style="width: 206px;display: none;" class="none" name="lohasOutLinks">
					  		 <c:forEach items="${listname}"  var="li">
								<option value="${li.LINKNO}" <c:if test="${cf.linkNo==li.LINKNO}">selected</c:if>>${li.LINKNAME}</option>
					 		 </c:forEach>
						</select>
			    	</c:if>
			    	<c:if test="${cf.linkType==2}">${cf.lohasOutLink}</c:if>
			    </th>
			    <th name="lohasParam">${cf.lohasParam}</th>
				<th><a href="javascript:editLohas('${cf.lohasId}');">修改</a></th>  
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
	     <a href="javascript:addLohasAllocation()" class="myBtn"><em>新增</em></a>
	     <a href="javascript:void(0)" onclick="delLohasAllocation()" class="myBtn"> <em>删除</em></a>
	  </div> 
	  ${bankLohasAllocation.page.pageStr }
	</div>
	</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
			$("select").each(function(){
				var a = $(this).find('option:selected').text();
				$(this).prev("span").text(a);

			});
			
		});
	 
		
		function addLohasAllocation(){
			var dg = new $.dialog({
				title:'添加乐活配置图片',
				width:850,
				height:400,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'lohasAllocation/addLohasAllocation.html'
				});
    		dg.ShowDialog();
		}
	   function editLohas(lohasId){
			var dg = new $.dialog({
				title:'编辑乐活配置',
				width:700,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'lohasAllocation/editLohasAllocation.html?lohasId='+lohasId
				});
    		dg.ShowDialog();
		}
		
		 
		
		function delLohasAllocation() {
			var str = "";
			//验证是否选择了记录
			if ($('#tableId input[name="lohasId"]:checkbox:checked').length < 1) {
				alert('请选择一条记录再进行删除！');
				return false;
			} else {
				$('#tableId input[name="lohasId"]:checkbox:checked')
						.each(function() {
							var id = $(this).next("input").val();
							str += id + ",";
						});
				if (confirm("确定要删除该记录？")) {
					$.ajax({
						url : 'lohasAllocation/delLohasAllocation.html',
						type : 'POST',
						data : {
							lohasIds : str
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
				$("input[name='lohasId']").attr("checked", true);
			} else {
				$("input[name='lohasId']").attr("checked", false);
			}
		}
		
		 function search(){
			$("#cardInfoForm").submit();
		 }
		 var type,num;
		 $("input[type='checkbox']").click(function(){
			 type=$(this).val();
             if($(this).attr("checked")){
            	 $("input[type='checkbox']").each(function(){
                	 num=$(this).val();
                	 if(type==num){
                		 $(this).attr("checked",true);
                	 }
                 });
             }else{
            	 $("input[type='checkbox']").each(function(){
                	 num=$(this).val();
                	 if(type==num){
                		 $(this).attr("checked",false);
                	 }
                 });
             }
            
        });
	</script>
</body> 
</html>
