package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.*;

public class CourierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount user;
	private Courier courier;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Courier Servlet: doGet");
		
		/*
		int[] testints = {0,12,15,16,14};
		req.setAttribute("vehicleLoopSize", 5);
		req.setAttribute("loop", testints);
		*/
		
		user = (UserAccount)req.getSession(true).getAttribute("valid_model");
		
		if(user != null) {
			req.setAttribute("name", user.getName());
			
			courier = new Courier();
			courier.populate(user.getUserId());
			
			courier.setVehicles();
			
			// send all of the read in courier's vehicles to the jsp
			req.setAttribute("loop", courier.getVehicles());
			
			// call JSP to generate empty form
			req.getRequestDispatcher("/_view/courier.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) //TODO: Implement 
			throws ServletException, IOException {
		
		System.out.println("Courier Servlet: doPost");
		
		req.setAttribute("name", user.getName());
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		UserAccount model = new UserAccount();
		
		// Add parameters as request attributes
		req.setAttribute("model", model);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		if(req.getParameter("edit") != null) {
			resp.sendRedirect("edit");
		}
		
		if(req.getParameter("insertvehicle") != null) {
			resp.sendRedirect("insertvehicle");
		}
		
		if(req.getParameter("logout") != null) {
			req.getSession().invalidate();
			model.logout();
			resp.sendRedirect("login");
		}
		
		// check if user has selected vehicle for removal
		for(int i = 0; i < courier.getVehicles().size(); i++) {
			String param = "delete" + Integer.toString(i);
			
			if(req.getParameter(param) != null) {
				courier.removeVehicle(courier.getVehicles().get(i));
				
				courier.setVehicles();
			}
		}
		
		// send all of the read in courier's vehicles to the jsp
		req.setAttribute("loop", courier.getVehicles());
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/courier.jsp").forward(req, resp);
	}
}
