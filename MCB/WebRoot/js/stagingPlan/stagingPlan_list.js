	$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
		function search(){
			$("#StagingPlanForm").submit();
		}	
		
		function addstagingPlan(){
			var dg = new $.dialog({
				title:'新增分期计划',
				id:'stagingPlan_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'stagingPlan/stagingPlanAdd.html'
				});
    		dg.ShowDialog();
		}
		
		function editStagingPlan(planId){
			var dg = new $.dialog({
				title:'编辑分期计划',
				id:'stagingPlan_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'stagingPlan/stagingPlanEdit.html?planId='+planId
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
		
		function stagingPlanDel(planId,stagingPlanCode){
			var url = getBasePath()+'/stagingPlan/getCountCode.html?stagingPlanCode='+stagingPlanCode;
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
								url:'stagingPlan/stagingPlan_del.html', 
								type: 'POST', 
								data: {planId:planId},
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