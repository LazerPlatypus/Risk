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

	public static Board CreateBoard(String[] playerNames) {
		HashMap<CountryName, Country> countries = new HashMap<>();
		for (int i = 0; i < 6; i++) {
			CountryName countryName = CountryName.values()[i];
			HashMap<TerritoryName, Territory> territories = new HashMap<>();
			switch (i) {
			case 0:
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
				territories.put(TerritoryName.ARGENTINA, new Territory(new ArrayList<Unit>(), TerritoryName.ARGENTINA));
				territories.put(TerritoryName.BRAZIL, new Territory(new ArrayList<Unit>(), TerritoryName.BRAZIL));
				territories.put(TerritoryName.PERU, new Territory(new ArrayList<Unit>(), TerritoryName.PERU));
				territories.put(TerritoryName.VENEZUELA, new Territory(new ArrayList<Unit>(), TerritoryName.VENEZUELA));
				break;
			case 2:
				territories.put(TerritoryName.GREAT_BRITAIN, new Territory(new ArrayList<Unit>(), TerritoryName.GREAT_BRITAIN));
				territories.put(TerritoryName.ICELAND, new Territory(new ArrayList<Unit>(), TerritoryName.ICELAND));
				territories.put(TerritoryName.NORTHERN_EUROPE, new Territory(new ArrayList<Unit>(), TerritoryName.NORTHERN_EUROPE));
				territories.put(TerritoryName.UKRAINE, new Territory(new ArrayList<Unit>(), TerritoryName.UKRAINE));
				territories.put(TerritoryName.WESTERN_EUROPE, new Territory(new ArrayList<Unit>(), TerritoryName.WESTERN_EUROPE));
				territories.put(TerritoryName.SOUTHERN_EUROPE, new Territory(new ArrayList<Unit>(), TerritoryName.SOUTHERN_EUROPE));
				territories.put(TerritoryName.SCANDINAVIA, new Territory(new ArrayList<Unit>(), TerritoryName.SCANDINAVIA));
				break;
			case 3:
				territories.put(TerritoryName.CONGO, new Territory(new ArrayList<Unit>(), TerritoryName.CONGO));
				territories.put(TerritoryName.EAST_AFRICA, new Territory(new ArrayList<Unit>(), TerritoryName.EAST_AFRICA));
				territories.put(TerritoryName.EGYPT, new Territory(new ArrayList<Unit>(), TerritoryName.EGYPT));
				territories.put(TerritoryName.MADAGASCAR, new Territory(new ArrayList<Unit>(), TerritoryName.MADAGASCAR));
				territories.put(TerritoryName.NORTH_AFRICA, new Territory(new ArrayList<Unit>(), TerritoryName.NORTH_AFRICA));
				territories.put(TerritoryName.SOUTH_AFRICA, new Territory(new ArrayList<Unit>(), TerritoryName.SOUTH_AFRICA));
				break;
			case 4:
				territories.put(TerritoryName.AFGHANISTAN, new Territory(new ArrayList<Unit>(), TerritoryName.AFGHANISTAN));
				territories.put(TerritoryName.CHINA, new Territory(new ArrayList<Unit>(), TerritoryName.CHINA));
				territories.put(TerritoryName.INDIA, new Territory(new ArrayList<Unit>(), TerritoryName.INDIA));
				territories.put(TerritoryName.IRKUTSK, new Territory(new ArrayList<Unit>(), TerritoryName.IRKUTSK));
				territories.put(TerritoryName.JAPAN, new Territory(new ArrayList<Unit>(), TerritoryName.JAPAN));
				territories.put(TerritoryName.KAMCATKA, new Territory(new ArrayList<Unit>(), TerritoryName.KAMCATKA));
				territories.put(TerritoryName.MIDDLE_EAST, new Territory(new ArrayList<Unit>(), TerritoryName.MIDDLE_EAST));
				territories.put(TerritoryName.MONGOLIA, new Territory(new ArrayList<Unit>(), TerritoryName.MONGOLIA));
				territories.put(TerritoryName.SIAM, new Territory(new ArrayList<Unit>(), TerritoryName.SIAM));
				territories.put(TerritoryName.SIBERIA, new Territory(new ArrayList<Unit>(), TerritoryName.SIBERIA));
				territories.put(TerritoryName.URAL, new Territory(new ArrayList<Unit>(), TerritoryName.URAL));
				territories.put(TerritoryName.YAKUTSK, new Territory(new ArrayList<Unit>(), TerritoryName.YAKUTSK));
				break;
			case 5:
				territories.put(TerritoryName.EASTERN_AUSTRALIA, new Territory(new ArrayList<Unit>(), TerritoryName.EASTERN_AUSTRALIA));
				territories.put(TerritoryName.INDONEASIA, new Territory(new ArrayList<Unit>(), TerritoryName.INDONEASIA));
				territories.put(TerritoryName.NEW_GUINEA, new Territory(new ArrayList<Unit>(), TerritoryName.NEW_GUINEA));
				territories.put(TerritoryName.WESTERN_AUSTRALIA, new Territory(new ArrayList<Unit>(), TerritoryName.WESTERN_AUSTRALIA));
				break;
			}
			countries.put(countryName, new Country(countryName, territories));
		}
		Map map = new Map(countries);
		ArrayList<Card> undrawnCards = new ArrayList<>();
		for (int i = 0; i < TerritoryName.values().length; i++) {
			undrawnCards.add(new Card(i%3==0?UnitName.CALVERY:i%1==0?UnitName.CANNON:UnitName.SOLDIER,TerritoryName.values()[i]));
		}
		ArrayList<Card> drawnCards = new ArrayList<>();
		Die[] attackDice = new Die[] {new Die(DieType.ATTACK, new Random(), 0), new Die(DieType.ATTACK, new Random(), 0), new Die(DieType.ATTACK, new Random(), 0)}; 
		Die[] defenceDice = new Die[] {new Die(DieType.DEFENCE, new Random(), 0), new Die(DieType.DEFENCE, new Random(), 0)};
		int numOfInctiveUnits = 50-playerNames.length*5;
		Player[] players = new Player[playerNames.length];
		for (int i = 0; i < players.length; i++) {
			String name = playerNames[i];
			ArrayList<Unit> inactiveUnits = new ArrayList<>();
			UnitColor unitColor = UnitColor.values()[i];
			for (int j = 0; j < numOfInctiveUnits; j++) {
				inactiveUnits.add(new Unit(unitColor, UnitStatus.INACTIVE));
			}
			ArrayList<Unit> activeUnits = new ArrayList<>();
			ArrayList<Card> cards = new ArrayList<>();
			ArrayList<Territory> ownedTerritories = new ArrayList<>();
			players[i] = new Player(name, inactiveUnits, activeUnits, cards, ownedTerritories); 
		}
		Player[] playerOrder = new Player[playerNames.length];
		for (int i = 0; i < playerOrder.length; i++) {
			playerOrder[i] = players[i];
		}
		Player activePlayer = playerOrder[0];
		return new Board(map, undrawnCards, drawnCards, attackDice, defenceDice, players, playerOrder, activePlayer, new int[] {0,0});
	}
	
	public static Board loadBoard() {
		Board loadedBoard = null;
		FileInputStream fileIn = null;
		ObjectInputStream objectIn = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load a Game");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Risk Save File", "*.rsf"));
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			try {
				fileIn = new FileInputStream(file);
				objectIn = new ObjectInputStream(fileIn);
				loadedBoard = (Board) objectIn.readObject();
			} catch (IOException ioe) {
				RiskController.Error("Invalid file path, please try again.");
			} catch (ClassNotFoundException cnfe) {
				RiskController.Error("That file does not contain a Risk game save (or it may be corrupted");
			} finally {
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
	
	public static void saveBoard(Board board) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Risk Save File", "*.rsf"));
		fileChooser.setTitle("Save Game");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			try {
				fileOut = new FileOutputStream(file);
				objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(board);
				RiskController.Error("Game saved succesfully");
			} catch (IOException ioe) {
				RiskController.Error("Something went terribly wrong.");
				ioe.printStackTrace();
			} finally {
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
