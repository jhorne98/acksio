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
	
	public UserAccount() {
		//TODO: Implement?
	}
	
	// constructor for UserAccount class, isValid is false on default
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		isValid = false;
		//save();
	}
	
	public UserAccount(DatabaseProvider provider, int id) {
		setUserId(id);
		populate(provider, id);
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
	
	
	@Override
	public void populate(DatabaseProvider provider, int id) {
		UserAccount hold = provider.getInstance().userAccountFromID(id);
		setUsername(hold.getUsername());
		setPassword(hold.getPassword());
		login();
	}

	@Override
	public void save(DatabaseProvider provider) {
		if(!provider.getInstance().update(this)) 
			provider.getInstance().insert(this);
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
	
	public void logout() {
		//TODO: Implement 
	}
	
	public boolean isLoggedIn() {
		//TODO: Implement 
		return false;
	}
}