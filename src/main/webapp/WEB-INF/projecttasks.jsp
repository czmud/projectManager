<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Project Tasks</title>
</head>
<body>
	<h1>Project: <c:out value="${ project.title }"/></h1>
	<p>Project Lead: <c:out value="${ project.owner.fullName() }"/></p>
	
	<a href="/users/log-user-out">Logout</a><br>
	<a href="/projects/dashboard">Back to Dashboard</a>
	
	<form:form action="/tasks/create-new-task" modelAttribute="newTask">
		<input type="hidden" name="user" value="${ user.id }">
		<input type="hidden" name="project" value="${ project.id }">
		
		<form:label path="description">Add a task ticket for this team:</form:label>
		<form:textarea path="description"/>
		<form:errors class="text-danger" path="description"/><br>
	
		<input type="submit" value="Submit">
	</form:form>
	
	<c:forEach var="oneTask" items="${ project.tasks }">
		<h6>
		Added by <c:out value="${oneTask.user.fullName() }"/> 
		at <c:out value="${ oneTask.createdAt.toString() }"/>
		</h6>
		<p><c:out value="${ oneTask.description }"/></p>
	</c:forEach>

	
</body>
</html>