
package edu.ycp.cs320.acksio.controller;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;

public interface DataController {
	// This method will make the database call: "select * from {insert table} where id = ?" and set id = id.
	public void populate(int id);	
	
	// This method will make the database call to update the database with the new information.
	public void save();
	
	// deprecated: bad idea
	//public Boolean verifyLogin(String username, String password);				// This method will verify that the user is logged in using a database call 
}
