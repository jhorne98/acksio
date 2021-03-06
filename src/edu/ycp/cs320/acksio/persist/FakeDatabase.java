/*Created by Joel Horne (jhorne@ycp.edu), Alaska Kiley (dkiley@ycp.edu), and Andrew Georgiou (ageorgiou@ycp.edu)
 at York College of Pennsylvania for CS320.103: Software Engineering
*/
package edu.ycp.cs320.acksio.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.acksio.model.Job;
import edu.ycp.cs320.acksio.model.UserAccount;
import edu.ycp.cs320.acksio.model.Courier;
import edu.ycp.cs320.acksio.model.Dispatcher;
import edu.ycp.cs320.acksio.model.Vehicle;
import edu.ycp.cs320.acksio.persist.InitialData;

public class FakeDatabase implements IDatabase{
	private List<Job> jobList;
	private List<UserAccount> userList;
	private List<Courier> courierList;
	private List<Dispatcher> dispatcherList;
	private List<Vehicle> vehicleList;
	
	public FakeDatabase() {
		userList = new ArrayList<UserAccount>();
		courierList = new ArrayList<Courier>();
		dispatcherList = new ArrayList<Dispatcher>();
		jobList = new ArrayList<Job>();
		vehicleList = new ArrayList<Vehicle>();
		
		readInitialData();
		
		System.out.println(userList.size() + " users");
		System.out.println(courierList.size() + " couriers");
		System.out.println(dispatcherList.size() + " dispatchers");
		System.out.println(jobList.size() + " jobs");
		System.out.println(vehicleList.size() + " vehicles");
	}
	
	public void readInitialData() {
		try {
			userList.addAll(InitialData.getUsers());
			courierList.addAll(InitialData.getCouriers());
			dispatcherList.addAll(InitialData.getDispatchers());
			jobList.addAll(InitialData.getJobs());
			vehicleList.addAll(InitialData.getVehicles());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	@Override
	public Boolean insert(Job job) {
		if(jobList.add(job))
			return true;
		return false;
	}
	@Override
	public Boolean insert(Courier courier) {
		if(courierList.add(courier))
			return true;
		return false;
	}
	@Override
	public Boolean insert(Dispatcher dispatcher) {
		if(dispatcherList.add(dispatcher))
			return true;
		return false;
	}
	@Override
	public Boolean insert(UserAccount user) {
		if(userList.add(user))
			return true;
		return false;
	}
	@Override
	public Boolean insert(Vehicle vehicle) {
		if(vehicleList.add(vehicle))
			return true;
		return false;
	}
	@Override
	public Boolean update(Job job) {
		int i = 0;
		while(i < jobList.size() && !(jobList.get(i).getJobID() == job.getJobID()))
			i++;
		if(jobList.get(i).getJobID() == job.getJobID()) {
			jobList.add(i, job);
			jobList.remove(i+1);
			return true;
		}
		return false;
	}
	@Override
	public Boolean update(Courier courier) {
		int i = 0;
		while(i < courierList.size() && !(courierList.get(i).getCourierID() == courier.getCourierID()))
			i++;
		if(courierList.get(i).getCourierID() == courier.getCourierID()) {
			courierList.add(i, courier);
			courierList.remove(i+1);
			return true;
		}
		return false;
	}
	@Override
	public Boolean update(Dispatcher dispatcher) {
		int i = 0;
		while(i < dispatcherList.size() && !(dispatcherList.get(i).getDispatcherID() == dispatcher.getDispatcherID()))
			i++;
		if(dispatcherList.get(i).getDispatcherID() == dispatcher.getDispatcherID()) {
			dispatcherList.add(i, dispatcher);
			dispatcherList.remove(i+1);
			return true;
		}
		return false;
	}
	@Override
	public Boolean update(UserAccount user) {
		int i = 0;
		while(i < userList.size() && !(userList.get(i).getUserId() == user.getUserId()))
			i++;
		if(userList.get(i).getUserId() == user.getUserId()) {
			userList.add(i, user);
			userList.remove(i+1);
			return true;
		}
		return false;
	}
	@Override
	public Boolean update(Vehicle vehicle) {
		int i = 0;
		while(i < vehicleList.size() && !(vehicleList.get(i).getVehicleID() == vehicle.getVehicleID()))
			i++;
		if(vehicleList.get(i).getVehicleID() == vehicle.getVehicleID()) {
			vehicleList.add(i, vehicle);
			vehicleList.remove(i+1);
			return true;
		}
		return false;
	}
	@Override
	public Boolean verifyLogin(String username, String password) {
		for(UserAccount user : userList)
			if(user.getUsername() == username && user.getPassword() == password)
				return true;
		return false;
	}

	@Override
	public Job jobFromID(int id) {
		for(int i = 0; i < jobList.size(); i++) {
			if(jobList.get(i).getJobID() == id)
				return jobList.get(i);
		}
		return null;
	}

	@Override
	public Courier courierFromID(int id) {
		for(int i = 0; i < courierList.size(); i++) {
			if(courierList.get(i).getCourierID() == id)
				return courierList.get(i);
		}
		return null;
	}

	@Override
	public Dispatcher dispatcherFromID(int id) {
		for(int i = 0; i < dispatcherList.size(); i++) {
			if(dispatcherList.get(i).getUserId() == id)
				return dispatcherList.get(i);
		}
		return null;
	}

	@Override
	public UserAccount userAccountFromID(int id) {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId() == id)
				return userList.get(i);
		}
		return null;
	}

	@Override
	public Vehicle vehicleFromID(int id) {
		for(int i = 0; i < vehicleList.size(); i++) {
			if(vehicleList.get(i).getVehicleID() == id)
				return vehicleList.get(i);
		}
		return null;
	}

	@Override
	public List<Vehicle> vehiclesFromCourierID(int id) {
		List<Vehicle> list = new ArrayList<Vehicle>();
		
		for(Vehicle vehicle : vehicleList) 
			if(vehicle.getCourierID() == id)
				list.add(vehicle);
		
		return list;
	}

	@Override
	public List<Job> jobsFromCourierID(int id) {
		List<Job> list = new ArrayList<Job>();
		
		for(Job job : jobList) 
			if(job.getCourierID() == id)
				list.add(job);
		
		
		return list;
	}

	@Override
	public List<Job> jobsFromDispatcherID(int id) {
		List<Job> list = new ArrayList<Job>();
		
		for(Job job : jobList) 
			if(job.getDispatcherID() == id)
				list.add(job);
		
		return list;
	}

	@Override
	public List<Courier> couriersFromDispatcherID(int id) {
		List<Courier> list = new ArrayList<Courier>();
		
		for(Courier courier : courierList) 
			if(courier.getDispatcherID() == id)
				list.add(courier);
		
		return list;
	}

	@Override
	public UserAccount userAccountFromUsername(String username) {
		for(UserAccount user : userList)
			if(user.getUsername().equals(username))
				return user;
		return null;
	}

	@Override
	public Courier courierFromUsername(String username) {
		for(Courier user : courierList)
			if(user.getUsername().equals(username))
				return user;
		return null;
	}

	@Override
	public Dispatcher dispatcherFromUsername(String username) {
		for(Dispatcher user : dispatcherList)
			if(user.getUsername().equals(username))
				return user;
		return null;
	}

	@Override
	public Boolean remove(Job job) {
		int i = 0;
		while(i < jobList.size() && !(jobList.get(i).getJobID() == job.getJobID()))
			i++;
		if(jobList.get(i).getJobID() == job.getJobID()) {
			jobList.remove(i);
			return true;
		}
		return false;
	}

	@Override
	public Boolean remove(Courier courier) {
		int i = 0;
		while(i < courierList.size() && !(courierList.get(i).getCourierID() == courier.getCourierID()))
			i++;
		if(courierList.get(i).getCourierID() == courier.getCourierID()) {
			courierList.remove(i);
			return true;
		}
		return false;
	}

	@Override
	public Boolean remove(Dispatcher dispatcher) {
		int i = 0;
		while(i < dispatcherList.size() && !(dispatcherList.get(i).getDispatcherID() == dispatcher.getDispatcherID()))
			i++;
		if(dispatcherList.get(i).getDispatcherID() == dispatcher.getDispatcherID()) {
			dispatcherList.remove(i);
			return true;
		}
		return false;
	}

	@Override
	public Boolean remove(UserAccount user) {
		int i = 0;
		while(i < userList.size() && !(userList.get(i).getUserId() == user.getUserId()))
			i++;
		if(userList.get(i).getUserId() == user.getUserId()) {
			userList.remove(i);
			return true;
		}
		return false;
	}

	@Override
	public Boolean remove(Vehicle vehicle) {
		int i = 0;
		while(i < vehicleList.size() && !(vehicleList.get(i).getVehicleID() == vehicle.getVehicleID()))
			i++;
		if(vehicleList.get(i).getVehicleID() == vehicle.getVehicleID()) {
			vehicleList.remove(i);
			return true;
		}
		return false;
	}
}
