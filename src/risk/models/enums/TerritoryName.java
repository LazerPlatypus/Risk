package risk.models.enums;


import risk.controllers.Tools;

public enum TerritoryName {
	ALASKA,
	ALBERTA,
	CENTERAL_AMERICA,
	EASTERN_UNITED_STATES,
	GREENLAND,
	NORTHWEST_TERRITORY,
	ONTARIO,
	QUEBEC,
	WESTERN_UNITED_STATES,
	ARGENTINA,
	BRAZIL,
	PERU,
	VENEZUELA,
	GREAT_BRITAIN,
	ICELAND,
	NORTHERN_EUROPE,
	SOUTHERN_EUROPE,
	SCANDINAVIA,
	UKRAINE,
	WESTERN_EUROPE,
	CONGO,
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
	KAMCHATKA,
	MIDDLE_EAST,
	MONGOLIA,
	SIAM,
	SIBERIA,
	URAL,
	YAKUTSK,
	EASTERN_AUSTRALIA,
	INDONESIA,
	NEW_GUINEA,
	WESTERN_AUSTRALIA;
	
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
