package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.*;

public class CreateJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("CreateJobServlet: doGet");	
		
		HttpSession session = req.getSession(true);
		/*if(session.getAttribute("valid_model") != null) {
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
		}*/
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/createJob.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) //TODO: Implement 
			throws ServletException, IOException {
		
		System.out.println("CreateJobServlet: doPost");
		//HttpSession session = req.getSession();
		//System.out.println(req.getSession().getAttribute("model"));
		//Dispatcher model = (Dispatcher) session.getAttribute("model");
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		try {
			String name = req.getParameter("name");
			System.out.println("Dispatcher Servlet:" + name);
			String phone = req.getParameter("phone");
			System.out.println("Dispatcher Servlet:" + phone);
			String start = req.getParameter("start");
			System.out.println("Dispatcher Servlet:"+ start);
			String address = req.getParameter("address");
			System.out.println("Dispatcher Servlet:" + address);
			Double payCof = getDoubleFromParameter(req.getParameter("payCof"));
			VehicleType vehicle = VehicleType.valueOf(req.getParameter("vehicleType"));
			System.out.println("Dispatcher Servlet:"+ vehicle);
			//Boolean tsaCertified = Boolean.valueOf((boolean) req.getAttribute("tsaCertified"));
			
			//gets tsaCertified
			int tsa;
			if (req.getAttribute("tsaCertified") == null) {
				tsa = 0;
				System.out.println("false");
			}
			else {
				tsa = 1; 
				System.out.println("true");
			}
			
			//System.out.println(tsaCertified);
			Double distance = getDoubleFromParameter(req.getParameter("distance"));
			System.out.println(distance);
			//Double payment = getDoubleFromParameter(req.getParameter("payment"));
			//System.out.println(payment);

			Double payment = distance * payCof;
			System.out.print(payment);
			
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
				dispatcherModel.queue(address, name, phone, distance, payment, vehicle, tsa);
				errorMessage = "Created Job";
				//System.out.println("Created Job!");

			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid input";
		}
		
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
	
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/createJob.jsp").forward(req, resp);
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
