
		var dg;
 		$(document).ready(function(){
 			dg = frameElement.lhgDG;
 			dg.addBtn('ok','保存',function(){
 				if($("#city").val()==""){
 					alert("带有*号的必填!");
 					$("#city").focus();
 					return false;
 				}
 				if($("#shopRingName").val()==""){
 					
 					alert("带有*号的必填!");
 					$("#shopRingName").focus();
 					return false;
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
		function onSelectItem()
		{
			var sel_obj = document.getElementById("city");
			var index = sel_obj.selectedIndex;
 			var name=sel_obj.options[index].text;
 			var cityno=$("select").val();
 			$("#shopRingCity").val(name);
 			$("#cityno").val(cityno);
		}