package risk.models;

import java.util.ArrayList;

import risk.models.enums.TerritoryName;

public class Territory implements Selection{
	//class variables
	private ArrayList<Unit> occupyingUnits;
	private Territory[] adjacentTerritories;
	private TerritoryName territoryName;
	
	
	//constructors
	public Territory(ArrayList<Unit> occupyingUnits, TerritoryName territoryName) {
		this.occupyingUnits = occupyingUnits;
		this.territoryName = territoryName;
	}
	
	
	//getters
	public ArrayList<Unit> getOccupyingUnits() {
		return occupyingUnits;
	}
	
	public Territory[] getAdjacentTerritories() {
		return adjacentTerritories;
	}
	
	public TerritoryName getTerritoryName() {
		return territoryName;
	}
	
	
	//setters
	public void setOccupyingUnits(ArrayList<Unit> occupyingUnits) {
		this.occupyingUnits = occupyingUnits;
	}
	
	public void setAdjacentTerritories(Territory[] adjacentTerritories) {
		this.adjacentTerritories = adjacentTerritories;
	}
	
	public void setTerritoryName(TerritoryName territoryName) {
		this.territoryName = territoryName;
	}
}
