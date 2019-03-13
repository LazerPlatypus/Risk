package risk.controllers;



import javafx.stage.Stage;
import risk.controllers.viewControllers.ViewController;
import risk.controllers.viewControllers.interfaces.Displayable;
import risk.controllers.viewControllers.interfaces.View;
import risk.controllers.viewControllers.ConfirmBox;
import risk.models.Board;
import risk.models.Territory;
import risk.models.enums.CountryName;
import risk.models.enums.TerritoryName;

public class RiskController {
	
	//class variables
	
	protected static View currentView;
	protected static Board currentBoard;
	protected static ViewController viewController;
	protected static Territory currentTerritory;
	
	//start logic
	
	public static void run(Stage stage) {
		viewController = new ViewController(stage);
	}
	
	//Menu Logic (pass-through)
	//note that "pass-through" simply means that the class is ferrying data, without manipulating it
	//or changing it in a significant way
	
	public static void createGame() {
		viewController.showCreateGameScreen();
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
	
	public static void saveGame() {
		currentView.hideError();
		BoardController.saveBoard(currentBoard);
	}
	
	public static void exitGame() {
		Boolean save = ConfirmBox.display("Exit", "Do you want to save before exiting?");
		if (save) {
			saveGame();
		}
		
		menu.Main.showMenu();
	}
	
	//Controller logic (mostly pass-through stuff to the view controllers)
	
	public static void setView(View currentView) {
		RiskController.currentView = currentView;
	}
	
	public static void Error(String error) {
		currentView.showError(error);
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
	
	public static Displayable getPlayerToDisplay() {
		return currentBoard.getPlayerOrder()[0];
	}
	
	//Game flow Logic
	
	public static void startGame(String[] playerNames) {
		currentBoard = BoardController.CreateBoard(playerNames);
		TerritoryController.setupAdjacentTerritories();
		for (Territory territory : currentBoard.getMap().getCountries().get(CountryName.AFRICA).getTerritories().get(TerritoryName.EAST_AFRICA).getAdjacentTerritories()) {
			System.out.println(territory);
		}
		resumeGame();
	}
	
	public static void resumeGame() { 
		if (currentBoard.getGameState()[0] == 0) {
			GameSetup.start();
		}
		else if (currentBoard.getGameState()[0] == 1) {
			Turn.start();
		}
	}
	
	public static void winGame() {
		
	}
	
	
}
