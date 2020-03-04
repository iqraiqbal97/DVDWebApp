package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.myDAO;
import models.User;

@WebServlet("/createAccountServlet")
public class createAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public createAccountServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("createAccount.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apikey = getAlphaNumericString(9);
		
		String un = request.getParameter("username");
		String pw = request.getParameter("password1");
		
		User u = new User(un, pw, apikey);
		
		myDAO dao = new myDAO();
		try {
			dao.insertUser(u);
			response.sendRedirect("./getDVDServlet");
		} catch (Exception e) {

			// TODO: handle exception
		}
	}
	
	public String getAlphaNumericString(int n)
	{
		String getAlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		
		StringBuilder sb = new StringBuilder(n);
		
		for(int i=0; i<n; i++)
		{
			int index = (int)(getAlphaNumericString.length()* Math.random());
			sb.append(getAlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

}
