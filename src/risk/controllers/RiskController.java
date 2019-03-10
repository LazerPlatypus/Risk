package risk.controllers;



import javafx.stage.Stage;
import risk.controllers.viewControllers.ViewController;
import risk.controllers.viewControllers.ConfirmBox;
import risk.controllers.viewControllers.View;
import risk.models.Board;

public class RiskController {
	private static View currentView;
	private static Board currentBoard;
	private static ViewController viewController;
	
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
		System.out.println("Hewwo");
	}
	
	public static void startGame(String[] playerNames) {
		currentBoard = BoardController.CreateBoard(playerNames);
		resumeGame();
	}
	
	public static void loadGame() {
		currentView.hideError();
		RiskController.currentBoard = BoardController.loadBoard();
		Error("Game loaded successfully");
		resumeGame();
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
}
