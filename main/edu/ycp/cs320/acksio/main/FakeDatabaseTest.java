package edu.ycp.cs320.acksio.main;

import edu.ycp.cs320.acksio.persist.*;

public class FakeDatabaseTest {

	public static void main(String[] args) {
		IDatabase db = new FakeDatabase();

		System.out.println();
		System.out.println(db);
		System.out.println(db.courierFromID(1).getBalance());
	}
}
