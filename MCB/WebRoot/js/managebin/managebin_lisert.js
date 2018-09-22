var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($.trim($("#binProductCode").val())==""){
					alert("卡产品BIN编码不能为空");
					$("#binProductCode").val("");
					$("#binProductCode").focus();
					return false;
				}else{
					var reg=/^[0-9]*$/;
					if(!reg.test($("#binProductCode").val())){
						alert("卡产品编码只能输入数字");
						$("#binProductCode").val("");
						$("#binProductCode").focus();
						return false;
					}
				}
				if($.trim($("#binProductName").val())==""){
					alert("卡产品BIN名称不能为空");
					$("#binProductName").val("");
					$("#binProductName").focus();
					return false;
				}
				if($.trim($("#binProductNoLow").val())==""){
					alert("卡产品BIN号低段不能为空");
					$("#binProductNoLow").val("");
					$("#binProductNoLow").focus();
					return false;
				}else{
					var reg=/^[0-9]*$/;
					if(!reg.test($("#binProductNoLow").val())){
						alert("号段只能输入数字");
						$("#binProductNoLow").val("");
						$("#binProductNoLow").focus();
						return false;
					}
				}
				if($.trim($("#binProductNoHigh").val())==""){
					alert("卡产品BIN号高段不能为空");
					$("#binProductNoHigh").val("");
					$("#binProductNoHigh").focus();
					return false;
				}else{
					var reg=/^[0-9]*$/;
					if(!reg.test($("#binProductNoHigh").val())){
						alert("号段只能输入数字");
						$("#binProductNoHigh").val("");
						$("#binProductNoHigh").focus();
						return false;
					}
				}
				
				if($("#stateCode").val()==""){
					alert("请选择是否白金卡");
					$("#stateCode").focus();
					return false;
				}
				
				if($("#cardOfType").val()==""){
					alert("请选择卡产品所属种类");
					$("#cardOfType").focus();
					return false;
				}
				
				if(parseInt($("#binProductNoLow").val())>parseInt($("#binProductNoHigh").val())){
					alert("低号段不能大于高号段");
					return false;
					
				}
				$("#managebinForm").submit();
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