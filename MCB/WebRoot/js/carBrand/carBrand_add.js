var count = 0;var dg;var dgs;
var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if (dgs != undefined && dgs != null) {
					dgs.cancel();
				}
				if(editor.count("text")>1500){
				  alert("活动内容超过字数限制，请适当删减部分内容");
				  return;
				}
				if($("#brandName").val()==""){
					alert("带有*号的必填!");
					$("#brandNames").focus();
					return false;
				}
				if($("#imgAddressUrl").val()==""){
					alert("带有*号的必填!");
					$("#imgAddressUrl").focus();
					return false;
				}
				$("#brandNames").submit();
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