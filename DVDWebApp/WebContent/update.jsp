<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- ALLWAYS use packages in model because if its not in a package you cant import  - when you want to import a class you have written -->
    <%@ page import="models.Dvd"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./UpdateDVDServlet" method = "POST">

	<%
		@SuppressWarnings("unchecked")
		//You can call Dvd ANYTHING
		//scriplets - java code
		//GET THE DVD FROM THE SERVER give me the dvd 
		Dvd dvd = (Dvd)request.getAttribute("dvd");
	%>

<h5>Update DVD</h5>
<input type = "hidden" name = "id" value="<%= dvd.getId()%>">
<input type = "text" name = "title" placeholder = "Title" value="<%= dvd.getTitle()%>">
<input type = "text" name = "genre" placeholder = "Genre" value="<%= dvd.getGenre()%>">
<input type = "number" name = "year" placeholder = "Year" value="<%= dvd.getYear()%>">
<button type = "submit">update</button>

</form>

</body>
</html>