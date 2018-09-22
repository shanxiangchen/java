var dg;
$(document).ready(function(){
	dg = frameElement.lhgDG;
	dg.addBtn('ok','保存',function(){
		if($("#expressServiceName").val()==""){
			alert("带有*号的必填!");
			$("#expressServiceName").focus();
			return false;
		}
		if($("#expressServiceAddress").val()==""){
			alert("带有*号的必填!");
			$("#expressServiceAddress").focus();
			return false;
		}
		if($("#isUse").val()==""){
			alert("带有*号的必填!");
			$("#isUse").focus();
			return false;
		}
		$("#userForm").submit();
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
		