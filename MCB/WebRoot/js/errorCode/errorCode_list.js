$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
			$("#ErrorCodeForm").submit();
		}
		
		
		function addErrorCode(){
			var dg = new $.dialog({
				title:'新增错误码',
				id:'ErrorCode_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'errorCode/errorCode_insert.html'
				});
    		dg.ShowDialog();
		}
		
		function delErrorCode(errorCodeId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'errorCode/delete.html', 
					type: 'POST', 
					data: {errorCodeId:errorCodeId},
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
		
		function editerrorCode(errorCodeId){
			var dg = new $.dialog({
				title:'修改错误码',
				id:'errorCode_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'errorCode/errorCode_update.html?errorCodeId='+errorCodeId
				});
    		dg.ShowDialog();
		}