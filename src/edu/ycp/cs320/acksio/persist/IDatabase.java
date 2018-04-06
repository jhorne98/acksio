package edu.ycp.cs320.acksio.persist;

import java.util.List;

import edu.ycp.cs320.acksio.model.Courier;
import edu.ycp.cs320.acksio.model.Dispatcher;
import edu.ycp.cs320.acksio.model.Job;
import edu.ycp.cs320.acksio.model.UserAccount;
import edu.ycp.cs320.acksio.model.Vehicle;

public interface IDatabase {
	//Save calls these methods
	//If the object is already in the database, switch to update
	public Boolean insert(Job job);
	public Boolean insert(Courier courier);
	public Boolean insert(Dispatcher dispatcher);
	public Boolean insert(UserAccount user);
	public Boolean insert(Vehicle vehicle);
	
	public Boolean update(Job job);
	public Boolean update(Courier courier);
	public Boolean update(Dispatcher dispatcher);
	public Boolean update(UserAccount user);
	public Boolean update(Vehicle vehicle);
	
	public Job jobFromID(int id);
	public Courier courierFromID(int id);
	public Dispatcher dispatcherFromID(int id);
	public UserAccount userAccountFromID(int id);
	public Vehicle vehicleFromID(int id);
	
	public Boolean verifyLogin(String username, String password);
	public List<Vehicle> vehiclesFromCourierID(int id);
	public List<Job> jobsFromCourierID(int id);
	public List<Job> jobsFromDispatcherID(int id);
	public List<Courier> couriersFromDispatcherID(int id);
	
	public UserAccount userAccountFromUsername(String username);
	public Courier courierFromUsername(String username);
	public Dispatcher dispatcherFromUsername(String username);
} 
