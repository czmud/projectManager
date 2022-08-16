<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Edit Project</title>
</head>
<body>
	<h1>Edit Project</h1>
	
	<a href="/users/log-user-out">Logout</a><br>
	
	
	<form:form action="/projects/edit-project/${ editProject.id }" modelAttribute="editProject">
		<input type="hidden" name="_method" value="put">
		<input type="hidden" name="owner" value="${ editProject.owner.id }">
		
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
	
	
	<form:form action="/projects/delete-project/${project.id}">
		<input type="hidden" name="_method" value="delete">
		<input type="submit" value="delete">
	</form:form>		
	
</body>
</html>