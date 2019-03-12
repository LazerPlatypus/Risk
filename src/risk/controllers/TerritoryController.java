package risk.controllers;

import java.io.NotActiveException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.CORBA.portable.ValueInputStream;

import com.sun.javafx.binding.BidirectionalBinding;

import risk.models.Board;
import risk.models.Country;
import risk.models.Territory;
import risk.models.enums.CountryName;
import risk.models.enums.TerritoryName;

public class TerritoryController extends RiskController {
	
	
	public static void setupAdjacentTerritories() {
		HashMap<TerritoryName, Territory>northAmericaTerritories = currentBoard.getMap().getCountries().get(CountryName.NORTH_AMERICA).getTerritories();
		HashMap<TerritoryName, Territory>southAmericaTerritories = currentBoard.getMap().getCountries().get(CountryName.SOUTH_AMERICA).getTerritories();
		HashMap<TerritoryName, Territory>europeTerritories = currentBoard.getMap().getCountries().get(CountryName.EUROPE).getTerritories();
		HashMap<TerritoryName, Territory>africaTerritories = currentBoard.getMap().getCountries().get(CountryName.AFRICA).getTerritories();
		HashMap<TerritoryName, Territory>asiaTerritories = currentBoard.getMap().getCountries().get(CountryName.ASIA).getTerritories();
		HashMap<TerritoryName, Territory>australiaTerritores = currentBoard.getMap().getCountries().get(CountryName.AUSTRALIA).getTerritories();
		for (Country country : currentBoard.getMap().getCountries().values()) {
			for (Territory territory : country.getTerritories().values()) {
				switch (territory.getTerritoryName()) {
				case ALASKA:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.NORTHWEST_TERRITORY),
								northAmericaTerritories.get(TerritoryName.ALBERTA), asiaTerritories.get(TerritoryName.KAMCATKA)});
					break;
				case ALBERTA:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.ALASKA),
							  northAmericaTerritories.get(TerritoryName.NORTHWEST_TERRITORY), northAmericaTerritories.get(TerritoryName.ONTARIO),
							  northAmericaTerritories.get(TerritoryName.WESTERN_UNITED_STATES)});
					break;
				case CENTERAL_AMERICA:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.WESTERN_UNITED_STATES),
							northAmericaTerritories.get(TerritoryName.EASTERN_UNITED_STATES), southAmericaTerritories.get(TerritoryName.VENEZUELA)});
					break;
				case EASTERN_UNITED_STATES:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.QUEBEC), northAmericaTerritories.get(TerritoryName.ONTARIO),
							northAmericaTerritories.get(TerritoryName.WESTERN_UNITED_STATES), northAmericaTerritories.get(TerritoryName.CENTERAL_AMERICA)});
					break;
				case GREENLAND:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.QUEBEC), northAmericaTerritories.get(TerritoryName.ONTARIO),
							northAmericaTerritories.get(TerritoryName.NORTHWEST_TERRITORY), europeTerritories.get(TerritoryName.ICELAND)});
					break;
				case NORTHWEST_TERRITORY:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.ALASKA), northAmericaTerritories.get(TerritoryName.ALBERTA),
							northAmericaTerritories.get(TerritoryName.ONTARIO), northAmericaTerritories.get(TerritoryName.GREENLAND)});
					break;
				case ONTARIO:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.NORTHWEST_TERRITORY), northAmericaTerritories.get(TerritoryName.ALBERTA),
							northAmericaTerritories.get(TerritoryName.WESTERN_UNITED_STATES), northAmericaTerritories.get(TerritoryName.EASTERN_UNITED_STATES),
							northAmericaTerritories.get(TerritoryName.QUEBEC), northAmericaTerritories.get(TerritoryName.GREENLAND)});
					break;
				case QUEBEC:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.ONTARIO), northAmericaTerritories.get(TerritoryName.EASTERN_UNITED_STATES),
							northAmericaTerritories.get(TerritoryName.GREENLAND)});
					break;
				case WESTERN_UNITED_STATES:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.ALBERTA), northAmericaTerritories.get(TerritoryName.ONTARIO),
							northAmericaTerritories.get(TerritoryName.EASTERN_UNITED_STATES), northAmericaTerritories.get(TerritoryName.CENTERAL_AMERICA)});
					break;
				case ARGENTINA:
					
					break;
				case BRAZIL:
					
					break;
				case PERU:
					
					break;
				case VENEZUELA:
					
					break;
				case GREAT_BRITAIN:
					
					break;
				case ICELAND:
					
					break;
				case NORTHERN_EUROPE:
					
					break;
				case SOUTHERN_EUROPE:
					
					break;
				case SCANDINAVIA:
					
					break;
				case UKRAINE:
					
					break;
				case WESTERN_EUROPE:
					
					break;
				case CONGO:
					
					break;
				case EAST_AFRICA:
					
					break;
				case EGYPT:
					
					break;
				case MADAGASCAR:
					
					break;
				case NORTH_AFRICA:
					
					break;
				case SOUTH_AFRICA:
					
					break;
				case AFGHANISTAN:
					
					break;
				case CHINA:
					
					break;
				case INDIA:
					
					break;
				case IRKUTSK:
					
					break;
				case JAPAN:
					
					break;
				case KAMCATKA:
					
					break;
				case MIDDLE_EAST:
					
					break;
				case MONGOLIA:
					
					break;
				case SIAM:
					
					break;
				case SIBERIA:
					
					break;
				case URAL:
					
					break;
				case YAKUTSK:
					
					break;
				case EASTERN_AUSTRALIA:
					
					break;
				case INDONEASIA:
					
					break;
				case NEW_GUINEA:
					
					break;
				case WESTERN_AUSTRALIA:
					
					break;
				}
			}
		}
	}
}
