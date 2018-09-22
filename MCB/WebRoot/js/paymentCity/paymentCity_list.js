$(document).ready(function(){
		$(".main_info:even").addClass("main_table_even");
	});
		function search(){
			$("#paymentCityForm").submit();
		}	
		
		function addPatmentCity(){
			var dg = new $.dialog({
				title:'新增分期城市',
				id:'PaymentCity_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'PaymentCity/addPaymentCity.html'
				});
    		dg.ShowDialog();
		}
		
		function getPaymentCityById(cityId){
			var dg = new $.dialog({
				title:'编辑分期城市',
				id:'PaymentCity_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'PaymentCity/getPaymentCityById.html?cityId='+cityId
				});
    		dg.ShowDialog();
		}
		
		function dePaymentCity(cityId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'PaymentCity/delPaymentCity.html', 
					type: 'POST', 
					data: {cityId:cityId},
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