package risk.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import risk.controllers.viewControllers.AlertBox;
import risk.controllers.viewControllers.CardTradeBox;
import risk.controllers.viewControllers.ConfirmBox;
import risk.controllers.viewControllers.DieBox;
import risk.controllers.viewControllers.NumberOptionBox;
import risk.controllers.viewControllers.NumberSliderBox;
import risk.models.Card;
import risk.models.Country;
import risk.models.Die;
import risk.models.Player;
import risk.models.Territory;
import risk.models.Unit;
import risk.models.enums.UnitColor;
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
		viewController.setPlayesToDisplay(currentBoard.getPlayerOrder().length);
		viewController.showGame();
		turnCount = currentBoard.getGameState()[1];
		currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[turnCount]);
//		System.out.println("Game setup finished!");
		//hides the 'place unit' button used in Turn
		viewController.hideButton(7);
				//hides the 'free move' button used in Turn
		viewController.hideButton(8);
				//hides the 'next' button used in Turn
		viewController.hideButton(12);
				//hides the 'attack' button used in Turn
		viewController.hideButton(11);
		upKeep();
	}
	
	//game flow logic ~~ organized by the earliest point the user can trigger it.
	
	public static void upKeep() {
		//hides the 'place unit' button used by GameSetup
		viewController.hideButton(0);
		//calculates the number of units the player has earned
		isAttackPhase = false;
		isFreeMovePhase = false;
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
		System.out.println("Ended the turn");
		//checks if the current player earned a card.
		if (getsCard) {
			//gives the current player a card 
			if (currentBoard.getUndrawnCards().size()>0) {
				Random rng = new Random();
				Card card = currentBoard.getUndrawnCards().get(rng.nextInt(currentBoard.getUndrawnCards().size()));
				currentBoard.getUndrawnCards().remove(card);
				currentBoard.getActivePlayer().getCards().add(card);
			} else {
				AlertBox.display("Draw Card", "There are no more cards remaining");
			}
		}
		//sets getCard to false for the next player
		getsCard = false;
		//increments the turn count
		turnCount++;
		if (turnCount>currentBoard.getPlayerOrder().length) {
			turnCount = 0;
		}
		//changes the game-state based off the turn count
		//from: GameSetup
		//the gameState is based off the phase of the game, and what player is active, so we need to set that.
		//as we are still in setup, the phase stays 0, but the active player is determined by taking the turnCount
		//and dividing it by the number of players in PlayerOrder, with the result being what's leftover.
		currentBoard.setGameState(new int[] {1,turnCount});
//		System.out.println(turnCount);
//		System.out.println(currentBoard.getPlayerOrder().length%turnCount);
		//changes the current player based of the game-state.
		//from: GameSetup
		//the active player is just a copy of the GameState, so we find it by taking the index of the PlayerOrder via the GameState 
		currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[currentBoard.getGameState()[1]]);
		System.out.println(currentBoard.getActivePlayer().displayName());
		upKeep();
	}
	
	//game-play logic. organized by the (rough) order it will probably get called.
	
	
	//this method calculates how many units the current player should recieve at the start of their turn.
	public static int calculateUnitsRecieved() {
		int unitsToRecieve = 0;
		//players get 1 unit per territory
		unitsToRecieve += currentBoard.getActivePlayer().getOwnedTerritories().size();
		System.out.println(unitsToRecieve);
		//players get 5 units per country owned, to check if the player owned a country, we need to:
		//save the color of the player, it makes the code easier to read, and reduces calls
		UnitColor colorToCheckFor = currentBoard.getActivePlayer().getActiveUnits().get(0).getUnitColor();
		//loop through every country in the map of the current board
		for (Country country : currentBoard.getMap().getCountries().values()) {
			//we assume that the player doesn't own any territories in the country to start.
			int territoryOwnedCount = 0;
			//loop through every territory in the country
			for (Territory territory : country.getTerritories().values()) {
				//check to make sure the territory has at least one unit (prevents null pointers)
				if (territory.getOccupyingUnits().size()>0) {
					//check if the color of unit at the first index of the territory is the same
					//as the player's unit color
					if (territory.getOccupyingUnits().get(0).getUnitColor() == colorToCheckFor) {
						//adds that territory to the total owned territories
						territoryOwnedCount += 1;
					}
				}
			}
			//if the total of owned territories is the same as there are territories in the country
			//the player owns every territory in the country
			if (territoryOwnedCount == country.getTerritories().size()) {
				//they receive their 5 units.
				unitsToRecieve += 5;
			}
		}
		
		
		System.out.println(unitsToRecieve);
		unitsToRecieve += setupTradeCards();
		System.out.println(unitsToRecieve);
		return unitsToRecieve;
	}
	
	public static int setupTradeCards() {
		int unitsToAdd = 0;
		ArrayList<Card> currentPlayerCards = currentBoard.getActivePlayer().getCards();
		ArrayList<Card[]> cardPairs = new ArrayList<>();
		ArrayList<Card> cardPair = new ArrayList<>();
		for (Card card : currentPlayerCards) {
			cardPair.add(card);
		}
		if (currentPlayerCards.size()==4) {
			cardPair.add(currentPlayerCards.get(0));
		} 
		else if (currentPlayerCards.size()==5) {
			cardPair.add(currentPlayerCards.get(0));
			cardPair.add(currentPlayerCards.get(1));
		}
		if (cardPair.size()>2) {
			for (int i = 2; i < cardPair.size(); i++) {
				if (cardPair.get(i-2).getUnitOnCard() == cardPair.get(i-1).getUnitOnCard() && cardPair.get(i-1).getUnitOnCard() == cardPair.get(i).getUnitOnCard()) {
					cardPairs.add(new Card[] {cardPair.get(i-2), cardPair.get(i-1), cardPair.get(i)});
				}
				else if (cardPair.get(i-2).getUnitOnCard() != cardPair.get(i-1).getUnitOnCard() && cardPair.get(i-1).getUnitOnCard() != cardPair.get(i).getUnitOnCard() && cardPair.get(i).getUnitOnCard() != cardPair.get(i-2).getUnitOnCard()) {
					cardPairs.add(new Card[] {cardPair.get(i-2), cardPair.get(i-1), cardPair.get(i)});
				}
			}
		}
		if (currentPlayerCards.size()>4) {
			AlertBox.display("Trade Cards", "You have 5 cards, you must trade your cards");
			unitsToAdd = tradeCards(cardPairs, false);
		}
		else if (cardPair.size()>1) {
			if (ConfirmBox.display("Trade Cards", "You can trade cards. would you like to?")) {
				unitsToAdd = tradeCards(cardPairs, true);
			}
		}
		return unitsToAdd;
	}
	
	public static int tradeCards(ArrayList<Card[]> cardPairs, boolean canRefuse) {
		int unitsToAdd = 0;
		String[][] cardDescriptions = new String[cardPairs.size()][];
		for (int i = 0; i < cardPairs.size(); i++) {
			for (int j = 0; j < cardPairs.get(i).length; j++) {
				cardDescriptions[i][j] = cardPairs.get(i)[j].getUnitOnCard().toString()+"\n"+cardPairs.get(i)[j].getTerritoryOnCard().toString(); 
			}
		}
		int cardPairToRemove = CardTradeBox.display(cardDescriptions, canRefuse);
		if (cardPairToRemove>=0) {
			Card[] cardsToRemove = cardPairs.get(cardPairToRemove);
			for (Card card : cardsToRemove) {
				for (Territory territory : currentBoard.getActivePlayer().getOwnedTerritories()) {
					if (territory.getTerritoryName() == card.getTerritoryOnCard()) {
						if (unitsToAdd<2) {
							unitsToAdd +=2;
						}
					}
				}
				currentBoard.getActivePlayer().getCards().remove(card);
				currentBoard.getDrawnCards().add(card);
				unitsToAdd += 5 + (currentBoard.getDrawnCards().size()/3*5);
			}
		}
		return unitsToAdd;
	}
	
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
	
	//when the user wants to attack, this gets called.
	//this method selects the 2 territories that will be used
	//for Attack() and verifies that they are both valid.
	
	
	
	public static void setupAttack() {
		//makes sure that it is the attack phase (theoretically we can get rid of this as the button isn't visible
		//until it is the attack phase. but in case the view gets changed we'll keep it
		if (isAttackPhase) {
			//if there isnt a territory to attack from
			if (territoryToAttackFrom == null) {
				//checks that the current territory is owned by the current Player
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					//verifies that the user wants to attack from that territory
					if(ConfirmBox.display("Attack", "Do you want to attack from: "+currentTerritory.getTerritoryName().toString()+"?")) {
						//sets the attacking territory to the current territory
						territoryToAttackFrom = currentTerritory;						
					}
				} else {
					AlertBox.display("Attack", "You can't attack from this Territory");
				}
			//if there has already been an attacking territory selected 
			} else {
				//checks to see if the selected territory is adjacent to the attacking territory
				boolean isadjacent = false;
				//loops through each territory in the selected territories adjacency list
				//if the attacking territory is found, attacking is allowed
				for (Territory territory : selectedTerritory.getAdjacentTerritories()) {
					if (territory == territoryToAttackFrom) {
						isadjacent = true;
					}
				}
				//only allows the attack to proceed if the current user DOESN'T own the selected territory,
				//and if the territory is adjacent
				if (!currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory) && isadjacent) {
					//verifies that the user wants to attack that territory
					if (ConfirmBox.display("Attack", "Do you want to attack: "+currentTerritory.getTerritoryName().toString()+"?")) {
						//attacks with the two territories
						attack(territoryToAttackFrom, currentTerritory);
					}
				}
				//if the user selects the current territory twice, it de-selects that territory
				//so they can attack from a new territory **1337 skills**
				else if (currentTerritory == territoryToAttackFrom) {
					territoryToAttackFrom=null;
				} else {
					//alerts the user if they selected an invalid territory
					AlertBox.display("Attack", "You can't attack this Territory");
				}
			}
		//alerts the player if it isnt the attack phase yet
			//again, shouldn't be possible to get here. but just in case.
		} else {
			AlertBox.display("Attack", "You can't attack yet");
		}
	}
	
	//this method checks to see who owns a territory
	
	public static Player getOwnerOfTerritory(Territory territoryToFindOwner) {
		Player owner = null;
		//checks to make sure the territory isn't empty
		if (territoryToFindOwner.getOccupyingUnits().size()>0) {
			//gets the color of the units in a territory
			UnitColor territoryColor = territoryToFindOwner.getOccupyingUnits().get(0).getUnitColor();
			//loops through all the players
			for (Player player : currentBoard.getPlayerOrder()) {
				//checks to see which one matches the color of the territory
				if (territoryColor == player.getActiveUnits().get(0).getUnitColor()) {
					owner = player;
				}
			}
		}
		//return that player
		return owner;
	}
	
	//attacking method
	
	public static void attack(Territory territoryToAttackFrom, Territory territoryToAttack) {
		//find the defending player
		Player defendingPlayer = getOwnerOfTerritory(territoryToAttack);
		//loop the attack phase until the attacking territory no longer has more than one unit --meaning the attacker lost
		//or the defending territory no longer has more than 0 units --meaning the defender lost
		do {
			//prompt the attacking user for how many units to attack with
			int numOfUnitsToAttackWith = NumberOptionBox.display("Attack", "How many units do you want to attack with, "+currentBoard.getActivePlayer().displayName()+"?", territoryToAttackFrom.getOccupyingUnits().size());
			//prompt the defending user for how many units to defend with
			int numOfUnitsToDefendWith = NumberOptionBox.display("Defend", "How many units do you want to defend with, "+defendingPlayer.displayName()+"?", territoryToAttack.getOccupyingUnits().size()>2?2:territoryToAttack.getOccupyingUnits().size());
			int[] deadUnits = rollDice(numOfUnitsToAttackWith, numOfUnitsToDefendWith);
			for (int i = 0; i<deadUnits[0]; i++) {
				territoryToAttackFrom.getOccupyingUnits().remove(territoryToAttackFrom.getOccupyingUnits().size()-1);
				currentBoard.getActivePlayer().getActiveUnits().remove(currentBoard.getActivePlayer().getActiveUnits().size()-1);
			}
			for (int i = 0; i<deadUnits[1]; i++) {
				territoryToAttack.getOccupyingUnits().remove(territoryToAttack.getOccupyingUnits().size()-1);
				defendingPlayer.getActiveUnits().remove(defendingPlayer.getActiveUnits().size()-1);
			}
		} while (territoryToAttackFrom.getOccupyingUnits().size()>1 && territoryToAttack.getOccupyingUnits().size()>0 && ConfirmBox.display("Attack", "Keep attacking?"));
		//if either one of the previous conditions are no longer met:
		//if the attaking territory has more than one unit, they won.
		if (territoryToAttackFrom.getOccupyingUnits().size()>1) {
			//notify the user that they won
			AlertBox.display("Victory", currentBoard.getActivePlayer().displayName()+", Attack Sucessful!");
			//move units from the attacking territory to the defending territory
			freeMove(territoryToAttackFrom, territoryToAttack);
			//remove the territory from the list of owned territories the defending player has
			defendingPlayer.getOwnedTerritories().remove(territoryToAttack);
			//add the territory to the attackers list of owned territories
			currentBoard.getActivePlayer().getOwnedTerritories().add(territoryToAttack);
			getsCard = true;
		} else {
			//notifies the player that they did not win
			AlertBox.display("Loss", "Attack was unsuccessful"); //successful couldn't spell
		}
		//sets the territory to attack from to null, to allow for re-selection and subsequent attacking.
		Turn.territoryToAttackFrom = null;
		//check to see if that eliminated the player
		checkForElimination(defendingPlayer);
	}
	
	public static int[] rollDice(int attackers, int defenders) {
		Die[] attackDice = new Die[attackers];
		for (int i = 0; i < attackers; i++) {
			attackDice[i] = currentBoard.getAttackDice()[i]; 
			attackDice[i].setOutcome(attackDice[i].getRng().nextInt(6)+1);
		}
		Die[] defenceDice = new Die[defenders];
		for (int i = 0; i < defenders; i++) {
			defenceDice[i] = currentBoard.getDefenceDice()[i];
			defenceDice[i].setOutcome(defenceDice[i].getRng().nextInt(6)+1);
		}
		Arrays.sort(attackDice);
		Arrays.sort(defenceDice);
		int attackersDead = 0;
		int defendersDead = 0;
		for (int i = 0; i<defenceDice.length; i++) {
			if ((attackDice.length-1)> i) {
				if (attackDice[i].getOutcome()>defenceDice[i].getOutcome()) {
					defendersDead++;
				} else {
					attackersDead++;
				}
			}
				
		}
		int[] attackOutcomes = new int[attackDice.length];
		for (int i = 0; i < attackOutcomes.length; i++) {
			attackOutcomes[i]= attackDice[i].getOutcome(); 
		}
		int[] defenceOutcomes = new int[defenceDice.length];
		for (int i = 0; i < defenceOutcomes.length; i++) {
			defenceOutcomes[i]= defenceDice[i].getOutcome(); 
		}
		DieBox.display(attackOutcomes, defenceOutcomes, attackersDead, defendersDead);
		return new int[] {attackersDead, defendersDead};
	}
	
	//very similar to setupAttack() this makes sure that the player has selected
	//two valid territories to move between, and calls the move function.
	
	public static void setupFreeMove() {
		//makes sure that the player is allowed to move (theoretically impossible not to trigger)
		if (isFreeMovePhase) {
			//checks to see if a territory to move from has been selected
			if (territoryToMoveFrom == null) {
				//if not, it makes sure the player owns that territory
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory)) {
					//makes sure that the territory to move from has more than one unit
					if (currentTerritory.getOccupyingUnits().size()>1) {
						//verifies that the player wants to move from the selected country
						if (ConfirmBox.display("Free Move", "Do you want to move from: "+currentTerritory.getTerritoryName().toString()+"?")) {
							//if so, sets that as the territory to move from
							territoryToMoveFrom = currentTerritory;
						}
					} else {
						//notifies the player that they can't move from the territory
						AlertBox.display("Free Move", "There aren't enough units to move from this territory");
					}
				} else {
					//notifies the player that they can't move from the territory
					AlertBox.display("Free Move", "You don't own this Territory");
				}
				//if a territory to move from has been selected: 
			} else {
				//we need to check and make sure that the territory is adjacent
				//to the territory to move from, we'll assume that it's not by default
				boolean isadjacent = false;
				//loops through each territory in the adjacent territory array
				for (Territory territory : selectedTerritory.getAdjacentTerritories()) {
					//checks if the territory is adjacent
					if (territory == territoryToMoveFrom) {
						//sets it so
						isadjacent = true;
					}
				}
				//only allows the move if the player owns the territory to move to, and the territory is adjacent
				if (currentBoard.getActivePlayer().getOwnedTerritories().contains(currentTerritory) && isadjacent) {
					//verifies that the player wants to move to that territory
					if (ConfirmBox.display("Free Move", "Do you want to move to: "+currentTerritory.getTerritoryName().toString()+"?")) {
						//moves the units
						freeMove(territoryToMoveFrom, currentTerritory);
						//ends the free move phase
						next();
					}
				}
				//resets the territory selector if the two territories are the same
				else if (currentTerritory == territoryToAttackFrom) {
					territoryToMoveFrom=null;
				} else {
					//informs the player that they cannot move to that territory
					AlertBox.display("Attack", "You do not own this territory");
				}
			}
		} else {
			//informs the player that they cannot move from this territory
			AlertBox.display("Free Move", "You do not own enough units to move from this territory");
		}
	}
	
	
	//this method moves units between two territories. is a minimum of one territory moved each time,
	//this method gets called every time there is a successful attack, or a free move.
	public static void freeMove(Territory movingFromTerritory, Territory movingToTerritory) {
		//prompts the user for how many units they would like to move
		int numOfUnitsToMove = NumberSliderBox.display("Free Move", "How many units would you like to move?", 1, movingFromTerritory.getOccupyingUnits().size()-1);
		//for each unit they want to move, this removes a unit from the territory to move from,
		//and adds it to the territory to move to,
		//always adding and taking from the back
		for (int i = 0; i < numOfUnitsToMove; i++) {
			movingToTerritory.getOccupyingUnits().add(movingFromTerritory.getOccupyingUnits().get(movingFromTerritory.getOccupyingUnits().size()-1));
			movingFromTerritory.getOccupyingUnits().remove(movingFromTerritory.getOccupyingUnits().size()-1);
		}
		//informs the user that the units were moved
		AlertBox.display("Free Move", "Successfuly moved "+numOfUnitsToMove+" Units to "+movingToTerritory.getTerritoryName().toString());
		//resets the territory to move from variable to allow for future selection
		Turn.territoryToMoveFrom = null;
	}
	
	public static void checkForElimination(Player playerToCheck) {
		//checks to see if the player to check has less than one territories
		if (playerToCheck.getOwnedTerritories().size()<1) {
			//if they have less than one, we need to remove them from the PlayerOrder[] by:
			//making a temporary array with a size one less than the playerOrder[]
			Player[] tempPlayerArray = new Player[currentBoard.getPlayerOrder().length-1];
			//make a separate counter to compensate for the reduced length of the array
			int internalCounter = 0;
			//loop through every index of the playerOrder[]
			for (int i = 0; i < currentBoard.getPlayerOrder().length; i++) {
				//if the player in the array is the player to check
				if (currentBoard.getPlayerOrder()[i]== playerToCheck) {
				//do nothing
				} else {
					//otherwise, add the player to the temporary array
					tempPlayerArray[internalCounter] = currentBoard.getPlayerOrder()[i];
					//increment the counter
					internalCounter++;
				}
			}
			//check for a win, because a player has just been eliminated.
			checkForWin();
		}
	}
	
	//checks to see if someone won the game.
	//a player wins if there are no other players in the game
	public static void checkForWin() {
		if (currentBoard.getPlayerOrder().length<2) {
			RiskController.winGame();
		}
	}
	
	
	
	
}
