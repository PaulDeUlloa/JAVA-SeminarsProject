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
<title>Edit Seminar</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>

<div class="container mt-5" >
		
		<div id="name_details_edit_page">
			<h1>
				<c:out value="${oneSeminar.seminarName}"></c:out>
			</h1>
			<h3>
				<a id="decorationStyle" href="/seminars">Dashboard</a>		
			</h3>
		</div>
		
		
		<div id="editformwidth">

			<h3 class="mt-5">Edit Seminar</h3>


			<form:form action="/seminars/${oneSeminar.id}/edit" method="PUT"
				modelAttribute="oneSeminar" class="form-control" id="editPgBgColor">
				<form:hidden path="seminarowner" />
				<div id="editPgTextColor">

					<div class="mt-2">
						<form:label path="seminarName" class="form-label">Seminar Name: </form:label>
						<form:input path="seminarName" type="text" class="form-control" />
						<form:errors path="seminarName" class="text-danger" />
					</div>
					<div class="mt-2">
						<form:label path="seminarDate" class="form-label">Seminar Date: </form:label>
						<form:input path="seminarDate" type="date" class="form-control" />
						<form:errors path="seminarDate" class="text-danger" />
					</div>
					<div class="mt-2">
						<form:label path="seminarLocation" class="form-label">Seminar Location: </form:label>
						<form:input path="seminarLocation" type="text"
							class="form-control" />
						<form:errors path="seminarLocation" class="text-danger" />
					</div>
					<div class="mt-2 mb-3">
						<form:label path="seminarState" class="form-label">Seminar State: </form:label>
						<form:select path="seminarState" id="editStatePadding">
							<option value="none" selected disabled hidden> select an option </option>
							<option value="CA">CA</option>
							<option value="WA">WA</option>
							<option value="AZ">AZ</option>
							<option value="FL">FL</option>
							<option value="NV">NV</option>
							<option value="OR">OR</option>
						</form:select>
						<form:errors path="seminarState" class="text-danger" />
					</div>

					<button type="submit" class="btn btn-primary">Submit Edit</button>

				</div>
			</form:form>
		</div>
	</div>

</body>
</html>