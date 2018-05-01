package edu.ycp.cs320.acksio.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.acksio.model.UserAccount;
import edu.ycp.cs320.acksio.persist.InitialData;
import jbcrypt.org.mindrot.jbcrypt.BCrypt;

public class PasswordHashingThing {

	public static void main(String[] args) {
		try {
			List<UserAccount> users = InitialData.getUsers();
			
			for(UserAccount user : users) {
				System.out.print(user.getUsername()+":\t");
				System.out.print(user.getPassword()+":\t");
				System.out.println(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
