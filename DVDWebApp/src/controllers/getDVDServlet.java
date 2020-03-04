
//CONTROLLER
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.myDAO;
import models.Dvd;
/* Servlets used to create dynamic web projects - Servlets work on the SERVER side * */

//Path of servlet 
@WebServlet("/getDVDServlet")
public class getDVDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getDVDServlet() {
		super();
	}

	// doGet method used to get information FROM the server
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instaniating the DAO method so that we an use the get
		myDAO dao = new myDAO();

		try {
			dao.getDVDs();
			// Make an araylist

			// System.out.println(dao.validateUserInfo("ayah", "12345"));

			ArrayList<Dvd> allDvds = dao.getDVDs();

			// Enhanced for loop iterating through all DVDs and printing the title
			for (Dvd dvd : allDvds) {
				System.out.println(dvd.getTitle());
			}

			// To pass array to the view
			request.setAttribute("dvds", allDvds);
			// Passings the value from servlet to HTML/JSP files
			request.getRequestDispatcher("home2.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
