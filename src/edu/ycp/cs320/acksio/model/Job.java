package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;

public class Job implements DataController{
	
	private String destinationAddress;
	private String vehicleType;
	private Boolean tsaVerified;
	private int recipentName;
	private int recipentPhone;
	private int distanceMi;
	private int payEstimateForJob;
	private int payActualForJob;
	private Boolean courierPaid;
	private int pickUpTime;
	private int dropOffTime;
	private int actualTime;
	private Boolean signed; 
	
	public void setDeststinationAddress (String address) {
		this.destinationAddress = address;
	}
	public void setVehicleType (String vehicle) {
		this.vehicleType = vehicle; 
	}
	public void setTsaVerified (Boolean tsa) {
		this.tsaVerified = tsa; 
	}
	public void setRecipentName (int name) {
		this.recipentName = name; 
	}
	public void setRecipentPhone (int phone) {
		this.recipentPhone = phone;
	}
	public void setDistanceMi (int distance) {
		this.distanceMi = distance;
	}
	public void setPayEstimateForJob (int pay) {
		this.payEstimateForJob = pay;
	}
	public void setPayActualForJob (int pay) {
		this.payActualForJob = pay; 
	}
	public void setCourierPaid  (Boolean paid) {
		this.courierPaid = paid;
	}
	public void setPickUpTime (int time) {
		this.pickUpTime = time; 
	}
	public void setDropOffTime (int time) {
		this.dropOffTime = time; 
	}
	public void setActualTime (int time) {
		this.actualTime = time; 
	}
	public void signed (Boolean signed) {
		this.signed = signed; 
	}
	public String getDeststinationAddress () {
		return destinationAddress;
	}
	public String getVehicleType () {
		return vehicleType; 
	}
	public Boolean setTsaVerified () {
		return tsaVerified; 
	}
	public int getRecipentName () {
		return recipentName; 
	}
	public int getRecipentPhone () {
		return recipentPhone;
	}
	public int getDistanceMi () {
		return distanceMi;
	}
	public int getPayEstimateForJob () {
		return payEstimateForJob;
	}
	public int getPayActualForJob () {
		return payActualForJob; 
	}
	public Boolean getCourierPaid  () {
		return courierPaid;
	}
	public int getPickUpTime () {
		return pickUpTime; 
	}
	public int getDropOffTime () {
		return dropOffTime; 
	}
	public int getActualTime () {
		return actualTime; 
	}
	public Boolean getSigned () {
		return signed; 
	}
	
	public void signOff() {
		// TODO: Implement
	}
	public void close(){
		
	}
	public void findRoute() {
		// TODO: Implement (Need Google Maps API)
	}
	public Courier findNearestCourier() {
		// TODO: Implement (Need Google Maps API)
		
		return null;
		
	}
	public double calculatePayment(double payment) {
		// TODO: Impelment
		
		return payment;
		
	}
	@Override
	public void populate(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}