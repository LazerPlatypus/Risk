package risk.controllers.viewControllers;

import javax.swing.TransferHandler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NumberOptionBox {
	private static int response = 0;
	public static int display(String title, String message, int range) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		Button oneButton = new Button("One");
		Button twoButton = new Button("Two");
		Button threeButton = new Button("Three");
		
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
		
		
		HBox options = new HBox(5);
		options.setAlignment(Pos.CENTER);
		if (range == 1) {
			options.getChildren().addAll(oneButton);
		}
		if (range == 2) {
			options.getChildren().addAll(oneButton, twoButton);
		}
		if (range >= 3) {
			options.getChildren().addAll(oneButton, twoButton, threeButton);
		}
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, options);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
		return response;
	}
}
