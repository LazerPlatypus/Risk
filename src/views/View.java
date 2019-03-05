package views;

import com.sun.java.swing.plaf.windows.resources.windows;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class View extends Application{
	Stage window;
	public View() {
		
	}
	public View(Stage window) {
		this.window = window;
	}
	public void showWindow(boolean show) {
		if (show) {
			window.show();
		} else {
			window.hide();
		}
	}
}
