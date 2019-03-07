package views;

import javafx.scene.control.*;
import controllers.RiskController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Selection;

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
	public Button northAmericaSelector;
	public Button southAmericaSelector;
	public Button europeSelector;
	public Button africaSelector;
	public Button asiaSelector;
	public Button australiaSelector;
	public Button alaskaSelector;
	public Button albertaSelector;
	public Button centralAmericaSelector;
	public Button easternUnitedStatesSelector;
	public Button greenlandSelector;
	public Button northwestTerritorySelector;
	public Button centeralCanadaSelector;
	public Button easternCanadaSelector;
	public Button westernUnitedStatesSelector;
	public Button argentinaSelector;
	public Button brazilSelector;
	public Button peruSelector;
	public Button venezuelaSelector;
	public Button greatBritianAndIrelandSelector;
	public Button icelandSelector;
	public Button northernEuropeSelector;
	public Button ukraineSelector;
	public Button westernEuropeSelector;
	public Button centralAfricaSelector;
	public Button eastAfricaSelector;
	public Button egyptSelector;
	public Button madagascarSelector;
	public Button northAfricaSelector;
	public Button southAfricaSelector;
	public Button afghanistanSelector;
	public Button chinaSelector;
	public Button indiaSelector;
	public Button irkutskSelector;
	public Button japanSelector;
	public Button kamcatkaSelector;
	public Button middleEastSelector;
	public Button mongoliaSelector;
	public Button southeastAsiaSelector;
	public Button siberiaSelector;
	public Button uralSelector;
	public Button yakutskSelector;
	public Button easternAustraliaSelector;
	public Button indoneasiaSelector;
	public Button newGuineaSelector;
	public Button westernAustraliaSelector;
	public Label selectedItemLabel;
	public Label selectedItemInfoLabel1a;
	public Label selectedItemInfoLabel1b;
	public Label selectedItemInfoLabel2a;
	public Label selectedItemInfoLabel2b;
	public Label selectedItemInfoLabel3a;
	public Label selectedItemInfoLabel3b;
	
	
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
	
	public void selectItem(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(northAmericaSelector)) {
			
		}
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/FXML/NormalScreen.fxml"));
		window.setTitle("Risk :: Start Menu");
		window.setScene(new Scene(root, 400, 640));
	}
	
}
