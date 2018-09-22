	//验证结束时间是否大于开始时间(时间格式为:xxxx-xx-xx)
	
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn('ok','保存',function(){
				$("#ratessForm").submit();
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
	