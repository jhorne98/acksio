package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.*;

import java.util.List;

// servlet based on Lab02 servlets
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Dispatcher Servlet: doGet");	
		
		HttpSession session = req.getSession(true);
		if(session.getAttribute("valid_model") != null) {
			UserAccount oldModel = (UserAccount) session.getAttribute("valid_model");
			DerbyDatabase db = new DerbyDatabase();
			Dispatcher model = db.dispatcherFromUsername(oldModel.getUsername());
			model.setJobs();
			model.setCouriers();
			session.setAttribute("model", model);
			
			session.setAttribute("courierList", model.getCouriers());
			session.setAttribute("jobList", model.getJobs());
		} else {
			req.setAttribute("username", "");
			req.setAttribute("password", "");
			
			resp.sendRedirect("login");
		}
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/dispatcher.jsp").forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Dispatcher Servlet: doPost");
		HttpSession session = req.getSession();
		//System.out.println(req.getSession().getAttribute("model"));
		Dispatcher model = (Dispatcher) session.getAttribute("model");
		model.setJobs();
		model.setCouriers();
		
		if(session.getAttribute("jobList") != null) {
			model.setJobs((List<Job>)session.getAttribute("jobList"));
		}
		
		if(session.getAttribute("courierList") != null) {
			model.setCouriers((List<Courier>)session.getAttribute("courierList"));
		}
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		String[] typeValues = req.getParameterValues("vehicleType");
		
		for(int i = 0; i < typeValues.length; i++) {
			System.out.println(typeValues[i]);
		}
		
		if(req.getParameter("examineCourier") != null) {
			System.out.println("Courier examination not implemented");
			resp.sendRedirect("specificCourier");
		}
		else if(req.getParameter("payCourier") != null) {
			//System.out.println("Courier payment not implemented");
			System.out.println(req.getParameter("courierSelection"));
			DerbyDatabase db = new DerbyDatabase();
			model.payCourier(db.courierFromCourierID(Integer.parseInt(req.getParameter("courierSelection"))));
		}
		else if(req.getParameter("examineJob") != null) {
			System.out.println("Job examination not implemented");
			System.out.println(req.getParameter("jobSelection"));
		}
		else if(req.getParameter("payJob") != null) {
			//System.out.println("Job payment not implemented");
			System.out.println(req.getParameter("jobSelection"));
			DerbyDatabase db = new DerbyDatabase();
			model.payCourier(Integer.parseInt(req.getParameter("jobSelection")));
		}
		
		// Add parameters as request attributes
		model.setJobs();
		model.setCouriers();
		req.setAttribute("model", model);
		req.setAttribute("courierList", model.getCouriers());
		req.setAttribute("jobList", model.getJobs());
		
		try {
			System.out.println("TEST");
			String address = req.getParameter("address");
			System.out.println(address);  
			String name = req.getParameter("name");
			System.out.println(name);
			String phone = req.getParameter("phone");
			System.out.println(phone);
			String start = req.getParameter("start");
			System.out.println(start);
			String end = req.getParameter("end");
			System.out.println(end);
			Double distance = getDoubleFromParameter(req.getParameter("distance"));
			System.out.println(distance);
			Double payment = getDoubleFromParameter(req.getParameter("payment"));
			System.out.println(payment);
		
			// check for errors in the form data before using is in a calculation
			//System.out.println("TEST-=-=-");
			if (address == null || name == null || phone  == null || distance  == null || payment  == null) {
				System.out.println("ERROR");
				errorMessage = "Please enter values";
				
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				Dispatcher dispatcherModel = new Dispatcher();
				dispatcherModel.queue(address, name, phone, distance, payment);
				System.out.println("Created Job!");

			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid input";
		}
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		if(req.getParameter("edit") != null) {
			resp.sendRedirect("edit");
		}
		
		if(req.getParameter("logout") != null) {
			req.getSession().invalidate();
			model.logout();
			resp.sendRedirect("login");
		}
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/dispatcher.jsp").forward(req, resp);
		
		
	}
	private Integer getIntFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Integer.parseInt(s);
		}
	}
	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
	
}