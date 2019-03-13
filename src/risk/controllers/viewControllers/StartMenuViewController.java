package risk.controllers.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import risk.controllers.RiskController;
import risk.controllers.viewControllers.interfaces.View;

public class StartMenuViewController implements View, Initializable{
	
	//FXML variables
	
	@FXML
	private Button createGameButton;
	@FXML
	private Button loadGameButton;
	@FXML
	private Button saveGameButton;
	@FXML
	private Button exitGameButton;
	@FXML
	private Label errorLabel;
	
	//FXML Methods
	
	@FXML
	private void buttonPressed(ActionEvent action) {
		int buttonHashCode = action.getSource().hashCode();
		if (buttonHashCode == createGameButton.hashCode()) {
			RiskController.createGame();
		}
		else if (buttonHashCode == exitGameButton.hashCode()) {
			RiskController.exitGame();
		}
		else if (buttonHashCode == saveGameButton.hashCode()) {
			RiskController.saveGame();
		}
		else if (buttonHashCode == loadGameButton.hashCode()) {
			RiskController.loadGame();
		}
		else {
			System.out.println("Invalid button pressed. FYI");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorLabel.managedProperty().bind(errorLabel.visibleProperty());
		hideError();
	}
	
	//Interface Methods
	
	@Override
	public void showError(String error) {
		errorLabel.setText(error);
		errorLabel.setVisible(true);	
	}

	@Override
	public void hideError() {
		errorLabel.setVisible(false);		
	}

	@Override
	public void updateDisplay() {		
	}
	
	//Constructors
	
	
	//Controller variables
	
	private ViewController viewController;		
	
	//Controller Methods
	
	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	
	public ViewController getViewController() {
		return viewController;
	}
	
	
	
	
	
	

	
	
	

}
