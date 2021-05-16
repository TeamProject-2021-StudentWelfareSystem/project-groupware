function FileDown(fileNo) {
	var FormObject = $("form[name='readForm']");
	$("#bFileID").attr("value", fileNo);
	FormObject.attr("action", "/groupware/FileDown");
	FormObject.submit();
}

function FileNameAddFile() {

	var fileIndex = 1;
		$("#fileAddButton").click(function(){
			$("#fileIndex").append("<tr><td><input type='file' name='file_"+(fileIndex++)+"' id='boardFile' class='inputBox' placeholder='파일을 첨부하세요.'></td>" +
					"</tr>")
			$("#fileIndex").child().remove();
		});
		
		$(document).on("click","#fileDel",function(){
			$(this).parent().remove();
			
		});
	}
		

var fileNoArray = new Array();
var fileNameArray = new Array();
function fnDel(value, name){
	
	fileNoArray.push(value);
	fileNameArray.push(name);
	$("#fileNoDel").attr("value", fileNoArray);
	$("#fileNameDel").attr("value", fileNameArray);
	
}