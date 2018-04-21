package edu.ycp.cs320.asksio.persist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.*;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

public class DerbyDatabaseTest {
	private DerbyDatabase db;
	private UserAccount user;
	private Courier courier;
	private Dispatcher dispatcher;
	private Job job;
	private Vehicle vehicle;
	
	@Before
	public void setUp() {
		System.out.println("Set up for DerbyDatabase Tests.");
	}
	
	@Test
	public void test() {
		
	}
}