package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.persist.DerbyDatabase;
import edu.ycp.cs320.acksio.persist.InitialData;

public class CourierTest {
	
	private Courier expected;
	private Courier model;
	private Double epsilon;
	
	@Before
	public void setUp() {
		try {
			expected = InitialData.getCouriers().get(0);
			model = new Courier(expected.getUsername());
			epsilon = 0.001;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testGets() {
		assertEquals(expected.getUsername(), model.getUsername());
		assertEquals(expected.getPassword(), model.getPassword());
		assertEquals(expected.getAccountType(), model.getAccountType());
		assertEquals(expected.getName(), model.getName());
		assertEquals(expected.getEmail(), model.getEmail());
		assertEquals(expected.getAvailability(), model.getAvailability());
		assertEquals(expected.getBalance(), model.getBalance(), epsilon);
		assertEquals(expected.getCourierID(), model.getCourierID());
		assertEquals(expected.getDispatcherID(), model.getDispatcherID());
		assertEquals(expected.getInsuranceCoverage(), model.getInsuranceCoverage());
		assertEquals(expected.getInsuranceExpiration(), model.getInsuranceExpiration());
		assertEquals(expected.getInsured(), model.getInsured());
		assertEquals(expected.getLatitude(), model.getLatitude(), epsilon);
		assertEquals(expected.getLicenseExpiration(), model.getLicenseExpiration());
		assertEquals(expected.getLicenseID(), model.getLicenseID());
		assertEquals(expected.getLongitude(), model.getLongitude(), epsilon);
		assertEquals(expected.getPayEstimate(), model.getPayEstimate(), epsilon);
		assertEquals(expected.getPayHistory(), model.getPayHistory(), epsilon);
		
		//These need to be tested elsewhere.
		//assertEquals(expected.getJobs(), model.getJobs());
		//assertEquals(expected.getUnapprovedJobs(), model.getUnapprovedJobs());
		//assertEquals(expected.getUnpaidJobs(), model.getUnpaidJobs());
		//assertEquals(expected.getVehicles(), model.getVehicles());

	}
	
	@Test 
	public void testSets() {
		String testString = "Hello, World!";
		
		model.setUsername(testString);
		model.setPassword(testString);
		model.setName(testString);
		model.setEmail(testString);
		model.setAccountType(testString);
		model.setLicenseExpiration(testString);
		
		assertEquals(testString, model.getUsername());
		assertEquals(testString, model.getPassword());
		assertEquals(testString, model.getName());
		assertEquals(testString, model.getEmail());
		assertEquals(testString, model.getAccountType());
		assertEquals(testString, model.getLicenseExpiration());
		
		int testInteger = 42;
		
		model.setCourierID(testInteger);
		model.setDispatcherID(testInteger);
		model.setAvailability(testInteger);
		model.setLicenseID(testInteger);
		model.setInsured(testInteger);
		model.setInsuranceExpiration(testInteger);
		model.setTsaVerified(testInteger);
		model.setUnpaidJobs(testInteger);
		model.setUnapprovedJobs(testInteger);
		model.setUserId(testInteger);
		
		assertEquals(testInteger, model.getCourierID());
		assertEquals(testInteger, model.getDispatcherID());
		assertEquals(testInteger, (int)model.getAvailability());
		assertEquals(testInteger, (int)model.getInsured());
		assertEquals(testInteger, model.getInsuranceExpiration());
		assertEquals((int)testInteger, (int)model.isTsaVerified());
		assertEquals(testInteger, model.getUnpaidJobs());
		assertEquals(testInteger, model.getUnapprovedJobs());
		assertEquals(testInteger, model.getUserId());
		
		double testDouble = 4.2;
		
		model.setPayHistory(testDouble);
		model.setPayEstimate(testDouble);
		model.setBalance(testDouble);
		model.setLatitude(testDouble);
		model.setLongitude(testDouble);
		
		assertEquals(testDouble, model.getPayHistory(), epsilon);
		assertEquals(testDouble, model.getPayEstimate(), epsilon);
		assertEquals(testDouble, model.getBalance(), epsilon);
		assertEquals(testDouble, model.getLatitude(), epsilon);
		assertEquals(testDouble, model.getLongitude(), epsilon);

		model.setValidity(false);
		assertFalse(model.getValidity());
		
		int[] testIntArray = {1, 1, 1};
		model.setInsuranceCoverage(testIntArray);
		assertEquals(testIntArray, model.getInsuranceCoverage());
	}
	
	@Test 
	public void testAddVehicle() {
		//NOTE: InsertVehicleServlet does not use this method.
		Vehicle vehicle = new Vehicle(1);
		model.addVehicle(vehicle);
		assertTrue(model.getVehicles().contains(vehicle));
	}
	
	@Test 
	public void testAcceptJob() {
		//TODO: Implement
	}
	
	@Test 
	public void testUpdateLocation() {
		//TODO: Implement
	}
	
	@Test 
	public void testCalculateTotalPayment() {
		//TODO: Implement
	}
	
	@Test 
	public void testApproveInvoice() {
		DerbyDatabase db = new DerbyDatabase();
		db.removeTables();
		db.createTables();
		db.loadInitialData();
		
		model.setJobs();
		model.setUnapprovedJobs();
		int beforeApproval = model.getUnapprovedJobs();
		model.acceptAllInvoices();
		model.setUnapprovedJobs();
		assertFalse(model.getUnapprovedJobs() == beforeApproval);
	}
}
