<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>营销活动发布</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<!-- <script type="text/javascript" src="js/marketActivity/marketActiviy_list.js"></script> -->
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<style>#Img1{ width:30px; height:30px;}#Img1:hover{ width:300px; height:300px;}</style>
</head>
<body>
 <form action="marketActivity.html" method="post" name="activityForm" id="activityForm">
	 <div class="search_div">
		活动标题:<input type="text" name="activityTitle" id="activityTitle" value="${market.activityTitle}"/>&nbsp;&nbsp;&nbsp;&nbsp;
		是否审核:<select id="isCheck" name="isCheck">
						 <option value="">请选择</option>
						 <option value="a" <c:if test="${market.isCheck=='a'}">selected</c:if>>未审核</option>
						 <option value="b" <c:if test="${market.isCheck=='b'}">selected</c:if>>审核中</option>
 						 <option value="c" <c:if test="${market.isCheck=='c'}">selected</c:if>>已审核</option>
 						 <option value="d" <c:if test="${market.isCheck=='d'}">selected</c:if>>已退回</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
	   活动状态:<select id="activityStatus"  name="activityStatus">
				  <option value="">请选择</option>
				  <option value="0" <c:if test="${market.activityStatus=='0'}">selected</c:if>>未发布</option>
				  <option value="1" <c:if test="${market.activityStatus=='1'}">selected</c:if>>已发布</option>
			      <option value="2" <c:if test="${market.activityStatus=='2'}">selected</c:if>>已结束</option>
				  <option value="3" <c:if test="${market.activityStatus=='3'}">selected</c:if>>已过期</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;		
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" id="tableId" border="0" cellpadding="1" cellspacing="1" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/>序号</th>
			<th>活动编号</th>
			<th>活动标题</th>
			<th>开始日期</th>
			<th>结束日期</th>
			<th>活动分类</th> 
			<th>活动图片</th>
			<th>商户图片</th>  
			<th>状态</th>
			<th>是否审核</th>
			<th>是否允许报名</th>
			<th>是否达标查询</th>
			<th>申请用户</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="vs">
					<tr class="main_info">
					<th><input type="checkbox" name="activityIds" id="activityIds" class="mybox" value="${list.activityId}" onclick="checkBox($(this),'${list.isCheck}');"/>
					&nbsp;&nbsp;
					<c:if test="${vs.index+1<=9}">0${vs.index+1}</c:if>
					<c:if test="${vs.index+1>9}">${vs.index+1}</c:if>
					</th>
					<th onmouseover="this.title=this.innerText">${list.activityNo}
					    <input type="hidden" value="${list.activityId}"/>
					</th>
					<th onmouseover="this.title=this.innerText">${list.activityTitle}</th>
					<th onmouseover="this.title=this.innerText">${list.activityStartDate}</th>
					<th onmouseover="this.title=this.innerText">${list.activityEndDate}</th>
					<th onmouseover="this.title=this.innerText">${list.activityType.activityType}</th>  
				    <th><img src="${path}${list.marketPictures.actPictureUrl}" width="30" height="30"></th>
					<th><img src="${path}${list.marketPictures.shopPictureUrl}" width="30" height="30"></th>  
					<th>
					<c:choose>
						<c:when test="${list.activityStatus=='0'}">
					  	  未发布
					  	</c:when>
					  	<c:when test="${list.activityStatus=='1'}">
					  	  已开始
					  	</c:when>
					  	<c:when test="${list.activityStatus=='2'}">
					  	  已结束
					  	</c:when>
					  	<c:when test="${list.activityStatus=='3'}">
					  	  已过期
					  	</c:when>
					  </c:choose>
					  	<input type="hidden" name="hiddenActivityStatus" id="activityStatus_${list.activityId}" value="${list.activityStatus}"/>
					</th>
					<th>
						<c:choose>
						  	<c:when test="${list.isCheck=='a'}">
						  	  未审核 
						  	</c:when>
						  	<c:when test="${list.isCheck=='b'}">
						  	 审核中  
						  	</c:when>
						  	<c:when test="${list.isCheck=='c'}">
						  	  已审核 
						  	</c:when>
						  	<c:when test="${list.isCheck=='d'}">
						  	  已退回
						  	</c:when>
						</c:choose>
						<input type="hidden" name="checks" id="checks_${list.activityId}" value="${list.isCheck}"/>
					</th>
					<th>
					<c:choose>
						  	<c:when test="${list.isApply=='0'}">否 </c:when>
						  	<c:when test="${list.isApply=='1'}">是  </c:when>
					</c:choose>
					</td>
					<td>
					<c:choose>
						  	<c:when test="${list.isMeetDemand=='0'}">否</c:when>
						  	<c:when test="${list.isMeetDemand=='1'}">是 </c:when>
						</c:choose>
					</th>
					<th>${list.loginname}</th>
					<th>
					<c:if test="${sessionScope.user.permissionsCategory=='2'}">
							<span style="font-color:#ccc">修改</span>|
					</c:if>
					<c:if test="${(sessionScope.user.permissionsCategory=='3')}">
							<a href="javascript:void(0)"  onclick="editUser('${list.activityId}','${list.activityStatus}','${list.isCheck}','${list.citysName}','${list.cityNo}')">修改</a>|
					</c:if>
					<%-- <c:if test="${(sessionScope.user.permissionsCategory=='3')&&(list.isCheck!='a')}">
							<span style="color:#5f5f5f;">修改</span>|
					</c:if> --%>
							<a href="javascript:void(0)" onclick="infoUser('${list.activityId}','${list.activityStatus}','${list.isCheck}','${list.citysName}','${list.cityNo}')">详情</a></th>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="14">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn"> 
	  <div>
		 <a href="javascript:addUser();" class="myBtn"  id="fisrt"><em>新增</em></a>
		 <a href="javascript:void(0)" onclick="delUser()" id="two" class="myBtn"><em>删除</em></a>
		 <a href="javascript:void(0)" onclick="checkData()" id="one" class="myBtn"><em>提交申请</em></a>
		 <a href="javascript:void(0)" onclick="checkData()" id="one1" class="myBtn"><em>审核</em></a>
		 <a href="javascript:void(0)" onclick="returnData()" class="myBtn" id="returnData"><em>退回申请</em></a>
	 </div>
	 ${market.page.pageStr}
   </div>
</form>
	<script type="text/javascript" src="js/style.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	var roleNames="${sessionScope.user.permissionsCategory}";
	//获取当前登录人的角色权限
	if(roleNames=="1" ||roleNames=="2"){
		$(document.getElementById("fisrt")).hide();
		$(document.getElementById("two")).hide();
		$(document.getElementById("one")).hide();
	}
	if(roleNames=="3"){
		document.getElementById("returnData").style.display="none";
		$(document.getElementById("one1")).hide();
	}
	$(".main_info:even").addClass("main_table_even");
});

function sltAllUser(){
	if($("#sltAll").attr("checked")){
	    $("input[name='activityIds']").attr("checked",true);
	}else{
		$("input[name='activityIds']").attr("checked",false);
	}
}

function addUser(){
	$('#activityTitle').val("");
	var dg = new $.dialog({
		title:'新增',
		id:'user_new',
		width:1000,
		height:400,
		iconTitle:false,
		cover:true,
		maxBtn:false,
		xButton:true,
		resize:false,
		page:'marketActivity/add.html'
		});
	dg.ShowDialog();
}

function infoUser(activityId,activityStatus,isCheck,citysName,cityNo){
	//获取当前登录人的角色权限
    var roleNames="${sessionScope.user.permissionsCategory}";
    var updateFlag=0;
    var dec="";
    if(roleNames!="1" || roleNames=="2"){
	    if(isCheck!="a" && isCheck!="d"){
	      updateFlag=1;
	      dec="---活动已审核状态不能修改";
	    }
	      updateFlag=1;
	      dec="---活动已发布状态不能修改";
    }else{
    	 if(activityStatus=="1" && isCheck=="c"){
	      updateFlag=1;
	      dec="---活动为已开始,并为已提交申请状态不能修改";
	     }
	     if(isCheck=="b" || isCheck=="d" ){
	      updateFlag=1;
	      dec="---活动为已提交申请状态不能修改";
	     }
    }
	var dg = new $.dialog({
		title:'营销活动活动发布详情',
		cancelBtnTxt:'关闭',
		id:'user_edit',
		width:800,
		height:400,
		iconTitle:false,
		cover:true,
		maxBtn:false,
		resize:false,
		page:'marketActivity/infodate.html?activityId='+activityId+'&cityNo='+cityNo+'&updateFlag='+updateFlag
		});
	dg.ShowDialog();
}

function editUser(activityId,activityStatus,isCheck,citysName,cityNo){
	//获取当前登录人的角色权限
    var roleNames="${sessionScope.user.permissionsCategory}";
    var updateFlag=0;
    var dec="";
    if(roleNames=="1" ||roleNames=="2"){
	    if(activityStatus=="1" && isCheck=="c"){
		      updateFlag=1;
		      dec="---活动为已发布,并为已审核状态不能修改";
		    }
		if(isCheck=="b"){
		      updateFlag=1;
		      dec="---活动正在审核中不能修改";
		     }
		if(isCheck=="c"){
		      updateFlag=1;
		      dec="---活动为已审核状态不能修改";
		    }    
		     
    	}
   if(roleNames=="3"){
      if(activityStatus=="1" && isCheck=="c"){
	      	updateFlag=1;
		     dec="---活动为已开始,已审核不能修改";
	     }
   	if(activityStatus=="1" && isCheck=="b"){
	      	updateFlag=1;
		     dec="---活动为已开始,审核中不能修改";
	     }
	    if(isCheck=="b"){
		      	updateFlag=1;
			  dec="---活动为审核中不能修改";
		    }
    }
	var dg = new $.dialog({
		title:'修改活动'+dec,
		id:'user_edit',
		width:1000,
		height:400,
		iconTitle:false,
		cover:true,
		maxBtn:false,
		resize:false,
		page:'marketActivity/update.html?activityId='+activityId+'&cityNo='+cityNo+'&updateFlag='+updateFlag
		});
	dg.ShowDialog();
}

 function delUser(){
    	//获取当前登录人的角色权限
    	var roleNames="${sessionScope.user.permissionsCategory}";
    	var activityIds="";
    	var checkActivityIds="";
		//验证是否选择了记录
	  	if($('#tableId input[name="activityIds"]:checkbox:checked').length < 1){
	  		alert('请选择一条记录再进行删除！');
	  		return false;
	  	}else{
	  		$('#tableId input[name="activityIds"]:checkbox:checked').each(function(){
			    var checks=$("#checks_"+$(this).val()).val();
			    var activityStatus=$("#activityStatus_"+$(this).val()).val();
			    if(roleNames!=""){
					   if(checks!="a" && checks!="d"){
							      activityIds+=$(this).val()+","; 
							      $(this).attr('checked',false);
					   }else{
							      checkActivityIds+=$(this).val()+","; 
					   }
				}else{
			   		  if(checks =="b" || checks =="c" && activityStatus=="1"){
					      activityIds+=$(this).val()+","; 
					      $(this).attr('checked',false);
					   }else{
					      checkActivityIds+=$(this).val()+","; 
					  }
			   }	   
			});
			if(roleNames!=""){
				if(activityIds!=""){
				  		alert("活动id"+activityIds+"已审核不能删除");
				  		return false;
				}
			}
			
			if(roleNames==""){
				if(activityIds!=""){
			  		alert("活动id"+activityIds+"存在已提交申请状态或已开始并已审核状态不能删除");
			  		return false;
				}
				
				/* if(activityIdTwo!=""){
					alert("活动id"+activityIdTwo+"为已开始并已审核状态不能删除");
				} */
			}
			
		   if(confirm("确定要删除该记录？")){
			   $.ajax({
						url:'marketActivity/delete.html', 
						type: 'POST', 
						data: {str:checkActivityIds},
						dataType: 'text',
						timeout: 1000, 
						async: false,
						error: function(text){ 
							alert('读取数据失败，请联系管理员！'); 
							return false;
						}, 
						success: function(text){
							if(text==""){  
							   alert("删除成功");
							   document.location.reload();
				   			}else{
				   			  	  alert("当前活动id"+text+"与某些商户存在关联，请在商户和活动关系维护功能模块解除关系后才能删除");
				   			  	  return false;
				   		    }
						}        
			    });
	        }
	}
 }

function checkData(){
    //获取当前登录人的角色权限
    var roleNames="${sessionScope.user.permissionsCategory}";
	var activityIds="";
	var checkActivityIds="";
	/* var activityStatusIds=""; */
		//验证是否选择了记录
	  	if($('#tableId input[name="activityIds"]:checkbox:checked').length < 1){
	  		alert('请选择一条记录再进行审核！');
	  		return false;
	  	}else{
	  		$('#tableId input[name="activityIds"]:checkbox:checked').each(function(){
			   var checks=$("#checks_"+$(this).val()).val();
			   /* var activityStatusId="activityStatus_"+$(this).val();
			   if($("#"+activityStatusId).val()=='0' && roleNames!="3"){
				   activityStatusIds +=$(this).val()+",";
			   }else{ */
				   if(roleNames=="3"){
				   		   if(checks=="a"){
				   		  		 checkActivityIds+=$(this).val()+","; 
						      
						     /*  alert( activityIds+=$(this).val()+","); */
						   }else if(checks=="d"){ 
						   		checkActivityIds+=$(this).val()+","; 
						   }else{
						    activityIds+=$(this).val()+","; 
						      $(this).attr('checked',false);
						   }
				   }else{
				   		  if(checks =="c" || checks =="d" ){
						      activityIds+=$(this).val()+","; 
						      $(this).attr('checked',false);
						   } else{
						      checkActivityIds+=$(this).val()+","; 
						   }
				   }
			   /* } */
			});
	  		
			if(roleNames=="3"){
				if(activityIds!=""){
			  		alert("活动id"+activityIds+"非未审核状态或已退回不能再次提交审核");
			  		return false;
				}
			}
			
			/* if(roleNames !='3'){
				if(activityStatusIds!=""){
					alert("活动id"+activityStatusIds+"未进行发布，不 能审核！");
					return false;
				}
			} */
			
			if(roleNames=="1" || roleNames=="3"){
				if(activityIds!=""){
			  		alert("活动id"+activityIds+"存在已审核状态或已退回状态不能再次审核");
			  		return false;
				}
			}
			
		   if(confirm("确定提交审核吗？")){
			   $.ajax({
						url:'marketActivity/checkDada.html', 
						type: 'POST', 
						data: {str:checkActivityIds,flag:'1'},
						dataType: 'text',
						timeout: 1000, 
						async: false,
						error: function(text){ 
							alert('读取数据失败，请联系管理员！'); 
							return false;
						}, 
						success: function(text){
							 if(text>0){
							   alert("提交成功");
							   document.location.reload();
				   			}else{
				   			   alert("提交失败");
				   			   document.location.reload();
				   			} 
						}        
			    });
	        }
	}
}
function returnData(){
  var activityIds="";
  var checkActivityIds="";
  if($('#tableId input[name="activityIds"]:checkbox:checked').length < 1){
	  		alert('请选择一条记录再进行退回申请！');
	  		return false;
	  	}else{
	  		$('#tableId input[name="activityIds"]:checkbox:checked').each(function(){
			    var checks=$("#checks_"+$(this).val()).val();
			    if(checks!="b"){
					      activityIds+=$(this).val()+","; 
					      $(this).attr('checked',false);
				}else{
					      checkActivityIds+=$(this).val()+","; 
			    }
			});
       }
       if(activityIds!=""){
       	  alert("活动id"+activityIds+"已审核,不能退回");
		  return false;
       }
       
       if(confirm("确定退回申请吗？")){
			   $.ajax({
						url:'marketActivity/checkDada.html', 
						type: 'POST', 
						data: {str:checkActivityIds,flag:'4'},
						dataType: 'text',
						timeout: 1000, 
						async: false,
						error: function(text){ 
							alert('读取数据失败，请联系管理员！'); 
							return false;
						}, 
						success: function(text){
							 if(text>0){
							   alert("退回申请成功");
							   document.location.reload();
				   			}else{
				   			   alert("退回申请失败");
				   			   document.location.reload();
				   			} 
						}        
			    });
	        }
}
function search(){
	$("#activityForm").submit();
}

function exportUser(){
	document.location = "user/excel.html";
}


	 //图片预览
function ShowDiv(pic){ 
	 divPic.innerHTML="<img src="+pic+" width='550' height='550'>";
	 divPic.style.display="block"; 
}
function HideDiv(){ 
	divPic.style.display="none";
}

function checkBox(obj,isCheck) {
 /*  var roleNames="${sessionScope.roleNames}";
  if(roleNames!="超级系统管理"){
  	if(isCheck=='b'){
       alert("该记录已提交申请不能做任何处理操作");
   	$(obj).attr('checked',false);
   }
  }  */
}
		
	</script>
</body>
</html>