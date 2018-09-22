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
	<form action="${pageContext.request.contextPath }/controlLog.html" method="post" name="controlLogForm" id="controlLogForm">
	 <div class="search_div">
		<table >
		<tr>
			<th>告警对象：</th>
			<th>
				<select name="showType" id="showType" style="width:178px;">
				  <option value="">请选择</option>
				  <option value="1"<c:if test="${bankControlShow.showType=='1'}">selected</c:if>>CCGW交易接口</option>
				  <option value="2"<c:if test="${bankControlShow.showType=='2'}">selected</c:if>>制卡系统加密机交易接口</option>
				  <option value="3"<c:if test="${bankControlShow.showType=='3'}">selected</c:if>>中间业务平台加密机交易接口</option>
				  <option value="4"<c:if test="${bankControlShow.showType=='4'}">selected</c:if>>彩信平台交易接口</option>
				  <option value="5"<c:if test="${bankControlShow.showType=='5'}">selected</c:if>>前置到交易平台模拟登录接口</option>
				  <option value="6"<c:if test="${bankControlShow.showType=='6'}">selected</c:if>>Apache服务器监测</option>
			   </select>
			</th>
			<th>告警内容：</th>
			<th>
			 <input type="text" maxlength="50" name="showAlarmInfo" value="${bankControlShow.showAlarmInfo}" />
			</th>  
			<th>告警开始时间：</th>
			<th>
			  <input type="text" name="startDate" value="${bankControlShow.startDate}" onclick="display()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;height:20px;width:150px;padding:0px"/>
			</th>
			<th>告警结束时间：</th>
			<th>
			  <input type="text" name="endDate" value="${bankControlShow.endDate}" onclick="display()" readonly="readonly" style="background-image:url(images/xiao.gif);background-repeat:no-repeat;background-position:right center;height:20px;width:150px;padding:0px"/>
			</th>
			<th><a href="javascript:search();" class="myBtn"><em>查询</em></a></th>	
		</tr>
		</table>
	</div>
	 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="main_table" id="tableId">
		<tr class="main_head">
			<th width="10%"><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>  序号</th>
			<th width="15%">告警对象</th>
			<th width="15%">告警时间</th>
			<th width="30%">告警内容描述</th>
			<th width="20%">备注说明</th>
			<th width="8%">处理情况</th>
		</tr>
		 <c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="cf" varStatus="vs">
				<tr class="main_info">
				<th ><input type="checkbox" type="hidden" name="showId"   value="${cf.showId}"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if></th>
				<th> 
				  <c:choose>
				     <c:when test="${cf.showType=='1'}">
		       			CCGW交易接口
		     		 </c:when>
		     		 <c:when test="${cf.showType=='2'}">
		       			制卡系统加密机交易接口
		     		 </c:when>
		     		 <c:when test="${cf.showType=='3'}">
		       			中间业务平台加密机交易接口
		     		 </c:when>
		     		 <c:when test="${cf.showType=='4'}">
		       			彩信平台交易接口
		     		 </c:when>
		     		 <c:when test="${cf.showType=='5'}">
		       			前置到交易平台模拟登录接口
		     		 </c:when>
		     		 <c:when test="${cf.showType=='6'}">
		       			Apache服务器监测
		     		 </c:when>
			  	  </c:choose>
			    </th>
			    <th>${cf.showInsertTime}</th>
			    <th>${cf.showAlarmInfo}</th>
				<th >${cf.showRemarks}</th>
			    <th >
			       <c:choose>
				     <c:when test="${cf.showIsOk=='1'}">
		       			待解决
		     		 </c:when>
		     		 <c:when test="${cf.showIsOk=='2'}">
		       			已解决
		     		 </c:when>
			  	  </c:choose>
			    </th>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<th colspan="6">没有相关数据</th>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div align="left" style="padding-bottom: 0px;padding-left: 5px"> <span style="color: blue;text-align: left">注：标准阀值=失败次数/(失败次数+成功次数)*100%</span></div> 
    <div class="page_and_btn">
       <div>
	     <a href="javascript:void(0)" onclick="addControlRate()" class="myBtn"> <em>阀值</em></a>
	     <a href="javascript:void(0)" onclick="delControlShow()" class="myBtn"> <em>已解决</em></a> 
	  </div>
	  ${bankControlShow.page.pageStr} 		
    </div>
  	
  	<script type="text/javascript" src="js/style.js"></script>	
	</form>
	 
	<script type="text/javascript">
	    $(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	  
		function search(){
			$("#controlLogForm").submit();
		}
		
		function addControlRate(){
			var dg = new $.dialog({
				title:'标准阀值配置',
				width:600,
				height:370,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'controlLog/addControlRate.html'
				});
    		dg.ShowDialog();
		}
		 
		 function delControlShow() {
				var str = "";
				//验证是否选择了记录
				if ($('#tableId input[name="showId"]:checkbox:checked').length < 1) {
					alert('请选择一条记录！');
					return false;
				} else {
					$('#tableId input[name="showId"]:checkbox:checked')
							.each(function() {
								str += $(this).val() + ",";
							});
					if (confirm("确认已解决？")) {
						$.ajax({
							url : 'controlLog/delControlShow.html',
							type : 'POST',
							data : {
								showIds : str
							},
							dataType : 'text',
							timeout : 1000,
							async : false,
							success : function(text) {
								if (text == "success") {
									alert("已解决");
									document.location.reload();
								} else {
									alert("未解决！");
									document.location.reload();
								}
							},
							error : function() {
								alert("未解决！");
								document.location.reload();
							}
						});
					}
				}
		}
		 
		function sltAllUser() {
				if ($("#sltAll").attr("checked")) {
					$("input[name='showId']").attr("checked", true);
				} else {
					$("input[name='showId']").attr("checked", false);
				}
	    }
		
		function display(){
			WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});
		}
	</script>
</body> 
</html>
