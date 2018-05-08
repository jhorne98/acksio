package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DispatcherTest {
	private Dispatcher model;
	
	@Before
	public void setUp() {
		model = new Dispatcher();
		
		model.setAddress("11313 Address Rd.");
		model.setDispatcherID(2);
		model.setPayment(2.0);
		model.setPhone("7177495979");
	}
	
	@Test
	public void testSets() {
		assertEquals("11313 Address Rd.", model.getAddress());
		assertEquals(2, model.getDispatcherID());
		assertEquals(2.0, model.getPayment(), 0.01);
		assertEquals("7177495979", model.getPhone());
	}
	
	@Test
	public void testQueue() {
		
	}
	
	@Test
	public void testPay() {
		
	}
}
