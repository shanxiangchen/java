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
	width: 165px;
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
	<form action="savePrActivityInfo.html" name="addInfoForm"
		id="addInfoForm" target="result" method="post">
		<table border="0" cellpadding="0" cellspacing="0" align="center">
			<tr class="info">
				<th>权益类型:</th>
				<td><select id="rightsTypeCode" name="rightsTypeCode"
					onchange="home(this)" style="width: 170px; line-height: 25px;">
						<option value="">--请选择--</option>
						<c:forEach items="${listRihtsType}" var="li">
							<option value="${li.rigthsTypeCode},${li.rightsOrderBtn},${li.rightsSelfUsed},${li.rightsHeelUsed}">${li.rigthsTypeName}</option>
						</c:forEach>
				</select> <label style="color: red;">*</label></td>
				<th>是否可预约:</th>
				<td><select id="activityOrder" name="activityOrder"
					style="width: 170px; line-height: 25px;">
						<option value="1">是</option>
						<option value="0">否</option>
				</select> <label style="color: red;">*</label></td>
			</tr>
			<tr class="info">
				<th>服务编码:</th>
				<td><input type="text" id="activityCode" name="activityCode"
					class="input_txt" /> <label style="color: red;">*</label></td>
				<td colspan="3" align="left"><font color="red"><b>请正确填写权益系统中对应服务的编码</b></font></td>
			</tr>
			<tr class="info">
				<th>服务名称:</th>
				<td><input type="text" id="activityName" name="activityName"
					class="input_txt" /> <label style="color: red;">*</label></td>
				 <th>是否展示:</th>
				<td><select id="activitySow" name="activitySow"
					style="width: 170px; line-height: 25px;">
						<option value="1">是</option>
						<option value="0">否</option>
				</select> <label style="color: red;" class="red">*</label>
			</tr>
			<tr class="info">
				<th>是否本人:</th>
				<td><select id="activitySelfUsed" name="activitySelfUsed"
					style="width: 170px; line-height: 25px;">
						<option value="1">是</option>
						<option value="0">否</option>
				</select> <label style="color: red;">*</label></td>
				<th>是否随行:</th>
				<td><select id="activityHeelUsed" name="activityHeelUsed"
					style="width: 170px; line-height: 25px;">
						<option value="1">是</option>
						<option value="0">否</option>
				</select> <label style="color: red;">*</label></td>
			</tr>
			<%-- 		<tr class="info">
				<th>服务开始时间:</th>
				<td><input type="text" name="activityStartDate" value="${bankPrActivityInfo.activityStartDate}"
					  onclick="WdatePicker()" id="activityStartDate"
					style="background-image:url(../images/xiao.gif);   background-repeat:no-repeat;background-position:right center;width:167px;height: 20px;padding:0px;"
					readonly="readonly" /> <label style="color: red;" class="red">*</label>
				</td>
			 
				<th>服务结束时间:</th>
				<td><input type="text" name="activityEndDate" value="${bankPrActivityInfo.activityEndDate}"
					  onclick="WdatePicker()" id="activityEndDate"
					style="background-image:url(../images/xiao.gif);   background-repeat:no-repeat;background-position:right center;width:167px;height: 20px;padding:0px;"
					readonly="readonly" /> <label style="color: red;" class="red">*</label>
				</td>
			</tr> --%>
			<tr class="info">
				<th>按钮名称:</th>
				<td><input type="text" maxlength="5" name="activityOrderName"
					id="activityOrderName" class="input_txt" /> </td>
			</tr>
			<tr class="info">
				<th>备注:</th>
				<td colspan="3"><textarea rows="6" cols="57"
					onpropertychange="if(value.length>1000) value=value.substr(0,1000)"	name="activityContent" id="activityContent"></textarea></td>
			</tr>
		</table>
	</form>

	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
		var dg;
		var count = 0;
		$(document).ready(function() {
			dg = frameElement.lhgDG;
			dg.addBtn('ok', '保存', function() {
				if (!validata()) {
					return;
				}

				if (count != 0) {
					return;
				}
				count = count + 1;
				$("#addInfoForm").ajaxSubmit({
					type : "POST",
					url : "savePrActivityInfo.html",
					dataType : "text",
					success : function(text) {
						if(text =="1"){
							alert("服务编码已存在！");
							dg.cancel();
						}else{
							alert("保存成功！");
							success();
						}
					},
					error : function() {
						alert("保存失败！");
						success();
					}
				});

			});

		});

		function validata() {
			if ($("#rightsTypeCode").val() == "") {
				alert("带有*号必填");
				return false;
			}
			if ($("#activityCode").val() == "") {
				alert("带有*号必填");
				return false;
			}
			var reg=/^[0-9]*$/;
		    if(!reg.test($("#activityCode").val())){
		    	alert("服务编码只能输入数字！");
		    	$("#activityCode").val("");
		    	$("#activityCode").focus();
		    	return false;
		    } else{
				var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
				var	a=document.getElementById("activityCode").value;
				if(pattern.test(a)){
				 alert("服务编码不能包含中文字符!");
		    	 return false;
				}
				}
			
			
			if ($("#activityName").val() == "") {
				alert("带有*号必填");
				return false;
			}
			/* if ($("#activityStartDate").val() == "") {
				alert("带有*号必填");
				return false;
			}
			if ($("#activityEndDate").val() == "") {
				alert("带有*号必填");
				return false;
			} */

			/* if ($("#activityOrderName").val() == "") {
				alert("带有*号必填");
				return false;
			} */

			return true;
		}

		function home(obj) {
			var array = [];
			array = obj.value.split(",");
			var activityOrder = array[1];
			var activitySelfUsed=array[2];
			var activityHeelUsed=array[3];
			if (activityOrder == "0") {
				$("#activityOrder option[value='0']").attr("selected",
						"selected");
				$("#activityOrder").attr("disabled", "disabled");
			} else {
				$("#activityOrder").removeAttr("disabled");
			}
			
			if (activitySelfUsed == "0") {
				$("#activitySelfUsed option[value='0']").attr("selected",
						"selected");
				$("#activitySelfUsed").attr("disabled", "disabled");
			} else {
				$("#activitySelfUsed").removeAttr("disabled");
			}
			
			if (activityHeelUsed == "0") {
				$("#activityHeelUsed option[value='0']").attr("selected",
						"selected");
				$("#activityHeelUsed").attr("disabled", "disabled");
			} else {
				$("#activityHeelUsed").removeAttr("disabled");
			}
		}

		function success() {
			if (dg.curWin.document.forms[0]) {
				dg.curWin.document.forms[0].action = dg.curWin.location + "";
				dg.curWin.document.forms[0].submit();
			} else {
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
	</script>
</body>
</html>