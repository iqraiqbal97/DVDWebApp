package models;

/* This class is a model class for the user object */
public class User {
	
	private String username;
	private String password;
	private String api;

	//Constructor initialises a newly created object
	public User(String username, String password, String api) {
		this.username = username;
		this.password = password;
		this.api = api;
	}

	//Setters and getters = access and update values
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

}
