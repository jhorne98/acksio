package edu.ycp.cs320.acksio.model;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import javafx.util.Pair;

public class Courier extends UserAccount{
	
	private int courierID;
	private int driverNumber;
	private String name;
	private Boolean availability;
	private int licenseID;
	private String licenseExpiration;
	private Boolean insured;
	private int insuranceExpiration;
	private int[] insuranceCoverage;
	private boolean tsaVerified;
	private double payHistory;
	private double payEstimate;
	private double balance;
	ArrayList<VehicleType> vehicles;
	private double latitude;
	private double longitude;
	List<Vehicle> vehicle;

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
	
	public Courier() {
		
	}
	
	public Courier(DatabaseProvider provider, int id) {
		setCourierID(id);
		populate(provider, id);
	}

		
	//METHODS
	@Override
	public void populate(DatabaseProvider provider, int id) {
		Courier hold = provider.getInstance().courierFromID(id);
		//TODO: Assign variables
	}

	@Override
	public void save(DatabaseProvider provider) {
		if(!provider.getInstance().update(this)) 
			provider.getInstance().insert(this);
	}
	
	public void addVehicle() {
		//TODO: Implement
	}
	
	public Boolean acceptJob(Job job) {
		//TODO: Implement
		return false;
	}
	
	public void updateLocation() {
		//TODO: Implement
	}
	
	public double calculateTotalPayment() {
		//TODO: Implement
		return 0.0;
	}
	
	public boolean acceptInvoice(ArrayList<Job> jobs) {
		
		int count=0;
		for(Job job : jobs) {
			boolean accepted = job.getApproved();
			if(accepted=true) {
				count++;
			}
		}
		if(count==jobs.size()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public double calculateTotalPayment(ArrayList<Job> jobs) {
		double total = 0;
		for(Job job : jobs) {
			total+=job.getPayActualForJob();
		}
		return total;
	}

	//SETTERS AND GETTERS
	
	public void addVehicle(VehicleType vehicle) {
		vehicles.add(vehicle);
	}
	
	public void updateLocation(double latitude, double longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
	}
	
	public Pair<Double, Double> getLocation(){
		Pair<Double, Double> pair = new Pair<Double, Double>(latitude, longitude);
		return pair;
	}
	
	public int getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(int driverNumber) {
		this.driverNumber = driverNumber;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getLicenseExp() {
		return licenseExpiration;
	}

	public void setLicenseExp(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	public boolean isInsured() {
		return insured;
	}

	public void setInsured(boolean insured) {
		this.insured = insured;
	}

	public boolean isTsaVerified() {
		return tsaVerified;
	}

	public void setTsaVerified(boolean tsaVerified) {
		this.tsaVerified = tsaVerified;
	}

	public void setPayHistory(double payHistory) {
		this.payHistory = payHistory;
	}

	public void setPayEstimate(double payEstimate) {
		this.payEstimate = payEstimate;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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

	public Boolean getInsured() {
		return insured;
	}

	public void setInsured(Boolean insured) {
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

	public Boolean getTsaVerified() {
		return tsaVerified;
	}

	public void setTsaVerified(Boolean tsaVerified) {
		this.tsaVerified = tsaVerified;
	}

	public Double getPayHistory() {
		return payHistory;
	}

	public void setPayHistory(Double payHistory) {
		this.payHistory = payHistory;
	}

	public Double getPayEstimate() {
		return payEstimate;
	}

	public void setPayEstimate(Double payEstimate) {
		this.payEstimate = payEstimate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ArrayList<VehicleType> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<VehicleType> vehicles) {
		this.vehicles = vehicles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public int getCourierID() {
		return courierID;
	}

	public void setCourierID(int courierID) {
		this.courierID = courierID;
	}
}