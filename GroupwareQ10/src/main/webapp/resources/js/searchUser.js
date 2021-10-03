$(document).ready(function() {
      let result = '<c:out value="${Checker}"/>';
      checkAlert(result);

      function checkAlert(result) {
         if (result === '') {
            return;
         } else if (result === "NoReiveiwList") {
            alert("작성된 후기가 없습니다.");
         }
      }
   });
$(".searchButton").click(
                                    function() {
                                       var SearchKeyWord = $('#searchKeyWord');
                                       if (SearchKeyWord.val().length == 0) {
                                          alert('검색어를 입력해주세요');
                                          return false;
                                       } else {
                                          var data = {
                                             key : SearchKeyWord
                                                   .val()
                                          };
                                       }

                                       var token = $(
                                             "input[name='_csrf']")
                                             .val();
                                       var header = "X-CSRF-TOKEN";
                                       $
                                             .ajax({
                                                type : "POST",
                                                url : "searchUser.do",
                                                data : JSON
                                                      .stringify(data),
                                                cache : false,
                                                dataType : "json",
                                                contentType : "application/json; charset=UTF-8",
                                                beforeSend : function(
                                                      xhr) {
                                                   xhr
                                                         .setRequestHeader(
                                                               header,
                                                               token);
                                                },
                                                success : function(
                                                      response) {
                                                   var html = "";
                                                   console
                                                         .log(response.length == 0);
                                                   if (response.length == 0) {
                                                      alert('해당 유저가 존재하지 않습니다.');
                                                   } else {
                                                      var num = 1;
                                                      for (key in response) {
                                                         html += '<tr>';
                                                         html += '<td class="col1">'
                                                               + num++;
                                                         html += '<td class="col2">'
                                                         html += '<a href = /groupware/search/reviewList?no='+response[key].UserEmail+'>'
                                                               + response[key].UserName;+'</a>'
                                                         html += '<td class="col3">'
                                                               + response[key].UserMajor;
                                                         html += '<td class="col4">'
                                                               + response[key].UserEmail;
                                                         html += '<td class="col5">'
                                                               + response[key].PhoneNum;
                                                         html += '<td class="col6">'
                                                               + response[key].Gender;
                                                         html += '<td class="col6">'
                                                        	   + response[key].Grade;
                                                         html += '</tr>';
                                                      }
                                                   }

                                                   $(
                                                         "#information")
                                                         .empty();
                                                   $(
                                                         "#information")
                                                         .append(
                                                               html);
                                                },
                                             });
                                    });

function notSelectValue(){
	if($("#team option:selected").val() ==""){
		alert("팀을 선택하지 않으셨습니다.");
		return true;
	}
	
}
	