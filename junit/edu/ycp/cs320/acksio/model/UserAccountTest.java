package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.persist.*;
import jbcrypt.org.mindrot.jbcrypt.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserAccountTest {
	private UserAccount model;
	private UserAccount signedInModel;
	private UserAccount editedModel;
	private Dispatcher editedDispatcher;
	private Courier editedCourier;
	private DerbyDatabase db;
	
	@Before
	public void setUp() {
		model = new UserAccount("username", "password");
		db = new DerbyDatabase();
		
		model.setPassword("pwd");
		model.setUsername("user");
		model.setUserId(1);
		model.setValidity(true);
		model.setName("name");
		model.setEmail("test@email.com");
		model.setAccountType("courier");
		
		editedDispatcher = new Dispatcher(VehicleType.CAR, false, "11313 Sample Road", "Dee", "7177495979", 0.0);
		editedCourier = new Courier(1);
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
		assertEquals(1, model.getUserId());
		assertEquals("pwd", model.getPassword());
		assertEquals("user", model.getUsername());
		assertEquals(1, model.getUserId());
		assertEquals(true, model.getValidity());
		assertEquals("name", model.getName());
		assertEquals("test@email.com", model.getEmail());
		assertEquals("courier", model.getAccountType());
	}
	
	@Test
	public void testLogin() {
		model.setUsername("joel");
		model.setPassword("password");
		model.login();
		
		assertEquals(true, model.getValidity());
		
		model.setUsername("joel");
		model.setPassword("wrong");
		model.login();
		
		assertEquals(false, model.getValidity());
	}
	
	@Test
	public void testLogout() {
		model.logout();
		assertEquals(false, model.getValidity());
	}
	
	@Test
	public void testSignup() {
		signedInModel = new UserAccount();
		
		model.setUsername("john");
		model.setPassword("pass");
		model.setEmail("sample@ycp.edu");
		model.setAccountType("courier");
		
		assertEquals(0, model.signup());
		assertEquals(true, model.getValidity());
		
		signedInModel = db.userAccountFromUsername("john");
		//System.out.println(signedInModel.getUserId());
		
		//System.out.println(signedInModel.getPassword());
		
		assertEquals("john", signedInModel.getUsername());
		assertTrue(BCrypt.checkpw("pass", signedInModel.getPassword()));
		assertEquals("sample@ycp.edu", signedInModel.getEmail());
		assertEquals("courier", signedInModel.getAccountType());
		
		model.remove(signedInModel.getUserId());
	}
	
	// tests edit for courier and dispatcher type UserAccounts
	@Test
	public void testEdit() {
		editedModel = new UserAccount();
		
		// set up the original model
		model.setUsername("john");
		model.setPassword("pass");
		model.setEmail("sample@ycp.edu");
		model.setAccountType("courier");
		
		// set up the edits to the orignal
		editedModel.setUsername("dan");
		editedModel.setPassword("newpass");
		editedModel.setEmail("newsample@ycp.edu");
		editedModel.setName("Dan");
		
		// place the original model into the db
		assertEquals(0, model.signup());
		assertEquals(true, model.getValidity());
		
		// place the added row (w/ userId) into model
		model = db.userAccountFromUsername("john");
		//System.out.println(model.getUserId());
		
		// insert new courier with corresponding userId into db
		editedCourier.setUserId(model.getUserId());
		editedCourier.setAvailability(0);
		editedCourier.setTsaVerified(1);
		//db.insert(editedCourier);
		
		// place the added courier row into model 
		editedCourier = db.courierFromUsername("john");
		//System.out.println(editedCourier.getUserId());
		
		// edit and test if updated
		assertTrue(model.edit(editedModel, "yes", editedDispatcher));
		model = db.userAccountFromUsername("dan");
		editedCourier = db.courierFromUsername("dan");
		
		assertEquals("dan", model.getUsername());
		assertTrue(BCrypt.checkpw("newpass", model.getPassword()));
		assertEquals("newsample@ycp.edu", model.getEmail());
		assertEquals("Dan", model.getName());
		
		assertEquals(1, (int)editedCourier.isTsaVerified());
		//System.out.println(editedCourier.isTsaVerified());
		
		// insert new dispatcher with corresponding userId into db
		editedDispatcher.setUserId(model.getUserId());
		db.insert(editedDispatcher);
		
		// edit and test with dispatcher
		model.setAccountType("dispatcher");
		assertTrue(model.edit(editedModel, "yes", editedDispatcher));
		editedDispatcher = db.dispatcherFromUsername("dan");
		
		assertEquals("11313 Sample Road", editedDispatcher.getAddress());
		assertEquals("7177495979", editedDispatcher.getPhone());
		
		
		// UserAccount and Courier models remove themselves from the db
		model.remove(model.getUserId());
		//db.remove(editedCourier, editedCourier.getCourierID());
		db.remove("courier", editedCourier.getCourierID());
		db.remove("dispatcher", editedDispatcher.getDispatcherID());
	}
	
	/*
	@Test
	public void testEditCourier() {
		UserAccount userJohn = new UserAccount();
		
		model.setUsername("john");
		model.setPassword("pass");
		model.setEmail("sample@ycp.edu");
		model.setAccountType("courier");
		
		model.signup();
		
		model.setUsername("dan");
		model.setPassword("newpass");
		model.setEmail("newsample@ycp.edu");
		model.setName("Dan");
		
		//signedInModel = db.userAccountFromUsername("john");
		//System.out.println(signedInModel.getUserId());
		
		//System.out.println(signedInModel.getUserId());
		userJohn = db.userAccountFromUsername("john");
		
		editCourier.setUserId(userJohn.getUserId());
		editCourier.setAvailability(0);
		db.insert(editCourier);
		
		assertTrue(model.edit(userJohn, "yes", editDispatcher));
		model = db.userAccountFromUsername("dan");
		
		assertEquals("dan", model.getUsername());
		assertTrue(BCrypt.checkpw("newpass", model.getPassword()));
		assertEquals("newsample@ycp.edu", model.getEmail());
		assertEquals("Dan", model.getName());
		
		System.out.println(model.getUserId());
		
		model.remove(model.getUserId());
	}*/
}
