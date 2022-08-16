<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Project Details</title>
</head>
<body>
	<h1>Project Details</h1>
	
	<a href="/users/log-user-out">Logout</a><br>
	<a href="/projects/dashboard">Back to Dashboard</a>
	
	<table>
		<tr>
			<td>Project:</td>
			<td><c:out value="${ project.title }"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><c:out value="${ project.description }"/></td>
		</tr>
		<tr>
			<td>Due Date:</td>
			<td><c:out value="${ project.dueDate }"/></td>
		</tr>
	</table>
	
	<a href="/projects/${project.id}/tasks">See Tasks</a>
	
	
	
	<c:if test="${ user.id.equals(project.owner.id) }">
		<form:form action="/projects/delete-project/${project.id}">
			<input type="hidden" name="_method" value="delete">
			<input type="submit" value="delete">
		</form:form>		
	</c:if>
	
	
	
</body>
</html>