package risk.models;

import java.io.Serializable;
import java.util.HashMap;

import risk.models.enums.CountryName;

public class Map implements Serializable{
	//class variables
	private HashMap<CountryName, Country> countries;

	
	//constructors
	public Map(HashMap<CountryName, Country> countries) {
		this.countries = countries;
	}
	
	
	//getters
	public HashMap<CountryName, Country> getCountries() {
		return countries;
	}
	
	
	//setters
	public void setCountries(HashMap<CountryName, Country> countries) {
		this.countries = countries;
	}
}
