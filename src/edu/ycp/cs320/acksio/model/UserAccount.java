package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;

public class UserAccount implements DataController{
	public UserAccount() {
		//TODO: Implement?
	}
	
	public UserAccount(String username, String password) {
		//TODO: make this for creating a new user in the database
		save();
	}
	
	public UserAccount(String id) {
		populate(id);
	}

	@Override
	public void populate(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void login() {
		//TODO: Implement 
	}
	
	public void logout() {
		//TODO: Implement 
	}
	
	public boolean isLoggedIn() {
		//TODO: Implement 
		return false;
	}
}