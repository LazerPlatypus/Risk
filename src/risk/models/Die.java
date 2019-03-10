package risk.models;

import java.util.Random;

import risk.models.enums.DieType;

public class Die {
	//class variables
	private DieType dieType;
	private Random rng;
	private int outcome;
	
	
	//constructors
	public Die(DieType dieType, Random rng, int outcome) {
		this.dieType = dieType;
		this.rng = rng;
		this.outcome = outcome;
	}
	
	
	//getters
	public DieType getDieType() {
		return dieType;
	}
	public Random getRng() {
		return rng;
	}
	public int getOutcome() {
		return outcome;
	}
	
	
	//setters
	public void setDieType(DieType dieType) {
		this.dieType = dieType;
	}
	public void setRng(Random rng) {
		this.rng = rng;
	}
	public void setOutcome(int outcome) {
		this.outcome = outcome;
	}
	
	
}
