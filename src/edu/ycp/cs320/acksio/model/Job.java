package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;

public class Job implements DataController{
	
	private String destinationAddress;
	private VehicleType vehicleType;
	private Boolean tsaVerified;
<<<<<<< HEAD
	private String recipientName;
	private long recipientPhone;
	private double distanceMi;
	private double payEstimateForJob;
	private double payActualForJob;
=======
	private String recipentName;
	private int recipentPhone;
	private int distanceMi;
	private int payEstimateForJob;
	private int payActualForJob;
>>>>>>> refs/heads/master
	private Boolean courierPaid;
	private int pickUpTime;
	private int dropOffTime;
	private int actualTime;
	private Boolean signed;
	private Boolean approved;
	
	public Job(String destinationAddress, VehicleType vehicleType, boolean tsaVerified, 
				String recipientName, long recipientPhone, double distanceMi, 
				double payEstimateForJob, double payActualForJob, 
				int pickUpTime, int dropOffTime) {
		this.destinationAddress=destinationAddress;
		this.vehicleType=vehicleType;
		this.tsaVerified=tsaVerified;
		this.recipientName=recipientName;
		this.recipientPhone=recipientPhone;
		this.distanceMi=distanceMi;
		this.payEstimateForJob=payEstimateForJob;
		this.payActualForJob=payActualForJob;
		this.pickUpTime=pickUpTime;
		this.dropOffTime=dropOffTime;
		approved=false;
	}
	
	public void setDeststinationAddress (String address) {
		this.destinationAddress = address;
	}
	public void setVehicleType (VehicleType vehicle) {
		this.vehicleType = vehicle; 
	}
	public void setTsaVerified (Boolean tsa) {
		this.tsaVerified = tsa; 
	}
<<<<<<< HEAD
	public void setRecipientName (String name) {
		this.recipientName = name; 
=======
	public void setRecipentName (String name) {
		this.recipentName = name; 
>>>>>>> refs/heads/master
	}
	public void setRecipientPhone (long phone) {
		this.recipientPhone = phone;
	}
	public void setDistanceMi (double distance) {
		this.distanceMi = distance;
	}
	public void setPayEstimateForJob (double pay) {
		this.payEstimateForJob = pay;
	}
	public void setPayActualForJob (double pay) {
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
	public VehicleType getVehicleType () {
		return vehicleType; 
	}
	public Boolean getTsaVerified () {
		return tsaVerified; 
	}
<<<<<<< HEAD
	public String getRecipientName () {
		return recipientName; 
=======
	public String getRecipentName () {
		return recipentName; 
>>>>>>> refs/heads/master
	}
	public long getRecipientPhone () {
		return recipientPhone;
	}
	public double getDistanceMi () {
		return distanceMi;
	}
	public double getPayEstimateForJob () {
		return payEstimateForJob;
	}
	public double getPayActualForJob () {
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
<<<<<<< HEAD
	public boolean getApproved() {
=======
	
	public boolean approvedOnInvoice() {
>>>>>>> refs/heads/master
		// TODO this needs to be implemented. It needs to check if the courier has approved this job on their invoice
		return approved;
	}

}