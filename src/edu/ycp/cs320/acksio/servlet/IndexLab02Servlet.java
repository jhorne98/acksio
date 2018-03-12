package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexLab02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/indexLab02.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
		
		if(req.getParameter("mult") != null){
			resp.sendRedirect("/acksio/multiplyNumbers");
		}
		else if(req.getParameter("add") != null){
			resp.sendRedirect("/acksio/addNumbers");
		} 
		else if(req.getParameter("guess") != null){
			resp.sendRedirect("/acksio/guessingGame");
		}
		
		req.getRequestDispatcher("/_view/indexLab02.jsp").forward(req, resp);
	}
}
