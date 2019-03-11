package risk.controllers.viewControllers;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import risk.controllers.RiskController;

public class CreateGameViewController implements View, Initializable{
	@FXML
	private Button continueButton;
	@FXML
	private Button backButton;
	@FXML
	private TextField player1TextField;
	@FXML
	private TextField player2TextField;
	@FXML
	private TextField player3TextField;
	@FXML
	private TextField player4TextField;
	@FXML
	private TextField player5TextField;
	@FXML
	private TextField player6TextField;
	@FXML
	private ComboBox<Integer> numOfPlayers;
	@FXML
	private HBox player3Options;
	@FXML
	private HBox player4Options;
	@FXML
	private HBox player5Options;
	@FXML
	private HBox player6Options;
	private ViewController viewController;
	public CreateGameViewController() {
		
	}
	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
//		System.out.println("Did this get ran?");
			//add all the options to the combo box
		numOfPlayers.getItems().addAll(2,3,4,5,6);
			//change the number of visible rows to 5
		numOfPlayers.setVisibleRowCount(5);
		numOfPlayers.getSelectionModel().selectLast();
		numOfPlayers.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					valueChanged(numOfPlayers);
					
				}
			});
		player3Options.managedProperty().bind(player3Options.visibleProperty());
		player4Options.managedProperty().bind(player4Options.visibleProperty());
		player5Options.managedProperty().bind(player5Options.visibleProperty());
		player6Options.managedProperty().bind(player6Options.visibleProperty());
		
	}
	private void valueChanged(ComboBox<Integer> numOfPlayers) {
		player3Options.setVisible(false);
		player4Options.setVisible(false);
		player5Options.setVisible(false);
		player6Options.setVisible(false);
		switch(numOfPlayers.getValue()) {
		case 6:
			player6Options.setVisible(true);
		case 5:
			player5Options.setVisible(true);
		case 4:
			player4Options.setVisible(true);
		case 3:
			player3Options.setVisible(true);
		}
	}
	
	@FXML
	private void buttonPressed(ActionEvent actionEvent) {
		int buttonHash = actionEvent.getSource().hashCode();
		if (buttonHash == backButton.hashCode()) {
			viewController.showStartMenu();
		}
		else if (buttonHash == continueButton.hashCode()) {
			
			String[] playerNames = new String[numOfPlayers.getValue()];
			switch (playerNames.length) {
			case 6:
				if (player6TextField.getText() == null || player6TextField.getText().trim().isEmpty()) {
					playerNames[5] = "Player 6";
				} else {
					playerNames[5] = player6TextField.getText();
				}
			case 5:
				if (player5TextField.getText() == null || player5TextField.getText().trim().isEmpty()) {
					playerNames[4] = "Player 5";
				} else {
					playerNames[4] = player5TextField.getText();
				}
			case 4:
				if (player4TextField.getText() == null || player4TextField.getText().trim().isEmpty()) {
					playerNames[3] = "Player 4";
				} else {
					playerNames[3] = player4TextField.getText();
				}
			case 3:
				if (player3TextField.getText() == null || player3TextField.getText().trim().isEmpty()) {
					playerNames[2] = "Player 3";
				} else {
					playerNames[2] = player3TextField.getText();
				}
			case 2:
				if (player2TextField.getText() == null || player2TextField.getText().trim().isEmpty()) {
					playerNames[1] = "Player 2";
				} else {
					playerNames[1] = player2TextField.getText();
				}
				if (player1TextField.getText() == null || player1TextField.getText().trim().isEmpty()) {
					playerNames[0] = "Player 1";
				} else {
					playerNames[0] = player1TextField.getText();
				}
			}
			RiskController.startGame(playerNames);
		}
		else {
			
		}
	}

	@Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideError() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub
		
	}
}
