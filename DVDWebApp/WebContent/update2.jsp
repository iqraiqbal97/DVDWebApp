<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ALLWAYS use packages in model because if its not in a package you cant import  - when you want to import a class you have written -->
<%@ page import="models.Dvd"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPDATE DVD</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>

	<h5>UPDATE DVD</h5>

	<form action="./UpdateDVDServlet" method="POST">

		<input type="hidden" name="id" value="${dvd.getId()}"> <input
			type="text" name="title" placeholder="Title"
			value="${dvd.getTitle()}"> 
			<input type="text" name="genre"
			placeholder="Genre" value="${dvd.getGenre()}"> 
			<input type="number" name="year" placeholder="Year" value="${dvd.getYear()}">
		<button type="submit" class="btn btn-primary">UPDATE</button>

	</form>

</body>
</html>