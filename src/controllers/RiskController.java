package controllers;

import java.io.IOException;

import javafx.stage.Stage;
import views.GameView;
import views.StartMenuController;

public class RiskController {
	public static StartMenuController startMenuController;
	public static GameView gameViewController;
	public static void run(Stage window) {
		startMenuController = new StartMenuController(window);
		startMenuController.showWindow(false);
		gameViewController = new GameView(window);
		gameViewController.showWindow(true);
	}
}
