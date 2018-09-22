	$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
		function search(){
			$("#stateForm").submit();
		}
		
		function addState(){
			var dg = new $.dialog({
				title:'新增状态',
				id:'state_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'state/stateAdd.html'
				});
    		dg.ShowDialog();
		}
		
		function editState(stateId){
			var dg = new $.dialog({
				title:'编辑状态',
				id:'state_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'state/state_Edit.html?stateId='+stateId
				});
    		dg.ShowDialog();
		}	
		
		//绝对路径
		function getBasePath(){
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
			return basePath;
		}
		function stateDel(stateId,stateCode){
			var url = getBasePath()+'/state/getCountCode.html?stateCode='+stateCode;
			$.ajax({
				url:url,
				type:'post',
				dataType:'text',
				success:function(objs){
					if(objs!=0){
						alert("此数据有关联,请删除对应关联,方可删除!");
					}else{
						if(confirm("确定要删除该记录？")){
							$.ajax({
								url:'state/state_del.html', 
								type: 'POST', 
								data: {stateId:stateId},
								dataType:'text',
								async: false,
								success: function(text){
									if(text=="success"){
										alert("删除成功！");
										 document.location.reload();
									}else{
										 alert("删除失败！");
										 document.location.reload();
									}
						   		},
								error:function(){
				     	               alert("删除失败！");
				     	               document.location.reload();
					            }  
							});
						} 
					}
				}
			});
		}