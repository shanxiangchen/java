		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if(!validate()){
					return;
				}
				$("#stagingPlanForm").submit();
				return true;
			});
		});
		
		
		
		function validate(){
			if($("#stagingPlanCode").val()==""){
				alert("带有*号的必填!");
				$("#stagingPlanCode").focus();
				return false;
			}
			var reg=/^[0-9]*$/;
		    if(!reg.test($("#stagingPlanCode").val())){
		    	alert("分期计划编码只能输入数字！");
		    	$("#stagingPlanCode").val("");
		    	$("#stagingPlanCode").focus();
		    	return false;
		    } 
			if($("#stagingPlanName").val()==""){
				alert("带有*号的必填!");
				$("#stagingPlanName").focus();
				return false;
			}
			if($("#stagingPlanRate").val()==""){
				alert("带有*号的必填!");
				$("#stagingPlanRate").focus();
				return false;
			}
			 var stagRate=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
			/* var  re=/^\d+(\.\d+)?$/;
			 var patrn = /^\d+(\.\d+)?$/;*/
			 if(!stagRate.test($("#stagingPlanRate").val())){
		    	alert("费率只能输入整数和小数！");
		    	$("#stagingPlanRate").val("");
		    	$("#stagingPlanRate").focus();
		    	return false;
		    } 
			if($("#stateCode").val()==""){
				alert("带有*号的必填!");
				$("#stateCode").focus();
				return false;
			}
			return true;
		}	
		
		
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
		alert("保存失败!");
		$("#stagingPlanCode").select();
		$("#stagingPlanCode").focus();
	}