package edu.ycp.cs320.acksio.controller;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.controller.NumbersController;
import edu.ycp.cs320.acksio.model.Numbers;

public class NumbersControllerTest {
	private Numbers model;
	private NumbersController control;
	private Double first = 2.0;
	private Double second = 3.0;
	private Double third = 4.0;
	
	
	@Before
	public void setUp() {
		model = new Numbers(first, second, third);
		control = new NumbersController(model);
	}
	
	@Test
	public void testAdds() {
		assertTrue(control.add(first, second) == first + second);
		assertTrue(control.add(second, third) == second + third);
		assertTrue(control.add(first, third) == first + third);
		
		assertTrue(control.add(first, second, third) == first + second + third);
		
		control.add();
		assertTrue(model.getResult() == first + second + third);
		
		model.setThird(null);
		control.add();
		assertTrue(model.getResult() == first + second);
	}
	
	public void testMultiplys() {
		assertTrue(control.multiply(first, second) == first + second);
		assertTrue(control.multiply(second, third) == second + third);
		assertTrue(control.multiply(first, third) == first + third);
		
		assertTrue(control.multiply(first, second, third) == first + second + third);
		
		control.multiply();
		assertTrue(model.getResult() == first + second + third);
		
		model.setThird(null);
		control.multiply();
		assertTrue(model.getResult() == first + second);
	}
}
