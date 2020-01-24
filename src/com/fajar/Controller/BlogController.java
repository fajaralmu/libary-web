package com.fajar.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fajar.DAO.DAOBuku;
import com.fajar.DAO.DAOUser;

/**
 * Servlet implementation class BlogController
 */
//@WebServlet("/blogsaya")
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOBuku dob = new DAOBuku();
	private DAOUser dou = new DAOUser();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String tindakan = request.getServletPath();

		try {
			switch (tindakan) {
			case "/postbaru":
				postingBaru(request, response);
				break;
			case "/":
				halamanBlog(request, response);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

	private void halamanBlog(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("cloneblogpage.jsp");
		dispatcher.forward(request, response);
		
	}

	private void postingBaru(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("blogform.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
