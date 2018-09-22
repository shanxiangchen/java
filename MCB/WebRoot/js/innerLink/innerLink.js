$(document).ready(function() {
		$(".main_info:even").addClass("main_table_even");
	});
	function search() {
		$("#bankInnerLink").submit();
	}
	function addBankInnerLink() {
		var dg = new $.dialog({
			title : '新增内部链接',
			width : 350,
			height : 200,
			iconTitle : false,
			cover : true,
			maxBtn : false,
			xButton : true,
			resize : false,
			page : 'bankInnerLink/addBankInnerLink.html'
		});
		dg.ShowDialog();
	}
	
	function edit(linkNo) {
		var dg = new $.dialog(
				{
					title : '编辑内部链接',
					width : 350,
					height : 200,
					iconTitle : false,
					cover : true,
					maxBtn : false,
					xButton : true,
					resize : false,
					page : 'bankInnerLink/editBankInnerLink.html?linkNo='+ linkNo
				});
		dg.ShowDialog();
	}
	
	function del(linkNo) {
		if (confirm("确定要删除该记录？")) {
			$.ajax({
				url : 'bankInnerLink/delBankInnerLink.html',
				type : 'POST',
				data : {
					linkNo : linkNo
				},
				dataType : 'text',
				async : false,
				success : function(text) {
					if (text == "success") {
						alert("删除成功！");
						document.location.reload();
					} else {
						alert("删除失败！");
						document.location.reload();
					}
				},
				error : function() {
					alert("删除失败！");
					document.location.reload();
				}
			});
		}
	}