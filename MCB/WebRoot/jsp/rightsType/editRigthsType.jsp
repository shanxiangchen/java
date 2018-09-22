<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信业银行信用卡APP后台管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css" />
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:160px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
.none{display: none;}
</style>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="editRigthsType.html" name="rigthsTypeForm" id="rigthsTypeForm" target="result" method="post" enctype="multipart/form-data">
		<input type="hidden" name="rigthsTypeId" id="rigthsTypeId" value="${rightsType.rigthsTypeId}"/>
		<input type="hidden" name="rigthsTypeUrl" id="rigthsTypeUrl" value="${rightsType.rigthsTypeUrl}">
		<input type="hidden" name="rigthsTypeUrlName" id="rigthsTypeUrlName" value="${rightsType.rigthsTypeUrlName}">
		<input type="hidden" name="rigthsTypeIconUrl" id="rigthsTypeIconUrl" value="${rightsType.rigthsTypeIconUrl}">
		<input type="hidden" name="rigthsTypeIconUrlNmae" id="rigthsTypeIconUrlNmae" value="${rightsType.rigthsTypeIconUrlNmae}">
	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tr class="info">
			<th>权益编码:</th>
			<td><input type="text" name="rigthsTypeCode" readonly="readonly" id="rigthsTypeCode" disabled="disabled" value="${rightsType.rigthsTypeCode}" class="input_txt"/></td>
			<th>权益名称:</th>
			<td align="left"><input type="text" name="rigthsTypeName" id="rigthsTypeName" value="${rightsType.rigthsTypeName}" class="input_txt"/>
				<label style="color: red;">*</label>
			</td>
		</tr>
 		<tr class="info">
			<th>权益图片:</th>
			<td><input type="file" name="rigthsTypeUrlFile" style="width: 237px; line-height: 25px;" id="rigthsTypeUrlFile" accept="image/*" maxlength="150"/>
				<img src="${path}${rightsType.rigthsTypeUrl}" width="30" height="30" alt="${rightsType.rigthsTypeUrlName}"/>
				<label style="color: red;">*</label>
			</td>
			<th>权益图标:</th>
			<td>
				<input type="file" name="rigthsTypeIconUrlFile" style="width: 237px; line-height: 25px;" id="rigthsTypeIconUrlFile" accept="image/*"  maxlength="150"/>
				<img src="${path}${rightsType.rigthsTypeIconUrl}" width="30" height="30" alt="${rightsType.rigthsTypeIconUrlNmae}"/>
				<label style="color: red;">*</label>
			</td>	
		</tr> 
		
		<tr class="info">
			<th>是否明细</th>
			<td>
				<select id="rightsDetailBtn" name="rightsDetailBtn" style="width: 166px; line-height: 25px;">
					<option value="1"<c:if test="${rightsType.rightsDetailBtn=='1'}">selected</c:if>>是</option>
					<option value="0"<c:if test="${rightsType.rightsDetailBtn=='0'}">selected</c:if>>否</option>
				</select>
				<label style="color: red;">*</label>
			</td>
			<th>是否预约:</th>
			<td>
				<select id="rightsOrderBtn" name="rightsOrderBtn" style="width: 166px; line-height: 25px;">
					<option value="1"<c:if test="${rightsType.rightsOrderBtn=='1'}">selected</c:if>>是</option>
					<option value="0"<c:if test="${rightsType.rightsOrderBtn=='0'}">selected</c:if>>否</option>
				</select>
				<label style="color: red;">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>是否本人:</th>
			<td>
				<select id="rightsSelfUsed" name="rightsSelfUsed" style="width: 166px; line-height: 25px;">
					<option value="1"<c:if test="${rightsType.rightsSelfUsed=='1'}">selected</c:if>>是</option>
					<option value="0"<c:if test="${rightsType.rightsSelfUsed=='0'}">selected</c:if>>否</option>
				</select>
				<label style="color: red;">*</label>
			</td>
			<th>是否随行:</th>
			<td>
				<select id="rightsHeelUsed" name="rightsHeelUsed" style="width: 166px; line-height: 25px;">
					<option value="1"<c:if test="${rightsType.rightsHeelUsed=='1'}">selected</c:if>>是</option>
					<option value="0"<c:if test="${rightsType.rightsHeelUsed=='0'}">selected</c:if>>否</option>
				</select>
				<label style="color: red;">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>本人次数最大值:</th>
			<td><input type="text" id="rightsMyNum" name="rightsMyNum" value="${rightsType.rightsMyNum}" maxlength="4" class="input_txt">
			</td>
			<th>随行次数最大值:</th>
			<td><input type="text" id="rightsTogetherNum" name="rightsTogetherNum" value="${rightsType.rightsTogetherNum}" maxlength="4" class="input_txt"></td>
		</tr>
		<tr class="info">
			<th>按钮名称1:</th>
			<td><input type="text" id="rightsDetailName" name="rightsDetailName" value="${rightsType.rightsDetailName}" maxlength="6" class="input_txt">
				<label style="color: red;">*</label>
			</td>
			<th>按钮名称2:</th>
			<td><input type="text" id="rightsOrderName" name="rightsOrderName" value="${rightsType.rightsOrderName}" maxlength="6" class="input_txt">
				<label style="color: red;">*</label>
			</td>
		</tr>
		<tr class="info">
		     <th>预约计算方式:</th>
			 <td><select id="calculation" onchange="changeCalculation(this)" name="calculation"
				style="width: 166px; line-height: 25px;">		
				    <option value="0" <c:if test="${rightsType.calculation=='0'}">selected</c:if>>按小时</option>
					<option value="1" <c:if test="${rightsType.calculation=='1'}">selected</c:if>>自然日</option>
					<option value="2" <c:if test="${rightsType.calculation=='2'}">selected</c:if>>工作日</option>
			    </select>
			    <label style="color: red;">*</label>
			</td>
			<th>最多预约天数:</th>
			<td><input type="text" maxlength="5" name="rightsMaxBeforeDay"
				id="rightsMaxBeforeDay" value="${rightsType.rightsMaxBeforeDay}" class="input_txt" />
			</td>
		 </tr>
		 <tr class="info">
		    <th>提前预约天数:</th>
			<td><input type="text" maxlength="5" name="rightsBeforeDay"
				id="rightsBeforeDay" value="${rightsType.rightsBeforeDay}" class="input_txt" />
				<input type="hidden" maxlength="5" 
				id="rightsBeforeDays" value="${rightsType.rightsBeforeDay}" class="input_txt" />
			</td>
			<th>提前预约小时:</th>
			<td><input type="text" maxlength="5" name="rightsBeforeHour"
				id="rightsBeforeHour" value="${rightsType.rightsBeforeHour}" class="input_txt" />
				<input type="hidden" maxlength="5" 
				id="rightsBeforeHours" value="${rightsType.rightsBeforeHour}" class="input_txt" />
			</td>
		 </tr>
		
		<tr class="info">
			<th>权益描述:</th>
			<td colspan="4">
				<textarea style="width:100%;visibility:hidden;" name="rightsInDescribe" id="rightsInDescribe">${rightsType.rightsInDescribe}</textarea>
				<label style="color: red;">*</label>
			</td>
		</tr>
		<tr>
		  <td align="right"></td>
		  <td align="left"><p>您当前输入了 <span style="color:blue" class="word_count1">0</span> 个文字，还可输入<span style="color:blue" class="word_count2">0</span> 个文字。<span style="color:red" class="word_count3"></span></p>
		  </td>
		</tr>
	</table> 
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js"></script>
</body>
<script type="text/javascript">	
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="rightsInDescribe"]', {
				cssPath : '${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/editor/uploadEditor.html',
				//fileManagerJson : '${pageContext.request.contextPath}/jsp/file_manager_json.jsp',
				allowFileManager : true ,
				allowPreviewEmoticons:true,
				resizeType : 1,
				height:"280px",
				afterCreate : function() { 
			       this.sync(); 
			    }, 
			    afterBlur:function(){ 
			       this.sync(); 
			    }, 
			    afterChange : function() {
					  var limitNum = 1500;  //设定限制字数
					  K('.word_count1').html(this.count("text"));
				      var maxNum=eval(limitNum-this.count("text"));
				      K('.word_count2').html(maxNum);
					  if(this.count("text")>limitNum){
					     K('.word_count3').html("字数超过限制，请适当删除部分内容");
					     //超过字数限制自动截取
					    /*  var strValue = editor.text();
					     strValue = strValue.substring(0,limitNum);
					     editor.text(strValue);   */  
					  }else{
					     K('.word_count3').html("");
					  } 		 
				},
				allowPreviewEmoticons : false,
				allowImageUpload : true,//本地图片上传
				allowImageRemote : true,//网络图片上传
				items : [ 'undo', 'redo', 'copy','paste','|',
				          'fontsize','forecolor','hilitecolor','bold', 'italic','underline','removeformat','|',
					      'justifyleft','justifycenter', 'justifyright', 'insertorderedlist',
					      'insertunorderedlist', 'indent','outdent','subscript','superscript','|',
					      'table','image','preview','selectall','fullscreen' ]
		
			});
			 
		});
		$(function(){
			var obj=$("#calculation").val();
			switch(obj){
				case '0':
					$("#rightsBeforeHour").removeAttr("disabled");
					$("#rightsBeforeDay").attr("disabled",true);
					$("#beforeDayFlag").addClass("none");
					$("#beforeHourFlag").removeClass("none");
					break;
				case '1':
					$("#rightsBeforeHour").attr("disabled",true);
					$("#rightsBeforeDay").removeAttr("disabled");
					$("#beforeDayFlag").removeClass("none");
					$("#beforeHourFlag").addClass("none");
				 
					break;
				case '2':
					$("#rightsBeforeHour").attr("disabled",true);
					$("#rightsBeforeDay").removeAttr("disabled");
					$("#beforeDayFlag").removeClass("none");
					$("#beforeHourFlag").addClass("none");
					break;	
			} 
		});
		
		/* 匹配数字 */
		var compareNum = new RegExp("^[0-9]*$");
		function testNum(){
			/* 获取计算方式 */
			var calculationNum = $.trim($("#calculation").val());
			/* 获取预定天数 */
			var rightsBeforeDayNum = $.trim($("#rightsBeforeDay").val());
			/* 获取提前预定天数 */
			var rightsMaxBefoDayNum = $.trim($("#rightsMaxBeforeDay").val());
			/* 获取提前预定小时 */
			var rightsBeforeHourNum = $.trim($("#rightsBeforeHour").val());
			/* 判断逻辑 */
			if(!compareNum.test(rightsMaxBefoDayNum)){
				alert("最大预约天数请输入数字或为空！");
				return false;
			}else{
				if(calculationNum == 0){
					if(!compareNum.test(rightsBeforeHourNum)){
						alert("提前预约小时请输入0到168以内的数字或为空！");
						return false;
					}
					if(rightsBeforeHourNum>168||rightsBeforeHourNum<0){
						alert("提前预约小时请输入0到168以内的数字或为空！");
						return false;
					}
				}else{
					if(!compareNum.test(rightsBeforeDayNum)){
						alert("提前预约天数请输入0到7以内的数字或为空！");
						return false;
					}
					if(rightsBeforeDayNum>7||rightsBeforeDayNum<0){
						alert("提前预约天数请输入0到7以内的数字或为空！");
						return false;
					}
				}
				$("#rightsBeforeDay").val(rightsBeforeDayNum);
				$("#rightsMaxBeforeDay").val(rightsMaxBefoDayNum);
				$("#rightsBeforeHour").val(rightsBeforeHourNum);
				return true;
			}
		}
        
		function changeCalculation(obj){
			var rightsBerforHours = $.trim($("#rightsBeforeHours").val());
			var rightsBerforHour = $.trim($("#rightsBeforeHour").val());
			var rightsBeforeDay = $.trim($("#rightsBeforeDay").val());
			var rightsBeforeDays = $.trim($("#rightsBeforeDays").val());
			switch(obj.value){
				case '0':
					$("#rightsBeforeHour").removeAttr("disabled");
					$("#rightsBeforeDay").attr("disabled",true);
					$("#rightsBeforeDays").val(rightsBeforeDay);
					$("#rightsBeforeHour").val(rightsBerforHours);
					$("#rightsBeforeDay").val("");
					$("#beforeDayFlag").addClass("none");
					$("#beforeHourFlag").removeClass("none");
					break;
				case '1':
					$("#rightsBeforeHour").attr("disabled",true);
					$("#rightsBeforeDay").removeAttr("disabled");
					$("#rightsBeforeHours").val(rightsBerforHour);
					$("#rightsBeforeDay").val(rightsBeforeDays);
					$("#rightsBeforeHour").val("");
					$("#beforeDayFlag").removeClass("none");
					$("#beforeHourFlag").addClass("none");
					break;
				case '2':
					$("#rightsBeforeHour").attr("disabled",true);
					$("#rightsBeforeDay").removeAttr("disabled");
					$("#rightsBeforeHours").val(rightsBerforHour);
					$("#rightsBeforeDay").val(rightsBeforeDays);
					$("#rightsBeforeHour").val("");
					$("#beforeDayFlag").removeClass("none");
					$("#beforeHourFlag").addClass("none");
					break;	
			} 
			
		}
        
        
		
		function addClick(){
			var dg = new $.dialog({
				title:'权益包编码',
				id:'RigthsType_new',
				width:650,
				height:350,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'../rt/rim.html'
				});
    		dg.ShowDialog();
		}
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){	
				var rigthsTypeUrlFile = document.getElementById("rigthsTypeUrlFile").value;
				var rigthsTypeIconUrlFile = document.getElementById("rigthsTypeIconUrlFile").value;
				if($.trim($("#rigthsTypeCode").val())==""){
					alert("权益编码不能为空");
					return false;
				}else if($.trim($("#rigthsTypeName").val())==""){
					alert("权益名称不能为空");
					return false;
				}else if($("#rigthsTypeUrl").val()=="" && $("#rigthsTypeUrl").val()==""){
					alert("请选择权益图片");
					return false;
				}else if($("#rigthsTypeIconUrl").val()=="" && $("#rigthsTypeIconUrlFile").val()==""){
					alert("请选择权益图标");
					return false;
				}else if($("#rightsDetailName").val()==""){
					alert("请输入按钮名称");
					return false;
				}else if($("#rightsOrderName").val()==""){
					alert("请输入按钮名称");
					return false;
				}else if($("#rightsInDescribe").val()==""){
					alert("请输入权益描述");
					return false;
				}
				var reg=/^[0-9]*$/;
				if($.trim($("#rightsMyNum").val())!=""){
					if(!reg.test($("#rightsMyNum").val())){
						alert("本人次数最大值只能输入数字");
						$("#rightsMyNum").val("");
						return false;
					}
				}
				
				if(!checkImgName(rigthsTypeUrlFile)){
					return false;
				}
				if(!checkImgName(rigthsTypeIconUrlFile)){
					return false;
				}
				
				if($.trim($("#rightsTogetherNum").val())!=""){
					if(!reg.test($("#rightsTogetherNum").val())){
						alert("随行次数最大值只能输入数字");
						$("#rightsTogetherNum").val("");
						return false;
					}
				}
				
				if(!testNum()){
					return false;
				}
				
				$("#rigthsTypeForm").submit();
				return true;
			});
		});
		
	function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		
</script>
</body>	
</html>