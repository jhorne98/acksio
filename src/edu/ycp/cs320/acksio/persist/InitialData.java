package edu.ycp.cs320.acksio.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.acksio.model.Courier;
import edu.ycp.cs320.acksio.model.Dispatcher;
import edu.ycp.cs320.acksio.model.Job;
import edu.ycp.cs320.acksio.model.UserAccount;
import edu.ycp.cs320.acksio.model.Vehicle;
import edu.ycp.cs320.acksio.persist.ReadCSV;

public class InitialData {
	public static List<UserAccount> getUsers() throws IOException {
		List<UserAccount> userList = new ArrayList<UserAccount>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				UserAccount user = new UserAccount();
				user.setUserId(userId++);				
				user.setUsername(i.next());
				user.setPassword(i.next());
				user.setEmail(i.next());
				user.setName(i.next());
				userList.add(user);
			}
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
 	public static List<Dispatcher> getDispatchers() throws IOException {
		List<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
		ReadCSV readDispatchers = new ReadCSV("dispatchers.csv");
		try {
			// auto-generated primary key for authors table
			Integer dispatcherId = 1;
			while (true) {
				List<String> tuple = readDispatchers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Dispatcher user = new Dispatcher();
				
				user.setDispatcherID(dispatcherId++);
				user.setUserId(Integer.parseInt(i.next()));
				user.setAddress(i.next());
				user.setPhone(Integer.parseInt(i.next()));
				
				dispatcherList.add(user);
			}
			return dispatcherList;
		} finally {
			readDispatchers.close();
		}
	}
	
 	public static List<Courier> getCouriers() throws IOException {
		List<Courier> courierList = new ArrayList<Courier>();
		ReadCSV readCouriers = new ReadCSV("couriers.csv");
		try {
			// auto-generated primary key for authors table
			Integer courierId = 1;
			while (true) {
				List<String> tuple = readCouriers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Courier user = new Courier();
				
				user.setCourierID(courierId++);
				user.setUserId(Integer.parseInt(i.next()));
				user.setDispatcherID(Integer.parseInt(i.next()));
				user.setTsaVerified(Boolean.parseBoolean(i.next()));
				user.setLongitude(Double.parseDouble(i.next()));
				user.setLatitude(Double.parseDouble(i.next()));
				user.setBalance(Double.parseDouble(i.next()));
				user.setPayEstimate(Double.parseDouble(i.next()));
				user.setPayHistory(Double.parseDouble(i.next()));
				user.setAvailability(Boolean.parseBoolean(i.next()));
				
				courierList.add(user);
			}
			return courierList;
		} finally {
			readCouriers.close();
		}
	}
	
 	public static List<Job> getJobs() throws IOException {
		List<Job> jobList = new ArrayList<Job>();
		ReadCSV readJobs = new ReadCSV("jobs.csv");
		try {
			// auto-generated primary key for authors table
			Integer jobId = 1;
			while (true) {
				List<String> tuple = readJobs.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Job job = new Job();
				job.setJobID(jobId++);
				//CourierID
				//DispatcherID
				//DestLat
				//DestLong
				//VehicleType
				//TSACert
				//RecipientName
				//RecipientPhone
				//Distance
				//CourierPaid
				//PickUpTime
				//DropOffTime
				//TimeForJob
				//PackageSignedFor
				//InvoiceApproved
				
				jobList.add(job);
			}
			return jobList;
		} finally {
			readJobs.close();
		}
	}
 	
 	public static List<Vehicle> getVehicles() throws IOException {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		ReadCSV readVehicles = new ReadCSV("vehicles.csv");
		try {
			// auto-generated primary key for authors table
			Integer vehicleId = 1;
			while (true) {
				List<String> tuple = readVehicles.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Vehicle vehicle = new Vehicle();
				vehicle.setVehicleID(vehicleId++);
				//CourierID
				//VehicleType
				//LicensePlate
				//Make
				//Model
				//Year
				//Active
				
				vehicleList.add(vehicle);
			}
			return vehicleList;
		} finally {
			readVehicles.close();
		}
	}
}
