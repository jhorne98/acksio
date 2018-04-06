
package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;

public class Dispatcher extends UserAccount{
	
	private int dispatcherID;
	private VehicleType vehicleType;
	private Boolean tsaCert;
	private Job testJob; //will go to database when implemented 
	private String address;
	private int phone;
	private double distance;
	
<<<<<<< HEAD
	public Dispatcher() {
		
	}
	
	public Dispatcher(String vehicleType, Boolean tsaCert, String address, String name, int phone, double distance) {
=======
	public Dispatcher(VehicleType vehicleType, Boolean tsaCert, String address, int phone) {
>>>>>>> refs/remotes/origin/master
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
		this.address = address;
		this.phone = phone;
<<<<<<< HEAD
		this.name = name; 
		this.distance = distance;
=======
>>>>>>> refs/remotes/origin/master
	}
	
	public Dispatcher() {
		//Purposefully empty
	}
	
	public Dispatcher(DatabaseProvider provider, int id) {
		setDispatcherID(id);
		populate(provider, id);
	}

	public Dispatcher(boolean tsaCert, String address, String name, int phone) {
		this.tsaCert = tsaCert;
		this.address = address;
		setName(name);
		this.phone = phone;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void Queue() {
		//TODO: Implement
<<<<<<< HEAD
		testJob = new Job();
		testJob.setActualTime(10); //Fixed number for testing
		testJob.setCourierPaid(tsaCert);
		testJob.setDeststinationAddress(address);
		testJob.setDistanceMi(distance); //Fixed number for testing
		testJob.setDropOffTime(10); //Fixed number for testing
		testJob.setPayActualForJob(10); //Fixed number for testing
		testJob.setPayEstimateForJob(10);//Fixed number for testing
		testJob.setPickUpTime(10); //Fixed number for testing
		testJob.setRecipentName(name);
		testJob.setRecipentPhone(phone);
		testJob.setTsaVerified(tsaCert);
		testJob.setVehicleType(vehicleType);
=======
		Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", 7175555555L, 64.9, 53.7, 53.7, 1430, 1730);
>>>>>>> refs/remotes/origin/master
	}
  
	public Boolean getTsaCert() {
		return tsaCert;
	}
	
	public Job getTestJob() {
		return testJob;
	}

	public void setTestJob(Job testJob) {
		this.testJob = testJob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Override
	public void populate(DatabaseProvider provider, int id) {
		Dispatcher hold = provider.getInstance().dispatcherFromID(id);
		if(hold != null) {
			setUserId(hold.getUserId());
			setAddress(hold.getAddress());
			setPhone(hold.getPhone());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void save(DatabaseProvider provider) {
		if(!provider.getInstance().update(this)) 
			provider.getInstance().insert(this);
	}

	public int getDispatcherID() {
		return dispatcherID;
	}

	public void setDispatcherID(int dispatcherID) {
		this.dispatcherID = dispatcherID;
	}

}
