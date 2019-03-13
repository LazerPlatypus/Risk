package risk.models;

import java.io.Serializable;
import java.util.ArrayList;

import risk.controllers.viewControllers.interfaces.Displayable;
import risk.models.enums.TerritoryName;

public class Territory implements Selection, Displayable,Serializable{
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


	@Override
	public String displayName() {
		return getTerritoryName().toString();
	}


	@Override
	public String displayUnits() {
		StringBuilder sBuilder = new StringBuilder("Occupying Units: ");
		if (getOccupyingUnits().size()>0) {
		sBuilder.append(Integer.toString(getOccupyingUnits().size()));
		sBuilder.append(" ").append(getOccupyingUnits().get(0).getUnitColor().toString());
		}
		else {
			sBuilder.append("None.");
		}
		return sBuilder.toString();
	}


	@Override
	public String resourceLocation() {
		return getTerritoryName().toString().replaceAll("\\s+", "");
	}
}
