package risk.controllers;

import java.util.ArrayList;

import risk.controllers.viewControllers.AlertBox;
import risk.models.Player;
import risk.models.Territory;
import risk.models.Unit;
import risk.models.enums.TerritoryName;

public class GameSetup extends RiskController{
	
	//Class variables
	
	protected static Territory selectedTerritory;
	protected static int turnCount = 0;
	
	//start logic
	
	public static void start() {
		//displays NormalView.fxml using GameViewController
		viewController.showGame();
		//tells the viewController how many players they are
		//which allows the controller to hide buttons for non-existant players
		viewController.setPlayesToDisplay(currentBoard.getPlayers().length);
		//gets the active player based of GameState -- essential for loading existing boards
		currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[currentBoard.getGameState()[1]]);
		//notifies the users of who's turn it is, and what to do
		currentView.showError("It is "+currentBoard.getActivePlayer().getName()+"'s Turn!\nPlace a unit by clicking on a territory, then pressing 'Place Unit'");
		//hides the 'place unit' button used in Turn
		viewController.hideButton(7);
		//hides the 'free move' button used in Turn
		viewController.hideButton(8);
		//hides the 'next' button used in Turn
		viewController.hideButton(12);
		//hides the 'attack' button used in Turn
		viewController.hideButton(11);
	}
	
	//Controller logic
	
	//when the game starts, all it consistes of is:
	//each player placing a single unit on unoccupied Territory, in turns
	//until each space is occupied, then players may add more units to Territories they
	//already controll.
	public static void placeUnit() {
		//check to make sure the player has selected a territory
		if (selectedTerritory != null) {
			//this was done to reduce calls to the current board.
			Player currentPlayer = currentBoard.getActivePlayer();
			//checks to see that the selected territory is empty, or
			//if the turnCount is over the number of territories and the player owns that territory
			//Explanation for turnCount and TerritoryName.values().length:
			//each turn, one unit is ALWAYS placed. thus, if there have been more turns than there are territories
			//there are no more territories left to place on that haven't already been occupied.
			//Explanation for figuring out what color the player is:
			//if every territory is occupied, each territory will have at least one unit on it:
			//thus it is safe to get the unit at index 0, and select the color from it.
			//if the player can place a territory (access to this class is limited to players that can place units)
			//they must have at least one unit, meaning it is safe to get the unit at index 0, and select the color from it.
			if (selectedTerritory.getOccupyingUnits().size()<1 || turnCount>=TerritoryName.values().length && selectedTerritory.getOccupyingUnits().get(0).getUnitColor() == currentPlayer.getActiveUnits().get(0).getUnitColor()) {
				//this was done to reduce calls to the currentBoard and the CurrentPlayer, not to mention increasing legibility
				ArrayList<Unit> currentPlayerActiveUnits = currentPlayer.getActiveUnits();
				//double checks that the player has enough units -- theoretically unneeded.
				if (currentPlayer.getInactiveUnits().size()>0) {
					//adds a unit at the next avalible index, from the last avaliable unit in the currentPlayer's inactiveUnits list.
					currentPlayerActiveUnits.add(currentPlayer.getInactiveUnits().get(currentPlayer.getInactiveUnits().size()-1));
					//adds that same unit to the selected territory
					selectedTerritory.getOccupyingUnits().add(currentPlayer.getInactiveUnits().get(currentPlayer.getInactiveUnits().size()-1));
					//removes that unit from the inactiveUnit list (always taking from the back so we can safely get the index
					//of the first item
					currentPlayer.getInactiveUnits().remove(currentPlayer.getInactiveUnits().size()-1);
					//if the player doesn't have the territory, we need to add it to the player's territory list
					boolean hasTerritory = false;
					//loops through every territory the player owns
					for (Territory territory : currentPlayer.getOwnedTerritories()) {
						if (territory == selectedTerritory) {
							hasTerritory = true;
						}
					}
					//if the player didnt have the territory, we're gonna add it to their ownedTerritories list.
					if (!hasTerritory) {
						currentPlayer.getOwnedTerritories().add(selectedTerritory);					
					}
			}
			//we need to update the display so the player knows they've added a unit
			currentView.updateDisplay();
			//the turn count increases
			turnCount++;
			//the gameState is based off the phase of the game, and what player is active, so we need to set that.
			//as we are still in setup, the phase stays 0, but the active player is determined by taking the turnCount
			//and dividing it by the number of players in PlayerOrder, with the result being what's leftover.
			currentBoard.setGameState(new int[] {0,turnCount%currentBoard.getPlayerOrder().length});
			//the active player is just a copy of the GameState, so we find it by taking the index of the PlayerOrder via the GameState 
			currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[currentBoard.getGameState()[1]]);
			//we inform the user's who's turn it is, and what they need to do.
			currentView.showError("It is "+currentBoard.getActivePlayer().getName()+"'s Turn!\nPlace a unit by clicking on a territory, then pressing 'Place Unit'");
			//if the player tried to put a unit in an invalid territory, we need to inform them
			} else {
//				testing purposes
//				System.out.println("Displaying error");
				//check the class for information
				AlertBox.display("Whoopsie-doo", "You cannot place a unit there.\nThat space is already occupied!");
			}
			//testing purposes
//			System.out.println(turnCount);
//			 FIX THIS IS FOR TESTING -- DONE
			//once every player is out of units, we can start the game.
			//if the last player in the turn roation is out of units all players MUST be.
			if (arePlayersOutOfUnits(currentBoard.getPlayerOrder()[currentBoard.getPlayerOrder().length-1])) {
				Turn.start();
			}
		}
	}
	
	//just checks the specified players to see if they are out of units.
	//return true if they are out of units,
	//false otherwise.
	public static boolean arePlayersOutOfUnits(Player player) {
		return player.getInactiveUnits().size()<1;
	}
}
