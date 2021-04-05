 $(document).ready(function() {
        $('.checkAll').click(function() {
          $('.check').prop('checked', this.checked);
        });
        
     	$('#btnAgree').click(function() {
        if($('input:checkBox[name=termsService]').is(':checked') == false ||
        $('input:checkbox[name=termsPrivacy]').is(':checked') == false) {
        	alert("이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요");
        	return false;
        }
        });
        
      });
      