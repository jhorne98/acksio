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
	
	@Test
	public void testSets() {
<<<<<<< HEAD
		
	}
	
	@Test
=======
		model.setPassword("pwd");
		assertEquals(model.getPassword(), "pwd");
		model.setUsername("user");
		assertEquals(model.getUsername(), "user");
		model.setUserId(1);
		assertEquals(model.getUserId(), 1);
		model.setValidity(false);
		assertEquals(model.getValidity(), false);
	}
	
>>>>>>> refs/remotes/origin/alaska
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
<<<<<<< HEAD

	@Test
	public void testSetUserId() {
		model.setUserId(1);
		assertEquals(model.getUserId(), 1);
	}
=======
>>>>>>> refs/remotes/origin/alaska
	
	public void testLogin() {
		// TODO: generate a new user and use testLogin() on it: requires creation of createUser() or similar for db
		model.setUsername("joel");
		model.setPassword("password");
		
		model.login();
		
		assertEquals(model.getValidity(), true);
<<<<<<< HEAD
=======
		
		model.setUsername("joel");
		model.setPassword("wrong");
		
		model.login();
		
		assertEquals(model.getValidity(), false);
>>>>>>> refs/remotes/origin/alaska
	}
}
