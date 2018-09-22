			$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	function search(){
		$("#patchFrom").submit();
		}
	function delPatch(patchTypeId){
		if(confirm("确定删除此数据？")){
			$.ajax({
				url:'patch/delPatch.html', 
				type: 'POST', 
				data: {patchTypeId:patchTypeId},
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
	
	function addPatch(){
		var dg = new $.dialog({
			title:'新增补件类型',
			id:'Patch_new',
			width:330,
			height:300,
			iconTitle:false,
			cover:true,
			maxBtn:false,
			xButton:true,
			resize:false,
			page:'patch/addPacth.html'
			});
		dg.ShowDialog();
	}
	