package risk.models;

import risk.models.enums.UnitColor;
import risk.models.enums.UnitStatus;

public class Unit {
	//class variables
	private UnitColor unitColor;
	private UnitStatus unitStatus;
	
	
	//contstructors
	public Unit(UnitColor unitColor, UnitStatus unitStatus) {
		this.unitColor = unitColor;
		this.unitStatus = unitStatus;
	}
	
	
	//getters
	public UnitColor getUnitColor() {
		return unitColor;
	}
	
	public UnitStatus getUnitStatus() {
		return unitStatus;
	}
	
	
	//setters
	public void setUnitColor(UnitColor unitColor) {
		this.unitColor = unitColor;
	}
	
	public void setUnitStatus(UnitStatus unitStatus) {
		this.unitStatus = unitStatus;
	}
}
