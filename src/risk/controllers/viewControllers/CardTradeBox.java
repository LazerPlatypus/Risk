package risk.controllers.viewControllers;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CardTradeBox {
	private static int option = -1;
	public static int display(String[][] cardDescription, boolean canRefuse) {
		//make a new Stage
		Stage stage = new Stage();
		//set the stage to always be in focus
		stage.initModality(Modality.APPLICATION_MODAL);
		//get rid on minimize and maximize buttons
		stage.initStyle(StageStyle.UTILITY);
		//set title
		stage.setTitle("Card Trade");
		//set the width
		stage.setMinWidth(550);
		//set the height
		stage.setMinHeight(280);
		//make a vbox for the layout
		VBox layout = new VBox(5);
		//make a label to prompt user
		Label messageLabel = new Label("Select the card pairing you'd like to trade");
		messageLabel.setMinHeight(30);
		messageLabel.setMinWidth(300);
		messageLabel.setAlignment(Pos.CENTER);
		messageLabel.setWrapText(true);
		messageLabel.getStyleClass().add("contentText");
		//add the label to the layout
		layout.getChildren().add(messageLabel);
		//make a toggle group for the buttons
		ToggleGroup group = new ToggleGroup();
		//make 3 labels for each button, one for each card description
		Label pair1card1 = new Label(cardDescription[0][0]);
		pair1card1.setWrapText(true);
		pair1card1.setAlignment(Pos.CENTER);
		pair1card1.setMinHeight(40);
		pair1card1.setMinWidth(100);
		pair1card1.getStyleClass().add("contentText");
		Label pair1card2 = new Label(cardDescription[0][1]);
		pair1card2.setWrapText(true);
		pair1card2.setAlignment(Pos.CENTER);
		pair1card2.setMinHeight(40);
		pair1card2.setMinWidth(100);
		pair1card2.getStyleClass().add("contentText");
		Label pair1card3 = new Label(cardDescription[0][2]);
		pair1card3.setWrapText(true);
		pair1card3.setAlignment(Pos.CENTER);
		pair1card3.setMinHeight(40);
		pair1card3.setMinWidth(100);
		pair1card3.getStyleClass().add("contentText");
		//make a hbox with enough spacing to make card descriptions distinguishable
		HBox pair1 = new HBox(50);
		//set the minimum height
		pair1.setMinHeight(51);
		//set the minimum width
		pair1.setMinWidth(341);
		//center align the hbox
		pair1.setAlignment(Pos.CENTER);
		//add the labels to the hbox
		pair1.getChildren().addAll(pair1card1, pair1card2, pair1card3);
		//make a toggle button for the option
		RadioButton radioButton1 = new RadioButton();
		RadioButton radioButton2 = new RadioButton();
		RadioButton radioButton3 = new RadioButton();
		//select the first button by default
		radioButton1.setSelected(true);
		//set the graphic of the button to be the hbox
		radioButton1.setGraphic(pair1);
		//add radioButton1 to the toggleGroup
		radioButton1.setToggleGroup(group);
		//add the radio button to the vbox
		layout.getChildren().add(radioButton1);
		//if there is another card pair, repeat
		if (cardDescription.length>1) {
			Label pair2card1 = new Label(cardDescription[1][0]);
			pair2card1.setWrapText(true);
			pair2card1.setAlignment(Pos.CENTER);
			pair2card1.setMinHeight(40);
			pair2card1.setMinWidth(100);
			pair2card1.getStyleClass().add("contentText");
			Label pair2card2 = new Label(cardDescription[1][1]);
			pair2card2.setWrapText(true);
			pair2card2.setAlignment(Pos.CENTER);
			pair2card2.setMinHeight(40);
			pair2card2.setMinWidth(100);
			pair2card2.getStyleClass().add("contentText");
			Label pair2card3 = new Label(cardDescription[1][2]);
			pair2card3.setWrapText(true);
			pair2card3.setAlignment(Pos.CENTER);
			pair2card3.setMinHeight(40);
			pair2card3.setMinWidth(100);
			pair2card3.getStyleClass().add("contentText");
			HBox pair2 = new HBox(50);
			pair2.setMinHeight(51);
			pair2.setMinWidth(341);
			pair2.setAlignment(Pos.CENTER);
			pair2.getChildren().addAll(pair2card1, pair2card2, pair2card3);
			radioButton2.setGraphic(pair2);
			radioButton2.setToggleGroup(group);
			layout.getChildren().add(radioButton2);
		}
		//if there is another card pair, repeat
		if (cardDescription.length>2) {
			Label pair3card1 = new Label(cardDescription[2][0]);
			pair3card1.setWrapText(true);
			pair3card1.setAlignment(Pos.CENTER);
			pair3card1.setMinHeight(40);
			pair3card1.setMinWidth(100);
			pair3card1.getStyleClass().add("contentText");
			Label pair3card2 = new Label(cardDescription[2][1]);
			pair3card2.setWrapText(true);
			pair3card2.setAlignment(Pos.CENTER);
			pair3card2.setMinHeight(40);
			pair3card2.setMinWidth(100);
			pair3card2.getStyleClass().add("contentText");
			Label pair3card3 = new Label(cardDescription[2][2]);
			pair3card3.setWrapText(true);
			pair3card3.setAlignment(Pos.CENTER);
			pair3card3.setMinHeight(40);
			pair3card3.setMinWidth(100);
			pair3card3.getStyleClass().add("contentText");
			HBox pair3 = new HBox(50);
			pair3.setMinHeight(51);
			pair3.setMinWidth(341);
			pair3.setAlignment(Pos.CENTER);
			pair3.getChildren().addAll(pair3card1, pair3card2, pair3card3);
			radioButton3.setGraphic(pair3);
			radioButton3.setToggleGroup(group);
			layout.getChildren().add(radioButton3);
		}
		//make a confirm button
		Button confirmButton = new Button("Yes");
		//make a deny button
		Button denyButton = new Button("Nevermind");
		//make a new Hbox with spacing 10
		HBox buttonBox = new HBox(10);
		//set its alignment to center
		buttonBox.setAlignment(Pos.CENTER);
		//add the confirmButton to it
		buttonBox.getChildren().add(confirmButton);
		
		//if we are allowed to make a deny button
		if (canRefuse) {
			//add it to the button box
			buttonBox.getChildren().add(denyButton);
		}
		
		//button events
		confirmButton.setOnAction(EventHandler -> {
			if (group.getSelectedToggle().equals(radioButton1)) {
				option = 0;
			}
			else if(group.getSelectedToggle().equals(radioButton2)) {
				option = 1;
			}
			else {
				option = 2;
			}
			stage.close();
		});
		denyButton.setOnAction(EventHandler -> {
			stage.close();
		});
		
		//add the buttonbox to the layout
		layout.getChildren().add(buttonBox);
		//center align the layout
		layout.setAlignment(Pos.CENTER);
		//make a new scene using the layout and set it to the stage
		Scene scene = new Scene(layout);
		//add css to the scene
		scene.getStylesheets().add("/risk/views/FXML/style2.css");
		stage.setScene(scene);
		//show the stage, and halt code unit the window is closed
		stage.showAndWait();
		//return the response
		return option;
	}
	
}
