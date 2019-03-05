package views;

import java.io.IOException;

import com.sun.java.swing.plaf.windows.resources.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartMenuController extends View{
	public Button createGameButton;
	public Button loadGameButton;
	public Button saveGameButton;
	public Button exitGameButton;
	
	public StartMenuController() {
		
	}
	
	public StartMenuController(Stage window) {
		super(window);
		try {
			start(window);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void createGame() {
		System.out.println("Game created");
	}
	
	public void loadGame() {
		System.out.println("Game loaded");
	}
	
	public void saveGame() {
		System.out.println("game saved");
	}
	
	public void exitGame() {
		System.out.println("game exited");
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/FXML/StartMenu.fxml"));
		window.setTitle("Risk :: Start Menu");
		window.setScene(new Scene(root, 400, 640));
	}
}
