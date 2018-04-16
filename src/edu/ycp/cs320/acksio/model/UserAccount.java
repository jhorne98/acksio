package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
//import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.*;
//import java.sql.*;

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
	
	public UserAccount(String username, String password, String email, String accountType) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountType = accountType;
		isValid = false;
		//save();
	}
	
	public UserAccount(DatabaseProvider provider, int id) {
		setUserId(id);
		populate(provider, id);
	}

	// getters and setters
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

	@Override
	public void populate(DatabaseProvider provider, int id) {
		UserAccount hold = provider.getInstance().userAccountFromID(id);
		if(hold != null) {
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			login();
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void save(DatabaseProvider provider) {
		if(!provider.getInstance().update(this)) 
			provider.getInstance().insert(this);
	}
	
	// remove user from users table by user_id
	public int remove(int id) {
		DerbyDatabase db = new DerbyDatabase();
		
		return db.removeEntry("user", id);
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
		
		int signupFlag = db.createAccount(username, password, email, accountType);
		
		if(signupFlag == 0) {
			isValid = true;
		}
		
		return signupFlag;
	}
	
	
}