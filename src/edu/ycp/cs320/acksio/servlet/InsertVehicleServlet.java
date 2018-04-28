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
		
		Vehicle insertVehicle = new Vehicle(courier.getCourierID(), req.getParameter("licenseplate"), getIntFromParameter(req.getParameter("year")), req.getParameter("make"), req.getParameter("model"), VehicleType.valueOf(req.getParameter("type")));
		insertVehicle.setActive(0);
		
		if(insertVehicle.save()) {
			req.setAttribute("successfulinsert", "Successful Insert!");
		}
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/insertvehicle.jsp").forward(req, resp);
	}
	
	private Integer getIntFromParameter(String s) {
		if (s == null || s.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}
	}
}
