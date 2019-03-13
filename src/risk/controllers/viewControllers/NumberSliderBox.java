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
	
	//class logic
	
	public static int display(String title, String message, int min, int max) {
		//make a new window
		Stage stage = new Stage();
		//set the window to be in focus
		stage.initModality(Modality.APPLICATION_MODAL);
		//set the title based on parameters
		stage.setTitle(title);
		//set the minimum width
		stage.setMinWidth(250);
		//set the minimum height
		stage.setMinHeight(150);
		//make a new label based on parameters
		Label label = new Label(message);
		label.setWrapText(true);
		label.getStyleClass().add("contentText");
		//make a new slider based on parameters
		Slider slider = new Slider(min, max, (Integer)(min+max)/2);
		//set the increments to 1
		slider.setBlockIncrement(1);
		//make it so the biggerst increment/movement is 1
		slider.setMajorTickUnit(1);
		//make it so the smallest movement is 0
		slider.setMinorTickCount(0);
		//show the tick marks at every increment
		slider.setShowTickMarks(true);
		//make it snap to the increments
		slider.setSnapToTicks(true);
		
		//make a label
		Label numberLabel = new Label();
		numberLabel.getStyleClass().add("contentText");
		//link it to the slider, so it always displays the number on the slider
		numberLabel.textProperty().bind(Bindings.format("%.0f", slider.valueProperty()));
		
		//make a button
		Button confirmButton = new Button("OK");
		//define a labda function that closes the window
		confirmButton.setOnAction(EventHandler -> {
			stage.close();
		});
		//make a vbox with spacing 10
		VBox layout = new VBox(10);
		//set center alignment
		layout.setAlignment(Pos.CENTER);
		//add the message label, the slider, the numberlabel, and the confirmButton to the vbox
		layout.getChildren().addAll(label, slider, numberLabel, confirmButton);
		//make a scene based off the layout and set it to the stage
		Scene scene = new Scene(layout);
		//add css to the scene
		scene.getStylesheets().add("/risk/views/FXML/style2.css");
		stage.setScene(scene);
		//show the window and halt code until the window is closed
		stage.showAndWait();
		//return the slider value.
		return slider.valueProperty().intValue();
	}
}
