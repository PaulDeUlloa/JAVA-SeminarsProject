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
<!-- ***For rendering DATE format -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="ISO-8859-1">
<title>Seminar Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>

	<div class="container mt-5">
		<div id="name_logout">
			<h1>
				Welcome, <span id="firstNameColor"> <c:out
						value="${firstName}" /></span>!
			</h1>
			<h3>
				<a id="decorationStyle" href="/logout">Logout</a>		
			</h3>
		</div>
		<h5 class="mt-5 mb-4">Here are some of the seminars in <span id="eventsStateColor">your </span> state:</h5>

		<table class="table table-striped-columns table-hover">
			<thead class="table-light">
				<tr>

					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Host</th>
					<th colspan="2">Action/ Status</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="eachSeminar" items="${seminarList}">
					<tr>
						<c:if test="${eachSeminar.seminarState == loggedInUser.state}">
							<td><a href="/seminars/${eachSeminar.id}"> <c:out
										value="${eachSeminar.seminarName}"></c:out>
							</a></td>
							<td><fmt:formatDate type="date"
									value="${eachSeminar.seminarDate}" /></td>
							<td><c:out value="${eachSeminar.seminarLocation}"></c:out></td>
							<td><c:out value="${eachSeminar.seminarowner.firstName} ${eachSeminar.seminarowner.lastName}"></c:out></td>
							<c:choose>
							
								<c:when test="${eachSeminar.seminarowner.id != (userId) && eachSeminar.users.contains(loggedInUser) == false}">
									<td>
										<form action="/seminars/${eachSeminar.id}/join" method="POST">
											<input type="hidden" name="_method" value="put" />
											<button type="submit" class="btn btn-outline-light" id="buttonsFontWeight">Join</button>
										</form>
									</td>
								</c:when>
									
							</c:choose>
							
							<c:choose>	
								<c:when test="${eachSeminar.seminarowner.id != (userId) && eachSeminar.users.contains(loggedInUser) == true}">
									<td><p id="joiningbtn" style="color:Chartreuse">Joining</p></td>
									
									<td>
										<form action="/seminars/${eachSeminar.id}/leave" method="POST">
											<input type="hidden" name="_method" value="put" />
											<button type="submit" class="btn btn-outline-light" id="buttonsFontWeight">Cancel</button>
										</form>
									</td>
									
								</c:when>
								
							
								<c:when test="${eachSeminar.seminarowner.id.equals(userId)}">
									<td><a href="/seminars/${eachSeminar.id}/edit"
										class="btn btn-outline-light" id="buttonsFontWeight">Edit</a>
									</td>
									<td>
										<form action="/seminars/${eachSeminar.id}" method="POST">
											<input type="hidden" name="_method" value="DELETE" />
											<button type="submit" class="btn btn-outline-light" id="buttonsFontWeight">Delete</button>
										</form>
									</td>
																	
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
														
							</c:choose>							
						</c:if>
					</tr>
				</c:forEach>				
			</tbody>
		</table>
	</div>

	<div class="container mt-5">

		<h5 class="mt-5 mb-3">Here are some of the seminars in <span id="eventsStateColor">other </span> states:</h5>

		<table class="table table-striped-columns table-hover">
			<thead class="table-light">
				<tr>

					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>State</th>
					<th>Host</th>
					<th colspan="2">Action</th>
					

				</tr>
			</thead>
			<tbody>

				<c:forEach var="eachSeminar" items="${seminarList}">
					<tr>
						<c:if test="${eachSeminar.seminarState != loggedInUser.state}">
							
							<td><a href="/seminars/${eachSeminar.id}"> <c:out
										value="${eachSeminar.seminarName}"></c:out>
							</a></td>
							<td><fmt:formatDate type="date"
									value="${eachSeminar.seminarDate}" /></td>
							<td><c:out value="${eachSeminar.seminarLocation}"></c:out></td>
							<td><c:out value="${eachSeminar.seminarState}"></c:out></td>
							<td><c:out value="${eachSeminar.seminarowner.firstName} ${eachSeminar.seminarowner.lastName}"></c:out></td>
							<c:choose>
								<c:when test="${eachSeminar.seminarowner.id != (userId) && eachSeminar.users.contains(loggedInUser) == false}">
									
									<td>
										<form action="/seminars/${eachSeminar.id}/join" method="POST">
											<input type="hidden" name="_method" value="put" />
											<button type="submit" class="btn btn-outline-light" id="buttonsFontWeight">Join</button>
										</form>	
									</td>
								</c:when>
								
							</c:choose>
							<c:choose>
								<c:when test="${eachSeminar.seminarowner.id != (userId) && eachSeminar.users.contains(loggedInUser) == true}">
									<td><p id="joiningbtn" style="color:Chartreuse">Joining</p></td>
									<td>
										<form action="/seminars/${eachSeminar.id}/leave" method="POST">
											<input type="hidden" name="_method" value="put" />
											
											<button type="submit" class="btn btn-outline-light" id="buttonsFontWeight">Cancel</button>
										</form>
									</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
								
							</c:choose>
									
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container mt-5 mb-5" id="createdash">
		<h1>Create a Seminar</h1>


		<form:form action="/seminars" method="post"
			modelAttribute="newSeminar" class="form-control">
			<form:hidden path="seminarowner" value="${userId }" />
	
			<div>
				<form:label path="seminarName" class="form-label"><span id="asteriskColor">* </span>Seminar Name: </form:label>
				<form:input path="seminarName" type="text" class="form-control"
					id="detailsPgBgColor" />
				<form:errors path="seminarName" class="text-danger" />
			</div>
			<div class="mt-3">
				<form:label path="seminarDate" class="form-label"><span id="asteriskColor">* </span>Seminar Date: <span id="createFutureText">(Future date only)</span></form:label>
				<form:input path="seminarDate" type="date" class="form-control" id="detailsPgBgColor" />
				<form:errors path="seminarDate" class="text-danger" />
			</div>
			<div class="mt-3 mb-2">
				<form:label path="seminarState" class="form-label"><span id="asteriskColor">* </span>Seminar State: </form:label>
				<br>
				<form:select path="seminarState" id="createStatePadding">
					<option value="none" selected disabled hidden> </option>
					<option value="CA">CA</option>
					<option value="WA">WA</option>
					<option value="AZ">AZ</option>
					<option value="FL">FL</option>
					<option value="NV">NV</option>
					<option value="OR">OR</option>
				</form:select>
				<form:errors path="seminarState" class="text-danger" />
			</div>
			<div class="mb-3">
				<form:label path="seminarLocation" class="form-label"><span id="asteriskColor">* </span>Seminar Location: </form:label>
				<form:input path="seminarLocation" type="text" class="form-control"
					id="detailsPgBgColor" />
				<form:errors path="seminarLocation" class="text-danger" />
			</div>
			

			<button type="submit" class="btn btn-primary">Submit</button>

		</form:form>
	</div>

</body>
</html>