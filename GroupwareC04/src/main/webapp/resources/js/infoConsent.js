 $(document).ready(function() {
   
      $("#checkAll").click(function(){
         if($("#checkAll").prop("checked")) {
            $(".check").prop("checked", true);
         } else {
            $(".check").prop("checked", false)
         }
      });
      
      $(".check").click(function(){
         if($('input:checkbox[name=TermsService]').is(':checked') == true && 
         $('input:checkbox[name=TermsPrivacy]').is(':checked') == true) {
            $("#checkAll").prop("checked", true);
         } else {
            $("#checkAll").prop("checked", false);
         }
      });
      
        $('#btnAgree').click(function() {
        if($('input:checkbox[name=TermsService]').is(':checked') == false ||
        $('input:checkbox[name=TermsPrivacy]').is(':checked') == false) {
           alert("이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요");
           return false;
        }
        });            
 });