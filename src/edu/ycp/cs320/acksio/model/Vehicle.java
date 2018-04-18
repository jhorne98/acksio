package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

public class Vehicle implements DataController{
	//ATTRIBUTES
	private int courierID;
	private int vehicleID;
	private String licensePlate;
	private int year;
	private String make;
	private String model;
	private VehicleType type;
	private boolean active;
	
	//CONSTRUCTORS
	public Vehicle() {
		//Purposefully empty
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
			setActive(hold.isActive());//Active
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void save() {
		DerbyDatabase db = new DerbyDatabase();
		if(!db.update(this)) 
			db.insert(this);
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
	
	public void setType (String vehicle) {
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
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public int getCourierID() {
		return courierID;
	}

	public void setCourierID(int courierID) {
		this.courierID = courierID;
	}
}