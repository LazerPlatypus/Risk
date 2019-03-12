package risk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import risk.controllers.viewControllers.Displayable;
import risk.models.enums.CountryName;
import risk.models.enums.TerritoryName;
import risk.models.enums.UnitColor;

public class Country implements Selection, Displayable, Serializable{
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


	@Override
	public String displayName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String displayUnits() {
		int[] unitCount = new int[] {0,0,0,0,0,0};
		for (Territory territory : getTerritories().values()) {
			ArrayList<Unit> units = territory.getOccupyingUnits();
			for (Unit unit : units) {
				unitCount[unit.getUnitColor().ordinal()] += 1;
			}
		}
		StringBuilder sBuilder = new StringBuilder("Units: ");
		for (int i =0; i < unitCount.length; i++) {
			if (unitCount[i]>0) {
			sBuilder.append("\n     ");
			sBuilder.append(UnitColor.values()[i].toString());
			sBuilder.append(": ").append(Integer.toString(unitCount[i]));
			} else {
				sBuilder.append("\n     ").append(UnitColor.values()[i].toString()).append(": None.");
			}
		}
		return sBuilder.toString();
	}
	
}
