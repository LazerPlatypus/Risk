package risk.controllers.viewControllers;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NumberSliderBox {
	public static int display(String title, String message, int min, int max) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setMinHeight(150);
		Label label = new Label();
		label.setText(message);
		
		Slider slider = new Slider(min, max, (Integer)(min+max)/2);
		slider.setBlockIncrement(1);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setShowTickMarks(true);
		slider.setSnapToTicks(true);
		
		Label numberLabel = new Label();
		numberLabel.textProperty().bind(Bindings.format("%.0f", slider.valueProperty()));
		
		
		Button confirmButton = new Button("OK");
		confirmButton.setOnAction(EventHandler -> {
			stage.close();
		});
		
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, slider, numberLabel, confirmButton);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
		return slider.valueProperty().intValue();
	}
}
