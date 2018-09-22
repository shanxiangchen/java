	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#stateName").val()==""){
					alert("带有*号的必填!");
					$("#stateName").focus();
					return false;
				}
				$("#stateForm").submit();
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