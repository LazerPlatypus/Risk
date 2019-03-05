package views;

import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameView extends View{

	public MenuItem file;
	public MenuItem edit;
	public MenuItem help;
	public ImageView mapView;
	public Button next;
	public Button attack;
	public Button player1;
	public Button player2;
	public Button player3;
	public Button player4;
	public Button player5;
	public Button player6;
	public Button aux1;
	public Button aux2;
	public ImageView selectedItemView;
	public Button button1;
	public Button button2;
	public Button button3;
	
	public GameView() {
		
	}
	
	public GameView(Stage window) {
		super(window);
		try {
			start(window);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/FXML/NormalScreen.fxml"));
		window.setTitle("Risk :: Start Menu");
		window.setScene(new Scene(root, 400, 640));
	}
	
}
