	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#happyName").val()==""){
					alert("带有*号的必填!");
					$("#happyName").focus();
					return false;
				}
				$("#happyForm").submit();
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
		alert("热词顺序已存在!");
		$("#happyPoollorder").select();
		$("#happyPoollorder").focus();
	}