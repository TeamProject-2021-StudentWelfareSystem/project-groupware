function TeamBoardFileDown(fileNo) {
   var FormObject = $("form[name='readForm']");
   console.log(FormObject);
   $("#tFileID").attr("value", fileNo);
   FormObject.attr("action", "/groupware/TeamBoardFileDown");
   FormObject.submit();
}

function FileNameAddFile() {

   var fileIndex = 1;
   $("#fileAddButton").click(function() {
      $("#fileIndex").append("<tr><td><input type='file' name='file_" + (fileIndex++) + "' id='tBoardFile' class='inputBox' placeholder='파일을 첨부하세요.'></td>" +
         "</tr>")
      $("#fileIndex").child().remove();
   });


}


$(document).on("click", "#fileDel", function() {
   $(this).parent().remove();

});

var fileNoArray = new Array();
var fileNameArray = new Array();
function fnDel(value, name) {

   fileNoArray.push(value);
   fileNameArray.push(name);

   $("#fileDeleteList[]").attr("value", fileNoArray);
   $("#fileDeleteNameList[]").attr("value", fileNameArray);

}

function fn_addFile() {
   var fileIndex = 1;
   //$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
   $(".fileAdd_btn").on("click", function() {
      $("#fileIndex").append("<div><input type='file' style='float:left;' name='file_" + (fileIndex++) + "'>" + "</button>" + "<button type='button' style='float:right;' id='fileDelBtn'>" + "삭제" + "</button></div>");
   });
   $(document).on("click", "#fileDelBtn", function() {
      $(this).parent().remove();

   });
}