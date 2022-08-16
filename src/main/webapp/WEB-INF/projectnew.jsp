<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Create a Project</title>
</head>
<body>
	<h1>Create a Project</h1>
	
	<a href="/users/log-user-out">Logout</a><br>
	
	
	<form:form action="/projects/create-new-project" modelAttribute="newProject">
		<input type="hidden" name="owner" value="${ user.id }">
		<input type="hidden" name="teamMembers" value="${ user.id }">
		
		<form:label path="title">Project Title:</form:label>
		<form:input path="title"/>
		<form:errors class="text-danger" path="title"/><br>
		
		<form:label path="description">Project Description:</form:label>
		<form:input path="description"/>
		<form:errors class="text-danger" path="description"/><br>
		
		<form:label path="dueDate">Due Date:</form:label>
		<form:input path="dueDate" type="date"/>
		<form:errors class="text-danger" path="dueDate"/><br>
	
		<a href="/projects/dashboard">Cancel</a>
		<input type="submit" value="Submit">
	</form:form>
	
</body>
</html>