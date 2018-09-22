$(document).ready(function(){
	$(".main_info:even").addClass("main_table_even");
});
function search(){
	$("#userForm").submit();
}
function addExpress(){
	var dg = new $.dialog({
	title:'新增快递',
	id:'express_new',
	width:330,
	height:300,
	iconTitle:false,
	cover:true,
	maxBtn:false,
	xButton:true,
	resize:false,
	page:'express/express_insert.html'
});
 dg.ShowDialog();
}
function delExpress(expressServiceNameId){
	if(confirm("确定要删除该记录？")){
	$.ajax({
		url:'express/delete.html', 
		type: 'POST', 
		data: {expressServiceNameId:expressServiceNameId},
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
function editExpress(expressServiceNameId){
	var dg = new $.dialog({
	title:'修改快递',
	id:'Express_edit',
	width:330,
	height:300,
	iconTitle:false,
	cover:true,
	maxBtn:false,
	resize:false,
	page:'express/express_update.html?expressServiceNameId='+expressServiceNameId
	});
    dg.ShowDialog();
}