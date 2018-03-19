package edu.ycp.cs320.acksio.model;

import java.util.List;

public class Courier extends UserAccount{
	//ATTRIBUTES
		private Boolean avalibility;
		private int licenseID;
		private String licenseExpiration;
		private Boolean insured;
		private int insuranceExpiration;
		private int[] insuranceCoverage;
		private Boolean tsaVerified;
		private Double payHistory;
		private Double payEstimate;
		private Double balance;
		private List<Vehicle> vehicles;
		
		//CONSTRUCTORS
		public Courier() {
			
		}
		
		public Courier(String id) {
			populate(id);
		}
		
		//METHODS
		@Override
		public void populate(String id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void save() {
			// TODO Auto-generated method stub
			
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

		//SETTERS AND GETTERS
		public Boolean getAvalibility() {
			return avalibility;
		}

		public void setAvalibility(Boolean avalibility) {
			this.avalibility = avalibility;
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

		public List<Vehicle> getVehicles() {
			return vehicles;
		}

		public void setVehicles(List<Vehicle> vehicles) {
			this.vehicles = vehicles;
		}
}