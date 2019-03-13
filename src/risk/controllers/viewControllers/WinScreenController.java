package risk.controllers.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import risk.controllers.RiskController;
import risk.controllers.viewControllers.interfaces.Displayable;

public class WinScreenController {	
	//FXML Variables
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView winnerPortrait;

    @FXML
    private Label winningPlayerNameLabel;

    @FXML
    private Button viewStatsButton;

    //FXML Methods
    
    @FXML
    void viewStats(ActionEvent event) {
    	viewController.showStatScreen();
    }
    
    @FXML
    void initialize() {
    	assert winnerPortrait != null : "fx:id=\"winnerPortrait\" was not injected: check your FXML file 'WinScreen.fxml'.";
    	assert winningPlayerNameLabel != null : "fx:id=\"winningPlayerNameLabel\" was not injected: check your FXML file 'WinScreen.fxml'.";
    	assert viewStatsButton != null : "fx:id=\"viewStatsButton\" was not injected: check your FXML file 'WinScreen.fxml'.";
    	Displayable winningPlayer = RiskController.getPlayerToDisplay();
    	winningPlayerNameLabel.setText(winningPlayer.displayName());
    	winnerPortrait.setImage(new Image(getClass().getResourceAsStream("/risk/views/Images/"+winningPlayer.resourceLocation()+".png")));
    }
    
    //Controller Variables
    
    private ViewController viewController;
    
    //Controller Methods
    
    public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

}

