package risk.models.enums;

import risk.controllers.Tools;

public enum CountryName {
	NORTH_AMERICA,
	SOUTH_AMERICA,
	EUROPE,
	AFRICA,
	ASIA,
	AUSTRALIA;
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
