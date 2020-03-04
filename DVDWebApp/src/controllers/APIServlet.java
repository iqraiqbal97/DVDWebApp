package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.myDAO;
import models.Dvd;
//All out roots are now protect hence now we must make an API

@WebServlet("/APIServlet")
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();
	myDAO dao = new myDAO();

	public APIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// P
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		PrintWriter writer = response.getWriter();
		// telling the browser we will return json
		response.setHeader("Content-Type", "application/json");

		try {
			if(dao.checkKey(request.getParameter("apikey")))
			{
			// Writing it out
			writer.write(gson.toJson(dao.getDVDs()));
			
			}else {
				writer.write("ERROR 902  INVALID API KEY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// PARAMS IN G
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		//key that were using
		String dvd = request.getParameter("dvd");

		Dvd d = gson.fromJson(dvd, Dvd.class);

		try {
			if(dao.checkKey(request.getParameter("apikey")))

			dao.insertDVD(d);
			else {
				writer.write("ERROR 902  INVALID API KEY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		int id = Integer.valueOf(req.getParameter("id"));

		try {
			if(dao.checkKey(req.getParameter("apikey"))) {
			dao.deleteDVD(id);
			}else {
				writer.write("ERROR 902  INVALID API KEY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String dvd = req.getParameter("dvd");
		System.out.println(dvd);

		// From json string to a java dvd object
		Dvd d = gson.fromJson(dvd, Dvd.class);

		try {
			if(dao.checkKey(req.getParameter("apikey"))) {
			dao.updateDVD(d);
			}else {
				writer.write("ERROR 902  INVALID API KEY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
