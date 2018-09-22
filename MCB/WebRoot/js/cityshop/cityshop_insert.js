function getBasePath()
		{
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
			return basePath;
		}
		var url = getBasePath()+"/CityShop/ajax_add.html";
	 	$(function(){
			$.ajax({
				url:url,
				type:'post',
				dataType:'json',
				success:function(objs){
					city1 = objs;
// 						获取省份的下拉列表
						var province=$("#city");
// 						遍历
						for(var i=0;i<objs.length;i++){
					
							var op="<option value='"+objs[i].cityno+"'>"+objs[i].cityname+"</option>";
							province.append(op);
						}
					}
			});	
}); 

		var dg;
 		$(document).ready(function(){
 			dg = frameElement.lhgDG;
 			dg.addBtn('ok','保存',function(){
 				if($("#shopRingCity").val()==""){
 					alert("带有*号的必填!");
 					$("#shopRingCity").focus();
 					return false;
 				}
 				if($.trim($("#shopRingName").val())==""){
 					alert("带有*号的必填!");
 					$("#shopRingName").focus();
 					return false;
 				}
 				if($("#shopRingNo").val()==""){
 					alert("带有*号的必填!");
 					$("#shopRingNo").focus();
 					return false;
 				}else{
					var reg=/^[0-9]*$/;
				    if(!reg.test($("#shopRingNo").val())){
				    	alert("商圈编号只能输入数字！");
				    	$("#shopRingNo").val("");
				    	$("#shopRingNo").focus();
				    	return false;
				    } 
				}
 				$("#errorCodeForm").submit();
 				return true;
 			});
 		});
		
 	function success(){
 			if(dg.curWin.document.forms[0]){
 				dg.curWin.document.forms[0].action = dg.curWin.location+"";
 				dg.curWin.document.forms[0].submit();
 			}else{
				dg.curWin.location.reload();
 			}
 			dg.cancel();
		} 
 	function failed(){
		alert("商圈编号已存在");
		$("#shopRingNo").select();
		$("#shopRingNo").focus();
	}
 	
		function onSelectItem()
		{
			var sel_obj = document.getElementById("city");
			var index = sel_obj.selectedIndex;
 			var name=sel_obj.options[index].text;
 			var no=$('select').val();
 			$("#shopRingCity").val(name);
 			$("#cityno").val(no);
		}