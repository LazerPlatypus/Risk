package risk.controllers.viewControllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NumberOptionBox {
	
	//class variables
	private static int response = 1;
	
	//class logic
	
	public static int display(String title, String message, int range) {
		//makes a new window
		Stage stage = new Stage();
		//sets it to be in focus
		stage.initModality(Modality.APPLICATION_MODAL);
		//sets the title based on reveived parameters
		stage.setTitle(title);
		//sets the minimum width
		stage.setMinWidth(250);
		//sets the minimum height
		stage.setMinHeight(200);
		//makes a new label based on received parameters
		Label messageLabel = new Label(message);
		messageLabel.setMaxWidth(150);
		messageLabel.setWrapText(true);
		messageLabel.getStyleClass().add("contentText");
		//makes three buttons
		Button oneButton = new Button("One");
		Button twoButton = new Button("Two");
		Button threeButton = new Button("Three");
		//defines labda functions where each button changes the response
		//to be a number based on the button, and closing the stage.
		oneButton.setOnAction(EventHandler -> {
			response = 1;
			stage.close();
		});
		
		twoButton.setOnAction(EventHandler -> {
			response = 2;
			stage.close();
		});
		
		threeButton.setOnAction(EventHandler -> {
			response = 3;
			stage.close();
		});
		
		//makes a HBox with spacing 6
		HBox options = new HBox(5);
		//sets the centers the items
		options.setAlignment(Pos.CENTER);
		//done with if statements to make sure the buttons stay in the right order
		//if the range is 1, only add oneButton
		if (range == 1) {
			options.getChildren().addAll(oneButton);
		}
		//if its 2, add one and two
		if (range == 2) {
			options.getChildren().addAll(oneButton, twoButton);
		}
		//if its 3, add all the buttons
		if (range >= 3) {
			options.getChildren().addAll(oneButton, twoButton, threeButton);
		}
		//makes a vbox with spacing 10
		VBox layout = new VBox(10);
		//centers alignment
		layout.setAlignment(Pos.CENTER);
		//adds the label and buttons to the layout
		layout.getChildren().addAll(messageLabel, options);
		//makes a new scene from the layout, and sets it to the stage
		Scene scene = new Scene(layout);
		//add css to the scene
		scene.getStylesheets().add("/risk/views/FXML/style1.css");
		stage.setScene(scene);
		//shows the window and halts code until the window is closed
		stage.showAndWait();
		//returns the response, with a default of 1
		return response;
	}
}
