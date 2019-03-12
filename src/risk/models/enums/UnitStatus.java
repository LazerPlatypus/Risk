package risk.models.enums;

import risk.controllers.Tools;

public enum UnitStatus {
	ACTIVE,
	INACTIVE;
	@Override
	public String toString() {
		String enumToString = this.name();
		return Tools.replaceUnderscoreAndCapitilizeEachWord(enumToString);
	}
}
