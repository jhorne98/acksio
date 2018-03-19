package edu.ycp.cs320.acksio.persist;

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
	
	public Job jobFromID(String id);
	public Courier courierFromID(String id);
	public Dispatcher dispatcherFromID(String id);
	public UserAccount userAccountFromID(String id);
	public Vehicle vehicleFromID(String id);
	
	public Boolean verifyLogin(String username, String password);
}
