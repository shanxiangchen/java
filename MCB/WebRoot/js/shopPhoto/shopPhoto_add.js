	/**
	 * 增加一行
	 */
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
	
	var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				if($("#oddsshop").val()==""){
					alert("带有*号的必填!");
					$("#oddsshop").focus();
					return false;
				}
				if(!checkImgName($("#oddsshop").val())){
					return false;
				}
				$("#shopPhotoForm").submit();
				return true;
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
