
package edu.ycp.cs320.acksio.model;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;

public class Dispatcher extends UserAccount{
	
	private int dispatcherID;
	private VehicleType vehicleType;
	private Boolean tsaCert;
	private Job testJob; //will go to database when implemented 
	private String address;
	private String phone;
	private double distance;
	public String name; 
	public double payment; 
	private Courier courier; 
	private ArrayList <Job> jobs;
	private ArrayList <Courier> couriers;
	
/*
	public Dispatcher() {
		
	}*/
	
	public Dispatcher(VehicleType vehicleType, Boolean tsaCert, String address, String name, String phone, double distance) {

		this.vehicleType = vehicleType;
		this.tsaCert = tsaCert;
		this.address = address;
		this.phone = phone;
		//this.name = name; 
		this.distance = distance;

	}
	
	public Dispatcher() {
		//Purposefully empty
	}
	
	public Dispatcher(DatabaseProvider provider, int id) {
		setDispatcherID(id);
		populate(provider, id);
	}

	public Dispatcher(boolean tsaCert, String address, String name, String phone, double distance, double payment) {
		this.tsaCert = tsaCert;
		this.address = address;
		setName(name);
		this.phone = phone;
		this.distance = distance; 
		this.payment = payment; 
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void Queue(String address, String name, String phone, double distance, double payment) {
		//TODO: Implement
		Job newJob = new Job();
		newJob.setDeststinationAddress(address);
		newJob.setRecipientName(name);
		newJob.setRecipientPhone(phone);
		newJob.setDistanceMi(distance);
		newJob.setPayEstimateForJob(payment);
		newJob.setCourier(findCourier());
		
		
		
		/*testJob = new Job();
		testJob.setActualTime(10); //Fixed number for testing
		testJob.setCourierPaid(tsaCert);
		testJob.setDeststinationAddress(address);
		testJob.setDistanceMi(distance); //Fixed number for testing
		testJob.setDropOffTime(10); //Fixed number for testing
		testJob.setPayActualForJob(10); //Fixed number for testing
		testJob.setPayEstimateForJob(10);//Fixed number for testing
		testJob.setPickUpTime(10); //Fixed number for testing
		testJob.setRecipientName("John Doe");
		testJob.setTsaVerified(tsaCert);
		testJob.setRecipientPhone(phone.toString());
		testJob.setVehicleType(vehicleType);*/
		// 		//Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", "7175555555L", 64.9, 53.7, 53.7, 1430, 1730);

		Job testJob =  new Job(); 
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

	public String getPhone() {
		//return Integer.parseInt(phone);
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getDistance() {
		return distance; 
	}
	
	public void setDistance(double distance) {
		this.distance = distance * 1.6; // convert from kilometers to miles 
	}
	
	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	public double getPayment() {
		return payment;
	}
	
	public ArrayList <Courier> getCouriers(){
		return couriers;
	}
	
	public void setCouriers(ArrayList<Courier> couriers) {
		this.couriers = couriers;
}
	public Courier findCourier() {
		for (int i = 0; i < couriers.size(); i++) {
			Courier search = couriers.get(i);
			double distance = search.getLatitude() + search.getLongitude();//Will change distance to match algorithm later
			
			if (search.getAvailability() == true && distance <= 20) { 
				System.out.println("Dispatcher Model: Found courrier " + search.getName() + ".");
				return search; 
			}
		}
		
		System.out.println("Dispatcher Model: No couriers avaliable.");
		return null;
		
		
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
			setDistance(hold.getDistance());
			setPayment(hold.getPayment());
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
