	$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
		function search(){
			$("#StagingTypeForm").submit();
		}	
		
		function addstagingType(){
			var dg = new $.dialog({
				title:'新增分期类型',
				id:'city_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'stagingType/stagingTypeAdd.html'
				});
    		dg.ShowDialog();
		}
		
		function editStagingType(id){
			var dg = new $.dialog({
				title:'编辑分期类型',
				id:'city_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'stagingType/stagingType_Edit.html?id='+id
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
		
		function stagingTypeDel(id,stagCode){
			var url = getBasePath()+'/stagingType/getCountCode.html?stagCode='+stagCode;
			$.ajax({
				url:url,
				type:'post',
				dataType:'text',
				success:function(objs){
					if(objs=="1"){
						alert("此数据有关联,请删除对应关联,方可删除!");
					}else{
						if(confirm("确定要删除该记录？")){
							$.ajax({
								url:'stagingType/stagingType_del.html', 
								type: 'POST', 
								data: {id:id},
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