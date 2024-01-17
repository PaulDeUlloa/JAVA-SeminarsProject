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
<title>Create Seminar</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>

<div class="container mt-5">
		<h1>Create a Seminar</h1>
		

		<form:form action="/seminars/new" method="POST" modelAttribute="newSeminar"
			class="form-control">
			<form:hidden path="seminarowner" value="${userId }" />

			<div>
				<form:label path="seminarName" class="form-label">Seminar Name: </form:label>
				<form:input path="seminarName" type="text" class="form-control" />
				<form:errors path="seminarName" class="text-danger" />
			</div>
			<div>
				<form:label path="seminarDate" class="form-label">Seminar Date: </form:label>
				<form:input path="seminarDate" type="date" class="form-control" />
				<form:errors path="seminarDate" class="text-danger" />
			</div>
			<div>
				<form:label path="seminarLocation" class="form-label">Seminar Location: </form:label>
				<form:input path="seminarLocation" type="text" class="form-control" />
				<form:errors path="seminarLocation" class="text-danger" />
			</div>
			<div class="mt-3">
				<form:label path="seminarState" class="form-label">Seminar State: </form:label>
				<form:select path="seminarState" >
					<option value="null">--</option>
					<option value="CA">CA</option>
					<option value="WA">WA</option>
					<option value="AZ">AZ</option>
					<option value="FL">FL</option>
					<option value="NV">NV</option>
					<option value="OR">OR</option>
				</form:select>
				<form:errors path="seminarState" class="text-danger" />
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>

		</form:form>
	</div>

</body>
</html>