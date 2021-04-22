/*
$(function(){
	var checkObject = document.getElementsByName("RowCheck");
	var rowCount = checkObject.length;
	
	$("input[name='CheckAll']").click(function(){
		var checkListArr = $("input[name='RowCheck']");
		for(var i=0; i<checkListArr.length; i++) {
			checkListArr[i].checked = this.checked;
		}
	});
	
	$("input[name='RowCheck']").click(function(){
		if($("input[name='RowCheck']:checked").length == rowCount) {
			$("input[name='CheckAll']")[0].checked = true;
		}
		else {
			$("input[name='CheckAll']")[0].checked = false;
		}
	});
});
*/

/*
function deleteValue() {
  	$('#delete').click(function(){
		if(confirm("삭제하시겠습니까?")){
			self.location.href = "/manageList/remove?userLoginID=${status.count}";
		}
	});

}
*/
