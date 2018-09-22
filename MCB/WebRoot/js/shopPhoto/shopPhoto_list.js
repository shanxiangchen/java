$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});

function editShopphoto(photoalbumid){
	var dg = new $.dialog({
		title:'编辑商户图片',
		id:'Shopphoto_edit',
		width:450,
		height:300,
		iconTitle:false,
		cover:true,
		maxBtn:false,
		xButton:true,
		resize:false,
		page:'../shopphto/editShopPhoto.html?photoalbumid='+photoalbumid
		});
	dg.ShowDialog();
} 

function addShopphoto(){
	var oddsshopId = $("#oddsshopId").val();
	var dg = new $.dialog({
		title:'编辑商户图片',
		id:'Shopphoto_edit',
		width:450,
		height:300,
		iconTitle:false,
		cover:true,
		maxBtn:false,
		xButton:true,
		resize:false,
		page:'../shopphto/addShopPhoto.html?oddsshopId='+oddsshopId
		});
	dg.ShowDialog();
}

function deShopphoto(photoalbumid){
	if(confirm("确定是否删除该图片？")){
		var url='../shopphto/deleteShopPhoto.html?photoalbumid='+photoalbumid;
		$.get(url,function(data){
			if(data=="success"){
				alert("删除成功！");
				document.location.reload();
			}else{
				document.location.reload();
			}
		});
	};
};
