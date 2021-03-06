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
import edu.ycp.cs320.acksio.model.VehicleType;
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
				//System.out.println("User... " + userId);
				user.setUserId(userId++);				
				user.setUsername(i.next());
				user.setPassword(i.next());
				user.setEmail(i.next());
				user.setName(i.next());
				//System.out.println(i.hasNext());
				user.setAccountType(i.next());
				//System.out.println(i.hasNext());
				userList.add(user);
				//Therefor, the data in the csv is presented as
				//username|password|email|name
			}
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
 	public static List<Dispatcher> getDispatchers() throws IOException {
		List<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
		List<UserAccount> userList = getUsers();
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
				user.setPhone(i.next());
				//Therefor, the data in the csv is presented as
				//user_id|Address|Phone
				
				int userID = 0;
				while(userList.get(userID).getUserId() != user.getUserId() && userID < userList.size()) {
					userID++;
				}
				if(userID < userList.size()) {
					user.setName(userList.get(userID).getName());
					user.setEmail(userList.get(userID).getEmail());
					user.setUsername(userList.get(userID).getUsername());
					user.setPassword(userList.get(userID).getPassword());
				}
				
				dispatcherList.add(user);
			}
			return dispatcherList;
		} finally {
			readDispatchers.close();
		}
	}
	
 	public static List<Courier> getCouriers() throws IOException {
		List<Courier> courierList = new ArrayList<Courier>();
		List<UserAccount> userList = getUsers();
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
				user.setTsaVerified(Integer.parseInt(i.next()));
				user.setLongitude(Double.parseDouble(i.next()));
				user.setLatitude(Double.parseDouble(i.next()));
				user.setBalance(Double.parseDouble(i.next()));
				user.setPayEstimate(Double.parseDouble(i.next()));
				user.setPayHistory(Double.parseDouble(i.next()));
				user.setAvailability(Integer.parseInt(i.next()));
				//Therefor, the data is presented in the csv as
				//UserID|DispatcherID|TSA_Ver|Long|Lat|Balance|PayEstimate|PayHistory|Availability
				
				int userID = 0;
				while(userList.get(userID).getUserId() != user.getUserId() && userID < userList.size()) {
					userID++;
				}
				if(userID < userList.size()) {
					user.setName(userList.get(userID).getName());
					user.setEmail(userList.get(userID).getEmail());
					user.setUsername(userList.get(userID).getUsername());
					user.setPassword(userList.get(userID).getPassword());
				}
				
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
			//System.out.println(VehicleType.CAR.toString());
			while (true) {
				List<String> tuple = readJobs.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Job job = new Job();
				job.setJobID(jobId++);
				job.setCourierID(Integer.parseInt(i.next()));
				job.setDispatcherID(Integer.parseInt(i.next()));
				job.setDestLong(Double.parseDouble(i.next()));
				job.setDestLat(Double.parseDouble(i.next()));
				job.setVehicleType(i.next().toUpperCase());//VehicleType
				job.setTsaVerified(Integer.parseInt(i.next()));
				job.setRecipientName(i.next());
				job.setRecipientPhone(Long.parseLong(i.next()));
				job.setDistanceMi(Double.parseDouble(i.next())); //Distance
				job.setCourierPaid(Integer.parseInt(i.next())); //CourierPaid
				job.setPickUpTime(Integer.parseInt(i.next())); //PickUpTime
				job.setDropOffTime(Integer.parseInt(i.next())); //DropOffTime
				job.setActualTime(Integer.parseInt(i.next())); //TimeForJob
				job.setSigned(Integer.parseInt(i.next())); //PackageSignedFor
				job.setApproved(Integer.parseInt(i.next())); //InvoiceApproved
				//Therefor, the data is presented in the csv as
				//CourierID|DispatcherID|Long|Lat|VehicleType|TSA_Ver|RecipientName|RecipientPhone|DistanceMi|CourierPaid|PickUpTime|DropOffTime|ActualTime|Signed|Approved
				
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
				vehicle.setCourierID(Integer.parseInt(i.next()));//CourierID
				vehicle.setType(i.next());
				vehicle.setLicensePlate(i.next());//LicensePlate
				vehicle.setMake(i.next());//Make
				vehicle.setModel(i.next());//Model
				vehicle.setYear(Integer.parseInt(i.next()));
				vehicle.setActive(Integer.parseInt(i.next()));
				//Therefor, the data is presented in the csv as
				//CourierID|Type|Plate|Make|Model|Year|Active
				
				vehicleList.add(vehicle);
			}
			return vehicleList;
		} finally {
			readVehicles.close();
		}
	}
}
