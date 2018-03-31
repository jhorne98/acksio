package edu.ycp.cs320.acksio.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.acksio.model.UserAccount;
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
				user.setEmail(i.next());
				userList.add(user);
			}
			return userList;
		} finally {
			readUsers.close();
		}
	}
}
