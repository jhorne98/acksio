package edu.ycp.cs320.asksio.persist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;
import edu.ycp.cs320.acksio.persist.FakeDatabase;

public class SameDatabaseTest {
	private DerbyDatabase ddb;
	private FakeDatabase fdb;
	private UserAccount fuser;
	private UserAccount duser;
	private Courier fcourier;
	private Courier dcourier;
	private Dispatcher fdispatcher;
	private Dispatcher ddispatcher;
	private Job fjob;
	private Job djob;
	private Vehicle fvehicle;
	private Vehicle dvehicle;
	private double epsilon;
	
	@Before
	public void setUp() {
		System.out.println("Set up for Same Database Tests.");
		ddb = new DerbyDatabase();
		fdb = new FakeDatabase();
		epsilon = .00001;
	}
	
	@Test
	public void testUserFetch() {
		fuser = fdb.userAccountFromID(1);
		duser = ddb.userAccountFromID(1);
		
		assertEquals(fuser.getUserId(), duser.getUserId());
		assertEquals(fuser.getName(), duser.getName());
		assertEquals(fuser.getEmail(), duser.getEmail());
		assertEquals(fuser.getUsername(), duser.getUsername());
		assertEquals(fuser.getPassword(), duser.getPassword());
		assertEquals(fuser.getAccountType(), duser.getAccountType());
		assertEquals(fuser.getValidity(), duser.getValidity());
	}
	
	@Test
	public void testCourierFetch() {
		fcourier = fdb.courierFromID(1);
		dcourier = ddb.courierFromID(1);
		
		assertEquals(fcourier.getUserId(), dcourier.getUserId());
		assertEquals(fcourier.getName(), dcourier.getName());
		assertEquals(fcourier.getEmail(), dcourier.getEmail());
		assertEquals(fcourier.getUsername(), dcourier.getUsername());
		assertEquals(fcourier.getPassword(), dcourier.getPassword());
		assertEquals(fcourier.getAccountType(), dcourier.getAccountType());
		assertEquals(fcourier.getValidity(), dcourier.getValidity());
		
		assertEquals(fcourier.getAvailability(), dcourier.getAvailability());
		assertEquals(fcourier.getCourierID(), dcourier.getCourierID());
		assertEquals(fcourier.getDispatcherID(), dcourier.getDispatcherID());
		assertEquals(fcourier.isTsaVerified(), dcourier.isTsaVerified());
		assertEquals(fcourier.getLongitude(), dcourier.getLongitude(), epsilon);
		assertEquals(fcourier.getLatitude(), dcourier.getLatitude(), epsilon);
		assertEquals(fcourier.getBalance(), dcourier.getBalance(), epsilon);
		assertEquals(fcourier.getPayEstimate(), dcourier.getPayEstimate(), epsilon);
		assertEquals(fcourier.getPayHistory(), dcourier.getPayHistory(), epsilon);
	}

	@Test
	public void testDispatcherFetch() {
		ddispatcher = ddb.dispatcherFromID(3);
		fdispatcher = fdb.dispatcherFromID(3);
		
		assertEquals(fdispatcher.getUserId(), ddispatcher.getUserId());
		assertEquals(fdispatcher.getName(), ddispatcher.getName());
		assertEquals(fdispatcher.getEmail(), ddispatcher.getEmail());
		assertEquals(fdispatcher.getUsername(), ddispatcher.getUsername());
		assertEquals(fdispatcher.getPassword(), ddispatcher.getPassword());
		assertEquals(fdispatcher.getAccountType(), ddispatcher.getAccountType());
		assertEquals(fdispatcher.getValidity(), ddispatcher.getValidity());
	}

	@Test
	public void testVehicleFetch() {
		fvehicle = fdb.vehicleFromID(1);
		dvehicle = ddb.vehicleFromID(1);
		
		assertEquals(fvehicle.getVehicleID(), dvehicle.getVehicleID());
		assertEquals(fvehicle.getCourierID(), dvehicle.getCourierID());
		assertEquals(fvehicle.getLicensePlate(), dvehicle.getLicensePlate());
		assertEquals(fvehicle.getMake(), dvehicle.getMake());
		assertEquals(fvehicle.getModel(), dvehicle.getModel());
		assertEquals(fvehicle.getType(), dvehicle.getType());
		assertEquals(fvehicle.getYear(), dvehicle.getYear());
		assertEquals(fvehicle.getActive(), dvehicle.getActive());
	}

	@Test
	public void testJobFetch() {
		fjob = fdb.jobFromID(1);
		djob = ddb.jobFromID(1);
		
		assertEquals(fjob.getCourierID(), djob.getCourierID());
		assertEquals(fjob.getDispatcherID(), djob.getDispatcherID());
		assertEquals(fjob.getDestLat(), djob.getDestLat(), epsilon);
		assertEquals(fjob.getDestLong(), djob.getDestLong(), epsilon);
		assertEquals(fjob.getTsaVerified(), djob.getTsaVerified());
		assertEquals(fjob.getRecipientName(), djob.getRecipientName());
		assertEquals(fjob.getRecipientPhone(), djob.getRecipientPhone());
		assertEquals(fjob.getDistanceMi(), djob.getDistanceMi(), epsilon);
		assertEquals(fjob.getCourierPaid(), djob.getCourierPaid());
		assertEquals(fjob.getPickUpTime(), djob.getPickUpTime());
		assertEquals(fjob.getDropOffTime(), djob.getDropOffTime());
		assertEquals(fjob.getActualTime(), djob.getActualTime());
		assertEquals(fjob.getSigned(), djob.getSigned());
		assertEquals(fjob.getApproved(), djob.getApproved());
		
	}
}