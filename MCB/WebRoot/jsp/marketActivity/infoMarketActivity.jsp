<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css" />
</head>
<body onload="change()">
	<form action="updateOk.html" name="marketActivityForms" id="marketActivityForms" target="result" method="post"  enctype="multipart/form-data">
	  <div align="center">
	   <table>
	   <c:forEach items="${li}" var="marketActivity" varStatus="vs">
	    
		<tr>
			<td align="right">活动Id:<br/><br/></td>
			<td align="left">
	   			 <input type="text" disabled="disabled"  name="activityId" id="activityId" value="${marketActivity.activityId}"    style="width:200px"/><br><br>
			</td>
			
			<td align="right">活动编号:<br><br></td>
			<td align="left">
			  <input type="text" disabled="disabled"  size="40" value="${marketActivity.activityNo}" maxlength="33" style="width:200px"/><br><br>
			</td>
		</tr>
		<tr>
			<td align="right">活动标题:<br/><br/></td>
			<td align="left">
	   			 <input type="text" disabled="disabled"id="activityTitle" name="activityTitle" size="40" value="${marketActivity.activityTitle}" maxlength="33" style="width:200px"/><br><br>
			</td>
			
			<td align="right">活动类型:<br><br></td>
			<td align="left">
			  <input type="text" disabled="disabled"  size="40" value="${marketActivity.activityType.activityType}" maxlength="33" style="width:200px"/><br><br>
			</td>
		</tr>
		<tr>		
		   <td align="right">活动置顶:<br><br></td>
		   <td align="left">
		     <c:if test="${marketActivity.isTop=='1'}">
		        <input type="text" disabled="disabled"  size="40" value="是" style="width:200px"/><br><br>
		     </c:if>
		      <c:if test="${marketActivity.isTop=='2'}">
		        <input type="text" disabled="disabled"  size="40" value="否" style="width:200px"/><br><br>
		     </c:if>
		  </td>
		  
		  <td align="right">活动区域性质:<br><br></td>
		  <td align="left">
		     <c:if test="${marketActivity.activityAreaQuale=='1'}">
		        <input type="text" disabled="disabled"  size="40" value="全国性活动"  style="width:200px"/><br><br>
		     </c:if>
		      <c:if test="${marketActivity.activityAreaQuale=='2'}">
		        <input type="text" disabled="disabled"  size="40" value="区域性活动"  style="width:200px"/><br><br>
		     </c:if>
		 </td>
		</tr>
		
		<tr>
		    <td align="right">活动展示顺序:<br><br></td>
		    <td align="left">
		  		 <input type="text" id="activityShowOrder" disabled="disabled"name="activityShowOrder"   value="${marketActivity.activityShowOrder}"   style="width:200px"/><br><br>
		    </td>
			
			<td align="right">区域性活动城市:<br/><br/></td>
			<td align="left">
			  <input type="text" id="city" name="city" value="${citysName}" disabled="disabled" style="width:200px"/><br><br>
			</td>
		</tr>
		
		<tr>
			<td align="right">活动详情简介:<br><br></td>
			<td align="left"> 
				<input type="text" disabled="disabled"id="activityInfo" name="activityInfo" value="${marketActivity.activityInfo}" maxlength="65" style="width:200px"/><br><br>
			</td>
			
			<td align="right">活动状态:<br><br></td>
		    <td align="left"> 
		        <c:if test="${marketActivity.activityStatus=='0'}">
		          <input type="text" id="activityStatus" name="activityStatus" value="未发布" disabled="disabled" style="width:200px"/><br><br>
		        </c:if>
		        <c:if test="${marketActivity.activityStatus=='1'}">
		          <input type="text" id="activityStatus" name="activityStatus" value="已开始" disabled="disabled"style="width:200px"/><br><br>
		        </c:if>
		        <c:if test="${marketActivity.activityStatus=='2'}">
		          <input type="text" id="activityStatus" name="activityStatus" value="已结束" disabled="disabled" style="width:200px"/><br><br>
		        </c:if>
		        <c:if test="${marketActivity.activityStatus=='3'}">
		          <input type="text" id="activityStatus" name="activityStatus" value="已过期" disabled="disabled" style="width:200px"/><br><br>
		        </c:if>
		   </td>
		</tr>
		<tr>
			<td align="right">活动详情url:<br/><br/></td>
			<td align="left">
			<input type="text" disabled="disabled"id="staticHtmlUrl" name="staticHtmlUrl" value="${marketActivity.staticHtmlUrl}"   style="width:200px"/><br><br>
			</td>
			
			<td align="right">是否允许报名:<br/><br/></td>
			<td align="left">
			  <c:if test="${marketActivity.isApply=='0'}">
			    <input type="text"  disabled="disabled"value="否"   style="width:200px"/><br><br>
			  </c:if>
			  <c:if test="${marketActivity.isApply=='1'}">
			    <input type="text"  disabled="disabled"value="是"   style="width:200px"/><br><br>
			  </c:if>
			</td>
		</tr>
		<tr>
			<td align="right">活动开始日期:<br><br></td>
			<td align="left">
				<input type="text" disabled="disabled"id="activityStartDate" name="activityStartDate"  value="<fmt:parseDate value="${marketActivity.activityStartDate}" var="date1"></fmt:parseDate><fmt:formatDate value="${date1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'%y-%M-{%d}'})" disabled="disabled"style="width:200px"/><br><br>
			</td>
			
			<td align="right">搭子类型:<br/><br/></td>
			<td align="left">
			    <c:if test="${marketActivity.activityTwo=='F'}">
			       <input type="text"  disabled="disabled"value="饭搭子"   style="width:200px"/><br><br>
			    </c:if>
			    <c:if test="${marketActivity.activityTwo=='W'}">
			       <input type="text"  disabled="disabled"value="玩搭子"   style="width:200px"/><br><br>
			    </c:if>
			    <c:if test="${marketActivity.activityTwo=='P'}">
			       <input type="text"  disabled="disabled"value="票搭子"   style="width:200px"/><br><br>
			    </c:if>
			    <c:if test="${marketActivity.activityTwo=='H'}">
			       <input type="text"  disabled="disabled"value="混搭子"   style="width:200px"/><br><br>
			    </c:if>
			</td>
		</tr>
		<tr>
			<td align="right">活动结束日期:<br><br></td>
			<td align="left">
				<input type="text" disabled="disabled"id="activityEndDate" name="activityEndDate"  value="<fmt:parseDate value="${marketActivity.activityEndDate}" var="date2"></fmt:parseDate><fmt:formatDate value="${date2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'%y-%M-{%d}'})" disabled="disabled"style="width:200px"/><br><br>
			</td>
			
			<td align="right">是否达标查询:<br/><br/></td>
			<td align="left">
			    <c:if test="${marketActivity.isMeetDemand=='0'}">
			       <input type="text"  disabled="disabled"value="否"   style="width:200px"/><br><br>
			    </c:if>
			    <c:if test="${marketActivity.isMeetDemand=='1'}">
			       <input type="text"  disabled="disabled"value="是"   style="width:200px"/><br><br>
			    </c:if>
			</td>
		</tr>
		<tr>
			<td align="right">活动小图片:<br></td>
			<td align="left"> 
			    <img src="${path}${pic.actPictureUrl}" width="30" height="30" alt="活动小图片"/><br><br>
			</td>
			
			<td align="right">达标查询链接url:<br/></td>
			<td align="left">
				<input type="text" disabled="disabled"id="activityMeetDemandUrl" name="activityMeetDemandUrl" value="${marketActivity.activityMeetDemandUrl}" maxlength="100" style="width:200px"/><br>
			</td>
		</tr>
		<tr>
			<td align="right">活动大图片:<br></td>
			<td align="left"> 
			    <img src="${path}${pic.acBigPicUrl}" width="30" height="30" alt="活动大图片"/><br><br>
			</td>
			
			<td align="right">达标成功提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" disabled="disabled"id="meetDemandSucceedPrompt" name="meetDemandSucceedPrompt" value="${marketActivity.meetDemandSucceedPrompt}" maxlength="33" style="width:200px"/><br><br/>
			</td>
		</tr>
		<tr>
			<td align="right">营销商户图片:<br></td>
			<td align="left">
			   <img src="${path}${pic.shopPictureUrl}" width="30" height="30" alt="商户图片" /><br><br>
			</td>
			<td align="right">达标失败提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" disabled="disabled"id="meetDemandFailPrompt" name="meetDemandFailPrompt" value="${marketActivity.meetDemandFailPrompt}" maxlength="33" style="width:200px"/><br><br/>
			</td>
		</tr>
		
		<tr>
		    <td align="right" width="100px">报名成功提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" disabled="disabled"id="applySucceed" name="applySucceed" value="${marketActivity.applySucceed}"   style="width:200px"/><br><br/>
			</td>
			
			<td align="right">报名失败提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" disabled="disabled"id="applyFailDescribe" name="applyFailDescribe" value="${marketActivity.applyFailDescribe}"   style="width:200px"/><br><br/>
			</td>
		</tr>
		<tr>
				<td align="right" colspan="1">活动内容:<br/></td>
				<td align="left"  colspan="3">
					<textarea name="activityContent" id="activityContent" style="width:100%;height:20px;visibility:hidden;" >${marketActivity.activityInContent}</textarea>
				</td>
			  </tr>
		 
    </c:forEach>
    </table>
    </div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
	    var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="activityContent"]', {
				cssPath : '${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/editor/uploadEditor.html',
				fileManagerJson : '${pageContext.request.contextPath}/jsp/file_manager_json.jsp',
				allowFileManager : true ,
				allowPreviewEmoticons:true,
				resizeType : 1,
				afterCreate : function() { 
			       this.sync(); 
			       this.readonly();
			    }, 
			    afterBlur:function(){ 
			       this.sync(); 
			    },               
				allowPreviewEmoticons : false,
				allowImageUpload : true,//本地图片上传
				allowImageRemote : true,//网络图片上传
				items : [ 'undo', 'redo', 'copy','paste','|',
				          'fontsize','forecolor','hilitecolor','bold', 'italic','underline','removeformat','|',
					      'justifyleft','justifycenter', 'justifyright', 'insertorderedlist',
					      'insertunorderedlist', 'indent','outdent','subscript','superscript']

			});
			 
		});
		</script>
</body>
</html>