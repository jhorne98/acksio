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
	List<Vehicle> vehicles;
	List<Job> jobs;

	/* 
	public Courier(int driverNumber, String name, boolean availability, 
					int licenseID, String licenseExp, boolean insured, 
					int[] insuranceCoverage, boolean tsaVerified, double payHistory, 
					double payEstimate, double balance, ArrayList<VehicleType> vehicles,
					double latitude, double longitude) {
	
		this.driverNumber=driverNumber;
		this.setName(name);
		this.setAvailability(availability);
		this.licenseID=licenseID;
		this.insured=insured;
		this.insuranceCoverage=insuranceCoverage;
		this.tsaVerified=tsaVerified;
		this.payHistory=payHistory;
		this.payEstimate=payEstimate;
		this.balance=balance;
		this.vehicles=vehicles;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	*/
	
	public Courier() {
		//Purposefully empty
	}
	
	public Courier(DatabaseProvider provider, int id) {
		setCourierID(id);
		populate(id);
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
	public void save() {
		DerbyDatabase db = new DerbyDatabase();
		if(!db.update(this)) 
			db.insert(this);
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
	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
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

	
}