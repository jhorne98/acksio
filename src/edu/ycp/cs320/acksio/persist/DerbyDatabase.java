package edu.ycp.cs320.acksio.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import edu.ycp.cs320.acksio.controller.DataController;
import edu.ycp.cs320.acksio.model.UserAccount;
import edu.ycp.cs320.acksio.persist.PersistenceException;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;
import edu.ycp.cs320.acksio.persist.InitialData;
import edu.ycp.cs320.acksio.sqldemo.DBUtil;
//import jdk.internal.util.xml.impl.Pair;

// copied almost directly from lab06, and modified
// credit for all copied code goes to djhake and others respectively
public class DerbyDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	public void populate(String id) {
		
	}
	
	public void save() {
		
	}
	
	// pull users from database to verify that correct username and corresponding password has been input
	public Boolean verifyLogin(String username, String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select users.user_id"
							+ "	from users"
							+ "	where users.username=?"
							+ "		and users.password=?"
					);
					
					stmt.setString(1, username);
					stmt.setString(2, password);
					
					resultSet = stmt.executeQuery();

					//System.out.println(resultSet.getRow());
					
					if (resultSet.next()) {
						return true;
						
						//System.out.println(resultSet.getMetaData());
					}
					
					return false;
							
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// attempt to create a user by first checking if the user/password/email exists in the users db
	// return error code based on user input matching field in db
	// TODO: SUPER KLUDGY!! fix later
	public Integer createAccount(String username, String password, String email, String accountType) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement checkUser = null;
				//PreparedStatement checkPassword = null;
				//PreparedStatement checkEmail = null;
				PreparedStatement insertNewUser = null;
				ResultSet userSet = null;
				//ResultSet passwordSet = null;
				//ResultSet emailSet = null;
				
				try {
					// determine if input credentials already exist in the database
					checkUser = conn.prepareStatement(
						"select *"
							+ "	from users"
							+ "	where users.username=?"
							+ "		or users.password=?"
							+ "		or users.email=?"
					);
					
					checkUser.setString(1, username);
					checkUser.setString(2, password);
					checkUser.setString(3, email);
					
					userSet = checkUser.executeQuery();
					
					// test result set for identical username/password/email: return based on those params in that order
					while(userSet.next()) {
						String takenUser = userSet.getString(2);
						String takenPassword = userSet.getString(3);
						String takenEmail = userSet.getString(4);
						
						//System.out.println(userSet.getString(2) + " " + userSet.getString(3) + " " + userSet.getString(4));
						
						if(takenUser.equals(username)) {
							return 1;
						} else if(takenPassword.equals(password)) {
							return 2;
						} else if(takenEmail.equals(email)) {
							return 3;
						}
					}
					
					// user does not already exist: insert into users
					insertNewUser = conn.prepareStatement(
						"insert into users (username, password, email, name, accountType)"
						+ "	values (?, ?, ?, ?, ?)"	
					);
					
					insertNewUser.setString(1, username);
					insertNewUser.setString(2, password);
					insertNewUser.setString(3, email);
					insertNewUser.setString(4, "");
					insertNewUser.setString(5, accountType);
					
					insertNewUser.executeUpdate();
					
					return 0;
				} finally {
					DBUtil.closeQuietly(userSet);
					DBUtil.closeQuietly(checkUser);
				}
			}
		});
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				//PreparedStatement stmt2 = null;
				
				try {
					stmt1 = conn.prepareStatement(
						"create table users (" +
						"	user_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	username varchar(40)," +
						"	password varchar(40)," +
						"	email varchar(40)," +
						"	name varchar(40), " +
						"	accounttype varchar(10)" +
						")"
					);	
					stmt1.executeUpdate();
					
					/*
					stmt2 = conn.prepareStatement(
							"create table books (" +
							"	book_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	author_id integer constraint author_id references authors, " +
							"	title varchar(70)," +
							"	isbn varchar(15)," +
							"   published integer " +
							")"
					);
					stmt2.executeUpdate();
					*/
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					//DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	// used to input all data into derby tables
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<UserAccount> userList;
				//List<Book> bookList;
				
				try {
					userList = InitialData.getUsers();
					//bookList = InitialData.getBooks();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;
				//PreparedStatement insertBook   = null;

				try {
					// populate users table
					insertUser = conn.prepareStatement("insert into users (username, password, email, name, accounttype) values (?, ?, ?, ?, ?)");
					for (UserAccount user : userList) {
						//insertUser.setInt(1, author.getUserId());	// auto-generated primary key, don't insert this
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getPassword());
						insertUser.setString(3, user.getEmail());
						insertUser.setString(4, user.getName());
						insertUser.setString(5, user.getAccountType());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					/*
					// populate books table (do this after authors table,
					// since author_id must exist in authors table before inserting book)
					insertBook = conn.prepareStatement("insert into books (author_id, title, isbn, published) values (?, ?, ?, ?)");
					for (Book book : bookList) {
						//insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertBook.setInt(1, book.getAuthorId());
						insertBook.setString(2, book.getTitle());
						insertBook.setString(3, book.getIsbn());
						insertBook.setInt(4,  book.getPublished());
						insertBook.addBatch();
					}
					insertBook.executeBatch();
					*/
					
					return true;
				} finally {
					//DBUtil.closeQuietly(insertBook);
					DBUtil.closeQuietly(insertUser);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		// TODO: fix createTables() to check if dbs already in schema
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
}
