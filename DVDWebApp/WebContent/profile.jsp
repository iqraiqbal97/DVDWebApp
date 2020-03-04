<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PROFILE</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>

	<h1>Hi ${sessionScope.user.getUsername()}</h1>

	<p>Your API key is: ${sessionScope.user.getApi()}</p>

	<a class="btn btn-primary" href="./getDVDServlet">BACK</a>

</body>
</html>