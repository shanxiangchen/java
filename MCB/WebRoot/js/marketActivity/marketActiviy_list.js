
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
    if(roleNames!="1"){
	    if(isCheck!="a" && isCheck!="d"){
	      updateFlag=1;
	      dec="---活动不为未审核或已退回状态不能修改";
	    }
	      updateFlag=1;
	      dec="---活动不为未发布,并不为已退回状态不能修改";
    }else{
    	 if(activityStatus=="1" && isCheck=="c"){
	      updateFlag=1;
	      dec="---活动为已开始,并为已提交申请状态不能修改";
	     }
	     if(isCheck=="b" || isCheck=="d" ){
	      updateFlag=1;
	      dec="---活动为已提交申请或已退回状态不能修改";
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
		page:'marketActivity/infodate.html?activityId='+activityId+'&citysName='+citysName+'&cityNo='+cityNo+'&updateFlag='+updateFlag
		});
	dg.ShowDialog();
}

function editUser(activityId,activityStatus,isCheck,citysName,cityNo){
	//获取当前登录人的角色权限
    var roleNames="${sessionScope.user.rights}";
    var updateFlag=0;
    var dec="";
    if(roleNames==""){
	    if(activityStatus=="1" && isCheck=="c"){
		      updateFlag=1;
		      dec="---活动为已发布,并为已审核状态不能修改";
		    }
		if(isCheck=="b"){
		      updateFlag=1;
		      dec="---活动为已提交申请不能修改";
		     }
    	}
   if(roleNames!=""){
	     if(activityStatus=="1" && isCheck=="b"){
	      	updateFlag=1;
		     dec="---活动为已提交申请不能修改";
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
		page:'marketActivity/update.html?activityId='+activityId+'&citysName='+citysName+'&cityNo='+cityNo+'&updateFlag='+updateFlag
		});
	dg.ShowDialog();
}

 function delUser(){
    	//获取当前登录人的角色权限
    	var roleNames="${sessionScope.user.rights}";
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
					   }/* else if(checks =="c" && activityStatus=="1"){
					      activityIdTwo+=$(this).val()+","; 
					      $(this).attr('checked',false);
					  } */else{
					      checkActivityIds+=$(this).val()+","; 
					  }
			   }	   
			});
			if(roleNames!=""){
				if(activityIds!=""){
				  		alert("活动id"+activityIds+"不为未审核或已退回状态不能删除");
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
    var roleNames="${sessionScope.user.rights}";
	var activityIds="";
	var activityIdTwo="";
	var checkActivityIds="";
		//验证是否选择了记录
	  	if($('#tableId input[name="activityIds"]:checkbox:checked').length < 1){
	  		alert('请选择一条记录再进行审核！');
	  		return false;
	  	}else{
	  		$('#tableId input[name="activityIds"]:checkbox:checked').each(function(){
			   var checks=$("#checks_"+$(this).val()).val();
			   if(roleNames==""){
			   		   if(checks!="a"){
					      activityIds+=$(this).val()+","; 
					      $(this).attr('checked',false);
					   }else{
					      checkActivityIds+=$(this).val()+","; 
					   }
			   }else{
			   		  if(checks =="c" || checks =="d" ){
					      activityIds+=$(this).val()+","; 
					      $(this).attr('checked',false);
					   } else{
					      checkActivityIds+=$(this).val()+","; 
					   }
			   }
			   
			});
			
			if(roleNames==""){
				if(activityIds!=""){
			  		alert("活动id"+activityIds+"不为未审核状态不能再次提交审核");
			  		return false;
				}
			}
			
			if(roleNames!=""){
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
       	  alert("活动id"+activityIds+"不为已提交申请状态不能退回");
		  return false;
       }
       
       if(confirm("确定退回申请吗？")){
			   $.ajax({
						url:'marketActivity/checkDada.html', 
						type: 'POST', 
						data: {str:checkActivityIds,flag:'2'},
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
		