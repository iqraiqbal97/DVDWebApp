<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create account</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
<body>
	<h5>CREATE A NEW ACCOUNT</h5>

	<form method="POST"action="./createAccountServlet" >
		<input type="text" name="username" > 
		<input type="password" name="password1" >
		<input type="password" name="password2" >
		<button type="submit" class="btn btn-primary">SIGN UP</button>
		
		<a class="btn btn-primary" href="./getDVDServlet">BACK</a>
		
	</form>
</body>
</html>