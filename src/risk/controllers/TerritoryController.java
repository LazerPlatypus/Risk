package risk.controllers;

import java.io.NotActiveException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.CORBA.portable.ValueInputStream;

import com.sun.javafx.binding.BidirectionalBinding;
import com.sun.swing.internal.plaf.metal.resources.metal;

import oracle.jrockit.jfr.tools.ConCatRepository;
import risk.models.Board;
import risk.models.Country;
import risk.models.Territory;
import risk.models.enums.CountryName;
import risk.models.enums.TerritoryName;
import sun.audio.AudioDataStream;

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
								northAmericaTerritories.get(TerritoryName.ALBERTA), asiaTerritories.get(TerritoryName.KAMCHATKA)});
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
					territory.setAdjacentTerritories(new Territory[] {southAmericaTerritories.get(TerritoryName.PERU), southAmericaTerritories.get(TerritoryName.BRAZIL)});
					break;
				case BRAZIL:
					territory.setAdjacentTerritories(new Territory[] {southAmericaTerritories.get(TerritoryName.VENEZUELA), southAmericaTerritories.get(TerritoryName.PERU),
							southAmericaTerritories.get(TerritoryName.ARGENTINA), africaTerritories.get(TerritoryName.NORTH_AFRICA)});
					break;
				case PERU:
					territory.setAdjacentTerritories(new Territory[] {southAmericaTerritories.get(TerritoryName.VENEZUELA), southAmericaTerritories.get(TerritoryName.BRAZIL),
							southAmericaTerritories.get(TerritoryName.ARGENTINA)});
					break;
				case VENEZUELA:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.CENTERAL_AMERICA), southAmericaTerritories.get(TerritoryName.BRAZIL),
							southAmericaTerritories.get(TerritoryName.PERU)});
					break;
				case GREAT_BRITAIN:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.ICELAND), europeTerritories.get(TerritoryName.SCANDINAVIA),
							europeTerritories.get(TerritoryName.NORTHERN_EUROPE), europeTerritories.get(TerritoryName.WESTERN_EUROPE)});
					break;
				case ICELAND:
					territory.setAdjacentTerritories(new Territory[] {northAmericaTerritories.get(TerritoryName.GREENLAND), europeTerritories.get(TerritoryName.GREAT_BRITAIN),
							europeTerritories.get(TerritoryName.SCANDINAVIA)});
					break;
				case NORTHERN_EUROPE:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.GREAT_BRITAIN),europeTerritories.get(TerritoryName.SCANDINAVIA),
							europeTerritories.get(TerritoryName.WESTERN_EUROPE), europeTerritories.get(TerritoryName.SOUTHERN_EUROPE), europeTerritories.get(TerritoryName.UKRAINE)});
					break;
				case SOUTHERN_EUROPE:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.WESTERN_EUROPE), europeTerritories.get(TerritoryName.NORTHERN_EUROPE),
							europeTerritories.get(TerritoryName.UKRAINE), asiaTerritories.get(TerritoryName.MIDDLE_EAST), africaTerritories.get(TerritoryName.EGYPT),
							africaTerritories.get(TerritoryName.NORTH_AFRICA)});
					break;
				case SCANDINAVIA:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.UKRAINE), europeTerritories.get(TerritoryName.NORTHERN_EUROPE),
							europeTerritories.get(TerritoryName.GREAT_BRITAIN), europeTerritories.get(TerritoryName.ICELAND)});
					break;
				case UKRAINE:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.SCANDINAVIA), europeTerritories.get(TerritoryName.NORTHERN_EUROPE),
							europeTerritories.get(TerritoryName.SOUTHERN_EUROPE), asiaTerritories.get(TerritoryName.MIDDLE_EAST), asiaTerritories.get(TerritoryName.AFGHANISTAN),
							asiaTerritories.get(TerritoryName.URAL)});
					break;
				case WESTERN_EUROPE:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.GREAT_BRITAIN), europeTerritories.get(TerritoryName.NORTHERN_EUROPE),
							europeTerritories.get(TerritoryName.SOUTHERN_EUROPE), africaTerritories.get(TerritoryName.NORTH_AFRICA)});
					break;
				case CONGO:
					territory.setAdjacentTerritories(new Territory[] {africaTerritories.get(TerritoryName.NORTH_AFRICA), africaTerritories.get(TerritoryName.EAST_AFRICA),
							africaTerritories.get(TerritoryName.SOUTH_AFRICA)});
					break;
				case EAST_AFRICA:
					territory.setAdjacentTerritories(new Territory[] {africaTerritories.get(TerritoryName.EGYPT), africaTerritories.get(TerritoryName.NORTH_AFRICA),
							africaTerritories.get(TerritoryName.CONGO), africaTerritories.get(TerritoryName.SOUTH_AFRICA), africaTerritories.get(TerritoryName.MADAGASCAR),
							asiaTerritories.get(TerritoryName.MIDDLE_EAST)});
					break;
				case EGYPT:
					territory.setAdjacentTerritories(new Territory[] {africaTerritories.get(TerritoryName.NORTH_AFRICA), africaTerritories.get(TerritoryName.EAST_AFRICA),
							europeTerritories.get(TerritoryName.SOUTHERN_EUROPE), asiaTerritories.get(TerritoryName.MIDDLE_EAST)});
					break;
				case MADAGASCAR:
					territory.setAdjacentTerritories(new Territory[] {africaTerritories.get(TerritoryName.SOUTH_AFRICA), africaTerritories.get(TerritoryName.EAST_AFRICA)});
					break;
				case NORTH_AFRICA:
					territory.setAdjacentTerritories(new Territory[] {africaTerritories.get(TerritoryName.EGYPT), africaTerritories.get(TerritoryName.EAST_AFRICA),
							africaTerritories.get(TerritoryName.CONGO), southAmericaTerritories.get(TerritoryName.BRAZIL), europeTerritories.get(TerritoryName.WESTERN_EUROPE),
							europeTerritories.get(TerritoryName.SOUTHERN_EUROPE)});
					break;
				case SOUTH_AFRICA:
					territory.setAdjacentTerritories(new Territory[] {africaTerritories.get(TerritoryName.CONGO), africaTerritories.get(TerritoryName.MADAGASCAR),
							africaTerritories.get(TerritoryName.EAST_AFRICA)});
					break;
				case AFGHANISTAN:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.UKRAINE), asiaTerritories.get(TerritoryName.URAL),
							asiaTerritories.get(TerritoryName.CHINA), asiaTerritories.get(TerritoryName.INDIA), asiaTerritories.get(TerritoryName.MIDDLE_EAST)});
					break;
				case CHINA:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.MONGOLIA), asiaTerritories.get(TerritoryName.SIBERIA),
							asiaTerritories.get(TerritoryName.URAL), asiaTerritories.get(TerritoryName.AFGHANISTAN), asiaTerritories.get(TerritoryName.INDIA),
							asiaTerritories.get(TerritoryName.SIAM)});
					break;
				case INDIA:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.MIDDLE_EAST), asiaTerritories.get(TerritoryName.AFGHANISTAN),
							asiaTerritories.get(TerritoryName.CHINA), asiaTerritories.get(TerritoryName.SIAM)});
					break;
				case IRKUTSK:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.SIBERIA), asiaTerritories.get(TerritoryName.YAKUTSK),
							asiaTerritories.get(TerritoryName.KAMCHATKA), asiaTerritories.get(TerritoryName.MONGOLIA)});
					break;
				case JAPAN:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.MONGOLIA), asiaTerritories.get(TerritoryName.KAMCHATKA)});
					break;
				case KAMCHATKA:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.YAKUTSK), asiaTerritories.get(TerritoryName.IRKUTSK),
							asiaTerritories.get(TerritoryName.MONGOLIA), asiaTerritories.get(TerritoryName.JAPAN), northAmericaTerritories.get(TerritoryName.ALASKA)});
					break;
				case MIDDLE_EAST:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.AFGHANISTAN), asiaTerritories.get(TerritoryName.INDIA),
							europeTerritories.get(TerritoryName.UKRAINE), europeTerritories.get(TerritoryName.SOUTHERN_EUROPE), africaTerritories.get(TerritoryName.EGYPT),
							africaTerritories.get(TerritoryName.EAST_AFRICA)});
					break;
				case MONGOLIA:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.IRKUTSK), asiaTerritories.get(TerritoryName.KAMCHATKA),
							asiaTerritories.get(TerritoryName.JAPAN), asiaTerritories.get(TerritoryName.SIBERIA), asiaTerritories.get(TerritoryName.CHINA)});
					break;
				case SIAM:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.INDIA), asiaTerritories.get(TerritoryName.CHINA),
							australiaTerritores.get(TerritoryName.INDONESIA)});
					break;
				case SIBERIA:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.URAL), asiaTerritories.get(TerritoryName.CHINA),
							asiaTerritories.get(TerritoryName.MONGOLIA), asiaTerritories.get(TerritoryName.IRKUTSK), asiaTerritories.get(TerritoryName.YAKUTSK)});
					break;
				case URAL:
					territory.setAdjacentTerritories(new Territory[] {europeTerritories.get(TerritoryName.UKRAINE), asiaTerritories.get(TerritoryName.AFGHANISTAN),
							asiaTerritories.get(TerritoryName.CHINA), asiaTerritories.get(TerritoryName.SIBERIA)});
					break;
				case YAKUTSK:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.KAMCHATKA), asiaTerritories.get(TerritoryName.SIBERIA),
							asiaTerritories.get(TerritoryName.IRKUTSK)});
					break;
				case EASTERN_AUSTRALIA:
					territory.setAdjacentTerritories(new Territory[] {australiaTerritores.get(TerritoryName.WESTERN_AUSTRALIA), australiaTerritores.get(TerritoryName.NEW_GUINEA)});
					break;
				case INDONESIA:
					territory.setAdjacentTerritories(new Territory[] {asiaTerritories.get(TerritoryName.SIAM), australiaTerritores.get(TerritoryName.NEW_GUINEA),
							australiaTerritores.get(TerritoryName.WESTERN_AUSTRALIA)});
					break;
				case NEW_GUINEA:
					territory.setAdjacentTerritories(new Territory[] {australiaTerritores.get(TerritoryName.INDONESIA), australiaTerritores.get(TerritoryName.WESTERN_AUSTRALIA),
							australiaTerritores.get(TerritoryName.EASTERN_AUSTRALIA)});
					break;
				case WESTERN_AUSTRALIA:
					territory.setAdjacentTerritories(new Territory[] {australiaTerritores.get(TerritoryName.INDONESIA), australiaTerritores.get(TerritoryName.NEW_GUINEA),
							australiaTerritores.get(TerritoryName.EASTERN_AUSTRALIA)});
					break;
				}
			}
		}
		currentBoard.getMap().getCountries().get(CountryName.AFRICA).setTerritories(africaTerritories);
		currentBoard.getMap().getCountries().get(CountryName.NORTH_AMERICA).setTerritories(northAmericaTerritories);
		currentBoard.getMap().getCountries().get(CountryName.SOUTH_AMERICA).setTerritories(southAmericaTerritories);
		currentBoard.getMap().getCountries().get(CountryName.EUROPE).setTerritories(europeTerritories);
		currentBoard.getMap().getCountries().get(CountryName.ASIA).setTerritories(asiaTerritories);
	}
}
