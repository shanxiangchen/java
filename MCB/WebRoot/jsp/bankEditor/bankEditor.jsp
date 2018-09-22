<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath}/editor/saveInfo.html" name="editorForm" id="editorForm" method="post" target="result" > 
		  
		     富文本类型：<select name="editorNum"  onchange="kindEditor(this)" style="width:205px;line-height:25px;" id="editorId" >
			     <option selected value="">请选择</option>
		          <c:forEach items="${list}" var="obj">
			        <option value="${obj.editorNum}" <c:if test="${obj.editorNum==editorNum}">selected</c:if>>${obj.editorName}</option>
		         </c:forEach>
		       </select>
			   <label style="color: red;">*</label><br><br> 
		 <textarea name="editorInfo"
			id="editorInfo" style="width:100%;height:230px;visibility:hidden;">
			${bankEditor.editorInfo} 
		</textarea>		    
		 
	</form>
	<form action="${pageContext.request.contextPath}/editor/selectBankEditorById.html" name="form2" id="form2">
	  <input type="hidden" name="editorNum" id="editorNum">
	   
	</form>
	<p>
		<input type="button" name="subform" value="保存" />
		<input type="button" name="clear" value="重置" />  
	</p>
     <iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxform.js"></script>
	<script>
		 
		function success(){
		     dg.curWin.location.reload();
			dg.cancel();
		}
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="editorInfo"]', {
				cssPath : '${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css',
				//uploadJson : '${pageContext.request.contextPath}/jsp/upload_json.jsp',
				uploadJson : '${pageContext.request.contextPath}/editor/uploadEditor.html',
				fileManagerJson : '${pageContext.request.contextPath}/jsp/file_manager_json.jsp',
				allowFileManager : true ,
				allowPreviewEmoticons:true,
				resizeType : 1,
				allowPreviewEmoticons : false,
				allowImageUpload : true,//本地图片上传
				allowImageRemote : true,//网络图片上传
				items : [ 'undo', 'redo', '|', 'fontname', 'fontsize', '|',
						'forecolor', 'hilitecolor', 'bold', 'italic',
						'underline', 'strikethrough','lineheight','removeformat', '|', 'justifyleft',
						'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', 'indent','outdent','subscript','superscript','quickformat',
						'|', 'table', 'hr','image', 'selectall','preview','fullscreen' ]
						

			});
			 
			K('input[name=clear]').click(function(e) {
				editor.html('');
			});
			K('input[name=subform]').click(function(e) {
			      editor.sync();
			      if($("#editorId").val()==""){
			         alert("请选择富文本类型");
			         return;
			      }
			      if(editor.isEmpty()){
			        alert("请输入文本内容！");
			        return;
			      }
			      $("#editorForm").ajaxSubmit({
		          type:"POST",
		          url:"${pageContext.request.contextPath}/editor/saveInfo.html",
		          dataType: "text",
		          success:function(text){
		              count=0;
		              alert("保存成功！");
		             
		          },
		          error:function(){
		              count=0;
		             alert("保存失败！");
		          }
		      });
			     //$("#editorForm").submit();
				 
			});
		});
		
		function kindEditor(obj){
		       $("#editorNum").val(obj.value);
		        
		       $("#form2").submit();
		   
		}
		 
	</script>
</body>
</html>
