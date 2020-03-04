package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Dvd;
import models.User;

/**
 * This class established a connection to the database and will contain methods
 * that will communicate to the database (Database Accessor Object)
 **/

public class myDAO {
	// Load the driver, connect to db

	private Connection getConnection() {

		// Creates an object connection
		Connection conn = null;

		try {
			// Load the driver that we neeed
			Class.forName("org.sqlite.JDBC");

			// URL for the database location
			String url = "jdbc:sqlite:/Users/iqraiqbal/Downloads/dvd.sqlite";

			// Initialise the conn object
			conn = DriverManager.getConnection(url);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Return conn object
		return conn;

	}

	// POLYMORPHISM - Allows you define 1 interface and have multiple
	// implementations

	// DYNAMIC METHODS - Meaning they can be reused and prevents the parameters that
	// are being passed to be written over and over again

	public ArrayList<Dvd> getDVDs() throws SQLException {
		// Instead of returning void - we return arraylist which is all dvds as an
		// object

		// Initalising array list
		ArrayList<Dvd> allDvds = new ArrayList<>();

		// Creating a connection object
		Connection connection = getConnection();
		// Statement object created which will hold the sql string that will be executed
		Statement statement = connection.createStatement();

		// A sql to query that will get all dvds from the collection table and stores it
		// into a sql string
		String sql = "SELECT * FROM collection;";

		// Print query to coonsole
		System.out.println(sql);

		// Code that executes the query - i.e runs it and catches the query result
		ResultSet rs = statement.executeQuery(sql);

		// If theres another row (.next function) do the while loop
		// (Itterates overeach row till there are no more rows)
		while (rs.next()) {
			// The id from the result set is being placed into the int variable id by using
			// the getters from the DVD model class
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");

			// Creating new dvd objecct
			Dvd dvd = new Dvd(id, title, genre, year);

			// add the argument from the dvd object to the allDvds array list
			allDvds.add(dvd);

			// Prints each row till it has veen itterated over
			// Empty strings will hold the DVD
			System.out.println(id + " " + title + " " + genre + " " + year);
		}
		// Close connection database to prevent the error of "Database locked"
		connection.close();

		// Returning all the dvds from the arraylist that holds the dvd objects
		return allDvds;
	}

	public Dvd get1DVD(int ID) throws SQLException {

		// Creating a connection object
		Connection connection = getConnection();

		// This is NOT needed when using prepare statements
		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

		// Declaring an object of dvd class - it has not been instantiated therefore it
		// is null
		// Every variable in a program must
		Dvd dvd = null;

		// A sql query that will get 1 dvd from the collection table where the ID is
		// equal to and stores it into a sql string

		// The initial syntax WITHOUT prepared statements
//		String sql = "SELECT * FROM collection WHERE id =" + ID + ";";

		// Syntax WITH prepared statements
		String sql = "SELECT * FROM collection WHERE id = ?";

		// PrepareStaement prevents sql injection and execute faster
		// Statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		// The numbers respresent the parameters in the query
		// 1 represents the first parameter in the query
		statement.setInt(1, ID);

		// Prints query to console
		System.out.println(sql);

		// Code that executes the query - i.e runs it and catches the query result

		// WITHOUT prepared statement - NOTICE THE SQL QUERY IS INSERTED HERE
//		ResultSet rs = statement.executeQuery(sql);

		// No need to place the query inside the parameters now as its passed in the
		// prepared statement ^
		ResultSet rs = statement.executeQuery();

		// While you got another row keep the while loop going - that's all next does
		while (rs.next()) {
			// The id from the result set is being placed into the int variable id by using
			// the getters from the DVD model class
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");

			// Creating new dvd object
			dvd = new Dvd(id, title, genre, year);

			// Prints each row till it has been itterated over
			// The values are appended to the empty strings
			System.out.println(id + " " + title + " " + genre + " " + year);
		}

		// Close connection database to prevent the error of "Database locked"
		connection.close();

		// Returning dvd objects
		return dvd;
	}

	public void insertDVD(Dvd dvd) throws SQLException {
		// Creates an object connection
		Connection connection = getConnection();

		// This is NOT needed when using prepare statements
		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

		// This sql query will insert a title,genre and year using the values imported
		// by the admin into collection tablestores it into a sql string

		// WITHOUT PS
//		String sql = "INSERT INTO collection (Title,Genre,Year)" + "VALUES ('" + dvd.getTitle() + "', '"
//				+ dvd.getGenre() + "', " + dvd.getYear() + ");";

		// WITH PS
		String sql = "INSERT INTO collection (Title, Genre, Year)" + " VALUES (?, ?, ?)";

		// PrepareStaement prevents sql injection and execute faster
		// Statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		// The numbers respresent the parameters in the query
		// 1 represents the first parameter in the query
		// .setString for string variables like title and genre
		statement.setString(1, dvd.getTitle());
		statement.setString(2, dvd.getGenre());
		// .setInt for year
		statement.setInt(3, dvd.getYear());

		// Prints query to console
		System.out.println(sql);

		// Carries out the update of inserting a dvd

		// WITHOUT PS
//		statement.executeUpdate(sql);

		// WITH PS
		statement.executeUpdate();

		// When the quyery is executed - the dvd has been inserted - print done to the
		// console
		System.out.println("Done");

		// Close connection database to prevent the error of "Database locked"
		connection.close();
	}

	public void updateDVD(Dvd dvd) throws SQLException {
		// Creates an object connection
		Connection connection = getConnection();

		// This is NOT needed when using prepare statements
		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

		// This sql query will update title,genre and year using the getters in the dvd
		// class

//		String sql = "UPDATE collection " + "SET Title = '" + dvd.getTitle() + "', Genre = '" + dvd.getGenre()
//				+ "', Year = " + dvd.getYear() + "" + " WHERE ID = " + dvd.getId() + ";";

		// NOTICE WITH PS - No getters are used in the sql query INSTEAD it is written
		// after
		String sql = "UPDATE collection " + "SET Title = ?, Genre = ?, Year = ?" + " WHERE ID = ?";

		// PrepareStaement prevents sql injection and executes faster
		// statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		// The numbers respresent the parameters in the query
		// 1 represents the first parameter in the query
		// .setString for string variables like title and genre
		// getters are used from the DVD model class
		statement.setString(1, dvd.getTitle());
		statement.setString(2, dvd.getGenre());
		statement.setInt(3, dvd.getYear());
		statement.setInt(4, dvd.getId());

		// Prints query to console
		System.out.println(sql);

		// Carries out the update query

		// WITHOUT PS
//		statement.executeUpdate(sql);

		// WITH PS
		statement.executeUpdate();

		// When the query is executed - the dvd has been updated - print done to the
		// console
		System.out.println("Done");

		// Close connection database to prevent the error of "Database locked"
		connection.close();
	}

	public void deleteDVD(int ID) throws SQLException {

		// Creates an object connection
		Connection connection = getConnection();

		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

		// This sql query will delete DVD using the id

		// WITHOUT PS
//		String sql = "DELETE FROM collection WHERE ID = " + id + ";";

		// WITH PS
		String sql = "DELETE FROM collection " + " WHERE ID = ?";

		// PrepareStaement prevents sql injection and executes faster
		// statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		// The numbers respresent the parameters in the query
		// 1 represents the first parameter in the query
		statement.setInt(1, ID);

		// Prints query to console
		System.out.println(sql);

		// Carries out the delete query
		statement.executeUpdate();

		// When the query is executed - the dvd has been deleted - print done to the
		// console
		System.out.println("Done");

		// Close connection database to prevent the error of "Database locked"
		connection.close();

	}

	public User validateUserInfo(String un, String pw) throws SQLException {

		// Creates an object connection
		Connection connection = getConnection();

		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

//		Selects the username, password, apikey and counts each row to see if the username and password matches

//		String sql = "SELECT username, password, apikey,"
//				+ "COUNT(*) as valid FROM Users WHERE username = '" + un + "' AND '" + pw + "';";

		String sql = "SELECT username, password, apikey, COUNT (*) as valid FROM Users WHERE username = ? AND password = ?";

		// PrepareStaement prevents sql injection and executes faster
		// statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		// The numbers respresent the parameters in the query
		// 1 represents the first parameter in the query
		statement.setString(1, un);
		statement.setString(2, pw);

		// Prints query to console
		System.out.println(sql);

		// WITHOUT PS
//		statement.executeQuery(sql);

		// Executes the sql string that is passed into the statement
		// sql IS NOT PASSED AS AN ARGUMENT - the prepared statement already did that
		// above
		ResultSet rs = statement.executeQuery();

		// While you got another row keep the while loop going - that's all next does
		while (rs.next()) {

			// Stores the result set of the row returned and is stored into int variable
			int valid = rs.getInt("valid");

			// If the row returned is greater than 0 - this means the user matched
			if (valid > 0) {

				// Hence in the console valid user is printed
				System.out.println("Valid User");

				// From the result set column username - placed into variable
				String username = rs.getString("username");
				String password = rs.getString("password");
				String api = rs.getString("apikey");

				// Close connection database to prevent the error of "Database locked"
				// Closing if user IS VALID
				connection.close();

				// Returns the user with the assigned values
				return new User(username, password, api);
			}
		}
		// Close connection if NOT VALID
		connection.close();

		// Nothing will be returned if nothing is returned
		// (not in the database as 0 is returned)
		return null;
	}

	public void insertUser(User user) throws SQLException {

		// Establishes connection to the database
		Connection connection = getConnection();

		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

		// Inserts the values into users table and stores into sql string

//		String sql = "INSERT INTO users (username,password,apikey)" + "VALUES ('" 
//				+ user.getUsername() + "', '"
//				+ user.getPassword()+"', '"
//				+ user.getApi()+"');";

		// ? added ad it is now a prepared statement for protection
		String sql = "INSERT INTO users (username, password, apikey)" + "VALUES (?, ?, ?)";

		// PrepareStaement prevents sql injection and executes faster
		// statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		// The numbers respresent the parameters in the query
		// 1 represents the first parameter in the query
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getApi());

		// Prints query to console
		System.out.println(sql);

		// Executes query
		statement.executeUpdate();

		// When the query is executed - the dvd has been deleted - print done to the
		// console
		System.out.println("Done");

		// Close connection database to prevent the error of "Database locked"
		connection.close();
	}

	public boolean checkKey(String apiKey) throws SQLException {

		// Establishes connection to the database
		Connection connection = getConnection();

		// Statement object created which will hold the sql string that will be executed
//		Statement statement = connection.createStatement();

		// Counts each row to see if the apiKey matches
//		String sql = "SELECT COUNT(*) as valid FROM Users WHERE apikey = '" + apiKey + "';";

		String sql = "SELECT COUNT(*) as valid FROM Users WHERE apikey = ?";

		// PrepareStaement prevents sql injection and executes faster
		// statement object created and sql string is passed as an arugument
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, apiKey);

		// Print query to console
		System.out.println(sql);

//		statement.executeQuery(sql);

		// Executes query
		ResultSet rs = statement.executeQuery();

		// Print done to the console when query executed
		System.out.println("Done");

		// While you got another row keep the while loop going - that's all next does
		while (rs.next()) {

			// Stores the result set of the row returned and is stored into int variable
			int valid = rs.getInt("valid");

			// If the row returned is greater than 0 - this means the user matched
			if (valid > 0) {

				// Closing if user IS VALID
				connection.close();

				// Returns boolean
				return true;
			}
		}
		// Closing if user is NOT VALID
		connection.close();

		// Returns boolean
		return false;
	}
}
