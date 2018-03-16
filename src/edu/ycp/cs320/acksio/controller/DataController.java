package edu.ycp.cs320.acksio.controller;

public interface DataController {
	public void populate(String id);	// This method will make the database call: "select * from {insert table} where id = ?" and set id = id.
	
	public void save();					// This method will make the database call to update the database with the new information.
	public void Queue();
}
