package risk.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import risk.models.Board;
import risk.models.Card;
import risk.models.Country;
import risk.models.Die;
import risk.models.Map;
import risk.models.Player;
import risk.models.Territory;
import risk.models.Unit;
import risk.models.enums.CountryName;
import risk.models.enums.DieType;
import risk.models.enums.TerritoryName;
import risk.models.enums.UnitColor;
import risk.models.enums.UnitName;
import risk.models.enums.UnitStatus;

public class BoardController {

	//this class has some pretty mean methods responsible for the creation, importation, and storing of board data
	//note that the board contains almost all the data for the game, so its pretty serious stuff.
	
	
	//This method creates a new board
	public static Board CreateBoard(String[] playerNames) {
		//First it makes the map, which is nothing more than a HashMap of countries
		//the CountryName Enum is used as the key, as it prevents errors in 'get'-ing countries from the HashMap
		//and allows for a few shortcuts with the enum's .values() property.
		HashMap<CountryName, Country> countries = new HashMap<>();
		//to loop through each continent, we simply need to loop through each index
		//of the CountryName Enum!
		for (int i = 0; i < CountryName.values().length; i++) {
			//because we are looping though each Enum in CountryName, we can safely reference each country by
			//the index 'i', instead of checking the CountryName each time.
			CountryName countryName = CountryName.values()[i];
			//Each country contains a HashMap of Territory, referenced by the Enum Territory name, for the same reasons
			//country is.
			//Each country contains a unique HashMap of Territory, so we need to create a new HashMap each time.
			HashMap<TerritoryName, Territory> territories = new HashMap<>();
			//I couldn't figure out a more cleaver way to do this, so each Territory is manually added to the hashmap
			switch (i) {
			case 0:
				//North America contains: (all of these are according to official Risk Rules)
				//Alaska, Alberta, Central America, Eastern United States, Greenland, Northwest Territory
				//Ontario, Quebec, and the Western United States
				territories.put(TerritoryName.ALASKA, new Territory(new ArrayList<Unit>(), TerritoryName.ALASKA));
				territories.put(TerritoryName.ALBERTA, new Territory(new ArrayList<Unit>(), TerritoryName.ALBERTA));
				territories.put(TerritoryName.CENTERAL_AMERICA, new Territory(new ArrayList<Unit>(), TerritoryName.CENTERAL_AMERICA));
				territories.put(TerritoryName.EASTERN_UNITED_STATES, new Territory(new ArrayList<Unit>(), TerritoryName.EASTERN_UNITED_STATES));
				territories.put(TerritoryName.GREENLAND, new Territory(new ArrayList<Unit>(), TerritoryName.GREENLAND));
				territories.put(TerritoryName.NORTHWEST_TERRITORY, new Territory(new ArrayList<Unit>(), TerritoryName.NORTHWEST_TERRITORY));
				territories.put(TerritoryName.ONTARIO, new Territory(new ArrayList<Unit>(), TerritoryName.ONTARIO));
				territories.put(TerritoryName.QUEBEC, new Territory(new ArrayList<Unit>(), TerritoryName.QUEBEC));
				territories.put(TerritoryName.WESTERN_UNITED_STATES, new Territory(new ArrayList<Unit>(), TerritoryName.WESTERN_UNITED_STATES));
				break;
			case 1:
				//South America contains:
				//Argentina, Brazil, Peru, and Venezuela
				territories.put(TerritoryName.ARGENTINA, new Territory(new ArrayList<Unit>(), TerritoryName.ARGENTINA));
				territories.put(TerritoryName.BRAZIL, new Territory(new ArrayList<Unit>(), TerritoryName.BRAZIL));
				territories.put(TerritoryName.PERU, new Territory(new ArrayList<Unit>(), TerritoryName.PERU));
				territories.put(TerritoryName.VENEZUELA, new Territory(new ArrayList<Unit>(), TerritoryName.VENEZUELA));
				break;
			case 2:
				//Europe contains:
				//Great Britian, Iceland, Northern Europe, Ukraine, Western Europe, Southern Europe, and Scandinavia
				territories.put(TerritoryName.GREAT_BRITAIN, new Territory(new ArrayList<Unit>(), TerritoryName.GREAT_BRITAIN));
				territories.put(TerritoryName.ICELAND, new Territory(new ArrayList<Unit>(), TerritoryName.ICELAND));
				territories.put(TerritoryName.NORTHERN_EUROPE, new Territory(new ArrayList<Unit>(), TerritoryName.NORTHERN_EUROPE));
				territories.put(TerritoryName.UKRAINE, new Territory(new ArrayList<Unit>(), TerritoryName.UKRAINE));
				territories.put(TerritoryName.WESTERN_EUROPE, new Territory(new ArrayList<Unit>(), TerritoryName.WESTERN_EUROPE));
				territories.put(TerritoryName.SOUTHERN_EUROPE, new Territory(new ArrayList<Unit>(), TerritoryName.SOUTHERN_EUROPE));
				territories.put(TerritoryName.SCANDINAVIA, new Territory(new ArrayList<Unit>(), TerritoryName.SCANDINAVIA));
				break;
			case 3:
				//Africa contains:
				//Congo, East Africa, Egypt, Madagascar, North Africa, and South Africa
				territories.put(TerritoryName.CONGO, new Territory(new ArrayList<Unit>(), TerritoryName.CONGO));
				territories.put(TerritoryName.EAST_AFRICA, new Territory(new ArrayList<Unit>(), TerritoryName.EAST_AFRICA));
				territories.put(TerritoryName.EGYPT, new Territory(new ArrayList<Unit>(), TerritoryName.EGYPT));
				territories.put(TerritoryName.MADAGASCAR, new Territory(new ArrayList<Unit>(), TerritoryName.MADAGASCAR));
				territories.put(TerritoryName.NORTH_AFRICA, new Territory(new ArrayList<Unit>(), TerritoryName.NORTH_AFRICA));
				territories.put(TerritoryName.SOUTH_AFRICA, new Territory(new ArrayList<Unit>(), TerritoryName.SOUTH_AFRICA));
				break;
			case 4:
				//Asia contains:
				//Afghanistan, China, India, Irkutsk, Japan, Kamchatka, Middle East, Mongolia, Siam,
				//Siberia, Ural, Yakutsk
				territories.put(TerritoryName.AFGHANISTAN, new Territory(new ArrayList<Unit>(), TerritoryName.AFGHANISTAN));
				territories.put(TerritoryName.CHINA, new Territory(new ArrayList<Unit>(), TerritoryName.CHINA));
				territories.put(TerritoryName.INDIA, new Territory(new ArrayList<Unit>(), TerritoryName.INDIA));
				territories.put(TerritoryName.IRKUTSK, new Territory(new ArrayList<Unit>(), TerritoryName.IRKUTSK));
				territories.put(TerritoryName.JAPAN, new Territory(new ArrayList<Unit>(), TerritoryName.JAPAN));
				territories.put(TerritoryName.KAMCHATKA, new Territory(new ArrayList<Unit>(), TerritoryName.KAMCHATKA));
				territories.put(TerritoryName.MIDDLE_EAST, new Territory(new ArrayList<Unit>(), TerritoryName.MIDDLE_EAST));
				territories.put(TerritoryName.MONGOLIA, new Territory(new ArrayList<Unit>(), TerritoryName.MONGOLIA));
				territories.put(TerritoryName.SIAM, new Territory(new ArrayList<Unit>(), TerritoryName.SIAM));
				territories.put(TerritoryName.SIBERIA, new Territory(new ArrayList<Unit>(), TerritoryName.SIBERIA));
				territories.put(TerritoryName.URAL, new Territory(new ArrayList<Unit>(), TerritoryName.URAL));
				territories.put(TerritoryName.YAKUTSK, new Territory(new ArrayList<Unit>(), TerritoryName.YAKUTSK));
				break;
			case 5:
				//Australia contains:
				//Eastern Australia, Indonesia, New Guinea, Western Australia
				territories.put(TerritoryName.EASTERN_AUSTRALIA, new Territory(new ArrayList<Unit>(), TerritoryName.EASTERN_AUSTRALIA));
				territories.put(TerritoryName.INDONESIA, new Territory(new ArrayList<Unit>(), TerritoryName.INDONESIA));
				territories.put(TerritoryName.NEW_GUINEA, new Territory(new ArrayList<Unit>(), TerritoryName.NEW_GUINEA));
				territories.put(TerritoryName.WESTERN_AUSTRALIA, new Territory(new ArrayList<Unit>(), TerritoryName.WESTERN_AUSTRALIA));
				break;
			}
			//now that we have each territory that belongs in the country, we can add it to the 'countries' HashMap
			countries.put(countryName, new Country(countryName, territories));
		}
		Map map = new Map(countries);
		//the board also has a list of undrawn cards TODO not fully implemented.
		//there is a card for each Territory, and each card will have a UnitName on it.
		//It doesn't really matter what Territory and what Unit are on the card (combination wise)
		//there just needs to be a semi-equal porportion of Units on the cards.
		ArrayList<Card> undrawnCards = new ArrayList<>();
		for (int i = 0; i < TerritoryName.values().length; i++) {
			//if the index equally divides by three, the card will have a Calvery unit on it -else
			//if the index has 1 left over when divided by three, the card will have a Cannon on it. -else
			//the card will have a Soldier on it
			//in every case, the card will have the Territory indexed by the current iteration through the loop.
			undrawnCards.add(new Card(i%3==0?UnitName.CALVERY:i%1==0?UnitName.CANNON:UnitName.SOLDIER,TerritoryName.values()[i]));
		}
		//the board also has a list of drawn cards, that is empty to start. TODO not fully implemented
		ArrayList<Card> drawnCards = new ArrayList<>();
		//The board stores 3 attack dice TODO not fully implemented
		Die[] attackDice = new Die[] {new Die(DieType.ATTACK, new Random(), 0), new Die(DieType.ATTACK, new Random(), 0), new Die(DieType.ATTACK, new Random(), 0)};
		//The board stores 2 defence dice TODO not fully implemented
		Die[] defenceDice = new Die[] {new Die(DieType.DEFENCE, new Random(), 0), new Die(DieType.DEFENCE, new Random(), 0)};
		//The number of units each player gets to start is based off the number of players, using the following formula:
		//50-the number of players.
		int numOfInctiveUnits = 50-playerNames.length*5;
		//the board will store up to 6 players
		//the number of players is based off the parameter passed in.
		Player[] players = new Player[playerNames.length];
		//Loop through each player, building every individual player
		for (int i = 0; i < players.length; i++) {
			//The name is based off the parameter passed in
			String name = playerNames[i];
			//each player stores inactive (unplaced) units in an list
			//a list was chosen as the number of inactive units fluctuates greatly turn to turn
			ArrayList<Unit> inactiveUnits = new ArrayList<>();
			//the color of the units is based of the index of the player TODO Future idea -- allow the players to choose their color
			UnitColor unitColor = UnitColor.values()[i];
			//the list of inactive units gets populated based of the color of the unit, the number of units
			//with every unit being inactive by default.
			for (int j = 0; j < numOfInctiveUnits; j++) {
				inactiveUnits.add(new Unit(unitColor, UnitStatus.INACTIVE));
			}
			//each player will also store their active (placed) units. at the start there are none.
			ArrayList<Unit> activeUnits = new ArrayList<>();
			//each player will also store cards that they have accrued. TODO not fully implemented 
			//at the start, the players have no cards
			ArrayList<Card> cards = new ArrayList<>();
			//each player will also store any territories they have captured.
			//at the start, they have no territories.
			ArrayList<Territory> ownedTerritories = new ArrayList<>();
			//make the player with all the gathered parameters
			players[i] = new Player(name, inactiveUnits, activeUnits, cards, ownedTerritories); 
		}
		//we will also need to store the order in which players take their turns TODO not fully implemented
		Player[] playerOrder = new Player[playerNames.length];
		for (int i = 0; i < playerOrder.length; i++) {
			playerOrder[i] = players[i];
		}
		//we also need to store the active player
		Player activePlayer = playerOrder[0];
		//we also need to store the state of the game.
		//the state of the game determines what phase of the game we are in
		//(Setup, normal, end)
		//and which player's turn it is.
		//at the start, we are in the setup phase, and its the first player in rotation's turn:
		int[] gameState = new int[] {0,0};
		return new Board(map, undrawnCards, drawnCards, attackDice, defenceDice, players, playerOrder, activePlayer, gameState);
	}
	
	//board loading logic.
	//nothing more than simple deserialization -- but with a twist:
	//we're using a fileChooser to select the file.
	public static Board loadBoard() {
		Board loadedBoard = null;
		FileInputStream fileIn = null;
		ObjectInputStream objectIn = null;
		FileChooser fileChooser = new FileChooser();
		//sets the title of the new windows the filechooser will use
		fileChooser.setTitle("Load a Game");
		//sets where the fileChooser will open, I've selected the user's home directory
		//as most users are more familiar with that directory than any other.
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		//adds a filter to the fileChooser, only allowing .rsf files to be selected -- in hopes to reduce
		//the amount of wrong files being loaded
		//Note: .rsf is a file extension I made up. stands for Risk Save File (hopefully nothing else uses it)
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Risk Save File", "*.rsf"));
		//opens the fileChooser in a new window
		//the script wont progress until the user selects a file, cancels the operation, or closes the window
		File file = fileChooser.showOpenDialog(new Stage());
		//if they cancel the operation, or close the window, the fileChooser will return null.
		if (file != null) {
			try {
				//reads the file from the fileChooser and deserializes it
				fileIn = new FileInputStream(file);
				objectIn = new ObjectInputStream(fileIn);
				loadedBoard = (Board) objectIn.readObject();
			//this *usually* gets thrown if the user specifies a bad path (hard to do with the fileChooser)
			} catch (IOException ioe) {
				RiskController.Error("Invalid file path, please try again.");
			//this *usually* gets thrown if the user selects a file that doesn't have a proper Board class inside
			} catch (ClassNotFoundException cnfe) {
				RiskController.Error("That file does not contain a Risk game save (or it may be corrupted");
			//no matter what:
			} finally {
				//close everything. If an error gets thrown before the FileInputStream or the ObjectInputStream
				//get initialized, it will throw an IOException. this checks to make sure they aren't null
				//increasing the probability that the resources can be successfully closed.
				if (objectIn != null) {
					try {
						objectIn.close();
					} catch (IOException e) {
						// Theoretically, this never happens... Then again, JAVA!
						e.printStackTrace();
					}
				}
				if (fileIn != null) {
					try {
						fileIn.close();
					} catch (IOException e) {
						//Theoretically, this never happens.
						e.printStackTrace();
					}
				}
			}
		}
		return loadedBoard;
	}
	
	//nothig more than serialization, but we're using a fileChooser to place the file
	public static void saveBoard(Board board) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		FileChooser fileChooser = new FileChooser();
		//making sure the user can *only* save the files as a .rsf or RiskSaveFile.
		//this precaution can be negated, by manually typing in the extension, but at that point
		//the user it TRYING to throw an error.
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Risk Save File", "*.rsf"));
		//sets the title of the window
		fileChooser.setTitle("Save Game");
		//sets the directory of the FileChooser, again, the user's home was selected as most users
		//are familiar with that directory.
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			try {
				fileOut = new FileOutputStream(file);
				objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(board);
				//Informs the user that the game was saved.
				//win97 sh*t goin' there.
				RiskController.Error("Game saved succesfully");
				//will get thrown if the user specifies a bad path, or tries to save
				//a null board.
			} catch (IOException ioe) {
				RiskController.Error("Something went terribly wrong.");
				ioe.printStackTrace();
			} finally {
				//again:
				//close everything. If an error gets thrown before the FileInputStream or the ObjectInputStream
				//get initialized, it will throw an IOException. this checks to make sure they aren't null
				//increasing the probability that the resources can be successfully closed.
				if (objectOut != null) {
					try {
						objectOut.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fileOut != null) {
					try {
						fileOut.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
