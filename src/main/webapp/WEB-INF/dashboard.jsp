<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Project Manager Dashboard</title>
</head>
<body>
	<h1> Welcome, <c:out value="${ user.firstName }"/></h1>
	<a href="/users/log-user-out">Logout</a><br>
	<a href="/projects/new">+New Project</a><br><br>
	
	
	<h2> All Projects </h2>
	
	<table>
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
 	<c:forEach var="oneProject" items="${ projectsUserNotOn }">
		<tr>
			<td><a href="/projects/${ oneProject.id }"><c:out value="${ oneProject.title }"/></a></td>
			<td><c:out value="${ oneProject.owner.fullName() }"/></td>
			<td><c:out value="${ oneProject.dueDate }"/></td>
			<td>
				<form:form action="/projects/join-team/${ oneProject.id }" method="post">
					<input type="submit" value="Join Team">
				</form:form>
			</td>
		</tr>
	</c:forEach>
	</table>
	
	
	<h2> Your Projects </h2>
	
	<table>
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
 	<c:forEach var="oneProject" items="${ user.joinedProjects }">
		<tr>
			<td><a href="/projects/${ oneProject.id }"><c:out value="${ oneProject.title }"/></a></td>
			<td><c:out value="${ oneProject.owner.fullName() }"/></td>
			<td><c:out value="${ oneProject.dueDate }"/></td>
			<td>
			
			<c:choose>
			<c:when test="${ user.id.equals(oneProject.owner.id)}">
				<a href="/projects/edit/${ oneProject.id }">edit</a>
			</c:when>
			<c:otherwise>
				<form:form action="/projects/leave-team/${ oneProject.id }">
					<input type="hidden" name="_method" value="delete">
					<input type="submit" value="Leave Team">
				</form:form>
			</c:otherwise>
			
			</c:choose>
			
			</td>
		</tr>
	</c:forEach>
	</table>
	
	
</body>
</html>