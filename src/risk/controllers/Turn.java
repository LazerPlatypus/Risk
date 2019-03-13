package risk.controllers;

import java.util.ArrayList;
import risk.controllers.viewControllers.AlertBox;
import risk.controllers.viewControllers.NumberOptionBox;
import risk.controllers.viewControllers.NumberSliderBox;
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
	
	//Class variables
	//because turns can be skipped, and the flow of the project is fairly fluid,
	//we are using booleans to figure out what phase of the turn it is.
	//traditionally, turns are taken as follows:
	//upkeep ~ where you get units for the turn and place them
	//attack ~ where you can attack other territories as many times as you have units for
	//free move ~ where you can move units from ONE territory to ONE other territory
	//endphase ~ where you get a card if you earned one.
	//however, the player doens't have to attack
	//and you can skip the free move -- making it more complicated to keep track of
	private static boolean isAttackPhase = false;
	private static boolean isFreeMovePhase = false;
	private static boolean getsCard = false;
	public static Territory territoryToAttackFrom;
	private static Territory territoryToMoveFrom;
	
	//start logic
	
	public static void start() {
		viewController.showGame();
		currentBoard.setGameState(new int[] {1,0});
		System.out.println("Game setup finished!");
		upKeep();
	}
	
	//game flow logic ~~ organized by the earliest point the user can trigger it.
	
	public static void upKeep() {
		//hides the 'place unit' button used by GameSetup
		viewController.hideButton(0);
		//calculates the number of units the player has earned
		int unitsToRecieve = calculateUnitsRecieved();
		//adds a new unit to the current players inactive unit list for every unit they earned
		for (int i = 0; i < unitsToRecieve; i++) {
			currentBoard.getActivePlayer().getInactiveUnits().add(new Unit(currentBoard.getActivePlayer().getActiveUnits().get(0).getUnitColor(), UnitStatus.INACTIVE));
		}
		//informs the user who's turn it is, and provides instructions
		currentView.showError("It is "+currentBoard.getActivePlayer().displayName()+"'s turn.\nplace all your inactive units on territories you control.");
		//shows the placeUnit button used by Turn
		viewController.showButton(7);
	}
	
	
	//if the user want to skip the current phase, this allows them to do it.
	//if its the attack phase, this will end it and start the freeMovePhase
	//if its the freeMovePhase, this will end it and start the endTurnPhase.
	//if its any other phase, it does nothing, but alerts the user that it didn't do anything.
	
	public static void next() {
		if (isAttackPhase) {
			isAttackPhase = false;
			freeMovePhase();
		}
		else if (isFreeMovePhase) {
			isFreeMovePhase = false;
			endTurn();
		} else {
			AlertBox.display("Next", "You must complete the current phase before proceeding");
		}
	}
	
	//after all the units have been placed, the attackPhase begins.
	
	public static void attackPhase() {
		isAttackPhase = true;
		//notifies the user that the attack phase has begun
		AlertBox.display("Attack", "You've entered the attack phase");
		//hides the 'place unit' button used by Turn
		viewController.hideButton(7);
		//shows the 'attack' button
		viewController.showButton(11);
		//shows the 'skip' button
		viewController.showButton(12);
	}
	
	//after the attack phase is over, the freeMovePhase begins
	
	public static void freeMovePhase() {
		isFreeMovePhase = true;	
		//notifies the user that the free move phase has begun
		AlertBox.display("Free Move", "You've entered the free move phase");
		//hides the 'attack' button
		viewController.hideButton(11);
		//shows the 'free move' button
		viewController.showButton(8);
	}
	
	//after the freeMovePhase is over, endTurn begins
	
	public static void endTurn() {
		//checks if the current player earned a card.
		if (getsCard) {
			//gives the current player a card TODO not fully implemented yet
			currentBoard.getActivePlayer().getCards().add(new Card(UnitName.CALVERY, TerritoryName.GREAT_BRITAIN));
		}
		//sets getCard to false for the next player
		getsCard = false;
		//increments the turn count
		turnCount++;
		//changes the game-state based off the turn count
		//from: GameSetup
		//the gameState is based off the phase of the game, and what player is active, so we need to set that.
		//as we are still in setup, the phase stays 0, but the active player is determined by taking the turnCount
		//and dividing it by the number of players in PlayerOrder, with the result being what's leftover.
		currentBoard.setGameState(new int[] {1, turnCount%currentBoard.getPlayerOrder().length});
		//changes the current player based of the game-state.
		//from: GameSetup
		//the active player is just a copy of the GameState, so we find it by taking the index of the PlayerOrder via the GameState 
		currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[currentBoard.getGameState()[1]]);
		
	}
	
	//game-play logic. organized by the (rough) order it will probably get called.
	
	//basically a copy-paste from GameSetup. the only difference is that every territory is occupied now.
	public static void placeUnit() {
		//checks to make sure the selected territory isn't null
		if (selectedTerritory != null) {
			//done to reduce calls and increase legibility
			Player currentPlayer = currentBoard.getActivePlayer();
			//makes sure that the selected territory is the same color as the player's by grabbing first occupying unit
			//from the territory, and checking if it's equal to the the color of the first inactive unit of the active player.
			if (selectedTerritory.getOccupyingUnits().get(0).getUnitColor() == currentPlayer.getActiveUnits().get(0).getUnitColor()) {
				//done to reduce calls and increase legibility
				ArrayList<Unit> currentPlayerActiveUnits = currentPlayer.getActiveUnits();
				//adds a unit to the current players' active units from the current player's inactive units (always takes from the rear so checking index0 is safe)
				currentPlayerActiveUnits.add(currentPlayer.getInactiveUnits().get(currentPlayer.getInactiveUnits().size()-1));
				//adds the same unit to the selected territory
				selectedTerritory.getOccupyingUnits().add(currentPlayer.getInactiveUnits().get(currentPlayer.getInactiveUnits().size()-1));
				//removes that unit from the current players inactive unit list
				currentPlayer.getInactiveUnits().remove(currentPlayer.getInactiveUnits().size()-1);
				//updates the display so the user knows what they did
				currentView.updateDisplay();
			} else {
				//testing purposes
//				System.out.println("Displaying error");
				//alerts the user if they try to place a unit in an invalid location
				AlertBox.display("Whoopsie-doo", "You cannot place a unit there.\nThat space is occupied by an enemy!");
			}
			//testing purposes
//			System.out.println(turnCount);
			//checks if the player is out of units
			if (arePlayersOutOfUnits(currentBoard.getActivePlayer())) {
				//starts the attack phase if true
				attackPhase();
			}
		}
	}
	
	
	
	public static void setupAttack() {
		if (isAttackPhase) {
			if (territoryToAttackFrom == null) {
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					AlertBox.display("Attack", "Attacking from: "+currentTerritory.getTerritoryName().toString());
					territoryToAttackFrom = currentTerritory;
				} else {
					AlertBox.display("Attack", "You can't attack from this Territory");
				}
			} else {
				boolean allow = false;
				for (Territory territory : selectedTerritory.getAdjacentTerritories()) {
					if (territory == territoryToAttackFrom) {
						allow = true;
					}
				}
				if (!currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory) && allow) {
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
	
	
	public static void setupFreeMove() {
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
				boolean allow = false;
				for (Territory territory : selectedTerritory.getAdjacentTerritories()) {
					if (territory == territoryToMoveFrom) {
						allow = true;
					}
				}
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory) && allow) {
					AlertBox.display("Free Move", "Moving to: "+currentTerritory.getTerritoryName().toString());
					freeMove(territoryToMoveFrom, currentTerritory);
					next();
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
			if (defendingPlayer.getOwnedTerritories().size()<1) {
				Player[] tempPlayerArray = new Player[currentBoard.getPlayerOrder().length-1];
				int internalCounter = 0;
				for (int i = 0; i < currentBoard.getPlayerOrder().length; i++) {
					if (currentBoard.getPlayerOrder()[i]== defendingPlayer) {
					} else {
						tempPlayerArray[internalCounter] = currentBoard.getPlayerOrder()[i];
						internalCounter++;
					}
				}
				if (currentBoard.getPlayerOrder().length<2) {
					RiskController.winGame();
				}
			}
			currentBoard.getActivePlayer().getOwnedTerritories().add(territoryToAttack);
			getsCard = true;
		} else {
			AlertBox.display("Loss", "Attack was unsuccessful"); //successful couldn't spell
		}
		Turn.territoryToAttackFrom = null;
	}
	
	public static void freeMove(Territory movingFromTerritory, Territory movingToTerritory) {
		int numOfUnitsToMove = NumberSliderBox.display("Free Move", "How many units would you like to move?", 1, movingFromTerritory.getOccupyingUnits().size()-1);
		for (int i = 0; i < numOfUnitsToMove; i++) {
			movingToTerritory.getOccupyingUnits().add(movingFromTerritory.getOccupyingUnits().get(movingFromTerritory.getOccupyingUnits().size()-1));
			movingFromTerritory.getOccupyingUnits().remove(movingFromTerritory.getOccupyingUnits().size()-1);
		}
		AlertBox.display("Free Move", "Successfuly moved "+numOfUnitsToMove+" Units to "+movingToTerritory.getTerritoryName().toString());
		Turn.territoryToMoveFrom = null;
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
	
	
}
