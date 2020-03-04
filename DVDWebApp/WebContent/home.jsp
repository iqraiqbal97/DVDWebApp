<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.Dvd"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<!-- DYNAMIC CONTENT - renders out first 
 -->
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"rel="stylesheet"/>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"rel="stylesheet"/>
</head>
<body>
	<div class="container text-center">
		<div class="row text-center">
			<div class="col text-center">

				<h2>All DVDS</h2>

				<%
					//You can call Dvd ANYTHING
					//scriplets - java code
					ArrayList<Dvd> dvdArray = (ArrayList<Dvd>) request.getAttribute("dvds");
				%>

				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Genre</th>
							<th>Year</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<%
						
							for (Dvd dvd : dvdArray) {
						
								String buttons = "<td> <a class=\"btn btn-danger\" href=\"./DeleteDvdServlet?id="+dvd.getId()+"\"><i class=\"fa fa-trash\"></i><a/> <a class=\"btn btn-warning\" href=\"./UpdateDVDServlet?id="+dvd.getId()+"\"><i class=\"fa fa-pencil-square-o\"></i><a/>";

								
								out.print("<tr><td>" + dvd.getTitle() + "</td><td>" + dvd.getGenre() 
								+ "</td><td>" + dvd.getYear()+ "</td>");
								
								out.print(session.getAttribute("loggedin"));
								
								if((boolean)session.getAttribute("loggedin"))
								{
									out.print(buttons);
								}
								
								out.print("</td></tr>");
								
							}
						%>

					</tbody>

				</table>
			</div>
		</div>

		<a class= "btn btn-primary "href="./insert.html"> Click her to insert DVD</a>


	</div>

</body>
</html>