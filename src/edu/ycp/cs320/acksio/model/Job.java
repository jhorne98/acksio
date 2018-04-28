package edu.ycp.cs320.acksio.model;

import java.util.List;

import edu.ycp.cs320.acksio.controller.DataController;
import edu.ycp.cs320.acksio.persist.DatabaseProvider;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;

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
	private double payForJob;
	private Boolean courierPaid;
	private int pickUpTime;
	private int dropOffTime;
	private int actualTime;
	private Boolean signed; 
	private String id;
	private Boolean approved;
	private double destLat;
	private double destLong;
	
	public Job(String destinationAddress, VehicleType vehicleType, boolean tsaVerified, 
				String recipientName, long recipientPhone, double distanceMi, 
				double pay, 
				int pickUpTime, int dropOffTime) {
		this.destinationAddress=destinationAddress;
		this.vehicleType=vehicleType;
		this.tsaVerified=tsaVerified;
		this.recipientName=recipientName;
		this.recipientPhone=recipientPhone;
		this.distanceMi=distanceMi;
		this.setPayForJob(pay);
		this.pickUpTime=pickUpTime;
		this.dropOffTime=dropOffTime;
		approved=false;
	}
	
	public Job() {
		//Purposefully empty
	}
	
	public Job(int id) {
		setJobID(id);
		populate(id);
	}
	
	public void setDeststinationAddress (String address) {
		this.destinationAddress = address;
	}
	public void setVehicleType (VehicleType vehicle) {
		this.vehicleType = vehicle; 
	}
	public void setVehicleType (String vehicle) {
		if(vehicle.equals(VehicleType.BICYCLE.toString())) {
			vehicleType = VehicleType.BICYCLE;
		} else if(vehicle.equals(VehicleType.MOTORCYCLE.toString())) {
			vehicleType = VehicleType.MOTORCYCLE;
		} else if(vehicle.equals(VehicleType.CAR.toString())) {
			vehicleType = VehicleType.CAR;
		} else if(vehicle.equals(VehicleType.SUV.toString())) {
			vehicleType = VehicleType.SUV;
		} else if(vehicle.equals(VehicleType.VAN.toString())) {
			vehicleType = VehicleType.VAN;
		} else if(vehicle.equals(VehicleType.PICKUP.toString())) {
			vehicleType = VehicleType.PICKUP;
		} else if(vehicle.equals(VehicleType.SPRINTER.toString())) {
			vehicleType = VehicleType.SPRINTER;
		} else if(vehicle.equals(VehicleType.SEMI.toString())) {
			vehicleType = VehicleType.SEMI;
		} else {
			vehicleType = null;
		}
	}
	public void setTsaVerified (Boolean tsa) {
		this.tsaVerified = tsa; 
	}
	public void setRecipientName (String name) {
		this.recipientName = name; 
	}
	public void setRecipientPhone (long recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	public void setDistanceMi (double distanceMi) {
		this.distanceMi = distanceMi;
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
	
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean getApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
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

	public double getDestLat() {
		return destLat;
	}

	public void setDestLat(double destLat) {
		this.destLat = destLat;
	}

	public double getDestLong() {
		return destLong;
	}

	public void setDestLong(double destLong) {
		this.destLong = destLong;
	}
	
	public double getPayForJob() {
		return payForJob;
	}

	public void setPayForJob(double payForJob) {
		this.payForJob = payForJob;
	}

	@Override
	public void populate(int id) {
		DerbyDatabase db = new DerbyDatabase();
		Job hold = db.jobFromID(id);
		if(hold != null) {
			setCourierID(hold.getCourierID());
			setDispatcherID(hold.getDispatcherID());
			setDestLat(hold.getDestLat());
			setDestLong(hold.getDestLong());
			setVehicleType(hold.getVehicleType());//VehicleType
			setTsaVerified(hold.getTsaVerified());
			setRecipientName(hold.getRecipientName());
			setRecipientPhone(hold.getRecipientPhone());
			setDistanceMi(hold.getDistanceMi()); //Distance
			setCourierPaid(hold.getCourierPaid()); //CourierPaid
			setPickUpTime(hold.getPickUpTime()); //PickUpTime
			setDropOffTime(hold.getDropOffTime()); //DropOffTime
			setActualTime(hold.getActualTime()); //TimeForJob
			setSigned(hold.getSigned()); //PackageSignedFor
			setApproved(hold.getApproved()); //InvoiceApproved
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
		// TODO: Implement
		
		return payment;
		
	}
	
	public boolean approvedOnInvoice() {
		// TODO this needs to be implemented. It needs to check if the courier has approved this job on their invoice
		return false;
	}

}