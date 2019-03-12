package risk.controllers.viewControllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {
	public static void display(String title, String message) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UTILITY);
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setMinHeight(150);
		Label label = new Label();
		label.setText(message);
		
		Button yesButton = new Button("OK");
		
		yesButton.setOnAction(EventHandler -> {
			stage.close();
		});
		
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, yesButton);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
	}
}
