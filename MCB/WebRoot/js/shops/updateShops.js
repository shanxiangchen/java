	//验证结束时间是否大于开始时间(时间格式为:xxxx-xx-xx)
	function checkTime(startTime, endTime) {
				var nd = new Date(startTime.replace("-", "/"));
				var ld = new Date(endTime.replace("-", "/"));
				if (Date.parse(ld) - Date.parse(nd) < 0) {
					alert("结束日期要大于或等于开始日期");
					return false;
				} else {
					return true;
				};
	    }
	
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn('ok','保存',function(){
			if($("#oddsshopname").val()==""){
				alert("商户名称不能为空!");
				$("#oddsshopname").focus();
				return false;
			}if($("#oddsshopaddress").val()==""){
				alert("商户地址不能为空!");
				$("#oddsshopaddress").focus();
				return false;
			} if($("#oddsshoplongitude").val()==""){
				 alert("商户经度不能为空！");
				 $("#oddsshoplongitude").focus();
				 return false;
			}if($("#oddsshoplatitude").val()==""){
				alert("商户纬度不能为空！");
				$("#oddsshoplatitude").focus();
				return false;
			}if($("#oddsshopphone").val()==""){
				alert("联系方式不能为空!");
				$("#oddsshopphone").focus();
				return false;
			}else{
				var reg=/^[0-9]*$/;
			    if(!reg.test($("#oddsshopphone").val())){
			    	alert("联系方式只能输入数字！");
			    	$("#oddsshopphone").val("");
			    	$("#oddsshopphone").focus();
			    	return false;
			    } 
			   
			}
			if($("#oddsshopcityId").val()==""){
			     alert("商户所在的城市 ");
				 $("#oddsshopcityId").focus();
				return false;
			} if($("#oddsshopring").val()==""){
			     alert("请选择商圈 ");
				 $("#oddsshopring").focus();
				return false;
			}if($("#oddsshopstatus ").val()==""){
			     alert("请选择状态");
				 $("#oddsshopstatus").focus();
				return false;
			} 
			var reg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
			if($("#oddsshoppictureurl").val()!=""){
				var start=$("#oddsshoppictureurl").val().lastIndexOf("\\");
				var end=$("#oddsshoppictureurl").val().lastIndexOf(".");
				var name=$("#oddsshoppictureurl").val().substring(start+1,end);
			    if(reg.test(name)){
				   alert("图片名称不能包含中文字符!");
				   return false;
				} 
			    if(!checkImgName($("#oddsshoppictureurl").val())){
					return false;
				}
			} 
			if($("#oddsshoptypeid").val()=="请选择"){
			     alert("请选择类型");
				 $("#oddsshoptypeid").focus();
				return false;
			}
			var a = /^(\d{4})-(\d{2})-(\d{2})$/;
			if (!a.test(document.getElementById("oddsshopendtime").value)) {
				alert("日期格式不正确!");
				return false;
			}else{
				$("#oddsshopendtime").val($("#oddsshopendtimes").val());
			}
			
			if (!a.test(document.getElementById("oddsshopbegintime").value)) {
				alert("日期格式不正确!");
				return false;
			}else{
				$("#oddsshopbegintime").val($("#oddsshopbegintimes").val());
			}
			var endtime = document.getElementById("oddsshopendtime").value;
			   var begintime = document.getElementById("oddsshopbegintime").value;
			   var bt=begintime.split("-")[0]+begintime.split("-")[1]+begintime.split("-")[2];
			   var et=endtime.split("-")[0]+endtime.split("-")[1]+endtime.split("-")[2];
			if(et-bt<0){
				alert("结束日期要大于或等于开始日期");
				return false;
			}
			
				$("#shopForm").submit();
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
	