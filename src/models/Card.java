package models;

import models.enums.TerritoryName;
import models.enums.UnitName;

public class Card {
	//class variables
	private UnitName unitOnCard;
	private TerritoryName territoryOnCard;

	
	//constructors
	public Card(UnitName unitOnCard, TerritoryName territoryOnCard) {
		super();
		this.unitOnCard = unitOnCard;
		this.territoryOnCard = territoryOnCard;
	}
	
	
	//getters
	public UnitName getUnitOnCard() {
		return unitOnCard;
	}
	public TerritoryName getTerritoryOnCard() {
		return territoryOnCard;
	}
	
	
	//setters
	public void setUnitOnCard(UnitName unitOnCard) {
		this.unitOnCard = unitOnCard;
	}
	public void setTerritoryOnCard(TerritoryName territoryOnCard) {
		this.territoryOnCard = territoryOnCard;
	}
	
	
}
