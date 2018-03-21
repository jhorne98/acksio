package edu.ycp.cs320.acksio.model;

public class Dispatcher extends UserAccount{
	private VehicleType vehicleType;
	private Boolean tsaCert;
	
	public Dispatcher() {
		
	}
	
	public Dispatcher(VehicleType vehicleType, Boolean tsaCert) {
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void Queue() {
		//TODO: Implement
		Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", 7175555555L, 64.9, 53.7, 53.7, 1430, 1730);
		
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
