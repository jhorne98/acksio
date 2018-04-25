package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
//import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.*;
import jbcrypt.org.mindrot.jbcrypt.*;
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
	
	public UserAccount(String username, String password, String email, String name, String accountType) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.accountType = accountType;
		isValid = false;
		//save();
	}
	
	public UserAccount(int id) {
		setUserId(id);
		populate(id);
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
	public void populate(int id) {
		DerbyDatabase db = new DerbyDatabase();
		UserAccount hold = db.userAccountFromID(id);
		if(hold != null) {
			setUserId(hold.getUserId());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType(hold.getAccountType());
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
			setAccountType(hold.getAccountType());
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
	
	// allows User to edit specific fields of UserAccount
	public Boolean edit(UserAccount updated) {
		DerbyDatabase db = new DerbyDatabase();
		//UserAccount editedUser = new UserAccount();
		
		// there's no good way to iterate through fields of a class, or so what StackOverflow tells me
		// user is able to update username, password, email, and name
		if(updated.getUsername().length() != 0) {
			username = updated.getUsername();
		}
		
		if(updated.getPassword().length() != 0) {
			password = BCrypt.hashpw(updated.getPassword(), BCrypt.gensalt());			
		}
		
		if(updated.getEmail().length() != 0) {
			email = updated.getEmail();
		}
		
		if(updated.getName().length() != 0) {
			name = updated.getName();			
		}
		
		// update() only edits four above
		/*
		userId = updated.getUserId();
		isValid = updated.getValidity();
		accountType = updated.getAccountType();
		*/
		
		return db.update(this);
	}
	
	// login() looks at users to determine if username exists and correct password has been input
	public UserAccount login() {
		DerbyDatabase db = new DerbyDatabase();
		UserAccount user = new UserAccount();
		
		isValid = db.verifyLogin(username, password);
		
		if(isValid) {
			user = db.userAccountFromUsername(username);
		}
		
		return user;
	}
	
	public int signup() {
		DerbyDatabase db = new DerbyDatabase();
		
		// implementation of jBCrypt to hash pw
		//String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
		
		int signupFlag = db.createAccount(username, password, email, accountType);
		
		if(signupFlag == 0) {
			isValid = true;
		}
		
		return signupFlag;
	}
	
	
}