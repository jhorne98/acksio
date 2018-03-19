package edu.ycp.cs320.acksio.model;

public class Dispatcher extends UserAccount{
	private String vehicleType;
	private Boolean tsaCert;
	private Job testJob; //will go to database when implemented 
	private String address;
	private String name;
	private int phone;
	
	public Dispatcher() {
		
	}
	
	public Dispatcher(String vehicleType, Boolean tsaCert, String address, String name, int phone) {
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
		this.address = address;
		this.phone = phone;
		this.name = name; 
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void Queue() {
		//TODO: Implement
		testJob = new Job();
		testJob.setActualTime(10); //Fixed number for testing
		testJob.setCourierPaid(tsaCert);
		testJob.setDeststinationAddress(address);
		testJob.setDistanceMi(10); //Fixed number for testing
		testJob.setDropOffTime(10); //Fixed number for testing
		testJob.setPayActualForJob(10); //Fixed number for testing
		testJob.setPayEstimateForJob(10);//Fixed number for testing
		testJob.setPickUpTime(10); //Fixed number for testing
		testJob.setRecipentName(name);
		testJob.setRecipentPhone(phone);
		testJob.setTsaVerified(tsaCert);
		testJob.setVehicleType(vehicleType);
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
