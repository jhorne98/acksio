package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.UserAccount;

public class UserAccountTest {
	private UserAccount model;
	
	@Before
	public void setUp() {
		model = new UserAccount();
	}
	
	@Test
	public void testSetUserId() {
		model.setUserId(1);
		assertEquals(model.getUserId(), 1);
	}
	
	@Test
	public void testSetUsername() {
		model.setUsername("user");
		assertEquals(model.getUsername(), "user");
	}
	
	@Test
	public void testSetPassword() {
		model.setPassword("pwd");
		assertEquals(model.getPassword(), "pwd");
	}
	
	@Test
	public void testLogin() {
		// TODO: generate a new user and use testLogin() on it: requires creation of createUser() or similar for db
		model.setUsername("joel");
		model.setPassword("password");
		
		model.login();
		
		assertEquals(model.getValidity(), true);
	}
}
