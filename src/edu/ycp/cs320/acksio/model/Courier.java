/*Created by Joel Horne (jhorne@ycp.edu), Alaska Kiley (dkiley@ycp.edu), and Andrew Georgiou (ageorgiou@ycp.edu)
 at York College of Pennsylvania for CS320.103: Software Engineering
*/
package edu.ycp.cs320.acksio.model;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;
//import javafx.util.Pair;

public class Courier extends UserAccount{
	private int courierID;
	private int dispatcherID;
	private Integer availability;
	private int licenseID;
	private String licenseExpiration;
	private Integer insured;
	private int insuranceExpiration;
	private int[] insuranceCoverage;
	private Integer tsaVerified;
	private double payHistory;
	private double payEstimate;
	private double balance;
	private double latitude;
	private double longitude;
	private int unpaidJobs;
	private int unapprovedJobs;
	List<Vehicle> vehicles;
	List<Job> jobs;
	
	public Courier() {
		//Purposefully empty
	}
	
	//Full Constructor with all fields from UserAccount and Courier, lacks unpaidJob and unapprovedJobs, but those are set using the DB
	public Courier(int userId, String username, String password, String email, String name, String accountType, 
			int courierID, int dispatcherID, int availability, int licenseID, String licenseExpiration, int insured, 
			int insuranceExpiration, int[] insuranceCoverage, int tsaVerified, double payHistory, double payEstimate, double balance, 
			double latitude, double longitude, List<Vehicle> vehicles, List<Job> jobs) {
		
		setUserId(userId);
		setName(name);
		setEmail(email);
		setUsername(username);
		setPassword(password);
		setAccountType(accountType);
		setValidity(false);
		this.courierID = courierID;
		this.dispatcherID = dispatcherID;
		this.availability = availability;
		this.licenseID = licenseID;
		this.licenseExpiration = licenseExpiration;
		this.insured = insured;
		this.insuranceExpiration = insuranceExpiration;
		this.insuranceCoverage = insuranceCoverage;
		this.tsaVerified = tsaVerified;
		this.payHistory = payHistory;
		this.payEstimate = payEstimate;
		this.balance = balance;
		this.latitude = latitude;
		this.longitude = longitude;
		this.vehicles = vehicles;
		this.jobs = jobs;
		
	}
	
	//Full Constructor with all fields from Courier, lacks unpaidJob and unapprovedJobs, but those are set using the DB
	public Courier(int courierID, int dispatcherID, int availability, int licenseID, String licenseExpiration, int insured, 
			int insuranceExpiration, int[] insuranceCoverage, int tsaVerified, double payHistory, double payEstimate, double balance, 
			double latitude, double longitude, List<Vehicle> vehicles, List<Job> jobs) {
		
		this.courierID = courierID;
		this.dispatcherID = dispatcherID;
		this.availability = availability;
		this.licenseID = licenseID;
		this.licenseExpiration = licenseExpiration;
		this.insured = insured;
		this.insuranceExpiration = insuranceExpiration;
		this.insuranceCoverage = insuranceCoverage;
		this.tsaVerified = tsaVerified;
		this.payHistory = payHistory;
		this.payEstimate = payEstimate;
		this.balance = balance;
		this.latitude = latitude;
		this.longitude = longitude;
		this.vehicles = vehicles;
		this.jobs = jobs;
		
	}
	
	//Full Constructor with all fields from Courier, does not lack unpaidJob and unapprovedJobs
	public Courier(int courierID, int dispatcherID, int availability, int licenseID, String licenseExpiration, int insured, 
			int insuranceExpiration, int[] insuranceCoverage, int tsaVerified, double payHistory, double payEstimate, double balance, 
			double latitude, double longitude, int unpaidJobs, int unapprovedJobs, List<Vehicle> vehicles, List<Job> jobs) {
		
		this.courierID = courierID;
		this.dispatcherID = dispatcherID;
		this.availability = availability;
		this.licenseID = licenseID;
		this.licenseExpiration = licenseExpiration;
		this.insured = insured;
		this.insuranceExpiration = insuranceExpiration;
		this.insuranceCoverage = insuranceCoverage;
		this.tsaVerified = tsaVerified;
		this.payHistory = payHistory;
		this.payEstimate = payEstimate;
		this.balance = balance;
		this.latitude = latitude;
		this.longitude = longitude;
		this.unpaidJobs = unpaidJobs;
		this.unapprovedJobs = unapprovedJobs;
		this.vehicles = vehicles;
		this.jobs = jobs;
		
	}
	
	//Int based Courier Constructor
	public Courier(Integer tsaVerified) {
		this.tsaVerified = tsaVerified;
	}
	
	//Int based Courier Constructor that pulls from the database based on the courier's USER ID (NOT COURIER ID)
	public Courier(int id, boolean database) {
		populate(id);
	}
		
	//String based Constructor that pulls from the database based on the courier's username
	public Courier(String username) {
		populate(username);
	}

	//METHODS
	@Override
	public void populate(int id) {
		DerbyDatabase db = new DerbyDatabase();
		Courier hold = db.courierFromID(id);
		if(hold != null) {
			setUserId(hold.getUserId());
			setCourierID(hold.getCourierID());
			setDispatcherID(hold.getDispatcherID());
			setTsaVerified(hold.isTsaVerified());
			setLongitude(hold.getLongitude());
			setLatitude(hold.getLatitude());
			setBalance(hold.getBalance());
			setPayEstimate(hold.getPayEstimate());
			setPayHistory(hold.getPayHistory());
			setAvailability(hold.getAvailability());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType(hold.getAccountType());
			setJobs();
			setVehicles();
		} else {
			throw new NullPointerException();
		}
	}
	
	public void populate(String username) {
		DerbyDatabase db = new DerbyDatabase();
		Courier hold = db.courierFromUsername(username);
		if(hold != null) {
			setUserId(hold.getUserId());
			setCourierID(hold.getCourierID());
			setDispatcherID(hold.getDispatcherID());
			setTsaVerified(hold.isTsaVerified());
			setLongitude(hold.getLongitude());
			setLatitude(hold.getLatitude());
			setBalance(hold.getBalance());
			setPayEstimate(hold.getPayEstimate());
			setPayHistory(hold.getPayHistory());
			setAvailability(hold.getAvailability());
			setName(hold.getName());
			setEmail(hold.getEmail());
			setUsername(hold.getUsername());
			setPassword(hold.getPassword());
			setAccountType(hold.getAccountType());
			setJobs();
			setVehicles();
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
			return false;
		}
	}
	
	//Adds a job to the Courier's list of jobs in model and database
	public Boolean acceptJob(Job job) {
		if(job.getCourierID() < 0) {
			job.setCourierID(courierID);
			jobs.add(job);
			job.save();
			payEstimate+=job.getPayForJob();
			return true;
		}
		return false;
	}
	
	//Approves the invoice for job and transfers payment records to match.
	public boolean acceptInvoice(Job job) {
		if(!job.approvedOnInvoice()) {
			job.setApproved(1);
			job.save();
			payEstimate -= job.getPayForJob();
			balance += job.getPayForJob();
			return true;
		}
		return false;
	}
	
	//Returns if all invoices are approved
	public boolean allInvoicesApproved(ArrayList<Job> jobs) {
		for(Job job : jobs) {
			if(!job.approvedOnInvoice()) {
				return false;
			}
		}
		return true;
	}
	
	//Returns if all invoices are approved, but uses the the model's list instead of from somewhere else
	public boolean allInvoicesApproved() {
		for(Job job : jobs) {
			if(!job.approvedOnInvoice()) {
				return false;
			}
		}
		return true;
	}
	
	//Returns if all invoices are approved, but updates the model's list and then uses the the model's list instead of from somewhere else
	public boolean allInvoicesApproved(boolean database) {
		if(database)
			setJobs();
		for(Job job : jobs) {
			if(!job.approvedOnInvoice()) {
				return false;
			}
		}
		return true;
	}
	
	//Accepts all invoices from a list of jobs
	public boolean acceptAllInvoices(ArrayList<Job> jobs) {
		for(Job job : jobs) {
			acceptInvoice(job);
		}
		return allInvoicesApproved(jobs);
	}
	
	//Accepts all invoices from the model's jobs after updating the model
	public boolean acceptAllInvoices(boolean database) {
		if(database)
			setJobs();
		for(Job job : jobs) {
			acceptInvoice(job);
		}
		return allInvoicesApproved((ArrayList<Job>) jobs);
	}
	
	//Accepts all invoices from the model's jobs
	public boolean acceptAllInvoices() {
		for(Job job : jobs) {
			acceptInvoice(job);
		}
		return allInvoicesApproved((ArrayList<Job>) jobs);
	}
	
	public void payAmount(double amount) {
		balance -= amount;
		payHistory += amount;
		save();
	}
	
	//Reset for payHistory and payEstimate from the database
	public Boolean recalculatePayment() {
		payHistory = calculateTotalPayment(true);
		payEstimate = calculateTotalPayment(false);
		return true;
	}
	
	public double calculateTotalPayment() {
		DerbyDatabase db = new DerbyDatabase();
		return calculateTotalPayment(db.jobsFromCourierID(courierID));
	}
	
	public double calculateTotalPayment(boolean approved) {
		DerbyDatabase db = new DerbyDatabase();
		return calculateTotalPayment(db.jobsFromCourierID(courierID), approved);
	}
	
	public double calculateTotalPayment(List<Job> jobs) {
		double total = 0;
		for(Job job : jobs) {
			if(job.getCourierPaid() != 0)
				total+=job.getPayForJob();
		}
		return total;
	}
	
	public double calculateTotalPayment(List<Job> jobs, boolean approved) {
		double total = 0;
		if(approved) {
			for(Job job : jobs) {
				if(job.getCourierPaid() != 0)
					total+=job.getPayForJob();
			}
		} else {
			for(Job job : jobs) {
				if(job.getCourierPaid() == 0)
					total+=job.getPayForJob();
			}
		}
		return total;
	}
	
	public int numberOfUnapprovedInvoices(boolean database) {
		if(database)
			setJobs();
		return numberOfUnapprovedInvoices(jobs);
	}
	
	public int numberOfUnapprovedInvoices() {
		return numberOfUnapprovedInvoices(jobs);
	}
	
	public int numberOfUnapprovedInvoices(List<Job> jobs) {
		int count = 0;
		
		for(Job job : jobs) {
			if(!job.approvedOnInvoice())
				count++;
		}
		
		return count;
	}

	//SETTERS AND GETTERS
	
	public List<Job> getJobs(){
		return jobs;
	}
	
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	public void setJobs() {
		DerbyDatabase db = new DerbyDatabase();
		jobs = db.jobsFromCourierID(courierID);
	}
	
	public void setVehicles() {
		DerbyDatabase db = new DerbyDatabase();
		vehicles = db.vehiclesFromCourierID(courierID);
	}
	
	public void updateVehicles() {
		DerbyDatabase db = new DerbyDatabase();
		for(Vehicle vehicle: vehicles) {
			db.update(vehicle);
		}
	}
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	public void removeVehicle(Vehicle vehicle) {
		DerbyDatabase db = new DerbyDatabase();
		db.remove(vehicle);
	}
	
	public void updateLocation(double latitude, double longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/*
	// just call getLatitude and getLongitude
	public Pair getLocation(){
		Pair ret = new Pair(latitude, longitude);
		return ret;
	}
	*/
	
	public int getCourierID() {
		return courierID;
	}
	
	public void setCourierID(int courierID) {
		this.courierID = courierID;
	}

	public int getDispatcherID() {
		return dispatcherID;
	}

	public void setDispatcherID(int dispatcherID) {
		this.dispatcherID = dispatcherID;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public int getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(int licenseID) {
		this.licenseID = licenseID;
	}

	public String getLicenseExpiration() {
		return licenseExpiration;
	}

	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	public Integer getInsured() {
		return insured;
	}

	public void setInsured(Integer insured) {
		this.insured = insured;
	}

	public int getInsuranceExpiration() {
		return insuranceExpiration;
	}

	public void setInsuranceExpiration(int insuranceExpiration) {
		this.insuranceExpiration = insuranceExpiration;
	}

	public int[] getInsuranceCoverage() {
		return insuranceCoverage;
	}

	public void setInsuranceCoverage(int[] insuranceCoverage) {
		this.insuranceCoverage = insuranceCoverage;
	}

	public Integer isTsaVerified() {
		return tsaVerified;
	}

	public void setTsaVerified(Integer tsaVerified) {
		this.tsaVerified = tsaVerified;
	}

	public double getPayHistory() {
		return payHistory;
	}

	public void setPayHistory(double payHistory) {
		this.payHistory = payHistory;
	}

	public double getPayEstimate() {
		return payEstimate;
	}

	public void setPayEstimate(double payEstimate) {
		this.payEstimate = payEstimate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUnpaidJobs() {
		return unpaidJobs;
	}

	public void setUnpaidJobs(int uppaidJobs) {
		this.unpaidJobs = uppaidJobs;
	}

	public void setUnpaidJobs() {
		unpaidJobs = 0;
		for(Job job : jobs) {
			if(job.isApproved() && !job.isCourierPaid())
				unpaidJobs++;
		}
	}

	public int getUnapprovedJobs() {
		return unapprovedJobs;
	}

	public void setUnapprovedJobs(int unapprovedJobs) {
		this.unapprovedJobs = unapprovedJobs;
	}
	
	public void setUnapprovedJobs() {
		unapprovedJobs = 0;
		for(Job job : jobs) {
			if(!job.isApproved())
				unapprovedJobs ++;
				
		}
	}


	
}