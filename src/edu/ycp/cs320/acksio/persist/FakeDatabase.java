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
		jobList = new ArrayList<Job>();
		userList = new ArrayList<UserAccount>();
		courierList = new ArrayList<Courier>();
		dispatcherList = new ArrayList<Dispatcher>();
		vehicleList = new ArrayList<Vehicle>();
		
		readInitialData();
		
		System.out.println(userList.size() + " users");
	}
	
	public void readInitialData() {
		try {
			userList.addAll(InitialData.getUsers());
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
		while(i < jobList.size() && !jobList.get(i).getId().equals(job.getId()))
			i++;
		if(jobList.get(i).getId().equals(job.getId())) {
			jobList.add(i, job);
			jobList.remove(i+1);
			return true;
		}
		return false;
	}
	@Override
	public Boolean update(Courier courier) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean update(Dispatcher dispatcher) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean update(UserAccount user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean update(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Job jobFromID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Courier courierFromID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Dispatcher dispatcherFromID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserAccount userAccountFromID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vehicle vehicleFromID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean verifyLogin(String username, String password) {
		for(UserAccount user : userList)
			if(user.getUsername() == username && user.getPassword() == password)
				return true;
		return false;
	}
	@Override
	public List<Vehicle> vehiclesFromCourierID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Job> jobsFromCourierID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Job> jobsFromDispatcherID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Courier> couriersFromDispatcherID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
