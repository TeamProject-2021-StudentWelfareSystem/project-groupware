
$(document).ready(function() {
  $("#submitId").click(function(){
    if($("#eamilLoginID").val().length==0 || $("#emailLoginPW").val().length==0) {
      alert("아이디 혹은 비밀번호가 입력되지 않았습니다.");
      return false;
    } 
  });
});

$(document).ready(function() {
	$('#userLoginID').keypress(function (event) {
    	   if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
    	      event.preventDefault(); 
    	    } 
    	    });
	$("#userLoginID").keydown(function(e) { 
		if (!(e.keyCode >=37 && e.keyCode<=40)) {
			var v = $(this).val();
			$(this).val(v.replace(/[^0-9]/gi,''));
		}
	});
});