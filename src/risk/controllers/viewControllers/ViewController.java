package risk.controllers.viewControllers;



import java.io.IOException;
import java.util.function.Predicate;

import org.omg.CORBA.PRIVATE_MEMBER;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import risk.controllers.RiskController;

public class ViewController{
	//class variables
	private Stage stage;
	private StartMenuViewController startMenuViewController;
	private Scene startMenuScreenScene;
	private CreateGameViewController createGameViewController;
	private Scene createGameScreenScene;
	private GameViewController gameViewController;
	private Scene gameViewScreenScene;
	
	//constructors
	public ViewController(Stage stage) {
		this.stage = stage;
		stage.setOnCloseRequest(e -> {e.consume(); RiskController.exitGame();});
		showStartMenu();
	}
	
	public void hideButton(int buttonID) {
		gameViewController.hideButton(buttonID);
	}
	public void showButton(int buttonID) {
		gameViewController.showButton(buttonID);
	}
	//display/switch scene logic
	public void showStartMenu() {
		if (startMenuViewController == null) {
			makeStartMenuViewController();
		}
		stage.setTitle("Risk :: Start Menu");
		stage.setScene(startMenuScreenScene);
		stage.show();
		RiskController.setView(startMenuViewController);
	}
	
	public void showCreateGameScreen() {
		if (createGameViewController == null) {
			makeCreateGameViewController();
			System.out.println("its null");
		}
		stage.setTitle("Risk :: Create Game");
		stage.setScene(createGameScreenScene);
		stage.show();
		RiskController.setView(createGameViewController);
	}
	
	public void showGame() {
		if (gameViewController == null) {
			makeGameViewController();
			System.out.println("its null");
		}
		stage.setTitle("Risk");
		stage.setScene(gameViewScreenScene);
		stage.setMaximized(true);
		stage.show();
		RiskController.setView(gameViewController);
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
			gameViewScreenScene = new Scene(root, 600, 400);
			gameViewController = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	//temp for testing
//	public void close() {
//		stage.hide();
//	}
	
}
