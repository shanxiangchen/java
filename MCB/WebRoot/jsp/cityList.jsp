<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.app.entity.CityGps"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请勾选城市</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
</head>
<body> 
	<form action="addMarketActivity.html" name="cityForm" id="cityForm"
		target="result" method="post">
		<div align="center">
			<table id="tableId">
				<% List list=(List)request.getAttribute("listCity");
				   for(int i=0;i<list.size();i++){
				    CityGps city=(CityGps)list.get(i);
				    String cityNo=city.getCityNo();
				    String cityName=city.getCityName();
				    String cityOne=city.getCityOne();
				     if((i+1)%5==1){%>
				       <tr>
				   <% }%>
				     	
				     	   <% List listCityNo=(List)request.getAttribute("listCityNo");
				     	      if(listCityNo!=null&&listCityNo.contains(cityNo)){
					   				for(int j=0;j<listCityNo.size();j++){
					   				  	  String cityNos=listCityNo.get(j).toString();
						   				  if(cityNo.equals(cityNos)){
						   				%>
						   					 <th><input type="checkbox" name="cityId" id="cityId"
													value="<%=cityNo%>" checked="checked"></th>
 	          								 <td ><%=cityOne%>:<%=cityName%><input type="hidden" id="cityId_<%=cityNo%>" value="<%=cityName%>"/></td>
						   					<% 
						   				  }/* else{ */%>
						   				<%-- <td><input type="checkbox" name="cityId" id="cityId"
													value="<%=cityNo%>" ></td>
													 <td ><%=cityName%><input type="hidden" id="cityId_<%=cityNo%>" value="<%=cityName%>"/></td> --%>
						   			    <%-- <% break;
						   			     }%> --%>
						   			   
						   			<%}
				   			  }else{%>
				   			      <td><input type="checkbox" name="cityId" id="cityId" value="<%=cityNo%>"></td>
				   			      <td><%=cityOne%>__:<%=cityName%><input type="hidden" id="cityId_<%=cityNo%>" value="<%=cityName%>"/></td>
				   			 <%}%>
				   	 <%if((i+1)%5==0){%>
				       </tr>
				   <% }%>		 
				<%}%>
			</table>
		</div>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
	<script src="../js/jquery-1.8.3.js" type="text/javascript"></script>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
	    var dgs;
		$(document).ready(function(){
			dgs = frameElement.lhgDG;
			dgs.addBtn('ok','确认',function(){ 
				getSelectCity();
				dgs.cancel();
			});
		});  
			
		//获取勾选的城市
		function getSelectCity(){
		   var cityNos="";
		   var cityNames="";
		   $('#tableId input[name="cityId"]:checkbox:checked').each(function(){
		   		var cityNo=$(this).val();
		   		var cityName=$("#cityId_"+$(this).val()).val();
		   		cityNos=cityNos+cityNo+",";
		   		cityNames=cityNames+cityName+",";
		   });
		  window.parent.document.getElementById("city").value=cityNames;
		  window.parent.document.getElementById("cityNo").value=cityNos;
		}

</script>
</body>
</html>