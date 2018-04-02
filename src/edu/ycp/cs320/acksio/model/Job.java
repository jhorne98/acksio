package edu.ycp.cs320.acksio.model;

import edu.ycp.cs320.acksio.controller.DataController;
import edu.ycp.cs320.acksio.persist.DatabaseProvider;

public class Job implements DataController{
	
	private int courierID;
	private int dispatcherID;
	private int jobID;
	private String destinationAddress;
	private VehicleType vehicleType;
	private Boolean tsaVerified;
	private String recipientName;
	private long recipientPhone;
	private double distanceMi;
	private double payEstimateForJob;
	private double payActualForJob;
	private Boolean courierPaid;
	private int pickUpTime;
	private int dropOffTime;
	private int actualTime;
	private Boolean signed; 
	private String id;
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
	
	public Job() {
		
	}
	
	public Job(DatabaseProvider provider, int id) {
		setJobID(id);
		populate(provider, id);
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
	public void setRecipientName (String name) {
		this.recipientName = name; 
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
	public String getRecipientName () {
		return recipientName; 
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public boolean getApproved() {
		return approved;
	}
	@Override
	public void populate(DatabaseProvider provider, int id) {
		Job hold = provider.getInstance().jobFromID(id);
		if(hold != null) {
		//TODO: Assign variables
		} else {
			
		}
	}

	@Override
	public void save(DatabaseProvider provider) {
		if(!provider.getInstance().update(this)) 
			provider.getInstance().insert(this);
	}

	public boolean approvedOnInvoice() {
		// TODO this needs to be implemented. It needs to check if the courier has approved this job on their invoice
		return approved;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

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

}