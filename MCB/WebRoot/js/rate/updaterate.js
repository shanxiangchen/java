	//验证结束时间是否大于开始时间(时间格式为:xxxx-xx-xx)
	
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn('ok','保存',function(){
			
			if($("#ratePeriod").val()==""){
		    	alert("期数不能为空");
		    	$("#ratePeriod").val("");
		    	$("#ratePeriod").focus();
		    	return false;
		    } 
			var reg=/^[0-9]*$/;
		    if(!reg.test($("#ratePeriod").val())){
		    	alert("期数只能输入数字！");
		    	$("#ratePeriod").val("");
		    	$("#ratePeriod").focus();
		    	return false;
		    } 
			
			if($("#rates").val()==""){
		    	alert("费率不能为空");
		    	$("#rates").val("");
		    	$("#rates").focus();
		    	return false;
		    } 
			 var stagRate=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
			 if(!stagRate.test($("#rates").val())){
		    	alert("费率只能输入整数和小数！");
		    	$("#rates").val("");
		    	$("#rates").focus();
		    	return false;
		    } 
				$("#rateForm").submit();
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
	