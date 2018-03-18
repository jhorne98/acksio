package edu.ycp.cs320.acksio.model;

public class Dispatcher extends UserAccount{
	private String vehicleType;
	private Boolean tsaCert;
	
	public Dispatcher() {
		
	}
	
	public Dispatcher(String vehicleType, Boolean tsaCert) {
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	
	public Boolean getTsaCert() {
		return tsaCert;
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
