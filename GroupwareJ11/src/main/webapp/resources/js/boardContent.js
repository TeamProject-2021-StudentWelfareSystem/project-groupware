function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
<<<<<<< HEAD
	$("#bFileID").attr("value",fileNo);
	FormObject.attr("action","/board/FileDown");
    FormObject.submit();
=======
	$("#bFileID").attr("value", fileNo);
	FormObject.attr("action", "/groupware/FileDown");
	FormObject.submit();
>>>>>>> J버전_Branch
}