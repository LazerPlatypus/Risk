package risk.models.enums;

import risk.controllers.Tools;

public enum UnitName {
	SOLDIER,
	CALVERY,
	CANNON;
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
