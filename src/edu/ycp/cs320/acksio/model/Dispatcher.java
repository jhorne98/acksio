package edu.ycp.cs320.acksio.model;

public class Dispatcher extends UserAccount{
	private String vehicleType;
	private Boolean tsaCert;
	private Job testJob; //To test Queue() 
	
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
		testJob = new Job();
		testJob.setActualTime(time);
		testJob.setCourierPaid(paid);
		testJob.setDeststinationAddress(address);
		testJob.setDistanceMi(distance);
		testJob.setDropOffTime(time);
		testJob.setPayActualForJob(pay);
		testJob.setPayEstimateForJob(pay);
		testJob.setPickUpTime(time);
		testJob.setPayActualForJob(pay);
		testJob.setPickUpTime(time);
		testJob.setRecipentName(name);
		testJob.setRecipentPhone(phone);
		testJob.setTsaVerified(tsa);
		testJob.setVehicleType(vehicle);
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
