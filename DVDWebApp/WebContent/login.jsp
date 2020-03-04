<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOG IN</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<h5>LOG IN</h5>

	<form method="POST" action="./LoginServlet">
		<input type="text" name="username"> <input type="password"
			name="password">
		<button type="submit" class="btn btn-primary">LOG IN</button>
		
		<a class="btn btn-primary" href="./getDVDServlet">BACK</a>

	</form>
</body>
</html>