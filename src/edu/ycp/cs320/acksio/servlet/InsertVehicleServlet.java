package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.acksio.model.*;

// inserts a new vehicle into the vehicles db
public class InsertVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount user;
	private Courier courier;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Empty Servlet: doGet");	
		
		user = (UserAccount)req.getSession(true).getAttribute("valid_model");
		
		if(user != null) {
			//req.setAttribute("accountType", editAccount.getAccountType());
			courier = new Courier();
			courier.populate(user.getUserId());
			
			req.getRequestDispatcher("/_view/insertvehicle.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) //TODO: Implement 
			throws ServletException, IOException {
		
		System.out.println("Empty Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;
		
		if(req.getParameter("submit") != null) {
			//System.out.println(getIntFromParameter(req.getParameter("year")));
			Vehicle insertVehicle = new Vehicle(courier.getCourierID(), req.getParameter("licenseplate"), getIntFromParameter(req.getParameter("year")), req.getParameter("make"), req.getParameter("model"), VehicleType.valueOf(req.getParameter("type")));
			insertVehicle.setActive(1);
			
			// the save method calls a derby update, which loads the Vehicle into the vehicles table
			if(insertVehicle.save()) {
				System.out.println(courier.getCourierID() + " " + req.getParameter("licenseplate") + " " + getIntFromParameter(req.getParameter("year")) + " " + req.getParameter("make") + " " + req.getParameter("model") + " " + VehicleType.valueOf(req.getParameter("type")));
				req.setAttribute("successfulinsert", "Successful Insert!");
			}
			// add result objects as attributes
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
		}
		
		if(req.getParameter("logout") != null) {
			req.getSession().invalidate();
			courier.logout();
			resp.sendRedirect("login");
		}
		
		if(req.getParameter("back") != null) {
			resp.sendRedirect("courier");
		}
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/insertvehicle.jsp").forward(req, resp);
	}
	
	// yeah, Java doesn't like it when you read null values into a constructor, returns 0
	private Integer getIntFromParameter(String s) {
		if (s == null || s.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}
	}
}
