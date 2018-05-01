package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

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
			resp.sendRedirect("login");
		}
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/dispatcher.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Dispatcher Servlet: doPost");
		HttpSession session = req.getSession();
		//System.out.println(req.getSession().getAttribute("model"));
		Dispatcher model = (Dispatcher) session.getAttribute("model");
		model.setJobs();
		model.setCouriers();
		
		if(session.getAttribute("jobList") != null)
			model.setJobs((List<Job>)session.getAttribute("jobList"));
		
		if(session.getAttribute("courierList") != null)
			model.setCouriers((List<Courier>)session.getAttribute("courierList"));
		
		
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
		
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
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
}
