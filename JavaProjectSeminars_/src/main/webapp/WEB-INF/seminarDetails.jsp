<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html data-bs-theme = "dark">
<head>
<meta charset="ISO-8859-1">
<title>Seminar Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>

	<div class="container mt-5">
		<div id="name_details_edit_page">
			<h1 id="detailsSeminarName">
				<c:out value="${oneSeminar.seminarName}"></c:out>
			</h1>
			<h3>
				<a id="decorationStyle" href="/seminars">Dashboard</a>		
			</h3>
		</div>
		
		<div class="card w-100 mx-auto" id="detailsPgBgColor">
			<div class="card-body" id="detailsPgTextColor">
				<p id="detailsPgTextboldness">Host: <c:out value="${oneSeminar.seminarowner.firstName}"></c:out></p>
				<p id="detailsPgTextboldness">Date: <fmt:formatDate type="date" value="${oneSeminar.seminarDate}"/></p>
				<p id="detailsPgTextboldness">Location: <c:out value="${oneSeminar.seminarLocation}"></c:out>, <c:out value="${oneSeminar.seminarState}"></c:out></p>		
			</div>
		</div>
		<div>
			<h5 class="mt-5 mb-4">People who are attending <span id="eventsStateColor">this </span> event:</h5>
		</div>
	</div>
	<div class="container mt-5">
		<table class="table table-striped-columns table-hover">
			<thead class="table-light">
				<tr>
					<th>Name</th>
					<th>Location</th>
				</tr>
			</thead>
			<tbody>
			
				
					
			</tbody>
		</table>
	</div>
	
	

</body>
</html>