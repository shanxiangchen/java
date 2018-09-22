		function getBasePath()
		{
			var obj = window.location;
			var contextPath = obj.pathname.split("/")[1];
			var basePath = obj.protocol+"//"+obj.host+"/"+contextPath;
			return basePath;
		}
		var url = getBasePath()+"/shop/ajax_searchShops.html";
	 	$(function(){
			$.ajax({
				url:url,
				type:'post',
				dataType:'json',
				success:function(objs){
						city1 = objs;
						//获取城市的下拉列表
						var province=$("#oddsshopcityId");
						//遍历
						for(var i=0;i<objs.length;i++){
							var op="<option value='"+objs[i].cityno+"'>"+objs[i].cityname+"</option>";
							province.append(op);
						}
					}
			});
			$("#oddsshopcityId").change(function(){
				//获取城市下拉列表的id
				var cityno=$("#oddsshopcityId").val();
				//获取状态的下拉列表
				var city=$("#oddsshopringId");
				$("#oddsshopringId option:gt(0)").remove();
				$.post(
				getBasePath()+"/shop/searchCityShop.html?cityno="+cityno,
					function(objs){
						for(var i=0;i<objs.length;i++){
							var op="<option value='"+objs[i].shopRingNo+"'>"+objs[i].shopRingName+"</option>";
							city.append(op);
						}
					},
					'json'
				);
			});
		});
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
			}
			if($("#oddsshopphone").val()==""){
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
			} if($("#oddsshopringId").val()==""){
			     alert("请选择商圈 ");
				 $("#oddsshopringId").focus();
				return false;
			}if($("#oddsshopstatus").val()==""){
			     alert("请选择状态");
				 $("#oddsshopstatus").focus();
				return false;
			} 
			var reg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
			if($("#oddsshoppictureurl").val()==""){
			     alert("请上传商户图片");
				 $("#oddsshoppictureurl").focus();
				return false;
			}else{
				var start=$("#oddsshoppictureurl").val().lastIndexOf("\\");
				var end=$("#oddsshoppictureurl").val().lastIndexOf(".");
				var name=$("#oddsshoppictureurl").val().substring(start+1,end);
			    if(reg.test(name)){
				   alert("图片名称不能包含中文字符!");
				   return false;
				} 
				
			}
			 if($("#oddsshop").val()==""){
			     alert("请上传其它图片");
				 $("#oddsshop").focus();
				return false;
			}else{
				var start=$("#oddsshop").val().lastIndexOf("\\");
				var end=$("#oddsshop").val().lastIndexOf(".");
				var name=$("#oddsshop").val().substring(start+1,end);
			    if(reg.test(name)){
				   alert("图片名称不能包含中文字符!");
				   return false;
				} 
			} if($("#oddsshoptypeid").val()==""){
			     alert("请选择类型");
				 $("#oddsshoptypeid").focus();
				return false;
			}
			var a = /^(\d{4})-(\d{2})-(\d{2})$/;
			if (!a.test(document.getElementById("oddsshopendtime").value)) {
				alert("日期格式不正确!");
				return false;
			}
			if (!a.test(document.getElementById("oddsshopbegintime").value)) {
				alert("日期格式不正确!");
				return false;
			}
			var endtime = document.getElementById("oddsshopendtime").value;
			   var begintime = document.getElementById("oddsshopbegintime").value;
					var nd = new Date(begintime.replace("-", "/"));
					var ld = new Date(endtime.replace("-", "/"));
					if (Date.parse(ld) - Date.parse(nd) < 0) {
						alert("结束日期要大于或等于开始日期");
						return false;
					}
			/*if(!checkbeginandendtime()){
				alert("结束日期要大于或等于开始日期");
				return false;
			}*/
				$("#shopForm").submit();
			});
			
		});
		


		
		
	  /* function checkbeginandendtime() {
		   var endtime = document.getElementById("oddsshopendtime").value;
		   var begintime = document.getElementById("oddsshopbegintime").value;
				var nd = new Date(begintime.replace("-", "/"));
				var ld = new Date(endtime.replace("-", "/"));
				if (Date.parse(ld) - Date.parse(nd) < 0) {
					return false;
				} else {
					return true;
				}
	    }*/
	   
	   function checkTime(startTime, endTime) {
		   var nd = new Date(startTime.replace("-", "/"));
		   var ld = new Date(endTime.replace("-", "/"));
		   if (Date.parse(ld) - Date.parse(nd) < 0) {
			   alert("结束日期要大于或等于开始日期");
			   return false;
		   } else {
			   return true;
		   }
	   }
	   
		function PreviewImage(imgFile) {
			var pattern = /(\.*.jpg	)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
			if (!pattern.test(imgFile.value)) {
				alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
				imgFile.focus();
			} else {
				var path;
				if (document.all)//IE 
				{
					imgFile.select();
					path = document.selection.createRange().text;
					document.getElementById("imgPreview").innerHTML = "";
					document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
							+ path + "\")";//使用滤镜效果 
				} else//FF 
				{
					path = URL.createObjectURL(imgFile.files[0]);
					document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>";
				}
			}
		}


		function clearNoNum(obj) {
				obj.value = obj.value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符
				obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是.
				obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
				obj.value = obj.value.replace(".", "$#$").replace(/\./g, "")
						.replace("$#$", ".");
			
		}


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
		var i=document.getElementById("oddsshoporder").value;
			alert("新增失败！展示顺"+i+"已经添加，不能重复添加");
			$("#oddsshoporder").select();
			$("#oddsshoporder").focus();
		}
		
		
	function onSelectItemTwo(){
		var sel_obj = document.getElementById("oddsshopcityId");
		var index = sel_obj.selectedIndex;
		var value=sel_obj.options[index].value;
	 	$("#oddsshopcity").val(value);
	}
		
	function onSelectItemThree(){
		var sel_obj = document.getElementById("oddsshopringId");
		var index = sel_obj.selectedIndex;
		var value=sel_obj.options[index].value;
	 	$("#oddsshopring").val(value);
	}	
		
		
		
/**
 * 增加一行
 */
 var i=2;
function addNewRow(){
    
	var purdetail=document.getElementById("purdetail");
	var newRow=purdetail.insertRow();
	var newcell =newRow.insertCell();
	$(newcell).append("<img src='../images/tupian.png' width='30' height='30' />");
	var newcell =newRow.insertCell();
	$(newcell).append("<input type='file'  id='oddsshop' name='oddsshopFile' accept='image/*' class='input_txt' maxlength='100'/>");
	var newcell=newRow.insertCell();
	$(newcell).append("<img style='cursor:pointer' title='删除' src='../images/delete.gif' onclick='deleteRow("+0+",this)'/>");
}
/**
 * 删除最后一行
 */
function deleteRow(id, event) {
	$(event.parentElement.parentElement).remove();
}

