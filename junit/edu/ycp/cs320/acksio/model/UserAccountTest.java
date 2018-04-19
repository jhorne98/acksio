package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.persist.DerbyDatabase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserAccountTest {
	private UserAccount model;
	private DerbyDatabase db;
	
	@Before
	public void setUp() {
		model = new UserAccount("username", "password");
		db = new DerbyDatabase();
	}
	
	/*
	@Test 
	public void testGets() {
		assertEquals(model.getPassword(), "password");
		assertEquals(model.getUsername(), "username");
	}
	*/
	
	@Test
	public void testSets() {
		model.setPassword("pwd");
		assertEquals(model.getPassword(), "pwd");
		model.setUsername("user");
		assertEquals(model.getUsername(), "user");
		model.setUserId(1);
		assertEquals(model.getUserId(), 1);
		model.setValidity(false);
		assertEquals(model.getValidity(), false);
		model.setName("name");
		assertEquals(model.getName(), "name");
		model.setEmail("test@email.com");
		assertEquals(model.getEmail(), "test@email.com");
		model.setAccountType("courier");
		assertEquals(model.getAccountType(), "courier");
	}
	
	@Test
	public void testLogout() {
		
	}
	
	@Test
	public void testVerifyLogin() {
		
	}
	
	@Test
	public void testEdit() {
		
	}
	
	@Test
	public void testAddVehicle() {
    
	}
	
	@Test
	public void testSetUserId() {
		model.setUserId(1);
		assertEquals(model.getUserId(), 1);
	}
	
	@Test
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
	
	@Test
	public void testSignup() {
		UserAccount signedInModel = new UserAccount();
		
		model.setUsername("john");
		model.setPassword("pass");
		model.setEmail("sample@ycp.edu");
		model.setAccountType("courier");
		
		assertEquals(model.signup(), 0);
		assertEquals(model.getValidity(), true);
		
		signedInModel = db.userAccountFromUsername("john");
		
		model.remove(signedInModel.getUserId());
	}
}
