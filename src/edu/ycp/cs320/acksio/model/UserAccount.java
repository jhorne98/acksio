package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;

public class UserAccount implements DataController{
	//ATTRIBUTES
	//TODO: Implement 
	
	//CONSTRUCTORS
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

	
	//METHODS
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
	
	public boolean verifyLogin() {
		//TODO: Implement
		return false;
	}
	
	public void logout() {
		//TODO: Implement 
	}
	
	public boolean isLoggedIn() {
		//TODO: Implement 
		return false;
	}
	
	public void edit(String username) {
		//TODO: Implement 
	}
	
	public void addVehicle(String username) {
		//TODO: Implement 
	}
}