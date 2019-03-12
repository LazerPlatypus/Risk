package risk.models.enums;

import risk.controllers.Tools;

public enum DieType {
	ATTACK,
	DEFENCE,
	ORDER;
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
