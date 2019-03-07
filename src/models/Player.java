package models;

import java.util.ArrayList;

public class Player implements Selection{
	//class variables
	private String name;
	private ArrayList<Unit> inactiveUnits;
	private ArrayList<Unit> activeUnits;
	private ArrayList<Card> cards;
	private ArrayList<Territory> ownedTerritories;
	
	
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
	
}
