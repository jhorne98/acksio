package edu.ycp.cs320.acksio.model;

import java.util.ArrayList;

import javafx.util.Pair;

public class Courier extends UserAccount{
	
	private int driverNumber;
	private String name;
	private boolean availability;
	private int licenseID;
	private String licenseExp;
	private boolean insured;
	private int[] insuranceCoverage;
	private boolean tsaVerified;
	private double payHistory;
	private double payEstimate;
	private double balance;
	ArrayList<VehicleType> vehicles;
	private double latitude;
	private double longitude;
	
	public Courier(int driverNumber, String name, boolean availability, 
					int licenseID, String licenseExp, boolean insured, 
					int[] insuranceCoverage, boolean tsaVerified, double payHistory, 
					double payEstimate, double balance, ArrayList<VehicleType> vehicles,
					double latitude, double longitude) {
		this.driverNumber=driverNumber;
		this.name=name;
		this.availability=availability;
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

	@Override
	public void populate(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public int getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(int licenseID) {
		this.licenseID = licenseID;
	}

	public String getLicenseExp() {
		return licenseExp;
	}

	public void setLicenseExp(String licenseExp) {
		this.licenseExp = licenseExp;
	}

	public boolean isInsured() {
		return insured;
	}

	public void setInsured(boolean insured) {
		this.insured = insured;
	}

	public int[] getInsuranceCoverage() {
		return insuranceCoverage;
	}

	public void setInsuranceCoverage(int[] insuranceCoverage) {
		this.insuranceCoverage = insuranceCoverage;
	}

	public boolean isTsaVerified() {
		return tsaVerified;
	}

	public void setTsaVerified(boolean tsaVerified) {
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

	public ArrayList<VehicleType> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<VehicleType> vehicles) {
		this.vehicles = vehicles;
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
}