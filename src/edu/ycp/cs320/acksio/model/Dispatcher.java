package edu.ycp.cs320.acksio.model;

public class Dispatcher extends UserAccount{
	private String vehicleType;
	private Boolean tsaCert;
	
	public Dispatcher() {
		save(); 
	}
	
	public Dispatcher(String vehicleType, Boolean tsaCert) {
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
		// Not going to remove this yet... but what is this for? - Andrew
	}
	
	public String getVehicleType() {
		return vehicleType;
		// same here... 
	}
	
	public void Queue() {
		//TODO: Implement
	}
	
	public void pay (String userName) {
		//TODO: Implement
	}
	
	@Override
	public void populate(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
