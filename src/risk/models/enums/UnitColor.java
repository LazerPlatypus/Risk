package risk.models.enums;

import risk.controllers.Tools;

public enum UnitColor {
	RED,
	ORANGE,
	YELLOW,
	GREEN,
	BLUE,
	VIOLET;
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
