$(document).ready(function(){
	var fileIndex = 1;
	
	$("#fileAddButton").click(function() {
		
		$("#addFile").append("<div class='custom-file mb-3' id='inputFile'><input name='file_" + (fileIndex++) + "' id='boardFile2' type='file' class='custom-file-input'><label class='custom-file-label' for='customFile'>파일을 선택해 주세요</label></div>");
		//$("#fileIndex").child().remove();
	});
	
	$("input[type='file']").on('change',function(){
      $(this).next('.custom-file-label').html(this.files[0].name);
	  console.log(this.files[0].name)
   });

  	$(document).on("change", "#boardFile2", function(){
        $(this).next('.custom-file-label').html(this.files[0].name);
		console.log(this.files[0].name)
    });

});
function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
	$("#bFileID").attr("value", fileNo);
	FormObject.attr("action", "/groupware/FileDown");
	FormObject.submit();
}

$(document).on("click", "#fileDel", function() {
	$(this).parent().remove();

});

function fn_addFile() {
	var fileIndex = 1;
	
	$(".fileAdd_btn").on("click", function() {
		$("#fileIndex").append("");
	});
	$(document).on("click", "#fileDelBtn", function() {
		$(this).parent().remove();
	});
}
var fileNoArray = new Array();
var fileNameArray = new Array();
function fnDel(value, name) {

	fileNoArray.push(value);
	fileNameArray.push(name);

	$("#fileDeleteList").attr("value", fileNoArray);
	$("#fileDeleteNameList").attr("value", fileNameArray);

}
