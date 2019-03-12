package risk.application;
/*
 * note to whomever ventures into this section of the codebase (Probably just future me) 
 * This code is riddled with some pretty bad practices, and quite a few 'hacks'
 * but there is some cleaver stuff here and there--albeit slightly obfuscated by a pretty major design flaw
 * Everything is so fragmented, that it made communication between the controllers, views, and models difficult
 *  ~ ~ on the bright side, figured out how to avoid those mistakes.. just not in time for this project ~ ~
 * So, there's kind of some ugly stuff going on with large switch statements, and if blocks.
 * If you're wondering what my thought process was: I had already dug a 10ft grave. So the only way to survive
 * was to keep digging until i made it to China. ~~Hence why some of this stuff might as well be in Chinese. (really sorry btw)
 * 
*/
import javafx.application.Application;
import javafx.stage.Stage;
import risk.controllers.RiskController;
import risk.controllers.viewControllers.NumberOptionBox;
import risk.controllers.viewControllers.NumberSliderBox;
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
