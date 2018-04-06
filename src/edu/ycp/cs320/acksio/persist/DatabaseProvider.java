package edu.ycp.cs320.acksio.persist;

import edu.ycp.cs320.acksio.controller.DataController;

// unused for the moment as there is no fake db implemented yet
// TODO: implement fake sb
public class DatabaseProvider {
	private static IDatabase theInstance;
	
	public static void setInstance(IDatabase db) {
		theInstance = db;
	}
	
	public static IDatabase getInstance() {
		/*
		if (theInstance == null) {
			throw new IllegalStateException("IDatabase instance has not been set!");
		}
		*/
		return theInstance;
	}
}
