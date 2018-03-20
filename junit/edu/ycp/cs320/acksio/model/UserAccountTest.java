package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserAccountTest {
	private UserAccount model;
	
	@Before
	public void setUp() {
		model = new UserAccount("username", "password");
	}
	
	@Test 
	public void testGets() {
		assertEquals(model.getPassword(), "password");
		assertEquals(model.getUsername(), "username");
	}
	
	public void testSets() {
		model.setPassword("pwd");
		assertEquals(model.getPassword(), "pwd");
		model.setUsername("user");
		assertEquals(model.getUsername(), "user");
		model.setUserId(1);
		assertEquals(model.getUserId(), 1);
		model.setValidity(false);
		assertEquals(model.getValidity(), false);
	}
	
	public void testLogout() {
		
	}
	
	public void testVerifyLogin() {
		
	}
	
	public void testEdit() {
		
	}
	
	public void testAddVehicle() {
    
	}
	
	public void testLogin() {
		// TODO: generate a new user and use testLogin() on it: requires creation of createUser() or similar for db
		model.setUsername("joel");
		model.setPassword("password");
		
		model.login();
		
		assertEquals(model.getValidity(), true);
		
		model.setUsername("joel");
		model.setPassword("wrong");
		
		model.login();
		
		assertEquals(model.getValidity(), false);
	}
}
