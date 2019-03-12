package risk.controllers;



import javafx.stage.Stage;
import risk.controllers.viewControllers.ViewController;
import risk.controllers.viewControllers.ConfirmBox;
import risk.controllers.viewControllers.Displayable;
import risk.controllers.viewControllers.View;
import risk.models.Board;
import risk.models.Territory;
import risk.models.enums.CountryName;
import risk.models.enums.TerritoryName;

public class RiskController {
	protected static View currentView;
	protected static Board currentBoard;
	protected static ViewController viewController;
	protected static Territory currentTerritory;
	
	public static void run(Stage stage) {
		viewController = new ViewController(stage);
	}
	
	public static void setView(View currentView) {
		RiskController.currentView = currentView;
	}
	
	public static void createGame() {
		viewController.showCreateGameScreen();
	}
	
	public static void resumeGame() { 
		if (currentBoard.getGameState()[0] == 0) {
			GameSetup.start();
		}
		else if (currentBoard.getGameState()[0] == 1) {
			Turn.start();
		}
	}
	
	public static void startGame(String[] playerNames) {
		currentBoard = BoardController.CreateBoard(playerNames);
		resumeGame();
	}
	
	public static void loadGame() {
		currentView.hideError();
		RiskController.currentBoard = BoardController.loadBoard();
		if (currentBoard != null) {
			Error("Game loaded successfully");
			viewController.makeStartMenuViewController();
			viewController.makeCreateGameViewController();
			viewController.makeGameViewController();
			resumeGame();			
		} else {
			Error("No game selected");
		}
	}
	
	public static void Error(String error) {
		currentView.showError(error);
	}
	
	public static void saveGame() {
		currentView.hideError();
		BoardController.saveBoard(currentBoard);
	}
	public static void exitGame() {
		Boolean save = ConfirmBox.display("Exit", "Do you want to save before exiting?");
		if (save) {
			saveGame();
		}
		
		//sets the passes the stage back to game galore
		//gameGalore.run(stage);
		//note to self, to get the program to close, use the terminate button.
		System.out.println("Just pretend I closed.");
	}
	public static Displayable getCountryToDisplay(CountryName countryKey) {
		return currentBoard.getMap().getCountries().get(countryKey);
	}
	public static Displayable getTerritoryToDisplay(CountryName countryKey, TerritoryName territoryKey) {
		currentTerritory = currentBoard.getMap().getCountries().get(countryKey).getTerritories().get(territoryKey);
		GameSetup.selectedTerritory = currentTerritory;
		return currentTerritory;
	}
	public static Displayable getPlayerToDisplay(int index) {
		return currentBoard.getPlayers()[index];
	}
	
}
