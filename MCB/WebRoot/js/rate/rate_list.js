			$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	function search(){
		$("#rateForm").submit();
		}
	function addRate(){
		var dg = new $.dialog({
		title:'新增费率',
		id:'rate_new',
		width:660,
		height:400,
		iconTitle:false,
		cover:true,
		maxBtn:false,
		xButton:true,
		resize:false,
		page:'rate/add.html'
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
	function editrateid(rateId){
		var dg = new $.dialog({
			title:'编辑状态',
			id:'rate_edit',
			width:630,
			height:300,
			iconTitle:false,
			cover:true,
			maxBtn:false,
			resize:false,
			page:'rate/rate_Edit.html?rateId='+rateId
			});
		dg.ShowDialog();
	}	
	
	
	
	function delRateById(rateId){
		if(confirm("确定要删除该记录？")){
			$.ajax({
				url:'rate/deleteById.html', 
				type: 'POST', 
				data: {rateId:rateId},
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