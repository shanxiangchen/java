$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
				$("#shopForm").submit();
			}

		function addshop(){
			var dg = new $.dialog({
				title:'新增特惠商户',
				id:'shop_new',
				width:800,
				height:340,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'shop/add.html'
				});
    		dg.ShowDialog();
		}
		function editshop(oddsshopid){
			var dg = new $.dialog({
				title:'编辑特惠商户',
				id:'shop_edit',
				width:800,
				height:340,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'shop/edit.html?oddsshopid='+oddsshopid
				});
    		dg.ShowDialog();
		}
		
		
	 function delshop(oddsshopid){
			if(confirm("确定要删除该记录？")){
				/*var url = "shop/delete.html?oddsshopid="+oddsshopid;
				$.get(url,function(data){
					if(data=="success"){
						alert("删除成功！");
						document.location.reload();
					}else{
						alert("删除失败！");
						document.location.reload();
					}
				});*/
				$.ajax({
					url:'shop/delete.html', 
					type: 'POST', 
					data: {oddsshopid:oddsshopid},
					dataType: 'text',
					timeout: 1000, 
					async: false,
					success: function(text){
						if(text=="success"){
								alert("删除成功");
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
	 function exportshop(){
			document.location = "shop/excel.html";
		}
	 
		function listShopphoto(oddsshopid){
			var dg = new $.dialog({
				title:'商户图片',
				id:'Shopphoto_new',
				width:500,
				height:340,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'shop/listShopphoto.html?oddsshopid='+oddsshopid
				});
    		dg.ShowDialog();
		}
	 
	 