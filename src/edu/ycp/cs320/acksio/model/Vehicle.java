package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;

public class Vehicle implements DataController{
	//ATTRIBUTES
	private String licensePlate;
	private int year;
	private String make;
	private String model;
	private VehicleType type;
	private boolean active;
	
	//CONSTRUCTORS
	public Vehicle() {
		
	}
	
	public Vehicle(String id) {
		populate(id);
	}
	
	//METHODS
	@Override
	public void populate(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	//SETTERS AND GETTERS
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}