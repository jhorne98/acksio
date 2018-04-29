package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class JobTest {
	Job model;
	Boolean[] bools;
	int[] integers;
	String[] strings;
	
	@Before
	public void setUp() {
		model = new Job();
		bools = new Boolean[3];
		for(int i = 0; i < bools.length; i++) {
			bools[i] = true;
		}
		integers = new int[7];
		for(int i = 0; i < integers.length; i++) {
			integers[i] = i;
		}
		strings = new String[2];
		strings[0] = "place";
		strings[1] = "cah";
		
	}
	
	@Test 
	public void testGets() {
		assertEquals(model.getSigned(), null);
		assertEquals(model.getCourierPaid(), null);
		assertEquals(model.getTsaVerified(), null);
		assertEquals(model.getDeststinationAddress(), null);
		assertEquals(model.getVehicleType(), null);
	}
	
	public void testSets() {
		//INTS
		model.setActualTime(integers[0]);
		model.setDistanceMi(integers[1]);
		model.setDropOffTime(integers[2]);
		//model.setPayActualForJob(integers[3]);
		//model.setPayEstimateForJob(integers[4]);
		model.setPickUpTime(integers[5]);
		//model.setRecipentPhone(integers[6]);
		assertEquals(model.getActualTime(),integers[0]);
		assertEquals(model.getDistanceMi(),integers[1]);
		assertEquals(model.getDropOffTime(),integers[2]);
		//assertEquals(model.getPayActualForJob(),integers[3]);
		//assertEquals(model.getPayEstimateForJob(),integers[4]);
		assertEquals(model.getPickUpTime(),integers[5]);
		//assertEquals(model.getRecipentPhone(),integers[6]);
		
		//BOOLEANS
		//model.setCourierPaid(bools[0]);
		//model.setTsaVerified(bools[1]);
		//assertEquals(model.getCourierPaid(), bools[0]);
		//assertEquals(model.getTsaVerified(), bools[1]);
		
		//STRINGS
		model.setDeststinationAddress(strings[0]);
		//model.setVehicleType(strings[1]);
		assertEquals(model.getDeststinationAddress(), strings[0]);
		assertEquals(model.getVehicleType(), strings[1]);
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
