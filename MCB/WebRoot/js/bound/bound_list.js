$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function search(){
			$("#userForm").submit();
		}
		
		function getBasePath(){
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
			return basePath;
		}
		function delBound(cardId,userPhone){
			var url = getBasePath()+"/bound/getBoundCount.html?userPhone="+userPhone;
			$.ajax({
				url:url,
				type:'post',
				dataType:'text',
				success:function(obj){
						if(obj!=1){
							var url = getBasePath()+"/bound/getDIsefaulTCrad.html?cardId="+cardId;
							$.ajax({
								url:url,
								type:'post',
								dataType:'text',
								success:function(objs){
								if(objs=="1"){
									alert("先删除非默认卡");
								}else{
									if(confirm("确定要解除该用户？")){
										$.ajax({
											url:'bound/delete.html', 
											type: 'POST', 
											data: {cardId:cardId},
											dataType:'text',
											async: false,
											success: function(text){
												if(text=="success"){
													 document.location.reload();
												}
									   		},
											error:function(){
							     	               alert("解绑失败！");
							     	               document.location.reload();
								                 } 
										});
									}
								}
							}
							});
						}else{
							if(confirm("确定要解除该用户？")){
								$.ajax({
									url:'bound/delete.html', 
									type: 'POST', 
									data: {cardId:cardId},
									dataType:'text',
									async: false,
									success: function(text){
										if(text=="success"){
											 document.location.reload();
										}
							   		},
									error:function(){
					     	               alert("解绑失败！");
					     	               document.location.reload();
						                 } 
								});
							}	
							}
					}
			});
			
		}
	
		/*//单选框全选
		function sltAllbound(){
			if($("#cardIds").attr("checked")){
				$("input[name='cardIdss']").attr("checked",true);
			}else{
				$("input[name='cardIdss']").attr("checked",false);
			}
		}
		
		
		
		//批量删除
		function Batdelete(){
			var str="";
			if($('#tableId input[name="cardIdss"]:checkbox:checked').length < 1){
						alert("请选择一条记录再进行解绑！");
						return false;
				}
				else{
					$('#tableId input[name="cardIdss"]:checked:checked').each(function(){
					str+=$(this).val()+",";
					});
					if(confirm("确定要解绑该用户?")){	
						 $.ajax({
								url:'bound/deletes.html', 
								type: 'POST', 
								data: {str:str},
								dataType: 'text',
								async: false,
								success: function(text){
									if(text=="success"){
									   document.location.reload();
									   $(this).attr('checked',false);  
						   		}
							}
					    });
					}
				}
		}*/
		
		
		function editBound(cardId){
			var dg = new $.dialog({
				title:'修改卡号',
				id:'bound_edit',
				width:330,
				height:130,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'bound/boundById.html?cardId='+cardId
				});
    		dg.ShowDialog();
		}


			