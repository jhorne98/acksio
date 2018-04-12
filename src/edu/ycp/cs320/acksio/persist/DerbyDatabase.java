package edu.ycp.cs320.acksio.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ycp.cs320.acksio.model.Courier;
import edu.ycp.cs320.acksio.model.Dispatcher;
import edu.ycp.cs320.acksio.model.Job;
//import edu.ycp.cs320.acksio.controller.DataController;
import edu.ycp.cs320.acksio.model.UserAccount;
import edu.ycp.cs320.acksio.model.Vehicle;
import edu.ycp.cs320.acksio.persist.PersistenceException;
import edu.ycp.cs320.acksio.persist.DerbyDatabase;
import edu.ycp.cs320.acksio.persist.InitialData;
import edu.ycp.cs320.acksio.sqldemo.DBUtil;
//import jdk.internal.util.xml.impl.Pair;

// copied almost directly from lab06, and modified
// credit for all copied code goes to djhake and others respectively
public class DerbyDatabase implements IDatabase{
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
	public Integer createAccount(String username, String password, String email) {
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
						"insert into users (username, password, email)"
						+ "	values (?, ?, ?)"	
					);
					
					insertNewUser.setString(1, username);
					insertNewUser.setString(2, password);
					insertNewUser.setString(3, email);
					
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
						"	email varchar(40) " +
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
					insertUser = conn.prepareStatement("insert into users (username, password, email) values (?, ?, ?)");
					for (UserAccount user : userList) {
						//insertUser.setInt(1, author.getUserId());	// auto-generated primary key, don't insert this
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getPassword());
						insertUser.setString(3, user.getEmail());
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

	@Override
	public Boolean insert(Job job) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							  "insert into jobs (courier_id, dispatcher_id, longitude, latitude, vehicle_type, TSA_verified, recipient_name, recipient_phone, distance, paid, pick_up, drop_off, time, signed, invoice_approved) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
									// 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15
					
					//CourierID|DispatcherID|Long|Lat|VehicleType|TSA_Ver|RecipientName|
					//RecipientPhone|DistanceMi|CourierPaid|
					//PickUpTime|DropOffTime|ActualTime|Signed|Approved
					
					stmt.setInt(1, job.getCourierID());
					stmt.setInt(2, job.getDispatcherID());
					stmt.setDouble(3, job.getDestLong());
					stmt.setDouble(4, job.getDestLat());
					stmt.setString(5, job.getVehicleType().toString());
					stmt.setBoolean(6, job.getTsaVerified());
					stmt.setString(7, job.getRecipientName());
					stmt.setLong(8, job.getRecipientPhone());
					stmt.setInt(9, job.getDispatcherID());
					stmt.setBoolean(10, job.getCourierPaid());
					stmt.setInt(11, job.getPickUpTime());
					stmt.setInt(12, job.getDropOffTime());
					stmt.setInt(13, job.getActualTime());
					stmt.setBoolean(14, job.getSigned());
					stmt.setBoolean(15, job.getApproved());
					
					return stmt.execute();
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean insert(Courier courier) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							  "insert into couriers (user_id, dispatcher_id, TSA_verified, longitude, latitude, balance, pay_estimate, pay_history, availability) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					//UserID|DispatcherID|TSA_Ver|Long|Lat|Balance|PayEstimate|PayHistory|Availability
					
					stmt.setInt(1, courier.getUserId());
					stmt.setInt(2, courier.getDispatcherID());
					stmt.setBoolean(3, courier.isTsaVerified());
					stmt.setDouble(4, courier.getLongitude());
					stmt.setDouble(5, courier.getLatitude());
					stmt.setDouble(6, courier.getBalance());
					stmt.setDouble(7, courier.getPayEstimate());
					stmt.setDouble(8, courier.getPayHistory());
					stmt.setBoolean(9, courier.getAvailability());
					
					return stmt.execute();
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean insert(Dispatcher dispatcher) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							  "insert into dispatchers (user_id, address, phone)"
							+ "values (?, ?, ?)");
					
					stmt.setInt(1, dispatcher.getUserId());
					stmt.setString(2, dispatcher.getAddress());
					stmt.setInt(3, dispatcher.getPhone());
					
					return stmt.execute();
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean insert(UserAccount user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							  "insert into users (username, password, email, name) "
							+ "values (?, ?, ?, ?)");
					
					stmt.setString(1, user.getUsername());
					stmt.setString(2, user.getPassword());
					stmt.setString(3, user.getEmail());
					stmt.setString(4, user.getName());
					
					return stmt.execute();
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean insert(Vehicle vehicle) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							  "insert into vehicles (courier_id, vehicle_type, plate, make, model, year, active)"
							+ "values (?, ?, ?, ?, ?, ?, ?)");
					
					stmt.setInt(1, vehicle.getCourierID());
					stmt.setString(2, vehicle.getType().toString());
					stmt.setString(3, vehicle.getLicensePlate());
					stmt.setString(4, vehicle.getMake());
					stmt.setString(5, vehicle.getModel());
					stmt.setInt(6, vehicle.getYear());
					stmt.setBoolean(7, vehicle.isActive());
					
					return stmt.execute();
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean update(Job job) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Boolean update(Courier courier) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Boolean update(Dispatcher dispatcher) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Boolean update(UserAccount user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Boolean update(Vehicle vehicle) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Job jobFromID(int id) {
		return executeTransaction(new Transaction<Job>() {
			@Override
			public Job execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							  "select courier_id, dispatcher_id, longitude, latitude, "
							+ "vehicle_type, TSA_verified, "
							+ "recipient_name, recipient_phone, distance, paid, "
							+ "pick_up, drop_off, time, signed, invoice_approved "
							+ "where job_id = ?");
					
					stmt.setInt(1, id);
					
					if(!resultSet.next())
						return null;
					
					Job job = new Job();
					
					job.setJobID(id);
					job.setCourierID(resultSet.getInt(1));
					job.setDispatcherID(resultSet.getInt(2));
					job.setDestLong(resultSet.getDouble(3));
					job.setDestLat(resultSet.getDouble(4));
					job.setVehicleType(resultSet.getString(5));
					job.setTsaVerified(resultSet.getBoolean(6));
					job.setRecipientName(resultSet.getString(7));
					job.setRecipientPhone(resultSet.getLong(8));
					job.setDistanceMi(resultSet.getDouble(9));
					job.setCourierPaid(resultSet.getBoolean(10));
					job.setPickUpTime(resultSet.getInt(11));
					job.setDropOffTime(resultSet.getInt(12));
					job.setActualTime(resultSet.getInt(13));
					job.setSigned(resultSet.getBoolean(14));
					job.setApproved(resultSet.getBoolean(15));
					
					return job;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}

	@Override
	public Courier courierFromID(int id) {
		return executeTransaction(new Transaction<Courier>() {
			@Override
			public Courier execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Dispatcher dispatcherFromID(int id) {
		return executeTransaction(new Transaction<Dispatcher>() {
			@Override
			public Dispatcher execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public UserAccount userAccountFromID(int id) {
		return executeTransaction(new Transaction<UserAccount>() {
			@Override
			public UserAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							  "select username, password, email, name from users "
							+ "where user_id = ?");
					
					stmt.setInt(1, id);
					
					resultSet = stmt.executeQuery();
					
					if(!resultSet.next())
						return null;
					
					UserAccount user = new UserAccount();
					user.setUserId(id);
					user.setUsername(resultSet.getString(1));
					user.setPassword(resultSet.getString(2));
					user.setEmail(resultSet.getString(3));
					user.setName(resultSet.getString(4));
					
					return user;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}

	@Override
	public Vehicle vehicleFromID(int id) {
		return executeTransaction(new Transaction<Vehicle>() {
			@Override
			public Vehicle execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							  "select "
							+ "courier_id, vehicle_type, plate, make, model, year, active from vehicle "
							+ "where vehicle_id = ?");
					
					stmt.setInt(1, id);
					
					resultSet = stmt.executeQuery();
					
					if(!resultSet.next())
						return null;
					
					Vehicle vehicle = new Vehicle();
					vehicle.setCourierID(resultSet.getInt(1));
					vehicle.setType(resultSet.getString(2));
					vehicle.setLicensePlate(resultSet.getString(3));
					vehicle.setMake(resultSet.getString(4));
					vehicle.setModel(resultSet.getString(5));
					vehicle.setYear(resultSet.getInt(6));
					vehicle.setActive(resultSet.getBoolean(7));
					
					return vehicle;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}

	@Override
	public List<Vehicle> vehiclesFromCourierID(int id) {
		return executeTransaction(new Transaction<List<Vehicle>>() {
			@Override
			public List<Vehicle> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public List<Job> jobsFromCourierID(int id) {
		return executeTransaction(new Transaction<List<Job>>() {
			@Override
			public List<Job> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public List<Job> jobsFromDispatcherID(int id) {
		return executeTransaction(new Transaction<List<Job>>() {
			@Override
			public List<Job> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public List<Courier> couriersFromDispatcherID(int id) {
		return executeTransaction(new Transaction<List<Courier>>() {
			@Override
			public List<Courier> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public UserAccount userAccountFromUsername(String username) {
		return executeTransaction(new Transaction<UserAccount>() {
			@Override
			public UserAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							  "select user_id, password, email, name from users "
							+ "where username = ?");
					
					stmt.setString(1, username);
					
					resultSet = stmt.executeQuery();
					
					if(!resultSet.next())
						return null;
					
					UserAccount user = new UserAccount();
					user.setUserId(resultSet.getInt(1));
					user.setUsername(username);
					user.setPassword(resultSet.getString(2));
					user.setEmail(resultSet.getString(3));
					user.setName(resultSet.getString(4));
					
					return user;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}

	@Override
	public Courier courierFromUsername(String username) {
		return executeTransaction(new Transaction<Courier>() {
			@Override
			public Courier execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}

	@Override
	public Dispatcher dispatcherFromUsername(String username) {
		return executeTransaction(new Transaction<Dispatcher>() {
			@Override
			public Dispatcher execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
				return null;
			}
		});
	}
}
