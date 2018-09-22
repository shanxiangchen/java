			$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
	function search(){
		$("#rateForm").submit();
		}
	
	//绝对路径
	function editratessid(id){
		var dg = new $.dialog({
			title:'编辑状态',
			id:'ratess_edit',
			width:400,
			height:200,
			iconTitle:false,
			cover:true,
			maxBtn:false,
			resize:false,
			page:'ratess/ratess_Edit.html?id='+id
			});
		dg.ShowDialog();
	}	
	
