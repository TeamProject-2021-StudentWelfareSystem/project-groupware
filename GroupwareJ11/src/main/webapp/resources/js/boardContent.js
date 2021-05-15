function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
	$("#bFileID").attr("value",fileNo);
	FormObject.attr("action","/board/FileDown");
    FormObject.submit();
}