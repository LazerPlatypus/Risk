package risk.controllers;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import risk.controllers.viewControllers.AlertBox;
import risk.controllers.viewControllers.NumberOptionBox;
import risk.controllers.viewControllers.NumberSliderBox;
import risk.controllers.viewControllers.ViewController;
import risk.models.Card;
import risk.models.Country;
import risk.models.Player;
import risk.models.Territory;
import risk.models.Unit;
import risk.models.enums.TerritoryName;
import risk.models.enums.UnitColor;
import risk.models.enums.UnitName;
import risk.models.enums.UnitStatus;

public class Turn extends GameSetup{
	
	private static boolean isAttackPhase = false;
	private static boolean isFreeMovePhase = false;
	private static boolean getsCard = false;
	public static void start() {
		viewController.showGame();
		System.out.println("Game setup finished!");
		upKeep();
	}
	
	public static void upKeep() {
		viewController.hideButton(0);
		int unitsToRecieve = calculateUnitsRecieved();
		for (int i = 0; i < unitsToRecieve; i++) {
			currentBoard.getActivePlayer().getInactiveUnits().add(new Unit(currentBoard.getActivePlayer().getActiveUnits().get(0).getUnitColor(), UnitStatus.INACTIVE));
		}
		currentView.showError("It is "+currentBoard.getActivePlayer().displayName()+"'s turn.\nplace all your inactive units on territories you control.");
		viewController.showButton(7);
	}
	
	public static void attackPhase() {
		AlertBox.display("Attack", "You've entered the attack phase");
		viewController.hideButton(7);
		isAttackPhase = true;
		viewController.showButton(11);
	}
	public static void freeMovePhase() {
		AlertBox.display("Free Move", "You've entered the free move phase");
		viewController.hideButton(11);
		isFreeMovePhase = true;
	}
	public static Territory territoryToAttackFrom;
	public static void startAttack() {
		if (isAttackPhase) {
			if (territoryToAttackFrom == null) {
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					AlertBox.display("Attack", "Attacking from: "+currentTerritory.getTerritoryName().toString());
					territoryToAttackFrom = currentTerritory;
				} else {
					AlertBox.display("Attack", "You can't attack from this Territory");
				}
			} else {
				if (!currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					AlertBox.display("Attack", "Attacking: "+currentTerritory.getTerritoryName().toString());
					attack(territoryToAttackFrom, currentTerritory);
				}
				else if (currentTerritory == territoryToAttackFrom) {
					territoryToAttackFrom=null;
				} else {
					AlertBox.display("Attack", "You can't attack this Territory");
				}
			}
		} else {
			AlertBox.display("Attack", "You can't attack yet");
		}
	}
	private static Territory territoryToMoveFrom;
	public static void startFreeMove() {
		if (isFreeMovePhase) {
			if (territoryToMoveFrom == null) {
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					if (currentTerritory.getOccupyingUnits().size()>1) {
						AlertBox.display("Free Move", "Moving from: "+currentTerritory.getTerritoryName().toString());
						territoryToMoveFrom = currentTerritory;
					} else {
						AlertBox.display("Free Move", "There aren't enough units to move from this territory");
					}
				} else {
					AlertBox.display("Free Move", "You don't own this Territory");
				}
			} else {
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					AlertBox.display("Free Move", "Moving to: "+currentTerritory.getTerritoryName().toString());
					freeMove(territoryToMoveFrom, currentTerritory);
				}
				else if (currentTerritory == territoryToAttackFrom) {
					territoryToMoveFrom=null;
				} else {
					AlertBox.display("Attack", "You can't attack this country");
				}
			}
		} else {
			AlertBox.display("Free Move", "You can't free move yet");
		}
	}
	
	public static void attack(Territory territoryToAttackFrom, Territory territoryToAttack) {
		Player defendingPlayer = null;
		UnitColor defendingPlayerColor = territoryToAttack.getOccupyingUnits().get(0).getUnitColor();
		for (Player player : currentBoard.getPlayerOrder()) {
			if (defendingPlayerColor == player.getActiveUnits().get(0).getUnitColor()) {
				defendingPlayer = player;
			}
		}
		while (territoryToAttackFrom.getOccupyingUnits().size()>1 && territoryToAttack.getOccupyingUnits().size()>0) {
			int numOfUnitsToAttackWith = NumberOptionBox.display("Attack", "How many units do you want to attack with?", territoryToAttackFrom.getOccupyingUnits().size());
			int numOfUnitsToDefendWith = NumberOptionBox.display("Defend", "How many units do you want to defend with?", territoryToAttack.getOccupyingUnits().size()>2?2:territoryToAttack.getOccupyingUnits().size());
			System.out.println("Num of units in territory to attack: "+territoryToAttack.getOccupyingUnits().size());
			while (numOfUnitsToAttackWith > 0) {
				if (territoryToAttack.getOccupyingUnits().size()>0) {
					territoryToAttack.getOccupyingUnits().remove(territoryToAttack.getOccupyingUnits().size()-1);
				}
				numOfUnitsToAttackWith--;
				System.out.println("units left attacking: "+numOfUnitsToAttackWith);
			}
			System.out.println("Num of units in territory to attack: "+territoryToAttack.getOccupyingUnits().size());
			System.out.println("Num of units in territory attaking: "+territoryToAttackFrom.getOccupyingUnits().size());
			while (numOfUnitsToDefendWith > 0) {
				if (territoryToAttackFrom.getOccupyingUnits().size()>1) {
					territoryToAttackFrom.getOccupyingUnits().remove(territoryToAttackFrom.getOccupyingUnits().size()-1);
				}
				numOfUnitsToDefendWith--;
				System.out.println("units left defending: "+numOfUnitsToDefendWith);
			}
			System.out.println("Num of units in territory attaking: "+territoryToAttackFrom.getOccupyingUnits().size());
		}
		if (territoryToAttackFrom.getOccupyingUnits().size()>1) {
			AlertBox.display("Victory", "Attack Sucessful");
			freeMove(territoryToAttackFrom, territoryToAttack);
			defendingPlayer.getOwnedTerritories().remove(territoryToAttack);
			currentBoard.getActivePlayer().getOwnedTerritories().add(territoryToAttack);
			getsCard = true;
		} else {
			AlertBox.display("Loss", "Attack was unsuccessful"); //successful couldnt spell
		}
		isAttackPhase = false;
		Turn.territoryToAttackFrom = null;
		freeMovePhase();
	}
	
	public static void freeMove(Territory movingFromTerritory, Territory movingToTerritory) {
		int numOfUnitsToMove = NumberSliderBox.display("Free Move", "How many units would you like to move?", 1, movingFromTerritory.getOccupyingUnits().size()-1);
		for (int i = 0; i < numOfUnitsToMove; i++) {
			movingToTerritory.getOccupyingUnits().add(movingFromTerritory.getOccupyingUnits().get(movingFromTerritory.getOccupyingUnits().size()-1));
			movingFromTerritory.getOccupyingUnits().remove(movingFromTerritory.getOccupyingUnits().size()-1);
		}
		AlertBox.display("Free Move", "Successfuly moved "+numOfUnitsToMove+" Units to "+movingToTerritory.getTerritoryName().toString());
		isFreeMovePhase = false;
		Turn.territoryToMoveFrom = null;
		endTurn();
	}
	
	public static void endTurn() {
		if (getsCard) {
			currentBoard.getActivePlayer().getCards().add(new Card(UnitName.CALVERY, TerritoryName.GREAT_BRITAIN_AND_IRELAND));
		}
		getsCard = false;
		turnCount++;
		currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[turnCount%currentBoard.getPlayerOrder().length]);
		
	}
	
	public static int calculateUnitsRecieved() {
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
		return unitsToRecieve;
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
