	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#stagCode").val()==""){
					alert("带有*号的必填!");
					$("#stagCode").focus();
					return false;
				}
				var reg=/^[0-9]*$/;
			    if(!reg.test($("#stagCode").val())){
			    	alert("分期类型编码只能输入数字！");
			    	$("#stagCode").val("");
			    	$("#stagCode").focus();
			    	return false;
			    } 
				
				if($("#stagName").val()==""){
					alert("带有*号的必填!");
					$("#stagName").focus();
					return false;
				}
				$("#StagingTypeForm").submit();
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
		$("#stagCode").select();
		$("#stagCode").focus();
	}