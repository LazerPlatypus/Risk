package risk.controllers;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import risk.controllers.viewControllers.AlertBox;
import risk.controllers.viewControllers.NumberOptionBox;
import risk.controllers.viewControllers.ViewController;
import risk.models.Country;
import risk.models.Player;
import risk.models.Territory;
import risk.models.Unit;
import risk.models.enums.UnitColor;

public class Turn extends GameSetup{
	
	private static boolean isAttackPhase = false;
	
	public static void start() {
		System.out.println("Game setup finished!");
		upKeep();
	}
	
	public static void upKeep() {
		viewController.hideButton(0);
		currentView.showError("It is "+currentBoard.getActivePlayer().displayName()+"'s turn.\nplace all your inactive units on territories you control.");
		viewController.showButton(7);
	}
	
	public static void attackPhase() {
		AlertBox.display("Attack", "You've entered the attack phase");
		viewController.hideButton(7);
		isAttackPhase = true;
		
	}
	public static Territory territoryToAttackFrom;
	public static void startAttack() {
		if (isAttackPhase) {
			if (territoryToAttackFrom == null) {
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					AlertBox.display("Attack", "Attacking from: "+currentTerritory.getTerritoryName().toString());
					territoryToAttackFrom = currentTerritory;
				} else {
					AlertBox.display("Attack", "You can't attack from this country");
				}
			} else {
				if (!currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					AlertBox.display("Attack", "Attacking: "+currentTerritory.getTerritoryName().toString());
					attack(territoryToAttackFrom, currentTerritory);
				}
				else if (currentTerritory == territoryToAttackFrom) {
					territoryToAttackFrom=null;
				} else {
					AlertBox.display("Attack", "You can't attack this country");
				}
			}
		} else {
			AlertBox.display("Attack", "You can't attack yet");
		}
	}
	
	public static void attack(Territory territoryToAttackFrom, Territory territoryToAttack) {
		while (territoryToAttackFrom.getOccupyingUnits().size()>1 && territoryToAttack.getOccupyingUnits().size()>0) {
			int numOfUnitsToAttackWith = NumberOptionBox.display("Attack", "How many units do you want to attack with?", territoryToAttackFrom.getOccupyingUnits().size());
			int numOfUnitsToDefendWith = NumberOptionBox.display("Defend", "How many units do you want to defend with?", territoryToAttack.getOccupyingUnits().size());
			while (numOfUnitsToAttackWith > 1) {
				if (territoryToAttack.getOccupyingUnits().size()>1) {
					territoryToAttack.getOccupyingUnits().remove(territoryToAttack.getOccupyingUnits().size()-1);
				}
				numOfUnitsToAttackWith--;
			}
			while (numOfUnitsToDefendWith > 1) {
				if (territoryToAttackFrom.getOccupyingUnits().size()>1) {
					territoryToAttackFrom.getOccupyingUnits().remove(territoryToAttackFrom.getOccupyingUnits().size()-1);
				}
				numOfUnitsToDefendWith--;
			}
		}
		if (territoryToAttackFrom.getOccupyingUnits().size()>1) {
			AlertBox.display("Victory", "Attack Sucessful");
		} else {
			AlertBox.display("Loss", "Attack was unsuccessful"); //successful couldnt spell
		}
		isAttackPhase = false;
		territoryToAttackFrom = null;
	}
	
	public static void freeMove() {
		
	}
	
	public static void endTurn() {
		
	}
	
	public static void calculateUnitsRecieved() {
		int unitsToRecieve = 0;
		unitsToRecieve += currentBoard.getActivePlayer().getOwnedTerritories().size();
		UnitColor colorToCheckFor = currentBoard.getActivePlayer().getActiveUnits().get(0).getUnitColor();
		for (Country country : currentBoard.getMap().getCountries().values()) {
			int ownedCount = 0;
			for (Territory territory : country.getTerritories().values()) {
				if (territory.getOccupyingUnits().size()>0) {
					if (territory.getOccupyingUnits().get(0).getUnitColor() == colorToCheckFor) {
						ownedCount += 1;
					}
				}
			}
			if (ownedCount == country.getTerritories().size()) {
				unitsToRecieve += 5;
			}
		}
	}
	
	public static void placeUnit() {
		if (selectedTerritory != null) {
			if (selectedTerritory.getOccupyingUnits().get(0).getUnitColor() == currentBoard.getActivePlayer().getActiveUnits().get(0).getUnitColor()) {
			Player currentPlayer = currentBoard.getActivePlayer();
			ArrayList<Unit> currentPlayerActiveUnits = currentPlayer.getActiveUnits();
			if (currentPlayer.getInactiveUnits().size()>0) {
				currentPlayerActiveUnits.add(currentPlayer.getInactiveUnits().get(currentPlayer.getInactiveUnits().size()-1));
				selectedTerritory.getOccupyingUnits().add(currentPlayer.getInactiveUnits().get(currentPlayer.getInactiveUnits().size()-1));
				currentPlayer.getInactiveUnits().remove(currentPlayer.getInactiveUnits().size()-1);
				boolean hasTerritory = false;
				for (Territory territory : currentPlayer.getOwnedTerritories()) {
					if (territory == selectedTerritory) {
						hasTerritory = true;
					}
				}
				if (!hasTerritory) {
					currentPlayer.getOwnedTerritories().add(selectedTerritory);					
				}
			}
			currentView.updateDisplay();
			turnCount++;
			} else {
				System.out.println("Displaying error");
				AlertBox.display("Whoopsie-doo", "You cannot place a unit there.\nThat space is occupied by an enemy!");
			}
			System.out.println(turnCount);
			if (arePlayersOutOfUnits(currentBoard.getActivePlayer())) {
				attackPhase();
			}
		}
	}
}
