/*Created by Joel Horne (jhorne@ycp.edu), Alaska Kiley (dkiley@ycp.edu), and Andrew Georgiou (ageorgiou@ycp.edu)
 at York College of Pennsylvania for CS320.103: Software Engineering
*/
package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.acksio.model.UserAccount;

// servlet for the UserAccount class login page
// this servlet and UserAccount.login() based directly on http://met.guc.edu.eg/OnlineTutorials/JSP%20-%20Servlets/Full%20Login%20Example.aspx

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Signup Servlet: doGet");
		
		/*
		// holds the error message text, if there is any
		String errorMessage = null;
		
		// creates a new UserAccount model with username, password from data
		UserAccount model = new UserAccount(req.getParameter("username"), req.getParameter("password"));
		
		// Add parameters as request attributes
		req.setAttribute("model", model);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		*/
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) //TODO: Implement 
			throws ServletException, IOException {
		
		System.out.println("Signup Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;
		
		// create the UserAccount model for form input
		UserAccount model = new UserAccount(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"), "", req.getParameter("accountType"));
		
		//System.out.println(req.getParameter("accountType"));
		
		System.out.println("Try: " + model.getUsername() + " " + model.getPassword() + " " + model.getEmail() + " " + model.getAccountType());
		
		try {
			if(model.getEmail().isEmpty() || model.getUsername().isEmpty() || model.getPassword().isEmpty() || model.getAccountType() == null) {
				errorMessage = "One or more fields are blank";
			} else {
			
				// TODO: find a better way to this; error codes are disgusting
				int signupFlag = model.signup();
				
				if(signupFlag == 0) {
					errorMessage = "Successful account creation!";
					System.out.println("Account created: " + model.getUsername() + " " + model.getPassword() + " " + model.getEmail() + " " + model.getAccountType());
				} else if(signupFlag == 1) {
					errorMessage = "Username is already taken.";
				} else if(signupFlag == 2) {
					errorMessage = "Email is already taken.";
				}
			}
			
			// Add parameters as request attributes
			req.setAttribute("model", model);
			
			// add result objects as attributes
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			if(req.getParameter("login") != null) {
				resp.sendRedirect("login");
			}
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		} catch(IOException e) {
			errorMessage = "Exception: something happened.";
		}
		
		/*
		if(!model.getValidity()) {
			errorMessage = "NA";
		}
		
		
		// Add parameters as request attributes
		req.setAttribute("model", model);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		*/
	}
}
