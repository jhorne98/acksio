package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.Numbers;

public class NumbersTest {
	private Numbers model;
	private Double first = 2.0;
	private Double second = 3.0;
	private Double third = 4.0;
	
	
	@Before
	public void setUp() {
		model = new Numbers(first, second, third);
	}
	
	@Test 
	public void testGets() {
		assertEquals(first, model.getFirst());
		assertEquals(second, model.getSecond());
		assertEquals(third, model.getThird());
	}
	
	public void testSets() {
		model.setFirst(null);
		model.setSecond(null);
		model.setThird(null);
		model.setResult(first + second + third);
		Double result = first + second + third;
		
		assertEquals(null, model.getFirst());
		assertEquals(null, model.getSecond());
		assertEquals(null, model.getThird());
		assertEquals(result, model.getResult());
	}
}
