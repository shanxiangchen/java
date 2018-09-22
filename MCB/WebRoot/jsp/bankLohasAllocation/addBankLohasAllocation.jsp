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
.info th{text-align:right;width:80px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
.none{display: none;}
#preview { position:absolute; bottom：30px; width:10%; height:10%; }
</style>
</head>
<body>
	<form  action="saveLohasAllocation.html" enctype="multipart/form-data" name="addLohasForm" id="addLohasForm" target="result"  method="post">
	   <table border="0" cellpadding="0" cellspacing="0" id="tableId">
	  </table>  
	  <div id="aa">
	     <table border="0" cellpadding="0" cellspacing="0">
		     <tr class="info">
	 		    <th>图片一:</th>
			    <td><input type="file" name="lohasfile"  style="width:205px;" accept="image/*" /></td>
	 		    <th>
	 		    	<select id="linkType1" name="linkTypes" onchange="javascript:changeTest(this.id);">
						<option value="2">外链</option>
	 		    		<option value="1">内链</option>
	 		   		</select>
	 		    </th>
			    <td>
			    	<input id="linkinput1" type="text" name="lohasOutLinks"  style="width:200px;" value="http://"/>
			    	<select id="linkNo1"  style="width: 206px;" class="none" name="linkNos">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select>
			    </td>
	 		    <td>
		 		    <input type="text" id="lohasParam1" value="" maxlength="100" name="lohasParams" style="margin-left:30px;" class="none" placeholder="参数形式：a1=abc;a2=abc" />
		 		    <span id="isaddphonespan1" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone1" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
	 		    </td>
		     </tr>
		     <tr class="info">
	 		    <th>图片二:</th>
			    <td><input type="file" name="lohasfile"    style="width:205px;" accept="image/*" /></td>
	 		      <th><select id="linkType2" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    </select></th>
			    <td>
			    	<input id="linkinput2" type="text" name="lohasOutLinks"  style="width:200px;" value="http://"/>
			    	<select id="linkNo2"  style="width: 206px;" class="none" name="linkNos">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select>
			    </td>
			    <td>
				    <input type="text" id="lohasParam2" value="" maxlength="100" name="lohasParams" style="margin-left:30px;"  class="none" placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
				    <span id="isaddphonespan2" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone2" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
			    </td>
		     </tr>
		     <tr class="info">
	 		    <th>图片三:</th>
			    <td><input type="file" name="lohasfile"    style="width:205px;" accept="image/*" /></td>
	 		      <th><select id="linkType3" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    </select></th>
			    <td><input id="linkinput3" type="text" name="lohasOutLinks"  style="width:200px;" value="http://"/>
			    <select id="linkNo3"  style="width: 206px;" class="none" name="linkNos">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select></td>
					<td>
						<input type="text" id="lohasParam3" value="" maxlength="100" name="lohasParams" style="margin-left:30px;" class="none"  placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
						<span id="isaddphonespan3" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone3" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
					</td>
		     </tr>
		     <tr class="info">
	 		    <th>图片四:</th>
			    <td><input type="file" name="lohasfile"    style="width:205px;" accept="image/*" /></td>
	 		      <th><select id="linkType4" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    </select></th>
			    <td><input id="linkinput4" type="text" name="lohasOutLinks"     style="width:200px;" value="http://"/>
			    <select id="linkNo4"  style="width: 206px;" class="none" name="linkNos">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select></td>
					<td>
						<input type="text" id="lohasParam4" value="" maxlength="100" name="lohasParams" style="margin-left:30px;" class="none" placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
						<span id="isaddphonespan4" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone4" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
					</td>
		     </tr>
		     <tr class="info">
	 		    <th>图片五:</th>
			    <td><input type="file" name="lohasfile" id="lohasfile"   style="width:205px;" accept="image/*" /></td>
	 		      <th><select id="linkType5" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    </select></th>
			    <td><input id="linkinput5" type="text" name="lohasOutLinks" id="lohasOutLink"   style="width:200px;" value="http://"/>
			    <select id="linkNo5"  style="width: 206px;" class="none" name="linkNos" >
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select></td>
					<td>
						<input type="text" id="lohasParam5" value="" maxlength="100" name="lohasParams" style="margin-left:30px;" class="none" placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
						<span id="isaddphonespan5" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone5" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
					</td>
		     </tr>
		     <tr class="info">
	 		    <th>图片六:</th>
			    <td><input type="file" name="lohasfile"     style="width:205px;" accept="image/*" /></td>
	 		     <th><select id="linkType6" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    </select></th>
			    <td><input id="linkinput6" type="text" name="lohasOutLinks"     style="width:200px;" value="http://"/>
			    <select id="linkNo6"  style="width: 206px;" class="none" name="linkNos">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select></td>
					<td>
						<input type="text" id="lohasParam6" value="" maxlength="100" name="lohasParams" style="margin-left:30px;" class="none" placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
						<span id="isaddphonespan6" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone6" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
					</td>
		     </tr>
		     <tr class="info">
	 		    <th>图片七:</th>
			    <td><input type="file" name="lohasfile"     style="width:205px;" accept="image/*" /></td>
	 		     <th><select id="linkType7" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    </select></th>
			    <td><input id="linkinput7" type="text" name="lohasOutLinks"    style="width:200px;" value="http://"/>
			    <select id="linkNo7"  style="width: 206px;" class="none" name="linkNos">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select></td>
					<td>
						<input type="text" id="lohasParam7" value="" name="lohasParams" maxlength="100" style="margin-left:30px;" class="none" placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
						<span id="isaddphonespan7" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone7" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
					</td>
		     </tr>
		     <tr class="info">
	 		    <th>图片八:</th>
			    <td><input type="file" name="lohasfile"     style="width:205px;" accept="image/*" /></td>
	 		      <th><select id="linkType8" name="linkTypes" onchange="javascript:changeTest(this.id);">
					<option value="2">外链</option>
	 		    	<option value="1">内链</option>
	 		    	</select></th>
			    <td><input id="linkinput8" type="text" name="lohasOutLinks"    style="width:200px;" value="http://"/>
			    <select id="linkNo8"  style="width: 206px;" name="linkNos" class="none">
				 		 <option value="请选择">请选择</option>
				  		 <c:forEach items="${listname}"  var="li">
							<option value="${li.LINKNO}">${li.LINKNAME}</option>
				 		 </c:forEach>
					</select></td>
					<td>
						<input type="text" id="lohasParam8" value="" name="lohasParams" maxlength="100" style="margin-left:30px;" class="none" placeholder="参数形式：a1=abc;a2=abc" style="margin-left:30px"/>
						<span id="isaddphonespan8" name="isaddphonespan" style="margin-left:30px;font-size: 16px;">是否添加参数</span>
						<select id="isAddPhone8" style="width:50px;margin-left:15px" name="isAddPhones">
			 		    	<option value="0">否</option>
			 		    	<option value="1">是</option>
			 		    </select>
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
	  $("#tdId,#tdIdOne").hide();
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
						url : "saveLohasAllocation.html",
						dataType : "text",
						success : function(text) {
							if (text=="success") {
								success(); 
							}else if(text=="1"){
							    alert("该位置图片已经上传");
							    count=0;
							    return;
							}else if(text=="2"){
							   alert("图片上传失败");
							    success();
							}else if(text=="3"){
								alert("展示顺序号已经存在");
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
		});
		
		function linkinput(){
			$("input[name='lohasOutLinks']").each(function(){
				var linkinput = $(this).val();
				var linkhead = "http://";
				if(linkinput.indexOf(linkhead)<0){
					$(this).val(linkhead+linkinput);
				}
			});
		}
		
		function validata(){
		    var fileCount=0;
		    var linkCount=0;
		    var imgCount=0;
		    var linkNoCount = 0;
		    var linkInputCount = 0;
		    
		    $("select[name='linkTypes']").each(function(){
				var linkType = $(this).val();
				var linkTypeId = $(this).attr("id");
				var Num = linkTypeId.substring(8);
				if(linkType == 1){
					var linkNoId = "linkNo"+Num;
					if($.trim($("#"+linkNoId).val()) == "请选择"){
						linkNoCount++;
					}
				}else{
					var linkInputId = "linkinput"+Num;
					if($.trim($("#"+linkInputId).val())==""){
						linkInputCount++;
					}
				}
			});
		    
		    $("div").each(function(){
		      if($(this).is(":hidden")){
		        $(this).find("input").attr("disabled",true);
		      }else{
		        $(this).find("input[name='lohasfile']").each(function(){
		              if($(this).val()==""){
		                 fileCount++;
		              }else{
		                var strs = $(this).val().split(".");
						var fileType = strs[strs.length - 1];
						if (fileType != "gif" && fileType != "jpg"
								&& fileType != "jpeg" && fileType != "bmp"
								&& fileType != "png") {
							imgCount++;
							
						}
		             }
		        });
		        $(this).find("input[name='lohasOutLinks']").each(function(){
		             if($(this).hasClass("none")){
		            	 if($(this).next().val() == ""){
		            		 linkCount++;
		            	 }
		             }else{
		            	 if($.trim($(this).val())==""){
		            		 linkCount++;
		            	 }
		             }
		        });
		        
		      }
		    });
		    
		    if(linkNoCount>0){
		    	alert("请选择内部链接！");
		    	return false;
		    }
		    
		    if(linkInputCount>0){
		    	alert("请输入外部链接！");
		    	return false;
		    }
		    
		    if(imgCount>0){
		      alert("图片类型必须是.gif,jpeg,jpg,bmp,png中的一种");
		      return false;
		    }
		    if(fileCount>0){
		      alert("请选择上传图片");
		      return false;
		    }
		    
		    if(linkCount>0){
		      alert("链拉不能为空");
		      return false;
		    }
			return true;

		}
		
		function changeTest(id){
			var Num = id.substring(8);
			var linkId = "linkNo"+Num;
			var linkInput = "linkinput"+Num;
			var isaddphonespan = "isaddphonespan"+Num;
			var isAddPhone = "isAddPhone"+Num;
			var lohasParam = "lohasParam"+Num;
			var linkTypeValue = $("#"+id).val();
			if(linkTypeValue == 1){
				$("#"+linkId).removeClass('none');
				$("#"+linkInput).addClass('none');
				$("#"+lohasParam).removeClass('none');
				$("#"+isAddPhone).addClass('none');
				$("#"+isaddphonespan).addClass('none');
			}else if(linkTypeValue == 2){
				$("#"+linkId).addClass('none');
				$("#"+linkInput).removeClass('none');
				$("#"+lohasParam).addClass('none');
				$("#"+isAddPhone).removeClass('none');
				$("#"+isaddphonespan).removeClass('none');
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