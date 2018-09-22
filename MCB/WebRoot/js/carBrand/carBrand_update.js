		var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn('ok','保存',function(){
			if($("#brandName").val()==""){
				alert("带有*号的必填!");
				$("#CarBrandForm").focus();
				return false;
			}
			$("#CarBrandForm").submit();
			
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
	alert("汽车品牌已存在!");
	$("#brandName").select();
	$("#brandName").focus();
}