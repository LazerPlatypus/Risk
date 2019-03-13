package risk.controllers;

//Quick little tools import from another project

//This capitalizes the first character of a string, and sets the rest to lowercase
public class Tools {
	public static String capitalizeWord(String str) { 
		str = str.trim();
		StringBuilder sBuilder = new StringBuilder(str.substring(0,1).toUpperCase());
		sBuilder.append(str.substring(1, str.length()).toLowerCase());
		return sBuilder.toString();
	}
	
	//this replaces every underscore in a string with spaces, and capitalizes each word made.
	public static String replaceUnderscoreAndCapitilizeEachWord(String input) { 
		String output = "";
		if (input != null) {
			if (!input.trim().isEmpty() ) {
				StringBuilder sBOutput = new StringBuilder();
				boolean previousWasUnderscore = false;
				for (int i = 0; i < input.length(); i++) {
					char currentChar = input.charAt(i);
					if (currentChar == '_') {
						if (!previousWasUnderscore) {
							sBOutput.append(' ');
							previousWasUnderscore = true;
						}
					} else {
						if (previousWasUnderscore) {
							sBOutput.append(Character.toUpperCase(currentChar));
						} else {
							sBOutput.append(Character.toLowerCase(currentChar));
						}
						previousWasUnderscore = false;
					}
				}
				output = sBOutput.toString();
				output = Character.toUpperCase(output.charAt(0))+output.substring(1);
				
			}
		} 
		return output;
	}
}
