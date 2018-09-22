var dg;
$(document).ready(function(){
	dg = frameElement.lhgDG;
	dg.addBtn('ok','保存',function(){
		if($("#infoTemplateType").val()==""){
			alert("带有*号的必填!");
			$("#infoTemplateType").focus();
			return false;
		}
		if($("#infoTemplateEncoding").val()==""){
			alert("带有*号的必填!");
			$("#infoTemplateEncoding").focus();
			return false;
		}
		if($.trim($("#infoTemplateDescribe").val())=="最多可输入250字"){
			alert("带有*号的必填!");
			$("#infoTemplateDescribe").focus();
			return false;
		}else if($.trim($("#infoTemplateDescribe").val())==""){
			alert("带有*号的必填!");
			$("#infoTemplateDescribe").focus();
			return false;
		}
		$("#templateFrom").submit();
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
	alert("信息编码存在");
	$("#infoTemplateEncoding").select();
	$("#infoTemplateEncoding").focus();
}