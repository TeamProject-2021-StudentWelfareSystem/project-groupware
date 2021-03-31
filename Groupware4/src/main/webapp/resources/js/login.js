$(document).ready(function() {
  $("#submit").click(function(){
    if($("#mid").val().length==0 || $("#pw").val().length==0) {
      alert("필수항목이 입력되지 않았습니다.");
      return false;
    }
  });
});
