package risk.controllers;

import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import risk.controllers.viewControllers.AlertBox;
import risk.models.Board;
import risk.models.Player;
import risk.models.Territory;
import risk.models.Unit;
import risk.models.enums.UnitColor;
import risk.models.enums.UnitStatus;

public class GameSetup extends RiskController{
	
	protected static Territory selectedTerritory;
	protected static int turnCount = 0;
	public static void start() {
		viewController.showGame();
		viewController.setPlayesToDisplay(currentBoard.getPlayers().length);
		currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[currentBoard.getGameState()[1]]);
		currentView.showError("It is "+currentBoard.getActivePlayer().getName()+"'s Turn!\nPlace a unit by clicking on a territory, then pressing 'Place Unit'");
		viewController.hideButton(7);
		viewController.hideButton(8);
		viewController.hideButton(12);
		viewController.hideButton(11);
	}
	
	
	
	public static void placeUnit() {
		if (selectedTerritory != null) {
			if (selectedTerritory.getOccupyingUnits().size()<1 || turnCount>=41 && selectedTerritory.getOccupyingUnits().get(0).getUnitColor() == currentBoard.getActivePlayer().getActiveUnits().get(0).getUnitColor()) {
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
			currentBoard.setGameState(new int[] {0,turnCount%currentBoard.getPlayerOrder().length});
			currentBoard.setActivePlayer(currentBoard.getPlayerOrder()[currentBoard.getGameState()[1]]);
			currentView.showError("It is "+currentBoard.getActivePlayer().getName()+"'s Turn!\nPlace a unit by clicking on a territory, then pressing 'Place Unit'");
			} else {
				System.out.println("Displaying error");
				AlertBox.display("Whoopsie-doo", "You cannot place a unit there.\nThat space is already occupied!");
			}
			System.out.println(turnCount);
//			TODO FIX THIS IS FOR TESTING
			if (arePlayersOutOfUnits(currentBoard.getPlayerOrder()[currentBoard.getPlayerOrder().length-1])) {
				Turn.start();
			}
		}
	}
	public static boolean arePlayersOutOfUnits(Player player) {
		return player.getInactiveUnits().size()<1;
	}
}
