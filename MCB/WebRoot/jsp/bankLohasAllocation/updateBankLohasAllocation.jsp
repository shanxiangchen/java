<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align:right;width:115px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
#preview { position:absolute; bottom：30px; width:10%; height:10%; }
.none{display:none;}
</style>
</head>
<body>
	<form  action="updateLohasAllocation.html" enctype="multipart/form-data" name="addLohasForm" id="addLohasForm" target="result"  method="post">
	   <input type="hidden" name="lohasId" id="lohasId" value="${lohas.lohasId}">
	   <input type="hidden" name="lohasImgName" value="${lohas.lohasImgName}">
	   <input type="hidden" name="lohasSortNo" value="${lohas.lohasSortNo}">
	   <input type="hidden" name="lohasImgUrl" value="${lohas.lohasImgUrl}">
	   <table border="0" cellpadding="0" cellspacing="0" id="tableId">
		 <tr class="info">
	 	       <th>图片:</th>
	 	       <td>
	 	       		<input type="file" id="lohasfile" name="lohasfile" style="width:180px;" accept="image/*" />
	 	       </td> 
	 	       <td>
			  	    <img src="${path}${lohas.lohasImgUrl}" width="30" height="30" alt="乐活图片"/>
			  	    <input type="hidden" id="url" value="${lohas.lohasImgUrl}" name="url"/>
			   </td>  
	 	  </tr>
		 <tr class="info">
	 	        <th><select id="linkType1" name="linkType" onchange="javascript:changeTest();">
		 	        	<option value="1" <c:if test="${lohas.linkType ==1}">selected</c:if>>内链</option>
						<option value="2" <c:if test="${lohas.linkType ==2}">selected</c:if>>外链</option>
	 		    </select></th>
			    <td>
			    	<input id="linkinput1" type="text" name="lohasOutLink" value="${lohas.lohasOutLink}"  style="width:200px;" />
			    	<select id="linkNo1"  style="width: 206px;" class="none" name="linkNo">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}" <c:if test="${lohas.linkNo==li.LINKNO}">selected</c:if>>${li.LINKNAME}</option>
				 		 </c:forEach>
					</select>
			    </td>
	 	  </tr>
		 <tr class="info">
	 	       <th>
		 	       <span id="line1">连接参数</span>
		 	       <span id="isaddphonespan1" name="isaddphonespan">是否添加参数</span>
	 	       </th>
			    <td>
			    	<input id="lohasParam1" type="text" name="lohasParam" class="none" value="${lohas.lohasParam}"  style="width:200px;" placeholder="参数形式：a1=abc,a2=123" />
					<c:if test="${lohas.isAddPhone==0}">
						<select id="isAddPhone1" style="width:205px;margin-left: 2px;" name="isAddPhone">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
		 		    </c:if>
		 		    <c:if test="${lohas.isAddPhone==1}">
		 		    	<select id="isAddPhone1" style="width:205px;margin-left: 2px;" name="isAddPhone">
			 		    	<option value="1">是</option>
			 		    	<option value="0">否</option>
			 		    </select>
		 		    </c:if>
			    </td>
	 	       
	 	  </tr>
	 	   
	  </table>  
	 
	 
	  </div>  
    </form>			
	</body>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/main.js" ></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
	<script type="text/javascript">
        var dg; var count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
			    if(!validata()){
			      return;
			    }  
				if(count!=0){
			       return;
			    }
				 linkinput();
			     count=count+1;
	             $("#addLohasForm").ajaxSubmit({
						type : "POST",
						url : "updateLohasAllocation.html",
						dataType : "text",
						success : function(text) {
							  success();
						},
						error : function() {
							alert("保存失败！");
							success();
						}
				 });
				 
			});
			changeTest();
		});
		function linkinput(){
			var linkinput = $("#linkinput1").val();
			var linkhead = "http://";
			if(linkinput.indexOf(linkhead)<0){
				$("#linkinput1").val(linkhead+linkinput);
			}
		}
		
		function changeTest(){
			var linkTypeNum = $("#linkType1").val();
			if(linkTypeNum == 2){
				$("#linkinput1").removeClass('none');
				$("#linkNo1").addClass('none');
				$("#lohasParam1").addClass('none');
				$("#line1").addClass('none');
				$("#isaddphonespan1").removeClass('none');
				$("#isAddPhone1").removeClass('none');
			}else if(linkTypeNum ==1){
				$("#linkinput1").addClass('none');
				$("#linkNo1").removeClass('none');
				$("#lohasParam1").removeClass('none');
				$("#line1").removeClass('none');
				$("#isaddphonespan1").addClass('none');
				$("#isAddPhone1").addClass('none');
			}	
		}
		
		function validata(){
			var linkType = $("#linkType1").val();
			var linkinput = $("#linkinput1").val();
			var linkNo = $("#linkNo1").val();
		    if(linkType ==1){
		    	//内链判断linkNo是否为空
		    	if(linkNo=="请选择"){
		    		alert("请选择内链跳转页面！");
		    		return false;
		    	}
		    }else if(linkType==2){
		    	if($.trim(linkinput)==""){
		    		alert("链接不能为空！");
		    		return false;
		    	}
		    }
			
		    if($("#lohasfile").val()!=""){
			    var strs = $("#lohasfile").val().split(".");
				var fileType = strs[strs.length - 1];
				if (fileType != "gif" && fileType != "jpg"
						&& fileType != "jpeg" && fileType != "bmp"
						&& fileType != "png") {
					alert("图片类型必须是.gif,jpeg,jpg,bmp,png中的一种");
				      return false;
				}
				var linkType = $("#linkType1").val();
				if(linkType == 1){
					if($("#linkNo1").val()=="请选择"){
						alert("请选择内部连接！");
						return false;
					}
				}
				return true;
		    }else{
				var linkType = $("#linkType1").val();
				if(linkType == 1){
					if($("#linkNo1").val()=="请选择"){
						alert("请选择内部连接！");
						return false;
					}
				}
				return true;
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
</html>