package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
//import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.*;
import java.sql.*;

public class UserAccount implements DataController{
	private String username;
	private String password;
	private int userId;
	private Boolean isValid;
	private String name;
	private String email;
	private String accountType;
	
	public UserAccount() {
		//Purposefully empty
	}
	
	// constructor for UserAccount class, isValid is false on default
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		isValid = false;
		//save();
	}
	
	public UserAccount(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		isValid = false;
		//save();
	}
	
	public UserAccount(int id) {
		setUserId(id);
		populate(id);
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
	
	public Boolean getValidity() {
		return isValid;
	}
	
	public void setValidity(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void setAccountType() {
		DerbyDatabase db = new DerbyDatabase();
		if(db.dispatcherFromUsername(username) != null)
			accountType = "dispatcher";
		else
			accountType = "courier";
	}

	@Override
	public void populate(int id) {
		DerbyDatabase db = new DerbyDatabase();
		UserAccount hold = db.userAccountFromID(id);
		if(hold != null) {
			setUserId(hold.getUserId());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType();
			login();
		} else {
			throw new NullPointerException();
		}
	}
	
	public void populate(String username) {
		DerbyDatabase db = new DerbyDatabase();
		UserAccount hold = db.userAccountFromUsername(username);
		if(hold != null) {
			setUserId(hold.getUserId());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType();
			login();
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void save() {
		DerbyDatabase db = new DerbyDatabase();
		if(!db.update(this)) 
			db.insert(this);
	}
	
	public void logout() {
		isValid = false; 
	}
	
	public boolean isLoggedIn() {
		return isValid;
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
}