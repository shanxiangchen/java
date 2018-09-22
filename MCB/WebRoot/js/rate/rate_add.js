/*var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#rateForm").submit();
				return true;
			});
		});*/
		
		

function getBasePath()
		{
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
			return basePath;
		}
		var url = getBasePath()+"/rate/ajax_StagingTpye.html";
	 	$(function(){
			$.ajax({
				url:url,
				type:'post',
				dataType:'json',
				success:function(objs){
						city1 = objs;
// 						获取分期类型的下拉列表
						var province=$("#StagingType");
// 						遍历
						for(var i=0;i<objs.length;i++){
							var op="<option value='"+objs[i].stagCode+"'>"+objs[i].stagName+"</option>";
							province.append(op);
						}
					}
			});
			$("#StagingType").change(function(){
		//获取分期类型下拉列表的id
		var id=$("#StagingType").val();
		//获取状态的下拉列表
		var city=$("#State");
		$("#State option:gt(0)").remove();
		$.post(
		getBasePath()+"/rate/searchState.html?id="+id,
			function(objs){
				for(var i=0;i<objs.length;i++){
					var op="<option value='"+objs[i].stateCode+"'>"+objs[i].stateName+"</option>";
					city.append(op);
				}
			},
			'json'
		);
	});
	$("#State").change(function(){
		//获取状态下拉列表的id
		var id=$("#State").val();
		//获取分期计划的下拉列表
		var city=$("#StagingPlan");
		//清空下拉列表 city.length=1;
		$("#StagingPlan option:gt(0)").remove();
		$.post(
		getBasePath()+"/rate/StagingPlan.html?id="+id,
			function(objs){
				for(var i=0;i<objs.length;i++){
					var op="<option value='"+objs[i].stagingPlanCode+"'>"+objs[i].stagingPlanName+"</option>";
					city.append(op);
				}
			},
			'json'
		);
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
		
		
		function onSelectItem(){
			var sel_obj = document.getElementById("StagingType");
			var index = sel_obj.selectedIndex;
 			var name=sel_obj.options[index].text;
 			var value=sel_obj.options[index].value;
	 		if(value=="005"){
	 			$(".CRselectValue").attr('disabled',"true");//添加disabled属性 
			}
			else
			{
				$(".CRselectValue").removeAttr("disabled"); //移除disabled属性 
			}
 		$("#stagCode").val(value);
		$("#stagName").val(name);
		}
		
		function onSelectItemTwo()
		{
			var sel_obj = document.getElementById("State");
			var index = sel_obj.selectedIndex;
			var value=sel_obj.options[index].value;
 			var name=sel_obj.options[index].text;
		 	$("#stateCode").val(value);
		 	$("#stateNmae").val(name);
		}		
		
		function onSelectItemThree()
		{
			var sel_obj = document.getElementById("StagingPlan");
			var index = sel_obj.selectedIndex;
			var value=sel_obj.options[index].value;
 			var name=sel_obj.options[index].text;
		 	$("#stagingPlanCode").val(value);
		 	$("#stagingPlanName").val(name);
		}
		
		function onSelectPaytype(){
		 	var  myselect=document.getElementById("Paytype");
		 	var index=myselect.selectedIndex ; 
		 	var value=myselect.options[index].value;
		 	var name= myselect.options[index].text;
		 	$("#paytypeCode").val(value);
		 	$("#paytypeName").val(name);
		}
		
		function onSelectcurrency(){
			var currencySelect=document.getElementById("currency");
			var index=currencySelect.selectedIndex ; 
		 	var value=currencySelect.options[index].value;
		 	var name= currencySelect.options[index].text;
		 	$("#currencyCode").val(value);
		 	$("#currencyName").val(name);
		}
		
		var dg;
 		$(document).ready(function(){
 			dg = frameElement.lhgDG;
 			dg.addBtn('ok','保存',function(){
 				if($("#stagCode").val()==""){
					alert("带有*号的必填!");
					$("#stagCode").focus();
					return false;
				}
 				if($("#stagName").val()==""){
					alert("带有*号的必填!");
					$("#stagName").focus();
					return false;
				}
 				if($("#paytypeCode").val()==""){
					alert("带有*号的必填!");
					$("#paytypeCode").focus();
					return false;
				}
 				if($("#paytypeName").val()==""){
					alert("带有*号的必填!");
					$("#paytypeName").focus();
					return false;
				}
 				if($("#ratePeriod").val()==""){
					alert("带有*号的必填!");
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
 				
 				if($("#stateCode").val()==""){
					alert("带有*号的必填!");
					$("#stateCode").focus();
					return false;
				}
 				if($("#stateNmae").val()==""){
					alert("带有*号的必填!");
					$("#stateNmae").focus();
					return false;
				}
 				if($("#stagingPlanCode").val()==""){
					alert("带有*号的必填!");
					$("#stagingPlanCode").focus();
					return false;
				}
 				if($("#stagingPlanName").val()==""){
					alert("带有*号的必填!");
					$("#stagingPlanName").focus();
					return false;
				}
 				if($("#rates").val()==""){
					alert("带有*号的必填!");
					$("#rates").focus();
					return false;
				}
 				
 			var regs=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
 				 if(!regs.test($("#rates").val())){
 			    	alert("费率只能输入整数和小数！");
 			    	$("#rates").val("");
 			    	$("#rates").focus();
 			    	return false;
 			    } 
 				$("#arteFrom").submit();
 				return true;
 			});
 		});