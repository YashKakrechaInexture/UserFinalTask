<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<!-- Datatable css -->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
	
	<!-- Datatable js -->
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
	
	<!-- export pdf plugin -->
	<script type="text/javascript" src="lib/plugin/exportPdf/tableExport.js"></script>
	<script type="text/javascript" src="lib/plugin/exportPdf/jquery.base64.js"></script>
	<script type="text/javascript" src="lib/plugin/exportPdf/jspdf/jspdf.js"></script>
	<script type="text/javascript" src="lib/plugin/exportPdf/jspdf/libs/sprintf.js"></script>
	<script type="text/javascript" src="lib/plugin/exportPdf/jspdf/libs/base64.js"></script>
	
	<!-- custom css -->
	<link rel="stylesheet" href="lib/css/admin.css">
	
	<!-- custom js -->
	<script type="text/javascript" src="lib/js/DataTablePlugin.js"></script>
	<script type="text/javascript" src="lib/js/ExportPdfPlugin.js"></script>
	
</head>
<body>
	
	<h4>Welcome Admin, ${sessionScope.email}.</h4>
	
	<h2>User Data</h2>
	
	<table id="usertable">
		<thead>
			<tr>
				<th>Uid</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Phone No.</th>
				<th>Gender</th>
				<th>Birthdate</th>
				<th>Hobby</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.data}" var="user">
				<tr>
					<td>${user.uid}</td>
					<td>${user.fname}</td>
					<td>${user.lname}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.gender}</td>
					<td>${user.birthdate}</td>
					<td>${user.hobby}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button class="btn btn-default" id="export-pdf-btn">Export as PDF</button>
</body>
</html>