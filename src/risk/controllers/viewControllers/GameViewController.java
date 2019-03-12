package risk.controllers.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import risk.controllers.GameSetup;
import risk.controllers.RiskController;
import risk.controllers.Turn;
import risk.models.enums.CountryName;
import risk.models.enums.TerritoryName;

public class GameViewController implements View, Initializable{
	//Continents
	@FXML
	private Button northAmericaSelector;
	@FXML
	private Button southAmericaSelector;
	@FXML              
	private Button europeSelector;
	@FXML                       
	private Button africaSelector;
	@FXML                       
	private Button asiaSelector;
	@FXML                         
	private Button australiaSelector;
	@FXML                    
	//Territories                                       
	private ImageView alaskaSelector;
	@FXML                       
	private ImageView albertaSelector;
	@FXML                      
	private ImageView centralAmericaSelector;
	@FXML               
	private ImageView easternUnitedStatesSelector;
	@FXML          
	private ImageView greenlandSelector;
	@FXML                    
	private ImageView northwestTerritorySelector;
	@FXML           
	private ImageView ontarioSelector;
	@FXML               
	private ImageView quebecSelector;
	@FXML                
	private ImageView westernUnitedStatesSelector;
	@FXML          
	private ImageView argentinaSelector;
	@FXML                    
	private ImageView brazilSelector;
	@FXML                       
	private ImageView peruSelector;
	@FXML                         
	private ImageView venezuelaSelector;
	@FXML                    
	private ImageView greatBritianSelector;
	@FXML       
	private ImageView icelandSelector;
	@FXML                      
	private ImageView northernEuropeSelector;
	@FXML               
	private ImageView ukraineSelector;
	@FXML                      
	private ImageView westernEuropeSelector;
	@FXML                              
	private ImageView eastAfricaSelector;
	@FXML                   
	private ImageView egyptSelector;
	@FXML                        
	private ImageView madagascarSelector;
	@FXML                   
	private ImageView northAfricaSelector;
	@FXML                  
	private ImageView southAfricaSelector;
	@FXML                  
	private ImageView afghanistanSelector;
	@FXML                  
	private ImageView chinaSelector;
	@FXML                        
	private ImageView indiaSelector;
	@FXML                        
	private ImageView irkutskSelector;
	@FXML                      
	private ImageView japanSelector;
	@FXML                        
	private ImageView kamchatkaSelector;
	@FXML                     
	private ImageView middleEastSelector;
	@FXML                   
	private ImageView mongoliaSelector;
	@FXML                     
	private ImageView siamSelector;
	@FXML                
	private ImageView siberiaSelector;
	@FXML                      
	private ImageView uralSelector;
	@FXML                         
	private ImageView yakutskSelector;
	@FXML                      
	private ImageView easternAustraliaSelector;
	@FXML             
	private ImageView indonesiaSelector;
	@FXML                   
	private ImageView newGuineaSelector;
	@FXML                    
	private ImageView westernAustraliaSelector;
	@FXML  
	private ImageView congoSelector;
	@FXML
	private ImageView southernEuropeSelector;
	@FXML
	private ImageView scandinaviaSelector;
	//player buttons                                    
	@FXML
	private Button player1;
	@FXML                              
	private Button player2;
	@FXML                              
	private Button player3;
	@FXML                              
	private Button player4;
	@FXML                              
	private Button player5;
	@FXML                              
	private Button player6;
	//aux buttons                                       
	@FXML                              
	private Button button1;
	@FXML                                 
	private Button button2;
	@FXML                                 
	private Button aux1;
	@FXML                              
	private Button aux2;
	@FXML                              
	private Button aux3;
	//turn buttons                                      
	@FXML                              
	private Button next;
	@FXML                                 
	private Button attack;
	//Map                                               
	@FXML                               
	private ImageView mapView;
	//image for context pane                            
	@FXML                           
	private ImageView selectedItemView;
	//label for context pane                            
	@FXML                  
	private Label contentNameLabel;
	@FXML                      
	private Label contentInfoLabel;
	@FXML 
	private Label instructionsLabel;
	//menu Buttons                                      
	@FXML                      
	private Menu file;
	@FXML                                   
	private Menu edit;
	@FXML                                   
	private Menu help;
	@FXML
	private AnchorPane anchorPane;
	
	
	private Displayable currentItemToDisplay;
	private ViewController viewController;
	public void setNumOfPlayers(int players) {
		switch (players) {
		case 2:
			hideButton(3);

		case 3:
			hideButton(4);

		case 4:
			hideButton(5);

		case 5:
			hideButton(6);
		}
	}
	@Override
	public void showError(String error) {
		instructionsLabel.setText(error);
		instructionsLabel.setVisible(true);
		
	}
	@Override
	public void hideError() {
		instructionsLabel.setVisible(false);
		
	}
	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	@FXML
	private void selectCountry(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		int sourceHashCode = source.hashCode();
		CountryName selection = null;
		if (sourceHashCode==northAmericaSelector.hashCode()) {
			selection = CountryName.NORTH_AMERICA;
		}
		else if (sourceHashCode==southAmericaSelector.hashCode()) {
			selection = CountryName.SOUTH_AMERICA;
		}
		else if (sourceHashCode==europeSelector.hashCode()) {
			selection = CountryName.EUROPE;
		}
		else if (sourceHashCode==africaSelector.hashCode()) {
			selection = CountryName.AFRICA;
		}
		else if (sourceHashCode==asiaSelector.hashCode()) {
			selection = CountryName.ASIA;
		}
		else if (sourceHashCode==australiaSelector.hashCode()) {
			selection = CountryName.AUSTRALIA;
		}
		currentItemToDisplay = RiskController.getCountryToDisplay(selection);
		updateContextScreen();
	}
	
	@FXML
	private void selectTerritory(MouseEvent mouseEvent) {
		Object source = mouseEvent.getSource();
		int sourceHashCode = source.hashCode();
		CountryName countryKey = null;
		TerritoryName territoryKey = null;
		if (sourceHashCode==alaskaSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.ALASKA;
		}
		else if (sourceHashCode==albertaSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.ALBERTA;
		}
		else if (sourceHashCode==centralAmericaSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.CENTERAL_AMERICA;
		}
		else if (sourceHashCode==easternUnitedStatesSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.EASTERN_UNITED_STATES;
		}
		else if (sourceHashCode==greenlandSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.GREENLAND;
		}
		else if (sourceHashCode==northwestTerritorySelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.NORTHWEST_TERRITORY;
		}
		else if (sourceHashCode==ontarioSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.ONTARIO;
		}
		else if (sourceHashCode==quebecSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.QUEBEC;
		}
		else if (sourceHashCode==westernUnitedStatesSelector.hashCode()) {
			countryKey = CountryName.NORTH_AMERICA;
			territoryKey = TerritoryName.WESTERN_UNITED_STATES;
		}
		else if (sourceHashCode==argentinaSelector.hashCode()) {
			countryKey = CountryName.SOUTH_AMERICA;
			territoryKey = TerritoryName.ARGENTINA;
		}
		else if (sourceHashCode==brazilSelector.hashCode()) {
			countryKey = CountryName.SOUTH_AMERICA;
			territoryKey = TerritoryName.BRAZIL;
		}
		else if (sourceHashCode==peruSelector.hashCode()) {
			countryKey = CountryName.SOUTH_AMERICA;
			territoryKey = TerritoryName.PERU;
		}
		else if (sourceHashCode==venezuelaSelector.hashCode()) {
			countryKey = CountryName.SOUTH_AMERICA;
			territoryKey = TerritoryName.VENEZUELA;
		}
		else if (sourceHashCode==greatBritianSelector.hashCode()) {
			countryKey = CountryName.EUROPE;
			territoryKey = TerritoryName.GREAT_BRITAIN;
		}
		else if (sourceHashCode==icelandSelector.hashCode()) {
			countryKey = CountryName.EUROPE;
			territoryKey = TerritoryName.ICELAND;
		}
		else if (sourceHashCode==northernEuropeSelector.hashCode()) {
			countryKey = CountryName.EUROPE;
			territoryKey = TerritoryName.NORTHERN_EUROPE;
		}
		else if (sourceHashCode==ukraineSelector.hashCode()) {
			countryKey = CountryName.EUROPE;
			territoryKey = TerritoryName.UKRAINE;
		}
		else if (sourceHashCode==westernEuropeSelector.hashCode()) {
			countryKey = CountryName.EUROPE;
			territoryKey = TerritoryName.WESTERN_EUROPE;
		}
		else if (sourceHashCode==congoSelector.hashCode()) {
			countryKey = CountryName.AFRICA;
			territoryKey = TerritoryName.CONGO;
		}
		else if (sourceHashCode==eastAfricaSelector.hashCode()) {
			countryKey = CountryName.AFRICA;
			territoryKey = TerritoryName.EAST_AFRICA;
		}
		else if(sourceHashCode==egyptSelector.hashCode()) {
			countryKey = CountryName.AFRICA;
			territoryKey = TerritoryName.EGYPT;
		}
		else if (sourceHashCode==madagascarSelector.hashCode()) {
			countryKey = CountryName.AFRICA;
			territoryKey = TerritoryName.MADAGASCAR;
		}
		else if (sourceHashCode==northAfricaSelector.hashCode()) {
			countryKey = CountryName.AFRICA;
			territoryKey = TerritoryName.NORTH_AFRICA;
		}
		else if (sourceHashCode==southAfricaSelector.hashCode()) {
			countryKey = CountryName.AFRICA;
			territoryKey = TerritoryName.SOUTH_AFRICA;
		}
		else if (sourceHashCode==afghanistanSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.AFGHANISTAN;
		}
		else if (sourceHashCode==chinaSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.CHINA;
		}
		else if (sourceHashCode==indiaSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.INDIA;
		}
		else if (sourceHashCode==irkutskSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.IRKUTSK;
		}
		else if (sourceHashCode==japanSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.JAPAN;
		}
		else if (sourceHashCode==kamchatkaSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.KAMCATKA;
		}
		else if (sourceHashCode==middleEastSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.MIDDLE_EAST;
		}
		else if (sourceHashCode==mongoliaSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.MONGOLIA;
		}
		else if (sourceHashCode==siamSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.SIAM;
		}
		else if (sourceHashCode==siberiaSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.SIBERIA;
		}
		else if (sourceHashCode==uralSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.URAL;
		}
		else if (sourceHashCode==yakutskSelector.hashCode()) {
			countryKey = CountryName.ASIA;
			territoryKey = TerritoryName.YAKUTSK;
		}
		else if (sourceHashCode==easternAustraliaSelector.hashCode()) {
			countryKey = CountryName.AUSTRALIA;
			territoryKey = TerritoryName.EASTERN_AUSTRALIA;
		}
		else if (sourceHashCode==indonesiaSelector.hashCode()) {
			countryKey = CountryName.AUSTRALIA;
			territoryKey = TerritoryName.INDONEASIA;
		}
		else if (sourceHashCode==newGuineaSelector.hashCode()) {
			countryKey = CountryName.AUSTRALIA;
			territoryKey = TerritoryName.NEW_GUINEA;
		}
		else if (sourceHashCode==westernAustraliaSelector.hashCode()) {
			countryKey = CountryName.AUSTRALIA;
			territoryKey = TerritoryName.WESTERN_AUSTRALIA;
		}
		else if (sourceHashCode==scandinaviaSelector.hashCode()) {
			countryKey = CountryName.EUROPE;
			territoryKey = TerritoryName.SCANDINAVIA;
		}
		else if (sourceHashCode==southernEuropeSelector.hashCode()) {
			countryKey=CountryName.EUROPE;
			territoryKey = TerritoryName.SOUTHERN_EUROPE;
		}
		if (countryKey != null && territoryKey != null) {
			currentItemToDisplay = RiskController.getTerritoryToDisplay(countryKey,territoryKey);
			updateContextScreen();
		}
	}
	
	public void updateContextScreen() {
		contentNameLabel.setText(currentItemToDisplay.displayName());
		contentInfoLabel.setText(currentItemToDisplay.displayUnits());
		System.out.println("/risk/views/Images/"+currentItemToDisplay.resourceLocation()+".png");
		selectedItemView.setImage(new Image(getClass().getResourceAsStream("/risk/views/Images/"+currentItemToDisplay.resourceLocation()+".png")));
	}
	
	@FXML 
	private void selectPlayer(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		int sourceHashCode = source.hashCode();
		int  selection = 0;
		if (sourceHashCode==player1.hashCode()) {
			selection = 0;
		}
		else if (sourceHashCode==player2.hashCode()) {
			selection = 1;
		}
		else if (sourceHashCode==player3.hashCode()) {
			selection = 2;
		}
		else if (sourceHashCode==player4.hashCode()) {
			selection = 3;
		}
		else if (sourceHashCode==player5.hashCode()) {
			selection = 4;
		}
		else if (sourceHashCode==player6.hashCode()) {
			selection = 5;
		}
		currentItemToDisplay = RiskController.getPlayerToDisplay(selection);
		updateContextScreen();
	}
	public void hideButton(int buttonID) {
		switch (buttonID) {
		case 0: aux1.setVisible(false);
		break;
		case 1: player1.setVisible(false);
		break;
		case 2: player2.setVisible(false);
		break;
		case 3: player3.setVisible(false);
		break;
		case 4: player4.setVisible(false);
		break;
		case 5: player5.setVisible(false);
		break;
		case 6: player6.setVisible(false);
		break;
		case 7: aux2.setVisible(false);
		break;
		case 8: aux3.setVisible(false);
		break;
		case 9: button1.setVisible(false);
		break;
		case 10: button2.setVisible(false);
		break;
		case 11: attack.setVisible(false);
		}
	}
	public void showButton(int buttonID) {
		switch (buttonID) {
		case 0: aux1.setVisible(true);
		break;
		case 1: player1.setVisible(true);
		break;                     
		case 2: player2.setVisible(true);
		break;                     
		case 3: player3.setVisible(true);
		break;                     
		case 4: player4.setVisible(true);
		break;                     
		case 5: player5.setVisible(true);
		break;                     
		case 6: player6.setVisible(true);
		break;
		case 7: aux2.setVisible(true);
		break;
		case 8: aux3.setVisible(true);
		break;
		case 9: button1.setVisible(true);
		break;
		case 10: button2.setVisible(true);
		break;
		case 11: attack.setVisible(true);
		}
	}
	@FXML
	private void doAuxAction(ActionEvent actionEvent) {
		int sourceHashCode = actionEvent.getSource().hashCode();
		if (sourceHashCode == aux1.hashCode()) {
			GameSetup.placeUnit();
		}
		else if (sourceHashCode == aux2.hashCode()) {
			Turn.placeUnit();
		}
		else if (sourceHashCode == aux3.hashCode()) {
			Turn.startFreeMove();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setImageStyles();
		player3.managedProperty().bind(player3.visibleProperty());
		player4.managedProperty().bind(player4.visibleProperty());
		player5.managedProperty().bind(player5.visibleProperty());
		player6.managedProperty().bind(player6.visibleProperty());	
		instructionsLabel.managedProperty().bind(instructionsLabel.visibleProperty());
		aux1.managedProperty().bind(aux1.visibleProperty());
		aux2.managedProperty().bind(aux2.visibleProperty());
		aux3.managedProperty().bind(aux3.visibleProperty());
		button1.managedProperty().bind(button1.visibleProperty());
		button2.managedProperty().bind(button2.visibleProperty());
		attack.managedProperty().bind(attack.visibleProperty());
		mapView.setPreserveRatio(true);
		Image mapImage = new Image("/risk/views/Images/risk-1-original.jpg");
		mapView.setImage(mapImage);
	}
	@Override
	public void updateDisplay() {
		updateContextScreen();
		
	}
	
	@FXML
	private void doButtonAction(ActionEvent actionEvent) {
		int sourceHashCode = actionEvent.getSource().hashCode();
		if (sourceHashCode == button1.hashCode()) {
			viewController.showStartMenu();
		}
		else if (sourceHashCode == button2.hashCode()) {
			
		}
	}
	
	
	@FXML
	private void attack(ActionEvent actionEvent) {
		Turn.startAttack();
	}
	
	@FXML
	private void next(ActionEvent actionEvent) {
		
	}
	
	private void turnOffPickOnBoundsFor(Node n) {
	    n.setPickOnBounds(false);
	    if (n instanceof Parent) {
	      for (Node c: ((Parent) n).getChildrenUnmodifiable()) {
	        turnOffPickOnBoundsFor(c);
	      }
	    }
	  }
	private void setImageStyles() {
		turnOffPickOnBoundsFor(afghanistanSelector);
		turnOffPickOnBoundsFor(alaskaSelector);
		turnOffPickOnBoundsFor(albertaSelector);
		turnOffPickOnBoundsFor(argentinaSelector);
		turnOffPickOnBoundsFor(brazilSelector);
		turnOffPickOnBoundsFor(centralAmericaSelector);
		turnOffPickOnBoundsFor(chinaSelector);
		turnOffPickOnBoundsFor(congoSelector);
		turnOffPickOnBoundsFor(eastAfricaSelector);
		turnOffPickOnBoundsFor(easternAustraliaSelector);
		turnOffPickOnBoundsFor(easternUnitedStatesSelector);
		turnOffPickOnBoundsFor(egyptSelector);
		turnOffPickOnBoundsFor(greatBritianSelector);
		turnOffPickOnBoundsFor(greenlandSelector);
		turnOffPickOnBoundsFor(icelandSelector);
		turnOffPickOnBoundsFor(indiaSelector);
		turnOffPickOnBoundsFor(indonesiaSelector);
		turnOffPickOnBoundsFor(irkutskSelector);
		turnOffPickOnBoundsFor(japanSelector);
		turnOffPickOnBoundsFor(kamchatkaSelector);
		turnOffPickOnBoundsFor(madagascarSelector);
		turnOffPickOnBoundsFor(middleEastSelector);
		turnOffPickOnBoundsFor(mongoliaSelector);
		turnOffPickOnBoundsFor(newGuineaSelector);
		turnOffPickOnBoundsFor(northAfricaSelector);
		turnOffPickOnBoundsFor(northernEuropeSelector);
		turnOffPickOnBoundsFor(northwestTerritorySelector);
		turnOffPickOnBoundsFor(ontarioSelector);
		turnOffPickOnBoundsFor(peruSelector);
		turnOffPickOnBoundsFor(quebecSelector);
		turnOffPickOnBoundsFor(scandinaviaSelector);
		turnOffPickOnBoundsFor(siamSelector);
		turnOffPickOnBoundsFor(siberiaSelector);
		turnOffPickOnBoundsFor(southAfricaSelector);
		turnOffPickOnBoundsFor(southernEuropeSelector);
		turnOffPickOnBoundsFor(ukraineSelector);
		turnOffPickOnBoundsFor(uralSelector);
		turnOffPickOnBoundsFor(venezuelaSelector);
		turnOffPickOnBoundsFor(westernAustraliaSelector);
		turnOffPickOnBoundsFor(westernEuropeSelector);
		turnOffPickOnBoundsFor(westernUnitedStatesSelector);
		turnOffPickOnBoundsFor(yakutskSelector);
		
	}
	
}
