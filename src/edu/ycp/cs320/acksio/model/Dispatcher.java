
package edu.ycp.cs320.acksio.model;

public class Dispatcher extends UserAccount{
	private int dispatcherID;
	private VehicleType vehicleType;
	private Boolean tsaCert;
	private Job testJob; //will go to database when implemented 
	private String address;
	private String name;
	private int phone;
	
	public Dispatcher(VehicleType vehicleType, Boolean tsaCert, String address, String name, int phone) {
		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
		this.address = address;
		this.phone = phone;
		this.name = name; 
	}
	
	public Dispatcher() {
		
	}
	
	public Dispatcher(int id) {
		populate(id);
	}
	
	public Dispatcher(String parameter, boolean tsaCert2, String parameter2, String parameter3, int intFromParameter) {
		// TODO Auto-generated constructor stub
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Override
	public void populate(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
