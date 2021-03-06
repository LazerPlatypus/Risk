package risk.models;

import java.io.Serializable;

import risk.models.enums.TerritoryName;
import risk.models.enums.UnitName;

public class Card implements Serializable{
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
