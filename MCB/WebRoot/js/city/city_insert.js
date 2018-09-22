	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#cityNo").val()==""){
					alert("带有*号的必填!");
					$("#cityNo").focus();
					return false;
				}else{
					var reg=/^[0-9]*$/;
				    if(!reg.test($("#cityNo").val())){
				    	alert("编号只能输入数字！");
				    	$("#cityNo").val("");
				    	$("#cityNo").focus();
				    	return false;
				    } 
				   
				}
				if($.trim($("#cityName").val())==""){
					alert("带有*号的必填!");
					setTimeout(function () {
						$("#cityName").focus();
		            }, 500);
					return false;
				}else{
					var reg=/[\u4e00-\u9fa5]$/;
					if(!reg.test($("#cityName").val())){
						alert("只能输入中文!");
						$("#cityName").val("");
						setTimeout(function () {
							$("#cityName").focus();
			            }, 500);
				    	return false;
				    } 
					
				}
				if($.trim($("#cityParentNo").val())!=""){
					var reg=/^[0-9]*$/;
				    if(!reg.test($("#cityParentNo").val())){
				    	alert("父级别编号只能输入数字！");
				    	$("#cityParentNo").val("");
				    	$("#cityParentNo").focus();
				    	return false;
				    } 
				}
				if($("#cityAdministration").val()==""){
					alert("带有*号的必选!");
					$("#cityAdministration").focus();
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
	function failed(){
		alert("编号已存在！");
		$("#cityNo").select();
		$("#cityNo").focus();
	}