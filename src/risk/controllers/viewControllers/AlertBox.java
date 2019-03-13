package risk.controllers.viewControllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {
	public static void display(String title, String message) {
		//makes a new stage
		Stage stage = new Stage();
		//sets the stage to always be in focus
		stage.initModality(Modality.APPLICATION_MODAL);
		//gets rid of minimize and maximize buttons
		stage.initStyle(StageStyle.UTILITY);
		//sets the title according to received parameters
		stage.setTitle(title);
		//sets the width
		stage.setMinWidth(250);
		//sets the height
		stage.setMinHeight(150);
		//makes a label with the text according to reveived parameters
		Label messageLabel = new Label(message);
		messageLabel.setWrapText(true);
		messageLabel.getStyleClass().add("contentText");
		//makes a confirm button
		Button confirmButton = new Button("OK");
		//makes a labda action to close the stage
		confirmButton.setOnAction(EventHandler -> {
			stage.close();
		});
		//sets the layout to be a vbox with spacing 10
		VBox layout = new VBox(10);
		//center aligns everything
		layout.setAlignment(Pos.CENTER);
		//adds the messageLabel and confirmButton to the layout
		layout.getChildren().addAll(messageLabel, confirmButton);
		//sets the scene according to the layout, and sets it to the stage
		Scene scene = new Scene(layout);
		//add css to the scene
		scene.getStylesheets().add("/risk/views/FXML/style1.css");
		stage.setScene(scene);
		//shows the stage, and halts code unit the stage is closed.
		stage.showAndWait();
	}
}
