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
<html data-bs-theme="dark">
<head>
<meta charset="ISO-8859-1">
<title>Login & Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>

	<div class="container mt-5 mb-5 ">
		<h1>
			<a id="decorationStyle" href="/">Seminars</a>
		</h1>
		<h4 id="appDescription">A place for creating and joining Seminars.</h4>
		
		<div id="logregformatting">

			<div id="regBorder">
				<h2 class="mt-4 mb-3 ">Register</h2>
				<form:form action="/register" method="POST" modelAttribute="newUser">
					<div class="form-group" id="logRegFormTextColor">
						<form:label path="firstName" class="form-label">First Name: </form:label>
						<form:input path="firstName" class="form-control" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div class="form-group" id="logRegFormTextColor">
						<form:label path="lastName" class="form-label">Last Name: </form:label>
						<form:input path="lastName" class="form-control" />
						<form:errors path="lastName" class="text-danger" />
					</div>
					<div class="form-group" id="logRegFormTextColor" >
						<form:label path="email" class="form-label">Email: </form:label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group" id="logRegFormTextColor" >
						<form:label path="location" class="form-label">Location: </form:label>
						<form:input path="location" class="form-control" />
						<form:errors path="location" class="text-danger" />
					</div>
					<br>
					<div class="form-group" id="logRegFormTextColor">
						<form:label path="state" >State: </form:label>
						<form:select path="state" id="regStatePadding">
							<option value="none" selected disabled hidden> </option>
							<option value="CA">CA</option>
							<option value="WA">WA</option>
							<option value="AZ">AZ</option>
							<option value="FL">FL</option>
							<option value="NV">NV</option>
							<option value="OR">OR</option>
						</form:select>
						<form:errors path="state" class="text-danger" />
					</div>
					<br>
					<div class="form-group" id="logRegFormTextColor">
						<form:label path="password" class="form-label">Password: </form:label>
						<form:input path="password" class="form-control" type="password" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group mb-2" id="logRegFormTextColor" >
						<form:label path="confirm" class="form-label">Confirm Password: </form:label>
						<form:input path="confirm" class="form-control" type="password" />
						<form:errors path="confirm" class="text-danger" />
					</div>

					<button type="submit" value="Submit" class="btn btn-primary" id="logRegButtonpadding">Register</button>

				</form:form>
			</div>

			<div id="loginBorder">

				<h2 class="mt-5 mb-3">Login</h2>
				<form:form action="/login" method="POST" modelAttribute="newLogin">

					<div class="form-group" id="logRegFormTextColor">
						<form:label path="email" class="form-label">Email: </form:label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group mb-2" id="logRegFormTextColor">
						<form:label path="password" class="form-label">Password: </form:label>
						<form:input path="password" class="form-control" type="password" />
						<form:errors path="password" class="text-danger" />
					</div>

					<button type="submit" value="Submit" class="btn btn-primary">Login</button>

				</form:form>
			</div>

		</div>
	</div>

</body>
</html>