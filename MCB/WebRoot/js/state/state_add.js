	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#stateCode").val()==""){
					alert("带有*号的必填!");
					$("#stateCode").focus();
					return false;
				}
				if($("#stateName").val()==""){
					alert("带有*号的必填!");
					$("#stateName").focus();
					return false;
				}
				if($("#stagCode").val()==""){
					alert("带有*号的必填!");
					$("#stagCode").focus();
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
	function failed(){
		alert("分期类型编号已存在!");
		$("#stateCode").select();
		$("#stateCode").focus();
	}