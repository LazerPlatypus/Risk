package risk.models.enums;

import com.sun.glass.ui.TouchInputSupport;

import risk.controllers.Tools;

public enum TerritoryName {
	ALASKA,
	ALBERTA,
	CENTERAL_AMERICA,
	EASTERN_UNITED_STATES,
	GREENLAND,
	NORTHWEST_TERRITORY,
	CENTERAL_CANADA,
	EASTERN_CANADA,
	WESTERN_UNITED_STATES,
	ARGENTINA,
	BRAZIL,
	PERU,
	VENEZUELA,
	GREAT_BRITAIN_AND_IRELAND,
	ICELAND,
	NORTHERN_EUROPE,
	UKRAINE,
	WESTERN_EUROPE,
	CENTRAL_AFRICA,
	EAST_AFRICA,
	EGYPT,
	MADAGASCAR,
	NORTH_AFRICA,
	SOUTH_AFRICA,
	AFGHANISTAN,
	CHINA,
	INDIA,
	IRKUTSK,
	JAPAN,
	KAMCATKA,
	MIDDLE_EAST,
	MONGOLIA,
	SOUTHEAST_ASIA,
	SIBERIA,
	URAL,
	YAKUTSK,
	EASTERN_AUSTRALIA,
	INDONEASIA,
	NEW_GUINEA,
	WESTERN_AUSTRALIA;
	
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
