	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#patchTypeCode").val()==""){
					alert("带有*号的必填!");
					$("#patchTypeCode").focus();
					return false;
				}else{
					var reg=/^[0-9]*$/;
				    if(!reg.test($("#patchTypeCode").val())){
				    	alert("请输入正确的原因码格式！");
				    	$("#patchTypeCode").val("");
				    	$("#patchTypeCode").focus();
				    	return false;
				    } 
				}
				if($("#patchTypeNmae").val()==""){
					alert("带有*号的必填!");
					$("#patchTypeNmae").focus();
					return false;
				}
				if($("#patchPageShow").val()==""){
					alert("带有*号的必填!");
					$("#patchPageShow").focus();
					return false;
				}
				if($("#patchPageExplain").val()==""){
					alert("带有*号的必填!");
					$("#patchPageExplain").focus();
					return false;
				}
				$("#patchForm").submit();
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
		alert("此原因码已存在");
		$("#patchTypeCode").select();
		$("#patchTypeCode").focus();
	}