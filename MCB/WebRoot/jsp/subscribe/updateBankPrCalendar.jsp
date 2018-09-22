<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	background-color: #FFFFFF;
	text-align: center;
}

.input_txt {
	width: 150px;
	height: 20px;
	line-height: 20px;
}

.info {
	height: 40px;
	line-height: 40px;
}

.info th {
	text-align: right;
	color: #4f4f4f;
	padding-right: 5px; 
	font-size: 13px;
}

.info td {
	text-align: left;
}
</style>
</head>
<body>
	<form action="updateCalendar.html" name="addInfoForm"
		id="addInfoForm" target="result" method="post">
		<input type="hidden" name="calendarType" value="${bankPrCalendar.calendarType}">
		<input type="hidden" name="calendarId" value="${bankPrCalendar.calendarId}">
		<table border="0" cellpadding="0" cellspacing="0" align="center">
		 <tr class="info">
		    <th>假期类型:</th>
			<td>
			 <select  id="calendarType" disabled="disabled" style="width:150px;">
				<option value="">请选择</option>
				<option value="YD" <c:if test="${bankPrCalendar.calendarType=='YD'}">selected</c:if>>元旦</option>
				<option value="CJ" <c:if test="${bankPrCalendar.calendarType=='CJ'}">selected</c:if>>春节</option>
				<option value="QM" <c:if test="${bankPrCalendar.calendarType=='QM'}">selected</c:if>>清明</option>
				<option value="WY" <c:if test="${bankPrCalendar.calendarType=='WY'}">selected</c:if>>五一</option>
				<option value="DW" <c:if test="${bankPrCalendar.calendarType=='DW'}">selected</c:if>>端午</option>
				<option value="ZQ" <c:if test="${bankPrCalendar.calendarType=='ZQ'}">selected</c:if>>中秋</option>
				<option value="GQ" <c:if test="${bankPrCalendar.calendarType=='GQ'}">selected</c:if>>国庆</option>
				<option value="GZR" <c:if test="${bankPrCalendar.calendarType=='GZR'}">selected</c:if>>工作日</option>
			 </select>
			 <label style="color: red;" class="red">*</label>
			</td>
		 </tr>	
		 <tr class="info">
		    <th>年份:</th>
		    <td>
		     <input type="text" name="calendarYear" id="calendarYear"  value="${bankPrCalendar.calendarYear}" onclick="display()" readonly="readonly" style="background-image:url(../images/xiao.gif);background-repeat:no-repeat;background-position:right center;height:20px;width:150px;padding:0px"/>
		      <label style="color: red;" class="red">*</label>
		    </td>
		</tr>
		<tr class="info">
		   <th>开始日期:</th>
			<td>
			 <input type="text" name="calendarBeginDate" value="${bankPrCalendar.calendarBeginDate}" id="calendarBeginDate"
				  onclick="displayShow()"
				style="background-image:url(../images/xiao.gif);   background-repeat:no-repeat;background-position:right center;width:150px;height: 20px; padding:0px;"
				readonly="readonly" /><label style="color: red;" class="red">*</label>
			</td> 
		 </tr>
		 <tr class="info">
		    <th>结束日期:</th>
		    <td>
				<input type="text" name="calendarEndDate" value="${bankPrCalendar.calendarEndDate}" id="calendarEndDate"
				 onclick="displayShow()"
				style="background-image:url(../images/xiao.gif);   background-repeat:no-repeat;background-position:right center;width:150px;height: 20px; padding:0px;"
				readonly="readonly" /> <label style="color: red;" class="red">*</label>
			</td>
		 </tr>
		 </table>
		</form>
</body>
<iframe name="result" id="result" src="about:blank" frameborder="0"
	width="0" height="0"></iframe>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jsAddress.js"></script>
<script type="text/javascript" src="../js/ajaxform.js"></script>
<script type="text/javascript">
    
	var dg;
	var count = 0;
	$(document).ready(
			function() {
				dg = frameElement.lhgDG;
				dg.addBtn('ok', '保存', function() {
					if (!validata()||!validataTwo()||!validataThree()||count!= 0) {
						return;
					}
					count = count + 1;
					$("#addInfoForm").ajaxSubmit({
						type : "POST",
						url : "updateCalendar.html",
						dataType : "text",
						success : function(text) {
							if(text=="success"){
							  alert("保存成功");
							  success();
							}else if(text=="1"){
							   alert("该年份的假期类型已经存在");
							   count=0;
							   return;
							}else{
							  alert("保存失败！");
							  success();
							}
						},
						error : function() {
							alert("保存失败！");
							success();
						}
					});

				});

				function validata() {
                    if($("#calendarType").val()==""){
                      alert("请选择假期类型");
                      return false;
                    }
                    if($("#calendarYear").val()==""){
                    	alert("年份不能为空");
                    	return false;
                    }
                    if($("#calendarBeginDate").val()==""){
                      alert("假期开始日期不能为空");
                      return false;
                    }
                     if($("#calendarEndDate").val()==""){
                      alert("假期结束日期不能为空");
                      return false;
                    }
					return true;
				}
				
			   function validataTwo() {
                    if($("#calendarBeginDate").val()>$("#calendarEndDate").val()){
                      alert("假期开始日期不能大于假期结束日期");
                      return false;
                    }
					return true;
				}
				
				function validataThree() {
				    var yds=$("#calendarBeginDate").val().split("-")[0];
                    var yde=$("#calendarBeginDate").val().split("-")[0];
                    var year=$("#calendarYear").val();
                    if(yds!=yde){
                       alert("请选择同一年份进行设置");
                       return false;
                    }
                    if(yde!=year||yds!=year){
                    	alert("开始日期、结束日期 请选择"+year);
                        return false;
                    }
					return true;
				}  
				function success() {
					if (dg.curWin.document.forms[0]) {
						dg.curWin.document.forms[0].action = dg.curWin.location
								+ "";
						dg.curWin.document.forms[0].submit();
					} else {
						dg.curWin.location.reload();
					}
					dg.cancel();
				}
				
	});
	function display(){
		WdatePicker({skin:'whyGreen',startDate:'%y',dateFmt:'yyyy'});
	}
    function displayShow(){
		WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'});
	} 
</script>
</html>