		function addTemplate(){
			var dg = new $.dialog({
				title:'新增信息',
				id:'Template_new',
				width:330,
				height:250,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'template/template_Add.html'
				});
    		dg.ShowDialog();
		}
		function editTemlate(infoTenplateId){
			var dg = new $.dialog({
				title:'修改信息',
				id:'Template_edit',
				width:330,
				height:250,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'template/template_Edit.html?infoTenplateId='+infoTenplateId
				});
    		dg.ShowDialog();
		}
		
		function delTemplate(infoTenplateId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'template/template_Del.html', 
					type: 'POST', 
					data: {infoTenplateId:infoTenplateId},
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
		
		function search(){
			$("#TemplateFrom").submit();
		}