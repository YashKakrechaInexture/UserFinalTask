$(document).ready( function () {
    $('#usertable').DataTable({
		dom: 'Bfrtip',
		buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
	});
});