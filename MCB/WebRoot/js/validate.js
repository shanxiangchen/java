/*
 * @param  id  根据不同需求调用不同验证方法
 * 1=版本号格式
 * 2=手机号码
 * 3=整数
 * 4=中文
 * */
function verificationText(id,objectId,stars){
if(id==1){
		if(objectId.value.match(/^[0-9]\.[0-9]\.[0-9]$/)){
			document.getElementById(stars).style.color="#FF0011";
			objectId.style.border="1px solid #ccc";
		}else{
			document.getElementById(stars).style.color="red";
			alert("只能输入正确版本格式!");
			objectId.style.border="2px solid red";
			objectId.value="";
		
		}
	}

if(id==2){
		if(objectId.value.match(/^[1][358][0-9]{9}$/)){
		document.getElementById(stars).style.color="#FF0011";
			objectId.style.border="1px solid #ccc";
		}else{
			document.getElementById(stars).style.color="red";
			alert("请输入正确的手机号码!");
			objectId.value="";
			objectId.style.border="2px solid red";
		}
	}
	if(id==3){
  		if(objectId.value.match(/^[0-9]+$/)){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("请输入整数!");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	if(id==4){
  		if(objectId.value.match(/[\u4e00-\u9fa5]$/)){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("只能输入中文!");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	if(id==5){
  		if(objectId.value.match(/^[0-9]+([.]{1}[0-9]+){0,1}$/)){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("只能输入整数和小数");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	if(id==6){
  		if(objectId.value.match(/^([a-zA-Z\u4e00-\u9fa5]){1,4}$/)!=null){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("只能输入四个汉字或字母");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	if(id==6){
  		if(objectId.value.match(/^([a-zA-Z\u4e00-\u9fa5]){1,4}$/)!=null){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("只能输入四个汉字或字母");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	
	if(id==7){
  		if(objectId.value.match(/^[1-9]{1}$/)){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("只能输入1-9的整数");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	
	if(id==8){
  		if(objectId.value.match(/(.*)(.jpg|.bmp|.png)$/)){
  			document.getElementById(stars).style.color="#FF0011";
  			objectId.style.border="1px solid #ccc";
  		}else{
  			document.getElementById(stars).style.color="red";
  			alert("只能是jpg和bmp和png格式");
  			objectId.style.border="2px solid red";
  			objectId.value="";
  		}
  	}
	
	
};

/**
 * 校验图片名称
 * 不能包含汉字，格式不能非图片格式，长度不能超长
 * @param obj
 * @returns {Boolean}
 */
function checkImgName(obj){ 
	if(/.*[\u4e00-\u9fa5]+.*$/.test(obj)) 
	{ 
		alert("图片名称不能含有汉字！"); 
		return false; 
	} 
	var photoExt=obj.substr(obj.lastIndexOf(".")).toLowerCase();//获得文件后缀名
    if(photoExt!=""&& photoExt!='.jpg'&&photoExt!='.jpeg'&&photoExt!='.png'&&photoExt!='.gif'&&photoExt!='.bmp'){
        alert("图片类型必须是.gif,jpeg,jpg,bmp,png中的一种!");
        return false;
    }
    var filename = "";
    if(obj.lastIndexOf("\\")>0){
    	filename=obj.substr(obj.lastIndexOf("\\")+1).toLowerCase();//获得文件名
    }else if(obj.lastIndexOf("/")>0){
    	filename=obj.substr(obj.lastIndexOf("/")+1).toLowerCase();//获得文件名
    }
    
	if(filename.length>30){
		alert("图片名称长度过长，请修改后重新上传！");
		return false;
	}
	return true; 
} 