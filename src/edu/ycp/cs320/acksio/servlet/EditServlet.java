package edu.ycp.cs320.acksio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.acksio.model.*;

// servlet for the UserAccount class login page
// this servlet and UserAccount.login() based directly on http://met.guc.edu.eg/OnlineTutorials/JSP%20-%20Servlets/Full%20Login%20Example.aspx

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount editAccount;
	private Courier courier;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Edit Servlet: doGet");
		
		// take in logged in account from LoginServlet
		editAccount = (UserAccount)req.getSession(true).getAttribute("valid_model");

		//System.out.println(editAccount.getValidity());
		
		// call JSP to generate empty form
		if(editAccount != null) {
			if(editAccount.getAccountType().equals("courier")) {
				courier = new Courier();
				courier.populate(editAccount.getUserId());
				req.setAttribute("couriertsaverified", courier.isTsaVerified());
			}
			
			req.setAttribute("accountType", editAccount.getAccountType());
			
			req.getRequestDispatcher("/_view/edit.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) //TODO: Implement 
			throws ServletException, IOException {
		
		System.out.println("Edit Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;
		Dispatcher editDispatcher = new Dispatcher(VehicleType.CAR, true, req.getParameter("address"), "", req.getParameter("phone"), 0.0);
		System.out.println(req.getParameter("phone"));
		
		System.out.println("UserAccount to edit: " + editAccount.getUsername() + " " + editAccount.getName() + " " + editAccount.getPassword() + " " + editAccount.getEmail() + " " + editAccount.getAccountType());
		
		// populate newValues account with info from jsp
		UserAccount newValuesAccount = new UserAccount(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"), req.getParameter("name"), editAccount.getAccountType());
		
		System.out.println("UserAccount new values: " + newValuesAccount.getUsername() + " " + newValuesAccount.getName() + " " + newValuesAccount.getPassword()  + " "  + newValuesAccount.getEmail()+ " " + newValuesAccount.getAccountType());
		System.out.println("Courier new tsaVerified: " + req.getParameter("tsaVerfied"));
		System.out.println("Dispatcher new values: " + req.getParameter("address") + " " + req.getParameter("phone"));
		
		//System.out.println(newValuesAccount.getName().length());
		
		// edit the db with new values
		if(editAccount.edit(newValuesAccount, req.getParameter("tsaVerified"), editDispatcher)) {
			req.setAttribute("successfulUpdate", "Successful update!");
		}

		/*
		try {
			
		} catch(IOException e) {
			errorMessage = "Exception: something happened.";
		}
		*/

		if(editAccount.getAccountType().equals("courier")) {
			courier.populate(editAccount.getUserId());
			req.setAttribute("couriertsaverified", courier.isTsaVerified());
		}
		
		// Forward to view to render the result HTML document
		req.setAttribute("accountType", editAccount.getAccountType());
		req.getRequestDispatcher("/_view/edit.jsp").forward(req, resp);
	}
	
	private Integer getIntFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Integer.parseInt(s);
		}
	}
}
