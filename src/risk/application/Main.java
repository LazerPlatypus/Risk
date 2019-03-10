package risk.application;

import javafx.application.Application;
import javafx.stage.Stage;
import risk.controllers.RiskController;
import risk.controllers.viewControllers.ViewController;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void start(Stage primaryStage) {
//		ViewController.initilizeView(primaryStage);
		RiskController.run(primaryStage);
	}

}
