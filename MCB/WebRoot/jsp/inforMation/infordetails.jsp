<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信业银行信用卡APP后台管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	background-color: #FFFFFF;
	text-align: center;
}

.input_txt {
	width: 200px;
	height: 20px;
	line-height: 20px;
}

.info {
	height: 40px;
	line-height: 40px;
}

.info th {
	text-align: right;
	width: 120px;
	color: #4f4f4f;
	padding-right: 5px;
	font-size: 13px;
}

.info td {
	text-align: left;
}
</style>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<!-- <script type="text/javascript" src="../js/pooll/pooll_deit.js"></script> -->
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
</head>
<body>
	<form action="inforMationList.html" method="post" name="infor"
		id="infor">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr class="info">
				<th>编号:</th>
				
				<td><input type="text" disabled="disabled" name="custId"
					id="custId" value="${mation.custId}" style="width:155px">
				</td>
				
				<th>客户姓名:</th>
				<td><input type="text" disabled="disabled" name="custName"
					id="custName" value="${mation.custName}" style="width:155px">
				</td>
			</tr>
			<tr class="info">
				<th>客户拼音:</th>
				<td><input type="text" disabled="disabled" name="custNameAbc"
					id="custNameAbc" value="${mation.custNameAbc}" style="width:155px">
				</td>

				<th>客户性别:</th>
				<td>
				<c:if test="${mation.custSex=='1'}">
				<input type="text" disabled="disabled" id="custSex"
					value="男" style="width:155px">
				</c:if>	
				<c:if test="${mation.custSex=='2'}">
				<input type="text" disabled="disabled" id="custSex"
					value="女" style="width:155px">
				</c:if>	
				</td>
			</tr>
			
			<tr class="info">
				<th>客户邮箱:</th>
				<td><input type="text" disabled="disabled" name="custEmail"
					id="custEmail" value="${mation.custEmail}" style="width:155px">
				</td>

				<th>婚姻状况:</th>
				<td>
				<c:if test="${mation.custIsmarry=='1'}">
				<input type="text" disabled="disabled" id="custIsmarry"
					value="未婚" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custIsmarry=='2'}">
				<input type="text" disabled="disabled" id="custIsmarry"
					value="已婚" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custIsmarry=='3'}">
				<input type="text" disabled="disabled" id="custIsmarry"
					value="其他" style="width:155px">
				</c:if>	
				</td>
				
			</tr>

			<tr class="info">
				<th>教育程度:</th>
				<td><input type="text" disabled="disabled" name="custEducation"
					id="custEducation" value="${mation.custEducation}"
					style="width:155px">
				</td>

				<th>当前住址:</th>
				<td><input type="text" disabled="disabled" id="custNowAddress"
					value="${mation.custNowAddress}" style="width:155px"></td>
			</tr>

			<tr class="info">
				<th>详细住址:</th>
				<td><input type="text" disabled="disabled" id="custMoreAddress"
					value="${mation.custMoreAddress}" style="width:155px"></td>

				<th>住宅邮编:</th>
				<td><input type="text" disabled="disabled" name="custZipCode"
					id="custZipCode" value="${mation.custZipCode}" style="width:155px">
				</td>


			</tr>

			<tr class="info">

				<th>手机号码:</th>
				<td><input type="text" disabled="disabled" id="custPhoneNum"
					value="${mation.custPhoneNum}" style="width:155px"></td>

				<th>客户身份证号:</th>
				<td><input type="text" disabled="disabled" name="custCardId"
					id="custCardId" value="${mation.custCardId}" style="width:155px">
				</td>


			</tr>

			<tr class="info">
				<th>工作单位:</th>
				<td><input type="text" disabled="disabled" id="custWorkUnit"
					value="${mation.custWorkUnit}" style="width:155px"></td>


				<th>单位电话:</th>
				<td><input type="text" disabled="disabled" name="custUnitPhone"
					id="custUnitPhone" value="${mation.custUnitPhone}"
					style="width:155px"></td>


			</tr>

			<tr class="info">

				<th>单位地址:</th>
				<td><input type="text" disabled="disabled" id="custUnitAddress"
					value="${mation.custUnitAddress}" style="width:155px"></td>

				<th>单位详细地址:</th>
				<td><input type="text" disabled="disabled"
					name="custUnitMoreAddress" id="custUnitMoreAddress"
					value="${mation.custUnitMoreAddress}" style="width:155px">
				</td>

			</tr>

			<tr class="info">
				<th>单位邮编:</th>
				<td><input type="text" disabled="disabled" id="custUnitZipcode"
					value="${mation.custUnitZipcode}" style="width:155px"></td>


				<th>现工龄单位:</th>
				<td>
				<c:if test="${mation.custWorkAge=='1'}">
				<input type="text" disabled="disabled" id="custWorkAge"
					value="6个月以内" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custWorkAge=='2'}">
				<input type="text" disabled="disabled" id="custWorkAge"
					value="6个月-12个月" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custWorkAge=='3'}">
				<input type="text" disabled="disabled" id="custWorkAge"
					value="12个月-24个月" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custWorkAge=='4'}">
				<input type="text" disabled="disabled" id="custWorkAge"
					value="24个月-36个月" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custWorkAge=='5'}">
				<input type="text" disabled="disabled" id="custWorkAge"
					value="36个月以上" style="width:155px">
				</c:if>	
				</td>
			</tr>

			<tr class="info">

				<th>年收入:</th>
				<td><input type="text" disabled="disabled" id="custYearManey"
					value="${mation.custYearManey}" style="width:155px"></td>

				<th>单位性质:</th>
				<td><input type="text" disabled="disabled" name="custUnitType"
					id="custUnitType" value="${mation.custUnitType}"
					style="width:155px"></td>

			</tr>

			<tr class="info">

				<th>任职部门:</th>
				<td><input type="text" disabled="disabled" id="custUnitDept"
					value="${mation.custUnitDept}" style="width:155px"></td>

				<th>单位职务:</th>
				<td><input type="text" disabled="disabled" name="custUnitPost"
					id="custUnitPost" value="${mation.custUnitPost}"
					style="width:155px"></td>


			</tr>

			<tr class="info">
				<th>车牌号:</th>
				<td><input type="text" disabled="disabled" id="custCarNum"
					value="${mation.custCarNum}" style="width:155px"></td>

				<th>证件有效期:</th>
				<td>
					<c:if test="${mation.custCardIsok=='1'}">
					<input type="text" disabled="disabled" id="custCardIsok"
					value="长期" style="width:155px">
					</c:if>
					
					<c:if test="${mation.custCardIsok=='2'}">
					<input type="text" disabled="disabled" id="custCardIsok"
					value="有效期限" style="width:155px">
					</c:if>
					</td>
			</tr>


			<tr class="info">
				<th>证件有效截止日:</th>
				<td><input type="text" disabled="disabled" id="custCardDate"
					value="${mation.custCardDate}" style="width:155px"></td>



				<th>是否他人推荐:</th>
				<td>
					<c:if test="${mation.custIsReco=='1'}">
					<input type="text" disabled="disabled" id="custIsOtherCard"
					value="是" style="width:155px">
					</c:if>
					<c:if test="${mation.custIsReco=='2'}">
					<input type="text" disabled="disabled" id="custIsOtherCard"
					value="否" style="width:155px">
					</c:if>
				</td>

			</tr>

			<tr class="info">

				<th>推荐人电话工号:</th>
				<td><input type="text" disabled="disabled" id="custRecoId"
					value="${mation.custRecoId}" style="width:155px"></td>

				<th>是否有他行卡:</th>
				<td>
					<c:if test="${mation.custIsOtherCard=='1'}">
					<input type="text" disabled="disabled" id="custIsOtherCard"
					value="是" style="width:155px">
					</c:if>
					
					<c:if test="${mation.custIsOtherCard=='2'}">
					<input type="text" disabled="disabled" id="custIsOtherCard"
					value="否" style="width:155px">
					</c:if>
					
				</td>
			</tr>

			<tr class="info">
				<th>持有时间:</th>
				<td>
				<c:if test="${mation.custOtherCardDate=='1'}">
					<input type="text" disabled="disabled" id="custOtherCardDate"
					value="1年内" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custOtherCardDate=='2'}">
					<input type="text" disabled="disabled" id="custOtherCardDate"
					value="1至2年" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custOtherCardDate=='3'}">
					<input type="text" disabled="disabled" id="custOtherCardDate"
					value="3年以上" style="width:155px">
				</c:if>	
				</td>

				<th>配偶/直系亲属姓名:</th>
				<td><input type="text" disabled="disabled"
					name="custFamilyName" id="custFamilyName"
					value="${mation.custFamilyName}" style="width:155px"></td>
			</tr>

			<tr class="info">
				<th>与申请人关系:</th>
				<td>
				<c:if test="${mation.custFamilyNexus=='1'}">
					<input type="text" disabled="disabled" id="custFamilyNexus"
					value="配偶" style="width:155px">
				</c:if>	
					<c:if test="${mation.custFamilyNexus=='2'}">
					<input type="text" disabled="disabled" id="custFamilyNexus"
					value="父母" style="width:155px">
				</c:if>	
				
				<c:if test="${mation.custFamilyNexus=='3'}">
					<input type="text" disabled="disabled" id="custFamilyNexus"
					value="兄弟姐妹" style="width:155px">
				</c:if>	
				</td>

				<th>联系人手机:</th>
				<td><input type="text" disabled="disabled"
					name="custFamilyPhone" id="custFamilyPhone"
					value="${mation.custFamilyPhone}" style="width:155px"></td>
		</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0"
		width="0" height="0"></iframe>
</body>
<script type="text/javascript">
	window.onload = function() {
		var verification = $("*[id^='verification$']");
		if (verification.length > 0) {
			for (var i = 0; i < verification.length; i++) {
				verification[i].style.color = "red";
			}
		} else {
			if (verification.length > 0) {
				for (var i = 0; i < verification.length; i++) {
					verification[i].style.color = "red";

				}
			}
		}
	};
</script>
</html>