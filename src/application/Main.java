package application;
	
import java.io.IOException;

import controllers.RiskController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		RiskController.run(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
