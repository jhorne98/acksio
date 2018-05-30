/*Created by Joel Horne (jhorne@ycp.edu), Alaska Kiley (dkiley@ycp.edu), and Andrew Georgiou (ageorgiou@ycp.edu)
 at York College of Pennsylvania for CS320.103: Software Engineering
*/
package edu.ycp.cs320.acksio.model;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

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
	private List<Job> jobs;
	private List<Courier> couriers;
	
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
	}/*Created by Joel Horne (jhorne@ycp.edu), Alaska Kiley (dkiley@ycp.edu), and Andrew Georgiou (ageorgiou@ycp.edu)
	 at York College of Pennsylvania for CS320.101: Software Engineering
	*/
	
	public Dispatcher(DatabaseProvider provider, int id) {
		setDispatcherID(id);
		populate(id);
	}

	public Dispatcher(boolean tsaCert, String address, String name, String phone, double distance, double payment) {
		this.tsaCert = tsaCert;
		this.address = address;
		setName(name);
		this.phone = phone;
		this.distance = distance; 
		this.payment = payment; 
	}
	
	public Dispatcher(String username) {
		populate(username);
	}

	public void queue(String address, String name, String phone, double distance, double payment, VehicleType vehicle, int tsaCertified) {
		//TODO: Implement fully
		//Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", 7175555555L, 64.9, 53.7, 53.7, 1430, 1730);
		//Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", 7175555555L, 64.9, 53.7, 53.7, 1430, 1730);
		Job newJob = new Job();
		DerbyDatabase db = new DerbyDatabase();
		newJob.setDispatcherID(dispatcherID);
		newJob.setDeststinationAddress(address);
		newJob.setRecipientName(name);
		newJob.setRecipientPhone(Long.parseLong(phone));
		newJob.setDistanceMi(distance);
		System.out.println(distance);
		newJob.setPayForJob(payment);
		newJob.setVehicleType(vehicle);
		newJob.setTsaVerified(tsaCertified);
		//Everything under is unimplemented parts of the Job class, so they are all fixed. 
		newJob.setApproved(0); 
		newJob.setDestLat(0);
		newJob.setDestLong(0);
		newJob.setActualTime(0);
		newJob.setCourierPaid(0);
		newJob.setSigned(0);
		newJob.setCourierID(0); //Will be set once a courier accepts a job
		Courier tempCourier = new Courier(); 
		newJob.setCourier(tempCourier); //Will be set once a courier accepts a job
		
		System.out.println("Dispatcher: CREATED JOB!");
		//creates new job
		db.insert(newJob);
		System.out.println("Dispatcher: ADDED TO DATABASE!");
		
		//newJob.setCourier(findCourier());
		
		
		
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
	
	public void payCourier() {
		for(Courier courier : couriers) {
			payCourier(courier); 
		}
	}
	
	public boolean payCourier(int JobID) {
		DerbyDatabase db = new DerbyDatabase();
		Job job = db.jobFromID(JobID);
		return payCourier(job);
	}
	
	public boolean payCourier(Job job) {
		DerbyDatabase db = new DerbyDatabase();
		Courier courier = db.courierFromCourierID(job.getCourierID());
		return payCourier(courier, job);
	}
	
	public void payCourier(Courier courier) {
		courier.setJobs();
		for(Job job : courier.getJobs()) {
			payCourier(courier, job);
		}
	}
	
	public boolean payCourier(Courier courier, Job job) {
		if(job.isApproved() && !job.isCourierPaid()) {
			courier.payAmount(job.getPayForJob());
			job.setCourierPaid(1);
			job.save();
			return true;
		}
		return false;
		//Job testJob = new Job("118 oak drive", vehicleType.CAR, true, "Don Hake", 7175555555L, 64.9, 53.7, 53.7, 1430, 1730);
	}
  
	public Boolean getTsaCert() {
		return tsaCert;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
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
	
	public void setCouriers(List<Courier> list) {
		this.couriers = list;
	}
	
	public Courier findCourier() {
		for (int i = 0; i < couriers.size(); i++) {
			Courier search = couriers.get(i);
			double distance = search.getLatitude() + search.getLongitude();//Will change distance to match algorithm later
			
			if (search.getAvailability() == 1 && distance <= 20) { 
				System.out.println("Dispatcher Model: Found courrier " + search.getName() + ".");
				return search; 
			}
		}
		
		System.out.println("Dispatcher Model: No couriers avaliable.");
		return null;
	}
	
	public void setCouriers() {
		DerbyDatabase db = new DerbyDatabase();
		couriers = db.couriersFromDispatcherID(dispatcherID);
		for(Courier courier : couriers) {
			courier.setJobs();
			courier.setUnapprovedJobs();
			courier.setUnpaidJobs();
		}
	}
	
	public List<Courier> getCouriers() {
		return couriers;
	}

	public int getDispatcherID() {
		return dispatcherID;
	}

	public void setDispatcherID(int dispatcherID) {
		this.dispatcherID = dispatcherID;
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
			setAccountType(hold.getAccountType());
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
	public Boolean save() {
		DerbyDatabase db = new DerbyDatabase();
		if (!db.update(this)) {
			return db.insert(this);
		} else {
			return true;
		}
	}

}
