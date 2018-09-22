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
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>

</head>
<body>
	<form action="editBankIntegral.html"  name="bankIntegralForm" id="bankIntegralForm" target="result" enctype="multipart/form-data" method="post">
	<input type="hidden" name="integralImgRulName" value="${integral.integralImgRulName}"/>
	<input type="hidden" name="integralImgRul" value="${integral.integralImgRul}"/>
	<input type="hidden" name="integralId" value="${integral.integralId}"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>积分图片:</th>
			 <td> <input type="file" id="integralImgRul" name="integralImgRulFile"  accept="image/*" class="input_txt" maxlength="150"/>
			 <label style="color: red;" class="red">*</label>
			 </td>
			 <td colspan="2" align="right"><img src="${path}${integral.integralImgRul}" width="30" height="30" alt="${integral.integralImgRulName}"/></td>
			 <th>积分类型:</th>
			<td>
				<select id="integralType" name="integralType"  class="input_txt">
					<option value="">--请选择--</option>
					<option value="1" <c:if test="${integral.integralType=='1'}">selected</c:if>>赚积分</option>
					<option value="0" <c:if test="${integral.integralType=='0'}">selected</c:if>>花积分</option>
				</select>
			<label style="color: red;" class="red">*</label>
			</td>
		</tr>
		<tr class="info">
			<th>积分详情:</th>
		<td colspan="5">
		<textarea name="integralDetailsPc" id="integralDetailsPc" style="width:100%;;visibility:hidden;">${integral.integralDetailsPc}</textarea>
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
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</body>
<script type="text/javascript">
var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="integralDetailsPc"]', {
				cssPath : '${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/editor/uploadEditor.html',
				fileManagerJson : '${pageContext.request.contextPath}/jsp/file_manager_json.jsp',
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
				allowImageRemote : false,//网络图片上传
				items : [ 'undo', 'redo', 'copy','paste','|',
				          'fontsize','forecolor','hilitecolor','bold', 'italic','underline','removeformat','|',
					      'justifyleft','justifycenter', 'justifyright', 'insertorderedlist',
					      'insertunorderedlist', 'indent','outdent','subscript','superscript','|',
					      'table','image' ]

			});
			 
		});
		
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#integralType").val()==""){
					alert("带有*号的必填!");
					$("#integralType").focus();
					return false;
				}
				$("#bankIntegralForm").submit();
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
</html>