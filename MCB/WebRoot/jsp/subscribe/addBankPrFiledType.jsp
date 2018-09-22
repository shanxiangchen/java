<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴业银行APP信用卡管理系统</title>
<style type="text/css">
body{background-color:#FAFBFD;left:0px;top:0px;right:0px;bottom:0px;margin:0px;font-family: Microsoft Yahei,Arial, sans-serif;font-size: 12px;margin-left:15px;margin-top:5px;}
a{text-decoration:none;}
.main_table{border: 1px solid #CCCCCC;margin-top:10px;background-color: #F2F2F2;table-layout: fixed;}
.main_head{height:27px;background: url("../images/news-title-bg.gif") repeat-x;}
.main_info{height:27px;text-align: center;color:#666666;}
.main_table_even{background-color: #ffffff;}

.dialog_iframe{width:100%;height:100%;}
.shadow{position:absolute;display:none;top:0px;left:0px;z-index:8;width:100%;height:100%;opacity: .7;filter: progid:DXImageTransform.Microsoft.Alpha(opacity=70);background: url('../images/shadow.png') fixed repeat;}
.main_table  th {
	text-overflow: ellipsis;
	/* for IE */
	-moz-text-overflow: ellipsis;
	/* for Firefox,mozilla */
	overflow: hidden;
	white-space: nowrap;
	border:0px;
	border-style:none;
	text-align: center
}
.search_div{width:100%; height:30px;line-height:30px;text-align: left;margin-top:10px;}
.search_div input{vertical-align: middle;}

a.myBtn,a.myBtn em{background-image:url(../images/lhgdg_bg.png);background-repeat:no-repeat;vertical-align: middle;}
a.myBtn,a.myBtn em{display:inline-block;height:21px;cursor: pointer;}
a.myBtn{text-decoration:none;background-position:-32px -41px;padding-left:15px;color:#000;cursor:default;margin-right:5px;}
a.myBtn:hover{background-position:-32px -83px;}
a.myBtn em{font-style:normal;background-position:right -62px;padding-right:15px;line-height:21px;line-height:24px\0;font-size:12px;}
a.myBtn:hover em{background-position:right -104px;}

.btn{width:60px;height:25px;cursor: pointer;border-width:0px;background-image: url(../images/btn-bg2.gif);letter-spacing: 3px;}
.page_and_btn {width:100%;height:auto;overflow:hidden;padding:10px 0px 0px 0px;}
.page_and_btn ul { margin:0px;list-style:none;float:right;width:auto;height:100%;overflow:hidden;padding:0px;}
.page_and_btn ul li { float:left; border:1px solid #CCCCCC; height:20px; line-height:20px; margin:0px 2px;}
.page_and_btn ul li a{color:#333; text-decoration:none;}
.page_and_btn ul li a, .pageinfo { display:block; padding:0px 6px; background:#FAFBFD;}
.page_and_btn  { color:#555;}
.current { background:#ccced4; display:block; padding:0px 6px; font-weight:bold;}
.page_and_btn div{float:left;}

.input_disabled{border: 1px solid #7F9DB9;background-color: #e8e8e8;}
 
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:20px;line-height:20px;}
.info th{text-align:right; color: #4f4f4f; padding-right:5px;font-size: 13px;}
.info td{text-align:left;}  
</style>
</head>
<body>
	<form  action="saveFiledType.html" enctype="multipart/form-data" name="addFiledTypeForm" id="addFiledTypeForm" target="result"  method="post">
	 <table border="0" cellpadding="0" cellspacing="0">
	 
		
		  <tr>
 	       <td align="right">权益类型:<br><br></td>
 	       <td align="left">
 	       		<select id="typeId"  style="width: 255px;" name="typeId">
				  <option value="请选择">请选择</option>
				  <c:forEach items="${typeList}"  var="li">
					<option value="${li.VALUE}">${li.CONTENT}</option>
				  </c:forEach>
				</select> 
				<label style="color: red;" class="red">*</label>
				<br><br>
 	       </td> 	    
 	    </tr>
		
		
	 </table>
	 <div><span style="color:#4f4f4f;font-size:13px;padding-right:5px;font-weight:bold;" id="cc">基本信息:</span><img id="aa" src="../images/accordion_expand.png"></img></div>
	 <div id="bb" style="padding-left:55px;" >
	 <table border="0" cellpadding="0" cellspacing="0"> 	
		<tr class="info">
		    <td width="150px" style="color:#4f4f4f; text-align:center;">展示名称</td>
		    <td width="100px" style="color:#4f4f4f; text-align:center;">展示顺序</td>
		    <td width="100px" style="color:#4f4f4f; text-align:center;">表单类型</td>
		    <td width="100px" style="color:#4f4f4f; text-align:center;">提示内容</td>
		</tr> 
		<c:forEach items="${baseList}" var="li">
		  <tr class="info">
		     <td>
		         <input name="test" type="checkbox"/>&nbsp;&nbsp;
		         <input type="hidden" name="filedIds" value="${li.FILEDID}"/>
		         <input type="hidden" name="isShows" value="0"/>
		         <input type="hidden" name="filedAbcNames" value="${li.FILEDABCNAME}"/>
		         <input type="hidden" maxlength="5" value="0" style="width:30px;" name="maxlengths"  > 
		         <input type="text"  maxlength="10" style="width:100px"   name="filedNames" value="${li.FILEDNAME}"  >
		     </td>
		     <td style="text-align:center;"><input type="text" maxlength="5" style="width:30px;" name="sortNos"  ></td>
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.FILEDFORMTYPE=='0'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="反显"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='1'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="日期"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='2'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="日期区间"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='3'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="城市"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='4'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="服务"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='5'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="次数"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='6'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="人数"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='7'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="计数"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='8'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="提示"/> 
		         </c:when>
		         <c:when test="${li.FILEDFORMTYPE=='9'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="单选"/> 
		         </c:when>
		         <c:when test="${li.FILEDFORMTYPE=='10'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="文本"/> 
		         </c:when>
		       </c:choose>
		     </td>
		     <td style="text-align:center;">
		       <c:if test="${li.FILEDABCNAME=='FILED_ALERT_INFO'}">
		          <input type="text" style="width:250px;" maxlength="250" name="filedAlertInfo"   >
		       </c:if>
		     </td>
		   </tr> 
		</c:forEach>
	</table> </div>
	<div><span style="color:#4f4f4f;font-size:13px;padding-right:5px;font-weight:bold;" id="dd">更多信息:</span><img id="ee" src="../images/accordion_expand.png"></img></div>
	 <div id="ff" style="display:none; padding-left:55px;" >
	  <table border="0" cellpadding="0" cellspacing="0"> 
		<tr class="info">
		    <td width="150px" style="color:#4f4f4f;text-align:center;">展示名称</td>
		    <td width="100px" style="color:#4f4f4f;text-align:center;">展示顺序</td>
		    <td width="100px" style="color:#4f4f4f; text-align:center;">最大长度</td>
		    <td width="100px" style="color:#4f4f4f;text-align:center;">表单类型</td>
		    <td width="100px" style="color:#4f4f4f;text-align:center;">是否展示</td>
		    <td width="100px" style="color:#4f4f4f;text-align:center;">提示内容</td>
		</tr> 
		<c:forEach items="${moreList}" var="li">
		  <tr class="info">
		     <td>
		        <c:choose>
		            <c:when test="${li.FILEDABCNAME=='YES_AND_NO'}">
		              <input id="yesAndNo" type="checkbox"/>&nbsp;&nbsp;
		            </c:when>
		            <c:otherwise>
		               <input type="checkbox"/>&nbsp;&nbsp;
		            </c:otherwise>
		        </c:choose>
		        <input type="hidden" name="filedIds" value="${li.FILEDID}"/>
		        <input type="hidden" name="filedAbcNames" value="${li.FILEDABCNAME}"/>
		        <input type="text"  maxlength="30" style="width:100px"  name="filedNames"  value="${li.FILEDNAME}"  />
		     </td>
		     <td style="text-align:center;">
		         <c:choose>
		          <c:when test="${li.FILEDABCNAME=='TOGETHER_NUM'}">
		             <input type="hidden" maxlength="5" value="100000" style="width:30px;" name="sortNos"  > 
		         </c:when>
		          <c:when test="${li.FILEDABCNAME=='SERVICE_ALERT_INFO'}">
		             <input type="hidden" maxlength="5" value="100001" style="width:30px;" name="sortNos"  > 
		         </c:when>
		         <c:otherwise>
		              <input type="text" maxlength="5" style="width:30px;" name="sortNos"  >
		         </c:otherwise>
		       </c:choose>
		    
		     </td>
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.FILEDFORMTYPE=='10'}">
		            <input type="text" maxlength="5" style="width:30px;" name="maxlengths"  > 
		         </c:when>
		         <c:otherwise>
		             <input type="hidden" maxlength="5" value="0" style="width:30px;" name="maxlengths"  > 
		         </c:otherwise>
		       </c:choose>
		     </td>
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.FILEDFORMTYPE=='0'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="反显"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='1'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="日期"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='2'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="日期区间"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='3'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="城市"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='4'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="服务"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='5'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="次数"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='6'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="人数"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='7'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="计数"/> 
		         </c:when>
		          <c:when test="${li.FILEDFORMTYPE=='8'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="提示"/> 
		         </c:when>
		         <c:when test="${li.FILEDFORMTYPE=='9'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="单选"/> 
		         </c:when>
		         <c:when test="${li.FILEDFORMTYPE=='10'}">
		            <input type="text" style="width:50px;text-align:center;"  name="filedFormType" value="文本"/> 
		         </c:when>
		       </c:choose>
		     </td>
		     <td style="text-align:center">
		       <c:choose>
		          <c:when test="${li.FILEDABCNAME=='YES_AND_NO'}">
		            <select name="isShows" >
				       <option value="0">默认展示</option>
			        </select>
		         </c:when>
		        <%--  <c:when test="${li.FILEDABCNAME=='ALERT_INFO_ONE'}">
		            <select name="isShows" >
				      <option value="0">默认展示</option>
				      <option value="1">选是展示</option>
				      <option value="2">选否展示</option>
			        </select>
		         </c:when>
		          <c:when test="${li.FILEDABCNAME=='ALERT_INFO_TWO'}">
		             <select name="isShows" >
				      <option value="0">默认展示</option>
				      <option value="1">选是展示</option>
				      <option value="2">选否展示</option>
			        </select>
		         </c:when>
		          <c:when test="${li.FILEDABCNAME=='ALERT_INFO_THREE'}">
		             <select name="isShows" >
				      <option value="0">默认展示</option>
				      <option value="1">选是展示</option>
				      <option value="2">选否展示</option>
			        </select>
		         </c:when>
		         <c:when test="${li.FILEDABCNAME=='SERVICE_ALERT_INFO'}">
		            <select name="isShows" >
				      <option value="0">默认展示</option>
				      <option value="1">选是展示</option>
				      <option value="2">选否展示</option>
			        </select>
		         </c:when> --%>
		         <c:otherwise>
		            <select name="isShows" >
				      <option value="0">默认展示</option>
				      <option value="1">选是展示</option>
				      <option value="2">选否展示</option>
			        </select>
		         </c:otherwise>
		       </c:choose>
		     </td>
		     <td style="text-align:center;">
		       <c:choose>
		         <c:when test="${li.FILEDABCNAME=='ALERT_INFO_ONE'}">
		            <input type="text"  maxlength="250" style="width:250px;" name="alertInfoOne"   >
		         </c:when>
		          <c:when test="${li.FILEDABCNAME=='ALERT_INFO_TWO'}">
		            <input type="text"  maxlength="250" style="width:250px;" name="alertInfoTwo"    >
		         </c:when>
		          <c:when test="${li.FILEDABCNAME=='ALERT_INFO_THREE'}">
		            <input type="text"  maxlength="250" style="width:250px;" name="alertInfoThree"   >
		         </c:when>
		         <c:when test="${li.FILEDABCNAME=='SERVICE_ALERT_INFO'}">
		            <input type="text"  maxlength="250" style="width:250px;" name="serviceAlertInfo"   >
		         </c:when>
		       </c:choose>
		     </td>
		   </tr> 
		</c:forEach>
		</table></div>
	  </form>
	 </body>			
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jsAddress.js"></script>
	<script type="text/javascript" src="../js/ajaxform.js"></script>
<script type="text/javascript">
  
       $("input[type='text']").attr("disabled",true);
       $("input[type='hidden']").attr("disabled",true);
       $("select[name='isShows']").attr("disabled",true);
	   $("input[type='checkbox']").click(function(){
	          if($(this).attr("checked")){
	             $(this).parent().parent("tr").children("td").children(":not(input[name='filedFormType'])").removeAttr("disabled");
	             if($("#yesAndNo").attr("checked")){
	                 $("input[type='checkbox']").each(function(){
	                      if($(this).attr("checked")){
	                          $(this).parent().parent("tr").children("td").children("select[name='isShows']").removeAttr("disabled");
	                      }
	                 });
	             }else{
	                 $(this).parent().parent("tr").children("td").children("select[name='isShows']").attr("disabled",true);
	             }
	             
	          }else{
	             $(this).parent().parent("tr").children("td").children(":not(input[type='checkbox'])").attr("disabled",true);
	             if($("#yesAndNo").attr("checked")){
	                 $("input[type='checkbox']").each(function(){
	                      if($(this).attr("checked")){
	                          $(this).parent().parent("tr").children("td").children("select[name='isShows']").removeAttr("disabled");
	                      }
	                 });
	             }else{
	                 $(this).parent().parent("tr").children("td").children("select[name='isShows']").attr("disabled",true);
	             }
	          }    
	    
	    });
	    $("#yesAndNo").click(function(){
	         if(!$("#yesAndNo").attr("checked")){
	            $("select[name='isShows']").attr("disabled",true);
	         }
	    }); 
/////////////////////////////////////////////互斥校验/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    $("input[name='filedAbcNames'][value='MY_OF_TIMES']").prevAll("input[type='checkbox']").click(function(){
	           if($(this).attr("checked")){
	        	  $("input[name='filedAbcNames'][value='MY_OF_TIMES']").removeAttr("disabled");
	              $("input[name='filedAbcNames'][value='PARKING_DAYS']").siblings().attr("disabled",true);
	              $("input[name='filedAbcNames'][value='PARKING_DAYS']").attr("disabled",true);
	              $("input[name='filedAbcNames'][value='PARKING_DAYS']").
	              parent().parent("tr").children("td").children("input[type='text']").attr("disabled",true);
	              $("input[name='filedAbcNames'][value='PARKING_DAYS']").prevAll("input[type='checkbox']").attr("checked",false);
	           }else{
	               $("input[name='filedAbcNames'][value='PARKING_DAYS']").prevAll("input[type='checkbox']").removeAttr("disabled");
	               $("input[name='filedAbcNames'][value='MY_OF_TIMES']").attr("disabled",true);
	                
	           }
	    
	    });
	    
	    $("input[name='filedAbcNames'][value='DATE_OF_EQUITY']").prevAll("input[type='checkbox']").click(function(){
	          if($(this).attr("checked")){
	        	  $("input[name='filedAbcNames'][value='DATE_OF_EQUITY']").removeAttr("disabled");
	        	  $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").attr("disabled",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").siblings().attr("disabled",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").
	              parent().parent("tr").children("td").children("input[type='text']").attr("disabled",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").attr("disabled",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").siblings().attr("disabled",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").
	              parent().parent("tr").children("td").children("input[type='text']").attr("disabled",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").prevAll("input[type='checkbox']").attr("checked",false);
	              $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").prevAll("input[type='checkbox']").attr("checked",false);
	          }else{
	              $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").prevAll("input[type='checkbox']").removeAttr("disabled");
	              $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").prevAll("input[type='checkbox']").removeAttr("disabled");
	              $("input[name='filedAbcNames'][value='DATE_OF_EQUITY']").attr("disabled",true);
	          }
	    
	    });
	    
	    
	    $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").prevAll("input[type='checkbox']").click(function(){
	          if($(this).attr("checked")){
	             $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").prevAll("input[type='checkbox']").attr("checked",true);
	              $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").
	              prevAll("input[type='checkbox']").parent().parent("tr").children("td").children(":not(input[name='filedFormType'])").removeAttr("disabled");
	          }else{
	             $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").prevAll("input[type='checkbox']").attr("checked",false);
	             $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").
	             prevAll("input[type='checkbox']").parent().parent("tr").children("td").children(":not(input[type='checkbox'])").attr("disabled",true);
	             $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").attr("disabled",true);
	          }
	    
	    });
	    
	    $("input[name='filedAbcNames'][value='DATE_OF_APPEARANCE']").prevAll("input[type='checkbox']").click(function(){
	          if($(this).attr("checked")){
	             $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").prevAll("input[type='checkbox']").attr("checked",true);
	             $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").
	             prevAll("input[type='checkbox']").parent().parent("tr").children("td").children(":not(input[name='filedFormType'])").removeAttr("disabled");
	          }else{
	        	 $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").attr("disabled",true); 
	             $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").prevAll("input[type='checkbox']").attr("checked",false);
	             $("input[name='filedAbcNames'][value='DATE_OF_ENTRY']").
	             prevAll("input[type='checkbox']").parent().parent("tr").children("td").children(":not(input[type='checkbox'])").attr("disabled",true);
	              
	          }
	    
	    });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        var dg; count=0;
        dg = frameElement.lhgDG; 
		dg.addBtn('ok','保存',function(){
		     if(!validate()){
		       return;
		     }
			 if(count!=0){
		       return;
		     }
		     count=count+1;
             $("#addFiledTypeForm").ajaxSubmit({
					type : "POST",
					url : "saveFiledType.html",
					dataType : "text",
					success : function(text) {
						if(text=="success"){
						  success();
						}else if(text=="1"){
						  alert("该权益类型已经存在！");
						  count=0;
						}else{
						  alert("保存失败");
						  success();
						}
						  
						 
					},
					error : function() {
						alert("保存失败！");
						success();
					}
			 });
			 
		});
		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
       function validate(){
           if($("#typeId").val()==""){
              alert("请选择权益类型");
              $("#typeId").focus();
              return false;
           }
           if(!valiCheckBox()){
              return false;
           }
           if(!valiSortNo()){
              return false;
           }
           
           if(!valiSortNoTwo()){
              return false;
           }
           
           if(!valiFiledName()){
             return false;
           }
           if(!valiAlertInfo()){
             return false;
           }
           
           if(!validateLength()){
        	   return false;
           }
           return true;
       }
       
       function valiSortNo(){
          var num=0; var count=0;
          var reg=/^[0-9]*$/;
          $("input[type='checkbox']").each(function(){
                 if($(this).attr("checked")){
                     var sortNo=$(this).parent().parent("tr").children("td").children("input[name='sortNos']").val();
                     if(sortNo){
                        if(!reg.test(sortNo)){
                           $(this).parent().parent("tr").children("td").children("input[name='sortNos']").val("");
                           count++;
                        }
                     }else{
                        $(this).parent().parent("tr").children("td").children("input[name='sortNos']").focus();
                        num++;
                     }
                 }
	       });
	      if(count>0){
	         alert("展示顺序只能输入数字");
	          return false;
	      }
	      if(num>0){
	         alert("请输入展示顺序");
	          return false;
	      }
          return true;
       }
       
       function valiSortNoTwo(){
           var count=0; var num;
           var myArray=new Array();
           $("input[type='checkbox']").each(function(){
               if($(this).attr("checked")){
                   var sortNo=$(this).parent().parent("tr").children("td").children("input[name='sortNos']").val();
                   if(sortNo){
                      myArray.push(sortNo);
                   }
               }
           
           });
		  if(myArray.length>0){
		      var nary=myArray.sort();
			  for(var i=0;i<nary.length;i++){
			    if(nary[i]==myArray[i+1]){
			      num=nary[i];
			      count++;
			   }
			  }
		  } 
		  
		  if(count>0){
		    alert("展示顺序号有重复:"+num);
		    return false;
		  }
           
          return true;
       }
       
       function valiCheckBox(){
           var count=0;
           $("input[type='checkbox']").each(function(){
                 if($(this).attr("checked")){
                      count++;
                 }
	       });
	       
	       if(count>0){
	         return true;
	       }else{
	          alert("请选择权益类型展示名称");
	          return false;
	       }
	      
       }
       
       function valiFiledName(){
           var num=0; count=0;
           $("input[type='checkbox']").each(function(){
                  if($(this).attr("checked")){
                      var filedName=$(this).parent().parent("tr").children("td").children("input[name='filedNames']").val();
                      if($.trim(filedName)){
                         if(filedName.indexOf(",")!=-1||filedName.indexOf("，")!=-1){
                            num++;
                         }
                      }else{
                        count++;
                        $(this).parent().parent("tr").children("td").children("input[name='filedNames']").focus();
                      }
                  }
	       });
           if(num>0){
              alert("展示名称不能包含:,");
              return false;
           }
           if(count>0){
              alert("展示名称不能为空");
              return false;
           }
           return true;
       
       }
       
       function validateLength(){
    	   var reg=/^[0-9]*$/;
    	   var init=0; var leCount=0; var nullCount=0;
    	   $("input[type='checkbox']").each(function(){
               if($(this).attr("checked")){
                   var maxlength=$(this).parent().parent("tr").children("td").children("input[name='maxlengths']").val();
                   if($.trim(maxlength)==""){
        			   nullCount++;
        			  
        		   }else if(!reg.test(maxlength)){
        			   leCount++;
        			   
        		   }else{
        			   init+=parseInt(maxlength);
        		   }
               }
	       });
    	    
    	   if(nullCount>0){
    		   alert("最大长度不能为空");
    		   return false;
    	   }
    	   if(leCount>0){
    		   alert("最大长度只能输入数字");
    		   return false;
    	   }
    	 
    	   if(init>100){
    		   alert("文本总长度不能大于100");
    		   return false;
    	   }
    	    
    	   return true;
       }
       
       function valiAlertInfo(){
            
            var num=0; count=0;
           $("input[type='checkbox']").each(function(){
                  if($(this).attr("checked")){
                      var alertInfo=$(this).parent().parent("tr").children("td").children("input[name='filedAlertInfo']").val();
                      var alertOne=$(this).parent().parent("tr").children("td").children("input[name='alertInfoOne']").val();
                      var alertTwo=$(this).parent().parent("tr").children("td").children("input[name='alertInfoTwo']").val();
                      var alertThree=$(this).parent().parent("tr").children("td").children("input[name='alertInfoThree']").val();
                      var serviceAlert=$(this).parent().parent("tr").children("td").children("input[name='serviceAlertInfo']").val();
                      if(alertInfo!=undefined){
                          if(!$.trim(alertInfo)){
                            count++;
                         } 
                      }
                      
                      if(alertOne!=undefined){
                           if(!$.trim(alertOne)){
                            count++;
                         } 
                      }
                      
                      if(alertTwo!=undefined){
                         if(!$.trim(alertTwo)){
                            count++;
                         } 
                      }
                      
                      if(alertThree!=undefined){
                         if(!$.trim(alertThree)){
                            count++;
                         } 
                      }
                      
                      if(serviceAlert!=undefined){
                         if(!$.trim(serviceAlert)){
                            count++;
                         } 
                      }
                      
                  }
	       });
	       
	       if(count>0){
	         alert("请输入提示信息");
	         return false;
	       }
	       
           return true;
       }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       $("#aa").click(function(){
          var flag=$("#bb").is(":hidden");
          if(flag){
              $("#bb").show();
	          $("#ff").hide();
	          $("#aa").attr("src","../images/accordion_collapse.png");
	          $("#ee").attr("src","../images/accordion_expand.png");
          }else{
              $("#bb").hide();
              $("#ff").show();
              $("#aa").attr("src","../images/accordion_expand.png");
              $("#ee").attr("src","../images/accordion_collapse.png");
              
          }
       
       });
       
       $("#ee").click(function(){
          var flag=$("#bb").is(":hidden");
          if(flag){
              $("#bb").show();
	          $("#ff").hide();
	          $("#aa").attr("src","../images/accordion_collapse.png");
	          $("#ee").attr("src","../images/accordion_expand.png");
          }else{
              $("#bb").hide();
              $("#ff").show();
              $("#aa").attr("src","../images/accordion_expand.png");
              $("#ee").attr("src","../images/accordion_collapse.png");
              
          }
       
       });
    
    /*  $("#aa").toggle(
        function(){
          $("#bb").hide();
          $("#ff").show();
          $("#aa").attr("src","../images/accordion_expand.png");
          $("#ee").attr("src","../images/accordion_collapse.png");
          
        },
        function(){
          $("#bb").show();
          $("#ff").hide();
          $("#aa").attr("src","../images/accordion_collapse.png");
          $("#ee").attr("src","../images/accordion_expand.png");
        }
       
      );
      
      $("#ee").toggle(
        function(){
          $("#ff").show();
          $("#bb").hide();
          $("#ee").attr("src","../images/accordion_collapse.png");
          $("#aa").attr("src","../images/accordion_expand.png");
        },
        function(){
          $("#ff").hide();
          $("#bb").show();
          $("#ee").attr("src","../images/accordion_expand.png");
          $("#aa").attr("src","../images/accordion_collapse.png");
        }
       
      ); */
       
	function success(){
		if(dg.curWin.document.forms[0]){
			dg.curWin.document.forms[0].action = dg.curWin.location+"";
			dg.curWin.document.forms[0].submit();
		}else{
			dg.curWin.location.reload();
		}
		dg.cancel();
	}
 
</script>

</html>