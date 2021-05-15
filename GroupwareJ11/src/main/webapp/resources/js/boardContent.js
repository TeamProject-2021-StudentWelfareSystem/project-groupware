function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
	$("#bFileID").attr("value", fileNo);
	FormObject.attr("action", "/groupware/FileDown");
	FormObject.submit();
}