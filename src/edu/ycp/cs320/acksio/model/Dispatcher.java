
package edu.ycp.cs320.acksio.model;

import java.util.List;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

public class Dispatcher extends UserAccount{
	
	private int dispatcherID;
	private VehicleType vehicleType;
	private Boolean tsaCert;
	private Job testJob; //will go to database when implemented 
	private String address;
	private int phone;
	private List<Job> jobs;
	private List<Courier> couriers;
	
	public Dispatcher(VehicleType vehicleType, Boolean tsaCert, String address, int phone) {
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
		this.address = address;
		this.phone = phone;
	}
	
	public Dispatcher() {
		//Purposefully empty
	}
	
	public Dispatcher(int id) {
		setDispatcherID(id);
		populate(id);
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
		Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", 7175555555L, 64.9, 53.7, 53.7, 1430, 1730);
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
	
	public List<Job> getJobs(){
		return jobs;
	}
	
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	public void setJobs() {
		DerbyDatabase db = new DerbyDatabase();
		jobs = db.jobsFromDispatcherID(dispatcherID);
	}
	
	public List<Courier> getCouriers(){
		return couriers;
	}
	
	public void setCouriers(List<Courier> couriers) {
		this.couriers = couriers;
	}
	
	public void setCouriers() {
		DerbyDatabase db = new DerbyDatabase();
		couriers = db.couriersFromDispatcherID(dispatcherID);
	}
	
	@Override
	public void populate(int id) {
		DerbyDatabase db = new DerbyDatabase();
		Dispatcher hold = db.dispatcherFromID(id);
		if(hold != null) {
			setDispatcherID(hold.getDispatcherID());
			setUserId(hold.getUserId());
			setAddress(hold.getAddress());
			setPhone(hold.getPhone());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType();
			setJobs();
			setCouriers();
		} else {
			throw new NullPointerException();
		}
	}
	
	public void populate(String username) {
		DerbyDatabase db = new DerbyDatabase();
		Dispatcher hold = db.dispatcherFromUsername(username);
		if(hold != null) {
			setDispatcherID(hold.getDispatcherID());
			setUserId(hold.getUserId());
			setAddress(hold.getAddress());
			setPhone(hold.getPhone());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType(hold.getAccountType());
			setJobs();
			setCouriers();
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

	public int getDispatcherID() {
		return dispatcherID;
	}

	public void setDispatcherID(int dispatcherID) {
		this.dispatcherID = dispatcherID;
	}

}
