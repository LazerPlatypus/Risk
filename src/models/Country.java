package models;

import java.util.HashMap;

import models.enums.CountryName;
import models.enums.TerritoryName;

public class Country {
	//class variables
	private CountryName countryName;
	private HashMap<TerritoryName, Territory> territories;
	
	
	//constructors
	public Country(CountryName countryName, HashMap<TerritoryName, Territory> territories) {
		this.countryName = countryName;
		this.territories = territories;
	}
	
	
	//getters
	public CountryName getCountryName() {
		return countryName;
	}
	
	public HashMap<TerritoryName, Territory> getTerritories() {
		return territories;
	}
	
	
	//setters
	public void setCountryName(CountryName countryName) {
		this.countryName = countryName;
	}
	
	public void setTerritories(HashMap<TerritoryName, Territory> territories) {
		this.territories = territories;
	}
	
}
