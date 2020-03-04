package models;

/* This class is a model class for the DVD object */
public class Dvd {
	
	// Instance variables
	private int id;
	private String title;
	private String genre;
	private int year;

	//Constructor for the DVD object to initialise the object
	public Dvd(int id, String title, String genre, int year) {
		//Your assigning the variables you set earier to the arguments being passed
		//Identifying parameter values
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}

	//Setters and Getter methods to access and update values
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
