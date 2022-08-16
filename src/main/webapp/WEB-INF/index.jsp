<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Project Manager</title>
</head>
<body class="container">
	<div class="d-flex flex-column align-items-center">
		<h1>Project Manager</h1>
		<p>A place for teams to manage projects.</p>
	</div>
	<div class="d-flex justify-content-center"> 
		<div class="d-flex flex-column">
			<h2>Register</h2>
			<form:form action="/users/create-new-user" metho="post" modelAttribute="newUser">
					<form:label path="firstName">First Name:</form:label>
					<form:input path="firstName"/>
					<form:errors class="text-danger" path="firstName"/><br>
					
					<form:label path="lastName">Last Name:</form:label>
					<form:input path="lastName"/>
					<form:errors class="text-danger" path="lastName"/><br>
					
					<form:label path="email">Email:</form:label>
					<form:input path="email"/>
					<form:errors class="text-danger" path="email"/><br>
					
					<form:label path="password">Password:</form:label>
					<form:input path="password" type="password"/>
					<form:errors class="text-danger" path="password"/><br>
					
					<form:label path="confirmPassword">Confirm Password:</form:label>
					<form:input path="confirmPassword" type="password"/>
					<form:errors class="text-danger" path="confirmPassword"/><br>
					
					<input type="submit" value="Register">
			</form:form>
		</div>
		<div class="d-flex flex-column">
			<h2>Log In</h2>
			<form:form action="/users/log-user-in" metho="post" modelAttribute="newLoginUser">
					<form:label path="email">Email:</form:label>
					<form:input path="email"/>
					<form:errors class="text-danger" path="email"/><br>
					
					<form:label path="password">Password:</form:label>
					<form:input path="password" type="password"/>
					<form:errors class="text-danger" path="password"/><br>
					
					<input type="submit" value="Login">
			</form:form>
		</div>
	</div>
</body>
</html>