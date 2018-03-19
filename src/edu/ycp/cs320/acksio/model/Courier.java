package edu.ycp.cs320.acksio.model;

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
	
	
	
	
	public Courier(int driverNumner, String name, boolean availability, int licenceID, String licenseExp, boolean insured, int[] insuranceCoverage, boolean tsaVeriflied, double payHistory, double payEstimate, double balance) {
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