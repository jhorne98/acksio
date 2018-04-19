package edu.ycp.cs320.asksio.persist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.FakeDatabase;

public class FakeDatabaseTest {
	private FakeDatabase db;
	private UserAccount user;
	private Courier courier;
	private Dispatcher dispatcher;
	private Job job;
	private Vehicle vehicle;
	
	@Before
	public void setUp() {
		System.out.println("Set up for FakeDatabase Tests.");
	}
	
	@Test
	public void test() {
		
	}
}
