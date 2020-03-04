package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.myDAO;
import models.Dvd;

@WebServlet("/DeleteDVDServlet")
public class DeleteDVDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDVDServlet() {
		super();
	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		myDAO dao = new myDAO();
//
//		HttpSession session = request.getSession(true);
//
//		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {
//
//			int ID = Integer.valueOf(request.getParameter("id"));
//
//			myDAO mydao = new myDAO();
//
//			try {
//				mydao.deleteDVD(ID);
//
//				response.sendRedirect("./getDVDServlet");
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} else {
//			response.sendRedirect("./LoginServlet");
//		}
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			myDAO dao = new myDAO();

			// When is a string you dont have to cast it but its an INTEGER so we have to
			// cast
			int ID = Integer.valueOf(request.getParameter("id"));

			myDAO mydao = new myDAO();

			try {
				mydao.deleteDVD(ID);

				response.sendRedirect("./getDVDServlet");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./LoginServlet");
		}

	}
}
