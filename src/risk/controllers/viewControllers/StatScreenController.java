package risk.controllers.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import risk.controllers.RiskController;
import risk.controllers.viewControllers.interfaces.Displayable;
import risk.controllers.viewControllers.interfaces.View;


public class StatScreenController implements View{

	//FXML Variables
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox player1StatBank;

    @FXML
    private Label player1Name;

    @FXML
    private ImageView player1Picture;

    @FXML
    private Label player1Stats;

    @FXML
    private VBox player2StatBank;

    @FXML
    private Label player2Name;

    @FXML
    private ImageView player2Picture;

    @FXML
    private Label player2Stats;

    @FXML
    private VBox player3StatBank;

    @FXML
    private Label player3Name;

    @FXML
    private ImageView player3Picture;

    @FXML
    private Label player3Stats;

    @FXML
    private VBox player4StatBank;

    @FXML
    private Label player4Name;

    @FXML
    private ImageView player4Picture;

    @FXML
    private Label player4Stats;

    @FXML
    private VBox Player5StatBank;

    @FXML
    private Label player5Name;

    @FXML
    private ImageView player5Picture;

    @FXML
    private Label player5Stats;

    @FXML
    private VBox player6StatBank;

    @FXML
    private Label player6Name;

    @FXML
    private ImageView player6Picture;

    @FXML
    private Label player6Stats;

    @FXML
    private Button mainMenuButton;

    //FXML Methods
    
    @FXML
    void showMainMenu(ActionEvent event) {
    	viewController.showStartMenu();
    }
    
    @FXML
    void initialize() {
        assert player1StatBank != null : "fx:id=\"player1StatBank\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player1Name != null : "fx:id=\"player1Name\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player1Picture != null : "fx:id=\"player1Picture\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player1Stats != null : "fx:id=\"player1Stats\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player2StatBank != null : "fx:id=\"player2StatBank\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player2Name != null : "fx:id=\"player2Name\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player2Picture != null : "fx:id=\"player2Picture\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player2Stats != null : "fx:id=\"player2Stats\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player3StatBank != null : "fx:id=\"player3StatBank\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player3Name != null : "fx:id=\"player3Name\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player3Picture != null : "fx:id=\"player3Picture\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player3Stats != null : "fx:id=\"player3Stats\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player4StatBank != null : "fx:id=\"player4StatBank\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player4Name != null : "fx:id=\"player4Name\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player4Picture != null : "fx:id=\"player4Picture\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player4Stats != null : "fx:id=\"player4Stats\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert Player5StatBank != null : "fx:id=\"Player5StatBank\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player5Name != null : "fx:id=\"player5Name\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player5Picture != null : "fx:id=\"player5Picture\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player5Stats != null : "fx:id=\"player5Stats\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player6StatBank != null : "fx:id=\"player6StatBank\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player6Name != null : "fx:id=\"player6Name\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player6Picture != null : "fx:id=\"player6Picture\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert player6Stats != null : "fx:id=\"player6Stats\" was not injected: check your FXML file 'statScreen.fxml'.";
        assert mainMenuButton != null : "fx:id=\"mainMenuButton\" was not injected: check your FXML file 'statScreen.fxml'.";
        player1StatBank.managedProperty().bind(player1StatBank.visibleProperty());
        player2StatBank.managedProperty().bind(player2StatBank.visibleProperty());
        player3StatBank.managedProperty().bind(player3StatBank.visibleProperty());
        player4StatBank.managedProperty().bind(player4StatBank.visibleProperty());
        Player5StatBank.managedProperty().bind(Player5StatBank.visibleProperty());
        player6StatBank.managedProperty().bind(player6StatBank.visibleProperty());
        Displayable[] players = RiskController.getPlayers();
        String[][] stats = RiskController.getPlayerStats();
        switch (stats.length) {
        case 2:
        	player1Name.setText(players[0].displayName());
        	player2Name.setText(players[1].displayName());
        	player1Stats.setText(buildStatString(stats[0]));
        	player2Stats.setText(buildStatString(stats[1]));
        	player1Picture.setImage(new Image(getClass().getResourceAsStream("/risk/views/Image/"+players[0].resourceLocation()+".png")));
        	player2Picture.setImage(new Image(getClass().getResourceAsStream("/risk/views/Image/"+players[1].resourceLocation()+".png")));
        	player3StatBank.setVisible(false);
        case 3:
        	player3Name.setText(players[2].displayName());
        	player3Stats.setText(buildStatString(stats[2]));
        	player3Picture.setImage(new Image(getClass().getResourceAsStream("/risk/views/Image/"+players[2].resourceLocation()+".png")));
        	player4StatBank.setVisible(false);
        case 4:
        	player4Name.setText(players[3].displayName());
        	player4Stats.setText(buildStatString(stats[3]));
        	player4Picture.setImage(new Image(getClass().getResourceAsStream("/risk/views/Image/"+players[3].resourceLocation()+".png")));
        	Player5StatBank.setVisible(false);
        case 5:
        	player5Name.setText(players[4].displayName());
        	player5Stats.setText(buildStatString(stats[4]));
        	player5Picture.setImage(new Image(getClass().getResourceAsStream("/risk/views/Image/"+players[4].resourceLocation()+".png")));
        	player6StatBank.setVisible(false);
        case 6:
        	player6Name.setText(players[5].displayName());
        	player6Stats.setText(buildStatString(stats[5]));
        	player6Picture.setImage(new Image(getClass().getResourceAsStream("/risk/views/Image/"+players[5].resourceLocation()+".png")));
        }
        
    }
    
    //Interface Methods
    
    @Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub
		
	}
    
    //Controller variables
    
    private ViewController viewController;
    
    //Controller Methods
    
    public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
    
    public String buildStatString(String[] stats) {
    	StringBuilder sBuilder = new StringBuilder();
    	for (String stat : stats) {
    		sBuilder.append(stat).append("\n");
    	}
    	return sBuilder.toString();
    }
}
