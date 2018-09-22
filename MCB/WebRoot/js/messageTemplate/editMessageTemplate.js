////加载绝对路径
//function getBasePath()
//		{
//			var obj = window.location;
//			var contextPath = obj.pathname.split("/")[1];
//			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
//			return basePath;
//		}
//加载判断是否是disabled属性
	$(document).ready(function(){ 
				var sel_obj = document.getElementById("LinkWay");
				var index = sel_obj.selectedIndex;
 				var value=sel_obj.options[index].value;
				var sel_objs = document.getElementById("inGoAndSeeLink");
				var index = sel_objs.selectedIndex;
 				var values=sel_objs.options[index].value;
		 		 if(value=="in"){
		 			$("#inGoAndSeeLink").removeAttr("disabled"); //移除disabled属性 
	 				$("#inLink").val(values);
		 			$("#outGoAndSeeLink").val("").attr('disabled',"true");//添加disabled属性 
		 			$("#outLink").val("");
				}
				 if(value=="out"){
					$("#outGoAndSeeLink").removeAttr("disabled"); //移除disabled属性 
					var outlink=$("#outGoAndSeeLink").val();
				$("#outLink").val(outlink);	
					$("#inGoAndSeeLink").val("").attr('disabled',"true");//添加disabled属性 
					$("#inLink").val("");
				}
				if(value==""){
					$("#inGoAndSeeLink").attr('disabled',"true");//添加disabled属性 
					$("#outGoAndSeeLink").attr('disabled',"true");//添加disabled属性 
				}	
		});
//改变事件方法
	function link(){
				var sel_obj = document.getElementById("LinkWay");
				var index = sel_obj.selectedIndex;
 				var value=sel_obj.options[index].value;
		 		 if(value=="in"){
		 			$("#inGoAndSeeLink").removeAttr("disabled"); //移除disabled属性 
		 			$("#outGoAndSeeLink").val("").attr('disabled',"true");//添加disabled属性 
		 			$("#outLink").val("");
				}
				 if(value=="out"){
					$("#outGoAndSeeLink").removeAttr("disabled"); //移除disabled属性 
					$("#inGoAndSeeLink").val("").attr('disabled',"true");//添加disabled属性 
					$("#inLink").val("");
				}
				if(value==""){
					$("#inGoAndSeeLink").attr('disabled',"true");//添加disabled属性 
					$("#outGoAndSeeLink").attr('disabled',"true");//添加disabled属性 
				}
			}
			
		function inlink(){
				var sel_obj = document.getElementById("inGoAndSeeLink");
					var index = sel_obj.selectedIndex;
	 				var value=sel_obj.options[index].value;
	 				$("#inLink").val(value);
			}
		function out(){
				var outlink=$("#outGoAndSeeLink").val();
				$("#outLink").val(outlink);	
			}
//		function onSelectItemTwo(){
//					var sel_obj = document.getElementById("smallTypeCode");
//					var index = sel_obj.selectedIndex;
//	 				var value=sel_obj.options[index].value;
//	 				$("#code").val(value);
//			}
	//加载		
//		$(function(){
//				$("#typeNo").ready(function(){
//					var typeNo=$("#typeNo").val();
//					$("#smallTypeCode option:gt(0)").remove();
//					var smallTypeCode=$("#smallTypeCode");
//					$.post(
//					getBasePath()+"/bankMessageTemplate/searchBankMessageSmallType.html?id="+typeNo,
//						function(objs){
//							for(var i=0;i<objs.length;i++){
//								if($("#smallTypeId").val() == objs[i].smallTypeCode){
//									op = "<option selected='true' value='"+objs[i].smallTypeCode+"'>"+objs[i].smallTypeName+"</option>";
//								}else{
//									op = "<option value='"+objs[i].smallTypeCode+"'>"+objs[i].smallTypeName+"</option>";
//								}
//								smallTypeCode.append(op);
//							}
//						},
//						'json'
//					);
//				});
//		});	
		//改变事件
//		function onSelectItem(){
//			var typeNo=$("#typeNo").val();
//			$("#smallTypeCode option:gt(0)").remove();
//			var smallTypeCode=$("#smallTypeCode");
//			$.post(
//			getBasePath()+"/bankMessageTemplate/searchBankMessageSmallType.html?id="+typeNo,
//				function(objs){
//					for(var i=0;i<objs.length;i++){
//						if($("#smallTypeId").val() == objs[i].smallTypeCode){
//							op = "<option selected='true' value='"+objs[i].smallTypeCode+"'>"+objs[i].smallTypeName+"</option>";
//						}else{
//							op = "<option value='"+objs[i].smallTypeCode+"'>"+objs[i].smallTypeName+"</option>";
//						}
//						smallTypeCode.append(op);
//					}
//				},
//				'json'
//			);
//		}
		//提交方法
		var dg;
			$(document).ready(function(){
				dg = frameElement.lhgDG;
				dg.addBtn('ok','保存',function(){
					if(!checkImgName($("#advertisingImgFile").val())){
						return false;
					}
					$("#MessageTemplateForm").submit();
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