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

@WebServlet("/UpdateDVDServlet")
public class UpdateDVDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateDVDServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			// get with a link
			// get you retrieve
			// URL
			// KEY HAS TO MATCH STOOPID
			// retrieve that id
			int ID = Integer.valueOf(request.getParameter("id"));

			myDAO dao = new myDAO();
			try {
				Dvd dvd = dao.get1DVD(ID);

				// to pass thing to the view
				request.setAttribute("dvd", dvd);
				// PASS EVERYTHING TO THAT PAGE
				request.getRequestDispatcher("update2.jsp").forward(request, resp);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("./LoginServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			// USE POST ONLY WITH FORM
			// populate from thte form
			int id = Integer.valueOf(request.getParameter("id"));
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			int year = Integer.valueOf(request.getParameter("year"));

			Dvd dvd = new Dvd(id, title, genre, year);

			myDAO dao = new myDAO();

			try {
				dao.updateDVD(dvd);

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
