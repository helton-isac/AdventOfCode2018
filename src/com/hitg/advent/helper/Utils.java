package com.hitg.advent.helper;

public class Utils {

	/**
	 * Generate the next code incrementing letters from A to Z for example: A, B,
	 * C..., AA, AB, AC,... DFZ, DGA, DGB, etc...
	 * 
	 * @param code Any String with only characters from A to Z.
	 * @return The next char sequence
	 */
	public static String nextCode(String code) {
		if (code == null || code.length() == 0) {
			return "A";
		}

		char chars[] = code.toCharArray();

		// Validate input;
		for (char c : chars) {
			if (c < 'A' || c > 'Z') {
				throw new IllegalArgumentException("Only characters from A to Z are accepted in the string.");
			}
		}

		boolean shouldContinue = true;
		for (int i = chars.length - 1; i >= 0 && shouldContinue; i--) {
			if (chars[i] == 'Z') {
				shouldContinue = true;
				chars[i] = 'A';
			} else {
				shouldContinue = false;
				chars[i]++;
			}
		}
		return (shouldContinue ? "A" : "") + String.valueOf(chars);
	}
}
