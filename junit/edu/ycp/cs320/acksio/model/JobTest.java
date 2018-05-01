package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class JobTest {
	Job model;
	
	double epsilon;
	
	@Before
	public void setUp() {
		model = new Job();
		epsilon = 0.1;
		
		model.setActualTime(500);
		model.setDistanceMi(5.0);
		model.setDropOffTime(500);
		model.setPayActualForJob(1.00);
		model.setPayEstimateForJob(1.00);
		model.setPickUpTime(500);
		
		model.setCourierPaid(0);
		model.setTsaVerified(1);
		
		model.setDeststinationAddress("11313 Sample Ln.");
		model.setVehicleType("Car");
	}
	
	@Test
	public void testSets() {
		//model.setRecipentPhone(integers[6]);
		assertEquals(500, model.getActualTime());
		assertEquals(5.0, model.getDistanceMi(), epsilon);
		assertEquals(500, model.getDropOffTime());
		assertEquals(1.00, model.getPayActualForJob(), epsilon);
		assertEquals(1.00, model.getPayEstimateForJob(), epsilon);
		assertEquals(500, model.getPickUpTime());
		//assertEquals(model.getRecipentPhone(),integers[6]);
		
		//BOOLEANS
		
		assertEquals(0, (int)model.getCourierPaid());
		assertEquals(1, (int)model.getTsaVerified());
		
		//STRINGS
		
		//model.setVehicleType(strings[1]);
		assertEquals("11313 Sample Ln.", model.getDeststinationAddress());
		assertEquals(VehicleType.CAR, model.getVehicleType());
	}
	
	public void testSignOff() {
		
	}
	
	public void testClose() {
		
	}
	
	public void testFindRoute() {
		
	}
	
	public void testFindNearestCourier() {
		
	}
	
	public void testCalculatePayment() {
		
	}
}
