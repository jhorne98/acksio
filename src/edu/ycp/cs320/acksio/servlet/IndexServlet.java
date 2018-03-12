package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
		
		if(req.getParameter("logIn") != null){
			resp.sendRedirect("/acksio/logIn");
		}
		else if(req.getParameter("dispatcher") != null){
			resp.sendRedirect("/acksio/dispatcher");
		} 
		else if(req.getParameter("courier") != null){
			resp.sendRedirect("/acksio/courier");
		}
		else if(req.getParameter("createJob") != null){
			resp.sendRedirect("/acksio/createJob");
		}
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}

/*	
 * <input type="Submit" name="logIn" value="Log In">
 * <input type="Submit" name="dispatcher" value="Dispatcher">
 * <input type="Submit" name="courier" value="Courier">
 * <input type="Submit" name="createJob" value="Create Job">
 */