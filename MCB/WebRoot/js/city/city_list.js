$(document).ready(function(){
	$(".main_info:even").addClass("main_table_even");
});
		function search(){
			$("#cityForm").submit();
		}	
		
		function delUser(cityId){
			if(confirm("确定要删除该记录？")){
				$.ajax({
					url:'bankCity/delete.html', 
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
		
		
		
		function addCity(){
			var dg = new $.dialog({
				title:'新增地区',
				id:'city_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bankCity/bankCity_insert.html'
				});
    		dg.ShowDialog();
		}
		
		function editBankCity(cityId){
			var dg = new $.dialog({
				title:'修改用户',
				id:'city_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'bankCity/bankCity_update.html?cityId='+cityId
				});
    		dg.ShowDialog();
		}