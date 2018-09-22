$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function search(){
			$("#cityshopForm").submit();
		}
		function addCityShop(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增商户圈',
				id:'cityShop_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'CityShop/CityShop_add.html'
				});
    		dg.ShowDialog();
		}
		
		function delCityShop(shopRingId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'CityShop/delete.html', 
					type: 'POST', 
					data: {shopRingId:shopRingId},
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
		
	 	function editCityShop(shopRingId){
			var dg = new $.dialog({
				title:'修改用户',
				id:'user_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'CityShop/cityShop_update.html?shopRingId='+shopRingId
				});
    		dg.ShowDialog();
		} 