package risk.controllers.viewControllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	//class variables
	
	static boolean response = false;
	
	//class logic
	
	public static boolean display(String title, String message) {
		//makes a new window
		Stage stage = new Stage();
		//makes the stage stay in focus
		stage.initModality(Modality.APPLICATION_MODAL);
		//sets the title according to received parameters
		stage.setTitle(title);
		//sets the minimum width
		stage.setMinWidth(250);
		//sets the minimum height
		stage.setMinHeight(250);
		//makes a new label with the text according to parameters
		Label messageLabel = new Label(message);
		messageLabel.setWrapText(true);
		messageLabel.getStyleClass().add("contentText");
		//makes a yes button
		Button yesButton = new Button("Yes");
		//makes a no button
		Button noButton = new Button("No");
		//defines a labda funtion to change the response to true
		//when selected
		yesButton.setOnAction(EventHandler -> {
			response = true;
			stage.close();
		});
		//defines a labda functino to change the response to
		//false when selected
		noButton.setOnAction(EventHandler -> {
			response = false;
			stage.close();
		});
		//makes a hbox to hold the yes and no buttons
		HBox yesAndNo = new HBox(5);
		//centers the alignment of the buttons
		yesAndNo.setAlignment(Pos.CENTER);
		//adds the buttons to the hbox
		yesAndNo.getChildren().addAll(yesButton, noButton);
		//makes a vbox with spacing 10
		VBox layout = new VBox(10);
		//center aligns everything in the vbox
		layout.setAlignment(Pos.CENTER);
		//adds the message and hbox to the vbox
		layout.getChildren().addAll(messageLabel, yesAndNo);
		//makes a new scene using the layout and sets it to the stage
		Scene scene = new Scene(layout);
		//add css to the scene
		scene.getStylesheets().add("/risk/views/FXML/style1.css");
		stage.setScene(scene);
		//shows the stage, and halts the code unil the window is closed
		stage.showAndWait();
		//returns the response.
		return response;
	}
}
