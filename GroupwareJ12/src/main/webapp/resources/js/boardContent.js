function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
	$("#bFileID").attr("value", fileNo);
	FormObject.attr("action", "/groupware/FileDown");
	FormObject.submit();
}

function FileNameAddFile() {

	var fileIndex = 1;
<<<<<<< HEAD
		$("#fileAddButton").click(function(){
			$("#fileIndex").append("<tr><td><input type='file' name='file_"+(fileIndex++)+"' id='boardFile' class='inputBox' placeholder='파일을 첨부하세요.'></td></tr>")
			$("#fileIndex").child().remove();
		})
		
		
		$(document).on("click", "#FileDeleteButton", function() {
			$(this).parent().remove();
=======
	$(".FileAddButton").on("click", function() {
		
		$(".FileAddButton").on("click", function() {
			$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_" + (fileIndex++) + "'>" + "</button>" + "<button type='button' style='float:right;' id='fileDelBtn'>" + "삭제" + "</button></div>");
		});

		$(document).on("click", "#FileDeleteButton", function() {
			$(this).parent().remove();

		});
>>>>>>> J버전_Branch
	});
}
