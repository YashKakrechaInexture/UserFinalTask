$(document).ready(function(){
	$("#export-pdf-btn").click(function(){
		$('#usertable').tableExport({
			type:'pdf',
			escape:'false'
		});
	});
});