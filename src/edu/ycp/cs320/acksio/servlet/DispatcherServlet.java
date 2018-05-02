package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.acksio.model.Dispatcher;
import edu.ycp.cs320.acksio.model.UserAccount;
//import edu.ycp.cs320.lab02.controller.AddNumbersController;

// servlet based on Lab02 servlets
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Dispatcher Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/dispatcher.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Dispatcher Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;
		
		String[] typeValues = req.getParameterValues("vehicleType");
		
		/*for(int i = 0; i < typeValues.length; i++) {
			System.out.println(typeValues[i]);
		}*/
		
		
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
			Double distance = getDoubleFromParameter("distance");
			System.out.println(distance);
			Double payment = getDoubleFromParameter("payment");
			System.out.println(payment);
		
			// check for errors in the form data before using is in a calculation
			if (address == null || name == null || phone  == null /*|| distance  == null || payment  == null*/) {
				errorMessage = "Please enter values";
				System.out.println("ERROR");
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
			Dispatcher model = new Dispatcher();
			model.Queue(address, name, phone, distance, payment);
			System.out.println("Created Job!");

			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid input";
		}
		
		
		//Dispatcher model = new Dispatcher(true, req.getParameter("address"), req.getParameter("name"), req.getParameter("phone"), getDoubleFromParameter(req.getParameter("distance")), getDoubleFromParameter(req.getParameter("payment")));
		//System.out.println(model.getAddress());
		/*model.setAddress(req.getParameter("address"));
		System.out.println(model.getAddress());
		model.setName(req.getParameter("name"));
		model.setPhone(req.getParameter("phone"));
		model.setDistance(getDoubleFromParameter(req.getParameter("distance")));
		System.out.println("DIS = " + model.getDistance());
		model.setPayment(getDoubleFromParameter(req.getParameter("payment")));*/
		//Dispatcher model = new Dispatcher(true, req.getParameter("address"), req.getParameter("name"), req.getParameter("phone"));
		
				// Add parameters as request attributes
		//req.setAttribute("model", model);
		
		/*model.setAddress(req.getParameter("address"));
		System.out.println(model.getAddress());
		model.setName(req.getParameter("name"));
		model.setPhone(req.getParameter("phone"));
		model.setDistance(getDoubleFromParameter(req.getParameter("distance")));
		System.out.println("DIS = " + model.getDistance());
		model.setPayment(getDoubleFromParameter(req.getParameter("payment")));
		*/
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/dispatcher.jsp").forward(req, resp);
		
		
	}
	private int getIntFromParameter(String s) {
		if (s == null || s.equals("")) {
			return (Integer) null;
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