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
				userList.add(user);
			}
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
 	public static List<Dispatcher> getDispatchers() throws IOException {
		List<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
		ReadCSV readUsers = new ReadCSV("dispatchers.csv");
		try {
			// auto-generated primary key for authors table
			Integer dispatcherId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Dispatcher user = new Dispatcher();
				
				user.setUserId(dispatcherId++);				
				/*
				user.setUsername(i.next());
				user.setPassword(i.next());
				*/
				dispatcherList.add(user);
			}
			return dispatcherList;
		} finally {
			readUsers.close();
		}
	}
	
 	public static List<Courier> getCouriers() throws IOException {
		List<Courier> courierList = new ArrayList<Courier>();
		ReadCSV readUsers = new ReadCSV("dispatchers.csv");
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Courier user = new Courier();
				/*
				user.setUserId(userId++);				
				user.setUsername(i.next());
				user.setPassword(i.next());
				*/
				courierList.add(user);
			}
			return courierList;
		} finally {
			readUsers.close();
		}
	}
	
 	public static List<Job> getJobs() throws IOException {
		List<Job> jobList = new ArrayList<Job>();
		ReadCSV readUsers = new ReadCSV("jobs.csv");
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Job user = new Job();
				/*
				user.setUserId(userId++);				
				user.setUsername(i.next());
				user.setPassword(i.next());
				jobList.add(user);
				*/
			}
			return jobList;
		} finally {
			readUsers.close();
		}
	}
 	
 	public static List<Vehicle> getVehicles() throws IOException {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		ReadCSV readUsers = new ReadCSV("vehicles.csv");
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Vehicle user = new Vehicle();
				/*
				user.setUserId(userId++);				
				user.setUsername(i.next());
				user.setPassword(i.next());
				*/
				vehicleList.add(user);
			}
			return vehicleList;
		} finally {
			readUsers.close();
		}
	}
}
