package controllers;

import java.io.IOException;

import com.sun.glass.ui.TouchInputSupport;

import javafx.stage.Stage;
import models.Selection;
import views.GameView;
import views.StartMenuController;
public class RiskController {
	public static Selection currentSelection;
	public static StartMenuController startMenuController;
	public static GameView gameViewController;
	public static void run(Stage window) {
		startMenuController = new StartMenuController(window);
		startMenuController.showWindow(false);
		gameViewController = new GameView(window);
		gameViewController.showWindow(true);
	}
	public static void setSelection(Selection selection) {
		currentSelection = selection;
	}
}
