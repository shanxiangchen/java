		var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn('ok','保存',function(){
			if($.trim($("#linesNumber").val())==""){
				alert("次数不能为空");
				$("#linesNumber").val("");
				$("#linesNumber").focus();
				return false;
			}
			var reg=/^[0-9]*$/;
		    if(!reg.test($("#linesNumber").val())){
		    	alert("次数只能输入数字！");
		    	$("#linesNumber").val("");
		    	$("#linesNumber").focus();
		    	return false;
		    } 
			
			if($.trim($("#linesMaxValue").val())==""){
				alert("额度权限不能为空");
				$("#linesMaxValue").val("");
				$("#linesMaxValue").focus();
				return false;
			}
			if($.trim($("#promptInformation").val())==""){
				alert("提示信息不能为空");
				$("#promptInformation").val("");
				$("#promptInformation").focus();
				return false;
			}
			$("#typeForm").submit();
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

