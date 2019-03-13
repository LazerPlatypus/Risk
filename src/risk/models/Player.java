package risk.models;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.geometry.Side;
import risk.controllers.viewControllers.interfaces.Displayable;

public class Player implements Selection, Displayable, Serializable{
	//class variables
	private String name;
	private ArrayList<Unit> inactiveUnits;
	private ArrayList<Unit> activeUnits;
	private ArrayList<Card> cards;
	private ArrayList<Territory> ownedTerritories;
	private int mostOwnedTerritories;
	private int mostActiveUnits;
	private int territoriesTaken;
	private int territoriesLost;
	private int timesAttacked;
	private int timeDefended;
	
	
	//constructors
	public Player(String name, ArrayList<Unit> inactiveUnits, ArrayList<Unit> activeUnits,
			ArrayList<Card> cards, ArrayList<Territory> ownedTerritories ) {
		this.name = name;
		this.inactiveUnits = inactiveUnits;
		this.activeUnits = activeUnits;
		this.cards = cards;
		this.ownedTerritories = ownedTerritories;
	}
	
	
	//getters
	public String getName() {
		return name;
	}
	
	public ArrayList<Unit> getInactiveUnits() {
		return inactiveUnits;
	}
	
	public ArrayList<Unit> getActiveUnits() {
		return activeUnits;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public ArrayList<Territory> getOwnedTerritories() {
		return ownedTerritories;
	}
	
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setInactiveUnits(ArrayList<Unit> inactiveUnits) {
		this.inactiveUnits = inactiveUnits;
	}
	
	public void setActiveUnits(ArrayList<Unit> activeUnits) {
		this.activeUnits = activeUnits;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void setOwnedTerritories(ArrayList<Territory> ownedTerritories) {
		this.ownedTerritories = ownedTerritories;
	}


	@Override
	public String displayName() {
		return getName();
	}


	@Override
	public String displayUnits() {
		StringBuilder sBuilder = new StringBuilder("Active Units: ");
		if (getActiveUnits().size()>0) {
			sBuilder.append(getActiveUnits().size()).append(" (").append(getActiveUnits().get(0).getUnitColor().toString()).append(")");
		} else {
			sBuilder.append("None.");
		}
		sBuilder.append("\nInactive Units: ");
		if (getInactiveUnits().size()>0) {
			sBuilder.append(getInactiveUnits().size()).append(" (").append(getInactiveUnits().get(0).getUnitColor().toString()).append(")");
		} else {
			sBuilder.append("None.");
		}
		sBuilder.append("\nOwned Territories: ");
		if (getOwnedTerritories().size()>0) {
			for (Territory territory : getOwnedTerritories()) {
				sBuilder.append(territory.getTerritoryName().toString()).append(", ");
			}
		} else {
			sBuilder.append("None.");
		}
		sBuilder.append("\nCards: ");
		if (getCards().size()>0) {
			for (int i = 0; i < getCards().size(); i++) {
				sBuilder.append(getCards().get(i).getUnitOnCard()).append(", ").append(getCards().get(i).getTerritoryOnCard());
				if (i < getCards().size()-1) {
					sBuilder.append(" | ");
				}
			}
		} else {
			sBuilder.append("None.");
		}
		return sBuilder.toString();
	}


	@Override
	public String resourceLocation() {
		String resouceLocation = "";
		if (getActiveUnits() != null && getActiveUnits().size()>0) {
			resouceLocation = getActiveUnits().get(0).getUnitColor().toString()+"Player";
		}
		else if (getInactiveUnits() != null && getInactiveUnits().size()>0) {
			resouceLocation = getInactiveUnits().get(0).getUnitColor().toString()+"Player";
		} else {
			resouceLocation = "Player";
		}
		return resouceLocation;
	}
	
	public int getMostActiveUnits() {
		return mostActiveUnits;
	}
	public int getMostOwnedTerritories() {
		return mostOwnedTerritories;
	}
	public int getTerritoriesLost() {
		return territoriesLost;
	}
	public int getTerritoriesTaken() {
		return territoriesTaken;
	}
	public int getTimeDefended() {
		return timeDefended;
	}
	public int getTimesAttacked() {
		return timesAttacked;
	}
	public void setMostActiveUnits(int mostActiveUnits) {
		this.mostActiveUnits = mostActiveUnits;
	}
	public void setMostOwnedTerritories(int mostOwnedTerritories) {
		this.mostOwnedTerritories = mostOwnedTerritories;
	}
	public void setTerritoriesLost(int territoriesLost) {
		this.territoriesLost = territoriesLost;
	}
	public void setTerritoriesTaken(int territoriesTaken) {
		this.territoriesTaken = territoriesTaken;
	}
	public void setTimeDefended(int timeDefended) {
		this.timeDefended = timeDefended;
	}
	public void setTimesAttacked(int timesAttacked) {
		this.timesAttacked = timesAttacked;
	}
	
	
}
