	var dg; var count=0;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if(count!=0){
					return;
				}
				if($("#brandId").val()==""){
					alert("请选择品牌");
					$("#brandId").val("");
					$("#brandId").focus();
					return false;
				} 
				if($.trim($("#carModel").val())==""){
					alert("车型不能为空");
					$("#carModel").val("");
					$("#carModel").focus();
					return false;
				} 
				if($.trim($("#downPaymentProportion").val())==""){
					alert("首付比例不能为空");
					$("#downPaymentProportion").val("");
					$("#downPaymentProportion").focus();
					return false;
				} 
				if($.trim($("#installmentMoney").val())==""){
					alert("分期金额不能为空");
					$("#installmentMoney").val("");
					$("#installmentMoney").focus();
					return false;
				} 
				if($.trim($("#installmentNumber").val())==""){
					alert("分期期数不能为空");
					$("#installmentNumber").val("");
					$("#installmentNumber").focus();
					return false;
				} 
				if($.trim($("#clienteleRate").val())==""){
					alert("客户费率不能为空");
					$("#clienteleRate").val("");
					$("#clienteleRate").focus();
					return false;
				} 
				if($("#startTime").val()==""){
					alert("活动开始时间不能为空");
					$("#startTime").val("");
					$("#startTime").focus();
					return false;
				} 
				if($("#endTime").val()==""){
					alert("活动结束时间不能为空");
					$("#endTime").val("");
					$("#endTime").focus();
					return false;
				} 
				if($("#detailsImgUrl").val()!=""){
					 var strs=$("#detailsImgUrl").val().split(".");
				     var fileType=strs[strs.length-1].toLowerCase();
				     var text = [ "jpg", "jpeg", "jpe", "bmp", "png" ];
				     //判断数组中是否包含某个元素
					if ($.inArray(fileType, text) == -1) {
						alert("请上传图片格式为jpg,jpeg,jpe,bmp,png的文件!");
						$("#detailsImgUrl").focus();
						return false;
					}
				}
				
				 count=count+1;
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
/*	function failed(){
		alert("汽车品牌已存在!");
		$("#brandName").select();
		$("#brandName").focus();
	}*/