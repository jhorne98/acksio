package edu.ycp.cs320.asksio.persist;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.*;
import jbcrypt.org.mindrot.jbcrypt.*;

public class DerbyDatabaseTest {
	private DerbyDatabase db;
	private UserAccount user;
	private Courier courier;
	private Dispatcher dispatcher;
	private Job job;
	private Vehicle vehicle;
	
	@Before
	public void setUp() {
		//System.out.println("Set up for DerbyDatabase Tests.");
		db = new DerbyDatabase();
		
		// "password" as hash
		// I understand this is bad security, I just want it to work
		user = new UserAccount("joe", "$2a$10$ISHKePyKxXAz8rKRD/2WUup.jzXgt3N2fSExg6TOB83zAT96GhYUC", "joe@sample.com", "Joe", "courier");
		
		courier = new Courier();
		courier.setTsaVerified(1);
		courier.setAvailability(1);
		courier.setUserId(1);
		
		dispatcher = new Dispatcher(VehicleType.CAR, true, "11313 Sample Ln.", "Dee", "7175555555", 0.0);
		dispatcher.setUserId(1);
		
		vehicle = new Vehicle(1, "R34DCK", 2004, "Dodge", "Dakota", VehicleType.CAR);
		//vehicle.setVehicleID(1);
		vehicle.setCourierID(1);
		vehicle.setActive(1);
		
		
	}
	
	@Test
	public void testInsertRemoveUser() {
		// insert user into db
		assertTrue(db.insert(user));
		
		// retrieve inserted user
		user = db.userAccountFromUsername("joe");
		
		// test if inserted user matches
		assertEquals("joe", user.getUsername());
		assertTrue(BCrypt.checkpw("password", user.getPassword()));
		assertEquals("joe@sample.com", user.getEmail());
		assertEquals("Joe", user.getName());
		assertEquals("courier", user.getAccountType());
		
		// remove user from db
		assertTrue(db.remove(user, user.getUserId()));
	}
	
	@Test
	public void testInsertRemoveDispatcher() {
		// insert dispatcher into db
		assertTrue(db.insert(dispatcher));
		
		// retrieve inserted dispatcher
		dispatcher = db.dispatcherFromID(dispatcher.getUserId());
		
		// remove dispatcher from db
		assertTrue(db.remove(dispatcher, dispatcher.getUserId()));
	}
	
	@Test
	public void testInsertRemoveCourier() {
		// insert courier into db
		assertTrue(db.insert(courier));
		
		// retrieve inserted courier
		courier = db.courierFromID(courier.getUserId());
		
		// remove courier from db
		assertTrue(db.remove(courier, courier.getUserId()));
	}
	
	@Test
	public void testInsertRemoveVehicle() {
		user.setPassword("password");
		user.signup();
		user = db.userAccountFromUsername("joe");
		
		// insert courier into db
		//assertTrue(db.insert(courier));
		//courier = db.courierFromUsername();
		
		// insert vehicle into db
		courier = db.courierFromUsername("joe");
		vehicle.setCourierID(courier.getCourierID());
		db.insert(vehicle);
		
		// retrive inserted vehicle
		List<Vehicle> dbVehicles = db.vehiclesFromCourierID(courier.getCourierID());
		
		vehicle = dbVehicles.get(1);
		assertEquals(VehicleType.CAR, vehicle.getType());
		assertEquals("R34DCK", vehicle.getLicensePlate());
		assertEquals(2004, (int)vehicle.getYear());
		assertEquals("Dodge", vehicle.getMake());
		assertEquals("Dakota", vehicle.getModel());
		assertEquals(courier.getCourierID(), (int)vehicle.getCourierID());
		
		// remove vehicle from db
		//assertTrue(db.remove(vehicle, vehicle.getVehicleID()));
		assertTrue(db.remove(courier, courier.getCourierID()));
		assertTrue(db.remove(user, user.getUserId()));
	}
	
	@Test
	public void testInsertRemoveJob() {
		user.setPassword("password");
		user.setAccountType("dispatcher");
		user.signup();
		user = db.userAccountFromUsername("joe");
		
		dispatcher = db.dispatcherFromUsername("joe");
		
		job = new Job(1, dispatcher.getDispatcherID(), "11313 Sample Ln.", VehicleType.CAR, 0, "Dan", 6, 0.0, 0.0, 4, 4);
		job.setCourierPaid(1);
		job.setSigned(1);
		
		// insert job into db
		assertTrue(db.insert(job));
		
		List<Job> dbJobs = db.jobsFromDispatcherID(dispatcher.getDispatcherID());
		job = dbJobs.get(0);
		
		//db.jobsFromDispatcherID();
		assertTrue(db.remove(job, job.getJobID()));
	}
}