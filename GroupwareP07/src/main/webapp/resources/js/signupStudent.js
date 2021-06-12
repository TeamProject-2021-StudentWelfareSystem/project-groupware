
function setDisplay(){
    if($('input:radio[id="memberDoubleMajor"]').is(':checked')){
      $('#studentDoubleMajor').show();
   } else{
      $('#studentDoubleMajor').hide();
   }
}

/* 단과대학 선택 시 해당 단과대학의 전공만 선택 가능 */
/*function majorChange(choice_value){
   init_value = new Array(new Array(""), 
      new Array("국어국문학과", "영어영문학과", "중어중문학과", "일어일문학과", "사학과", "문헌정보학과", "아랍지역학과", 
      "미술사학과", "철학과", "문예창작학과"), 
      new Array("행정학과", "경제학과", "정치외교학과", "디지털미디어학과", "아동학과", "청소년지도학과"), 
      new Array("경영정보학과", "국제통상학과","경영학과","부동산학과"), 
      new Array("법학과", "법무행정학과"), 
      new Array("융합소프트웨어학부", "디지털콘텐츠디자인학과"), 
      new Array("창의융합인재학부", "사회복지학과", "부동산학과", "법무행정학과", "심리치료학과", "미래융합경영학과", "멀티디자인학과", "계약학과"));
      
   if(choice_value != ""){
      document.SignupStudent.StudentMajor.options.length = 1;
      loop_count=init_value[choice_value].length;
         
      for(i=0;i<loop_count;i++){
           new_option=document.createElement("OPTION");
           new_option.text=init_value[choice_value][i];
           new_option.value=init_value[choice_value][i];
           document.SignupStudent.StudentMajor.add(new_option);
       }
      }
      else{
       document.SignupStudent.StudentMajor.options.length = 1;
      }
}*/


$(document).ready(function(){

    $('#SignupComplete').click(function(){
        if($('#userLoginID').val().length==0 ||
        $('#userLoginPwd').val().length==0 ||
        $('#userName').val().length==0||
        $('#studentGender option:selected').val()==" "||
        $('#userPhoneNum').val().length==0||
        $('#studentGrade option:selected').val()==" "||
        $('#studentMajor option:selected').val()==" "||
        $('#studentColleges option:selected').val()==" "){
            alert("필수항목을 모두 입력해주세요.");
            return false;

        } else if($('#userLoginPwd').val().length<8){
            alert("비밀번호는 8자보다 많아야합니다.");
            return false;
        } 
    })

    $('#userLoginID').keypress(function (event) {
        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
            event.preventDefault(); 
        }
    });

   $("#userLoginID").keyup(function(e) { 
      if (!(e.keyCode >=37 && e.keyCode<=40)) {
         var v = $(this).val();
         $(this).val(v.replace(/[^a-z0-9]/gi,''));
      }
   });
   
   $('#userPhoneNum').keypress(function (event) {
           if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
               event.preventDefault(); 
           }
       });
   $("#userPhoneNum").keyup(function(e) { 
      if (!(e.keyCode >=37 && e.keyCode<=40)) {
         var v = $(this).val();
         $(this).val(v.replace(/[^a-z0-9]/gi,''));
      }
   });

$("#studentColleges").change(function(){
      var 인문대학 = ["-선택-", "국어국문학과", "영어영문학과", "중어중문학과", "일어일문학과", "사학과", "문헌정보학과", "아랍지역학과", 
            "미술사학과", "철학과", "문예창작학과"];
      var 사회과학대학 = ["-선택-", "행정학과", "경제학과", "정치외교학과", "디지털미디어학과", "아동학과", "청소년지도학과"];
      var 경영대학 = ["-선택-", "경영정보학과", "국제통상학과", "부동산학과","경영학과"];
      var 법과대학 = ["-선택-", "법학과", "법무행정학과"];
      var ICT융합대학 = ["-선택-", "융합소프트웨어학부", "디지털콘텐츠디자인학과"];
      var 미래융합대학 = ["-선택-", "창의융합인재학부", "사회복지학과", "부동산학과", "법무행정학과", "심리치료학과", "미래융합경영학과", "멀티디자인학과", "계약학과"];
      
      var changeItem;
      
      if(this.value == "인문대학"){
         changeItem = 인문대학;
      }
      else if(this.value == "사회과학대학"){
         changeItem = 사회과학대학;
      }
      else if(this.value == "경영대학"){
         changeItem = 경영대학;
      }
      else if(this.value == "법과대학"){
         changeItem = 법과대학;
      }
      else if(this.value == "ICT융합대학"){
         changeItem = ICT융합대학;
      }
      else if(this.value == "미래융합대학"){
         changeItem = 미래융합대학;
      }
      $("#studentMajor").empty();
      for(var count = 0; count < changeItem.length; count++){
         var option = $("<option>" + changeItem[count] + "</option>");
         $("#studentMajor").append(option);
      }
   });
});
