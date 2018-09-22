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
	    <tr class="info">
		    <th>权益类型:</th>
			<td width="150px;"><input type="text"  value="${typeName}" /></td>
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
		<c:forEach items="${listOne}" var="li">
		  <tr class="info">
		     <td>
		          &nbsp;&nbsp;
		         <input type="text"  maxlength="30" style="width:100px"   name="filedNames" value="${li.filedName}" >
		     </td>
		     <td style="text-align:center;"><input type="text"   value="${li.sortNo}" style="width:30px;" name="sortNos"  ></td>
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.formType=='0'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="反显"/> 
		         </c:when>
		          <c:when test="${li.formType=='1'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="日期"/> 
		         </c:when>
		          <c:when test="${li.formType=='2'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="日期区间"/> 
		         </c:when>
		          <c:when test="${li.formType=='3'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="城市"/> 
		         </c:when>
		          <c:when test="${li.formType=='4'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="服务"/> 
		         </c:when>
		          <c:when test="${li.formType=='5'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="次数"/> 
		         </c:when>
		          <c:when test="${li.formType=='6'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="人数"/> 
		         </c:when>
		          <c:when test="${li.formType=='7'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="计数"/> 
		         </c:when>
		          <c:when test="${li.formType=='8'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="提示"/> 
		         </c:when>
		         <c:when test="${li.formType=='9'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="单选"/> 
		         </c:when>
		         <c:when test="${li.formType=='10'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="文本"/> 
		         </c:when>
		       </c:choose>
		     </td>  
		      <td style="text-align:center;">
		       <c:if test="${not empty li.filedAlert}">
		          <input type="text" style="width:250px;"  value="${li.filedAlert}" name="filedAlertInfo"   >
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
		<c:forEach items="${listTwo}" var="li">
		  <tr class="info">
		     <td>
		      &nbsp;&nbsp;
		        <input type="text"  maxlength="30" style="width:100px"  name="filedNames"  value="${li.filedName}"  />
		     </td>
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.filedId=='24'}">
		             <input type="hidden" maxlength="5" value="100000" style="width:30px;" name="sortNos"  > 
		         </c:when>
		          <c:when test="${li.filedId=='28'}">
		             <input type="hidden" maxlength="5" value="100001" style="width:30px;" name="sortNos"  > 
		         </c:when>
		         <c:otherwise>
		              <input type="text" maxlength="5" value="${li.sortNo}" style="width:30px;" name="sortNos"  >
		         </c:otherwise>
		       </c:choose>
		     </td>
		      
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.formType=='10'}">
		            <input type="text" maxlength="5" value="${li.maxLength}" style="width:30px;" name="maxlengths"  > 
		         </c:when>
		          
		       </c:choose>
		     </td>
		     <td style="text-align:center;">
		       <c:choose>
		          <c:when test="${li.formType=='0'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="反显"/> 
		         </c:when>
		          <c:when test="${li.formType=='1'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="日期"/> 
		         </c:when>
		          <c:when test="${li.formType=='2'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="日期区间"/> 
		         </c:when>
		          <c:when test="${li.formType=='3'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="城市"/> 
		         </c:when>
		          <c:when test="${li.formType=='4'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="服务"/> 
		         </c:when>
		          <c:when test="${li.formType=='5'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="次数"/> 
		         </c:when>
		          <c:when test="${li.formType=='6'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="人数"/> 
		         </c:when>
		          <c:when test="${li.formType=='7'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="计数"/> 
		         </c:when>
		          <c:when test="${li.formType=='8'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="提示"/> 
		         </c:when>
		         <c:when test="${li.formType=='9'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="单选"/> 
		         </c:when>
		         <c:when test="${li.formType=='10'}">
		            <input type="text" style="width:50px;text-align:center;"  name="formType" value="文本"/> 
		         </c:when>
		       </c:choose>
		     </td>
		     <td style="text-align:center">
	             <select name="isShows" >
			      <option value="0" <c:if test="${li.isShow=='0'}">selected</c:if>>默认展示</option>
			      <option value="1" <c:if test="${li.isShow=='1'}">selected</c:if>>选是展示</option>
			      <option value="2" <c:if test="${li.isShow=='2'}">selected</c:if>>选否展示</option>
		         </select>
		     </td>  
		     <td style="text-align:center;">
		        <c:if test="${not empty li.filedAlert}">
		          <input type="text" style="width:200px;"  value="${li.filedAlert}" name="filedAlertInfo" />
		       </c:if>
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