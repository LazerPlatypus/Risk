package risk.controllers.viewControllers;



import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import risk.controllers.RiskController;
import risk.controllers.viewControllers.interfaces.Displayable;

public class ViewController{
	
	//class variables
	private Stage stage;
	private StartMenuViewController startMenuViewController;
	private Scene startMenuScreenScene;
	private CreateGameViewController createGameViewController;
	private Scene createGameScreenScene;
	private GameViewController gameViewController;
	private Scene gameViewScreenScene;
	private WinScreenController winScreenController;
	private Scene winScreenScene;
	private StatScreenController statScreenController;
	private Scene statScreenScene;
	
	
	//constructors
	public ViewController(Stage stage) {
		this.stage = stage;
		stage.setOnCloseRequest(e -> {e.consume(); RiskController.exitGame();});
		showStartMenu();
	}
	
	//Controller Methods
	
	
	//Stage switching Methods
	public void showStartMenu() {
		if (startMenuViewController == null) {
			//testing purposes
//			System.out.println("startMenuViewController is null. making it.");
			makeStartMenuViewController();
		}
		stage.setMaximized(false);
		stage.setTitle("Risk :: Start Menu");
		stage.setScene(startMenuScreenScene);
		stage.show();
		RiskController.setView(startMenuViewController);
	}
	
	public void showCreateGameScreen() {
		if (createGameViewController == null) {
			makeCreateGameViewController();
			//testing purposes
//			System.out.println("createGameScreen is null. making it.");
		}
		stage.setMaximized(false);
		stage.setTitle("Risk :: Create Game");
		stage.setScene(createGameScreenScene);
		stage.show();
		RiskController.setView(createGameViewController);
	}
	
	public void showGame() {
		if (gameViewController == null) {
			makeGameViewController();
			//testing purposes
//			System.out.println("gameViewController is null. making it.");
		}
		stage.setTitle("Risk");
		stage.setScene(gameViewScreenScene);
		stage.setMaximized(false);
		stage.show();
		RiskController.setView(gameViewController);
	}
	
	public void showWinScreen() {
		if (winScreenController == null) {
			makeGameViewController();
			//testing purposes
//			System.out.println("gameViewController is null. making it.");
		}
		stage.setTitle("Risk");
		stage.setScene(winScreenScene);
		stage.setMaximized(false);
		stage.show();
		RiskController.setView(winScreenController);
	}
	
	public void showStatScreen() {
		if (gameViewController == null) {
			makeGameViewController();
			//testing purposes
//			System.out.println("gameViewController is null. making it.");
		}
		stage.setTitle("Risk");
		stage.setScene(statScreenScene);
		stage.setMaximized(false);
		stage.show();
		RiskController.setView(statScreenController);
	}
	
	//Pass-through logic
	
	public void hideButton(int buttonID) {
		gameViewController.hideButton(buttonID);
	}
	
	public void showButton(int buttonID) {
		gameViewController.showButton(buttonID);
	}
	
	public void showDisplayable(Displayable itemToDisplay) {
		
	}
	
	public void setPlayesToDisplay(int players) {
		gameViewController.setNumOfPlayers(players);
	}
	
	//scene creation logic
	
	public void makeStartMenuViewController() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/risk/views/FXML/StartMenuScreen.fxml"));
			root = loader.load();
			startMenuScreenScene = new Scene(root, 600, 400);
			startMenuScreenScene.getStylesheets().add("/risk/views/FXML/style2.css");
			startMenuViewController = loader.getController();
			startMenuViewController.setViewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void makeCreateGameViewController() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/risk/views/FXML/CreateGameScreen.fxml"));
			root = (Parent) loader.load();
			createGameScreenScene = new Scene(root, 600, 400);
			createGameScreenScene.getStylesheets().add("/risk/views/FXML/style2.css");
			createGameViewController = loader.getController();
			createGameViewController.setViewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void makeGameViewController() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/risk/views/FXML/NormalScreen.fxml"));
			root = (Parent) loader.load();
			gameViewScreenScene = new Scene(root, 1308, 919);
			gameViewScreenScene.getStylesheets().add("/risk/views/FXML/style2.css");
			gameViewController = loader.getController();
			gameViewController.setViewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void makeWinScreenController() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/risk/views/FXML/WinScreen.fxml"));
			root = (Parent) loader.load();
			winScreenScene = new Scene(root, 1308, 919);
			winScreenController = loader.getController();
			winScreenController.setViewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void makeStatScreenController() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/risk/views/FXML/StatScreen.fxml"));
			root = (Parent) loader.load();
			statScreenScene = new Scene(root, 1308, 919);
			statScreenController = loader.getController();
			statScreenController.setViewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
