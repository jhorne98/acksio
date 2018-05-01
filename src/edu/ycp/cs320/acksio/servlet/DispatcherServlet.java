package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.acksio.model.Dispatcher;
import edu.ycp.cs320.acksio.model.UserAccount;
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

		// holds the error message text, if there is any
		String errorMessage = null;
		
		String[] typeValues = req.getParameterValues("vehicleType");
		
		for(int i = 0; i < typeValues.length; i++) {
			System.out.println(typeValues[i]);
		}
		
		Dispatcher model = new Dispatcher(true, req.getParameter("address"), req.getParameter("name"), req.getParameter("phone"));
		
		// Add parameters as request attributes
		req.setAttribute("model", model);
		
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
