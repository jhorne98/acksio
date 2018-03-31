package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
//import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

//import java.sql.*;

public class UserAccount implements DataController{
	private String username;
	private String password;
	private String email;
	private int userId;
	private Boolean isValid;
	
	public UserAccount() {
		//TODO: Implement?
	}
	
	// constructor for UserAccount class, isValid is false on default
	public UserAccount(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		isValid = false;
		//save();
	}
	
	public UserAccount(String id) {
		//populate(id);
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getValidity() {
		return isValid;
	}
	
	public void setValidity(Boolean isValid) {
		this.isValid = isValid;
	}
	
	
	@Override
	public void populate(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	@Override
	public Boolean verifyLogin(String username, String Password) {
		return true;
	}
	*/
	
	// login() looks at users to determine if username exists and correct password has been input
	public void login() {
		DerbyDatabase db = new DerbyDatabase();
		
		isValid = db.verifyLogin(username, password);
	}
	
	public int signup() {
		DerbyDatabase db = new DerbyDatabase();
		
		int signupFlag = db.createAccount(username, password, email);
		
		if(signupFlag == 0) {
			isValid = true;
		}
		
		return signupFlag;
	}
	
	public void logout() {
		//TODO: Implement 
	}
	
	public boolean isLoggedIn() {
		//TODO: Implement 
		return false;
	}
}