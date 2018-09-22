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
.input_txt{width:150px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>

</head>
<body>
	<form action="updatesave.html" name="CarBrandForm" id="CarBrandForm" target="result" method="post" enctype="multipart/form-data">
		<input type="hidden" name="brandId" id="brandId" value="${bankCarBrand.brandId}"/>
		<input type="hidden" name="imgAddressUrl" id="imgAddressUrl" value="${bankCarBrand.imgAddressUrl}"/>
		<input type="hidden" name="imgName" id="imgName" value="${bankCarBrand.imgName}"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
		<th>品牌名称:</th>
			<td>
				<input type="text" name="brandName" id="brandName" value="${bankCarBrand.brandName}" style="width:155px"/>
				<label style="color: red;" class="red"  >*</label>
			</td>
			   <th>品牌图片:</th>
		       <td><input type="file" id="imgAddressUrl" name="imgAddressUrlFile"   accept="image/*"   class="input_txt" maxlength="100"/>&nbsp;&nbsp;
				<td align="left"><img src="${path}${bankCarBrand.imgAddressUrl}" width="30" height="30" alt="${bankCarBrand.imgAddressUrl}"/></td>
		</tr> 
		<tr class="info">
			<th>品牌详情:</th>
			<td colspan="3">
				<textarea name="brandDetailsPc" id="brandDetailsPc" style="width:100%;visibility:hidden;">${bankCarBrand.brandDetailsPc}</textarea>
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
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/carBrand/carBrand_update.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js"></script>
</body>
<script type="text/javascript">   
	    var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="brandDetailsPc"]', {
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
</script>
</html>