$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
			$("#ManageBinForm").submit();
		}
		
		function addManageBin(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增卡BIN配置',
				id:'managebin_new',
				width:600,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'managebin/ManageBin_insert.html'
				});
    		dg.ShowDialog();
		}
		
		function delManageBin(binProductId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'managebin/delete.html', 
					type: 'POST', 
					data: {binProductId:binProductId},
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
		
		function editUser(binProductId){
			var dg = new $.dialog({
				title:'修改卡BIN配置',
				id:'managebin_edit',
				width:600,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'managebin/managebin_update.html?binProductId='+binProductId
				});
    		dg.ShowDialog();
		}