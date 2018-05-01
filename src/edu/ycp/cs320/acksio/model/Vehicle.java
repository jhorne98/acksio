package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

public class Vehicle implements DataController{
	//ATTRIBUTES
	private Integer vehicleID;
	private Integer courierID;
	private String licensePlate;
	private Integer year;
	private String make;
	private String model;
	private VehicleType type;
	private Integer active;
	
	//CONSTRUCTORS
	public Vehicle() {
		//Purposefully empty
	}
	
	public Vehicle(Integer courierID, String licensePlate, Integer year, String make, String model, VehicleType type) {
		this.courierID = courierID;
		this.licensePlate = licensePlate;
		this.year = year;
		this.make = make;
		this.model = model;
		this.type = type;
	}
	
	public Vehicle(int id) {
		setVehicleID(id);
		populate(id);
	}
	
	//METHODS
	@Override
	public void populate(int id) {
		DerbyDatabase db = new DerbyDatabase();
		Vehicle hold = db.vehicleFromID(id);
		if(hold != null) {
			setCourierID(hold.getCourierID());//CourierID
			setType(hold.getType());
			setLicensePlate(hold.getLicensePlate());//LicensePlate
			setMake(hold.getMake());//Make
			setModel(hold.getModel());//Model
			setYear(hold.getYear());//Year
			setActive(hold.getActive());//Active
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public Boolean save() {
		DerbyDatabase db = new DerbyDatabase();
		if(!db.update(this)) 
			return db.insert(this);
		return true;
	}
	
	//SETTERS AND GETTERS
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
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
	
	
	public void setType(String vehicle) {
		/*
		if(vehicle.equals(VehicleType.BICYCLE.toString())) {
			type = VehicleType.BICYCLE;
		} else if(vehicle.equals(VehicleType.MOTORCYCLE.toString())) {
			type = VehicleType.MOTORCYCLE;
		} else if(vehicle.equals(VehicleType.CAR.toString())) {
			type = VehicleType.CAR;
		} else if(vehicle.equals(VehicleType.SUV.toString())) {
			type = VehicleType.SUV;
		} else if(vehicle.equals(VehicleType.VAN.toString())) {
			type = VehicleType.VAN;
		} else if(vehicle.equals(VehicleType.PICKUP.toString())) {
			type = VehicleType.PICKUP;
		} else if(vehicle.equals(VehicleType.SPRINTER.toString())) {
			type = VehicleType.SPRINTER;
		} else if(vehicle.equals(VehicleType.SEMI.toString())) {
			type = VehicleType.SEMI;
		} else {
			type = null;
		}
		*/
		
		vehicle = vehicle.toUpperCase();
		
		type = VehicleType.valueOf(vehicle);
	}
	
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(Integer vehicleID) {
		this.vehicleID = vehicleID;
	}

	public Integer getCourierID() {
		return courierID;
	}

	public void setCourierID(Integer courierID) {
		this.courierID = courierID;
	}
}