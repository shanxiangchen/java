	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#cityCode").val()==""){
					alert("带有*号的必填!");
					$("#cityCode").focus();
					return false;
				}
				var cityCode=/^[0-9]+$/;
				if(!cityCode.test($("#cityCode").val())){
					alert("请输入整数!");
					return false;
				} 
				
				if($("#cityName").val()==""){
					alert("带有*号的必填!");
					$("#cityName").focus();
					return false;
				}
				var stars=/[\u4e00-\u9fa5]$/;
				 if(!stars.test($("#cityName").val())){
					alert("只能输入中文!");
					return false;
				} 
				
				$("#paymentCityForm").submit();
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