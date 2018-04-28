package edu.ycp.cs320.acksio.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acksio.model.Vehicle;

public class VehicleTest {
	private Vehicle vehicleModel;
	//private Courier courierModel;
	//private Dispatcher dispatcherModel;
	
	@Before
	public void setUp() {
		vehicleModel = new Vehicle(1, "REM1516", 2004, "Toyota", "Prius", VehicleType.CAR);
		vehicleModel.setVehicleID(1);
		vehicleModel.setActive(0);
	}
	
	@Test 
	public void testGets() {
		assertTrue(vehicleModel.getVehicleID() == 1);
		assertTrue(vehicleModel.getCourierID() == 1);
		assertTrue(vehicleModel.getLicensePlate() == "REM1516");
		assertTrue(vehicleModel.getYear() == 2004);
		assertTrue(vehicleModel.getMake() == "Toyota");
		assertTrue(vehicleModel.getModel() == "Prius");
		assertTrue(vehicleModel.getType() == VehicleType.CAR);
		assertTrue(vehicleModel.isActive() == 0);
	}
	
	@Test
	public void testSets() {
		vehicleModel.setVehicleID(2);
		assertTrue(vehicleModel.getVehicleID() == 2);
		
		vehicleModel.setCourierID(2);
		assertTrue(vehicleModel.getCourierID() == 2);
		
		vehicleModel.setLicensePlate("R34DCK");
		assertTrue(vehicleModel.getLicensePlate() == "R34DCK");
		
		vehicleModel.setYear(2005);
		assertTrue(vehicleModel.getYear() == 2005);
		
		vehicleModel.setMake("Dodge");
		assertTrue(vehicleModel.getMake() == "Dodge");
		
		vehicleModel.setModel("Dakota");
		assertTrue(vehicleModel.getModel() == "Dakota");
		
		vehicleModel.setType(VehicleType.PICKUP);
		assertTrue(vehicleModel.getType() == VehicleType.PICKUP);
		
		vehicleModel.setActive(1);
		assertTrue(vehicleModel.isActive() == 1);
	}
}
