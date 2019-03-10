package risk.controllers.viewControllers;

import java.nio.channels.NonWritableChannelException;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	static boolean response;
	
	public static boolean display(String title, String message) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setOnAction(EventHandler -> {
			response = true;
			stage.close();
		});
		
		noButton.setOnAction(EventHandler -> {
			response = false;
			stage.close();
		});
		
		HBox yesAndNo = new HBox(5);
		yesAndNo.setAlignment(Pos.CENTER);
		yesAndNo.getChildren().addAll(yesButton, noButton);
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, yesAndNo);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
		return response;
	}
}