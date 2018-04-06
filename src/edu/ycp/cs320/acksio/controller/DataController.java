
package edu.ycp.cs320.acksio.controller;

import edu.ycp.cs320.acksio.persist.DatabaseProvider;

public interface DataController {
	public void populate(DatabaseProvider provider, int id);	// This method will make the database call: "select * from {insert table} where id = ?" and set id = id.
	
	public void save(DatabaseProvider provider);					// This method will make the database call to update the database with the new information.
	
	// deprecated: bad idea
	//public Boolean verifyLogin(String username, String password);				// This method will verify that the user is logged in using a database call 
}
