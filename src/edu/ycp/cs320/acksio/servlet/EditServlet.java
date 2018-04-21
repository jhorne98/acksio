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

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount editAccount;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Edit Servlet: doGet");
		
		// take in logged in account from LoginServlet
		editAccount = (UserAccount)req.getSession(false).getAttribute("valid_model");
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) //TODO: Implement 
			throws ServletException, IOException {
		
		System.out.println("Edit Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;
		String successfulUpdate = null;
		
		System.out.println("UserAccount to edit: " + editAccount.getUsername() + " " + editAccount.getName() + " " + editAccount.getPassword() + " " + editAccount.getAccountType() + " "  + editAccount.getEmail());
		
		// populate newValues account with info from jsp
		UserAccount newValuesAccount = new UserAccount(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"), req.getParameter("name"), editAccount.getAccountType());
		
		System.out.println("UserAccount new values: " + newValuesAccount.getUsername() + " " + newValuesAccount.getName() + " " + newValuesAccount.getPassword() + " " + newValuesAccount.getAccountType() + " "  + newValuesAccount.getEmail());
		
		// edit the db with new values
		editAccount.edit(newValuesAccount);
		
		successfulUpdate = "Success!";
		
		/*
		try {
			
		} catch(IOException e) {
			errorMessage = "Exception: something happened.";
		}
		*/
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/edit.jsp").forward(req, resp);
	}
}
