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
 <script type="text/javascript">
 	  var flag=false;
	  function checkShowOrder(){
			var showOrder=$("#activityShowOrder").val();
		    flag=false;
		    if($("#isTop").val()=="1" && showOrder!=""){
			    if(showOrder<1 || showOrder>8){
							alert("请重新输入活动展示顺序!，展示顺序为1至8的整数数字");
							$("#activityShowOrder").focus();
							return false;
				}
			 }
		     $.ajax({
					url:'getNums.html', 
					type: 'POST', 
					data: {num:showOrder},
					dataType: 'text',
					timeout: 1000, 
					async: false,
					error: function(text){ 
						alert('读取数据失败，请联系管理员！'); 
					}, 
					success: function(text){
						 if(text>0){
						   flag=true;
						   alert("活动展示顺序已存在，请重新输入");
						   $("#activityShowOrder").focus();
			   			}
					}        
		    }); 
	   }  
	</script>
</head>
<body onload="change()">
	<form action="updateOk.html" name="marketActivityForms" id="marketActivityForms" target="result" method="post"  enctype="multipart/form-data">
	  <div align="center">
	   <table>
	   <c:forEach items="${li}" var="marketActivity" varStatus="vs">
	    <input type="hidden" name="acPicId" value="${marketActivity.marketPictures.acPicId}"/>
	    <input type="hidden" name="acBigPicId" value="${marketActivity.marketPictures.acBigPicId}"/>
	    <input type="hidden" name="shopPicId" value="${marketActivity.marketPictures.shopPicId}"/>
		<input type="hidden" name="activityId" id="activityId" value="${marketActivity.activityId}"/>
		<input type="hidden" name="activityNo" id="activityNo" value="${marketActivity.activityNo}"/>
		<input type="hidden" name="activityShopPictureId" id="activityShopPictureId" value="${marketActivity.activityShopPictureId}"/>
		<tr>
			<td align="right">活动标题:<br/><br/></td>
			<td align="left">
	   			 <input type="text" id="activityTitle" name="activityTitle" size="40" value="${marketActivity.activityTitle}" maxlength="33" style="width:215px"/><br><br>
			</td>
			
			<td align="right">活动类型:<br><br></td>
			<td align="left">
			  <select  id="activityTypeId" name="activityTypeId" style="width:215px">
			  <option value="${marketActivity.activityTypeId}">${marketActivity.activityType.activityType}</option>
			  <option value="请选择">请选择</option>
			   <c:forEach items="${list}" var="list">
				 <option value="${list.activityTypeId}" <c:if test="${list.activityTypeId==marketActivity.activityTypeId}">selected</c:if>>${list.activityType}</option>
			   </c:forEach>
			</select><br><br>
		</tr>
		<tr>		
		   <td align="right">活动置顶:<br><br></td>
		   <td align="left">
			<select id="isTop" name="isTop" onchange="selectChange()" style="width:215px">
			  <option value="请选择">请选择</option>
			  <option value="1" <c:if test="${marketActivity.isTop=='1'}">selected</c:if>>是</option>
			  <option value="2" <c:if test="${marketActivity.isTop=='2'}">selected</c:if>>否</option>
			</select><br><br>
		  </td>
		  
		  <td align="right">活动区域性质:<br><br></td>
		  <td align="left">
				<%-- <input type="text" id="activityAreaQuale" name="activityAreaQuale" value="${marketActivity.activityAreaQuale}" maxlength="10"/><br><br> --%>
				<select id="activityAreaQuale" name="activityAreaQuale" onchange="change()" style="width:220px">
						 <option value="请选择">请选择</option>
						 <option value="1" <c:if test="${marketActivity.activityAreaQuale=='1'}">selected</c:if>>全国性活动</option>
			  			 <option value="2" <c:if test="${marketActivity.activityAreaQuale=='2'}">selected</c:if>>区域性活动 </option>
				</select><br><br>
		 </td>
		</tr>
		
		<tr>
		    <td align="right">活动展示顺序:<br><br></td>
		    <td align="left">
		  		 <input type="text" id="activityShowOrder" name="activityShowOrder" onblur="checkShowOrder()" value="${marketActivity.activityShowOrder}" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:215px"/><em><font color="red" size="1">如选择活动置顶为是,请输入1至8中间的整数</font></em><br><br>
		    </td>
			
			<td align="right">区域性活动城市:<br/><br/></td>
			<td align="left">
			  <input type="text" id="city" name="city" value="${citysName}" readonly="readonly" style="width:212px"/><a href="javascript:void(0)" onclick="getCity()" id="onclickId">选择</a>
			  <input type="hidden" id="cityNo" name="cityNo" value="${cityNo}"/><br><br>
			</td>
		</tr>
		
		<tr>
			<td align="right">活动详情简介:<br><br></td>
			<td align="left"> 
				<input type="text" id="activityInfo" name="activityInfo" value="${marketActivity.activityInfo}" maxlength="65" style="width:215px"/><br><br>
			</td>
			
			<td align="right">活动状态:<br><br></td>
		    <td align="left"> 
				<select id="activityStatus" name="activityStatus" style="width:215px">
				  <option value="请选择">请选择</option>
				  <option value="0" <c:if test="${marketActivity.activityStatus=='0'}">selected</c:if>>未发布</option>
				  <option value="1" <c:if test="${marketActivity.activityStatus=='1'}">selected</c:if>>已发布</option>
				</select><br><br>
		   </td>
		</tr>
		<tr>
			<td align="right">活动详情url:<br/><br/></td>
			<td align="left">
			<input type="text"  id="staticHtmlUrl" name="staticHtmlUrl" value="${marketActivity.staticHtmlUrl}" maxlength="65" style="width:215px"/><br><br>
			</td>
			
			<td align="right">是否允许报名:<br/><br/></td>
			<td align="left">
				<select id="isApply" name="isApply" style="width:215px">
				  <option value="请选择" >请选择</option>
				  <option value="0" <c:if test="${marketActivity.isApply=='0'}">selected</c:if>>否</option>
				  <option value="1" <c:if test="${marketActivity.isApply=='1'}">selected</c:if>>是</option>
				</select><br><br>
			</td>
		</tr>
		<tr>
			<td align="right">活动开始日期:<br><br></td>
			<td align="left">
				<input type="text" id="activityStartDate" name="activityStartDate"  
				style="background-image:url(../images/xiao.gif);   background-repeat:no-repeat;background-position:right center;width:215px;height: 20px;padding:0px;"
				value="<fmt:parseDate value="${marketActivity.activityStartDate}" var="date1"></fmt:parseDate><fmt:formatDate value="${date1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'%y-%M-{%d}'})" readonly="readonly" style="width:215px"/><br><br>
			</td>
			
			<td align="right">搭子类型:<br/><br/></td>
			<td align="left">
				<select id="activityTwo" name="activityTwo" style="width:215px">
				   <option value="请选择" >请选择</option>
				  <option value="F" <c:if test="${marketActivity.activityTwo=='F'}">selected</c:if>>饭搭子</option>
				  <option value="W" <c:if test="${marketActivity.activityTwo=='W'}">selected</c:if>>玩搭子</option>
				   <option value="P" <c:if test="${marketActivity.activityTwo=='P'}">selected</c:if>>票搭子</option>
				  <option value="H" <c:if test="${marketActivity.activityTwo=='H'}">selected</c:if>>混搭子</option>
				</select><br><br>
			</td>
		</tr>
		<tr>
			<td align="right">活动结束日期:<br><br></td>
			<td align="left">
				<input type="text" id="activityEndDate" name="activityEndDate"  
				style="background-image:url(../images/xiao.gif);   background-repeat:no-repeat;background-position:right center;width:215px;height: 20px;padding:0px;"
				value="<fmt:parseDate value="${marketActivity.activityEndDate}" var="date2"></fmt:parseDate><fmt:formatDate value="${date2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'%y-%M-{%d}'})" readonly="readonly" style="width:215px"/><br><br>
			</td>
			
			<td align="right">是否达标查询:<br/><br/><br/></td>
			<td align="left">
				<select id="isMeetDemand" name="isMeetDemand" style="width:215px">
				   <option value="请选择" >请选择</option>
				  <option value="0" <c:if test="${marketActivity.isMeetDemand=='0'}">selected</c:if>>否</option>
				  <option value="1" <c:if test="${marketActivity.isMeetDemand=='1'}">selected</c:if>>是</option>
				</select><br><br><br/>
			</td>
		</tr>
		<tr>
			<td align="right">活动小图片:<br></td>
			<td align="left"><!--accept="image/*"  只能选择图片格式的文件-->
				<input type="file" id="actPictureUrl" name="actPicFile"  accept="image/*"/>
			    <img src="${path}${pic.actPictureUrl}" width="30" height="30" alt="活动小图片"/><br><br>
			</td>
			
			<td align="right">达标查询链接url:<br/></td>
			<td align="left">
				<input type="text" id="activityMeetDemandUrl" name="activityMeetDemandUrl" value="${marketActivity.activityMeetDemandUrl}" maxlength="100" style="width:215px"/><br>
			</td>
		</tr>
		<tr>
			<td align="right">活动大图片:<br></td>
			<td align="left"><!--accept="image/*"  只能选择图片格式的文件-->
				<input type="file" id="actPictureUrls" name="actBigPicFile"  accept="image/*"/>
			    <img src="${path}${pic.acBigPicUrl}" width="30" height="30" alt="活动大图片"/><br><br>
			</td>
			
			<td align="right">达标成功提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" id="meetDemandSucceedPrompt" name="meetDemandSucceedPrompt" value="${marketActivity.meetDemandSucceedPrompt}" maxlength="33" style="width:215px"/><br><br/>
			</td>
		</tr>
		<tr>
		    <!-- <input type="hidden" name="marketPictures.shopPictureUrl" /> -->
			<td align="right">营销商户图片:<br></td>
			<td align="left">
			   <input type="file" id="shopPictureUrl" name="shopPicFile"  accept="image/*"/>
			   <img src="${path}${pic.shopPictureUrl}" width="30" height="30" alt="商户图片" /><br><br>
			</td>
			<td align="right">达标失败提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" id="meetDemandFailPrompt" name="meetDemandFailPrompt" value="${marketActivity.meetDemandFailPrompt}" maxlength="33" style="width:215px"/><br><br/>
			</td>
		</tr>
		
		<tr>
		    <td align="right">报名成功提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" id="applySucceed" name="applySucceed" value="${marketActivity.applySucceed}" maxlength="33" style="width:215px"/><br><br/>
			</td>
			
			<td align="right">报名失败提示语:<br/><br/></td>
			<td align="left">
			   <input type="text" id="applyFailDescribe" name="applyFailDescribe" value="${marketActivity.applyFailDescribe}" maxlength="33" style="width:215px"/><br><br/>
			</td>
		</tr>
			<tr>
				<td align="right" colspan="1">活动内容:<br/></td>
				<td align="left"  colspan="3">
				<textarea  name="activityContent" id="activityContent" style="width:97%;height:20px;visibility:hidden;">${marketActivity.activityInContent}</textarea>
				</td>
			  </tr>
		 <tr>
		     <td align="right"></td>
		     <td align="left"><p>您当前输入了 <span style="color:blue" class="word_count1">0</span> 个文字，还可输入<span style="color:blue" class="word_count2">0</span> 个文字。<span style="color:red" class="word_count3"></span></p></td>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
	
	    var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="activityContent"]', {
				cssPath : '${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/editor/uploadEditor.html',
				//fileManagerJson : '${pageContext.request.contextPath}/jsp/file_manager_json.jsp',
				allowFileManager : true ,
				allowPreviewEmoticons:true,
				resizeType : 1,
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
	    
		var dg; var dgs;
		$(document).ready(function(){	
			    //var upFlag="${sessionScope.updateFlag}";
			    //if(upFlag!=1){ 
			   	    dg = frameElement.lhgDG;
					dg.addBtn('ok','保存',function(){
					if(dgs!=undefined&&dgs!=null){
		               dgs.cancel();
		            }
		            if(editor.count("text")>1500){
						alert("活动内容超过字数限制，请适当删减部分内容");
						return;
					}
					if($("#activityAreaQuale").val()=="请选择"){
						alert("请选择营销活动区域性质!");
						$("#activityAreaQuale").focus();
						return false;
					}
					
					if($("#activityAreaQuale").val()=="2"){
					    if($("#city").val()==""){
					        alert("请选择城市!");
							$("#city").focus();
							return false;
					     }
					}
					
					if ($("#activityInfo").val() == "") {
						alert("请输入活动详情简单介绍信息!");
						$("#activityInfo").focus();
						return false;
					} 
					if ($("#staticHtmlUrl").val() == "") {
						alert("请输入活动详情介绍静态url地址!");
						$("#staticHtmlUrl").focus();
						return false;
					} 
					if($("#activityTypeId").val()=="请选择"){
						alert("请选择活动分类!");
						$("#activityTypeId").focus();
						return false;
					}
					if($("#isTop").val()=="请选择"){
						alert("请选择活动是否置顶!");
						$("#isTop").focus();
						return false;
					}
					if($("#isTop").val()=="1"){
						if($("#activityShowOrder").val()==""){
							alert("请输入活动展示顺序!");
							$("#activityShowOrder").focus();
							return false;
					    }else{
						     var order=$("#activityShowOrder").val();
							 if(8<order||order<1){
								alert("请重新输入活动展示顺序!，展示顺序为1至8的整数数字");
								$("#activityShowOrder").focus();
								return false;
							 }
						
						}
					}
					
					if(flag){
							alert("活动展示顺序已存在，请重新输入");
							$("#activityShowOrder").focus();
							return false;
					 } 
					
					if($("#isTop").val()=="2"){
						if($("#activityShowOrder").val()==""){
							$("#activityShowOrder").val(0);
						}
					}
		 		
					if($("#activityStatus").val()==""){
						alert("请选择活动状态!");
						$("#activityStatus").focus();
						return false;
					}
					if($("#isApply").val()=="请选择"){
						alert("请选择是否允许报名!");
						$("#isApply").focus();
						return false;
					}
					if($("#isMeetDemand").val()=="请选择"){
						alert("请选择是否达标查询!");
						$("#isMeetDemand").focus();
						return false;
					}
					if($("#activityTwo").val()=="请选择"){
						alert("请选择搭子类型!");
						$("#activityTwo").focus();
						return false;
					}
					
					if($("#activityStartDate").val()==""){
					     alert("请选择开始日期!");
						 $("#activityShowOrder").focus();
						return false;
					} 
					
					if($("#activityEndDate").val()==""){
					     alert("请选择结束日期!");
						 $("#activityShowOrder").focus();
						return false;
					} 
					
					if($("#activityStartDate").val()!="" && $("#activityEndDate").val()!=""){
						if(!checkTime($("#activityStartDate").val(),$("#activityEndDate").val())){
							$("#activityEndDate").focus();
							return false;
						 }
					} 
					//图片格式 {".gif", ".jpg", ".jpeg", ".jpe", ".bmp", ".png"}gif格式的做限制
					var text=["jpg", "jpeg", "jpe", "bmp", "png"];
					var actPicUrl=$("#actPictureUrl").val();
					var actPicUrls=actPicUrl.split(".");
					var reg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
					if ($("#actPictureUrl").val()!="") {
						//判断数组中是否包含某个元素
						if($.inArray(actPicUrls[1],text)==-1){
							alert("请上传图片格式为jpg,jpeg,jpe,bmp,png的文件!");
							$("#actPictureUrl").focus();
							return false;
						}
						var start=$("#actPictureUrl").val().lastIndexOf("\\");
						var end=$("#actPictureUrl").val().lastIndexOf(".");
						var name=$("#actPictureUrl").val().substring(start+1,end);
					    if(reg.test(name)){
						   alert("图片名称不能包含中文字符!");
						   return false;
						} 
					}
					
					var actPictureUrls=$("#actPictureUrls").val();
					var actPictureUrl=actPictureUrls.split(".");
					if($("#actPictureUrls").val()!=""){
						if($.inArray(actPictureUrl[1],text)==-1){
							alert("请上传图片格式为jpg,jpeg,jpe,bmp,png的文件!");
							$("#actPictureUrls").focus();
							return false;
						}
						
						var start=$("#actPictureUrls").val().lastIndexOf("\\");
						var end=$("#actPictureUrls").val().lastIndexOf(".");
						var name=$("#actPictureUrls").val().substring(start+1,end);
					    if(reg.test(name)){
						   alert("图片名称不能包含中文字符!");
						   return false;
						} 
					}
					
					if($("#shopPictureUrl").val()!=""){
						var shopPictureUrl=$("#shopPictureUrl").val();
						var shopPicUrl=shopPictureUrl.split(".");
						if($.inArray(shopPicUrl[1],text)==-1){
							alert("请上传图片格式为jpg,jpeg,jpe,bmp,png的文件!");
							$("#shopPictureUrl").focus();
							return false;
						}
						
						var start=$("#shopPictureUrl").val().lastIndexOf("\\");
						var end=$("#shopPictureUrl").val().lastIndexOf(".");
						var name=$("#shopPictureUrl").val().substring(start+1,end);
					    if(reg.test(name)){
						   alert("图片名称不能包含中文字符!");
						   return false;
						} 
					}
					
					$("#marketActivityForms").submit();
					return true;
				});
			// }	 
		});
			
		function getCity() {
		      var cityNo=$("#cityNo").val();
			   dgs = new $.dialog({
				title:'城市列表',
				id:'cityList',
				width:840,
				height:460,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'getCitys.html?cityNo='+cityNo
				});
    		dgs.ShowDialog();
				    		
		}
		//验证结束时间是否大于开始时间(时间格式为:xxxx-xx-xx)
	   function checkTime(startTime, endTime) {
				var nd = new Date(startTime.replace("-", "/"));
				var ld = new Date(endTime.replace("-", "/"));
				if (Date.parse(ld) - Date.parse(nd) < 0) {
					alert("结束日期要大于或等于开始日期");
					return false;
				} else {
					return true;
				}
	    }
	    
	     //初始化控件
	    function change(){
	      if($("#activityShowOrder").val()=='0'){
	      	$("#activityShowOrder").val("");
	      }
	      
		  if($("#activityAreaQuale").val()=='1'){
			 if($("#city").val()!=""){
					$("#city").val("");
					$("#cityNo").val("");
			 }
		     $("#city").attr("disabled",true); 
		     //$("#onclickId").attr(c); 
		     document.getElementById("onclickId").style.visibility="hidden";
		  }
		  
		  if($("#activityAreaQuale").val()=='2'){
		     $("#city").attr("disabled",false); 
		     //$("#onclickId").attr("disabled",false); 
		     document.getElementById("onclickId").style.visibility="visible";
		  }
		  selectChange();
		}
		
		//是否置顶决定活动顺序是否可编辑
		function selectChange(){
		  if($("#isTop").val()=="1"){
		    $("#activityShowOrder").attr("disabled",false);
		  }
		  
		  if($("#isTop").val()=="2"){
		  	$("#activityShowOrder").attr("disabled",true);
		  }

		}
		
		
		function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		
		function failed(){
			alert("修改失败");
			$("#loginname").select();
			$("#loginname").focus();
		}
		
	</script>
	<!-- <script type="text/javascript">
		addressInit('cmbProvince', 'cmbCity', 'cmbArea', '上海', '市辖区', '浦东新区');
		addressInit('Select1', 'Select2', 'Select3');
	</script> -->
</body>
</html>