function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
	$("#bFileID").attr("value", fileNo);
	FormObject.attr("action", "/groupware/FileDown");
	FormObject.submit();
}

function FileNameAddFile() {

	var fileIndex = 1;
	$(".FileAddButton").on("click", function() {
		
		$(".FileAddButton").on("click", function() {
			$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_" + (fileIndex++) + "'>" + "</button>" + "<button type='button' style='float:right;' id='fileDelBtn'>" + "삭제" + "</button></div>");
		});

		$(document).on("click", "#FileDeleteButton", function() {
			$(this).parent().remove();

		});
	});
}
